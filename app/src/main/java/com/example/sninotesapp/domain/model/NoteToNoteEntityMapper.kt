package com.example.sninotesapp.domain.model

import com.example.sninotesapp.data.database.entity.NoteEntity

fun Note.toNoteEntity():NoteEntity{
    return NoteEntity(
        id = id,
        title = title,
        text = text,
        color = color,
        online_sync = online_sync
    )
}

fun NoteEntity.toNote():Note{
    return Note(
        id = id,
        title = title,
        text = text,
        color = color,
        online_sync = online_sync
    )
}