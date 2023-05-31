package kk.example.todoapp.presentation.navigation

sealed class Screen(val route:String){


    object Home: Screen(route = "home_screen")
    object Add: Screen(route = "Add_screen")

}


