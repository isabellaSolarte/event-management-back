package com.eventmanagement.eventmanagement.services;

import java.util.List;

import com.eventmanagement.eventmanagement.AccesoDatos.models.User;

public interface IUserServices {
    public List<User> findAll();
    public User findById(int id);
    public User findByName(String name);
    public User create(User user);
    public void deleteById(int id);
    public User update(User event, int id);
}
