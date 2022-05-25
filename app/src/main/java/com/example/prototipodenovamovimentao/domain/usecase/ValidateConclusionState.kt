package com.example.prototipodenovamovimentao.domain.usecase

class ValidateConclusionState {
    fun execute(statusBoxChecked: Boolean?): ValidationResult {
        if (statusBoxChecked == null ) {
            return ValidationResult(
                successful = false,
                errorMessage = "Por favor selecione uma opção"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}