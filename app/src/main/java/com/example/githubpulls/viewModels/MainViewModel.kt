package com.example.githubpulls.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubpulls.models.Pull
import com.example.githubpulls.repositories.PullRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val pullRepository: PullRepository
) : ViewModel() {

    private val _pullList = MutableLiveData<List<Pull>?>()

    val pullList: LiveData<List<Pull>?>
        get() = _pullList


    fun loadPullList(user: String, repo: String) {
        viewModelScope.launch {
            val response = pullRepository.loadPulls(user, repo)
            _pullList.value = response
        }
    }
}