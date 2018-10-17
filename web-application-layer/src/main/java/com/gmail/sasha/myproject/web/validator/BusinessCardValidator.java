package com.gmail.sasha.myproject.web.validator;

import com.gmail.sasha.myproject.service.model.BusinessCardDTO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.validation.constraints.NotNull;

@Component("businessCardValidator")
public class BusinessCardValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return BusinessCardDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object object, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "title", "card.title.empty");
        ValidationUtils.rejectIfEmpty(errors, "fullName", "card.name.empty");
        ValidationUtils.rejectIfEmpty(errors, "workingPhone", "card.phone.empty");
        BusinessCardDTO businessCard = (BusinessCardDTO) object;

        if (businessCard.getTitle().length() > 50) {
            errors.rejectValue("title", "card.title.length.wrong");
        }
        if (businessCard.getFullName().length() > 50) {
            errors.rejectValue("fullName", "card.name.length.wrong");
        }
        if (businessCard.getWorkingPhone().length() > 20) {
            errors.rejectValue("workingPhone", "card.phone.length.wrong");
        }
    }
}