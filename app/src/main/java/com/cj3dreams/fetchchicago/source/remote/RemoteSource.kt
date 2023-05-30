package com.cj3dreams.fetchchicago.source.remote

import com.cj3dreams.fetchchicago.model.response.FetchListResponseItem

interface RemoteSource {
    suspend fun getFetchList(): RemoteResult<List<FetchListResponseItem>>
}