package com.framework.meteor.framework.service;

import com.framework.meteor.framework.dao.HttpExceptioinDao;
import com.framework.meteor.framework.model.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * http异常
 * ClassName  : HttpExceptionService
 * Author     : meteor.jacky.wu
 * CreateDate : 2019:11:15 11:49
 * Version    : 1.0
 * Company    : D球
 */
@Service
public class HttpExceptionService {
    @Autowired
    private HttpExceptioinDao httpExceptioinDao;

    public void insert(HttpException httpExceptioni) {
        httpExceptioinDao.save(httpExceptioni);
    }
}
