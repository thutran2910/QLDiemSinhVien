package com.tmt.service;

import com.tmt.pojo.SinhVien;
import com.tmt.pojo.LopHoc;
import com.tmt.pojo.Khoa;
import com.tmt.pojo.NganhDaoTao;
import java.io.IOException;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface SinhVienService {

    List<SinhVien> findAll(int page, int pageSize);

    List<SinhVien> findByLopHocId(int lopHocId);

    List<SinhVien> getNameSinhVien();

    List<LopHoc> getAllLopHocs(); // Thêm phương thức lấy danh sách lớp

    List<Khoa> getAllKhoas(); // Thêm phương thức lấy danh sách khoa

    List<NganhDaoTao> getAllNganhDaoTaos(); // Thêm phương thức lấy danh sách ngành đào tạo

    SinhVien getSinhVienById(int id);

    void save(SinhVien sinhVien);

    void update(SinhVien sinhVien);

    void saveOrUpdate(SinhVien sinhVien);

    void deleteById(int id);

    int countAll();

    List<SinhVien> searchSinhVienByTerm(String searchTerm);

    boolean registerSinhVien(SinhVien sinhVien);

    String uploadAvatar(MultipartFile file, int sinhVienId);
}
