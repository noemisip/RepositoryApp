package com.example.repositoryapp.ui.screens.main.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.repositoryapp.data.model.Repository
import com.example.repositoryapp.utils.StarRow

@Composable
fun RepoItem(repo: Repository, modifier: Modifier)
{
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    val halfScreenWidth = (screenWidth / 2)
    Column (modifier = modifier){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .width(halfScreenWidth)
                    .padding(0.dp)
            ) {
                repo.name?.let { Text(it, fontSize = 20.sp) }
                Spacer(Modifier.height(8.dp))
                repo.desc?.let { Text(it, fontSize = 14.sp) }
            }
            Column(
                modifier = Modifier.align(Alignment.BottomEnd),
                horizontalAlignment = Alignment.End
            ) {
                StarRow(string = repo.stars.toString())
                Spacer(Modifier.width(8.dp))
                Text(repo.updated.toString(), fontSize = 14.sp)
            }
        }
        Divider(
            color = MaterialTheme.colorScheme.primaryContainer,
            thickness = 1.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp)
        )
    }
}
