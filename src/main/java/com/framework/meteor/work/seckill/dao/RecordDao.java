package com.framework.meteor.work.seckill.dao;

import com.framework.meteor.work.seckill.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordDao extends JpaRepository<Record, String> {
}
