package com.tmt.service.impl;

import com.tmt.pojo.SinhVien;
import com.tmt.pojo.LopHoc;
import com.tmt.pojo.Khoa;
import com.tmt.pojo.NganhDaoTao;
import com.tmt.repository.SinhVienRepository;
import com.tmt.service.SinhVienService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

@Service
public class SinhVienServiceImpl implements SinhVienService {

    private final SinhVienRepository sinhVienRepository;

    @Autowired
    private ServletContext servletContext;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    public SinhVienServiceImpl(SinhVienRepository sinhVienRepository) {
        this.sinhVienRepository = sinhVienRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public SinhVien getSinhVienById(int id) {
        return sinhVienRepository.getSinhVienById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SinhVien> findAll(int page, int pageSize) {
        return sinhVienRepository.findAll(page, pageSize);
    }

    @Override
    @Transactional(readOnly = true)
    public int countAll() {
        return sinhVienRepository.countAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<SinhVien> findByLopHocId(int lopHocId) {
        return sinhVienRepository.findByLopHocId(lopHocId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SinhVien> getNameSinhVien() {
        return sinhVienRepository.getNameSinhVien();
    }

//    @Override
//    @Transactional
//    public void save(SinhVien sinhVien) {
//        String hashedPassword = passwordEncoder.encode(sinhVien.getPassword());
//        sinhVien.setPassword(hashedPassword);
//        sinhVienRepository.save(sinhVien);
//    }
    @Override
    @Transactional
    public void update(SinhVien sinhVien) {
        String hashedPassword = passwordEncoder.encode(sinhVien.getPassword());
        sinhVien.setPassword(hashedPassword);
        sinhVienRepository.save(sinhVien);
    }

    @Override
    public void saveOrUpdate(SinhVien sinhVien) {
        String hashedPassword = passwordEncoder.encode(sinhVien.getPassword());
        sinhVien.setPassword(hashedPassword);
        sinhVienRepository.saveOrUpdate(sinhVien);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        sinhVienRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LopHoc> getAllLopHocs() {
        return sinhVienRepository.getAllLopHocs();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Khoa> getAllKhoas() {
        return sinhVienRepository.getAllKhoas();
    }

    @Override
    @Transactional(readOnly = true)
    public List<NganhDaoTao> getAllNganhDaoTaos() {
        return sinhVienRepository.getAllNganhDaoTaos();
    }

    @Override
    public List<SinhVien> searchSinhVienByTerm(String searchTerm) {
        return sinhVienRepository.searchByTerm(searchTerm);
    }

    @Override
    @Transactional
    public boolean registerSinhVien(SinhVien sinhVien) {
        if (!isValidEmail(sinhVien.getEmail())) {
            return false;
        }
        if (sinhVienRepository.isEmailExists(sinhVien.getEmail())) {
            return false;
        }
        try {
            sinhVienRepository.save(sinhVien);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    @Transactional
    public void save(SinhVien sinhVien) {
        // Kiểm tra nếu mật khẩu đã được băm
        if (!isPasswordEncoded(sinhVien.getPassword())) {
            String hashedPassword = passwordEncoder.encode(sinhVien.getPassword());
            sinhVien.setPassword(hashedPassword);
        }
        sinhVienRepository.save(sinhVien);
    }

    private boolean isPasswordEncoded(String password) {
        return password != null && password.startsWith("$2a$") && password.length() == 60;
    }

    private boolean isValidEmail(String email) {
        return email != null && email.endsWith("@ou.edu.vn");
    }

    @Override
    @Transactional
    public String uploadAvatar(MultipartFile file, int sinhVienId) {
        try {
            // Tải ảnh lên và lấy URL
            Map<String, Object> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String avatarUrl = (String) uploadResult.get("secure_url");

            // Cập nhật URL của ảnh vào cơ sở dữ liệu
            SinhVien sinhVien = sinhVienRepository.getSinhVienById(sinhVienId);
            if (sinhVien != null) {
                sinhVien.setAvatar(avatarUrl);
                sinhVienRepository.saveOrUpdate(sinhVien);
                return avatarUrl;
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
