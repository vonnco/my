package com.vonco.easyexcel.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.vonco.easyexcel.converter.SexConverter;
import lombok.Builder;
import lombok.Data;

/**
 * @author ke feng
 * @title: Student
 * @projectName my
 * @description: TODO
 * @date 2021/12/10 13:49
 */
@Data
@Builder
public class Student {
    @ExcelProperty(value = "姓名")
    private String name;
    @ExcelProperty(value = "性别",converter = SexConverter.class)
    private Integer sex;
    @ExcelProperty({"各科分数","学科"})
    private String subject;
    @ExcelProperty({"各科分数","分数"})
    private Integer score;
}
