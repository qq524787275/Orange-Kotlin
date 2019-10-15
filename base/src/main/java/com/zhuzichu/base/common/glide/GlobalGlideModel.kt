package com.zhuzichu.base.common.glide

import android.content.Context
import android.os.Handler
import android.os.Looper
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import com.zhuzichu.base.global.CacheGlobal
import okhttp3.*
import okio.*
import java.io.IOException
import java.io.InputStream
import java.util.HashMap

@GlideModule
class GlobalGlideModel : AppGlideModule() {

    companion object {
        private const val M = 1024 * 1024L
        private const val MAX_DISK_CACHE_SIZE = 256 * M

        fun forget(url: String) {
            DispatchingProgressListener.forget(url)
        }

        fun expect(url: String, listener: UiOnProgressListener) {
            DispatchingProgressListener.expect(url, listener)
        }
    }

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        builder.setDiskCache(
            DiskLruCacheFactory(
                CacheGlobal.getGlideCacheDir().absolutePath,
                MAX_DISK_CACHE_SIZE
            )
        )
    }

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        val client = OkHttpClient.Builder()
            .addNetworkInterceptor {
                val request = it.request()
                val response = it.proceed(request)
                val listener = DispatchingProgressListener()
                response.body()?.let { body ->
                    response.newBuilder()
                        .body(OkHttpProgressResponseBody(request.url(), body, listener))
                        .build()
                }
            }
            .build()
        registry.replace(
            GlideUrl::class.java,
            InputStream::class.java,
            OkHttpUrlLoader.Factory(client)
        )
    }

    override fun isManifestParsingEnabled(): Boolean {
        return false
    }

    class DispatchingProgressListener : ResponseProgressListener {

        companion object {
            private val LISTENERS = HashMap<String, UiOnProgressListener>()
            private val PROGRESSES = HashMap<String, Long>()

            internal fun forget(url: String) {
                LISTENERS.remove(url)
                PROGRESSES.remove(url)
            }

            internal fun expect(url: String, listener: UiOnProgressListener) {
                LISTENERS[url] = listener
            }
        }

        private val handler: Handler by lazy { Handler(Looper.getMainLooper()) }

        override fun update(url: HttpUrl, bytesRead: Long, contentLength: Long) {
            val key = url.toString()
            val listener = LISTENERS[key] ?: return
            if (contentLength <= bytesRead) {
                forget(key)
            }
            if (needsDispatch(key, bytesRead, contentLength, listener.getGranualityPercentage())) {
                handler.post { listener.onProgress(bytesRead, contentLength) }
            }

        }

        private fun needsDispatch(
            key: String,
            current: Long,
            total: Long,
            granularity: Float
        ): Boolean {
            if (granularity == 0f || current == 0L || total == current) {
                return true
            }
            val percent = 100f * current / total
            val currentProgress = (percent / granularity).toLong()
            val lastProgress = PROGRESSES[key]
            return if (lastProgress == null || currentProgress != lastProgress) {
                PROGRESSES[key] = currentProgress
                true
            } else {
                false
            }
        }
    }

    class OkHttpProgressResponseBody(
        private val url: HttpUrl,
        private val responseBody: ResponseBody,
        private val progressListener: ResponseProgressListener
    ) : ResponseBody() {

        private val bufferedSource: BufferedSource by lazy { Okio.buffer(source(responseBody.source())) }

        override fun contentLength(): Long {
            return responseBody.contentLength()
        }

        override fun contentType(): MediaType? {
            return responseBody.contentType()
        }

        override fun source(): BufferedSource {
            return bufferedSource
        }

        private fun source(source: Source): Source {
            return object : ForwardingSource(source) {
                var totalBytesRead = 0L
                @Throws(IOException::class)
                override fun read(sink: Buffer, byteCount: Long): Long {
                    val bytesRead = super.read(sink, byteCount)
                    val fullLength = responseBody.contentLength()
                    if (bytesRead == -1L) {
                        totalBytesRead = fullLength
                    } else {
                        totalBytesRead += bytesRead
                    }
                    progressListener.update(url, totalBytesRead, fullLength)
                    return bytesRead
                }
            }
        }
    }

    interface UiOnProgressListener {
        fun getGranualityPercentage(): Float
        fun onProgress(bytesRead: Long, expectedLength: Long)
    }

    interface ResponseProgressListener {
        fun update(url: HttpUrl, bytesRead: Long, contentLength: Long)
    }
}