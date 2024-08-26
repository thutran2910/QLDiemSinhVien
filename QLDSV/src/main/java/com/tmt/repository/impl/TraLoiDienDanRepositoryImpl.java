/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tmt.repository.impl;

import com.tmt.pojo.TraLoiDienDan;
import com.tmt.repository.TraLoiDienDanRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class TraLoiDienDanRepositoryImpl implements TraLoiDienDanRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<TraLoiDienDan> getTraLoiByDienDan(int dienDanId) {
        Session session = this.sessionFactory.getCurrentSession();
        String hql = "FROM TraLoiDienDan t WHERE t.dienDan.id = :dienDanId";
        return session.createQuery(hql, TraLoiDienDan.class)
                .setParameter("dienDanId", dienDanId)
                .getResultList();
    }

    @Override
    public void addTraLoi(TraLoiDienDan traLoiDienDan) {
        this.sessionFactory.getCurrentSession().save(traLoiDienDan);
    }

    @Override
    public void updateTraLoi(TraLoiDienDan traLoiDienDan) {
        this.sessionFactory.getCurrentSession().update(traLoiDienDan);
    }

    @Override
    public void deleteTraLoi(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        TraLoiDienDan traLoiDienDan = session.get(TraLoiDienDan.class, id);
        if (traLoiDienDan != null) {
            session.delete(traLoiDienDan);
        }
    }
}
