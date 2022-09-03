package com.example.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Attribute {
    @Id
    @TableId(value = "id",type = IdType.AUTO)
    private int id;
    private String attributeValue;
    private String attributeType;
    private int offeringId;
}
