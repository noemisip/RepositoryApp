package com.example.repositoryapp.ui.screens.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.repositoryapp.data.ApiState
import com.example.repositoryapp.data.RepositoryViewModel
import com.example.repositoryapp.data.model.Owner
import com.example.repositoryapp.data.model.Repository
import com.example.repositoryapp.ui.nav.NAV_DETAILS
import com.example.repositoryapp.ui.screens.main.components.RepoItem
import com.example.repositoryapp.ui.screens.main.components.SearchField
import com.example.repositoryapp.utils.MyUrlEncoder
import com.squareup.moshi.Moshi
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: RepositoryViewModel, navController: NavHostController) {

    var searchField by remember { mutableStateOf("") }
    //var repositories by remember { mutableStateOf<List<Repository>>() }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.primaryContainer,
                ),
                title = {
                    Text("Repositories")
                },
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            SearchField(searchField = searchField, onValueChanged = { newText ->
                searchField = newText
            }, onClick = {
                viewModel.getRepos(searchField)

            })
            Spacer(Modifier.height(15.dp))
            when (val result = viewModel.response.value) {
                is ApiState.Success -> {
                    LazyColumn {
                        items(result.data.repos) { repository ->
                                RepoItem(
                                    repo = repository,
                                    modifier = Modifier.clickable {
//                                        navController.navigate(
//                                            NAV_DETAILS //Just modify your route accordingly
//                                                .replace(
//                                                    oldValue = "{name}",
//                                                    newValue = repository.name.toString()
//                                                )
//                                        )
                                        val moshi = Moshi.Builder().build()
                                        val jsonAdapter = moshi.adapter(Repository::class.java).lenient()
                                        val repoJson = jsonAdapter.toJson(Repository(
                                            id = repository.id,
                                            name = repository.name,
                                            desc = repository.desc,
                                            forks = repository.forks,
                                            url = repository.url?.let { MyUrlEncoder(it) },
                                            stars = repository.stars,
                                            created = repository.created,
                                            updated = repository.updated,
                                            owner = Owner(
                                                id = repository.owner?.id,
                                                name = repository.owner?.name,
                                                url = repository.owner?.url?.let { MyUrlEncoder(it) },
                                                avatar = repository.owner?.avatar?.let { MyUrlEncoder(it) },
                                            )
                                        ))
                                        navController.navigate(
                                            "details/{repo}".replace("{repo}", repoJson) //Just modify your route accordingly
                                        )
//                                        navController.currentBackStackEntry?.savedStateHandle?.set(
//                                            key = "repo",
//                                            value = repository
//                                        )

                                    }
                                )
                        }
                    }
                }

                is ApiState.Error -> {
                    Text(text = "${result.msg}")
                }

                ApiState.Loading -> {
                    CircularProgressIndicator()
                }

                ApiState.Empty -> {

                }
            }
        }
    }
}

