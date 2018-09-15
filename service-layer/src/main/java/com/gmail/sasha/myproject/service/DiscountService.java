package com.gmail.sasha.myproject.service;



import com.gmail.sasha.myproject.model.DiscountDTO;
import com.gmail.sasha.myproject.model.ItemDTO;

import java.util.List;

public interface DiscountService {
    List<DiscountDTO> save(List<DiscountDTO> itemList);

    List<DiscountDTO> getAllItems();

}
