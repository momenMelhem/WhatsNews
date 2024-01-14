package com.example.whatsnews.presentation.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whatsnews.domain.model.Article
import com.example.whatsnews.domain.usecases.NewsUseCases
import com.example.whatsnews.domain.usecases.UpsertArticle
import com.example.whatsnews.util.UIComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
   private val newsUseCases: NewsUseCases
) : ViewModel() {
    var sideEffect by mutableStateOf<UIComponent?>(null)
        private set

    fun onEvent(event: DetailsEvent){
        when(event){
            is DetailsEvent.UpsertDeleteArticle -> {
                viewModelScope.launch {
                    val article = newsUseCases.selectArticle(url =  event.article.url)
                    if (article==null){
                        upsertArticle (article = event.article)
                    }
                    else
                    {
                        deleteArticle(article= event.article)
                    }
                }
            }
            is DetailsEvent.RemoveSideEffect -> {
                sideEffect = null
            }
        }
    }

    private suspend fun deleteArticle(article: Article) {
        newsUseCases.deleteArticle(article=article)
        sideEffect = UIComponent.Toast("Article Deleted")
    }

    private suspend fun upsertArticle(article: Article) {
        newsUseCases.upsertArticle(article=article)
        sideEffect = UIComponent.Toast("Article Saved")
    }
}