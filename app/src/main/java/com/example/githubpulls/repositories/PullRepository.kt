package com.example.githubpulls.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.githubpulls.models.Pull
import com.example.githubpulls.paging.PullPagingSource
import kotlinx.coroutines.flow.Flow

class PullRepository {

    fun getPulls(): Flow<PagingData<Pull>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PullPagingSource() }
        ).flow
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 30
    }
}