package com.cj3dreams.fetchchicago.di

import com.cj3dreams.fetchchicago.source.remote.GetFetchListApi
import com.cj3dreams.fetchchicago.utils.AppConstants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    fun <Api> provideRetrofit(api: Class<Api>) =
        Retrofit.Builder().baseUrl(BASE_URL)
            .client(OkHttpClient.Builder().also { client->
                val logging = HttpLoggingInterceptor()
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                client.addInterceptor(logging)
            }.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)

    factory { provideRetrofit(GetFetchListApi::class.java) }
}