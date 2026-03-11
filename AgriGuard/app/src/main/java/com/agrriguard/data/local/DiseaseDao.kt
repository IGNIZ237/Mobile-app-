package com.agrriguard.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.agrriguard.data.model.DiseaseKnowledge
import com.agrriguard.data.model.DiseaseReport
import kotlinx.coroutines.flow.Flow

@Dao
interface DiseaseDao {
    // Knowledge Base
    @Query("SELECT * FROM diseases_knowledge")
    fun getAllKnowledge(): Flow<List<DiseaseKnowledge>>

    @Query("SELECT * FROM diseases_knowledge WHERE name = :name LIMIT 1")
    suspend fun getKnowledgeByName(name: String): DiseaseKnowledge?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertKnowledge(knowledge: List<DiseaseKnowledge>)

    // Reports
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertReport(report: DiseaseReport)

    @Query("SELECT * FROM disease_reports ORDER BY timestamp DESC")
    fun getAllReports(): Flow<List<DiseaseReport>>

    @Query("SELECT * FROM disease_reports WHERE isSynced = 0")
    suspend fun getUnsyncedReports(): List<DiseaseReport>

    @Query("UPDATE disease_reports SET isSynced = 1 WHERE id = :reportId")
    suspend fun markReportAsSynced(reportId: Long)
}
