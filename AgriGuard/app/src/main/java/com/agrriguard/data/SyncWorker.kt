package com.agrriguard.data

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.agrriguard.AgriGuardApp

class SyncWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        val repository = (applicationContext as AgriGuardApp).repository
        val unsyncedReports = repository.getUnsyncedReports()

        if (unsyncedReports.isEmpty()) {
            return Result.success()
        }

        return try {
            for (report in unsyncedReports) {
                // TODO: Appel API réel avec Retrofit ici
                // simulatedApiCall(report)
                Log.d("SyncWorker", "Synchronisation du rapport : ${report.diseaseName}")
                repository.markAsSynced(report.id)
            }
            Result.success()
        } catch (e: Exception) {
            Log.e("SyncWorker", "Erreur de synchronisation", e)
            Result.retry()
        }
    }
}
