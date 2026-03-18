package com.agrriguard.ui.map;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\b\u0010\u0011\u001a\u00020\fH\u0002J\u0012\u0010\u0012\u001a\u00020\f2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0014J\b\u0010\u0015\u001a\u00020\fH\u0014J\b\u0010\u0016\u001a\u00020\fH\u0014J\b\u0010\u0017\u001a\u00020\fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b\u00a8\u0006\u0018"}, d2 = {"Lcom/agrriguard/ui/map/MapActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/agrriguard/databinding/ActivityMapBinding;", "viewModel", "Lcom/agrriguard/ui/home/HomeViewModel;", "getViewModel", "()Lcom/agrriguard/ui/home/HomeViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "drawRiskCircle", "", "center", "Lorg/osmdroid/util/GeoPoint;", "diseaseName", "", "observeReports", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onPause", "onResume", "setupMap", "app_debug"})
public final class MapActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.agrriguard.databinding.ActivityMapBinding binding;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy viewModel$delegate = null;
    
    public MapActivity() {
        super();
    }
    
    private final com.agrriguard.ui.home.HomeViewModel getViewModel() {
        return null;
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupMap() {
    }
    
    private final void observeReports() {
    }
    
    private final void drawRiskCircle(org.osmdroid.util.GeoPoint center, java.lang.String diseaseName) {
    }
    
    @java.lang.Override
    protected void onResume() {
    }
    
    @java.lang.Override
    protected void onPause() {
    }
}