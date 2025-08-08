package com.jayr.supabasecrud.data.repository

import com.jayr.supabasecrud.data.models.Todo
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.ktor.websocket.WebSocketDeflateExtension.Companion.install

class TodoRepository: TodoService {
    val supabase = createSupabaseClient(
        supabaseUrl = "https://xyzcompany.supabase.co",
        supabaseKey = "public-anon-key"
    ) {
        install(Auth)
        install(Postgrest)
        //install other modules
    }

    override suspend fun createTask(todo: Todo): Todo? {
        TODO("Not yet implemented")
    }

    override suspend fun getAllTasks(): List<Todo> {
        TODO("Not yet implemented")
    }

    override suspend fun getTask(id: Int): Todo? {
        TODO("Not yet implemented")
    }

    override suspend fun updateTask(todo: Todo): Todo? {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTask(id: Int): Boolean {
        TODO("Not yet implemented")
    }
}