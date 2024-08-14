/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tmt.repository;

import com.tmt.pojo.User;

/**
 *
 * @author admin
 */
public interface UserRepository {
    User getUserByUsername(String username);
    boolean authUser(String username, String password);
    User addUser(User user);
}