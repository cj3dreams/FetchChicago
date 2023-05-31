package com.cj3dreams.fetchchicago.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cj3dreams.fetchchicago.model.FetchListModel
import com.cj3dreams.fetchchicago.repo.DataRepositoryImpl
import com.cj3dreams.fetchchicago.source.remote.RemoteResult
import com.cj3dreams.fetchchicago.usecase.GetFetchListFromLocalUseCase
import com.cj3dreams.fetchchicago.usecase.GetFetchListFromRemoteUseCase
import com.cj3dreams.fetchchicago.usecase.InsertFetchListToLocalUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SortedLocalViewModel(private val dataRepository: DataRepositoryImpl): ViewModel() {

    val fetchListLiveDataLocal = MutableLiveData<List<FetchListModel>?>()

    private val getFetchListFromRemoteUseCase get() = GetFetchListFromRemoteUseCase(dataRepository)
    private val insertFetchListToLocalUseCase get() = InsertFetchListToLocalUseCase(dataRepository)
    private val getFetchListFromLocalUseCase get() = GetFetchListFromLocalUseCase(dataRepository)

    fun getFetchListFromRemoteAndInsertToLocal() = viewModelScope.launch(Dispatchers.IO){
        when(val remoteResult = getFetchListFromRemoteUseCase.invoke()){

            is RemoteResult.Success -> {
                val response = remoteResult.data
                insertFetchListToLocalUseCase.invoke(response)
            }
            is RemoteResult.Error -> {
                fetchListLiveDataLocal.postValue(null)
                getFetchListFromLocal()
            }
        }
    }
    fun getFetchListFromLocal() = viewModelScope.launch(Dispatchers.IO) {
        getFetchListFromLocalUseCase.invoke().collect{
            if (it.isNotEmpty()) fetchListLiveDataLocal.postValue(it)
            else if (it.isEmpty()) fetchListLiveDataLocal.postValue(emptyList())
            else fetchListLiveDataLocal.postValue(null)
        }
    }
}