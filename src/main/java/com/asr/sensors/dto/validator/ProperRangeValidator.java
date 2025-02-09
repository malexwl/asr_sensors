package com.asr.sensors.dto.validator;

import com.asr.sensors.dto.model.RangeDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ProperRangeValidator implements ConstraintValidator<ProperRange, RangeDto> {
    @Override
    public boolean isValid(RangeDto rangeDto, ConstraintValidatorContext constraintValidatorContext) {
        return  rangeDto.getTo() >= rangeDto.getFrom();
    }
}
