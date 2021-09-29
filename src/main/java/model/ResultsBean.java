package model;

import db.DatabaseManager;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;
import static servlets.Utils.*;

@ManagedBean(name = "resultsBean")
@SessionScoped
public class ResultsBean implements Serializable {
    private Deque<Result> entries;
    private double x;
    private double y = 0.0;
    private double r = 2.0;

    public ResultsBean() {
        entries = new ArrayDeque<>();
        Session session = DatabaseManager.getInstance().getSession();
        List<Result> list = session.createQuery("SELECT * FROM results", Result.class).getResultList();
        entries.addAll(list);
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
        Date date = new Date();
        if (isValid(x, y, r)) {
            Result result = new Result(x, y, r, isInside(), date, new Date());
            entries.addFirst(result);
            Session session = DatabaseManager.getInstance().getSession();
            Transaction transaction = session.beginTransaction();
            session.save(result);
            transaction.commit();
//            System.out.println(entries);
        }
    }

    public void clear() {
        entries = new ArrayDeque<>();
        Session session = DatabaseManager.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        transaction.commit();
    }

    private boolean isInside() {
        return isInArea(x, y, r);
    }

    public LinkedList<Result> getEntries() {
        return new LinkedList<>(entries);
    }

    public void addEntry(Result result) {
        entries.addFirst(result);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (! (o instanceof ResultsBean)) return false;
        ResultsBean resultsBean1 = (ResultsBean) o;
        return Objects.equals(getEntries(), resultsBean1.getEntries());
    }

    @Override
    public String toString() {
        return "Entries{" +
                "entries=" + entries +
                '}';
    }
}
