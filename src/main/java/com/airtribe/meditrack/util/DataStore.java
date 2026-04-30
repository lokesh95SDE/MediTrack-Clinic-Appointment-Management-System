package com.airtribe.meditrack.util;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataStore<T> {

    private List<T> data = new ArrayList<>();

    public void add(T obj) {
        data.add(obj);
    }

    public List<T> getAll() {
        return data;
    }

    public void remove(T obj) {
        data.remove(obj);
    }
}