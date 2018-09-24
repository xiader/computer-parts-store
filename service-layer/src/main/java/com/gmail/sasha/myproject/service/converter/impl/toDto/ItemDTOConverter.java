package com.gmail.sasha.myproject.service.converter.impl.toDto;

import com.gmail.sasha.myproject.dao.model.Discount;
import com.gmail.sasha.myproject.dao.model.Item;
import com.gmail.sasha.myproject.dao.model.Order;
import com.gmail.sasha.myproject.service.converter.DTOConverter;
import com.gmail.sasha.myproject.service.model.DiscountDTO;
import com.gmail.sasha.myproject.service.model.ItemDTO;
import com.gmail.sasha.myproject.service.model.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("itemDTOConverter")
public class ItemDTOConverter implements DTOConverter<ItemDTO, Item> {

    @Autowired
    @Qualifier("orderDTOConverter")
    private DTOConverter<OrderDTO, Order> orderDTOConverter;

    @Autowired
    @Qualifier("discountDTOConverter")
    private DTOConverter<DiscountDTO, Discount> discountDTOConverter;

    @Override
    public ItemDTO toDTO(Item entity) {
        if (entity == null) {
            return null;
        }
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setDescription(entity.getDescription());
        itemDTO.setId(entity.getId());
        itemDTO.setName(entity.getName());
        itemDTO.setOrderDTOS(orderDTOConverter.toDTOList(entity.getOrders()));
        itemDTO.setPrice(entity.getPrice());
        itemDTO.setUniqueNumber(entity.getUniqueNumber());
        itemDTO.setDiscounts(discountDTOConverter.toDTOSet(entity.getDiscounts()));
        return itemDTO;
    }
}
