package com.example.repositoryapp.ui.screens.details

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.repositoryapp.data.model.Repository
import com.example.repositoryapp.ui.screens.details.components.DetailsHeader
import com.example.repositoryapp.ui.screens.details.components.ScrollableContent
import com.example.repositoryapp.ui.screens.details.components.UserContainer

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(navController: NavHostController, repo: Repository) {

    val appBarSize by remember { mutableStateOf(0.dp) }
    val appBarHeightPx = with(LocalDensity.current) { appBarSize }

    Scaffold(
        topBar = {
            DetailsHeader(repo = repo, navController = navController)
        }
    ) {
        Column(
            modifier = Modifier.padding(top = appBarHeightPx + 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(40.dp))
            UserContainer(repo = repo)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(
                        MaterialTheme.colorScheme.primaryContainer
                    )
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(16.dp),
                ) {
                    items(1) {
                        ScrollableContent(repo = repo)
                    }
                }
            }
        }
    }
}

