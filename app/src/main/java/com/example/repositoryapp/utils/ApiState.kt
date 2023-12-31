package com.example.repositoryapp.utils

import com.example.repositoryapp.data.model.ResponseBody

sealed class ApiState {
    class Success(val data: ResponseBody) : ApiState()
    class Error(val msg: Throwable) : ApiState()
    object Loading : ApiState()
    object Empty : ApiState()
}