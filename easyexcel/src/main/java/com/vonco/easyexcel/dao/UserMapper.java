package com.vonco.easyexcel.dao;

import com.vonco.easyexcel.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author ke feng
 * @title: UerMapper
 * @projectName my
 * @description: TODO
 * @date 2021/12/9 10:42
 */
@Mapper
@Component
public interface UserMapper {
    int insertBatch(List<User> userList);
    List<User> selectAll();
}
