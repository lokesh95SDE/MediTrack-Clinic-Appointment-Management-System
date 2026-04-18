package com.airtribe.meditrack.service;
import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.exception.AppointmentNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class AppointmentService {

    private Map<Integer, Appointment> appointments = new HashMap<>();

    public void createAppointment(Appointment a) {
        appointments.put(a.getId(), a);
    }

    public void cancelAppointment(int id) throws AppointmentNotFoundException {
        if (!appointments.containsKey(id)) {
            throw new AppointmentNotFoundException("Not found");
        }
    }
}
