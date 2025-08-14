package com.example.omninote.data

import java.time.LocalDateTime

object SampleData {

    // Use a single reference time so relative dates are consistent within a session
    private val referenceTime: LocalDateTime = LocalDateTime.now()
    
    fun getSampleNotes(): List<Note> {
        return emptyList()
    }
    
    fun getSampleLinks(): List<NoteLink> {
        return emptyList()
    }
}
