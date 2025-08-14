package com.example.omninote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.omninote.data.OmniNoteDatabase
import com.example.omninote.data.Note
import com.example.omninote.repository.NoteRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.*
import org.junit.runner.RunWith
import java.time.LocalDateTime

@RunWith(AndroidJUnit4::class)
class NoteRepositoryAndroidTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: OmniNoteDatabase
    private lateinit var repository: NoteRepository

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            OmniNoteDatabase::class.java
        ).allowMainThreadQueries().build()

        repository = NoteRepository(
            noteDao = database.noteDao(),
            strokeDao = database.strokeDao(),
            noteLinkDao = database.noteLinkDao()
        )
    }

    @After
    fun cleanup() {
        database.close()
    }

    @Test
    fun testCreateAndRetrieveNote() = runTest {
        val note = Note(
            title = "Test Note",
            content = "This is a test note content",
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )
        val noteId = repository.insertNote(note)
        val retrievedNote = repository.getNoteById(noteId)
        Assert.assertEquals(note.title, retrievedNote?.title)
    }
}
