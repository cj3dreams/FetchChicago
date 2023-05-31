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

    fun getFetchListFromRemoteAndSort() = viewModelScope.launch(Dispatchers.IO){

        when(val remoteResult = getFetchListFromRemoteUseCase.invoke()){

            is RemoteResult.Success -> {
                val response = remoteResult.data
                var sortedList = response.filter { !it.name.isNullOrBlank() }
                sortedList = sortedList.sortedWith(object : Comparator<FetchListResponseItem> {
                    override fun compare(o1: FetchListResponseItem, o2: FetchListResponseItem) =
                        extractInt(o1) - extractInt(o2)

                    fun extractInt(s: FetchListResponseItem): Int {
                        val num = s.name!!.replace("\\D".toRegex(), "")
                        return if (num.isEmpty()) 0 else Integer.parseInt(num)
                    }
                })
                sortedList = sortedList.sortedBy { it.listId }

                fetchListLiveData.postValue(sortedList)
            }
            is RemoteResult.Error -> fetchListLiveData.postValue(null)
        }
    }
}