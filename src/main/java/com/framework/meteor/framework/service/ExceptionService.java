package com.framework.meteor.framework.service;


import com.framework.meteor.framework.dao.ExceptionDao;
import com.framework.meteor.framework.model.ExceptionEntity;
import com.framework.meteor.framework.model.Response;
import com.framework.meteor.framework.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Component
public class ExceptionService {
    @Autowired
    private ExceptionDao exceptionDao;

    @Transactional
    public void addException(Response response, String userId, String method){
        if(null == response.getTimestamp()) {
            response.setTimestamp(System.currentTimeMillis() + "");
        }
        ExceptionEntity exceptionEntity = new ExceptionEntity();
        exceptionEntity.setError(response.getError());
        exceptionEntity.setMessage(response.getMessage());
        exceptionEntity.setPath(response.getPath());
        exceptionEntity.setStatus(response.getCode());
        exceptionEntity.setTimestamp(response.getTimestamp());
        exceptionEntity.setUserId(userId);
        exceptionEntity.setMethod(method);
        exceptionDao.save(exceptionEntity);
    }

    public void addException(Map<String, Object> body){
        ExceptionEntity exceptionEntity = new ExceptionEntity();
        exceptionEntity.setError(body.get("error").toString());
        exceptionEntity.setMessage(body.get("message").toString());
        exceptionEntity.setPath(body.get("path").toString());
        exceptionEntity.setStatus(body.get("code").toString());
        exceptionEntity.setTimestamp(DateUtils.timeStampStr2DateStr(System.currentTimeMillis() + "", null));
        exceptionEntity.setMessage(body.get("method").toString());
        exceptionEntity.setUserId(body.get("userId").toString());
        exceptionDao.save(exceptionEntity);
    }

}
