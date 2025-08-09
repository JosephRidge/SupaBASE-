package com.jayr.supabasecrud.ui.screens.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = viewModel (),
    modifier: Modifier
){
    val todos = homeViewModel.todos.collectAsState()

    LazyColumn(modifier = modifier) {
        itemsIndexed(todos.value) {  index, item ->
            Text(
                text=item.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold)
            Text(
                text=item.description,
                fontSize = 14.sp,
                fontWeight = FontWeight.Light)
            Text(
                text="Due on ${item.dueDate}",
                fontSize = 14.sp,
                fontWeight = FontWeight.Light)
            Text(
                text= if(item.isComplete == true) {"Completed"}else{"incomplete!"},
                fontSize = 14.sp,
                fontWeight = FontWeight.Light)
            AsyncImage(
                model = item.media,
                contentDescription = "Image of ${item.title} task"
            )
        }
    }
}