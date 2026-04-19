package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.enums.AppointmentStatus;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Appointment implements Cloneable{

    private int id;
    private Patient patient;
    private Doctor doctor;
    private AppointmentStatus status;
    private LocalDate date;

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
}
