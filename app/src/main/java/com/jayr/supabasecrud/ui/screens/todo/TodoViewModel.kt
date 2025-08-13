package com.jayr.supabasecrud.ui.screens.todo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jayr.supabasecrud.data.models.Todo
import com.jayr.supabasecrud.data.repository.TodoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TodoViewModel : ViewModel() {
    val todoRepository = TodoRepository()
// state

    private val _todos = MutableStateFlow<List<Todo>>(emptyList())
    val todos: StateFlow<List<Todo>> get() = _todos

    // init
    init {
        getTodos()
    }

    // method
    fun getTodos() {
        viewModelScope.launch {
            _todos.value = todoRepository.getAllTasks()
        }
    }


}