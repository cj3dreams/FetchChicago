package com.cj3dreams.fetchchicago.source.remote

import com.cj3dreams.fetchchicago.model.FetchListModel

interface RemoteSource {
    suspend fun getFetchList(): RemoteResult<List<FetchListModel>>
}