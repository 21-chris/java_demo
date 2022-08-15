package com.example.service.impl;

import com.example.mapper.AttributeMapper;
import com.example.pojo.Attribute;
import com.example.service.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttributeServiceImpl implements AttributeService {
    @Autowired
    private AttributeMapper attributeMapper;
    @Override
    public int insertAttribute(Attribute attribute) {
        return attributeMapper.insert(attribute);
    }
}
