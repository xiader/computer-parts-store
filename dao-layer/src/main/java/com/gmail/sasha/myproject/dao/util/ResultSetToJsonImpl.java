package com.gmail.sasha.myproject.dao.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import javax.persistence.TupleElement;
import java.util.ArrayList;
import java.util.List;

@Service
public class ResultSetToJsonImpl implements ResultSetToJson {
    public List<ObjectNode> toJson(List<Tuple> results) {

        List<ObjectNode> json = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();

        for (Tuple t : results) {
            List<TupleElement<?>> cols = t.getElements();

            ObjectNode one = mapper.createObjectNode();

            for (TupleElement col : cols) {
               if(t.get(col.getAlias())!= null ) {
                    one.put(col.getAlias(), t.get(col.getAlias()).toString());
               }
                }

            json.add(one);
        }

        return json;
    }
}
