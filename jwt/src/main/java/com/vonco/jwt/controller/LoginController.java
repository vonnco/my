package com.vonco.jwt.controller;

import com.vonco.jwt.model.UserExt;
import com.vonco.jwt.model.request.LoginRequest;
import com.vonco.jwt.model.response.AuthCode;
import com.vonco.jwt.model.response.CommonCode;
import com.vonco.jwt.model.response.LoginResult;
import com.vonco.jwt.untils.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ke feng
 * @title: LoginController
 * @projectName my
 * @description: TODO
 * @date 2023/3/28 11:20
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Value("${jwt.key}")
    private String key;

    @Value("${jwt.ttl}")
    private Long ttl;

    @PostMapping("/userlogin")
    public LoginResult login(@RequestBody LoginRequest loginRequest) {
        //校验账号是否输入
        if(loginRequest == null || StringUtils.isEmpty(loginRequest.getUsername())){
            return new LoginResult(AuthCode.AUTH_USERNAME_NONE,null);
        }
        //校验密码是否输入
        if(StringUtils.isEmpty(loginRequest.getPassword())){
            return new LoginResult(AuthCode.AUTH_PASSWORD_NONE,null);
        }
        //创建BCryptPasswordEncoder对象用于校验密码
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //根据用户名查询用户信息
        UserExt userExt = new UserExt();
        userExt.setId("1");
        userExt.setUsername("test");
        userExt.setPassword(passwordEncoder.encode("123456"));
        userExt.setMenuCodes("test01,test02");
        //判断是否登录成功
        if (userExt != null && passwordEncoder.matches(loginRequest.getPassword(),userExt.getPassword())) {
            //创建jwt工具类
            JwtUtil jwtUtil = new JwtUtil();
            //设置私钥
            jwtUtil.setKey(key);
            //设置失效时间
            jwtUtil.setTtl(ttl);
            //创建jwt令牌
            Map<String,Object> map = new HashMap<>();
            String menuCodes = userExt.getMenuCodes();
            map.put("menuCodes",menuCodes);
            String token = jwtUtil.createJwt(userExt.getId(), userExt.getUsername(), map);
            return new LoginResult(CommonCode.SUCCESS,token);
        } else {
            return new LoginResult(AuthCode.AUTH_CREDENTIAL_ERROR,null);
        }
    }
}
