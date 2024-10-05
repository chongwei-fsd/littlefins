package com.cw.littlefins_proj.service;

import com.cw.littlefins_proj.model.User;

import java.util.List;
import java.util.Optional;

public interface UserServiceInterface {
    public abstract User save(User user);               // save()
    public abstract List<User> findAll();                       // findAll()
    public abstract Optional<User> findById(Long id);           // findById()
    public abstract User update(User user);             // update()
    public abstract void deleteById(Long id);                       //  deleteById()
    public abstract long count();
}
