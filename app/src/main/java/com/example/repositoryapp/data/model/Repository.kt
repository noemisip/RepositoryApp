package com.example.repositoryapp.data.model

import com.google.gson.annotations.SerializedName

data class Repository(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("description") val desc: String? = null,
    @SerializedName("forks") val forks: Int? = null,
    @SerializedName("url") var url: String? = null,
    @SerializedName("stargazers_count") val stars: Int? = null,
    @SerializedName("created_at") val created: String? = null,
    @SerializedName("updated_at") val updated: String? = null,
    @SerializedName("owner") val owner: Owner? = null
    )