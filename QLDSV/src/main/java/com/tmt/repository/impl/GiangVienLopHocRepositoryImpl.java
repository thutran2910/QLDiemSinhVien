/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tmt.repository.impl;

import com.tmt.pojo.GiangVienLopHoc;
import com.tmt.repository.GiangVienLopHocRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class GiangVienLopHocRepositoryImpl implements GiangVienLopHocRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<GiangVienLopHoc> findByLopHocId(int lopHocId) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM GiangVienLopHoc WHERE lopHoc.id = :lopHocId";
        return session.createQuery(hql, GiangVienLopHoc.class)
                      .setParameter("lopHocId", lopHocId)
                      .list();
    }

    @Override
    public List<GiangVienLopHoc> findByGiangVienId(int giangVienId) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM GiangVienLopHoc WHERE giangVien.id = :giangVienId";
        return session.createQuery(hql, GiangVienLopHoc.class)
                      .setParameter("giangVienId", giangVienId)
                      .list();
    }

    @Override
    public void save(GiangVienLopHoc giangVienLopHoc) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(giangVienLopHoc);
    }

    @Override
    public void deleteById(int giangVienId, int lopHocId) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "DELETE FROM GiangVienLopHoc WHERE giangVien.id = :giangVienId AND lopHoc.id = :lopHocId";
        session.createQuery(hql)
               .setParameter("giangVienId", giangVienId)
               .setParameter("lopHocId", lopHocId)
               .executeUpdate();
    }
}
