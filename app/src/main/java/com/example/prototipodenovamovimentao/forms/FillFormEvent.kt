package com.example.prototipodenovamovimentao.forms

sealed class FillFormEvent {
    data class OriginChanged(val origin: String): FillFormEvent()
    data class DestinationChanged(val destination: String): FillFormEvent()
    data class KMChanged(val km: String): FillFormEvent()
    data class GasChanged(val gas: Int): FillFormEvent()
    data class FinalKMChanged(val finalKM: String, val initialKM: String): FillFormEvent()
    data class FinalGasChanged(val finalGas: Int): FillFormEvent()
    data class WhoReceived(val receiver: String): FillFormEvent()
    data class WhatHappened(val occur: String): FillFormEvent()
    data class StatusConclusionChanged(val isAllRight: Boolean): FillFormEvent()

    object Continue: FillFormEvent()
    object End: FillFormEvent()
}
