package com.gmail.sasha.myproject.converter.impl.toEntity;



import com.gmail.sasha.myproject.converter.EntityConverter;
import com.gmail.sasha.myproject.model.Discount;
import com.gmail.sasha.myproject.model.DiscountDTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DiscountConverter implements EntityConverter<DiscountDTO, Discount> {
    @Override
    public Discount toEntity(DiscountDTO dto) {
        Discount discount = new Discount();
        discount.setExpirationDate(dto.getExpirationDate());
        discount.setInterestRate(dto.getInterestRate());
        discount.setItems(dto.getItems());
        discount.setName(dto.getName());
        return discount;
    }



}
