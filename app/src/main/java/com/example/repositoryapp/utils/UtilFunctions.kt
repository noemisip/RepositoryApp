package com.example.repositoryapp.utils

import java.net.URLEncoder

fun myUrlEncoder(url: String): String {
    return URLEncoder.encode(url, "UTF-8")
}

fun replaceDTZ(input: String, replacement: String): String {
    val regex = Regex("[DTZ]")
    return regex.replace(input, replacement)
}
