package com.airtribe.meditrack.service;
import com.airtribe.meditrack.entity.Patient;
import java.util.ArrayList;
import java.util.List;

public class PatientService {

    private List<Patient> patients = new ArrayList<>();

    public void addPatient(Patient p) {
        patients.add(p);
    }

    // 🔥 Method Overloading (Polymorphism)
    public Patient searchPatient(int id) {  }

    public List<Patient> searchPatient(String name) { }

    public List<Patient> searchPatient(int age, boolean flag) { }
}
