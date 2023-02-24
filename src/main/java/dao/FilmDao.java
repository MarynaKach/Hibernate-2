package dao;

import entity.Customer;
import entity.Film;
import org.hibernate.query.Query;
import org.hibernate.SessionFactory;

public class FilmDao extends GenericDao<Film> {
    public FilmDao(SessionFactory sessionFactory) {
        super(Film.class, sessionFactory);
    }

    public Film getFirstAvailableFilm(Customer customer) {
        Query<Film> query = getCurrentSession().createQuery("select f from Film f " +
                "where f.id not in (select distinct film.id from Inventory)", Film.class);
        query.setMaxResults(1);
        return query.getSingleResult();
    }
}
