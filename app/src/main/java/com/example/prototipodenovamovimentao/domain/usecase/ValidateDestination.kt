package com.example.prototipodenovamovimentao.domain.usecase


class ValidateDestination {

    fun execute(destination: String): ValidationResult {
        if (destination.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "destino vazio"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}