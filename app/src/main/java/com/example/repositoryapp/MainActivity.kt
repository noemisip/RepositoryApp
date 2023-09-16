package com.example.repositoryapp

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.repositoryapp.data.RepositoryViewModel
import com.example.repositoryapp.ui.nav.MyNavHost
import com.example.repositoryapp.ui.screens.main.MainScreen
import com.example.repositoryapp.ui.theme.RepositoryAppTheme
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: RepositoryViewModel by viewModels()
        setContent {
            RepositoryAppTheme {
               MyNavHost(navController = rememberNavController(), viewModel = viewModel)
            }
        }
    }
}


@HiltAndroidApp
class RepositoryApp : Application()