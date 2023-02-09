package com.example.bletutorial.data

import com.example.bletutorial.util.Resource
import kotlinx.coroutines.flow.MutableSharedFlow

interface SAFEButtonReceiveManager {

    val data: MutableSharedFlow<Resource<SAFEButtonResult>>

    fun reconnect()

    fun disconnect()

    fun startReceiving()

    fun closeConnection()

}