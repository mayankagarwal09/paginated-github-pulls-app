package com.example.githubpulls.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.githubpulls.models.Pull
import com.example.githubpulls.repositories.PullRepository
import kotlinx.coroutines.flow.Flow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val pullRepository: PullRepository
) : ViewModel() {

    fun getPullList(): Flow<PagingData<Pull>> {
        return pullRepository.getPulls()
            .cachedIn(viewModelScope)
    }
}