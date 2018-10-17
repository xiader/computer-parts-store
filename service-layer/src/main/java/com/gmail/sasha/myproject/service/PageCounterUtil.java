package com.gmail.sasha.myproject.service;

import org.springframework.stereotype.Service;

@Service
public class PageCounterUtil {


    public Long countPages(Long amountOfRows, Long quantity) {
        return amountOfRows % quantity != 0 ? (amountOfRows / quantity + 1) : (amountOfRows / quantity);
    }
}
