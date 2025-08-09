package com.jayr.supabasecrud.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jayr.supabasecrud.data.models.Todo
import com.jayr.supabasecrud.data.repository.TodoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {

//    state
    private val _todos = MutableStateFlow<List<Todo>>(emptyList())
    val todos: StateFlow<List<Todo>> get() = _todos

    val todoRepository = TodoRepository()

//    init
init {

}
//    methods

    fun generateDummytTodos(): List<Todo>{
        return  listOf(
            Todo(
                id = 1,
                createdAt = System.currentTimeMillis(),
                title = "Buy groceries",
                description = "Milk, eggs, bread, and fresh veggies",
                media = "https://example.com/images/groceries.jpg",
                isComplete = false,
                dueDate = System.currentTimeMillis() + 86_400_000 // +1 day
            ),
            Todo(
                id = 2,
                createdAt = System.currentTimeMillis(),
                title = "Finish Kotlin project",
                description = "Complete API integration and push to GitHub",
                media = "https://example.com/images/kotlin_project.png",
                isComplete = false,
                dueDate = System.currentTimeMillis() + (3 * 86_400_000) // +3 days
            ),
            Todo(
                id = 3,
                createdAt = System.currentTimeMillis(),
                title = "Morning workout",
                description = "30-minute run and stretching routine",
                media = "https://example.com/images/workout.mp4",
                isComplete = true,
                dueDate = System.currentTimeMillis() - 86_400_000 // yesterday
            ),
            Todo(
                id = 4,
                createdAt = System.currentTimeMillis(),
                title = "Plan vacation",
                description = "Research destinations and book flights",
                media = "https://example.com/images/vacation.jpg",
                isComplete = false,
                dueDate = System.currentTimeMillis() + (7 * 86_400_000) // +1 week
            )
        )

    }

    fun  getTodos(){
        viewModelScope.launch {
            todoRepository.getAllTasks()
        }
    }
}