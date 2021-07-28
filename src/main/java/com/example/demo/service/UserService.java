package com.example.demo.service;



import com.example.demo.entity.User;

import java.util.List;

public interface UserService {
    public List<User> index();

    public User show(final Integer id);

    public void save(User user);

    public void update(User updatedUser);

    public void delete(Integer id);

    public User findPersonByEmail(String email);

    public User findPersonByName(String name);
}
