package com.example.repositoryapp.utils

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StarRow ( string: String){
    Row (
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(string,
            fontSize = 18.sp)
        Spacer(Modifier.width(4.dp))
        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = null,
            tint =  MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(18.dp)
        )
    }
}