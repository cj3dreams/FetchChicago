package com.cj3dreams.fetchchicago.usecase

import com.cj3dreams.fetchchicago.repo.DataRepository

class GetFetchListFromRemoteUseCase(private val dataRepository: DataRepository) {
    suspend operator fun invoke() = dataRepository.getFetchListFromRemote()
}
