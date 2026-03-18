package com.agrriguard.ui.result;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aJ\b\u0010\u001b\u001a\u00020\u0018H\u0014J\u0016\u0010\u001c\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001eR\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\t0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0019\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013R\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\r0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006 "}, d2 = {"Lcom/agrriguard/ui/result/ResultViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "repository", "Lcom/agrriguard/data/repository/DiseaseRepository;", "(Landroid/app/Application;Lcom/agrriguard/data/repository/DiseaseRepository;)V", "_diagnosticResult", "Landroidx/lifecycle/MutableLiveData;", "Lcom/agrriguard/ml/DiagnosticResult;", "_diseaseKnowledge", "Lcom/agrriguard/data/model/DiseaseKnowledge;", "_isReporting", "", "classifier", "Lcom/agrriguard/ml/PlantDiseaseClassifier;", "diagnosticResult", "Landroidx/lifecycle/LiveData;", "getDiagnosticResult", "()Landroidx/lifecycle/LiveData;", "diseaseKnowledge", "getDiseaseKnowledge", "isReporting", "classifyImage", "", "imageUri", "Landroid/net/Uri;", "onCleared", "reportCase", "latitude", "", "longitude", "app_debug"})
public final class ResultViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull
    private final com.agrriguard.data.repository.DiseaseRepository repository = null;
    @org.jetbrains.annotations.NotNull
    private final com.agrriguard.ml.PlantDiseaseClassifier classifier = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<com.agrriguard.ml.DiagnosticResult> _diagnosticResult = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<com.agrriguard.ml.DiagnosticResult> diagnosticResult = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<com.agrriguard.data.model.DiseaseKnowledge> _diseaseKnowledge = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<com.agrriguard.data.model.DiseaseKnowledge> diseaseKnowledge = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _isReporting = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<java.lang.Boolean> isReporting = null;
    
    public ResultViewModel(@org.jetbrains.annotations.NotNull
    android.app.Application application, @org.jetbrains.annotations.NotNull
    com.agrriguard.data.repository.DiseaseRepository repository) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.agrriguard.ml.DiagnosticResult> getDiagnosticResult() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.agrriguard.data.model.DiseaseKnowledge> getDiseaseKnowledge() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.lang.Boolean> isReporting() {
        return null;
    }
    
    public final void classifyImage(@org.jetbrains.annotations.NotNull
    android.net.Uri imageUri) {
    }
    
    public final void reportCase(double latitude, double longitude) {
    }
    
    @java.lang.Override
    protected void onCleared() {
    }
}