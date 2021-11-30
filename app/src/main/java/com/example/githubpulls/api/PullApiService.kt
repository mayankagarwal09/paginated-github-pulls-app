package com.example.githubpulls.api

import com.example.githubpulls.models.Pull
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url


interface PullApiService {

    @GET("repos/{user}/{repo}/pulls?state=closed")
    suspend fun getPulls(
        @Path(value = "user", encoded = true) user: String,
        @Path(value = "repo", encoded = true) repo: String
    ): List<Pull>

    @GET
    suspend fun getPostsFromLink(@Url link: String): List<Pull>

}