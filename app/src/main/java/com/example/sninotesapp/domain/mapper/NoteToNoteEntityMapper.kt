package com.example.sninotesapp.domain.mapper

import com.example.sninotesapp.data.database.entity.NoteEntity
import com.example.sninotesapp.domain.model.Note

fun Note.toNoteEntity():NoteEntity{
    return if(id != null) NoteEntity(
        id = id!!,
        title = title,
        text = text,
        color = color,
        online_sync = online_sync,
        visible = visible
    ) else{
        NoteEntity(
            title = title,
            text = text,
            color = color,
            online_sync = online_sync,
            visible = visible
        )
    }
}

fun NoteEntity.toNote(): Note {
    return Note(
        id = id,
        title = title,
        text = text,
        color = color,
        online_sync = online_sync,
        visible = visible
    )
}