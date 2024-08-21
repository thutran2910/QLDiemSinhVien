package com.tmt.repository.impl;

import com.tmt.pojo.Khoa;
import com.tmt.pojo.LopHoc;
import com.tmt.pojo.NganhDaoTao;
import com.tmt.pojo.SinhVien;
import com.tmt.repository.SinhVienRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import javax.persistence.criteria.Predicate;

@Repository
public class SinhVienRepositoryImpl implements SinhVienRepository {

    @Autowired
    private SessionFactory sessionFactory;

    private static final int PAGE_SIZE = 10;

    @Override
    public int countAll() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<SinhVien> root = query.from(SinhVien.class);
        query.select(builder.count(root));
        Query<Long> q = session.createQuery(query);
        Long count = q.getSingleResult();
        session.close();
        return count.intValue();
    }

    @Override
    public SinhVien getSinhVienById(int id) {
        Session session = sessionFactory.openSession();
        SinhVien sinhVien = session.get(SinhVien.class, id);
        session.close();
        return sinhVien;
    }

    @Override
    public List<SinhVien> findAll(int page, int pageSize) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<SinhVien> query = builder.createQuery(SinhVien.class);
        Root<SinhVien> root = query.from(SinhVien.class);
        query.select(root);

        Query<SinhVien> q = session.createQuery(query);
        int offset = (page - 1) * pageSize;
        q.setFirstResult(offset);
        q.setMaxResults(pageSize);
        List<SinhVien> sinhViens = q.getResultList();
        session.close();
        return sinhViens;
    }

    @Override
    public List<SinhVien> findByLopHocId(int lopHocId) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<SinhVien> query = builder.createQuery(SinhVien.class);
        Root<SinhVien> root = query.from(SinhVien.class);
        query.select(root);
        query.where(builder.equal(root.get("lopHoc").get("id"), lopHocId));

        Query<SinhVien> q = session.createQuery(query);
        List<SinhVien> sinhViens = q.getResultList();
        session.close();
        return sinhViens;
    }
    
    @Override
    public List<SinhVien> searchByTerm(String searchTerm) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<SinhVien> criteriaQuery = criteriaBuilder.createQuery(SinhVien.class);
        Root<SinhVien> sinhVienRoot = criteriaQuery.from(SinhVien.class);

        Predicate idPredicate = criteriaBuilder.like(sinhVienRoot.get("id").as(String.class), "%" + searchTerm + "%");
        Predicate namePredicate = criteriaBuilder.like(sinhVienRoot.get("name"), "%" + searchTerm + "%");
        Predicate combinedPredicate = criteriaBuilder.or(idPredicate, namePredicate);

        criteriaQuery.select(sinhVienRoot)
                .where(combinedPredicate);

        Query<SinhVien> query = session.createQuery(criteriaQuery);
        List<SinhVien> sinhViens = query.getResultList();

        return sinhViens;
    }

    @Override
    public void save(SinhVien sinhVien) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(sinhVien);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(SinhVien sinhVien) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(sinhVien);
        transaction.commit();
        session.close();
    }

    @Override
    public void saveOrUpdate(SinhVien sinhVien) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        if (sinhVien.getId() != null) {
            session.update(sinhVien);
        } else {
            session.save(sinhVien);
        }
        transaction.commit();
        session.close();
    }

    @Override
    public void deleteById(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        SinhVien sinhVien = session.get(SinhVien.class, id);
        if (sinhVien != null) {
            session.delete(sinhVien);
        }
        transaction.commit();
        session.close();
    }

    @Override
    public List<SinhVien> getNameSinhVien() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<SinhVien> query = builder.createQuery(SinhVien.class);
        Root<SinhVien> root = query.from(SinhVien.class);
        query.select(root);

        Query<SinhVien> q = session.createQuery(query);
        List<SinhVien> sinhViens = q.getResultList();
        session.close();
        return sinhViens;
    }

    @Override
    public List<LopHoc> getAllLopHocs() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<LopHoc> query = builder.createQuery(LopHoc.class);
        Root<LopHoc> root = query.from(LopHoc.class);
        query.select(root);

        Query<LopHoc> q = session.createQuery(query);
        List<LopHoc> lopHocs = q.getResultList();
        session.close();
        return lopHocs;
    }

    @Override
    public List<Khoa> getAllKhoas() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Khoa> query = builder.createQuery(Khoa.class);
        Root<Khoa> root = query.from(Khoa.class);
        query.select(root);

        Query<Khoa> q = session.createQuery(query);
        List<Khoa> khoas = q.getResultList();
        session.close();
        return khoas;
    }

    @Override
    public List<NganhDaoTao> getAllNganhDaoTaos() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<NganhDaoTao> query = builder.createQuery(NganhDaoTao.class);
        Root<NganhDaoTao> root = query.from(NganhDaoTao.class);
        query.select(root);

        Query<NganhDaoTao> q = session.createQuery(query);
        List<NganhDaoTao> nganhDaoTaos = q.getResultList();
        session.close();
        return nganhDaoTaos;
    }
}
