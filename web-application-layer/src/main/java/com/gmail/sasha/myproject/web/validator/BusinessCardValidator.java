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
        ValidationUtils.rejectIfEmpty(errors, "title", "businessCard.title.empty");
        ValidationUtils.rejectIfEmpty(errors, "fullName", "businessCard.fullName.empty");
        ValidationUtils.rejectIfEmpty(errors, "workingPhone", "businessCard.workingPhone.empty");
        BusinessCardDTO businessCard = (BusinessCardDTO) object;

        if (businessCard.getTitle().length() > 40) {
            errors.rejectValue("title", "businessCard.title.wrong.length");
        }
        if (businessCard.getFullName().length() > 40) {
            errors.rejectValue("fullName", "businessCard.fullName.wrong.length");
        }
        if (businessCard.getTitle().length() > 20) {
            errors.rejectValue("workingPhone", "businessCard.workingPhone.wrong.length");
        }
    }
}