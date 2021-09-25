package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Result implements Serializable {
    public double x;
    public double y;
    public double r;
    public boolean result;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
    private Date queryTime;
    private Date resultTime;

    public Result(double x, double y, double r, boolean result, Date queryTime, Date resultTime) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.result = result;
        this.queryTime = queryTime;
        this.resultTime = resultTime;
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

    public String getQueryTime() {
        return simpleDateFormat.format(queryTime);
    }

    public void setQueryTime(Date queryTime) {
        this.queryTime = queryTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (! (o instanceof Result)) return false;
        Result result = (Result) o;
        return Double.compare(result.getX(), getX()) == 0 && Double.compare(result.getY(), getY()) == 0 && Double.compare(result.getR(), getR()) == 0 && isInside() == result.isInside() && Objects.equals(getQueryTime(), result.getQueryTime());
    }

    public long getResultTime() {
        return (resultTime.getTime() - queryTime.getTime());
    }

    public void setResultTime(Date resultTime) {
        this.resultTime = resultTime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY(), getR(), isInside(), getQueryTime());
    }

    @Override
    public String toString() {
        return "Entry{" +
                "x=" + x +
                ", y=" + y +
                ", r=" + r +
                ", result=" + result +
                ", queryTime=" + queryTime +
                '}';
    }

    public boolean getResult() {
        return result;
    }
}
