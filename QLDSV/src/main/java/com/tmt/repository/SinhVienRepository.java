package com.tmt.repository;

import com.tmt.pojo.Khoa;
import com.tmt.pojo.LopHoc;
import com.tmt.pojo.NganhDaoTao;
import com.tmt.pojo.SinhVien;
import java.util.List;

public interface SinhVienRepository {

    List<SinhVien> findAll(int page, int pageSize);

    List<SinhVien> findByLopHocId(int lopHocId);

    List<SinhVien> getNameSinhVien();

    List<LopHoc> getAllLopHocs();

    List<Khoa> getAllKhoas();

    List<NganhDaoTao> getAllNganhDaoTaos();

    SinhVien getSinhVienById(int id);

    void save(SinhVien sinhVien);

    void update(SinhVien sinhVien);

    void saveOrUpdate(SinhVien sinhVien);

    void deleteById(int id);

    int countAll();

    List<SinhVien> searchByTerm(String searchTerm);
    
    boolean isEmailExists(String email);
    
    
}
