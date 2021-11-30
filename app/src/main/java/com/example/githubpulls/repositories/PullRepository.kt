package com.example.githubpulls.repositories

import com.example.githubpulls.api.PullApiService
import com.example.githubpulls.models.Pull
import javax.inject.Inject

class PullRepository @Inject constructor(
    private val pullService: PullApiService
) {
    suspend fun loadPulls(user: String, repo: String): List<Pull> {
        return pullService.getPulls(user, repo)
    }
}