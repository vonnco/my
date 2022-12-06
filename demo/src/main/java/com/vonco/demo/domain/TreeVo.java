package com.vonco.demo.domain;

import lombok.Data;

import java.util.List;

/**
 * @author ke feng
 * @title: TreeVo
 * @projectName my
 * @description: TODO
 * @date 2022/11/2 10:37
 */
@Data
public class TreeVo {

    private String code;

    private String name;

    private String parentCode;

    private List<TreeVo> children;
}
