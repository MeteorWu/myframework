package com.framework.meteor.work.client.dao;

import com.framework.meteor.work.client.model.Client;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Repository;

/**
 * 客户模块mybatis DAO
 *
 * @author Meteor.wu
 * @since 2017/12/4 16:50
 */
@Mapper
@Repository
public interface ClientMyBatisDao {

    @Insert("insert into `client`(client_name, phone,create_time) values(#{clientName},#{phone},now())")
//    @Options(useGeneratedKeys = true, keyProperty = "clientId")  // 如果id是自增的类型，那么这个设置可以将id在插入后赋给client对象里面的值
    @SelectKey(statement = "select last_insert_id() as clientId", keyProperty = "clientId", before = false, resultType = long.class)
    int insert(Client client);

    // 批量插入
//    @Insert("<script>" +
//            "insert into city (id, name, state) values " +
//            "<foreach collection=\"list\" item=\"city\" separator=\",\" >" +
//            "(#{city.id}, #{city.cityName}, #{city.cityState})" +
//            "</foreach>" +
//            "</script>")
//    @Options(useGeneratedKeys = true, keyProperty = "id")
//    int insertCities(List<City2> cities);
}
