package com.jesusdmedinac.compose.gobus.utils

import java.security.MessageDigest

actual fun sha256(input: String): String {
    val bytes = input.toByteArray()
    val md = MessageDigest.getInstance("SHA-256")
    val digest = md.digest(bytes)

    // Convertir el resultado en una cadena hexadecimal
    val hexChars = CharArray(digest.size * 2)
    for (i in digest.indices) {
        val v = digest[i].toInt() and 0xFF
        hexChars[i * 2] = "0123456789ABCDEF"[v ushr 4]
        hexChars[i * 2 + 1] = "0123456789ABCDEF"[v and 0x0F]
    }
    return String(hexChars)
}
