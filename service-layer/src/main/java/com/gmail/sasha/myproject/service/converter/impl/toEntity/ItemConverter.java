package com.gmail.sasha.myproject.service.converter.impl.toEntity;

import com.gmail.sasha.myproject.dao.model.Discount;
import com.gmail.sasha.myproject.dao.model.Item;
import com.gmail.sasha.myproject.dao.model.Order;
import com.gmail.sasha.myproject.service.converter.EntityConverter;
import com.gmail.sasha.myproject.service.model.DiscountDTO;
import com.gmail.sasha.myproject.service.model.ItemDTO;
import com.gmail.sasha.myproject.service.model.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("itemEntityConverter")
public class ItemConverter implements EntityConverter<ItemDTO, Item> {


    @Autowired
    @Qualifier("orderEntityConverter")
    private EntityConverter<OrderDTO, Order> orderDtoToEntity;

    @Autowired
    @Qualifier("discountEntityConverter")
    private EntityConverter<DiscountDTO, Discount> discountEntityConverter;

    @Override
    public Item toEntity(ItemDTO dto) {
        if (dto == null) {
            return null;
        }
        Item item = new Item();
        item.setPrice(dto.getPrice());
        item.setName(dto.getName());
        item.setDescription(dto.getDescription());
        item.setUniqueNumber(dto.getUniqueNumber());
        item.setOrders(orderDtoToEntity.toEntityList(dto.getOrderDTOS()));
        item.setDiscounts(discountEntityConverter.toEntitySet(dto.getDiscounts()));
        return item;
    }

}
