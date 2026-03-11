package com.agrriguard.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.agrriguard.data.model.DiseaseKnowledge
import com.agrriguard.data.model.DiseaseReport
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [DiseaseReport::class, DiseaseKnowledge::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun diseaseDao(): DiseaseDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "agri_guard_database"
                )
                .fallbackToDestructiveMigration() // Pour mettre à jour la structure sans erreur
                .addCallback(AppDatabaseCallback(scope))
                .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class AppDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch(Dispatchers.IO) {
                    populateDatabase(database.diseaseDao())
                }
            }
        }

        suspend fun populateDatabase(dao: DiseaseDao) {
            val diseases = listOf(
                // TOMATE
                DiseaseKnowledge("tomate_mildiou_precoce", "Tomate Mildiou precoce", "Tomate", 
                    "Petites taches brunes sur les feuilles anciennes.", 
                    "Supprimer les feuilles infectées au bas du plant.", 
                    "Fongicides à base de cuivre ou mancozèbe.", 
                    "Rotation des cultures, espacement suffisant.", 0),
                DiseaseKnowledge("tomate_mildiou_tardif", "Tomate Mildiou tardif", "Tomate", 
                    "Grandes taches sombres et huileuses.", 
                    "Arracher et détruire les plants très atteints.", 
                    "Traitement fongicide préventif.", 
                    "Éviter de mouiller le feuillage lors de l'arrosage.", 0),
                
                // CACAO (Rappel)
                DiseaseKnowledge("moniliose", "Moniliose du cacao", "Cacao", 
                    "Duvet blanc sur les cabosses.", 
                    "Éliminer les cabosses infectées.", 
                    "Fongicides cuivriques.", 
                    "Taille de formation pour l'aération.", 0),

                // MAÏS
                DiseaseKnowledge("maize_rust", "Maïs Rouille commune", "Maïs", 
                    "Pustules orange/brunes sur les deux faces des feuilles.", 
                    "Utilisation de variétés résistantes.", 
                    "Application de fongicides foliaires.", 
                    "Élimination des résidus de récolte.", 0)
                
                // Vous pourrez ajouter les 35 autres ici...
            )
            dao.insertKnowledge(diseases)
        }
    }
}
