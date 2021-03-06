package com.example.prototipodenovamovimentao.domain.usecase


class ValidateOrigin {

    fun execute(origin: String): ValidationResult {
        if (origin.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "origem vazia"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}