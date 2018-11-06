package com.gmail.sasha.myproject.service.service.impl.upload;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "itemType", propOrder = {
        "name",
        "description",
        "uniqueNumber",
        "price",
        "available"
})
public class ItemType {

    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected String description;
    @XmlElement(required = true)
    protected String uniqueNumber;
    @XmlJavaTypeAdapter(BigDecimalAdaptor.class)
    // @XmlElement(required = true)
    protected BigDecimal price;
    @XmlElement(required = true)
    protected Boolean available;


    public String getName() {
        return name;
    }


    public void setName(String value) {
        this.name = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String value) {
        this.description = value;
    }

    public String getUniqueNumber() {
        return uniqueNumber;
    }

    public void setUniqueNumber(String value) {
        this.uniqueNumber = value;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal value) {
        this.price = value;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean value) {
        this.available = value;
    }

}
