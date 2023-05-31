package com.cj3dreams.fetchchicago.repo

import com.cj3dreams.fetchchicago.model.FetchListModel
import com.cj3dreams.fetchchicago.source.local.LocalSource
import com.cj3dreams.fetchchicago.source.remote.RemoteResult
import com.cj3dreams.fetchchicago.source.remote.RemoteSource
import kotlinx.coroutines.flow.Flow

class DataRepositoryImpl(
    private val remoteSource: RemoteSource,
    private val localSource: LocalSource): DataRepository {

    override suspend fun getFetchListFromRemote(): RemoteResult<List<FetchListModel>> =
        remoteSource.getFetchList()

    override fun getFetchListFromLocal(): Flow<List<FetchListModel>> = localSource.getFetchList()
    override suspend fun insertFetchListToLocal(list: List<FetchListModel>) =
        localSource.insertFetchList(list)

}