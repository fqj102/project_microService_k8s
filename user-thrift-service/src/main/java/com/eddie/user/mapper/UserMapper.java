package com.eddie.user.mapper;

import com.eddie.micro.user.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select id,USER_NAME as userName,USER_PASSWORD as passWord,REAL_NAME as realName,USER_MOBILE as mobile,USER_EMAIL as email FROM PE_USER WHERE ID=#{id}")
    UserInfo getUserById(@Param("id")int id);

    @Select("select id,USER_NAME as userName,USER_PASSWORD as passWord,REAL_NAME as realName,USER_MOBILE as mobile,USER_EMAIL as email FROM PE_USER WHERE USER_NAME=#{name}")
    UserInfo getUserByName(@Param("name")String UserName);

    @Select("select u.id,u.USER_NAME as userName,u.USER_PASSWORD as passWord,u.REAL_NAME as realName," +
            "u.USER_MOBILE as mobile,u.USER_EMAIL as email,t.intro,t.stars from PE_USER u," +
            "PE_TEACHER t where u.id=#{id} " +
            "and u.id=t.id")
    UserInfo getTeacherById(@Param("id")int id);

    @Insert("INSERT INTO PE_USER (USER_NAME,USER_PASSWORD,REAL_NAME,USER_MOBILE,USER_EMAIL) " +
            "VALUES (#{user.userName},#{user.passWord},#{user.realName},#{user.mobile},#{user.email })")
    void registerUser(@Param("user")UserInfo userInfo);
}
