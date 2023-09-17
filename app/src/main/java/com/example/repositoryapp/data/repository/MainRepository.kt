package com.example.repositoryapp.data.repository

import com.example.repositoryapp.data.model.ResponseBody
import com.example.repositoryapp.data.service.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainRepository
@Inject
constructor(private val apiService: ApiService){
    fun getRepos(q : String): Flow<ResponseBody> = flow {
        emit(apiService.getRepositories(q))
    }.flowOn(Dispatchers.IO)

}