package dao;

import entity.Store;
import org.hibernate.SessionFactory;

public class StoreDao extends GenericDao<Store> {
    public StoreDao(SessionFactory sessionFactory) {
        super(Store.class, sessionFactory);
    }
}
