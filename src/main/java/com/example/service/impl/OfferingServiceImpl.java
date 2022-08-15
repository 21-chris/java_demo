package com.example.service.impl;

import com.example.mapper.OfferingMapper;
import com.example.mapper.UserMapper;
import com.example.pojo.Offering;
import com.example.pojo.User;
import com.example.service.OfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferingServiceImpl implements OfferingService {
    @Autowired
    private OfferingMapper offeringMapper;

    @Override
    public int insertOffering(Offering offering) {
//        鉴权是那个用户上架商品，
        return offeringMapper.insert(offering);
    }
}
