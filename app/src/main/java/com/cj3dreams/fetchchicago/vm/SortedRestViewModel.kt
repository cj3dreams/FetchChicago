package com.cj3dreams.fetchchicago.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cj3dreams.fetchchicago.model.response.FetchListResponseItem
import com.cj3dreams.fetchchicago.repo.DataRepositoryImpl
import com.cj3dreams.fetchchicago.source.remote.RemoteResult
import com.cj3dreams.fetchchicago.usecase.GetFetchListFromRemoteUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SortedRestViewModel(private val dataRepository: DataRepositoryImpl): ViewModel() {
    val fetchListLiveData = MutableLiveData<List<FetchListResponseItem>?>()

    private val getFetchListFromRemoteUseCase get() = GetFetchListFromRemoteUseCase(dataRepository)

    fun getFetchListFromRemote() = viewModelScope.launch(Dispatchers.IO){

        when(val remoteResult = getFetchListFromRemoteUseCase.invoke()){

            is RemoteResult.Success -> {
                val response = remoteResult.data
                fetchListLiveData.postValue(response)
            }
            is RemoteResult.Error -> fetchListLiveData.postValue(null)
        }
    }
}