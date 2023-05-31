package com.cj3dreams.fetchchicago.source.remote

import com.cj3dreams.fetchchicago.model.FetchListModel
import retrofit2.Response
import retrofit2.http.GET

interface GetFetchListApi {

    @GET("hiring.json")
    suspend fun getFetchList(): Response<List<FetchListModel>>
}