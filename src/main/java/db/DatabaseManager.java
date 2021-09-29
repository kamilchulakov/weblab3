package db;

import org.hibernate.MappingException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;

public class DatabaseManager {
    private static DatabaseManager dm;
    private Session session;

    public DatabaseManager() {
        try {
            Configuration configuration = new Configuration().configure();
            SessionFactory sessionFactory = configuration.buildSessionFactory();
            session = sessionFactory.openSession();
        } catch (MappingException e) {
            System.out.println("Invalid Hibernate Mapping!");
            System.exit(1);
        }
    }

    public Session getSession() {
        return session;
    }

    public synchronized static DatabaseManager getInstance() {
        if (dm == null) dm = new DatabaseManager();
        return dm;
    }
}
