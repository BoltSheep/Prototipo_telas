package com.example.prototipodenovamovimentao.domain.usecase

import androidx.core.text.isDigitsOnly

class ValidateFinalKM {

    fun execute(finalKM: String, initialKM: String): ValidationResult {
        if (finalKM.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Quilometragem vazia"
            )
        }
        if (finalKM == initialKM) {
            return ValidationResult(
                successful = false,
                errorMessage = "valor é o mesmo do inicio"
            )
        }
        if (!finalKM.isDigitsOnly()){
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