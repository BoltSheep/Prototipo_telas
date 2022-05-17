package com.example.prototipodenovamovimentao.domain.usecase


class ValidateOrigin {

    fun execute(initialKM: String): ValidationResult {
        if (initialKM.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Quilometragem vazia"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}