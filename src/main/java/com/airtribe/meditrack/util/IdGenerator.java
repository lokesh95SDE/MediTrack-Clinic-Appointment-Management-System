package com.airtribe.meditrack.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class IdGenerator {
    /**
     * Singleton IdGenerator created instance to use only onc instance throughout the application
     * Used AtomicInteger for thread safe
     */
    private static IdGenerator instance = new IdGenerator();
    private Map<String,AtomicInteger> counters = new HashMap<>();

    private IdGenerator() {}

    public static IdGenerator getInstance() {
        return instance;
    }

    public synchronized int generateId(String entityName) {
        counters.putIfAbsent(entityName, new AtomicInteger(1));
        return counters.get(entityName).getAndIncrement();
    }
}
