package com.agrriguard.utils;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J&\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004J\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\rH\u0007\u00a8\u0006\u000e"}, d2 = {"Lcom/agrriguard/utils/GpsUtils;", "", "()V", "calculateDistance", "", "lat1", "lon1", "lat2", "lon2", "getCurrentLocation", "Lcom/google/android/gms/tasks/Task;", "Landroid/location/Location;", "context", "Landroid/content/Context;", "app_debug"})
public final class GpsUtils {
    @org.jetbrains.annotations.NotNull
    public static final com.agrriguard.utils.GpsUtils INSTANCE = null;
    
    private GpsUtils() {
        super();
    }
    
    @android.annotation.SuppressLint(value = {"MissingPermission"})
    @org.jetbrains.annotations.NotNull
    public final com.google.android.gms.tasks.Task<android.location.Location> getCurrentLocation(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        return null;
    }
    
    /**
     * Calcule la distance entre deux points en kilomètres en utilisant la formule de Haversine.
     */
    public final double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        return 0.0;
    }
}