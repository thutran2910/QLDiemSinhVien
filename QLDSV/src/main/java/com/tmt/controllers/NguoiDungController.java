//package com.btl.controllers;
//
//import com.btl.pojo.NguoiDung;
//import com.btl.service.NguoiDungService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/nguoidung")
//public class NguoiDungController {
//
//    @Autowired
//    private NguoiDungService nguoiDungService;
//
//    @GetMapping("/{id}")
//    public Optional<NguoiDung> getNguoiDungById(@PathVariable Integer id) {
//        return nguoiDungService.findById(id);
//    }
//
//    @GetMapping
//    public List<NguoiDung> getAllNguoiDung() {
//        return nguoiDungService.findAll();
//    }
//
//    @PostMapping
//    public NguoiDung createNguoiDung(@RequestBody NguoiDung nguoiDung) {
//        return nguoiDungService.save(nguoiDung);
//    }
//
//    @PutMapping("/{id}")
//    public NguoiDung updateNguoiDung(@PathVariable Integer id, @RequestBody NguoiDung nguoiDung) {
//        nguoiDung.setId(id);
//        return nguoiDungService.save(nguoiDung);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteNguoiDung(@PathVariable Integer id) {
//        nguoiDungService.deleteById(id);
//    }
//}
