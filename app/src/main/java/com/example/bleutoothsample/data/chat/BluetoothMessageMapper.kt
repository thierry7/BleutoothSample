package com.example.bleutoothsample.data.chat

import com.example.bleutoothsample.domain.chat.BluetoothMessage

/**
Create an helper extension to convert the Message from string
to byte as with Bleutooth we need to send
byte from one location to another
*  */

fun String.toBluetoothMessage(isFromLocalUser: Boolean): BluetoothMessage {
    val name = substringBeforeLast("#")
    val message = substringAfter("#")
    return BluetoothMessage(
        message = message,
        senderName = name,
        isFromLocalUser = isFromLocalUser
    )
}

fun BluetoothMessage.toByteArray(): ByteArray {
    return "$senderName#$message".encodeToByteArray()
}