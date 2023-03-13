package com.example.bletutorial

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bletutorial.databinding.ActivityMapsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding



        //intent.getParcelableExtra(MainActivity.PKEY) as UserLocation?

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        //val myLat  = intent.getDoubleExtra(MainActivity.PKEYLAT, 0.0)
        //val myLong = intent.getDoubleExtra(MainActivity.PKEYLONG, 0.0)
        val myPos = intent.getParcelableExtra(MainActivity.PKEY) as UserLocation?
        Log.d("map debug", "My position is ${myPos?.latitude}, ${myPos?.longitude}")
        mMap = googleMap

        val myLat = myPos!!.latitude
        val myLong = myPos!!.longitude
        // Add a marker in Sydney and move the camera

        val crimeLog: Button = findViewById(R.id.logButton)
        crimeLog.setOnClickListener{
            //val sydney = LatLng((-90..90).random().toDouble(), (-180..180).random().toDouble())
            val currentLocation = LatLng(myLat, myLong)
            mMap.addMarker(MarkerOptions().position(currentLocation).title("Marker in Sydney"))
            mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation))

            val toast = Toast.makeText(applicationContext, "Data logged", Toast.LENGTH_SHORT)
            toast.show()
        }

    }
}