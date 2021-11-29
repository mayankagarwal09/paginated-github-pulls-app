package com.example.githubpulls.api

import com.example.githubpulls.models.Pull
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url
import java.util.*


val moshi: Moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
    .build()


private fun getLoggingInterceptor(): HttpLoggingInterceptor {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return httpLoggingInterceptor
}


val client = OkHttpClient.Builder()
    .addInterceptor(getLoggingInterceptor())
    .build()

val retrofit: Retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .client(client)
    .baseUrl(PullApi.BASE_URL)
    .build()

interface PullApiService {

    @GET("repos/{user}/{repo}/pulls?state=closed")
    suspend fun getPulls(
        @Path(value = "user", encoded = true) user: String,
        @Path(value = "repo", encoded = true) repo: String
    ): List<Pull>

    @GET
    suspend fun getPostsFromLink(@Url link: String): List<Pull>

}

object PullApi {
    const val BASE_URL = "https://api.github.com/"

    val retrofitService: PullApiService by lazy {
        retrofit.create(PullApiService::class.java)
    }
}