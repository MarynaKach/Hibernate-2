import dao.StoreDao;
import dao.*;
import entity.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class Main {
    private final SessionFactory sessionFactory;
    private ActorDao actorDao;
    private AddressDao addressDao;
    private CategoryDao categoryDao;
    private CityDao cityDao;
    private CountryDao countryDao;
    private CustomerDao customerDao;
    private FilmDao filmDao;
    private FilmTextDao filmTextDao;
    private InventoryDao inventoryDao;
    private LanguageDao languageDao;
    private PaymentDao paymentDao;
    private RentalDao rentalDao;
    private StaffDao staffDao;
    private StoreDao storeDao;

    public Main() {
        Properties properties = new Properties();
        properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
        properties.put(Environment.DRIVER, "com.p6spy.engine.spy.P6SpyDriver");
        properties.put(Environment.URL, "jdbc:p6spy:mysql://localhost:3306/movie");
        properties.put(Environment.USER, "root");
        properties.put(Environment.PASS, "qwerty");
        properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        properties.put(Environment.HBM2DDL_AUTO, "validate");
        sessionFactory = new Configuration()
                .addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Address.class)
                .addAnnotatedClass(Category.class)
                .addAnnotatedClass(City.class)
                .addAnnotatedClass(Country.class)
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Film.class)
                .addAnnotatedClass(FilmText.class)
                .addAnnotatedClass(Inventory.class)
                .addAnnotatedClass(Language.class)
                .addAnnotatedClass(Payment.class)
                .addAnnotatedClass(Rental.class)
                .addAnnotatedClass(Staff.class)
                .addAnnotatedClass(Store.class)
                .setProperties(properties)
                .buildSessionFactory();
        actorDao = new ActorDao(sessionFactory);
        addressDao = new AddressDao(sessionFactory);
        categoryDao = new CategoryDao(sessionFactory);
        cityDao = new CityDao(sessionFactory);
        countryDao = new CountryDao(sessionFactory);
        customerDao = new CustomerDao(sessionFactory);
        filmDao = new FilmDao(sessionFactory);
        filmTextDao = new FilmTextDao(sessionFactory);
        inventoryDao = new InventoryDao(sessionFactory);
        languageDao = new LanguageDao(sessionFactory);
        paymentDao = new PaymentDao(sessionFactory);
        rentalDao = new RentalDao(sessionFactory);
        staffDao = new StaffDao(sessionFactory);
        storeDao = new StoreDao(sessionFactory);
    }

    public static void main(final String[] args) throws Exception {
        Main main = new Main();
        Customer customer = main.createCustomer();
        main.customerReturnInventoryToStore();
        main.customerRentInventory(customer);
        main.newFilmMade();
    }

    private void newFilmMade() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Language language = languageDao.getItems(0, 20).stream()
                    .unordered().findAny().get();
            List<Actor> actors = actorDao.getItems(0, 10);
            List<Category> categories = categoryDao.getItems(0, 5);
            Film film = new Film();
            film.setActors(new HashSet<>(actors));
            film.setRating(Rating.NC17);
            film.setSpecialFeatures(Set.of(Feature.DELETE_SCENES, Feature.BEHIND_THE_SCENES));
            film.setLength((short) 123);
            film.setReplacementCost(BigDecimal.ZERO);
            film.setRentalRate(BigDecimal.ONE);
            film.setLanguage(language);
            film.setDescription("Best seller");
            film.setTitle("Me");
            film.setRentalDuration((byte) 35);
            film.setOriginalLanguage(language);
            film.setCategories(new HashSet<>(categories));
            film.setYear(Year.now());
            filmDao.save(film);

            FilmText filmText = new FilmText();
            filmText.setFilmId(film.getId());
            filmText.setFilm(film);
            filmText.setDescription(film.getDescription());
            filmText.setTitle("Me");
            filmTextDao.save(filmText);

            session.getTransaction().commit();
        }
    }

    private void customerRentInventory(Customer customer) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Film film = filmDao.getFirstAvailableFilm(customer);
            Store store = storeDao.getItems(0, 1).get(0);
            Inventory inventory = new Inventory();
            inventory.setFilm(film);
            inventory.setStore(store);
            inventoryDao.save(inventory);
            Staff staff = store.getStaff();
            Rental rental = new Rental();
            rental.setRentalDate(LocalDateTime.now());
            rental.setInventory(inventory);
            rental.setStaff(staff);
            rental.setCustomer(customer);
            rentalDao.save(rental);
            Payment payment = new Payment();
            payment.setCustomer(customer);
            payment.setPaymentDate(LocalDateTime.now());
            payment.setRental(rental);
            payment.setAmount(BigDecimal.valueOf(70.56));
            payment.setStaff(staff);
            paymentDao.save(payment);
            session.getTransaction().commit();
        }
    }

    private void customerReturnInventoryToStore() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Rental rental = rentalDao.getUnreturnedRental();
            rental.setReturnDate(LocalDateTime.now());
            rentalDao.save(rental);
            session.getTransaction().commit();
        }
    }

    private Customer createCustomer() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Store store = storeDao.getItems(0, 1).get(0);
            City city = cityDao.getByName("Baku");
            Address address = new Address();
            address.setAddress("Mira str., 29");
            address.setPhone("276 86 36");
            address.setCity(city);
            address.setDistrict("North");
            addressDao.save(address);
            Customer customer = new Customer();
            customer.setAddress(address);
            customer.setFirstName("John");
            customer.setLastName("Johnson");
            customer.setStore(store);
            customer.setActive(true);
            customer.setEmail("1234@gmail.com");
            customerDao.save(customer);
            session.getTransaction().commit();
            return customer;
        }
    }
}
