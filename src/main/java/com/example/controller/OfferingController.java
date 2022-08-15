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
//        插入商品表
        System.out.println("df"+offeringDto);

        Offering offering = OfferingConvert.Instance.abc(offeringDto);
        Object userId = request.getSession().getAttribute("user");
        User user = userMapper.selectById(userId.toString());
//        入参数转为数据库表中的
        System.out.println("df"+userId+ user);
        System.out.println("df"+ offering);

//        offering.setUserId(userId.toString());
//        offeringService.insertOffering(offering);
//
////        插入商品属性
//        ArrayList arrayList=  offeringDto.getAttributeList();
//        for (int i = 0; i < arrayList.size(); i++) {
//            Attribute dfd = (Attribute) arrayList.get(i);
////            dfd.setOfferingId();
//            attributeService.insertAttribute(dfd);
//        }

//        offering.setUserId(user.getId().toString()) ;
//        offeringService.insertOffering(offering);
        return 1;
    }
}
