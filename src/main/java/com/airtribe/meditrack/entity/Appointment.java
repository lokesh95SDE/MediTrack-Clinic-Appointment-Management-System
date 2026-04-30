package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.Interface.Observer;
import com.airtribe.meditrack.Interface.ObserverSubject;
import com.airtribe.meditrack.enums.AppointmentStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Appointment implements ObserverSubject, Cloneable{

    private int id;
    private Patient patient;
    private Doctor doctor;
    private AppointmentStatus status;
    private LocalDate date;

    public List<Observer> observers = new ArrayList<>();

    public Appointment(int id, Patient patient, Doctor doctor, AppointmentStatus status, LocalDate date) {
        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.status = status;
        this.date = date;
    }

    @Override
    public Appointment clone() {
        return new Appointment(
                this.id,
                patient.clone(),
                doctor,
                status,
                date
                );
    }

    public LocalDate getDate() {
        return date;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer o : observers){
            o.update(message);
        }
    }

    public void confirmNotification() {
        this.status = AppointmentStatus.CONFIRMED;
        notifyObservers("Appointment CONFIRMED for " + patient.getName());
    }

    public void cancelNotification() {
        this.status = AppointmentStatus.CANCELLED;
        notifyObservers("Appointment CANCELLED for " + patient.getName());
    }

}
