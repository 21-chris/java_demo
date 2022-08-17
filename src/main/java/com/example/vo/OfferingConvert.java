package com.example.vo;


import com.example.pojo.Attribute;
import com.example.pojo.Offering;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public abstract class OfferingConvert {

    public static OfferingConvert Instance = Mappers.getMapper(OfferingConvert.class);
    public abstract Offering abc(OfferingDto offeringDto);

    public abstract Attribute attributeConvert(OfferingDto offeringDto);

    public List dtoAfter (OfferingDto offeringDto){
        List<Attribute> attributeList =  offeringDto.getAttributeList();
        return attributeList;
    }
}
