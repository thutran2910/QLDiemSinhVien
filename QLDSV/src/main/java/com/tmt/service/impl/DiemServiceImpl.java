package com.tmt.service.impl;

import com.tmt.pojo.Diem;
import com.tmt.pojo.DiemTrungBinh;
import com.tmt.pojo.HocKy;
import com.tmt.pojo.LoaiDiem;
import com.tmt.pojo.LopHoc;
import com.tmt.pojo.MonHoc;
import com.tmt.pojo.SinhVien;
import com.tmt.repository.DiemRepository;
import com.tmt.service.DiemService;
import com.itextpdf.text.Element;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DiemServiceImpl implements DiemService {

    private final DiemRepository diemRepository;

    @Autowired
    public DiemServiceImpl(DiemRepository diemRepository) {
        this.diemRepository = diemRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Diem> getAllDiems() {
        return diemRepository.getAllDiems();
    }

    @Override
    @Transactional(readOnly = true)
    public List<SinhVien> getAllSinhViens() {
        return diemRepository.getAllSinhViens();
    }

    @Override
    @Transactional(readOnly = true)
    public List<MonHoc> getAllMonHocs() {
        return diemRepository.getAllMonHocs();
    }

    @Override
    @Transactional(readOnly = true)
    public List<LopHoc> getAllLopHocs() {
        return diemRepository.getAllLopHocs();
    }

    @Override
    @Transactional(readOnly = true)
    public List<HocKy> getAllHocKys() {
        return diemRepository.getAllHocKys();
    }

    @Override
    @Transactional(readOnly = true)
    public List<LoaiDiem> getAllLoaiDiems() {
        return diemRepository.getAllLoaiDiems();
    }

    @Override
    @Transactional(readOnly = true)
    public void saveDiem(Diem diem) {
        diemRepository.saveDiem(diem);
    }

    @Override
    @Transactional(readOnly = true)
    public void updateDiem(Diem diem) {
        diemRepository.updateDiem(diem);
    }

    @Override
    @Transactional(readOnly = true)
    public void deleteDiem(int id) {
        diemRepository.deleteDiem(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Diem> getDiemBySinhVienIdAndMonHocIdAndLopHocId(int sinhVienId, int monHocId, int lopHocId) {
        return diemRepository.getDiemBySinhVienIdAndMonHocIdAndLopHocId(sinhVienId, monHocId, lopHocId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Diem> getDiemByMonHocIdAndLopHocId(int monHocId, int lopHocId) {
        return diemRepository.getDiemByMonHocIdAndLopHocId(monHocId, lopHocId);
    }

    @Override
    public void exportDiemToPdf(HttpServletResponse response, List<Diem> diemList) throws IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=DanhSachDiem.pdf");

        Document document = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            // Nội dung của tài liệu
            Paragraph title = new Paragraph("DANH SACH DIEM MON HOC");
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            PdfPTable table = new PdfPTable(5);
            table.addCell("Sinh vien");
            table.addCell("Lop hoc");
            table.addCell("Mon hoc");
            table.addCell("Diem");
            table.addCell("Loai diem");

            for (Diem diem : diemList) {
                table.addCell(diem.getSinhVien().getName());
                table.addCell(diem.getLopHoc().getName());
                table.addCell(diem.getMonHoc().getName());
                table.addCell(String.valueOf(diem.getScore()));
                table.addCell(diem.getLoaiDiem().getName());
            }

            document.add(table);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            document.close();  // Đóng tài liệu trong khối finally
        }
    }

 @Override
    public Double getAverageScoreForStudent(int sinhVienId, int monHocId, int lopHocId) {
        return diemRepository.getAverageScoreForStudent(sinhVienId, monHocId, lopHocId);
    }

    @Override
    public List<Object[]> getAllAverageScores(int monHocId, int lopHocId) {
        return diemRepository.getAllAverageScores( monHocId, lopHocId);
    }

    @Override
    public List<Object[]> getHighestAverageScoresByClass(int monHocId) {
          return diemRepository.getHighestAverageScoresByClass(monHocId);
    }
}
