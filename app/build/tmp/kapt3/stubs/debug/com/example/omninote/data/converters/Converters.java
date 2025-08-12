package com.example.omninote.data.converters;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0007J\u0014\u0010\r\u001a\u0004\u0018\u00010\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\nH\u0007J\u0018\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\f0\u00102\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0007J\u0016\u0010\u0011\u001a\u00020\f2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\f0\u0010H\u0007J\u0018\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u00102\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0007J\u0016\u0010\u0015\u001a\u00020\f2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0010H\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n \b*\u0004\u0018\u00010\u00070\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lcom/example/omninote/data/converters/Converters;", "", "<init>", "()V", "gson", "Lcom/google/gson/Gson;", "formatter", "Ljava/time/format/DateTimeFormatter;", "kotlin.jvm.PlatformType", "fromTimestamp", "Ljava/time/LocalDateTime;", "value", "", "dateToTimestamp", "date", "fromStringList", "", "toStringList", "list", "fromPointList", "Lcom/example/omninote/data/Point;", "toPointList", "app_debug"})
public final class Converters {
    @org.jetbrains.annotations.NotNull()
    private final com.google.gson.Gson gson = null;
    private final java.time.format.DateTimeFormatter formatter = null;
    
    public Converters() {
        super();
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.Nullable()
    public final java.time.LocalDateTime fromTimestamp(@org.jetbrains.annotations.Nullable()
    java.lang.String value) {
        return null;
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String dateToTimestamp(@org.jetbrains.annotations.Nullable()
    java.time.LocalDateTime date) {
        return null;
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> fromStringList(@org.jetbrains.annotations.Nullable()
    java.lang.String value) {
        return null;
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String toStringList(@org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> list) {
        return null;
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.example.omninote.data.Point> fromPointList(@org.jetbrains.annotations.Nullable()
    java.lang.String value) {
        return null;
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String toPointList(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.omninote.data.Point> list) {
        return null;
    }
}