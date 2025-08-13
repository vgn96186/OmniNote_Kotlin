package com.example.omninote.data;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\tH&\u00a8\u0006\u000b"}, d2 = {"Lcom/example/omninote/data/OmniNoteDatabase;", "Landroidx/room/RoomDatabase;", "<init>", "()V", "noteDao", "Lcom/example/omninote/data/dao/NoteDao;", "strokeDao", "Lcom/example/omninote/data/dao/StrokeDao;", "noteLinkDao", "Lcom/example/omninote/data/dao/NoteLinkDao;", "Companion", "app_release"})
@androidx.room.Database(entities = {com.example.omninote.data.Note.class, com.example.omninote.data.Stroke.class, com.example.omninote.data.NoteLink.class}, version = 1, exportSchema = false)
@androidx.room.TypeConverters(value = {com.example.omninote.data.converters.Converters.class})
public abstract class OmniNoteDatabase extends androidx.room.RoomDatabase {
    @kotlin.jvm.Volatile()
    @org.jetbrains.annotations.Nullable()
    private static volatile com.example.omninote.data.OmniNoteDatabase INSTANCE;
    @org.jetbrains.annotations.NotNull()
    public static final com.example.omninote.data.OmniNoteDatabase.Companion Companion = null;
    
    public OmniNoteDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.omninote.data.dao.NoteDao noteDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.omninote.data.dao.StrokeDao strokeDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.omninote.data.dao.NoteLinkDao noteLinkDao();
    
    @kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bR\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/example/omninote/data/OmniNoteDatabase$Companion;", "", "<init>", "()V", "INSTANCE", "Lcom/example/omninote/data/OmniNoteDatabase;", "getDatabase", "context", "Landroid/content/Context;", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.omninote.data.OmniNoteDatabase getDatabase(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
    }
}