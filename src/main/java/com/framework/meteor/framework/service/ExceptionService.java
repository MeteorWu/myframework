package com.framework.meteor.framework.service;


import com.framework.meteor.framework.model.ExceptionEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ExceptionService {
//    @Autowired
//    private ExceptionDao exceptionDao;

    @Transactional
    public void addException(ExceptionEntity exceptionEntity){
//        exceptionDao.save(exceptionEntity);
    }

}
