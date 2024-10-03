package com.example.bleutoothsample.data.chat

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import com.example.bleutoothsample.domain.chat.BluetoothDeviceDomain

@SuppressLint("MissingPermission")
fun BluetoothDevice.toBluetoothDeviceDomain(): BluetoothDeviceDomain {
    return BluetoothDeviceDomain(
        name = name,
        address = address
    )
}