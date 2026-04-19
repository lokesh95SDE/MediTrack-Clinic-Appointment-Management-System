package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.exception.InvalidDataException;
import com.airtribe.meditrack.util.DataStore;
import com.airtribe.meditrack.util.Validator;

import java.util.ArrayList;
import java.util.List;

public class DoctorService {

    private DataStore<Doctor> doctorStore = new DataStore<>();

    /**
     * POST Doctor details
     *
     * @param doctor
     * @throws InvalidDataException
     */
    public void addDoctor(Doctor doctor) throws InvalidDataException {
        Validator.validateName(doctor.getName());
        Validator.validateAge(doctor.getAge());
        doctorStore.add(doctor);
    }

    /**
     * GET Doctor list
     *
     * @return
     */
    public List<Doctor> readDoctorList() {
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
}
