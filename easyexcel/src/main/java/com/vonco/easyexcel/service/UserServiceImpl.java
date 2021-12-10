package com.vonco.easyexcel.service;

import com.vonco.easyexcel.dao.UserMapper;
import com.vonco.easyexcel.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ke feng
 * @title: UserServiceImpl
 * @projectName my
 * @description: TODO
 * @date 2021/12/10 15:57
 */
@Service
public class UserServiceImpl {
    @Autowired
    private UserMapper userMapper;

    public Integer insertBatch(List<User> userList){
        return userMapper.insertBatch(userList);
    }
}
