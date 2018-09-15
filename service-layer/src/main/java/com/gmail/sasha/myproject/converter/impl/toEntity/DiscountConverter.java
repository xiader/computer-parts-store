package com.gmail.sasha.myproject.converter.impl.toEntity;


import com.gmail.sasha.myproject.converter.EntityConverter;
import com.gmail.sasha.myproject.model.Discount;
import com.gmail.sasha.myproject.model.DiscountDTO;
import com.gmail.sasha.myproject.model.Item;
import com.gmail.sasha.myproject.model.ItemDTO;


public class DiscountConverter implements EntityConverter<DiscountDTO, Discount> {
    @Override
    public Discount toEntity(DiscountDTO dto) {
        if (dto == null) {
            return null;
        }
        EntityConverter<ItemDTO, Item> discountEntityConverter = new ItemConverter();
        Discount discount = new Discount();
        discount.setExpirationDate(dto.getExpirationDate());
        discount.setInterestRate(dto.getInterestRate());
        discount.setItems(discountEntityConverter.toEntitySet(dto.getItems()));
        discount.setName(dto.getName());
        return discount;
    }


}
