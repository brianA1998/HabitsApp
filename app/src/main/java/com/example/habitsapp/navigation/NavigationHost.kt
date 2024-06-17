package com.example.habitsapp.navigation


import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.habitsapp.authentication.presentation.login.LoginScreen
import com.example.habitsapp.authentication.presentation.signup.SignupScreen
import com.example.habitsapp.home.presentation.detail.DetailScreen
import com.example.habitsapp.home.presentation.home.HomeScreen
import com.example.habitsapp.onboarding.domain.usecase.HasSeenOnboardingUseCase
import com.example.habitsapp.onboarding.presentation.OnboardingScreen
import com.example.habitsapp.onboarding.presentation.OnboardingViewModel

@Composable
fun NavigationHost(
    navHostController: NavHostController,
    startDestination: NavigationRoute
) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination.route
    ) {
        composable(NavigationRoute.Onboarding.route) {
            OnboardingScreen(onFinish = {
                navHostController.popBackStack()
                navHostController.navigate(NavigationRoute.Login.route)
            }
            )
        }

        composable(NavigationRoute.Login.route) {
            LoginScreen(onLogin = {
                navHostController.popBackStack()
                navHostController.navigate(NavigationRoute.Home.route)
            }, onSignUp = {
                navHostController.navigate(NavigationRoute.Signup.route)
            })
        }

        composable(NavigationRoute.Signup.route) {
            SignupScreen(onSignIn = {
                navHostController.navigate(NavigationRoute.Home.route) {
                    popUpTo(navHostController.graph.id) {
                        inclusive = true
                    }
                }
            }, onLogin = {
                navHostController.popBackStack()
            })
        }

        composable(NavigationRoute.Home.route) {
            HomeScreen(onNewHabit = {
                navHostController.navigate(NavigationRoute.Detail.route)
            }, onSettings = {
                navHostController.navigate(NavigationRoute.Settings.route)
            },
                onEditHabit = {
                    navHostController.navigate(NavigationRoute.Detail.route + "?habitId=$it")
                })
        }

        composable(NavigationRoute.Detail.route + "?habitId={habitId}", arguments = listOf(
            navArgument("habitId") {
                type = NavType.StringType
                nullable = true
                defaultValue = null
            }
        )) {
            DetailScreen(
                onBack = { navHostController.popBackStack() },
                onSave = { navHostController.popBackStack() })
        }
    }
}