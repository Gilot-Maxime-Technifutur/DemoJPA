package be.technifutur.repository.impl;

import be.technifutur.entities.Product;
import be.technifutur.repository.ProductRepository;
import be.technifutur.utils.EMFSharer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

public class ProductRepoImpl implements ProductRepository {
    private final EntityManager em = EMFSharer.getInstance().createEntityManager();

    @Override
    public Optional<Product> getOne(Long id) {
        Product p = em.find(Product.class, id);
        em.clear();
        return Optional.ofNullable(p);
    }

    @Override
    public List<Product> getAll() {
        List<Product> list = em.createQuery("SELECT p FROM Product p", Product.class).getResultList();
        em.clear();
        return list;
    }

    @Override
    public void add(Product toInsert) {
        em.getTransaction().begin();
            em.persist(toInsert);
        em.getTransaction().commit();
    }

    @Override
    public void update(Product updated, Long id) {
        if(getOne(id).isPresent()) {
            em.getTransaction().begin();
                em.merge(updated);
            em.getTransaction().commit();
        }else
            throw new IllegalArgumentException("element does not exist");
    }

    @Override
    public void remove(Long id) {
        em.getTransaction().begin();
            Product p = em.find(Product.class, id);
            if(p != null)
                em.remove(p);
        em.getTransaction().commit();
    }

    @Override
    public List<Product> getUnitPriceBetween(double min, double max) {
        String qlQuerry = """
                SELECT p
                FROM Product p 
                WHERE p.unitPrice BETWEEN ?1 AND ?2
                """;
        TypedQuery<Product> query = em.createQuery(qlQuerry, Product.class);
        query.setParameter(1, min);
        query.setParameter(2, max);
        List<Product> products = query.getResultList();
        em.clear();
        return products;
    }
}
