package com.agrriguard.data.local;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\'\u0018\u0000 \u00062\u00020\u0001:\u0002\u0005\u0006B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&\u00a8\u0006\u0007"}, d2 = {"Lcom/agrriguard/data/local/AppDatabase;", "Landroidx/room/RoomDatabase;", "()V", "diseaseDao", "Lcom/agrriguard/data/local/DiseaseDao;", "AppDatabaseCallback", "Companion", "app_debug"})
@androidx.room.Database(entities = {com.agrriguard.data.model.DiseaseReport.class, com.agrriguard.data.model.DiseaseKnowledge.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends androidx.room.RoomDatabase {
    @kotlin.jvm.Volatile
    @org.jetbrains.annotations.Nullable
    private static volatile com.agrriguard.data.local.AppDatabase INSTANCE;
    @org.jetbrains.annotations.NotNull
    public static final com.agrriguard.data.local.AppDatabase.Companion Companion = null;
    
    public AppDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public abstract com.agrriguard.data.local.DiseaseDao diseaseDao();
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0019\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\r"}, d2 = {"Lcom/agrriguard/data/local/AppDatabase$AppDatabaseCallback;", "Landroidx/room/RoomDatabase$Callback;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "(Lkotlinx/coroutines/CoroutineScope;)V", "onCreate", "", "db", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "populateDatabase", "dao", "Lcom/agrriguard/data/local/DiseaseDao;", "(Lcom/agrriguard/data/local/DiseaseDao;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
    static final class AppDatabaseCallback extends androidx.room.RoomDatabase.Callback {
        @org.jetbrains.annotations.NotNull
        private final kotlinx.coroutines.CoroutineScope scope = null;
        
        public AppDatabaseCallback(@org.jetbrains.annotations.NotNull
        kotlinx.coroutines.CoroutineScope scope) {
            super();
        }
        
        @java.lang.Override
        public void onCreate(@org.jetbrains.annotations.NotNull
        androidx.sqlite.db.SupportSQLiteDatabase db) {
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.Object populateDatabase(@org.jetbrains.annotations.NotNull
        com.agrriguard.data.local.DiseaseDao dao, @org.jetbrains.annotations.NotNull
        kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lcom/agrriguard/data/local/AppDatabase$Companion;", "", "()V", "INSTANCE", "Lcom/agrriguard/data/local/AppDatabase;", "getDatabase", "context", "Landroid/content/Context;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.agrriguard.data.local.AppDatabase getDatabase(@org.jetbrains.annotations.NotNull
        android.content.Context context, @org.jetbrains.annotations.NotNull
        kotlinx.coroutines.CoroutineScope scope) {
            return null;
        }
    }
}