package com.vonco.demo.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("user")
public class User {
    private Integer id;
    private String name;
    private Integer sex;
    private Integer age;
    private Date birthday;
    private String address;
}