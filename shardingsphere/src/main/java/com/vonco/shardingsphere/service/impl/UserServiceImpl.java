package com.vonco.shardingsphere.service.impl;

import com.vonco.shardingsphere.model.User;
import com.vonco.shardingsphere.dao.UserMapper;
import com.vonco.shardingsphere.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ke feng
 * @since 2023-04-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
