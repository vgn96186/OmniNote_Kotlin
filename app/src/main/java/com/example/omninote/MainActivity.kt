package com.example.omninote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme

import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.omninote.data.OmniNoteDatabase
import com.example.omninote.repository.NoteRepository
import com.example.omninote.ui.screens.MainScreen
import com.example.omninote.ui.theme.OmniNoteTheme
import com.example.omninote.viewmodel.NoteViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Initialize database and repository
        val database = OmniNoteDatabase.getDatabase(this)
        val repository = NoteRepository(
            noteDao = database.noteDao(),
            strokeDao = database.strokeDao(),
            noteLinkDao = database.noteLinkDao()
        )
        
        setContent {
            // ViewModel is now obtained within the Composable context
            val viewModel: NoteViewModel = viewModel {
                NoteViewModel(repository)
            }
            
            val isDarkTheme = isSystemInDarkTheme()
            
            OmniNoteTheme(darkTheme = isDarkTheme) {
                MainScreen(viewModel = viewModel) 
            }
        }
    }
}
