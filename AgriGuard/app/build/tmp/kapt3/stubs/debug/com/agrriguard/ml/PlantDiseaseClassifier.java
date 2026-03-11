package com.agrriguard.ml;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010J\u0006\u0010\u0011\u001a\u00020\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082D\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/agrriguard/ml/PlantDiseaseClassifier;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "inputImageSize", "", "interpreter", "Lorg/tensorflow/lite/Interpreter;", "labels", "", "", "modelPath", "classify", "Lcom/agrriguard/ml/DiagnosticResult;", "bitmap", "Landroid/graphics/Bitmap;", "close", "", "app_debug"})
public final class PlantDiseaseClassifier {
    @org.jetbrains.annotations.NotNull
    private final android.content.Context context = null;
    @org.jetbrains.annotations.Nullable
    private org.tensorflow.lite.Interpreter interpreter;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String modelPath = "plant_disease_model.tflite";
    private final int inputImageSize = 200;
    @org.jetbrains.annotations.NotNull
    private final java.util.List<java.lang.String> labels = null;
    
    public PlantDiseaseClassifier(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.agrriguard.ml.DiagnosticResult classify(@org.jetbrains.annotations.NotNull
    android.graphics.Bitmap bitmap) {
        return null;
    }
    
    public final void close() {
    }
}