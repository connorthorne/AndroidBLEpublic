package com.example.bletutorial.presentation

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState


@Composable
fun MapScreen(
    navController: NavController
) {

        //val myPos = intent.getParcelableExtra("UserLocation")
/*    return try {
        MainActivity().getLastKnownLocation()
        val myLat = myPos[0]
        val myLng = myPos[1]

        Log.d("map debug","Lat is $myLat Long is $myLng")
        return myPos
    } catch (e: NullPointerException) {
        Log.d("map debug", "Coordinates loaded as null")
        myPos = arrayOf(1.35,103.87)
        return myPos
    }*/

    try{

    } catch (e: NullPointerException){
        val myPos = mutableListOf(1.35,103.87)
        Log.d("map debug", "Coordinates loaded as null")
    }

    //https://developers.google.com/maps/documentation/android-sdk/maps-compose
    //https://googlemaps.github.io/android-maps-compose/maps-compose/com.google.maps.android.compose/-google-map.html
    //val singapore = LatLng(1.35, 103.87)
    //Ucross - 39.9560729,-75.1924832
    val myPosition = LatLng(39.9536534, -75.1890614)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(myPosition, 17f)

    }
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
    }
}
