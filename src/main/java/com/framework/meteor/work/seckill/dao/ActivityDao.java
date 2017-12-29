package com.framework.meteor.work.seckill.dao;

import com.framework.meteor.work.seckill.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Meteor.wu
 * @since 2017/12/29 14:17
 */
@Repository
public interface ActivityDao extends JpaRepository<Activity, String>{

    @Modifying
    @Query(value = "update Activity set prizeCount = prizeCount -1 where id = ?1")
    void seckillPrize(String activityId);
}
