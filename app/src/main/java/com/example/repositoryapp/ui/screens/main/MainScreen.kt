package com.example.repositoryapp.ui.screens.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.repositoryapp.utils.ApiState
import com.example.repositoryapp.viewmodel.RepositoryViewModel
import com.example.repositoryapp.data.model.Repository
import com.example.repositoryapp.ui.screens.main.components.MainHeader
import com.example.repositoryapp.ui.screens.main.components.RepoItem
import com.example.repositoryapp.ui.screens.main.components.SearchField
import com.example.repositoryapp.ui.screens.main.components.StateContainer
import com.example.repositoryapp.utils.myUrlEncoder
import com.squareup.moshi.Moshi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: RepositoryViewModel, navController: NavHostController) {

    var searchField by remember { mutableStateOf("") }

    Scaffold(
        topBar = { MainHeader() }
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

                    if (result.data.repos.isNotEmpty()) {

                        LazyColumn {
                            items(result.data.repos) { repository ->

                                repository.url = repository.url?.let { myUrlEncoder(it) }
                                repository.owner?.url =
                                    repository.owner?.url?.let { myUrlEncoder(it) }
                                repository.owner?.avatar =
                                    repository.owner?.avatar?.let { myUrlEncoder(it) }

                                RepoItem(
                                    repo = repository,
                                    modifier = Modifier.clickable {

                                        val moshi = Moshi.Builder().build()
                                        val jsonAdapter =
                                            moshi.adapter(Repository::class.java).lenient()
                                        val repoJson = jsonAdapter.toJson(repository)
                                        navController.navigate(
                                            "details/{repo}".replace("{repo}", repoJson)
                                        )
                                    }
                                )
                            }
                        }
                    } else {
                        StateContainer(text = "No such repository found")
                    }
                }

                is ApiState.Error -> {
                    StateContainer(text = "${result.msg}")
                }

                ApiState.Loading -> {
                    Column(
                        Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator()
                    }
                }

                ApiState.Empty -> {
                    StateContainer(text = "Start searching repos on GitHub")
                }
            }
        }
    }
}

