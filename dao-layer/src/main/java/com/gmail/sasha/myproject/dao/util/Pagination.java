package com.gmail.sasha.myproject.dao.util;

public interface Pagination {

    int calculateOffset(int page, int limit);
}
