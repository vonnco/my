package com.vonco.shardingsphere.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vonco.shardingsphere.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ke feng
 * @since 2023-04-04
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
