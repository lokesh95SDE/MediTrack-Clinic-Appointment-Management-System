package com.airtribe.meditrack.entity;

public class Patient extends Person implements Cloneable {

    public Patient(String name, int age, int id) {
        super(name, age, id);
    }

    @Override
    public Patient clone(){
        return new Patient(this.name, this.age,this.id);
    }

}
