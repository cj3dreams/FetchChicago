package com.cj3dreams.fetchchicago.source.remote

import com.cj3dreams.fetchchicago.model.response.FetchListResponseItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteSourceImpl(private val api: GetFetchListApi):RemoteSource {

    override suspend fun getFetchList(): RemoteResult<List<FetchListResponseItem>> =
        withContext(Dispatchers.IO){
            try {
                val response = api.getFetchList()
                if (response.isSuccessful) return@withContext RemoteResult.Success(response.body()!!)
                else return@withContext RemoteResult.Error(Exception(response.message()))
            }catch (e:Exception){
                return@withContext RemoteResult.Error(e)
            }
        }
}