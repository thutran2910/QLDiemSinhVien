/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tmt.repository;

import com.tmt.pojo.HocKy;

import java.util.List;

public interface HocKyRepository {
    List<HocKy> getNameHocKy();  // Changed from findAll
}
