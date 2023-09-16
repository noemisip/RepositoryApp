package com.example.repositoryapp.ui.nav

import android.util.Log
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.repositoryapp.data.RepositoryViewModel
import com.example.repositoryapp.data.model.Owner
import com.example.repositoryapp.data.model.Repository
import com.example.repositoryapp.ui.screens.details.DetailsScreen
import com.example.repositoryapp.ui.screens.main.MainScreen
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.squareup.moshi.Moshi

@Composable
fun MyNavHost(navController: NavHostController, viewModel: RepositoryViewModel ) {
    NavHost(
        navController = navController,
        startDestination = NAV_SEARCH
    ) {
        composable(NAV_SEARCH) {
            MainScreen(viewModel, navController)
        }
//        composable("details"){
//            val result = navController.previousBackStackEntry?.savedStateHandle?.get<Repository>("repo")
//            Log.d("DetailsScreen", "${result?.name}")
//            if (result != null) {
//                DetailsScreen(navController = navController, repo = result )
//            }
//        }
        composable("details/{repo}") { backStackEntry ->
            val repoJson =  backStackEntry.arguments?.getString("repo")
            val moshi = Moshi.Builder().build()
            val jsonAdapter = moshi.adapter(Repository::class.java).lenient()
            val repoObject = repoJson?.let { jsonAdapter.fromJson(it) }
            Log.d("DetailsScreen", repoJson.toString())
            if (repoObject != null) {
                DetailsScreen(navController =  navController, repo = repoObject)
            }
        }
//        composable(NAV_DETAILS) { navBackStackEntry ->
//
//            val name = navBackStackEntry.arguments?.getString("name")
//            //val desc = navBackStackEntry.arguments?.getString("desc")
////            val forks = navBackStackEntry.arguments?.getString("forks")
////            val url = navBackStackEntry.arguments?.getString("url")
////            val stars = navBackStackEntry.arguments?.getString("stars")
////            val created = navBackStackEntry.arguments?.getString("created")
////            val updated = navBackStackEntry.arguments?.getString("updated")
////            val ownerName = navBackStackEntry.arguments?.getString("ownerName")
////            val ownerAvatar = navBackStackEntry.arguments?.getString("ownerAvatar")
////            val ownerUrl = navBackStackEntry.arguments?.getString("ownerUrl")
//
//            name?.let { name->
//                DetailsScreen(navController, repo = Repository(
//                    name = name,
//                    //desc = desc,
////                    forks = forks?.toInt(),
////                    url = url,
////                    stars = stars?.toInt(),
////                    created = created,
////                    updated = updated,
////                    owner = Owner(name = ownerName, avatar = ownerAvatar, url = ownerUrl)
//                ))
//            }
        //}
    }
}