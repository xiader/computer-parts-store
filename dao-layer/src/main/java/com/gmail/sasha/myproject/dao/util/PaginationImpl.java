package com.gmail.sasha.myproject.dao.util;

import org.springframework.stereotype.Service;

@Service
public class PaginationImpl implements Pagination{
    @Override
    public int calculateOffset(int page, int limit) {
        return ((limit * page) - limit);
    }
}
