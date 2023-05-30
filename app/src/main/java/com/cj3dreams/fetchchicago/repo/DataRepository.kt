package com.cj3dreams.fetchchicago.repo

import com.cj3dreams.fetchchicago.model.response.FetchListResponseItem
import com.cj3dreams.fetchchicago.source.remote.RemoteResult

interface DataRepository {
    suspend fun getFetchListFromRemote(): RemoteResult<List<FetchListResponseItem>>
}