package com.example.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.News
import com.example.domain.usecase.GetNewsUseCase
import com.example.domain.usecase.UpdateNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase,
    private val updateNewsUseCase: UpdateNewsUseCase
) : ViewModel() {

    private val _isException = MutableLiveData<Boolean>()
    val isException: LiveData<Boolean>
        get() = _isException

    val handler = CoroutineExceptionHandler { _, _ ->
        _isException.value = true
    }

    private val _news = MutableLiveData<List<News>>()
    val news: LiveData<List<News>>
        get() = _news

    fun getNews() {
        viewModelScope.launch(handler) {
            _news.value = getNewsUseCase.getNews()
        }
    }

    fun updateNews() {
        viewModelScope.launch(handler) {
            _news.value = updateNewsUseCase.updateNews()
        }
    }
}