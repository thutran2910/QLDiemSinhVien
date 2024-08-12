/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tmt.repository.impl;

import com.tmt.pojo.VaiTro;
import com.tmt.repository.VaiTroRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class VaiTroRepositoryImpl implements VaiTroRepository {
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<VaiTro> getVaiTro() {
        Session session = this.factory.getObject().getCurrentSession();
        Query<VaiTro> query = session.createQuery("FROM VaiTro", VaiTro.class);
        return query.getResultList();
    }

    @Override
    public void saveVaiTro(VaiTro vaiTro) {
        Session session = this.factory.getObject().getCurrentSession();
        session.saveOrUpdate(vaiTro);
    }

    @Override
    public void deleteVaiTro(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        VaiTro vaiTro = session.get(VaiTro.class, id);
        if (vaiTro != null) {
            session.delete(vaiTro);
        }
    }
}
