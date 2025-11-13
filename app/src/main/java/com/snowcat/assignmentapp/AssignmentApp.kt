package com.snowcat.assignmentapp

import android.app.Application
import com.snowcat.domain.GetScratchCardUseCase
import com.snowcat.domain.ResetScratchCardUseCase
import com.snowcat.domain.dto.ValidationStateDto
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * Application class for the Assignment App.
 */
@HiltAndroidApp
class AssignmentApp : Application() {

    @Inject
    lateinit var resetScratchCardUseCase: ResetScratchCardUseCase

    @Inject
    lateinit var getScratchCardUseCase: GetScratchCardUseCase

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        CoroutineScope(Dispatchers.Unconfined).launch {
            val card = getScratchCardUseCase.getScratchCard().first()
            if (card?.valid == ValidationStateDto.VALID || card == null) {
                resetScratchCardUseCase.resetScratchCard()
            }
        }
    }
}
