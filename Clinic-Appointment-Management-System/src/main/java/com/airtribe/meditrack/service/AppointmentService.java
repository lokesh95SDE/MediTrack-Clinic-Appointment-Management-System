package com.airtribe.meditrack.service;
import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.enums.AppointmentStatus;
import com.airtribe.meditrack.exception.AppointmentNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class AppointmentService {

    private Map<Integer, Appointment> appointments = new HashMap<>();

    /**
     * POST Appointment details
     * @param a
     */
    public void createAppointment(Appointment a) {
        appointments.put(a.getId(), a);
    }

    /**
     * GET Appointment list
     */
    public void viewAppointments() {
        for (Appointment a : appointments.values()) {
            System.out.println(a);
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
        appointments.get(id).setStatus(AppointmentStatus.CANCELLED);
    }
}
