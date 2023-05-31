package com.cj3dreams.fetchchicago.usecase

import com.cj3dreams.fetchchicago.model.FetchListModel
import com.cj3dreams.fetchchicago.repo.DataRepositoryImpl

class InsertFetchListToLocalUseCase(private val dataRepository: DataRepositoryImpl) {
    suspend operator fun invoke(list: List<FetchListModel>) =
        dataRepository.insertFetchListToLocal(list)
}