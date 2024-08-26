/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tmt.service;

import com.tmt.pojo.NguoiDung;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface NguoiDungService extends UserDetailsService {
    NguoiDung getUserByUsername(String username);
    boolean authUser(String username, String password);
}