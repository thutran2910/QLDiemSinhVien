/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tmt.controllers;

import com.tmt.pojo.Diem;
import com.tmt.pojo.DiemTrungBinh;
import com.tmt.pojo.HocKy;
import com.tmt.pojo.LoaiDiem;
import com.tmt.pojo.LopHoc;
import com.tmt.pojo.MonHoc;
import com.tmt.pojo.SinhVien;
import com.tmt.service.DiemService;
import com.tmt.service.LopHocService;
import com.tmt.service.MonHocService;
import com.tmt.service.SinhVienService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DiemController {

    private final DiemService diemService;
    private final MonHocService monHocService;
    private final LopHocService lopHocService;
    private final SinhVienService sinhVienService;

    @Autowired
    public DiemController(DiemService diemService, MonHocService monHocService, SinhVienService sinhVienService, LopHocService lopHocService) {
        this.diemService = diemService;
        this.monHocService = monHocService;
        this.sinhVienService = sinhVienService;
        this.lopHocService = lopHocService;
    }
    

    @GetMapping("/diem") // Xem điểm GK, CK
    public String showScoresBySubject(@RequestParam(value = "monHocId", required = false, defaultValue = "0") int monHocId,
            @RequestParam(value = "lopHocId", required = false, defaultValue = "0") int lopHocId,
            Model model) {
        if (monHocId > 0 && lopHocId > 0) {
            List<Diem> diemList = diemService.getDiemByMonHocIdAndLopHocId(monHocId, lopHocId);
            List<MonHoc> monHocList = monHocService.getNameMonHoc();
            List<LopHoc> lopHocList = lopHocService.getNameLopHoc(); // Fixed service call
            model.addAttribute("diemList", diemList);
            model.addAttribute("monHocList", monHocList);
            model.addAttribute("lopHocList", lopHocList);
            model.addAttribute("selectedMonHocId", monHocId);
            model.addAttribute("selectedLopHocId", lopHocId);
        } else {
            model.addAttribute("error", "Both MonHocId and LopHocId are required");
            model.addAttribute("monHocList", monHocService.getNameMonHoc());
            model.addAttribute("lopHocList", lopHocService.getNameLopHoc()); // Ensure lopHocList is available
        }
        return "DanhSachDiem";
    }

    @GetMapping("/DanhSachDTB") // Danh sách điểm trung bình
public String getAllAverageScores(@RequestParam(value = "monHocId", required = false, defaultValue = "0") int monHocId,
                                   @RequestParam(value = "lopHocId", required = false, defaultValue = "0") int lopHocId,
                                   Model model) {
    List<Object[]> averageScores = null;
    
    // Vérifier si les paramètres sont valides avant de récupérer les scores
    if (monHocId > 0 && lopHocId > 0) {
        averageScores = diemService.getAllAverageScores(monHocId, lopHocId);
    }
    
    // Récupération des listes de matières et de classes
    List<MonHoc> monHocList = monHocService.getNameMonHoc();
    List<LopHoc> lopHocList = lopHocService.getNameLopHoc();
    
    // Ajouter les attributs au modèle
    model.addAttribute("monHocId", monHocId);
    model.addAttribute("lopHocId", lopHocId);
    model.addAttribute("averageScores", averageScores);
    model.addAttribute("monHocList", monHocList);
    model.addAttribute("lopHocList", lopHocList);
    
    // Retourner la vue JSP
    return "DanhSachDTB";
}


@GetMapping("/dtb") // Tra cứu điểm trung bình 
public String getAverageScore(@RequestParam(value = "sinhVienId", required = false) Integer sinhVienId,
                              @RequestParam(value = "monHocId", required = false) Integer monHocId,
                              @RequestParam(value = "lopHocId", required = false) Integer lopHocId,
                              Model model) {
    Double averageScore = null;
    if (sinhVienId != null && monHocId != null && lopHocId != null) {
        averageScore = diemService.getAverageScoreForStudent(sinhVienId, monHocId, lopHocId);
    }
    model.addAttribute("sinhVienId", sinhVienId);
    model.addAttribute("monHocId", monHocId);
    model.addAttribute("lopHocId", lopHocId);
    model.addAttribute("averageScore", averageScore);
    return "DTB"; 
}



    @GetMapping("/diem/form") // Form để nhập điểm
    public String showDiemForm(Model model) {
        model.addAttribute("diem", new Diem());
        model.addAttribute("sinhViens", diemService.getAllSinhViens());
        model.addAttribute("monHocs", diemService.getAllMonHocs());
        model.addAttribute("lopHocs", diemService.getAllLopHocs());
        model.addAttribute("hocKys", diemService.getAllHocKys());
        model.addAttribute("loaiDiems", diemService.getAllLoaiDiems());
        return "NhapDiem";
    }

    @PostMapping("/diem/save") // Lưu điểm
    public String saveDiem(@ModelAttribute Diem diem) {
        if (diem.getId() == 0) {
            diemService.saveDiem(diem);
        } else {
            diemService.updateDiem(diem);
        }
        return "redirect:/diem?monHocId=" + diem.getMonHoc().getId(); 
    }

    @GetMapping("/diem/export/csv") // Xuất csv điểm
    public void exportToCSV(@RequestParam(value = "monHocId", required = false, defaultValue = "0") int monHocId,
            @RequestParam(value = "lopHocId", required = false, defaultValue = "0") int lopHocId,
            HttpServletResponse response) throws IOException {
        List<Diem> diemList = diemService.getDiemByMonHocIdAndLopHocId(monHocId, lopHocId);

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=diem.csv");

        try (PrintWriter writer = response.getWriter()) {
            writer.println("Sinh vien,Lop hoc,Mon hoc,Diem,Loai diem");
            for (Diem diem : diemList) {
                writer.printf("%s,%s,%s,%s,%s%n",
                        diem.getSinhVien().getName(),
                        diem.getLopHoc().getName(),
                        diem.getMonHoc().getName(),
                        diem.getScore(),
                        diem.getLoaiDiem().getName());
            }
        }
    }

    @GetMapping("/diem/export/pdf") // Xuất pdf điểm
    public void exportDiemToPdf(HttpServletResponse response,
            @RequestParam("monHocId") int monHocId,
            @RequestParam("lopHocId") int lopHocId) throws IOException {
        List<Diem> diemList = diemService.getDiemByMonHocIdAndLopHocId(monHocId, lopHocId);
        diemService.exportDiemToPdf(response, diemList);
    }
    
@GetMapping("/exportAverageScoresToCsv") // Xuất csv điểm trung bình
public void exportAverageScoresToCsv(@RequestParam(value = "monHocId", required = false, defaultValue = "0") int monHocId,
                                     @RequestParam(value = "lopHocId", required = false, defaultValue = "0") int lopHocId,
                                     HttpServletResponse response) throws IOException {
    List<Object[]> averageScores = diemService.getAllAverageScores(monHocId, lopHocId);
    
    // Set the response headers
    response.setContentType("text/csv");
    response.setHeader("Content-Disposition", "attachment;filename=average_scores.csv");

    // Write data to CSV
    try (PrintWriter writer = response.getWriter()) {
        writer.println("Student ID,Student Name,Average Score");

        for (Object[] row : averageScores) {
            writer.printf("%s,%s,%.2f%n", row[0], row[1], row[2]);
        }
    }
}
@GetMapping("/exportAverageScoresToPdf")  // Xuất PDF dieedmerm trung bình
public void exportAverageScoresToPdf(@RequestParam(value = "monHocId", required = false, defaultValue = "0") int monHocId,
                                     @RequestParam(value = "lopHocId", required = false, defaultValue = "0") int lopHocId,
                                     HttpServletResponse response) throws DocumentException, IOException {
    List<Object[]> averageScores = diemService.getAllAverageScores(monHocId, lopHocId);
    
    // Set the response headers
    response.setContentType("application/pdf");
    response.setHeader("Content-Disposition", "attachment;filename=diemTB.pdf");

    // Create a new PDF document
    Document document = new Document();
    PdfWriter.getInstance(document, response.getOutputStream());

    document.open();

    // Add title
    document.add(new Paragraph("BANG DIEM TRUNG BINH "));
    document.add(new Paragraph(" "));

    // Create a table with 3 columns
    PdfPTable table = new PdfPTable(3);
    table.addCell("ID");
    table.addCell("Họ và tên");
    table.addCell("Điểm trung bình");

    // Add rows to the table
    for (Object[] row : averageScores) {
        table.addCell(String.valueOf(row[0]));
        table.addCell(String.valueOf(row[1]));
        table.addCell(String.format("%.2f", row[2]));
    }

    document.add(table);
    document.close();
}


}