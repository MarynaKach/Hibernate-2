package dao;

import entity.FilmText;
import org.hibernate.SessionFactory;

public class FilmTextDao extends GenericDao<FilmText> {
    public FilmTextDao(SessionFactory sessionFactory) {
        super(FilmText.class, sessionFactory);
    }
}
