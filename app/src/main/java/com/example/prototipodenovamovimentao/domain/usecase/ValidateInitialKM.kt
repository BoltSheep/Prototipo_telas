package com.example.prototipodenovamovimentao.domain.usecase

import androidx.core.text.isDigitsOnly

class ValidateInitialKM {

    fun execute(initialKM: String): ValidationResult {
        if (initialKM.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Quilometragem vazia"
            )
        }
        if (!initialKM.isDigitsOnly()){
            return ValidationResult(
                successful = false,
                errorMessage = "nao é um valor valido"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}