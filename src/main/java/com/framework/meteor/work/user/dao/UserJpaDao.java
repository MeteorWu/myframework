package com.framework.meteor.work.user.dao;

import com.framework.meteor.work.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaDao extends JpaRepository<User, String> {
}
