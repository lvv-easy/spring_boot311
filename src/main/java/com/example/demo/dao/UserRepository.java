package com.example.demo.dao;


import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
//    public List<User> index();
//
//    public User show(final Long id);
//
//    public void save(User user);
//
//    public void update(User updatedUser);
//
//    public void delete(Long id);
//
    public User findUserByEmail(String email);

    public User findUserByName(String name);
}
