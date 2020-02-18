package com.springboot.server.dao;

import com.springboot.server.model.Role;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface RoleDao extends CrudRepository<Role,Integer> {
    @Override
    Optional<Role> findById(Integer integer);
}
