package com.springboot.server.dao;

import com.springboot.server.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserDao extends CrudRepository<User, Integer> {

    @Override
    <S extends User> S save(S user);

    @Override
    void deleteById(Integer integer);

    @Override
    Optional<User> findById(Integer integer);

    @Override
    Iterable<User> findAll();

    Optional<User> findByLogin(String login);

}
