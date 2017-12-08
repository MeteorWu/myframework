package com.framework.meteor.framework.dao;

import com.framework.meteor.framework.model.ExceptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExceptionDao extends JpaRepository<ExceptionEntity, Long> {
}
