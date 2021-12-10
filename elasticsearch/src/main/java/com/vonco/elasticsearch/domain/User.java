package com.vonco.elasticsearch.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ke feng
 * @title: User
 * @projectName my
 * @description: TODO
 * @date 2021/11/26 10:52
 */
@Data
@Document(indexName = "user",shards = 1,replicas = 0)
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @Field(type = FieldType.Keyword)
    private String name;

    @Field(type = FieldType.Integer)
    private Integer sex;

    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String school;

    @Field(type = FieldType.Text,analyzer = "ik_smart")
    private String hobby;

    @Field(type = FieldType.Date,format = DateFormat.basic_date_time)
    private Date birthday;

    @Field(index = false)
    private String remark;
}
