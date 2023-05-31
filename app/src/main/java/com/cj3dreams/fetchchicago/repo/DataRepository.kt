package com.cj3dreams.fetchchicago.repo

import com.cj3dreams.fetchchicago.model.FetchListModel
import com.cj3dreams.fetchchicago.source.remote.RemoteResult
import kotlinx.coroutines.flow.Flow

interface DataRepository {
    suspend fun getFetchListFromRemote(): RemoteResult<List<FetchListModel>>

    fun getFetchListFromLocal(): Flow<List<FetchListModel>>
    suspend fun insertFetchListToLocal(list: List<FetchListModel>)
}