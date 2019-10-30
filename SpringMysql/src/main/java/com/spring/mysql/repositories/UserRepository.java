package com.spring.mysql.repositories;

import org.springframework.data.repository.CrudRepository;

import com.spring.mysql.models.User;

public interface UserRepository extends CrudRepository<User, Integer>{

}
