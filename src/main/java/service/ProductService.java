package service;

import entities.Product;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.List;

public class ProductService {
    public void save(Product product) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(product);
        session.getTransaction().commit();
        session.close();
    }

    public boolean existsByName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Long count = session.createQuery(
                        "select count(p.id) from Product p where lower(p.bookName) = :name", Long.class)
                .setParameter("name", name.toLowerCase())
                .uniqueResult();
        session.close();
        return count != null && count > 0;
    }

    public List<Product> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Product> list = session.createQuery("from Product", Product.class).getResultList();
        session.close();
        return list;
    }

    public Product findById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Product p = session.get(Product.class, id);
        session.close();
        return p;
    }

    public void update(Product p) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(p);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(Product p) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(p);
        session.getTransaction().commit();
        session.close();
    }
}