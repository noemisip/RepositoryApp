package com.example.repositoryapp.ui.screens.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.repositoryapp.data.model.Repository

@Composable
fun UserContainer( repo: Repository) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(shape = RoundedCornerShape(15.dp))
            .background(
                MaterialTheme.colorScheme.primary
            )
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(70.dp)
                    .clip(shape = CircleShape)
            ) {
                AsyncImage(
                    model = repo.owner?.avatar,
                    contentDescription = "User avatar",
                )

            }
            Spacer(Modifier.width(25.dp))
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = repo.owner?.name.toString(),
                    fontSize = 20.sp,
                    color = Color.White
                )
                Spacer(Modifier.height(5.dp))
                Text(
                    text = repo.owner?.url.toString(),
                    fontSize = 15.sp,
                    color = Color.White
                )
            }
        }
    }
}