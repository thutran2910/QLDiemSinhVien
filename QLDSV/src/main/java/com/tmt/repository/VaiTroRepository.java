/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tmt.repository;

import com.tmt.pojo.VaiTro;
import java.util.List;

public interface VaiTroRepository {
    List<VaiTro> getVaiTro();
    void saveVaiTro(VaiTro vaiTro);
    void deleteVaiTro(int id);
}
