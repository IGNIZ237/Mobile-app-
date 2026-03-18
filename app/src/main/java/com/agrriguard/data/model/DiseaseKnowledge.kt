package com.agrriguard.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "diseases_knowledge")
data class DiseaseKnowledge(
    @PrimaryKey val diseaseId: String,
    val name: String,
    val affectedCrops: String,
    val symptoms: String,
    val cause: String, // Nouveau champ demandé par le prof
    val naturalTreatment: String,
    val chemicalTreatment: String,
    val prevention: String,
    val imageResId: Int
)
