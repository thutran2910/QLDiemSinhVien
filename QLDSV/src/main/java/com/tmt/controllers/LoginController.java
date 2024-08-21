/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tmt.controllers;

import com.tmt.pojo.NguoiDung;
import com.tmt.service.NguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    
    @Autowired
    private NguoiDungService nguoiDungService;

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session) {
        NguoiDung nguoiDung = nguoiDungService.getUserByUsername(username);

        if (nguoiDung != null && nguoiDung.getPassword().equals(password)) {
            session.setAttribute("user", nguoiDung);
            return "redirect:/trangchu"; // Redirect to TrangChu.jsp
        } else {
            session.setAttribute("error", "Đăng nhập không thành công!");
            return "DangNhap"; // Redirect to login page on failure
        }
    }

    @GetMapping("/dangxuat")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/dangnhap"; // Redirect to DangNhap.jsp after logout
    }
}
