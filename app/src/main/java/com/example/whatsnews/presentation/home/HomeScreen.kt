package com.example.whatsnews.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.example.whatsnews.R
import com.example.whatsnews.domain.model.Article
import com.example.whatsnews.navigation.Route
import com.example.whatsnews.presentation.common.ArticlesList
import com.example.whatsnews.presentation.common.SearchBar

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    articles:LazyPagingItems<Article>,
    navigateToSearch:()->Unit,
    navigateToDetails:(Article)->Unit
) {
    val titles by remember {
        derivedStateOf {
            if(articles.itemCount>10){
                articles.itemSnapshotList.items
                    .slice(IntRange(start = 0, endInclusive = 9))
                    .joinToString (separator = "\uD83d\uDFE5"){it.title}
            }
            else{
                ""
            }
        }
    }
    Column (
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
            Image(
                modifier = Modifier
                    .width(150.dp)
                    .height(30.dp)
                    .padding(horizontal = 24.dp),
                painter = painterResource(id = R.drawable.whats_news),
                contentDescription = null,
                )
        Spacer(modifier = Modifier.height(16.dp))

        SearchBar(
            modifier = Modifier
                .padding(top=4.dp,end=4.dp, start = 4.dp),
            text = "",
            readOnly = true,
            onValueChange = {},
            onClick = {
             navigateToSearch()
            },
            onSearch = {}
        )
        Spacer(modifier =Modifier.padding(24.dp))
        
        Text(
            text =titles ,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp)
                .basicMarquee(),
            fontSize = 12.sp,
            color = Color(0xFFA0A3BD)
        )
        Spacer(modifier = Modifier.height(24.dp))
        ArticlesList(

            articles = articles,
            onClick = { navigateToDetails(it) }
        )

    }
}