package com.cj3dreams.fetchchicago.source.local

import com.cj3dreams.fetchchicago.model.FetchListModel
import kotlinx.coroutines.flow.Flow

interface LocalSource {
    fun getFetchList(): Flow<List<FetchListModel>>
    suspend fun insertFetchList(list: List<FetchListModel>)
}