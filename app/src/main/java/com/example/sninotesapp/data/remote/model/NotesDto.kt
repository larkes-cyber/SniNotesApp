package com.example.sninotesapp.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class NotesDto(
    val notes:List<NoteDto>
)