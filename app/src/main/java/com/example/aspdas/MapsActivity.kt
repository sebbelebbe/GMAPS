package com.example.aspdas


import android.content.pm.PackageManager
import android.location.Location
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback,GoogleMap.OnMarkerClickListener {
    override fun onMarkerClick(p0: Marker?) = false

    private lateinit var map: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var lastLocation: Location

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {

        map = googleMap
        map.getUiSettings().setZoomControlsEnabled(true)
        map.setOnMarkerClickListener(this)
        map.isMyLocationEnabled = true

        val Lyngby = LatLng(55.770185,12.511951)
        val City = LatLng(55.682041, 12.576234)
        val Hilleroed = LatLng(55.920099, 12.282106)
        val Noerrebro = LatLng(55.686137, 12.559091)
        val Soeerne = LatLng(55.682085, 12.562409)
        val Bornholm = LatLng(55.106786, 14.712701)


        this.map.addMarker(MarkerOptions().position(Lyngby).title("CPHBusiness Lyngby"))
        this.map.addMarker(MarkerOptions().position(City).title("CPHBusiness City"))
        this.map.addMarker(MarkerOptions().position(Hilleroed).title("CPHBusiness Hillerød"))
        this.map.addMarker(MarkerOptions().position(Noerrebro).title("CPHBusiness Nørrebro"))
        this.map.addMarker(MarkerOptions().position(Soeerne).title("CPHBusiness Søerne"))
        this.map.addMarker(MarkerOptions().position(Bornholm).title("CPHBusiness Bornholm"))

        fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->
            setUpMap()
            if (location != null) {
                lastLocation = location
                val currentLatLng = LatLng(location.latitude, location.longitude)

                map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 12f))
            }

    }

    }
    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }
    private fun setUpMap() {
        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
            return
        }
    }



}
