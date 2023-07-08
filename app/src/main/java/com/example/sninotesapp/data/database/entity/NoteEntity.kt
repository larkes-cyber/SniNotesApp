package com.example.sninotesapp.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    var id:Int? = null,
    val title:String,
    val text:String,
    val color:Long,
    val online_sync:Boolean
)