package com.gmail.sasha.myproject.service.converter.impl.toEntity;

import com.gmail.sasha.myproject.dao.model.News;
import com.gmail.sasha.myproject.service.converter.EntityConverter;
import com.gmail.sasha.myproject.service.model.NewsDTO;
import org.springframework.stereotype.Service;

@Service("newsEntityConverter")
public class NewsConverter implements EntityConverter<NewsDTO, News> {
    @Override
    public News toEntity(NewsDTO dto) {
        return null;
    }
}
