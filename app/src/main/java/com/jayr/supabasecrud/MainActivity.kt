package com.jayr.supabasecrud

import TodoScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jayr.supabasecrud.data.models.Todo
import com.jayr.supabasecrud.ui.screens.Routes
import com.jayr.supabasecrud.ui.screens.todoform.TodoForm
import com.jayr.supabasecrud.ui.theme.SupabaseCRUDTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SupabaseCRUDTheme {
                val navController: NavHostController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Navigation(
                        navController,
                        innerPadding
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}
@Composable
fun Navigation(navController: NavHostController, innerPaddingValues: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = Routes.Home.name,
    ) {
        composable(route = Routes.TodoForm.name) {
            TodoForm(
                innerPaddingValues = innerPaddingValues,
                navController = navController,
                modifier = Modifier)
        }
        composable(route = Routes.Home.name) {
            TodoScreen(innerPaddingValues = innerPaddingValues,
                navController = navController)
        }
    }
}

@Composable
fun LandingPage(x0: PaddingValues, x1: NavHostController) {
    TODO("Not yet implemented")
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SupabaseCRUDTheme {
        Greeting("Android")
    }
}