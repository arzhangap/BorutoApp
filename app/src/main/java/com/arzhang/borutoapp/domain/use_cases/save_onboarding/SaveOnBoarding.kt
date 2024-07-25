package com.arzhang.borutoapp.domain.use_cases.save_onboarding

import com.arzhang.borutoapp.data.repository.Repository

// we create this to call function implicitly without need to call it directly
class SaveOnBoarding(
    private val repository: Repository
) {
    suspend operator fun invoke(completed: Boolean) {
       repository.saveOnBoardingState(completed = completed)
    }
}