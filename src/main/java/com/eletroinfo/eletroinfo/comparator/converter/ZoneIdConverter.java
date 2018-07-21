package com.eletroinfo.eletroinfo.comparator.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.ZoneId;

@Converter(autoApply = true)
public class ZoneIdConverter implements AttributeConverter<ZoneId, String> {

    @Override
    public String convertToDatabaseColumn(ZoneId obj) {
        if(obj == null)
            return null;
        return obj.toString();
    }

    @Override
    public ZoneId convertToEntityAttribute(String obj) {
        if (obj == null){
            return null;
        }

        try {
            return  ZoneId.of(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
