package com.example.controller;

import com.example.mapper.UserMapper;
import com.example.pojo.Attribute;
import com.example.pojo.Offering;
import com.example.pojo.User;
import com.example.service.AttributeService;
import com.example.service.OfferingService;
import com.example.vo.OfferingConvert;
import com.example.vo.OfferingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/offering")
public class OfferingController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AttributeService attributeService;

    @Autowired
    private OfferingService offeringService;

    @PostMapping("/add")
    public int insertUser(HttpServletRequest request, @RequestBody OfferingDto offeringDto){
//        入参数转为数据库表中的
        //        插入商品表
        Offering offering = OfferingConvert.Instance.abc(offeringDto);
        offering.setUserId("3");
        offeringService.insertOffering(offering);
        System.out.println();

//        使用mapstruct after解析出属性列表
        List<Attribute> attriubteList = OfferingConvert.Instance.dtoAfter(offeringDto);
//        插入商品属性
//        ArrayList arrayList=  offeringDto.getAttributeList();
//        for (int i = 0; i < arrayList.size(); i++) {
//            Attribute dfd = (Attribute) arrayList.get(i);
//            attributeService.insertAttribute(dfd);
//        }
        for (int i = 0; i < attriubteList.size(); i++) {
            Attribute attribute = attriubteList.get(i);
            attribute.setOfferingId(offering.getId());
            attributeService.insertAttribute(attribute);
        }
//        Object userId = request.getSession().getAttribute("user");
//        User user = userMapper.selectById(userId.toString());


        return 1;
    }
}
