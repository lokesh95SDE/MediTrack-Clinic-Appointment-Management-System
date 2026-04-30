package com.airtribe.meditrack.service;
import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.enums.Entities;
import com.airtribe.meditrack.util.DataStore;
import com.airtribe.meditrack.util.IdGenerator;

import java.util.ArrayList;
import java.util.List;

public class PatientService {

    private DataStore<Patient> patientStore = new DataStore<>();

    /**
     * POST Patient details
     * @param age name
     */
    public void addPatient(String name, int age) {
        int id = IdGenerator.getInstance().generateId(String.valueOf(Entities.PATIENT));
        Patient p = new Patient(name, age, id);
        patientStore.add(p);
    }

    /**
     * Get Patient list
     * @return
     */
    public List<Patient> getAllPatients(){
        return patientStore.getAll();
    }

    public Patient getPatientDetails(String name){
        for(Patient patient : patientStore.getAll()){
            if(patient.getName().equalsIgnoreCase(name)){
                return patient;
            }
        }
        return null;
    }

    /**
     * DELETE Patient
     * @param p
     */
    public void removePatient(Patient p){
        patientStore.remove(p);
    }


    /**
     * Polymorphism Method overriding
     * Search by Id
     * @param id
     * @return
     */
    public Patient searchPatient(int id) {
        for(Patient patient : patientStore.getAll()){
            if(patient.getId() == id) {
                return patient;
            }
        }
        return null;
    }

    /**
     * Polymorphism Method overriding
     * Search by Name
     * @param name
     * @return
     */
    public Patient searchPatient(String name) {
        for(Patient patient : patientStore.getAll()){
            if(patient.getName().equalsIgnoreCase(name)){
                return patient;
            }
        }
        return null;
    }

    /**
     * Polymorphism Method overriding
     * Search by Age
     * @param age
     * @param gender
     * @return
     */
    public List<Patient> searchPatient(int age, String gender) {
        List<Patient> ageList = new ArrayList<>();
        for(Patient patient : patientStore.getAll()){
            if(patient.getAge()== age){
                ageList.add(patient);
            }
        }
        return ageList;
    }

    public void viewAllPatients() {
        List<Patient> patients = getAllPatients();
        if (patients.isEmpty()) {
            System.out.println("No patients registered yet.");
            return;
        }
        System.out.println("\n--- All Patients ---");
        for (Patient patient : patients) {
            System.out.println("ID: " + patient.getId() + ", Name: " + patient.getName() + ", Age: " + patient.getAge());
        }
    }
}
