package com.example.githubpulls.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.githubpulls.models.Pull
import com.example.githubpulls.repositories.PullRepository
import kotlinx.coroutines.flow.Flow

class MainViewModel(private val pullRepository: PullRepository) : ViewModel() {

    fun getPullList(): Flow<PagingData<Pull>> {
        return pullRepository.getPulls()
            .cachedIn(viewModelScope)
    }
}

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val pullRepository: PullRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(pullRepository) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}