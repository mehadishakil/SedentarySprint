package com.example.loginsignup_jetpackcompose.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.amplify.presentation.screens.ProfileScreen
import com.example.loginsignup_jetpackcompose.screens.HomeScreen

@Composable
fun AmplifyApp() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        // SignUpScreen()
        ProfileScreen()
    }
}