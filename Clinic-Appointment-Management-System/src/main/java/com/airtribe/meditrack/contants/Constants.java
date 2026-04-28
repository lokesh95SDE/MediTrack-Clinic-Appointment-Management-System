package com.airtribe.meditrack.contants;

import java.util.HashMap;
import java.util.Map;

public class Constants {

    public static final double TAX_RATE;
    public static final Map<String, String> FILE_PATHS = new HashMap<>();

    static {
        TAX_RATE = 0.18;

        FILE_PATHS.put("PATIENT", "data/patients.csv");
        FILE_PATHS.put("DOCTOR", "data/doctors.csv");
        FILE_PATHS.put("APPOINTMENT", "data/appointments.csv");
    }
}