package db;

import model.Result;
import org.hibernate.MappingException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class DatabaseManager {
    private static DatabaseManager dm;
    private Session session;
//    private EntityManager entityManager;
    private Deque<Result> resultList = new LinkedList<>();

    public DatabaseManager() {
        try {
//            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Result");
//            if (entityManagerFactory != null) {
//                entityManager = entityManagerFactory.createEntityManager();
//                System.out.println(entityManager != null);
//            } else System.out.println("EntityManager is not working.");
//            session = entityManager.unwrap(Session.class);
            Configuration configuration = new Configuration().configure();
            SessionFactory sessionFactory = configuration.buildSessionFactory();
            session = sessionFactory.openSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Result> cq = cb.createQuery(Result.class);
            Root<Result> rootEntry = cq.from(Result.class);
            CriteriaQuery<Result> all = cq.select(rootEntry);
            TypedQuery<Result> allQuery = session.createQuery(all);
            resultList.addAll(allQuery.getResultList());
            Collections.reverse((LinkedList<Result>) resultList);
        } catch (MappingException e) {
            System.out.println("Invalid Hibernate Mapping!");
            System.exit(1);
        }
    }

    public synchronized Deque<Result> getResultList() {
        return resultList;
    }



    public synchronized void clearResults() {
        Session session = DatabaseManager.getInstance().getSession();
        session.beginTransaction();
        for (Result result: resultList) {
            session.delete(result);
        }
        session.getTransaction().commit();
        resultList.clear();
    }

    public synchronized void addResults(Result result) {
        resultList.addFirst(result);
    }

    public Session getSession() {
        return session;
    }

    public synchronized static DatabaseManager getInstance() {
        if (dm == null) dm = new DatabaseManager();
        return dm;
    }

    public static void destroyInstance() {
        dm.inInstanceDestroy();
        dm = null;
    }

    private void inInstanceDestroy() {
        session = null;
//        entityManager = null;
        resultList = null;
    }
}
