package com.example.prototipodenovamovimentao.domain.usecase

class ValidateFinalGas {

    fun execute(finalGas: Int?): ValidationResult {
        if (finalGas != null) {
            if (finalGas < 0) {
                return ValidationResult(
                    successful = false,
                    errorMessage = "Selecione um valor valido"
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