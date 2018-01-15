package com.framework.meteor.work.user.dao;

import com.framework.meteor.work.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaDao extends JpaRepository<User, String> {
    User getByPhone(String phone);
}
