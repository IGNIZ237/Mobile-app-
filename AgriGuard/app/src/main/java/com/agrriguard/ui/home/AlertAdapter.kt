package com.agrriguard.ui.home

import android.location.Location
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.agrriguard.R
import com.agrriguard.data.model.DiseaseReport
import com.agrriguard.utils.GpsUtils
import java.text.SimpleDateFormat
import java.util.*

class AlertAdapter : ListAdapter<DiseaseReport, AlertAdapter.AlertViewHolder>(AlertDiffCallback()) {

    private var currentUserLocation: Location? = null

    fun updateLocation(location: Location) {
        currentUserLocation = location
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlertViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_alert, parent, false)
        return AlertViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlertViewHolder, position: Int) {
        holder.bind(getItem(position), currentUserLocation)
    }

    class AlertViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtDisease: TextView = itemView.findViewById(R.id.txt_alert_disease)
        private val txtDetails: TextView = itemView.findViewById(R.id.txt_alert_details)
        private val txtDistance: TextView = itemView.findViewById(R.id.txt_alert_distance)
        private val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())

        fun bind(report: DiseaseReport, currentLocation: Location?) {
            txtDisease.text = report.diseaseName
            txtDetails.text = "${report.cropType} - ${sdf.format(Date(report.timestamp))}"
            
            if (currentLocation != null) {
                val distance = GpsUtils.calculateDistance(
                    currentLocation.latitude, currentLocation.longitude,
                    report.latitude, report.longitude
                )
                txtDistance.text = String.format("%.1f km", distance)
                txtDistance.visibility = View.VISIBLE
            } else {
                txtDistance.visibility = View.GONE
            }
        }
    }

    class AlertDiffCallback : DiffUtil.ItemCallback<DiseaseReport>() {
        override fun areItemsTheSame(oldItem: DiseaseReport, newItem: DiseaseReport): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DiseaseReport, newItem: DiseaseReport): Boolean {
            return oldItem == newItem
        }
    }
}
