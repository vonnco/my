package com.vonco.activiti.model;

import java.io.Serializable;

/**
 * @author ke feng
 * @title: Holiday
 * @projectName my
 * @description: TODO
 * @date 2021/12/2 15:19
 */
public class Holiday implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer num;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
