package com.gmail.sasha.dao.services.converter;

import java.util.List;

public interface DTOConverter<D, E> {

    D toDTO(E entity);

    List<D> toDTOList(List<E> list);
}
