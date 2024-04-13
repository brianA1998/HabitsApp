package com.example.habitsapp.navigation


import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.habitsapp.onboarding.domain.usecase.HasSeenOnboardingUseCase
import com.example.habitsapp.onboarding.presentation.OnboardingScreen
import com.example.habitsapp.onboarding.presentation.OnboardingViewModel

@Composable
fun NavigationHost(navHostController: NavHostController,
                   startDestination: NavigationRoute){
    NavHost(navController = navHostController,
        startDestination = startDestination.route ){
        composable(NavigationRoute.Onboarding.route){
            OnboardingScreen(onFinish = {
                navHostController.popBackStack()
                navHostController.navigate(NavigationRoute.Login.route)
            }
            )
        }

        composable(NavigationRoute.Login.route){
            Text("Login Screen")
        }
    }
}