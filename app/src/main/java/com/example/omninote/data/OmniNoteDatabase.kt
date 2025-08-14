package com.example.omninote.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.omninote.data.converters.Converters
import com.example.omninote.data.dao.NoteDao
import com.example.omninote.data.dao.NoteLinkDao
import com.example.omninote.data.dao.StrokeDao
// Import XMLInputFactory to create a secure XML parser
// javax.xml.stream.XMLInputFactory

@Database(
    entities = [Note::class, Stroke::class, NoteLink::class],
    version = 3,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class OmniNoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
    abstract fun strokeDao(): StrokeDao
    abstract fun noteLinkDao(): NoteLinkDao

    companion object {
        @Volatile
        private var INSTANCE: OmniNoteDatabase? = null

        fun getDatabase(context: Context): OmniNoteDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    OmniNoteDatabase::class.java,
                    "omninote_database"
                )
                    .fallbackToDestructiveMigration(dropAllTables = true)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
