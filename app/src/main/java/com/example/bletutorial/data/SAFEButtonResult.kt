package com.example.bletutorial.data

//current place holders based on YT tutorial, can be changed later on to include different
//data from the BLE chip
data class SAFEButtonResult(
    val temperature:Float,
    val humidity:Float,
    val connectionState: ConnectionState
)
