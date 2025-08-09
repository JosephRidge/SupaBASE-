package com.jayr.supabasecrud.data.repository

import com.jayr.supabasecrud.data.models.Todo

interface TodoService {
    suspend fun createTask(todo: Todo):Todo? // create task
    suspend fun getAllTasks(): List<Todo> // read all tasks
    suspend fun getTask(id:Int): Todo? // read one task
    suspend fun updateTask(todo: Todo):Todo? // update task
    suspend fun insertImage(fileName: String, fileBytes: ByteArray): String? // insert image
    suspend fun deleteTask(id:Int): Boolean // delete task and return true or false based on success
}