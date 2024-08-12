/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tmt.repository.impl;

import com.tmt.pojo.Diem;
import com.tmt.pojo.HocKy;
import com.tmt.pojo.Khoa;
import com.tmt.pojo.LoaiDiem;
import com.tmt.pojo.LopHoc;
import com.tmt.pojo.MonHoc;
import com.tmt.pojo.NganhDaoTao;
import com.tmt.pojo.SinhVien;
import com.tmt.repository.DiemRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class DiemRepositoryImpl implements DiemRepository {

    @Autowired
    private SessionFactory sessionFactory;

    private static final int PAGE_SIZE = 10;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Diem> getAllDiems() {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Diem> query = builder.createQuery(Diem.class);
        Root<Diem> root = query.from(Diem.class);
        query.select(root);
        Query<Diem> q = session.createQuery(query);
        return q.getResultList();
    }

    @Override
    public List<SinhVien> getAllSinhViens() {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<SinhVien> query = builder.createQuery(SinhVien.class);
        Root<SinhVien> root = query.from(SinhVien.class);
        query.select(root);
        Query<SinhVien> q = session.createQuery(query);
        return q.getResultList();
    }

    @Override
    public List<MonHoc> getAllMonHocs() {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<MonHoc> query = builder.createQuery(MonHoc.class);
        Root<MonHoc> root = query.from(MonHoc.class);
        query.select(root);
        Query<MonHoc> q = session.createQuery(query);
        return q.getResultList();
    }
    
        @Override
    public List<LopHoc> getAllLopHocs() {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<LopHoc> query = builder.createQuery(LopHoc.class);
        Root<LopHoc> root = query.from(LopHoc.class);
        query.select(root);
        Query<LopHoc> q = session.createQuery(query);
        return q.getResultList();
    }


    @Override
    public List<HocKy> getAllHocKys() {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<HocKy> query = builder.createQuery(HocKy.class);
        Root<HocKy> root = query.from(HocKy.class);
        query.select(root);
        Query<HocKy> q = session.createQuery(query);
        return q.getResultList();
    }

    @Override
    public List<LoaiDiem> getAllLoaiDiems() {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<LoaiDiem> query = builder.createQuery(LoaiDiem.class);
        Root<LoaiDiem> root = query.from(LoaiDiem.class);
        query.select(root);
        Query<LoaiDiem> q = session.createQuery(query);
        return q.getResultList();
    }

  @Override
public List<Diem> getDiemBySinhVienIdAndMonHocIdAndLopHocId(int sinhVienId, int monHocId, int lopHocId) {
    Session session = getSession();
    CriteriaBuilder builder = session.getCriteriaBuilder();
    CriteriaQuery<Diem> query = builder.createQuery(Diem.class);
    Root<Diem> root = query.from(Diem.class);
    query.select(root);
    Predicate predicateSinhVien = builder.equal(root.get("sinhVien").get("id"), sinhVienId);
    Predicate predicateMonHoc = builder.equal(root.get("monHoc").get("id"), monHocId);
    Predicate predicateLopHoc = builder.equal(root.get("lopHoc").get("id"), lopHocId);
    query.where(builder.and(predicateSinhVien, predicateMonHoc, predicateLopHoc));
    Query<Diem> q = session.createQuery(query);
    return q.getResultList();
}

@Override
public List<Diem> getDiemByMonHocIdAndLopHocId(int monHocId, int lopHocId) {
    Session session = getSession();
    CriteriaBuilder builder = session.getCriteriaBuilder();
    CriteriaQuery<Diem> query = builder.createQuery(Diem.class);
    Root<Diem> root = query.from(Diem.class);
    query.select(root);
    Predicate predicateMonHoc = builder.equal(root.get("monHoc").get("id"), monHocId);
    Predicate predicateLopHoc = builder.equal(root.get("lopHoc").get("id"), lopHocId);
    query.where(builder.and(predicateMonHoc, predicateLopHoc));
    Query<Diem> q = session.createQuery(query);
    return q.getResultList();
}
    
    @Override
    @Transactional
    public void saveDiem(Diem diem) {
        Session session = sessionFactory.getCurrentSession();
        session.save(diem);
    }

    @Override
    public void updateDiem(Diem diem) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.update(diem);
        transaction.commit();
    }

    @Override
    public void deleteDiem(int id) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        Diem diem = session.get(Diem.class, id);
        if (diem != null) {
            session.delete(diem);
        }
        transaction.commit();
    }
    
       @Override
    public List<Diem> getDiemByLoaiDiemId(int loaiDiemId) {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Diem> query = builder.createQuery(Diem.class);
        Root<Diem> root = query.from(Diem.class);
        query.select(root);
        Predicate predicateLoaiDiem = builder.equal(root.get("loaiDiem").get("id"), loaiDiemId);
        query.where(predicateLoaiDiem);
        Query<Diem> q = session.createQuery(query);
        return q.getResultList();
    }
     @Override
    public List<Diem> getDiemBySinhVienId(int sinhVienId) {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Diem> query = builder.createQuery(Diem.class);
        Root<Diem> root = query.from(Diem.class);
        query.select(root);
        Predicate predicateSinhVien = builder.equal(root.get("sinhVien").get("id"), sinhVienId);
        query.where(predicateSinhVien);
        Query<Diem> q = session.createQuery(query);
        return q.getResultList();
    }
}
