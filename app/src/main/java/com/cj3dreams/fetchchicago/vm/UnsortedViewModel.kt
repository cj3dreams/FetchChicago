package com.cj3dreams.fetchchicago.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cj3dreams.fetchchicago.model.FetchListModel
import com.cj3dreams.fetchchicago.repo.DataRepositoryImpl
import com.cj3dreams.fetchchicago.source.remote.RemoteResult
import com.cj3dreams.fetchchicago.usecase.GetFetchListFromRemoteUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UnsortedViewModel(private val dataRepository: DataRepositoryImpl): ViewModel() {
    val fetchListLiveDataUnsorted = MutableLiveData<List<FetchListModel>?>()
    private val getFetchListFromRemoteUseCase get() = GetFetchListFromRemoteUseCase(dataRepository)

    fun getFetchListFromRemote() = viewModelScope.launch(Dispatchers.IO){

        when(val remoteResult = getFetchListFromRemoteUseCase.invoke()){

            is RemoteResult.Success -> {
                val response = remoteResult.data
                fetchListLiveDataUnsorted.postValue(response)
            }
            is RemoteResult.Error -> fetchListLiveDataUnsorted.postValue(null)
        }
    }
}