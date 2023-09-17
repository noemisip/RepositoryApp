package com.example.repositoryapp.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.repositoryapp.utils.ApiState
import com.example.repositoryapp.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepositoryViewModel
@Inject constructor(private val mainRepository: MainRepository) : ViewModel() {
    val response: MutableState<ApiState> = mutableStateOf(ApiState.Empty)
    fun getRepos(param: String) =
        viewModelScope.launch {
            mainRepository.getRepos(q = param).onStart {
                response.value = ApiState.Loading
            }.catch {
                response.value = ApiState.Error(it)
            }.collect {
                response.value = ApiState.Success(it)
            }
        }
}