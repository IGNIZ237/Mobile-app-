package com.agrriguard.ui.map

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.agrriguard.AgriGuardApp
import com.agrriguard.databinding.ActivityMapBinding
import com.agrriguard.ui.home.HomeViewModel
import com.agrriguard.ui.home.HomeViewModelFactory
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.Marker

class MapActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMapBinding
    private val viewModel: HomeViewModel by viewModels {
        HomeViewModelFactory((application as AgriGuardApp).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupMap()
        observeReports()

        binding.btnBack.setOnClickListener { finish() }
    }

    private fun setupMap() {
        binding.mapView.setTileSource(TileSourceFactory.MAPNIK)
        binding.mapView.setMultiTouchControls(true)
        
        // Centrer sur le Cameroun par défaut (Yaoundé environ)
        val startPoint = GeoPoint(3.8480, 11.5021)
        binding.mapView.controller.setZoom(7.0)
        binding.mapView.controller.setCenter(startPoint)
    }

    private fun observeReports() {
        viewModel.allReports.observe(this) { reports ->
            binding.mapView.overlays.clear()
            reports.forEach { report ->
                val marker = Marker(binding.mapView)
                marker.position = GeoPoint(report.latitude, report.longitude)
                marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                marker.title = report.diseaseName
                marker.snippet = "Culture: ${report.cropType}"
                binding.mapView.overlays.add(marker)
            }
            binding.mapView.invalidate()
        }
    }

    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.mapView.onPause()
    }
}
