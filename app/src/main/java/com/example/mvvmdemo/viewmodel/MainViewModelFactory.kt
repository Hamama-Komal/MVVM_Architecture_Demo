package com.example.mvvmdemo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmdemo.repository.QuoteRepository

class MainViewModelFactory(private  val quoteRepository: QuoteRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(quoteRepository) as T
    }
}