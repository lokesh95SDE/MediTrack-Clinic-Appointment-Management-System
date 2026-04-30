package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.enums.Entities;
import com.airtribe.meditrack.enums.Specialization;
import com.airtribe.meditrack.exception.InvalidDataException;
import com.airtribe.meditrack.util.DataStore;
import com.airtribe.meditrack.util.IdGenerator;

import java.util.ArrayList;
import java.util.List;

public class DoctorService {

    private DataStore<Doctor> doctorStore = new DataStore<>();

    /**
     * POST Doctor details
     *
     * @param
     * @throws InvalidDataException
     */
    public void addDoctor(String name, int age, Specialization spec, double fee) {
        int id = IdGenerator.getInstance().generateId(String.valueOf(Entities.DOCTOR));
        Doctor d = new Doctor(name, age, id, spec, fee);
        doctorStore.add(d);
    }

    /**
     * GET Doctor list
     *
     * @return
     */
    public List<Doctor> getAllDoctors() {
        return doctorStore.getAll();
    }

    /**
     * DELETE Doctor
     *
     * @param doctor
     */
    public void deleteDoctor(Doctor doctor) {
        doctorStore.remove(doctor);
    }

    public Doctor getDoctorDetails(String name){
        for(Doctor doctor : doctorStore.getAll()){
            if(doctor.getName().equalsIgnoreCase(name)){
                return doctor;
            }
        }
        return null;
    }

    public Doctor getDoctorDetails(int id){
        for(Doctor doctor : doctorStore.getAll()){
            if(doctor.getId() == id){
                return doctor;
            }
        }
        return null;
    }

    /**
     * Search by Specializtion
     * @param specialization
     * @return
     */
    public List<Doctor> searchBySpecialization(String specialization) {
        List<Doctor> specialList = new ArrayList<>();
        for (Doctor doctor : doctorStore.getAll()) {
            if (doctor.getSpecialisation().name().equalsIgnoreCase(specialization)) {
                specialList.add(doctor);
            }
        }
        return specialList;
    }


    public void viewAllDoctors() {
        List<Doctor> doctors = getAllDoctors();
        if (doctors.isEmpty()) {
            System.out.println("No doctors registered yet.");
            return;
        }
        System.out.println("\n--- All Doctors ---");
        for (Doctor doctor : doctors) {
            System.out.println("ID: " + doctor.getId() + ", Name: " + doctor.getName() + ", Age: " + doctor.getAge() + ", Specialization: " + doctor.getSpecialisation() + ", Fee: " + doctor.getConsultationFees());
        }
    }
}
