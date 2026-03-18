package com.agrriguard.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "disease_reports")
data class DiseaseReport(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val diseaseName: String,
    val cropType: String,
    val latitude: Double,
    val longitude: Double,
    val timestamp: Long,
    val isSynced: Boolean = false,
    val rating: Float = 0f, // Avis de l'utilisateur (0 à 5)
    val comment: String = "" // Commentaire de l'utilisateur
)
