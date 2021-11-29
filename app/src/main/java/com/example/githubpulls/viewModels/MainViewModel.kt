package com.example.githubpulls.viewModels

import androidx.lifecycle.*
import com.example.githubpulls.models.Pull
import com.example.githubpulls.repositories.PullRepository
import kotlinx.coroutines.launch

class MainViewModel(private val pullRepository: PullRepository) : ViewModel() {

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

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val pullRepository: PullRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(pullRepository) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}