package model;

import db.DatabaseManager;
import mBeans.PointsCounter;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.management.*;
import java.io.Serializable;
import java.lang.management.ManagementFactory;
import java.util.*;

import static servlets.Utils.isInArea;
import static servlets.Utils.isValid;


@ManagedBean(name = "resultsBean", eager = true)
@ApplicationScoped
public class ResultsBeanPoints implements Serializable {


    private Deque<Result> entries;
    private double x;
    private double y = 0.0;
    private double r = 2.0;

    public ResultsBeanPoints() {
        entries = new ArrayDeque<>();
        entries.addAll(DatabaseManager.getInstance().getResultList());

    }


    private void clearDbWithEntries() {
        DatabaseManager.getInstance().clearResults();
    }

    public double getX() {
        return x;
    }


    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public void submitResult() {
        HuetaDlyaRegistration.getPcBean().incrementPointsCount();
        Date date = new Date();
        if (isValid(x, y, r)) {
            x = x * r / 2.0;
            x = Math.ceil(x * 10.0) / 10.0;
            y = y * r / 2.0;
            y = Math.ceil(y * 10.0) / 10.0;
            Result result = new Result(x, y, r, isInside(), date, new Date());
            save(result);
            entries.addFirst(result);
//            System.out.println(entries);
        }
    }

    private void save(Result result) {
        Session session = DatabaseManager.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(result);
        transaction.commit();
        System.out.println("Start of DM update");
        DatabaseManager.getInstance().addResults(result);
        System.out.println("Finish of DM update");
    }

    public void clear() {
        clearDbWithEntries();
        entries = new ArrayDeque<>();
    }

    private boolean isInside() {
        return isInArea(x, y, r);
    }

    public LinkedList<Result> getEntries() {
        if (entries.size() != DatabaseManager.getInstance().getResultList().size())
            entries = DatabaseManager.getInstance().getResultList();
        return new LinkedList<>(entries);
    }

    public void addEntry(Result result) {
        entries.addFirst(result);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResultsBeanPoints)) return false;
        ResultsBeanPoints resultsBean1 = (ResultsBeanPoints) o;
        return Objects.equals(getEntries(), resultsBean1.getEntries());
    }

    @Override
    public String toString() {
        return "Entries{" + "entries=" + entries + '}';
    }
}
