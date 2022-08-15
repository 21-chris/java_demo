package com.example.vo;


import com.example.pojo.Offering;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class OfferingConvert {
    public static OfferingConvert Instance = Mappers.getMapper(OfferingConvert.class);
    public abstract Offering abc(OfferingDto offeringDto);
}
