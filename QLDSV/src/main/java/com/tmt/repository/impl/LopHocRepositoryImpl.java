/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tmt.repository.impl;

import com.tmt.pojo.HocKy;
import com.tmt.pojo.LopHoc;
import com.tmt.pojo.SinhVien;
import com.tmt.repository.LopHocRepository;
import com.tmt.repository.SinhVienRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.query.Query;
import org.springframework.jdbc.core.JdbcTemplate;

@Repository
@Transactional
public class LopHocRepositoryImpl implements LopHocRepository {

    @Autowired
    private SessionFactory sessionFactory;

    private static final int PAGE_SIZE = 7;

    @Override
    public LopHoc findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(LopHoc.class, id);
    }

    @Override
    public List<LopHoc> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from LopHoc", LopHoc.class).list();
    }

    @Override
    public List<LopHoc> getNameLopHoc() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from LopHoc", LopHoc.class).list();
    }

    @Override
    public void save(LopHoc lopHoc) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(lopHoc);
    }

    @Override
    public void update(LopHoc lopHoc) {
        Session session = sessionFactory.getCurrentSession();
        session.update(lopHoc);
    }

    @Override
    public void deleteById(int id) {
        Session session = sessionFactory.getCurrentSession();
        LopHoc lopHoc = session.load(LopHoc.class, id);
        if (lopHoc != null) {
            session.delete(lopHoc);
        }
    }

    @Override
    public List<SinhVien> getStudentsByLopHocId(int lopHocId, int page, int pageSize) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "SELECT s FROM SinhVien s WHERE s.lopHoc.id = :lopHocId";
        Query<SinhVien> query = session.createQuery(hql, SinhVien.class)
                .setParameter("lopHocId", lopHocId);

        int offset = (page - 1) * pageSize;  // Calculate offset
        query.setFirstResult(offset);
        query.setMaxResults(pageSize);

        return query.getResultList();
    }

    @Override
    public int countStudentsByLopHocId(int lopHocId) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "SELECT COUNT(s.id) FROM SinhVien s WHERE s.lopHoc.id = :lopHocId";
        Query<Long> query = session.createQuery(hql, Long.class)
                .setParameter("lopHocId", lopHocId);
        return query.getSingleResult().intValue();
    }
}
