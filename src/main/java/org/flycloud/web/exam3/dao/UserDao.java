package org.flycloud.web.exam3.dao;

import org.flycloud.web.exam3.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, String> {

}
