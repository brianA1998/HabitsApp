package com.example.habitsapp.onboarding.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.habitsapp.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingPager(
    pages: List<String>,
    onFinish: () -> Unit,
    modifier: Modifier = Modifier
){
    val pagerState = rememberPagerState()
    Box(modifier = Modifier){
        HorizontalPager(count = pages.size, state = pagerState) { index ->
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally){
                Spacer(modifier = Modifier.height(32.dp))
                Text("Titulo")
                Spacer(modifier = Modifier.height(32.dp))
                Image(painter = painterResource(id = R.drawable.onboarding1), contentDescription = "onboarding")
                Spacer(modifier = Modifier.height(32.dp))
                Text("Subtitle")
            }
        }
        Row(modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter).padding(64.dp, 16.dp,16.dp), verticalAlignment = Alignment.CenterVertically) {
            
        }
    }
}