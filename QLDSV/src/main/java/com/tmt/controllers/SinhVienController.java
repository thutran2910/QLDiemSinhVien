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
import javax.validation.Valid;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

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

        if (page < 1) {
            page = 1;
        }

        List<SinhVien> sinhVienList = sinhVienService.findAll(page, pageSize);
        int totalItems = sinhVienService.countAll();
        int totalPages = (int) Math.ceil((double) totalItems / pageSize);

        if (page > totalPages) {
            page = totalPages;
            sinhVienList = sinhVienService.findAll(page, pageSize);
        }

        model.addAttribute("sinhVienList", sinhVienList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("pageSize", pageSize);

        return "DanhSachSinhVien";
    }

    @GetMapping("/chitietsinhvien")
    public String viewSinhVienDetail(@RequestParam("id") int id, Model model) {
        SinhVien sinhVien = sinhVienService.getSinhVienById(id);
        if (sinhVien != null) {
            model.addAttribute("sinhVien", sinhVien);
            return "ChiTietSinhVien";
        } else {
            return "error";
        }
    }

    @PostMapping("/{id}/upload-avatar")
    public String uploadAvatar(@PathVariable("id") int sinhVienId, @RequestParam("file") MultipartFile file) {
        String avatarUrl = sinhVienService.uploadAvatar(file, sinhVienId);
        if (avatarUrl != null) {
            return "redirect:/sinhvien/" + sinhVienId; // Redirect đến trang chi tiết sinh viên
        }
        return "error"; // Xử lý lỗi nếu cần
    }

    @GetMapping("/search")
    public String searchStudent(@RequestParam("searchTerm") String searchTerm, Model model) {
        List<SinhVien> sinhVienList = sinhVienService.searchSinhVienByTerm(searchTerm);
        model.addAttribute("sinhVienList", sinhVienList);
        model.addAttribute("searchTerm", searchTerm); // Pass searchTerm to JSP
        return "DanhSachSinhVien"; // Trả về trang JSP hiển thị danh sách sinh viên
    }

    @GetMapping("/sinhvien/{id}/saveOrUpdate")
    public String showForm(@PathVariable("id") Integer id, Model model) {
        SinhVien sinhVien = (id != null) ? sinhVienService.getSinhVienById(id) : new SinhVien();
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

        if (sinhVien.getUserRole() == null) {
            sinhVien.setUserRole("ROLE_SINHVIEN");
        }

        sinhVienService.saveOrUpdate(sinhVien);
        return "redirect:/dssv";
    }

    @PostMapping("/sinhvien/{id}/delete")
    public String deleteSinhVien(@PathVariable("id") int id) {
        sinhVienService.deleteById(id);
        return "redirect:/dssv";
    }

    @GetMapping("/dangki")
    public String showRegistrationForm(Model model) {
        model.addAttribute("sinhVien", new SinhVien());
        return "DangKi"; // Ensure this matches the JSP file name
    }

    @PostMapping("/dangki")
    public String registerStudent(@RequestParam("file") MultipartFile file,
            @Valid @ModelAttribute("sinhVien") SinhVien sinhVien,
            BindingResult result, Model model) {

        // Kiểm tra lỗi từ BindingResult
        if (result.hasErrors()) {
            return "DangKi";
        }

        // Thiết lập vai trò người dùng mặc định nếu chưa có
        if (sinhVien.getUserRole() == null || sinhVien.getUserRole().isEmpty()) {
            sinhVien.setUserRole("ROLE_SINHVIEN");
        }

        // Kiểm tra tên tài khoản và mật khẩu không được để trống
        if (sinhVien.getUsername() == null || sinhVien.getUsername().isEmpty()
                || sinhVien.getPassword() == null || sinhVien.getPassword().isEmpty()) {
            model.addAttribute("errorMessage", "Tên tài khoản và mật khẩu không được để trống.");
            return "DangKi";
        }

        // Đăng ký sinh viên mới
        boolean isRegistered = sinhVienService.registerSinhVien(sinhVien);
        if (!isRegistered) {
            model.addAttribute("errorMessage", "Tên tài khoản hoặc email đã tồn tại. Vui lòng thử lại.");
            return "DangKi";
        }

        // Xử lý ảnh đại diện nếu có
        if (!file.isEmpty()) {
            try {
                // Tải ảnh lên và lấy URL
                String avatarUrl = sinhVienService.uploadAvatar(file, sinhVien.getId()); // Sử dụng phương thức uploadAvatar với sinhVienId
                if (avatarUrl != null) {
                    sinhVien.setAvatar(avatarUrl);
                    // Cập nhật thông tin sinh viên sau khi tải ảnh
                    sinhVienService.saveOrUpdate(sinhVien); // Cập nhật thông tin sinh viên
                } else {
                    model.addAttribute("errorMessage", "Lỗi khi tải ảnh đại diện. Vui lòng thử lại.");
                    return "DangKi";
                }
            } catch (Exception e) {
                model.addAttribute("errorMessage", "Lỗi khi tải ảnh đại diện: " + e.getMessage());
                return "DangKi";
            }
        }

        return "redirect:/login";
    }

}
