package com.example.repositoryapp.ui.screens.details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.repositoryapp.data.model.Repository
import com.example.repositoryapp.ui.common.StarRow
import com.example.repositoryapp.utils.replaceDTZ

@Composable
fun ScrollableContent(repo: Repository) {
    Text(
        repo.name.toString(), fontSize = 25.sp,
        modifier = Modifier.padding(horizontal = 15.dp)
    )
    Spacer(Modifier.height(10.dp))
    Text(
        repo.desc.toString(),
        textAlign = TextAlign.Start,
        modifier = Modifier.padding(horizontal = 15.dp)
    )
    Spacer(Modifier.height(20.dp))
    Column(
        Modifier
            .padding(start = 15.dp)
    ) {
        Row {
            Text("Forks: ", fontWeight = FontWeight.SemiBold)
            Text(repo.forks.toString())
        }
        Row {
            Text("Stars: ", fontWeight = FontWeight.SemiBold)
            StarRow(string = repo.stars.toString())
        }
        Row {
            Text("Created: ", fontWeight = FontWeight.SemiBold)
            Text(replaceDTZ(repo.created.toString(), " "))
        }
        Row {
            Text("Last updated: ", fontWeight = FontWeight.SemiBold)
            Text(replaceDTZ(repo.updated.toString(), " "))
        }
    }
}
