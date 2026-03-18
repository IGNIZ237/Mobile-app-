package com.agrriguard.ui.map

import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.agrriguard.AgriGuardApp
import com.agrriguard.databinding.ActivityMapBinding
import com.agrriguard.ui.home.HomeViewModel
import com.agrriguard.ui.home.HomeViewModelFactory
import com.agrriguard.utils.GpsUtils
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.Polygon

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
        
        val startPoint = GeoPoint(3.8480, 11.5021)
        binding.mapView.controller.setZoom(18.0) // Zoom plus proche pour voir les maisons
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

            val groupedByDisease = reports.groupBy { it.diseaseName }
            
            groupedByDisease.forEach { (disease, diseaseReports) ->
                diseaseReports.forEach { report ->
                    val nearbyCount = diseaseReports.count { 
                        GpsUtils.calculateDistance(report.latitude, report.longitude, it.latitude, it.longitude) < 0.1 // 100 mètres
                    }

                    if (nearbyCount >= 3) {
                        drawRiskCircle(GeoPoint(report.latitude, report.longitude), disease)
                    }
                }
            }

            binding.mapView.invalidate()
        }
    }

    private fun drawRiskCircle(center: GeoPoint, diseaseName: String) {
        val circlePoints = Polygon.pointsAsCircle(center, 50.0) // Rayon réduit à 50 mètres pour plus de précision (taille d'une parcelle/maison)
        val circle = Polygon(binding.mapView)
        circle.points = circlePoints
        circle.fillPaint.color = Color.argb(80, 255, 0, 0) // Un peu plus opaque
        circle.outlinePaint.color = Color.RED
        circle.outlinePaint.strokeWidth = 2f
        circle.title = "ZONE À HAUT RISQUE : $diseaseName"
        binding.mapView.overlays.add(circle)
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
