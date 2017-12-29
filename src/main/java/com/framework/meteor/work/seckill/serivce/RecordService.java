package com.framework.meteor.work.seckill.serivce;

import com.framework.meteor.framework.util.UUIDUtil;
import com.framework.meteor.work.seckill.dao.RecordDao;
import com.framework.meteor.work.seckill.model.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 日志记录
 *
 * @author Meteor.wu
 * @since 2017/12/29 13:29
 */
@Service
public class RecordService {

    @Autowired
    private RecordDao recordDao;

    public void save(Record record) {
        record.setId(UUIDUtil.getUUID());
        recordDao.save(record);
    }
}
