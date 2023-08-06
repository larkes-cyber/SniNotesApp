package com.example.sninotesapp.domain.mapper

import com.example.sninotesapp.data.remote.model.NoteDto
import com.example.sninotesapp.domain.model.Note

fun Note.toNoteDto() = NoteDto(
    title = title,
    id = id?.toString(),
    text = text,
    timestamp = color
)
fun NoteDto.toNote() = Note(
    title = title,
    id = id,
    text = text,
    color = timestamp,
    online_sync = true
)