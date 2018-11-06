package com.gmail.sasha.myproject.service.service.impl.upload;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bodyType", propOrder = {
        "item"
})
@XmlRootElement(name = "body")
public class BodyType {

    protected List<ItemType> item;

    public List<ItemType> getItem() {
        if (item == null) {
            item = new ArrayList<>();
        }
        return this.item;
    }

}
