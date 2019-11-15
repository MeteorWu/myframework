package com.framework.meteor.framework.dao;

import com.framework.meteor.framework.model.HttpException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HttpExceptioinDao extends JpaRepository<HttpException, String> {
}
