package com.example.bletutorial

import android.annotation.SuppressLint
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Looper
import android.os.Parcelable
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.location.LocationRequestCompat.*
import com.example.bletutorial.presentation.Navigation
import com.example.bletutorial.ui.theme.BLETutorialTheme
import com.google.android.gms.location.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

@Parcelize
data class UserLocation(
    var latitude: Double,
    var longitude: Double
) : Parcelable

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var bluetoothAdapter: BluetoothAdapter

    // FusedLocationProviderClient - Main class for receiving location updates.
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    //private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback

    companion object{
        const val PKEY = "user_location"
    }

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Initialize location provider client and define requests
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        val locationRequest = LocationRequest.create().apply {
            interval = 10000
            fastestInterval = 5000
            priority = Priority.PRIORITY_HIGH_ACCURACY
        }



        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                locationResult ?: return
                for (location in locationResult.locations){

                    Log.d("map debug", "Current Lat: ${location.latitude}, Current LNG: ${location.longitude}")

                    //Creating a parcel to send location data from MainActivity to MapScreen
                    val myPos = UserLocation(location.latitude, location.longitude)
                    Log.d("map debug", "$myPos, ${myPos.latitude}, ${myPos.longitude}")
                    //TODO - some issue is happening with the intent passing the data as null
                    //figure out why this is not working and how to pass the data from
                    //MainActivity to MapsActivity correctly

                    //val intent = Intent(this@MainActivity, MapsActivity::class.java)
                    //intent.putExtra(PKEY, arrayOf(myPos.latitude,myPos.longitude))
                    //startActivity(intent)
                }
            }
        }

        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper())

        setContent {
            BLETutorialTheme {
                Navigation(
                    onBluetoothStateChanged = {
                        showBluetoothDialog()
                    }
                )
            }
        }

    }




    override fun onStart() {
        super.onStart()
        showBluetoothDialog()
    }

    private var isBluetoothDialogAlreadyShown = false
    private fun showBluetoothDialog(){
            //prompts user to enable bluetooth, repeatedly if denied
            if(!bluetoothAdapter.isEnabled){
                if(!isBluetoothDialogAlreadyShown) {
                    val enableBluetoothIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                    startBluetoothIntentForResult.launch(enableBluetoothIntent)
                    isBluetoothDialogAlreadyShown = true
                }
            }
    }

    private val startBluetoothIntentForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
            isBluetoothDialogAlreadyShown = false
            if(result.resultCode != Activity.RESULT_OK){
                showBluetoothDialog()
            }
        }


    @SuppressLint("MissingPermission")
    fun getLastKnownLocation(): List<Double> {
        val myPos: MutableList<Double> = mutableListOf()
        if (!isLocationPermissionGranted())
        {
            ActivityCompat.requestPermissions(this,
                arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION),
                1234)
        }
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                if (location != null) {
                    myPos.add(location.latitude)
                    myPos.add(location.longitude)
                    Log.d("map debug","My position is: $myPos")
                }
                else {Log.d("map debug", "fusedLocationClient returned null for location")}
            }

        return myPos
    }

    private fun isLocationPermissionGranted(): Boolean {
        return if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                1234
            )
            false
        } else {
            true
        }
    }

}




