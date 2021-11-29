package com.example.githubpulls

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.githubpulls.api.PullApi
import com.example.githubpulls.repositories.PullRepository
import com.example.githubpulls.viewModels.MainViewModel
import com.example.githubpulls.viewModels.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    companion object {
        const val USER = "mozilla-mobile"
        const val REPO = "fenix"
    }

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory(PullRepository(PullApi.retrofitService))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel.loadPullList(USER, REPO)
    }
}