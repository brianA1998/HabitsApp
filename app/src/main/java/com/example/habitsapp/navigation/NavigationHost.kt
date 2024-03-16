package com.example.habitsapp.navigation


import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavigationHost(navHostController: NavHostController,
                   startDestination: NavigationRoute){
    NavHost(navController = navHostController,
        startDestination = startDestination.route ){
        composable(NavigationRoute.Onboarding.route){
            Text("Soy onboarding")
        }
    }
}