package com.example.repositoryapp.utils

import java.net.URLEncoder

fun MyUrlEncoder(url: String): String {
    return URLEncoder.encode(url, "UTF-8")
}