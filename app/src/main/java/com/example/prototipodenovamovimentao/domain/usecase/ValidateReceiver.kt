package com.example.prototipodenovamovimentao.domain.usecase

import androidx.core.text.isDigitsOnly

class ValidateReceiver {

    fun execute(receiverName: String?): ValidationResult {
        if (receiverName.isNullOrBlank()){
            return ValidationResult(
                successful = false,
                errorMessage = "Escreva o nome  de quem recebeu"
            )
        }
        val nameContainsNumbers = receiverName.any { it.isDigit() }
        if (nameContainsNumbers){
            return ValidationResult(
                successful = false,
                errorMessage = "escreva um nome valido"
            )
        }
        return ValidationResult(
            successful = true
        )
   }

}