package com.zhuzichu.base.http.exception

import android.net.ParseException
import android.util.MalformedJsonException
import com.google.gson.JsonParseException
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLException


class ExceptionHandle {
    companion object {
        fun handleException(e: Throwable): ResponseThrowable {
            val ex = ResponseThrowable()
            if (e is HttpException) {
                when (e.code()) {
                    HttpCode.UNAUTHORIZED.code -> {
                        ex.msg = HttpCode.UNAUTHORIZED.msg
                        ex.code = HttpCode.UNAUTHORIZED.code
                    }
                    HttpCode.FORBIDDEN.code -> {
                        ex.msg = HttpCode.FORBIDDEN.msg
                        ex.code = HttpCode.FORBIDDEN.code
                    }
                    HttpCode.NOT_FOUND.code -> {
                        ex.msg = HttpCode.NOT_FOUND.msg
                        ex.code = HttpCode.NOT_FOUND.code
                    }
                    HttpCode.REQUEST_TIMEOUT.code -> {
                        ex.msg = HttpCode.REQUEST_TIMEOUT.msg
                        ex.code = HttpCode.REQUEST_TIMEOUT.code
                    }
                    HttpCode.INTERNAL_SERVER_ERROR.code -> {
                        ex.msg = HttpCode.INTERNAL_SERVER_ERROR.msg
                        ex.code = HttpCode.INTERNAL_SERVER_ERROR.code
                    }
                    HttpCode.SERVICE_UNAVAILABLE.code -> {
                        ex.msg = HttpCode.SERVICE_UNAVAILABLE.msg
                        ex.code = HttpCode.SERVICE_UNAVAILABLE.code
                    }
                    else -> {
                        ex.msg = HttpCode.NETWORD_ERROR.msg
                        ex.code = HttpCode.NETWORD_ERROR.code
                    }
                }
            } else if (e is JsonParseException || e is JSONException || e is ParseException || e is MalformedJsonException) {
                ex.msg = HttpCode.PARSE_ERROR.msg
                ex.code = HttpCode.PARSE_ERROR.code
            } else if (e is ConnectException) {
                ex.msg = HttpCode.NETWORD_ERROR.msg
                ex.code = HttpCode.NETWORD_ERROR.code
            } else if (e is SSLException) {
                ex.msg = HttpCode.SSL_ERROR.msg
                ex.code = HttpCode.SSL_ERROR.code
            } else if (e is SocketTimeoutException || e is UnknownHostException) {
                ex.msg = HttpCode.TIMEOUT_ERROR.msg
                ex.code = HttpCode.TIMEOUT_ERROR.code
            } else if (e is ResponseThrowable) {
                ex.msg = e.msg
                ex.code = e.code
            } else {
                ex.msg = HttpCode.UNKNOWN.msg
                ex.code = HttpCode.UNKNOWN.code
            }
            return ex
        }
    }
}