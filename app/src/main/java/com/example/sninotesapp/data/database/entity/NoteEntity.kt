package com.example.sninotesapp.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
class NoteEntity(
    @PrimaryKey(autoGenerate = false)
    var id:String = UUID.randomUUID().toString(),
    val title:String,
    val text:String,
    val color:Long,
    val online_sync:Boolean
)