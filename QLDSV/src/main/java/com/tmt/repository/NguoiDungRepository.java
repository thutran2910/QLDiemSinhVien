/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tmt.repository;


import com.tmt.pojo.NguoiDung;


public interface NguoiDungRepository {
    NguoiDung getUserByUsername(String username);
}