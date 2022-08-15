package com.example.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
//    @TableId(value = "id",type = IdType.AUTO)
    @Id
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String name;
    private String password;
}
