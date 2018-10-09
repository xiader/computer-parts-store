package com.gmail.sasha.myproject.dao.util;

import com.fasterxml.jackson.databind.node.ObjectNode;

import javax.persistence.Tuple;
import java.util.List;

public interface ResultSetToJson {
    List<ObjectNode> toJson(List<Tuple> results);
}
