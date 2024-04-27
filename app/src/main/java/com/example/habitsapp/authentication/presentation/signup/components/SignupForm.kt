package com.example.habitsapp.authentication.presentation.signup.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.habitsapp.authentication.presentation.signup.SignupState
import com.example.habitsapp.core.presentation.HabitTextfield
import com.example.habitsapp.core.presentation.HabitTitle

@Composable
fun SignupForm(state: SignupState, modifier: Modifier = Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        HabitTitle(title = "Create your account")
        Spacer(modifier = Modifier.height(32.dp))
        HabitTextfield(value = state.email, onValueChange =, placeholder =, contentDescription =)
    }
}