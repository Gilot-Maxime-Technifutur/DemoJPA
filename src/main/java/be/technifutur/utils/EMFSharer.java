package be.technifutur.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public final class EMFSharer {

    // region SINGLETON
    private static EMFSharer instance;
    public static EMFSharer getInstance() {
        return instance == null ? instance = new EMFSharer() : instance;
//        if( instance == null ){
//            instance = new EMFSharer();
//        }
//
//        return instance;
    }
    private EMFSharer() {
        this.emf = Persistence.createEntityManagerFactory("northwind");
    }
    // endregion

    private final EntityManagerFactory emf;


    public EntityManagerFactory getEmf() {
        return emf;
    }

    public EntityManager createEntityManager(){
        return emf.createEntityManager();
    }

    public void close(){
        emf.close();
    }
}
