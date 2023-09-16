package com.example.repositoryapp.data.model

import com.google.gson.annotations.SerializedName

class ResponseBody {
    @SerializedName("items")
    var repos: List<Repository> = arrayListOf()
}