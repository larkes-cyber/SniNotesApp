package com.example.sninotesapp.presentation.screen.notes

import com.example.sninotesapp.domain.model.Note

data class NotesUiState(
    val isLoading:Boolean = false,
    val notesList:List<Note> = listOf(),
    val error:String = "",
    val selectingMode:Boolean = false,
    val selectedNotes:List<Note> = listOf()
)