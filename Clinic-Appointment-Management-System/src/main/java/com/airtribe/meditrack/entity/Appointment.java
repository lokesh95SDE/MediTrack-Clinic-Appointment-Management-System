package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.enums.AppointmentStatus;

public class Appointment implements Cloneable{

    private int id;
    private Patient patient;
    private Doctor doctor;
    private AppointmentStatus status;

    public Appointment(int id, Patient patient, Doctor doctor, AppointmentStatus status) {
        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.status = status;
    }

    @Override
    public Appointment clone() {
        return new Appointment(
                this.id,
                patient.clone(),
                doctor,
                status
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

    public int getId() {
        return id;
    }
}
