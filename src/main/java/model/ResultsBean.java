package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;

public class ResultsBean implements Serializable {
    final Deque<Result> entries;
    SimpleDateFormat simpleDateFormat;

    public ResultsBean() {
        entries = new ArrayDeque<>();
        simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
    }

    public LinkedList<Result> getEntries() {
        return new LinkedList<>(entries);
    }

    public void addEntry(Result result) {
        entries.addFirst(result);
    }

    public SimpleDateFormat getSimpleDateFormat() {
        return simpleDateFormat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (! (o instanceof ResultsBean)) return false;
        ResultsBean resultsBean1 = (ResultsBean) o;
        return Objects.equals(getEntries(), resultsBean1.getEntries()) && Objects.equals(getSimpleDateFormat(), resultsBean1.getSimpleDateFormat());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEntries(), getSimpleDateFormat());
    }

    @Override
    public String toString() {
        return "Entries{" +
                "entries=" + entries +
                ", simpleDateFormat=" + simpleDateFormat +
                '}';
    }
}
