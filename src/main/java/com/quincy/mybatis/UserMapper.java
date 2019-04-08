package com.quincy.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author quincy
 * @date 2019/3/15 星期五
 */
@Mapper
public interface UserMapper {

    int insert(User user);
    @Select("select * from user where name=#{name}")
    User get(String name);

}
