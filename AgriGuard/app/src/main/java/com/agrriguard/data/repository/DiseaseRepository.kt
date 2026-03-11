package com.agrriguard.data.repository

import com.agrriguard.data.local.DiseaseDao
import com.agrriguard.data.model.DiseaseKnowledge
import com.agrriguard.data.model.DiseaseReport
import kotlinx.coroutines.flow.Flow

class DiseaseRepository(private val diseaseDao: DiseaseDao) {

    val allKnowledge: Flow<List<DiseaseKnowledge>> = diseaseDao.getAllKnowledge()
    val allReports: Flow<List<DiseaseReport>> = diseaseDao.getAllReports()

    suspend fun getKnowledgeByName(name: String): DiseaseKnowledge? {
        return diseaseDao.getKnowledgeByName(name)
    }

    suspend fun insertReport(report: DiseaseReport) {
        diseaseDao.insertReport(report)
    }

    suspend fun getUnsyncedReports(): List<DiseaseReport> {
        return diseaseDao.getUnsyncedReports()
    }

    suspend fun markAsSynced(reportId: Long) {
        diseaseDao.markReportAsSynced(reportId)
    }
}
