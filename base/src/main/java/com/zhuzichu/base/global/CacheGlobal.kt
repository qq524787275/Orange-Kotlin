package com.zhuzichu.base.global

import android.text.format.Formatter
import com.zhuzichu.base.common.glide.GlideApp
import com.zhuzichu.base.ext.isSDCardMounted
import java.io.File

object CacheGlobal {
    private const val GLIDE_CACHE_DIR_NAME = "/glide_cache"

    private const val HTTP_CACHE_DIR_NAME = "/http_response_cache"

    private const val CAMERA_CACHE_DIR_NAME = "/camera_cache"

    private const val DOWNLOAD_CACHE_DIR_NAME = "/download_cache"

    fun initDir() {
        getGlideCacheDir()
        getHttpCacheDir()
        getCameraCacheDir()
        getDownLoadCacheDir()
    }

    private fun getDiskCacheDir(last: String): File {
        val path: String = if (isSDCardMounted()) {
            AppGlobal.context.externalCacheDir.toString() + last
        } else {
            AppGlobal.context.cacheDir.toString() + last
        }
        val file = File(path)
        if (!file.exists()) {
            file.mkdirs()
        }
        return file.absoluteFile
    }

    private fun getDownLoadCacheDir(): File {
        return getDiskCacheDir(DOWNLOAD_CACHE_DIR_NAME)
    }

    private fun getCameraCacheDir(): File {
        return getDiskCacheDir(CAMERA_CACHE_DIR_NAME)
    }

    fun getHttpCacheDir(): File {
        return getDiskCacheDir(HTTP_CACHE_DIR_NAME)
    }

    fun getHttpCacheFileSizeStr(): String {
        val fileSize = getHttpCacheFileSizeNum()
        return Formatter.formatFileSize(AppGlobal.context, fileSize)
    }

    private fun getHttpCacheFileSizeNum(): Long {
        var fileSize: Long = 0
        val file = getHttpCacheDir()
        file.listFiles()?.let {
            for (childFile in it) {
                fileSize += childFile.length()
            }
        }
        return fileSize
    }


    fun getGlideCacheDir(): File {
        return getDiskCacheDir(GLIDE_CACHE_DIR_NAME)
    }

    fun getGlideCacheFileSizeStr(): String {
        val fileSize = getGlideCacheFileSizeNum()
        return Formatter.formatFileSize(AppGlobal.context, fileSize)
    }

    private fun getGlideCacheFileSizeNum(): Long {
        var fileSize: Long = 0
        val file = getGlideCacheDir()
        file.listFiles()?.let {
            for (childFile in it) {
                fileSize += childFile.length()
            }
        }
        return fileSize
    }

}