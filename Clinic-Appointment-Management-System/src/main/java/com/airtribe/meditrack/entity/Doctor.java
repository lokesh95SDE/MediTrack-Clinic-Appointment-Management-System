package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.enums.Specialization;

public class Doctor extends Person{
    private Specialization specialisation ;
    private double consultationFees;

    public Doctor(String name, int age, int id, Specialization specialisation, double consultationFees) {
        super(name, age, id);
        this.consultationFees =consultationFees;
        this.specialisation = specialisation;
    }

}
