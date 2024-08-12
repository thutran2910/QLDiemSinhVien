/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tmt.repository.impl;

import com.tmt.pojo.NguoiDung;
import com.tmt.repository.NguoiDungRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
@Transactional
public class NguoiDungRepositoryImpl implements NguoiDungRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public List<NguoiDung> getAllNguoiDung() {
        Session session = sessionFactory.getObject().getCurrentSession();
        Query<NguoiDung> query = session.createQuery("FROM NguoiDung", NguoiDung.class);
        return query.getResultList();
    }

    @Override
    public NguoiDung getNguoiDungById(int id) {
        Session session = sessionFactory.getObject().getCurrentSession();
        return session.get(NguoiDung.class, id);
    }

    @Override
    public void addNguoiDung(NguoiDung nguoiDung) {
        Session session = sessionFactory.getObject().getCurrentSession();
        session.saveOrUpdate(nguoiDung);
    }

    @Override
    public void deleteNguoiDung(int id) {
        Session session = sessionFactory.getObject().getCurrentSession();
        NguoiDung nguoiDung = session.get(NguoiDung.class, id);
        if (nguoiDung != null) {
            session.delete(nguoiDung);
        }
    }
}
