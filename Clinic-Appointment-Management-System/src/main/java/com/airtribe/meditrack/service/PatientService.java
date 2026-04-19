package com.airtribe.meditrack.service;
import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.util.DataStore;
import com.airtribe.meditrack.util.IdGenerator;

import java.util.ArrayList;
import java.util.List;

public class PatientService {

    private DataStore<Patient> patientStore = new DataStore<>();

    /**
     * POST Patient details
     * @param p
     */
    public void addPatient(Patient p) {
        patientStore.add(p);
    }

    /**
     * Get Patient list
     * @return
     */
    public List<Patient> readAllPatientList(){
        return patientStore.getAll();
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
    public List<Patient> searchPatient(String name) {
        List<Patient> nameList = new ArrayList<>();
        for(Patient patient : patientStore.getAll()){
            if(patient.getName().equalsIgnoreCase(name)){
                nameList.add(patient);
            }
        }
        return nameList;
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
}
