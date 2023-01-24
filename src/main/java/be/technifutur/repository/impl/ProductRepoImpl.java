package be.technifutur.repository.impl;

import be.technifutur.entities.Product;
import be.technifutur.repository.ProductRepository;
import be.technifutur.utils.EMFSharer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

public class ProductRepoImpl extends AbstractCrudRepoImpl<Product, Long> implements ProductRepository {
    private final EntityManager em;
    public ProductRepoImpl() {
        super(Product.class);
        this.em = super.em;
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
