package com.agrriguard.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.agrriguard.data.model.DiseaseKnowledge
import com.agrriguard.data.model.DiseaseReport
import com.agrriguard.data.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [DiseaseReport::class, DiseaseKnowledge::class, User::class], version = 4, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun diseaseDao(): DiseaseDao
    abstract fun userDao(): UserDao

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
                .fallbackToDestructiveMigration()
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
                DiseaseKnowledge("tomato bacterial spot", "Tomato Bacterial spot", "Tomate",
                    "Petites taches noires entourées d'un halo jaune sur les feuilles.",
                    "Bactérie Xanthomonas, favorisée par une humidité élevée et des températures chaudes.",
                    "Pulvérisation d'extraits d'ail ou de solution de bicarbonate.",
                    "Produits à base de cuivre.",
                    "Utiliser des semences certifiées, éviter l'irrigation par aspersion.", 0),

                DiseaseKnowledge("tomato early blight", "Tomato Early blight", "Tomate",
                    "Taches brunes avec des anneaux concentriques (cible) sur les vieilles feuilles.",
                    "Champignon Alternaria solani, survit dans le sol et les débris végétaux. Favorisé par l'humidité.",
                    "Infusion de prêle ou purin d'ortie.",
                    "Fongicides à base de chlorothalonil ou de cuivre.",
                    "Rotation des cultures, paillage, arrosage au pied.", 0),

                DiseaseKnowledge("tomato late blight", "Tomato Late blight", "Tomate",
                    "Grandes taches sombres d'apparence huileuse se propageant rapidement.",
                    "Oomycète Phytophthora infestans, se propage par le vent et la pluie par temps frais et humide.",
                    "Solution de lait dilué (10%) ou bicarbonate de soude.",
                    "Fongicides spécifiques (Mancozèbe, Cuivre).",
                    "Éliminer les plants infectés, assurer une bonne circulation d'air.", 0),

                DiseaseKnowledge("tomato leaf mold", "Tomato Leaf mold", "Tomate",
                    "Taches jaune pâle sur la face supérieure, duvet olive sur la face inférieure.",
                    "Champignon Passalora fulva, favorisé par une humidité relative très élevée (>85%).",
                    "Vinaigre de cidre dilué.",
                    "Fongicides protecteurs.",
                    "Améliorer la ventilation, réduire l'humidité.", 0),

                DiseaseKnowledge("tomato septoria leaf spot", "Tomato Septoria leaf spot", "Tomate",
                    "Nombreuses petites taches circulaires avec un centre gris et un bord sombre.",
                    "Champignon Septoria lycopersici, se propage par les éclaboussures d'eau.",
                    "Décoction de prêle.",
                    "Fongicides à large spectre.",
                    "Nettoyer les outils, éliminer les mauvaises herbes (solanacées).", 0),

                DiseaseKnowledge("tomato spider mites two spotted spider mite", "Tomato Spider mites", "Tomate",
                    "Petit pointillé jaune sur les feuilles, présence de fines toiles.",
                    "Acariens Tetranychus urticae, favorisés par un temps très chaud et sec (manque d'eau).",
                    "Douche d'eau savonneuse ou huile de Neem.",
                    "Acaricides spécifiques.",
                    "Maintenir une humidité ambiante, brumiser le feuillage.", 0),

                DiseaseKnowledge("tomato target spot", "Tomato Target spot", "Tomate",
                    "Taches brunes avec anneaux concentriques, souvent entourées de zones jaunes.",
                    "Champignon Corynespora cassiicola, survit longtemps dans le sol.",
                    "Rotation des cultures longue.",
                    "Fongicides adaptés.",
                    "Éviter les blessures aux plantes, assurer un bon drainage.", 0),

                DiseaseKnowledge("tomato tomato yellow leaf curl virus", "Tomato Yellow Leaf Curl Virus", "Tomate",
                    "Feuilles qui s'enroulent vers le haut, jaunissement des bords, nanisme.",
                    "Virus transmis par les mouches blanches (Aleurodes). Pas de cause environnementale directe.",
                    "Pièges collants jaunes pour les mouches blanches.",
                    "Traitement insecticide contre les aleurodes.",
                    "Utiliser des filets anti-insectes, variétés résistantes.", 0),

                DiseaseKnowledge("tomato tomato mosaic virus", "Tomato Tomato mosaic virus", "Tomate",
                    "Motifs de mosaïque vert clair/foncé sur les feuilles, déformation.",
                    "Virus ToMV, très stable, se transmet par contact (mains, outils, semences).",
                    "Désinfection rigoureuse des mains et outils.",
                    "Aucun traitement chimique efficace contre le virus.",
                    "Utiliser des semences traitées, ne pas fumer près des plants.", 0),

                DiseaseKnowledge("tomato healthy", "Tomato Healthy", "Tomate",
                    "Feuillage vert vigoureux, absence de taches.",
                    "Bonnes conditions de culture, nutrition équilibrée et sol sain.",
                    "Continuer l'entretien normal.",
                    "Pas de traitement nécessaire.",
                    "Maintenir la surveillance.", 0)
            )
            dao.insertKnowledge(diseases)
        }
    }
}
