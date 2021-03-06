package model;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="results")
public class Result implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    public double x;
    public double y;
    public double r;
    public boolean result;
    @Transient
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
    private long query;
    private long time;

    public Result(double x, double y, double r, boolean result, Date query, Date time) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.result = result;
        this.query = query.getTime();
        this.time = time.getTime() - this.query;
    }

    public Result() {

    }

    public SimpleDateFormat getSimpleDateFormat() {
        return simpleDateFormat;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean isInside() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getQuery() {
        return simpleDateFormat.format(query);
    }

    public void setQuery(long queryTime) {
        this.query = queryTime;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long resultTime) {
        this.time = resultTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (! (o instanceof Result)) return false;
        Result result = (Result) o;
        return Double.compare(result.getX(), getX()) == 0 && Double.compare(result.getY(), getY()) == 0 && Double.compare(result.getR(), getR()) == 0 && isInside() == result.isInside() && Objects.equals(getQuery(), result.getQuery());
    }


    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY(), getR(), isInside(), getQuery());
    }

    @Override
    public String toString() {
        return "Entry{" +
                "x=" + x +
                ", y=" + y +
                ", r=" + r +
                ", result=" + result +
                ", queryTime=" + query +
                '}';
    }

    public boolean getResult() {
        return result;
    }
}
