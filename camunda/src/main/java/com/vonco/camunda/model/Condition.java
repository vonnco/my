package com.vonco.camunda.model;

import lombok.Data;

@Data
public class Condition {
    //业务字段名称
    private String variableName;
    //业务条件值
    private Object variableValue;
    //变量类型 0-任务变量 1-流程变量
    private Integer variableType;
    //条件 eq：等于 like: 模糊 ge：大等于 le 小等于 in 包含
    private String conditionType;

}