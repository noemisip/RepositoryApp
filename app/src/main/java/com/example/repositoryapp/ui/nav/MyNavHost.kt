package com.example.repositoryapp.ui.nav

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.repositoryapp.viewmodel.RepositoryViewModel
import com.example.repositoryapp.data.model.Repository
import com.example.repositoryapp.ui.screens.details.DetailsScreen
import com.example.repositoryapp.ui.screens.main.MainScreen
import com.example.repositoryapp.utils.NAV_DETAILS
import com.example.repositoryapp.utils.NAV_SEARCH
import com.squareup.moshi.Moshi

@Composable
fun MyNavHost(navController: NavHostController, viewModel: RepositoryViewModel) {
    NavHost(
        navController = navController,
        startDestination = NAV_SEARCH
    ) {
        composable(NAV_SEARCH) {
            MainScreen(viewModel, navController)
        }
        composable(NAV_DETAILS) { backStackEntry ->
            val repoJson = backStackEntry.arguments?.getString("repo")
            val moshi = Moshi.Builder().build()
            val jsonAdapter = moshi.adapter(Repository::class.java).lenient()
            val repoObject = repoJson?.let { jsonAdapter.fromJson(it) }
            Log.d("DetailsScreen", repoJson.toString())
            if (repoObject != null) {
                DetailsScreen(navController = navController, repo = repoObject)
            }
        }
    }
}