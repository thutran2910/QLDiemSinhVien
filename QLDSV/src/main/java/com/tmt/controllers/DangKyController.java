package com.tmt.controllers;

import com.tmt.pojo.Khoa;
import com.tmt.pojo.LopHoc;
import com.tmt.pojo.NganhDaoTao;
import com.tmt.pojo.SinhVien;
import com.tmt.service.SinhVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/dangky")  // Define a common base path for all methods in this controller
public class DangKyController {

    private final SinhVienService sinhVienService;

    @Autowired
    public DangKyController(SinhVienService sinhVienService) {
        this.sinhVienService = sinhVienService;
    }

    @GetMapping
    public String showRegisterForm(Model model) {
        SinhVien sinhVien = new SinhVien(); // Create an empty SinhVien object for the form
        List<LopHoc> lopHocs = sinhVienService.getAllLopHocs();
        List<Khoa> khoas = sinhVienService.getAllKhoas();
        List<NganhDaoTao> nganhDaoTaos = sinhVienService.getAllNganhDaoTaos();

        model.addAttribute("sinhVien", sinhVien);
        model.addAttribute("lopHocs", lopHocs);
        model.addAttribute("khoas", khoas);
        model.addAttribute("nganhDaoTaos", nganhDaoTaos);

        return "DangKy"; // Name of the registration page
    }

    @PostMapping
    public String registerSinhVien(@ModelAttribute("sinhVien") SinhVien sinhVien, BindingResult result, Model model, HttpSession session) {
        if (result.hasErrors()) {
            List<LopHoc> lopHocs = sinhVienService.getAllLopHocs();
            List<Khoa> khoas = sinhVienService.getAllKhoas();
            List<NganhDaoTao> nganhDaoTaos = sinhVienService.getAllNganhDaoTaos();

            model.addAttribute("sinhVien", sinhVien);
            model.addAttribute("lopHocs", lopHocs);
            model.addAttribute("khoas", khoas);
            model.addAttribute("nganhDaoTaos", nganhDaoTaos);

            return "DangKy"; // Return to registration page if there are errors
        }

        // Validate email domain
        if (!sinhVien.getEmail().endsWith("@ou.edu.vn")) {
            model.addAttribute("emailError", "Email phải có đuôi @ou.edu.vn");
            List<LopHoc> lopHocs = sinhVienService.getAllLopHocs();
            List<Khoa> khoas = sinhVienService.getAllKhoas();
            List<NganhDaoTao> nganhDaoTaos = sinhVienService.getAllNganhDaoTaos();

            model.addAttribute("sinhVien", sinhVien);
            model.addAttribute("lopHocs", lopHocs);
            model.addAttribute("khoas", khoas);
            model.addAttribute("nganhDaoTaos", nganhDaoTaos);

            return "DangKy"; // Return to registration page if email validation fails
        }

        sinhVienService.saveOrUpdate(sinhVien);
        session.setAttribute("message", "Đăng ký thành công!");
        return "redirect:/dangnhap"; // Redirect to login page after successful registration
    }
}
