package com.tmt.controllers;

import com.tmt.pojo.LopHoc;
import com.tmt.pojo.GiangVienLopHoc;
import com.tmt.pojo.SinhVien;
import com.tmt.service.LopHocService;
import com.tmt.service.GiangVienService;
import com.tmt.service.GiangVienLopHocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class LopHocController {

    @Autowired
    private LopHocService lopHocService;

    @Autowired
    private GiangVienService giangVienService;

    @Autowired
    private GiangVienLopHocService giangVienLopHocService;

    @GetMapping("/dslop")
    public String listLopHoc(Model model) {
        List<LopHoc> lopHocList = lopHocService.findAll();
        model.addAttribute("lopHocList", lopHocList);
        return "DanhSachLopHoc";
    }

    @GetMapping("/lophoc/{id}")
public String getLopHocDetails(
        @PathVariable int id,
        @RequestParam(value = "page", defaultValue = "1") int page,
        @RequestParam(value = "size", defaultValue = "7") int pageSize,
        Model model) {

    LopHoc lopHoc = lopHocService.findById(id);
    if (lopHoc != null) {
        int totalItems = lopHocService.countStudentsByLopHocId(id);
        int totalPages = (int) Math.ceil((double) totalItems / pageSize);

        if (page < 1) {
            page = 1;
        }

        if (page > totalPages) {
            page = totalPages;
        }

        List<SinhVien> sinhVienList = lopHocService.getStudentsByLopHocId(id, page, pageSize);
        List<GiangVienLopHoc> giangVienLopHocList = giangVienLopHocService.findByLopHocId(id);

        model.addAttribute("lopHoc", lopHoc);
        model.addAttribute("giangVienLopHocList", giangVienLopHocList);
        model.addAttribute("sinhVienList", sinhVienList);  // Sử dụng danh sách đơn thuần
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalPages", totalPages);
    }
    return "ChiTietLopHoc";
}

}
