package com.example.prototipodenovamovimentao.domain.usecase

import androidx.core.text.isDigitsOnly

class ValidateInitialGas {

    fun execute(initialGas: Int?): ValidationResult {
        if (initialGas != null) {
            if (initialGas < 0) {
                return ValidationResult(
                    successful = false,
                    errorMessage = "Valor de combustivel errado"
                )
            }
            return ValidationResult(
                successful = true
            )
        } else {
            return ValidationResult(
                successful = false,
                errorMessage = "Escolha um valor de Combustivel na regua"
            )
        }


    }
}