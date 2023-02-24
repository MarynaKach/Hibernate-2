package dao;

import entity.Staff;
import org.hibernate.SessionFactory;

public class StaffDao extends GenericDao<Staff> {
    public StaffDao(SessionFactory sessionFactory) {
        super(Staff.class, sessionFactory);
    }
}
