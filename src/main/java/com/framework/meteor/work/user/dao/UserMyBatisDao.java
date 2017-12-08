package com.framework.meteor.work.user.dao;

import com.framework.meteor.work.user.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper // 这里可以使用@Mapper注解，但是每个mapper都加注解比较麻烦，所以统一配置@MapperScan在扫描路径在application类中
        // 如果把mapper全部写在一个包，或者若干个少量location，那么MapperScan方便，如果习惯写在多个业务包，那么@mapper更合适
@Repository
public interface UserMyBatisDao {

    @Select(value = "select * from user")
    @ResultMap("userResult")    // 引用下面的  id = "userResult"
    List<User> getUserList();

    @Select(value = "SELECT * FROM user WHERE user_id = #{id}")
    @Results(id = "userResult", value = {
            @Result(property = "userId", column = "user_id")
    })
    User getById(String id);

    @Insert("insert into user(user_id, username,sex,create_time) values(#{userId},#{username},#{sex},now())")
//    @Options(useGeneratedKeys = true, keyProperty = "userId")  // 如果id是自增的类型，那么这个设置可以将id在插入后赋给user对象里面的值
    int insert(User user);

    @Update("UPDATE user SET username = #{user.username} , age = #{user.age} WHERE user_id = #{id}")
    int update(@Param("id") Integer id, @Param("user") User user);

//    @Delete("DELETE from tb_user where id = #{id} ")
//    int delete(Integer id);
}
