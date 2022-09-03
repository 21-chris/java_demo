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
public class User {
//    @TableId(value = "id",type = IdType.AUTO)
    @TableId
    private Long id;
    private String name;
    private String password;

}
