package com.vonco.shardingsphere.controller;


import com.vonco.shardingsphere.model.User;
import com.vonco.shardingsphere.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ke feng
 * @since 2023-04-04
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/add")
    public Boolean add(){
        List<User> users = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            User user = new User();
            user.setId(i);
            user.setAge(i+1);
            users.add(user);
        }
        return userService.saveBatch(users);
    }

    @GetMapping("/all")
    public List<User> all(){
        return userService.list();
    }

    @GetMapping("/detail/{id}")
    public User detail(@PathVariable("id") Integer id){
        return userService.getById(id);
    }

}
