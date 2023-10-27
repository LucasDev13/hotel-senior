package br.com.hotel_senior.cadastro_hospedes.application.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

public class CurrentOrFutureValidator implements ConstraintValidator<CurrentOrFutureDate, LocalDateTime> {

    @Override
    public void initialize(CurrentOrFutureDate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(LocalDateTime localDateTime, ConstraintValidatorContext constraintValidatorContext) {
        return localDateTime == null || localDateTime.isBefore(LocalDateTime.now());
    }
}
