package com.airtribe.meditrack.Interface;

public interface ObserverSubject {
     void addObserver(Observer o);

    void removeObserver(Observer o);

    void notifyObservers(String message);
}