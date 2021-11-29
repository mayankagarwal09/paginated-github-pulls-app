package com.example.githubpulls.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.githubpulls.adapters.PullAdapter
import com.example.githubpulls.databinding.FragmentPullBinding
import com.example.githubpulls.viewModels.MainViewModel


class PullFragment : Fragment() {


    private lateinit var binding: FragmentPullBinding

    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentPullBinding.inflate(layoutInflater, container, false)

        val adapter = PullAdapter()
        binding.pullList.adapter = adapter

        mainViewModel.pullList.observe(viewLifecycleOwner, {
            it?.let { adapter.submitList(it) }
        })

        return binding.root
    }

}