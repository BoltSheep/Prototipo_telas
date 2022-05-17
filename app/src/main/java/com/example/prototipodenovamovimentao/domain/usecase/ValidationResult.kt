package com.example.prototipodenovamovimentao.domain.usecase

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)
