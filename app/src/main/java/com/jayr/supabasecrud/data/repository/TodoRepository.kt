package com.jayr.supabasecrud.data.repository

import com.jayr.supabasecrud.data.models.Todo
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.storage.Storage
import io.github.jan.supabase.storage.UploadStatus
import io.github.jan.supabase.storage.storage
import io.github.jan.supabase.storage.uploadAsFlow

class TodoRepository : TodoService {

    val supabase = createSupabaseClient(
        supabaseUrl = "https://hsapkgxtuybcktzepdbr.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImhzYXBrZ3h0dXliY2t0emVwZGJyIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NTQyOTY4OTIsImV4cCI6MjA2OTg3Mjg5Mn0.0CzMp75GlRFvT5CdASFcAHS0nX2FocUDCip19hF7Xvw"
    ) {
        install(Auth)
        install(Postgrest)
        install(Storage)
    }

    override suspend fun createTask(todo: Todo): Todo? {
        val task = supabase.from("tasks").select().decodeSingle<Todo>()
        return task
    }

    override suspend fun getAllTasks(): List<Todo> {
        val task = supabase.from("tasks").select().decodeList<Todo>()
        return task
    }

    override suspend fun getTask(id: Int): Todo? {
        val todo = supabase.from("tasks").select() {
            filter {
                Todo::id eq id
            }
        }.decodeAsOrNull<Todo>()
        return todo
    }

    override suspend fun updateTask(todo: Todo): Todo? {
        val todo = supabase.from("tasks").update(
            todo
        ) {
            select()
            filter {
                eq("id", todo.id!!)
            }
        }.decodeSingle<Todo>()
        return todo
    }

    override suspend fun insertImage(
        fileName: String,
        fileBytes: ByteArray
    ): String? {
        val bucket = supabase.storage.from("deepseek")
        var uploadedUrl: String? = null

        bucket.uploadAsFlow(fileName, fileBytes).collect { status ->
            when (status) {
                is UploadStatus.Progress -> {
                    val percent = status.totalBytesSend.toFloat() / status.contentLength * 100
                    println("Progress: $percent%")
                }
                is UploadStatus.Success -> {
                    println("Upload successful!")
                    uploadedUrl = bucket.publicUrl(fileName)
                }
            }
        }
        return uploadedUrl
    }


    override suspend fun deleteTask(id: Int): Boolean {
        supabase.from("cities").delete {
            filter {
                eq("id", id)
            }
        }
        return getTask(id) == null
    }
}