package com.zhuzichu.base.http.exception

enum class HttpCode(val code: Int, val msg: String) {
    UNAUTHORIZED(401, "操作未授权"),
    FORBIDDEN(403, "请求被拒绝"),
    NOT_FOUND(404, "资源不存在"),
    REQUEST_TIMEOUT(408, "服务器执行超时"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误"),
    SERVICE_UNAVAILABLE(503, "服务器不可用"),

    UNKNOWN(1000, "未知错误"),
    PARSE_ERROR(1001, "解析错误"),
    NETWORD_ERROR(1002, "网络错误"),
    HTTP_ERROR(1003, "协议错误"),
    SSL_ERROR(1005, "证书验证失败"),
    TIMEOUT_ERROR(1006, "连接超时"),

    //业务自定义
    SUCCESS(1, "请求成功"),
    PASSWORD_ERROR(100, "密码错误")
}