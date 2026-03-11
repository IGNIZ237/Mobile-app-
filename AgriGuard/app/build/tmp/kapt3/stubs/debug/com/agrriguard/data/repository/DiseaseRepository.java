package com.agrriguard.data.repository;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001b\u0010\u000e\u001a\u0004\u0018\u00010\b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0011J\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\f0\u0007H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0013J\u0019\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\fH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0017J\u0019\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u001aH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001bR\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001d\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001c"}, d2 = {"Lcom/agrriguard/data/repository/DiseaseRepository;", "", "diseaseDao", "Lcom/agrriguard/data/local/DiseaseDao;", "(Lcom/agrriguard/data/local/DiseaseDao;)V", "allKnowledge", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/agrriguard/data/model/DiseaseKnowledge;", "getAllKnowledge", "()Lkotlinx/coroutines/flow/Flow;", "allReports", "Lcom/agrriguard/data/model/DiseaseReport;", "getAllReports", "getKnowledgeByName", "name", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUnsyncedReports", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertReport", "", "report", "(Lcom/agrriguard/data/model/DiseaseReport;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "markAsSynced", "reportId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class DiseaseRepository {
    @org.jetbrains.annotations.NotNull
    private final com.agrriguard.data.local.DiseaseDao diseaseDao = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.agrriguard.data.model.DiseaseKnowledge>> allKnowledge = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.agrriguard.data.model.DiseaseReport>> allReports = null;
    
    public DiseaseRepository(@org.jetbrains.annotations.NotNull
    com.agrriguard.data.local.DiseaseDao diseaseDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.agrriguard.data.model.DiseaseKnowledge>> getAllKnowledge() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.agrriguard.data.model.DiseaseReport>> getAllReports() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getKnowledgeByName(@org.jetbrains.annotations.NotNull
    java.lang.String name, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.agrriguard.data.model.DiseaseKnowledge> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object insertReport(@org.jetbrains.annotations.NotNull
    com.agrriguard.data.model.DiseaseReport report, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getUnsyncedReports(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.agrriguard.data.model.DiseaseReport>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object markAsSynced(long reportId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}