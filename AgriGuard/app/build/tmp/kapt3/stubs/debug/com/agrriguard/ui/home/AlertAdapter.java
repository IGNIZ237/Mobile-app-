package com.agrriguard.ui.home;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0002\u0012\u0013B\u0005\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0018\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000bH\u0016J\u000e\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u0006R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/agrriguard/ui/home/AlertAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/agrriguard/data/model/DiseaseReport;", "Lcom/agrriguard/ui/home/AlertAdapter$AlertViewHolder;", "()V", "currentUserLocation", "Landroid/location/Location;", "onBindViewHolder", "", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "updateLocation", "location", "AlertDiffCallback", "AlertViewHolder", "app_debug"})
public final class AlertAdapter extends androidx.recyclerview.widget.ListAdapter<com.agrriguard.data.model.DiseaseReport, com.agrriguard.ui.home.AlertAdapter.AlertViewHolder> {
    @org.jetbrains.annotations.Nullable
    private android.location.Location currentUserLocation;
    
    public AlertAdapter() {
        super(null);
    }
    
    public final void updateLocation(@org.jetbrains.annotations.NotNull
    android.location.Location location) {
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public com.agrriguard.ui.home.AlertAdapter.AlertViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull
    com.agrriguard.ui.home.AlertAdapter.AlertViewHolder holder, int position) {
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016\u00a8\u0006\t"}, d2 = {"Lcom/agrriguard/ui/home/AlertAdapter$AlertDiffCallback;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/agrriguard/data/model/DiseaseReport;", "()V", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "app_debug"})
    public static final class AlertDiffCallback extends androidx.recyclerview.widget.DiffUtil.ItemCallback<com.agrriguard.data.model.DiseaseReport> {
        
        public AlertDiffCallback() {
            super();
        }
        
        @java.lang.Override
        public boolean areItemsTheSame(@org.jetbrains.annotations.NotNull
        com.agrriguard.data.model.DiseaseReport oldItem, @org.jetbrains.annotations.NotNull
        com.agrriguard.data.model.DiseaseReport newItem) {
            return false;
        }
        
        @java.lang.Override
        public boolean areContentsTheSame(@org.jetbrains.annotations.NotNull
        com.agrriguard.data.model.DiseaseReport oldItem, @org.jetbrains.annotations.NotNull
        com.agrriguard.data.model.DiseaseReport newItem) {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/agrriguard/ui/home/AlertAdapter$AlertViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "sdf", "Ljava/text/SimpleDateFormat;", "txtDetails", "Landroid/widget/TextView;", "txtDisease", "txtDistance", "bind", "", "report", "Lcom/agrriguard/data/model/DiseaseReport;", "currentLocation", "Landroid/location/Location;", "app_debug"})
    public static final class AlertViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView txtDisease = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView txtDetails = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView txtDistance = null;
        @org.jetbrains.annotations.NotNull
        private final java.text.SimpleDateFormat sdf = null;
        
        public AlertViewHolder(@org.jetbrains.annotations.NotNull
        android.view.View itemView) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull
        com.agrriguard.data.model.DiseaseReport report, @org.jetbrains.annotations.Nullable
        android.location.Location currentLocation) {
        }
    }
}