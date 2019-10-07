package com.zhuzichu.base.ext

import java.io.ByteArrayOutputStream
import java.io.InputStream

fun InputStream.toByteArray(): ByteArray {
    val output = ByteArrayOutputStream()
    write(this, output)
    output.close()
    return output.toByteArray()
}

private fun write(inputStream: InputStream, outputStream: ByteArrayOutputStream) {
    var len: Int
    val buffer = ByteArray(4096)
    while (inputStream.read(buffer).also { len = it } != -1) outputStream.write(buffer, 0, len)
}
