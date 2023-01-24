package be.technifutur.repository;

import be.technifutur.entities.Product;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long>{
    List<Product> getUnitPriceBetween(double min, double max);
}
