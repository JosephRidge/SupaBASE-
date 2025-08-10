package com.jayr.supabasecrud.ui.screens.todoform

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jayr.supabasecrud.data.models.Todo
import com.jayr.supabasecrud.data.repository.TodoRepository
import com.jayr.supabasecrud.data.repository.UploadResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.sql.Date

class TodoViewModel : ViewModel() {
    val todoRepository = TodoRepository()

    private val _uploadProgress = MutableStateFlow<Float>(0f)
    val uploadProgress: StateFlow<Float> get() = _uploadProgress

    private val _todo: MutableStateFlow<Todo> = MutableStateFlow(Todo(
        title = "",
        media = "",
        description = "",
        isComplete = false,
        dueDate = 0,
        ))

    val todo: StateFlow<Todo> get() = _todo

    fun createPlace(title: String, description:String, dueDate: Long) {
        viewModelScope.launch {
            _todo.value = _todo.value.copy(title = title,
                description = description
                        ,dueDate = dueDate
                        ,isComplete = false)
            todoRepository.createTask(_todo.value)
        }
    }

    fun insertImage(fileName: String, fileBytes: ByteArray) {
        viewModelScope.launch {
            todoRepository.insertImage(fileName, fileBytes).collect { result ->
                when (result) {
                    is UploadResult.Progress -> {
                        _uploadProgress.value = result.percent
                    }
                    is UploadResult.Success -> {
                       _todo.value = _todo.value.copy(media = result.url)
                    }
                }

            }
        }
    }
}