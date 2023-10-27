package br.com.hotel_senior.cadastro_hospedes.application.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CurrentOrFutureValidator.class)
@Documented
public @interface CurrentOrFutureDate {
    String message() default "A data de entrada deve estar no presente ou no futuro.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
