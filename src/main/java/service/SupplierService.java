package service;

import entities.Supplier;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.List;

public class SupplierService {
    public void save(Supplier supplier) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(supplier);
        session.getTransaction().commit();
        session.close();
    }

    public boolean existsByName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Long count = session.createQuery(
                        "select count(s.id) from Supplier s where lower(s.name) = :name", Long.class)
                .setParameter("name", name.toLowerCase())
                .uniqueResult();
        session.close();
        return count != null && count > 0;
    }

    public List<Supplier> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Supplier> list = session.createQuery("from Supplier", Supplier.class).getResultList();
        session.close();
        return list;
    }

    public Supplier findById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Supplier s = session.get(Supplier.class, id);
        session.close();
        return s;
    }

    public void update(Supplier s) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(s);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(Supplier s) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(s);
        session.getTransaction().commit();
        session.close();
    }
}