package com.jayr.supabasecrud.data.models

data class Todo(
    val id:Int? =null,
    val createdAt:String? = null,
    val title:String,
    val description:String,
    val media: String, //  store images or video
    val isComplete: Boolean = false, // default to false
    val dueDate:Long, // store as unix timestamp
    val dueTime:Long, // store as timestamp

)
