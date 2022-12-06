package com.vonco.easypoi.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

/**
 * @author ke feng
 * @title: CompoentEntity
 * @projectName my
 * @description: TODO
 * @date 2022/10/29 10:52
 */
@Data
@ExcelTarget("componentEntity")
public class ComponentEntity {

    @Excel(name = "收集点编码")
    private String code;

    @Excel(name = "收集点名称")
    private String name;

    @Excel(name = "数量")
    private Integer boxNum;

    @Excel(name = "照片")
    private String photo;
}
