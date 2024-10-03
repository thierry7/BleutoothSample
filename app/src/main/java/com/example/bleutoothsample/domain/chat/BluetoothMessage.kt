package com.example.bleutoothsample.domain.chat

/** ??
 Create a message class
 */
data class BluetoothMessage(
    val message: String,
    val senderName: String,
    val isFromLocalUser: Boolean
)