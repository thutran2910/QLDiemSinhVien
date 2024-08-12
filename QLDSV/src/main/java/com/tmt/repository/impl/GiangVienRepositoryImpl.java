/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tmt.repository.impl;

import com.tmt.pojo.GiangVien;
import com.tmt.repository.GiangVienRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class GiangVienRepositoryImpl implements GiangVienRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public GiangVien findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(GiangVien.class, id);
    }

    @Override
    public List<GiangVien> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from GiangVien", GiangVien.class).list();
    }

    @Override
    public void save(GiangVien giangVien) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(giangVien);
    }

    @Override
    public void update(GiangVien giangVien) {
        Session session = sessionFactory.getCurrentSession();
        session.update(giangVien);
    }

    @Override
    public void deleteById(int id) {
        Session session = sessionFactory.getCurrentSession();
        GiangVien giangVien = session.load(GiangVien.class, id);
        if (giangVien != null) {
            session.delete(giangVien);
        }
    }
}
