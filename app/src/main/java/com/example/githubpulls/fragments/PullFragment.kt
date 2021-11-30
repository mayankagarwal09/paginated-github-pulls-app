package com.example.githubpulls.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.githubpulls.adapters.PullAdapter
import com.example.githubpulls.adapters.PullLoadStateAdapter
import com.example.githubpulls.databinding.FragmentPullBinding
import com.example.githubpulls.repositories.PullRepository
import com.example.githubpulls.viewModels.MainViewModel
import com.example.githubpulls.viewModels.MainViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class PullFragment : Fragment() {


    private lateinit var binding: FragmentPullBinding

    private val mainViewModel: MainViewModel by activityViewModels {
        MainViewModelFactory(PullRepository())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentPullBinding.inflate(layoutInflater, container, false)

        val pullAdapter = PullAdapter()
        binding.pullList.adapter = pullAdapter.withLoadStateHeaderAndFooter(
            header = PullLoadStateAdapter { pullAdapter.retry() },
            footer = PullLoadStateAdapter { pullAdapter.retry() }
        )

        viewLifecycleOwner.lifecycleScope.launch {
            mainViewModel.getPullList().collectLatest {
                pullAdapter.submitData(it)
            }
        }

        return binding.root
    }

}