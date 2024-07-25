package com.arzhang.borutoapp.domain.use_cases.read_onboarding

import com.arzhang.borutoapp.data.repository.Repository
import kotlinx.coroutines.flow.Flow

class ReadOnBoarding(
    private val repository: Repository
) {
    operator fun invoke() : Flow<Boolean> {
        return repository.readOnBoardingState()
    }
}