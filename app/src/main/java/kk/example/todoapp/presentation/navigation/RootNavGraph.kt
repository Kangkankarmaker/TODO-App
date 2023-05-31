package kk.example.todoapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kk.example.todoapp.presentation.screens.AddTaskScreen
import kk.example.todoapp.presentation.screens.HomeScreen

@Composable
fun RootNavigationGraph(

    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination =Screen.Home.route
    ) {



        composable(route = Screen.Home.route) {
            HomeScreen(navController)
        }

        composable(route = Screen.Add.route) {
            AddTaskScreen(navController)
        }


    }
}


