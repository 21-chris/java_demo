package com.example.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.ComponentScan;

import javax.persistence.Id;


@Data
public class Offering {
    @Id
    @TableId(value = "id",type = IdType.AUTO)
    private int id;
    private String offeringName;
    private String reverseField;
    private String userId;
}
