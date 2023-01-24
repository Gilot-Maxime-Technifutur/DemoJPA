package be.technifutur.repository;

import be.technifutur.entities.Supplier;

import java.util.List;

public interface SupplierRepository extends CrudRepository<Supplier, Long> {
    List<Supplier> getAllFrom(String region);
    void giveVIPtoAllX(char firstLetter);
    void giveVIPtoAllXfrom(String city);
    void giveVIPtoAllXfromSQL(String city);
    void giveVIPtoAllXfromCriteria(String city);
}
