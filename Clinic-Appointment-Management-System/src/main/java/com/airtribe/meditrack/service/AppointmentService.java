package com.airtribe.meditrack.service;
import com.airtribe.meditrack.Interface.Observer;
import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.enums.AppointmentStatus;
import com.airtribe.meditrack.enums.Entities;
import com.airtribe.meditrack.exception.AppointmentNotFoundException;
import com.airtribe.meditrack.util.DateUtil;
import com.airtribe.meditrack.util.IdGenerator;


import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppointmentService {

    private Map<Integer, Appointment> appointments = new HashMap<>();
    private Observer observersNotification = new NotificationService();
    private Observer EmailNotification = new EmailNotificationService();
    private Observer SmsNotification = new SmsNotificationService();
    /**
     * POST Appointment details
     * @param
     */
    public void createAppointment(Patient p, Doctor d, LocalDate date) {
        int id = IdGenerator.getInstance().generateId(String.valueOf(Entities.APPOINTMENT));
        Appointment a = new Appointment(
                id,
                p,
                d,
                AppointmentStatus.CONFIRMED,
                date
        );
        appointments.put(id, a);
        a.addObserver(observersNotification);
        a.addObserver(EmailNotification);
        a.addObserver(SmsNotification);
        a.confirmNotification();
    }

    /**
     * GET Appointment list
     */
    public void viewAppointments() {
        for (Appointment a : appointments.values()) {
            System.out.println(a.getId() + " " + a.getPatient().getName() + " " + a.getDoctor().getName());
        }
    }

    public void getAppointmentsByPatient(String name) {
        for (Appointment a : appointments.values()) {
            if (a.getPatient().getName().equalsIgnoreCase(name)) {
                System.out.println(a.getId() + " " + a.getPatient().getName() + " " + a.getDoctor().getName());
            }
        }
    }

    public Appointment getAppointmentById(String id) {
        for (Appointment a : appointments.values()) {
            if (String.valueOf(a.getId()).equalsIgnoreCase(id)) {
                return a;
            }
        }
        return null;
        }

    public void viewAllAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("No appointments scheduled yet.");
            return;
        }
        System.out.println("\n--- All Appointments ---");
        for (Appointment app : appointments.values()) {
            System.out.println("ID: " + app.getId() +
                    ", Patient: " + app.getPatient().getName() +
                    ", Doctor: " + app.getDoctor().getName() +
                    ", Date: " + DateUtil.format(app.getDate()) +
                    ", Status: " + app.getStatus());
        }
    }

    /**
     * DELETE Appointment
     * @param id
     * @throws AppointmentNotFoundException
     */
    public void cancelAppointment(int id) throws AppointmentNotFoundException {
        if (!appointments.containsKey(id)) {
            throw new AppointmentNotFoundException("Not found");
        }
        Appointment a = appointments.get(id);
        a.cancelNotification();
    }
}
