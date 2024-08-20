//package com.tmt.controllers;
//
///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.ModelAndView;
//import com.tmt.service.UserService;
//import java.util.Map;
//
//@Controller
//public class DangKyController {
//
//    @Autowired
//    private UserService userService;
//
//    @PostMapping("/register")
//    public ModelAndView registerUser(
//            @RequestParam("firstName") String firstName,
//            @RequestParam("lastName") String lastName,
//            @RequestParam("email") String email,
//            @RequestParam("phone") String phone,
//            @RequestParam("username") String username,
//            @RequestParam("password") String password,
//            @RequestParam("userRole") String userRole,
//            @RequestParam("avatar") MultipartFile avatar) {
//        try {
//            userService.addUser(Map.of(
//                "firstName", firstName,
//                "lastName", lastName,
//                "email", email,
//                "phone", phone,
//                "username", username,
//                "password", password,
//                "userRole", userRole
//            ), avatar);
//            return new ModelAndView("redirect:/login");
//        } catch (Exception e) {
//            return new ModelAndView("register", "errorMessage", e.getMessage());
//        }
//    }
//}
