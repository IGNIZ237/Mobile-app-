package com.agrriguard.data.local;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H\'J\u0014\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00040\u0003H\'J\u001b\u0010\b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\t\u001a\u00020\nH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\u0004H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\rJ\u001f\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0011J\u0019\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u0007H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0014J\u0019\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u0017H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0018\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0019"}, d2 = {"Lcom/agrriguard/data/local/DiseaseDao;", "", "getAllKnowledge", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/agrriguard/data/model/DiseaseKnowledge;", "getAllReports", "Lcom/agrriguard/data/model/DiseaseReport;", "getKnowledgeByName", "name", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUnsyncedReports", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertKnowledge", "", "knowledge", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertReport", "report", "(Lcom/agrriguard/data/model/DiseaseReport;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "markReportAsSynced", "reportId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao
public abstract interface DiseaseDao {
    
    @androidx.room.Query(value = "SELECT * FROM diseases_knowledge")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.agrriguard.data.model.DiseaseKnowledge>> getAllKnowledge();
    
    @androidx.room.Query(value = "SELECT * FROM diseases_knowledge WHERE name = :name LIMIT 1")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getKnowledgeByName(@org.jetbrains.annotations.NotNull
    java.lang.String name, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.agrriguard.data.model.DiseaseKnowledge> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertKnowledge(@org.jetbrains.annotations.NotNull
    java.util.List<com.agrriguard.data.model.DiseaseKnowledge> knowledge, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Insert(onConflict = 5)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertReport(@org.jetbrains.annotations.NotNull
    com.agrriguard.data.model.DiseaseReport report, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM disease_reports ORDER BY timestamp DESC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.agrriguard.data.model.DiseaseReport>> getAllReports();
    
    @androidx.room.Query(value = "SELECT * FROM disease_reports WHERE isSynced = 0")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getUnsyncedReports(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.agrriguard.data.model.DiseaseReport>> $completion);
    
    @androidx.room.Query(value = "UPDATE disease_reports SET isSynced = 1 WHERE id = :reportId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object markReportAsSynced(long reportId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}