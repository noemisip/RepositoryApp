package com.example.repositoryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.example.repositoryapp.viewmodel.RepositoryViewModel
import com.example.repositoryapp.ui.nav.MyNavHost
import com.example.repositoryapp.ui.theme.RepositoryAppTheme
import dagger.hilt.android.AndroidEntryPoint

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