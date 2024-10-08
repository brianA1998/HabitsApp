package com.example.habitsapp.settings.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.habitsapp.settings.presentation.components.SettingsItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onBack: () -> Unit,
    onLogout: () -> Unit
) {
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        CenterAlignedTopAppBar(title = {
            Text(text = "Settings")
        }, navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back")
            }
        })
    }) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it)
                .padding(20.dp), verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            Text(
                text = "Support",
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.tertiary
            )
            SettingsItem(text = "Contact", icon = Icons.Default.Phone, onClick = { /*TODO*/ })
            SettingsItem(text = "Log out", icon = Icons.Default.ExitToApp, onClick =  onLogout )
        }

    }
}