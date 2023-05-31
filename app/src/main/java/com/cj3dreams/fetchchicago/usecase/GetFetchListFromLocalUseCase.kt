package com.cj3dreams.fetchchicago.usecase

import com.cj3dreams.fetchchicago.repo.DataRepositoryImpl

class GetFetchListFromLocalUseCase(private val dataRepository: DataRepositoryImpl) {
    operator fun invoke() = dataRepository.getFetchListFromLocal()
}