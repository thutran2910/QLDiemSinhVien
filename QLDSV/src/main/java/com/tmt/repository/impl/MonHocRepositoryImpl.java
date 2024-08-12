/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tmt.repository.impl;

import com.tmt.pojo.MonHoc;
import com.tmt.repository.MonHocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class MonHocRepositoryImpl implements MonHocRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public List<MonHoc> getNameMonHoc() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MonHoc> criteriaQuery = criteriaBuilder.createQuery(MonHoc.class);
        Root<MonHoc> root = criteriaQuery.from(MonHoc.class);
        criteriaQuery.select(root);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<MonHoc> findByGiangVienId(int giangVienId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MonHoc> criteriaQuery = criteriaBuilder.createQuery(MonHoc.class);
        Root<MonHoc> root = criteriaQuery.from(MonHoc.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("giangVienId"), giangVienId));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public MonHoc findById(int id) {
        return entityManager.find(MonHoc.class, id);
    }
}
