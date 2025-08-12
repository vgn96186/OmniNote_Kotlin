package com.example.omninote.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment // Added for centering text
// import androidx.lifecycle.viewmodel.compose.viewModel // No longer creating viewModel internally
// import com.example.omninote.ui.AppNavigation // Import the AppNavigation - Commented out
import com.example.omninote.ui.theme.OmniNoteTheme
import com.example.omninote.viewmodel.NoteViewModel

// The MainScreen now acts as the top-level container for your app's UI.
@Composable
fun MainScreen(
    modifier: Modifier = Modifier, // This modifier will now be used
    viewModel: NoteViewModel // Added viewModel as a parameter
) {
    // A single source of truth for the ViewModel.
    // val viewModel: NoteViewModel = viewModel() // Removed: ViewModel is now passed in

    OmniNoteTheme {
        // AppNavigation now controls the screen layout and bottom tabs.
        // AppNavigation(viewModel = viewModel) - Commented out, but now uses the passed-in viewModel

        // Placeholder for the main content or navigation
        Box(
            modifier = modifier.fillMaxSize(), // Apply the modifier and fill the screen
            contentAlignment = Alignment.Center // Center the placeholder text
        ) {
            Text("Main content or AppNavigation missing. ViewModel is available.")
        }
    }
}
