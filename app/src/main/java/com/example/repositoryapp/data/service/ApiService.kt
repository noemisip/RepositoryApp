package com.example.repositoryapp.data.service

import com.example.repositoryapp.data.model.Repository
import com.example.repositoryapp.data.model.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("repositories")
    suspend fun getRepositories(@Query("q") word: String): ResponseBody
}