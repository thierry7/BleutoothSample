package com.example.bleutoothsample.data.chat

import android.bluetooth.BluetoothSocket
import com.example.bleutoothsample.domain.chat.BluetoothMessage
import com.example.bleutoothsample.domain.chat.TransferFailedException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import java.io.IOException

/**
 * Service to handle the sending and receiving of data
 * Takes a socket
 *
 */

class BluetoothDataTransferService(
    private val socket: BluetoothSocket
) {

    /**
     * Listen ning to the incoming messages
     */
    fun listenForIncomingMessages(): Flow<BluetoothMessage> {
        return flow {
            if(!socket.isConnected) {
                return@flow
            }
            //Just a place holder to keep our byte
            val buffer = ByteArray(1024)
            while(true) {
                val byteCount = try {
                    socket.inputStream.read(buffer)
                } catch(e: IOException) {
                    throw TransferFailedException()
                }

                emit(
                    buffer.decodeToString(
                        endIndex = byteCount
                    ).toBluetoothMessage(
                        isFromLocalUser = false
                    )
                )
            }
        }.flowOn(Dispatchers.IO)
    }

    /**
     * Function to send messages
     */
    suspend fun sendMessage(bytes: ByteArray): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                socket.outputStream.write(bytes)
            } catch(e: IOException) {
                e.printStackTrace()
                return@withContext false
            }

            true
        }
    }
}