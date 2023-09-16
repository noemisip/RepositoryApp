package com.example.repositoryapp.data.model

import com.google.gson.annotations.SerializedName

data class Owner (
    @SerializedName("id") val id: Int? = null,
    @SerializedName("login") val name: String? = null,
    @SerializedName("avatar_url") var avatar: String? = null,
    @SerializedName("url") var url: String? = null
)