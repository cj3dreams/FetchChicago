package com.cj3dreams.fetchchicago.source.local

import com.cj3dreams.fetchchicago.model.FetchListModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class LocalSourceImpl(private val dao: FetchListDao): LocalSource {

    override fun getFetchList(): Flow<List<FetchListModel>> = dao.getFetchList()

    override suspend fun insertFetchList(list: List<FetchListModel>) =
        withContext(Dispatchers.IO){
            dao.insertFetchList(list)
        }
}