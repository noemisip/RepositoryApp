package com.example.repositoryapp.ui.screens.details

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.repositoryapp.data.model.Repository
import com.example.repositoryapp.utils.StarRow

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(navController: NavHostController, repo: Repository) {
    var appBarSize by remember { mutableStateOf(0.dp) }
    val appBarHeightPx = with(LocalDensity.current) { appBarSize }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.primaryContainer,
                ),
                title = {
                    Text(repo.name.toString())

                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigateUp()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            tint = MaterialTheme.colorScheme.primaryContainer,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier.padding(top = appBarHeightPx + 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(40.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(250.dp)
                    .background(
                        MaterialTheme.colorScheme.primaryContainer
                    )
            ) {

                Column(
                    Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box (
                        modifier = Modifier
                            .size(150.dp) // Set the size of the image
                            .clip(shape = CircleShape)  // Apply rounded corners to the image
                    ){
                        AsyncImage(
                            model = repo.owner?.avatar,
                            contentDescription = "The delasign logo",
                        )
                    }
                    Spacer(Modifier.height(5.dp))
                    Text(
                        text = repo.owner?.name.toString(),
                        fontSize = 20.sp
                    )
                    Text(
                        text = repo.owner?.url.toString(),
                        fontSize = 15.sp
                    )
                }

            }
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(repo.name.toString(), fontSize = 25.sp)
                Spacer(Modifier.height(10.dp))
                Text(repo.desc.toString())
                Spacer(Modifier.height(30.dp))
                Box(modifier = Modifier.fillMaxSize()) {
                    Column(
                        Modifier
                            .padding(start = 15.dp)
                            .align(Alignment.TopStart)
                    ) {
                        Text("Created: " + repo.created.toString())
                        Text("Last Updated: " + repo.updated.toString())
                    }
                    Column(
                        Modifier
                            .padding(end = 15.dp)
                            .align(Alignment.TopEnd)
                    ) {
                        Text(repo.forks.toString())
                        StarRow(string = repo.stars.toString())

                    }
                }

            }
        }

    }
}