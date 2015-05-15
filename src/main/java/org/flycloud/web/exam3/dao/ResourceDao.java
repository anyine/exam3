package org.flycloud.web.exam3.dao;

import org.flycloud.web.exam3.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceDao extends JpaRepository<Resource, String> {

}
