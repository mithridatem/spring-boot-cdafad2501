package com.adrar.cdafad.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Year;
public class PastOrPresentYearValidator implements ConstraintValidator<PastOrPresentYear, Date> {
    private int maxYear;
    private int minYear;

    @Override
    public void initialize(PastOrPresentYear constraintAnnotation) {
        this.minYear = constraintAnnotation.min();
        this.maxYear = Year.now().getValue();
    }

    @Override
    public boolean isValid(Date date, ConstraintValidatorContext context) {
        if (date == null) {
            return false;
        }
        LocalDate localDate = date.toLocalDate();
        int year = localDate.getYear();
        return !localDate.isAfter(LocalDate.now()) && year >= minYear && year <= maxYear;
    }
}

