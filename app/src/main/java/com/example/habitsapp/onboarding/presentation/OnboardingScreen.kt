package com.example.habitsapp.onboarding.presentation

import androidx.compose.runtime.Composable
import com.example.habitsapp.onboarding.presentation.components.OnboardingPager

@Composable
fun OnboardingScreen(){
    val pages = listOf(
        "titulo 1",
        "titulo 2",
        "titulo 3"
    )
    
    OnboardingPager(pages = pages, onFinish = { /*TODO*/ })
}