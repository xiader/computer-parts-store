package com.gmail.sasha.myproject.service.converter.impl.toEntity;


import com.gmail.sasha.myproject.dao.model.Discount;
import com.gmail.sasha.myproject.dao.model.Item;
import com.gmail.sasha.myproject.service.converter.EntityConverter;
import com.gmail.sasha.myproject.service.model.DiscountDTO;
import com.gmail.sasha.myproject.service.model.ItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("discountEntityConverter")
public class DiscountConverter implements EntityConverter<DiscountDTO, Discount> {

    @Autowired
    @Qualifier("itemEntityConverter")
    private EntityConverter<ItemDTO, Item> discountEntityConverter;

    @Override
    public Discount toEntity(DiscountDTO dto) {
        if (dto == null) {
            return null;
        }
        Discount discount = new Discount();
        discount.setExpirationDate(dto.getExpirationDate());
        discount.setInterestRate(dto.getInterestRate());
        discount.setItems(discountEntityConverter.toEntitySet(dto.getItems()));
        discount.setName(dto.getName());
        return discount;
    }


}
