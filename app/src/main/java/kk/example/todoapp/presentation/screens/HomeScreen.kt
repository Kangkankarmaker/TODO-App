package kk.example.todoapp.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import kk.example.todoapp.R
import kk.example.todoapp.data.Task
import kk.example.todoapp.presentation.addTask.MainViewModel
import kk.example.todoapp.presentation.navigation.Screen
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    navHostController: NavHostController, mainViewModel: MainViewModel = hiltViewModel()
) {
    HomeScreenView(
        viewModel = mainViewModel, navHostController = navHostController
    )
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun HomeScreenView(
    viewModel: MainViewModel,
    navHostController: NavHostController,
) {
    var textSearch by remember { mutableStateOf("") }
    val stateScroll = rememberLazyListState()
    val list: State<List<Task>> = viewModel.listTasks.collectAsState(listOf())
    val coroutineScope = rememberCoroutineScope()


    Column {

        // Show empty state
        if (list.value.isEmpty()) EmptyState()


        val color = if (isSystemInDarkTheme()) Color.White else Color.Black.copy(.7f)

        // Top App Bar
        CenterAlignedTopAppBar(
            modifier = Modifier,
            colors = TopAppBarDefaults.smallTopAppBarColors(MaterialTheme.colorScheme.background),
            title = {
                Text(
                    text = "All Tasks",
                    color = color
                )
            },

            navigationIcon = {
                IconButton(onClick = { }) {
                    Icon(
                        modifier = Modifier.size(20.dp),
                        painter = painterResource(id = R.drawable.menu),
                        contentDescription = "Menu icon",
                        tint = color
                    )
                }
            },
        )



        LazyColumn(
            
            state = stateScroll
        ) {
            coroutineScope.launch {
                stateScroll.animateScrollToItem(0, 0)
            }
            items(
                list.value,
                key = { task -> task.id!! }
            ) { task ->
                var complite by remember { mutableStateOf(task.complitable) }
                if (textSearch.isEmpty())
                    ItemTask(
                        task = task,
                        isComplete = complite,
                        onCompleteChange = {
                            complite = it
                            task.complitable = !task.complitable
                            viewModel.updateTask(task)
                        }
                    )
                else if (task.title.lowercase().contains(textSearch))
                    ItemTask(
                        task = task,
                        isComplete = complite,
                        onCompleteChange = {
                            complite = it
                            task.complitable = !task.complitable
                            viewModel.updateTask(task)
                        }
                    )
            }
        }
    }


    // Button Add Task
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color.Transparent,Color.Transparent, Color.White.copy(.6f))
                )
            ),
        contentAlignment = Alignment.BottomCenter

    ) {
        FloatingActionButton(
            modifier = Modifier
                .padding(10.dp)
                .size(65.dp),
            onClick = {
                navHostController.navigate(Screen.Add.route)
            },
            shape = MaterialTheme.shapes.small.copy(CornerSize(percent = 50)),
            elevation = FloatingActionButtonDefaults.elevation(18.dp)
        ) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
        }
    }
}

@Composable
fun EmptyState() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Add a Task",
            fontSize = 23.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemTask(
    task: Task,
    isComplete: Boolean,
    onCompleteChange: (Boolean) -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp, start = 20.dp, end = 20.dp, bottom = 5.dp),
        shape = RoundedCornerShape(30.dp)
    ) {

        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {


                Text(
                    text = task.title,
                    fontSize = 17.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 15.dp)
                        .weight(9f),
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(textDecoration = if (task.complitable) TextDecoration.LineThrough else TextDecoration.None),
                    color = if (task.complitable) {
                        Color.Gray
                    } else {
                        if (isSystemInDarkTheme()) Color.White else Color.Black
                    }
                )


                Checkbox(
                    modifier = Modifier
                        .clip(CircleShape)
                        .weight(1f),
                    checked = isComplete,
                    onCheckedChange = onCompleteChange,
                )


            }


        }
    }
}