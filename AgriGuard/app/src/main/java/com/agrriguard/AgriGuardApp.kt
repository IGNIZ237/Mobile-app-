package com.agrriguard

import android.app.Application
import androidx.work.*
import com.agrriguard.data.SyncWorker
import com.agrriguard.data.local.AppDatabase
import com.agrriguard.data.repository.DiseaseRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import java.util.concurrent.TimeUnit

class AgriGuardApp : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { AppDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { DiseaseRepository(database.diseaseDao()) }

    override fun onCreate() {
        super.onCreate()
        
        // Configuration OsmDroid
        org.osmdroid.config.Configuration.getInstance().load(
            this,
            androidx.preference.PreferenceManager.getDefaultSharedPreferences(this)
        )

        setupSyncWorker()
    }

    private fun setupSyncWorker() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val syncRequest = PeriodicWorkRequestBuilder<SyncWorker>(1, TimeUnit.HOURS)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "AgriGuardSync",
            ExistingPeriodicWorkPolicy.KEEP,
            syncRequest
        )
    }
}
