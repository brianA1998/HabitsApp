package com.example.habitsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.habitsapp.navigation.NavigationHost
import com.example.habitsapp.navigation.NavigationRoute
import com.example.habitsapp.ui.theme.HabitsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HabitsAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavigationHost(
                        navHostController = navController,
                        startDestination = getStartDestination(),
                        logout = {
                            viewModel.logout()
                        }
                    )
                }
            }
        }
    }

    private fun getStartDestination(): NavigationRoute {

        if (viewModel.isLoggedIn) {
            return NavigationRoute.Home
        }

        return if (viewModel.hasSeenOnboarding) {
            NavigationRoute.Login
        } else {
            NavigationRoute.Onboarding
        }
    }
}
