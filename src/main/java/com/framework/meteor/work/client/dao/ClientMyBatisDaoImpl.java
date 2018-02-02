package com.framework.meteor.work.client.dao;

import org.apache.ibatis.session.SqlSessionManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Meteor.wu
 * @since 2018/2/2 14:28
 */

public class ClientMyBatisDaoImpl {
    @Autowired
    private SqlSessionManager sqlSessionManager;
}
