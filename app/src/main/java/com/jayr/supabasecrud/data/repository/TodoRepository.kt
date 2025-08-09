package com.jayr.supabasecrud.data.repository

import com.jayr.supabasecrud.data.models.Todo
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.storage.Storage

class TodoRepository : TodoService {

    val supabase = createSupabaseClient(
        supabaseUrl = "https://xyzcompany.supabase.co",
        supabaseKey = "public-anon-key"
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

    override suspend fun deleteTask(id: Int): Boolean {
        supabase.from("cities").delete {
            filter {
                eq("id", id)
            }
        }
        return getTask(id) == null
    }
}