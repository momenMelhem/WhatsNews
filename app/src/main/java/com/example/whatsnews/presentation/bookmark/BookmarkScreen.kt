package com.example.whatsnews.presentation.bookmark

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.whatsnews.R
import com.example.whatsnews.domain.model.Article
import com.example.whatsnews.navigation.Route
import com.example.whatsnews.presentation.common.ArticlesList

@Composable
fun BookmarkScreen (
    state: BookmarkState,
    navigateToDetails:(Article) -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(top = 24.dp, start = 24.dp, end = 24.dp)
    ) {
        Text(
            text = stringResource(R.string.bookmark),
            style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold),
            color = Color.Black
        )
        Spacer(modifier = Modifier
            .height(24.dp)
        )
       ArticlesList(
           articles = state.articles ,
           onClick = navigateToDetails
       )

    }
}