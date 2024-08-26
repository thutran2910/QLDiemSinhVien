/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tmt.components;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHashValidator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        // Simulate saved hash (from your database)
        String encodedPassword = "$2a$10$Bd4Rrm6h7g9jec8NJRe8auhDiR5Jgpn8fK2hYS2tWlZSlEa6DFWxm";  // Example hash
        
        // Raw password to validate
        String rawPassword = "sv1";  // Example password
        
        // Validate password
        boolean matches = encoder.matches(rawPassword, encodedPassword);
        System.out.println("Password matches: " + matches);
    }
}
