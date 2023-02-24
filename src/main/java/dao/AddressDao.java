package dao;

import entity.Address;
import org.hibernate.SessionFactory;

public class AddressDao extends GenericDao<Address> {
    public AddressDao(SessionFactory sessionFactory) {
        super(Address.class, sessionFactory);
    }
}
