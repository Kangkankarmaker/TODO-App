package kk.example.todoapp.presentation.screens

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import kk.example.todoapp.R
import kk.example.todoapp.data.Task
import kk.example.todoapp.presentation.addTask.AddTaskViewModel

@Composable
fun AddTaskScreen(
    navHostController: NavHostController,
    addTaskViewModel: AddTaskViewModel = hiltViewModel()
) {
    val current = LocalContext.current

    var title by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {

        val color = if (isSystemInDarkTheme()) Color.White else Color.Black.copy(.7f)
        // Top App Bar
        CenterAlignedTopAppBar(
            modifier = Modifier,
            colors = TopAppBarDefaults.smallTopAppBarColors(MaterialTheme.colorScheme.background),
            title = {
                Text(
                    text = "Add Tasks",
                    color = color
                )
            },

            navigationIcon = {
                IconButton(onClick = {
                    navHostController.popBackStack()
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Menu icon",
                        tint = color
                    )
                }
            },
        )


        // Edit Text Title
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp, start = 40.dp, end = 40.dp),
            value = title,
            onValueChange = { title = it },
            label = {
                Text(text = "Title")
            },
            singleLine = true
        )



        // Button Save Task
        Button(
            onClick = {
                if (title.isEmpty()) Toast.makeText(current, "Title is empty", Toast.LENGTH_SHORT).show()
                else {
                    val task = Task(null, title, false)
                    addTaskViewModel.addTask(task)
                    navHostController.popBackStack()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(top = 50.dp, start = 40.dp, end = 40.dp),
        ) {
            Text(text = "SAVE")
        }
    }
}

