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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SinhVienController {

    private final SinhVienService sinhVienService;

    @Autowired
    public SinhVienController(SinhVienService sinhVienService) {
        this.sinhVienService = sinhVienService;
    }

    @GetMapping("/dssv")
    public String listSinhVien(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "7") int pageSize,
            Model model) {

        // Ensure page is at least 1
        if (page < 1) {
            page = 1;
        }

        List<SinhVien> sinhVienList = sinhVienService.findAll(page, pageSize);
        int totalItems = sinhVienService.countAll();
        int totalPages = (int) Math.ceil((double) totalItems / pageSize);

        // Ensure page is not greater than totalPages
        if (page > totalPages) {
            page = totalPages;
            sinhVienList = sinhVienService.findAll(page, pageSize); // Refresh list for the corrected page
        }

        model.addAttribute("sinhVienList", sinhVienList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "DanhSachSinhVien";
    }

    @GetMapping("/chitietsinhvien")
    public String viewSinhVienDetail(@RequestParam("id") int id, Model model) {
        SinhVien sinhVien = sinhVienService.findById(id);
        if (sinhVien != null) {
            model.addAttribute("sinhVien", sinhVien);
            return "ChiTietSinhVien";
        } else {
            return "error";
        }
    }

    @GetMapping("/sinhvien/{id}/saveOrUpdate")
    public String showForm(@PathVariable("id") Integer id, Model model) {
        SinhVien sinhVien = (id != null) ? sinhVienService.findById(id) : new SinhVien();
        List<LopHoc> lopHocs = sinhVienService.getAllLopHocs();
        List<Khoa> khoas = sinhVienService.getAllKhoas();
        List<NganhDaoTao> nganhDaoTaos = sinhVienService.getAllNganhDaoTaos();

        model.addAttribute("sinhVien", sinhVien);
        model.addAttribute("lopHocs", lopHocs);
        model.addAttribute("khoas", khoas);
        model.addAttribute("nganhDaoTaos", nganhDaoTaos);

        return "SuaSinhVien";
    }

    @PostMapping("/sinhvien/{id}/saveOrUpdate")
    public String saveOrUpdateSinhVien(@PathVariable("id") Integer id,
            @ModelAttribute("sinhVien") SinhVien sinhVien,
            BindingResult result,
            Model model) {

        sinhVienService.saveOrUpdate(sinhVien);
        return "redirect:/dssv";
    }

    @GetMapping("/sinhvien/form")
    public String showAddSinhVienForm(Model model) {
        SinhVien sinhVien = new SinhVien(); // Create an empty SinhVien object for the form
        List<LopHoc> lopHocs = sinhVienService.getAllLopHocs();
        List<Khoa> khoas = sinhVienService.getAllKhoas();
        List<NganhDaoTao> nganhDaoTaos = sinhVienService.getAllNganhDaoTaos();

        model.addAttribute("sinhVien", sinhVien);
        model.addAttribute("lopHocs", lopHocs);
        model.addAttribute("khoas", khoas);
        model.addAttribute("nganhDaoTaos", nganhDaoTaos);

        return "ThemSinhVien"; // Return the view name for adding a student
    }

    @PostMapping("/sinhvien/saveOrUpdate")
    public String saveOrUpdateSinhVien(@ModelAttribute("sinhVien") SinhVien sinhVien, BindingResult result, Model model) {
        System.out.println("Received SinhVien: " + sinhVien);
        System.out.println("GioiTinh: " + sinhVien.getGioiTinh());

        if (result.hasErrors()) {
            System.out.println("Validation errors: " + result.getAllErrors());

            List<LopHoc> lopHocs = sinhVienService.getAllLopHocs();
            List<Khoa> khoas = sinhVienService.getAllKhoas();
            List<NganhDaoTao> nganhDaoTaos = sinhVienService.getAllNganhDaoTaos();

            model.addAttribute("sinhVien", sinhVien);
            model.addAttribute("lopHocs", lopHocs);
            model.addAttribute("khoas", khoas);
            model.addAttribute("nganhDaoTaos", nganhDaoTaos);

            return "ThemSinhVien";
        }
        sinhVienService.saveOrUpdate(sinhVien);
        return "redirect:/dssv";
    }

    @PostMapping("/sinhvien/{id}/delete")
    public String deleteSinhVien(@PathVariable("id") int id) {
        sinhVienService.deleteById(id);
        return "redirect:/dssv";
    }
}
