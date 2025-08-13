import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.jayr.supabasecrud.data.models.Todo
import com.jayr.supabasecrud.ui.screens.Routes
import com.jayr.supabasecrud.ui.screens.todo.TodoViewModel

@Composable
fun TodoScreen(
    navController: NavHostController,
    innerPaddingValues: PaddingValues,
    todoViewModel: TodoViewModel = viewModel()
) {
    val todos = todoViewModel.todos.collectAsState()


    Scaffold(
        floatingActionButton = {
            IconButton(
                colors = IconButtonColors(
                    containerColor = Color.Blue,
                    contentColor = Color.White,
                    disabledContainerColor = Color.Gray,
                    disabledContentColor = Color.DarkGray,
                ), onClick = {
                    navController.navigate(Routes.TodoForm.name)
                }) {
                Icon(
                    imageVector = Icons.Outlined.Add,
                    contentDescription = "Button to create new todo or task item"
                )
            }
        }) { innerPaddingValues ->

        LazyColumn(modifier = Modifier.padding(innerPaddingValues)) {
            itemsIndexed(todos.value) { index, item ->
                jobCard(item)
            }

        }
    }
}

@Composable
fun jobCard(todo: Todo) {
    Column(modifier = Modifier.padding(20.dp).background(Color.Gray)) {
        Text("Title: ${todo.title}")
        Text("description: ${todo.description}")
        Text("Image: ${todo.media}")
        Text("isComplete: ${todo.isComplete}")

    }
}