package be.technifutur;

import be.technifutur.entities.Category;
import be.technifutur.entities.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("northwind");
//-------------------------------------------------------------------------------------------------------------------//
        EntityManager em = emf.createEntityManager();

        //GET ONE
        Product product = em.find(Product.class, 33L);

        System.out.println(product.getName());
        System.out.println(product.getCategory().getName());
        System.out.println(product.getSupplier().getCompanyName());

        //GET ALL
        List<Product> list = em.createQuery("SELECT p FROM Product p", Product.class).getResultList();
        list.forEach(p -> System.out.println(p.getName()));

        //UPDATE
        em.getTransaction().begin();
            product.setQttPerUnit("300g");
        em.getTransaction().commit();

        //INSERT
        Category cat = new Category();
        cat.setId(19L);
        cat.setName("Other");
        cat.setDescr("Products with default category");

        em.getTransaction().begin();
            em.persist(cat); //persist -> que insertion sinon crash
            //em.merge(cat); //merge -> insertion ou update en fctn de l'id
        em.getTransaction().commit(); //commit -> detache tt les entites

        //DELETE
        em.getTransaction().begin();
            cat = em.merge(cat); //merge renvoie une entite attachee
            em.remove(cat);      //via entite attachee
        em.getTransaction().commit();
//-------------------------------------------------------------------------------------------------------------------//
        emf.close();
    }
}