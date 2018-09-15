package com.gmail.sasha.dao.services.converter;

import java.util.List;
import java.util.Set;

public interface EntityConverter<D, E> {

    E toEntity(D dto);

    List<E> toEntityList(List<D> list);

    Set<E> toEntityList(Set<D> list);
}
