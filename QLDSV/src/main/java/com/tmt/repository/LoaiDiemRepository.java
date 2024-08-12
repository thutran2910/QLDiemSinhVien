/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tmt.repository;

import com.tmt.pojo.LoaiDiem;

import java.util.List;

public interface LoaiDiemRepository {
    List<LoaiDiem> getNameLoaiDiem();  // Changed from findAll
}

