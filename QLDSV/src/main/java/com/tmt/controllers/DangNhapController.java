package com.tmt.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DangNhapController {

    @GetMapping("/login")
    public String login() {
        return "DangNhap"; 
    }
}
