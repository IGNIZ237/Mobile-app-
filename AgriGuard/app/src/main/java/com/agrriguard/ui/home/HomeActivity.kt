package com.agrriguard.ui.home

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.agrriguard.AgriGuardApp
import com.agrriguard.R
import com.agrriguard.databinding.ActivityHomeBinding
import com.agrriguard.ui.camera.CameraActivity
import com.agrriguard.ui.map.MapActivity
import com.agrriguard.utils.GpsUtils

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var alertAdapter: AlertAdapter
    private val viewModel: HomeViewModel by viewModels {
        HomeViewModelFactory((application as AgriGuardApp).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupClickListeners()
        observeViewModel()
        checkPermissions()
    }

    private fun setupRecyclerView() {
        alertAdapter = AlertAdapter()
        binding.recyclerAlerts.layoutManager = LinearLayoutManager(this)
        binding.recyclerAlerts.adapter = alertAdapter

        viewModel.allReports.observe(this) { reports ->
            alertAdapter.submitList(reports)
        }
    }

    private fun setupClickListeners() {
        binding.cardDiagnose.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            startActivity(intent)
        }
        
        binding.cardMap.setOnClickListener {
            val intent = Intent(this, MapActivity::class.java)
            startActivity(intent)
        }
    }

    private fun observeViewModel() {
        viewModel.isGpsActive.observe(this) { isActive ->
            binding.txtGpsStatus.text = if (isActive) getString(R.string.gps_active) else getString(R.string.gps_inactive)
            binding.txtGpsStatus.setTextColor(
                ContextCompat.getColor(this, if (isActive) R.color.primary else R.color.error)
            )
            
            if (isActive) {
                updateUserLocation()
            }
        }
    }

    private fun updateUserLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            GpsUtils.getCurrentLocation(this).addOnSuccessListener { location ->
                if (location != null) {
                    alertAdapter.updateLocation(location)
                }
            }
        }
    }

    private fun checkPermissions() {
        val permissions = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )

        val missingPermissions = permissions.filter {
            ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED
        }

        if (missingPermissions.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, missingPermissions.toTypedArray(), PERMISSION_REQUEST_CODE)
        } else {
            viewModel.setGpsStatus(true)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            val allGranted = grantResults.all { it == PackageManager.PERMISSION_GRANTED }
            viewModel.setGpsStatus(allGranted)
            if (!allGranted) {
                Toast.makeText(this, "Permissions requises pour le bon fonctionnement", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        private const val PERMISSION_REQUEST_CODE = 1001
    }
}
