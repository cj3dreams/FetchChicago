package com.cj3dreams.fetchchicago.di

import android.app.Application
import androidx.room.Room
import com.cj3dreams.fetchchicago.repo.DataRepositoryImpl
import com.cj3dreams.fetchchicago.source.local.AppDb
import com.cj3dreams.fetchchicago.source.local.FetchListDao
import com.cj3dreams.fetchchicago.source.local.LocalSourceImpl
import com.cj3dreams.fetchchicago.source.remote.GetFetchListApi
import com.cj3dreams.fetchchicago.source.remote.RemoteSourceImpl
import com.cj3dreams.fetchchicago.utils.AppConstants.BASE_URL
import com.cj3dreams.fetchchicago.vm.SortedLocalViewModel
import com.cj3dreams.fetchchicago.vm.SortedRestViewModel
import com.cj3dreams.fetchchicago.vm.UnsortedViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
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

val dataSourceModule = module {

    fun provideDatabase(app: Application) =
        Room.databaseBuilder(app,AppDb::class.java, "fetchDatabase")
            .fallbackToDestructiveMigration()
            .build()

    fun provideDao(database: AppDb) = database.fetchListDao()

    fun provideDataRepository(api: GetFetchListApi, dao: FetchListDao) =
        DataRepositoryImpl(RemoteSourceImpl(api), LocalSourceImpl(dao))

    single { provideDatabase(androidApplication()) }
    single { provideDao(get()) }
    single { provideDataRepository(get(), get()) }

    viewModel {
        SortedRestViewModel(get())
    }
}
val sortedLocalViewModel = module {
    viewModel {
        SortedLocalViewModel(get())
    }
}
val unsortedViewModel = module {
    viewModel {
        UnsortedViewModel(get())
    }
}