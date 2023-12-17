package com.example.whatsnews.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.whatsnews.domain.model.Article

@Composable
fun ArticlesList(
    modifier: Modifier = Modifier,
    articles: LazyPagingItems<Article>,
    onClick:(Article)->Unit
){
    val handlePagingResult = handlePagingResult(articles = articles )
    if(handlePagingResult){
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(24.dp),
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier
            .fillMaxSize()
            ){
            items(count=articles.itemCount){
                articles[it]?.let{
                    ArticleCard(article = it) {
                        onClick(it)
                    }
                }
            }
        }
    }
}
@Composable
fun handlePagingResult(
    articles: LazyPagingItems<Article>
):Boolean{
    val loadState = articles.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }
    return when{
        loadState.refresh is LoadState.Loading ->{
            ShimmerEffect()
            false
        }
        error!=null ->{
            EmptyScreen()
            false
        }
        else ->{
            true
        }
    }

}
@Composable
fun ShimmerEffect(){
    Column (verticalArrangement = Arrangement.spacedBy(24.dp)) {
        repeat(10){
            ArticleCardShimmerEffect(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
            )
        }
    }
}