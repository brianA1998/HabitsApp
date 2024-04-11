package com.example.habitsapp.onboarding.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.habitsapp.R
import com.example.habitsapp.onboarding.presentation.components.OnboardingPager

@Composable
fun OnboardingScreen(
    viewmodel: OnboardingViewModel = hiltViewModel(),
    onFinish: () -> Unit
){
    LaunchedEffect(key1 = viewmodel.hasSeenOnboarding){
        if(viewmodel.hasSeenOnboarding){
            onFinish()
        }
    }
    val pages = listOf(
      OnboardingPagerInformation("Welcome to Monumental Habits",
          "We can help you to be a better version of yourself",
          R.drawable.onboarding1),
        OnboardingPagerInformation("Create new habits easily",
            "We can help you to be a better version of yourself",
            R.drawable.onboarding2),
        OnboardingPagerInformation("Keep track of your progress",
            "We can help you to be a better version of yourself",
            R.drawable.onboarding3),
        OnboardingPagerInformation("Join a supportive community",
            "We can help you to be a better version of yourself",
            R.drawable.onboarding4)
    )
    
    OnboardingPager(pages = pages, onFinish = {
        viewmodel.completeOnboarding()
    })
}