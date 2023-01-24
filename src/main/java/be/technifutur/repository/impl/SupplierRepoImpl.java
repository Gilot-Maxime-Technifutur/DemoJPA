package be.technifutur.repository.impl;

import be.technifutur.entities.Supplier;
import be.technifutur.repository.SupplierRepository;
import be.technifutur.utils.EMFSharer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.Optional;

public class SupplierRepoImpl implements SupplierRepository {
    private final EntityManager em = EMFSharer.getInstance().createEntityManager();

    @Override
    public Optional<Supplier> getOne(Long id) {
        Supplier p = em.find(Supplier.class, id);

        em.clear();
        return Optional.ofNullable(p);
    }

    @Override
    public List<Supplier> getAll() {
        List<Supplier> list = em.createQuery("SELECT p FROM Supplier p", Supplier.class).getResultList();

        em.clear();
        return list;
    }

    @Override
    public void add(Supplier toInsert) {
        em.getTransaction().begin();
        em.persist(toInsert);
        em.getTransaction().commit();
    }

    @Override
    public void update(Supplier updated, Long id) {
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
            Supplier p = em.find(Supplier.class, id);
            if(p != null)
                em.remove(p);
        em.getTransaction().commit();
    }

    @Override
    public List<Supplier> getAllFrom(String region) {
        String qlQuerry = """
                SELECT s
                FROM Supplier s 
                WHERE s.region = ?1
                """;
        TypedQuery<Supplier> query = em.createQuery(qlQuerry, Supplier.class);
        query.setParameter(1, region);
        List<Supplier> suppliers = query.getResultList();

        em.clear();
        return suppliers;
    }

    @Override
    public void giveVIPtoAllX(char firstLetter) {
        String qlQuerry = """
                SELECT s
                FROM Supplier s 
                WHERE s.companyName like ?1
                """;
        TypedQuery<Supplier> query = em.createQuery(qlQuerry, Supplier.class);
        query.setParameter(1, firstLetter + "%");
        List<Supplier> suppliers = query.getResultList();

        suppliers.forEach(this::giveVIP);

        em.clear();
    }

    @Override
    public void giveVIPtoAllXfrom(String city) {
        String qlQuerry = """
                SELECT s
                FROM Supplier s 
                WHERE s.city = ?1
                """;
        TypedQuery<Supplier> query = em.createQuery(qlQuerry, Supplier.class);
        query.setParameter(1, city);
        List<Supplier> suppliers = query.getResultList();

        suppliers.forEach(this::giveVIP);

        em.clear();
    }

    @Override
    public void giveVIPtoAllXfromSQL(String city) {
        String qlQuerry = """
                UPDATE Supplier s
                SET s.companyName = CONCAT(s.companyName, ' VIP')
                WHERE s.city = ?1
                """;
        Query query = em.createQuery(qlQuerry);
        query.setParameter(1, city);

        em.getTransaction().begin();
            query.executeUpdate();
        em.getTransaction().commit();
    }

    @Override
    public void giveVIPtoAllXfromCriteria(String city){
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaUpdate<Supplier> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Supplier.class);

        Root<Supplier> root = criteriaUpdate.from(Supplier.class);
        criteriaUpdate.set("companyName", criteriaBuilder.concat(root.get("companyName"), " VIP"));
        criteriaUpdate.where(criteriaBuilder.equal(root.get("city"), city));

        em.getTransaction().begin();
            em.createQuery(criteriaUpdate).executeUpdate();
        em.getTransaction().commit();
    }

    private void giveVIP(Supplier s){
        String vip = s.getCompanyName();

        //*/mettre vip
        if(!vip.endsWith("VIP")) {
            s.setCompanyName(vip + " VIP");
            update(s, s.getId());
        }
        //*/
        /*/enlever vip
        if(vip.endsWith("VIP")) {
            s.setCompanyName(vip.substring(0, vip.length() - 3));
            update(s, s.getId());
        }
        //*/
    }
}
