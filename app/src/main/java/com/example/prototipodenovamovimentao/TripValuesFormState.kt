package com.example.prototipodenovamovimentao

data class TripValuesFormState(
    val moveType: Int? = null,
    val destination: String = "",
    val destinationError: String? = null,
    val origin: String = "",
    val originError: String? = null,
    val initialKM: String = "",
    val initialKMError: String? = null,
    val initialGas: Int? = null,
    val initialGasError: String? = null,
    val finalKM: String = "",
    val finalKMError: String? = null,
    val finalGas: Int = 0,
    val finalGasError: String? = null,
    val statusConclusion: Boolean? = null,
    val statusConclusionError: String? = null,
    val registeredOccur: String = "",
    val registeredOccurError: String? = null,
    val receiverName: String = "",
    val receiverNameError: String? = null
)
