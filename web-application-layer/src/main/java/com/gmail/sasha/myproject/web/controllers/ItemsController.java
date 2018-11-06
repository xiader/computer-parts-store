package com.gmail.sasha.myproject.web.controllers;


import com.gmail.sasha.myproject.service.model.ItemDTO;
import com.gmail.sasha.myproject.service.service.ItemService;
import com.gmail.sasha.myproject.service.service.OrderService;
import com.gmail.sasha.myproject.web.util.PageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequestMapping("/items")
public class ItemsController {

    @Autowired
    private PageProperties pageProperties;
    @Autowired
    private ItemService itemService;
    @Autowired
    private OrderService orderService;

    @GetMapping
    @PreAuthorize("hasAuthority('VIEW_ITEMS')")
    public String getItems(
            ModelMap modelMap,
            @RequestParam(value = "page", defaultValue = "1") Integer page
    ) {
        Long elementsOnPage = pageProperties.getElementsOnPage();
        List<ItemDTO> items = itemService
                .getAvailableItems(page, Math.toIntExact(elementsOnPage));

        Long pages = itemService.countAvailableItems(elementsOnPage);
        modelMap.addAttribute("items", items);
        modelMap.addAttribute("pages", pages);
        return pageProperties.getItemsPagePath();
    }














   /* @GetMapping(value = "/{id}/order")
    @PreAuthorize("hasAuthority('CREATE_ORDER')")
    public String createOrder(
            @PathVariable("id") String id
    ) {
        orderService.save(Long.valueOf(id));
        return "redirect:/items";
    }*/
}
