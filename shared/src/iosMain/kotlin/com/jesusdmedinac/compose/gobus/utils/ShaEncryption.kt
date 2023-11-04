package com.jesusdmedinac.compose.gobus.utils

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.UByteVar
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.cstr
import kotlinx.cinterop.get
import kotlinx.cinterop.nativeHeap
import platform.CoreCrypto.CC_SHA256
import platform.CoreCrypto.CC_SHA256_DIGEST_LENGTH

@OptIn(ExperimentalForeignApi::class)
actual fun sha256(input: String): String {
    val inputBytes = input.cstr
    val inputLength = inputBytes.size.toULong()

    val outputBuffer = nativeHeap.allocArray<UByteVar>(CC_SHA256_DIGEST_LENGTH)

    CC_SHA256(inputBytes, inputLength.toUInt(), outputBuffer)

    val hexChars = CharArray(CC_SHA256_DIGEST_LENGTH * 2)
    for (i in 0 until CC_SHA256_DIGEST_LENGTH) {
        val byte = outputBuffer[i].toUByte()
        hexChars[i * 2] = "0123456789ABCDEF"[byte.toInt() ushr 4]
        hexChars[i * 2 + 1] = "0123456789ABCDEF"[byte.toInt() and 0x0F]
    }

    // TODO: We need to free the memory allocated for the outputBuffer
    // nativeHeap.free(outputBuffer)

    return hexChars.concatToString()
}
