package com.example.sninotesapp.domain.mapper

import com.example.sninotesapp.data.remote.model.NoteDto
import com.example.sninotesapp.domain.model.Note

fun Note.toNoteDto() = NoteDto(
    title = title,
    id = id?.toString(),
    text = text,
    color = color,
    timestamp = timestamp
)
fun NoteDto.toNote() = Note(
    title = title,
    id = id,
    text = text,
    color = color,
    online_sync = true,
    timestamp = timestamp
)