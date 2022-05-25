package com.example.prototipodenovamovimentao.models

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prototipodenovamovimentao.forms.FillFormEvent
import com.example.prototipodenovamovimentao.TripValuesFormState
import com.example.prototipodenovamovimentao.domain.usecase.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class TripViewModel(
    private val validateInitialGas: ValidateInitialGas = ValidateInitialGas(),
    private val validateInitialKM: ValidateInitialKM = ValidateInitialKM(),
    private val validateOrigin: ValidateOrigin = ValidateOrigin(),
    private val validateDestination: ValidateDestination = ValidateDestination(),
    private val validateFinalGas: ValidateFinalGas = ValidateFinalGas(),
    private val validateFinalKM: ValidateFinalKM = ValidateFinalKM(),
    private val validateReceiver: ValidateReceiver = ValidateReceiver(),
    private val validateWhatHappened: ValidateWhatHappened = ValidateWhatHappened(),
    private val validateConclusionState: ValidateConclusionState = ValidateConclusionState()

): ViewModel() {

    var movementType: Int = 0
    var enableButton: Boolean = false
    var enableEndButton: Boolean = false

    var state by mutableStateOf(TripValuesFormState())

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(event: FillFormEvent) {
        when (event) {
            is FillFormEvent.OriginChanged -> {
                state = state.copy(origin = event.origin)
            }
            is FillFormEvent.DestinationChanged -> {
                state = state.copy(destination = event.destination)
            }
            is FillFormEvent.GasChanged -> {
                state = state.copy(initialGas = event.gas)
            }
            is FillFormEvent.KMChanged -> {
                state = state.copy(initialKM = event.km)
            }
            is FillFormEvent.StatusConclusionChanged -> {
                state = state.copy(statusConclusion = event.isAllRight)
            }
            is FillFormEvent.WhatHappened -> {
                state = state.copy(registeredOccur = event.occur)
            }
            is FillFormEvent.WhoReceived -> {
                state = state.copy(receiverName = event.receiver)
            }
            is FillFormEvent.FinalGasChanged -> {
                state = state.copy(finalGas = event.finalGas)
            }
            is FillFormEvent.FinalKMChanged -> {
                state = state.copy(finalKM = event.finalKM)
            }
            is FillFormEvent.Continue -> {
                continueTripFlux()
            }
            is FillFormEvent.End -> {
                endTripFlux()
            }

        }
    }

    fun continueTripFlux() {
        val originResult = validateOrigin.execute(state.origin)
        val destinationResult = validateDestination.execute(state.destination)
        val initialGasResult = validateInitialGas.execute(state.initialGas)
        val initialKMResult = validateInitialKM.execute(state.initialKM)

        val hasError = listOf(
            originResult,
            destinationResult,
            initialGasResult,
            initialKMResult
        ).any { !it.successful }

        Log.d("miojo", "continueTripFlux: $hasError,      $state")

        if (hasError) {
            state = state.copy(
                originError = originResult.errorMessage,
                destinationError = destinationResult.errorMessage,
                initialGasError = initialGasResult.errorMessage,
                initialKMError = initialKMResult.errorMessage
            )

            return
        } else {
            enableButton = true
        }

        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.Success)
        }

    }
    fun endTripFlux() {

        val occurResult = validateWhatHappened.execute(state.registeredOccur)
        val receiverNameResult = validateReceiver.execute(state.receiverName)
        val finalGasResult = validateFinalGas.execute(state.finalGas)
        val finalKMResult = validateFinalKM.execute(state.finalKM, state.initialKM)

        val hasError = listOf(
            receiverNameResult,
            finalGasResult,
            finalKMResult
        ).any { !it.successful }

        if (hasError) {
            state = state.copy(
                registeredOccurError = occurResult.errorMessage,
                receiverNameError = receiverNameResult.errorMessage,
                initialGasError = finalGasResult.errorMessage,
                initialKMError = finalKMResult.errorMessage
            )
            return
        } else {
            enableEndButton = true
        }
    }

    sealed class ValidationEvent {
        object Success: ValidationEvent()
    }

    fun clearState() {
        state.origin
        state.originError
        state.destination
        state.destinationError
        state.initialGas
        state.initialGasError
        state.initialKM
        state.finalKMError
        state.finalGas
        state.finalGasError
        state.finalKM
        state.finalKMError
        state.receiverName
        state.receiverNameError
    }


}