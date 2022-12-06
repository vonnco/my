package com.vonco.easypoi.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author ke feng
 * @title: SubjectEntity
 * @projectName my
 * @description: TODO
 * @date 2022/10/29 10:29
 */
@Data
@ExcelTarget("subjectEntity")
public class SubjectEntity {

    @Excel(name = "序号", needMerge = true)
    private String id;

    @Excel(name = "单位编码", needMerge = true)
    private String code;

    @Excel(name = "单位名称", needMerge = true)
    private String name;

    @Excel(name = "医院属性（公、私）", needMerge = true, replace = {"公_公","私_私"}, addressList = true)
    private String attribute;

    @Excel(name = "医院等级", needMerge = true, replace = {"一级_1","二级_2","三级_3"}, addressList = true)
    private Integer grade;

    @Excel(name = "街巷代码", needMerge = true)
    private String  sectionCode;

    @Excel(name = "所属街巷", needMerge = true)
    private String  sectionName;

    @Excel(name = "门（楼）牌号", needMerge = true)
    private String houseNum;

    @Excel(name = "经度", needMerge = true, groupName = "地理位置")
    private BigDecimal longitude;

    @Excel(name = "纬度", needMerge = true, groupName = "地理位置")
    private BigDecimal latitude;

    @Excel(name = "单位照片", needMerge = true)
    private String photo;

    @Excel(name = "单位责任人", needMerge = true)
    private String responsible;

    @Excel(name = "责任人联系电话", needMerge = true)
    private String phone;

    @Excel(name = "在编", needMerge = true, groupName = "收费方式")
    private Integer insiderNum;

    @Excel(name = "非编", needMerge = true, groupName = "收费方式")
    private Integer outsiderNum;

    @Excel(name = "床位", needMerge = true, groupName = "收费方式")
    private Integer bedNum;

    @Excel(name = "<=30", needMerge = true, groupName = "收费方式")
    private BigDecimal area1;

    @Excel(name = ">30", needMerge = true, groupName = "收费方式")
    private BigDecimal area2;

    @Excel(name = "桶数", needMerge = true, groupName = "收费方式")
    private Integer bucketNum;

    @ExcelCollection(name = "环卫设施")
    private List<ComponentEntity> componentEntityList;
}
