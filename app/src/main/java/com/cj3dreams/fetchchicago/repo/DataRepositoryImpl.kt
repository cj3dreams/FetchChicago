package com.cj3dreams.fetchchicago.repo

import com.cj3dreams.fetchchicago.model.response.FetchListResponseItem
import com.cj3dreams.fetchchicago.source.remote.RemoteResult
import com.cj3dreams.fetchchicago.source.remote.RemoteSource

class DataRepositoryImpl(
    private val remoteSource: RemoteSource

    ):DataRepository {

    override suspend fun getFetchListFromRemote(): RemoteResult<List<FetchListResponseItem>> =
        remoteSource.getFetchList()

}