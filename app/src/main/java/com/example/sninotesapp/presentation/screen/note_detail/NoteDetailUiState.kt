package com.example.sninotesapp.presentation.screen.note_detail

data class NoteDetailUiState(
    val title:String = "Title",
    val text:String = "Write your note...",
    val online_sync:Boolean = false,
    val noteHasBeenEdited:Boolean = false,
    val color:Long = 0L
)