package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;

public class Results implements Serializable {
    final Deque<Result> entries;
    SimpleDateFormat simpleDateFormat;

    public Results() {
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
        if (! (o instanceof Results)) return false;
        Results results1 = (Results) o;
        return Objects.equals(getEntries(), results1.getEntries()) && Objects.equals(getSimpleDateFormat(), results1.getSimpleDateFormat());
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
