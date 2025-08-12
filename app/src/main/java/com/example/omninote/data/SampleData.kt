package com.example.omninote.data

import java.time.LocalDateTime

object SampleData {
    
    fun getSampleNotes(): List<Note> {
        return listOf(
            Note(
                id = 1,
                title = "Project Planning",
                content = "This is a comprehensive project planning note. We need to consider multiple aspects including timeline, resources, and deliverables. The project involves developing a new mobile application with advanced features like real-time collaboration and cloud synchronization.",
                createdAt = LocalDateTime.now().minusDays(5),
                updatedAt = LocalDateTime.now().minusDays(2),
                tags = listOf("project", "planning", "mobile", "development")
            ),
            Note(
                id = 2,
                title = "Meeting Notes - Design Review",
                content = "Design review meeting for the mobile application project. Key decisions made: 1) Use Material Design 3 for consistent UI/UX, 2) Implement dark mode support, 3) Focus on tablet optimization for better user experience. Team agreed on using Jetpack Compose for modern UI development.",
                createdAt = LocalDateTime.now().minusDays(3),
                updatedAt = LocalDateTime.now().minusDays(1),
                tags = listOf("meeting", "design", "mobile", "UI/UX")
            ),
            Note(
                id = 3,
                title = "Technical Architecture",
                content = "Technical architecture for the mobile application. Core components: Room database for local storage, Repository pattern for data management, MVVM architecture with ViewModels, and Jetpack Compose for UI. The app will support offline-first design with cloud synchronization capabilities.",
                createdAt = LocalDateTime.now().minusDays(4),
                updatedAt = LocalDateTime.now().minusDays(1),
                tags = listOf("architecture", "technical", "database", "MVVM")
            ),
            Note(
                id = 4,
                title = "User Research Findings",
                content = "User research findings for tablet users. Key insights: 1) Users prefer landscape orientation for note-taking, 2) S Pen integration is crucial for natural writing experience, 3) Knowledge graph visualization helps with information organization, 4) Offline functionality is highly valued.",
                createdAt = LocalDateTime.now().minusDays(2),
                updatedAt = LocalDateTime.now(),
                tags = listOf("research", "user", "tablet", "UX")
            ),
            Note(
                id = 5,
                title = "Development Timeline",
                content = "Development timeline for the mobile application project. Phase 1: Core note-taking functionality (2 weeks), Phase 2: Knowledge graph implementation (3 weeks), Phase 3: Advanced features and optimization (2 weeks), Phase 4: Testing and bug fixes (1 week). Total timeline: 8 weeks.",
                createdAt = LocalDateTime.now().minusDays(1),
                updatedAt = LocalDateTime.now(),
                tags = listOf("timeline", "development", "planning", "project")
            ),
            Note(
                id = 6,
                title = "Feature Requirements",
                content = "Feature requirements for the note-taking application. Must-have features: infinite canvas, S Pen support, knowledge graph, search functionality, export capabilities. Nice-to-have features: cloud sync, collaboration, advanced drawing tools, PDF annotation.",
                createdAt = LocalDateTime.now().minusDays(6),
                updatedAt = LocalDateTime.now().minusDays(3),
                tags = listOf("requirements", "features", "specifications")
            ),
            Note(
                id = 7,
                title = "Testing Strategy",
                content = "Testing strategy for the mobile application. Unit tests for ViewModels and Repository classes, integration tests for database operations, UI tests for Compose components, and performance testing for drawing canvas. Focus on tablet-specific testing scenarios.",
                createdAt = LocalDateTime.now().minusDays(2),
                updatedAt = LocalDateTime.now().minusDays(1),
                tags = listOf("testing", "strategy", "quality", "mobile")
            ),
            Note(
                id = 8,
                title = "Performance Optimization",
                content = "Performance optimization strategies for the note-taking app. Key areas: 1) Efficient stroke rendering with GPU acceleration, 2) Optimized database queries with proper indexing, 3) Memory management for large note files, 4) Battery optimization for continuous drawing.",
                createdAt = LocalDateTime.now().minusDays(1),
                updatedAt = LocalDateTime.now(),
                tags = listOf("performance", "optimization", "technical", "mobile")
            )
        )
    }
    
    fun getSampleLinks(): List<NoteLink> {
        return listOf(
            NoteLink(sourceNoteId = 1, targetNoteId = 2, linkType = LinkType.RELATED),
            NoteLink(sourceNoteId = 1, targetNoteId = 3, linkType = LinkType.RELATED),
            NoteLink(sourceNoteId = 1, targetNoteId = 5, linkType = LinkType.RELATED),
            NoteLink(sourceNoteId = 2, targetNoteId = 3, linkType = LinkType.RELATED),
            NoteLink(sourceNoteId = 2, targetNoteId = 4, linkType = LinkType.RELATED),
            NoteLink(sourceNoteId = 3, targetNoteId = 6, linkType = LinkType.RELATED),
            NoteLink(sourceNoteId = 3, targetNoteId = 8, linkType = LinkType.RELATED),
            NoteLink(sourceNoteId = 4, targetNoteId = 6, linkType = LinkType.RELATED),
            NoteLink(sourceNoteId = 5, targetNoteId = 7, linkType = LinkType.RELATED),
            NoteLink(sourceNoteId = 6, targetNoteId = 8, linkType = LinkType.RELATED),
            NoteLink(sourceNoteId = 7, targetNoteId = 8, linkType = LinkType.RELATED)
        )
    }
}
