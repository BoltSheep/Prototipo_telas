package com.example.prototipodenovamovimentao.domain.usecase

class ValidateWhatHappened {

    fun execute(occurText: String?): ValidationResult {
        return if (occurText.isNullOrBlank()){
            ValidationResult(
                successful = false,
                errorMessage = "Por Favor no conte o Ocorrido"
            )
        } else {
            ValidationResult(
                successful = true
            )
        }
    }

}