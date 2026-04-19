package com.airtribe.meditrack.entity;

public class Person extends MedicalEntity{
    protected String name;
    protected  int age;
    protected int id;


    public Person(String name, int age, int id) {
        this.name = name;
        this.age = age;
        this.id =id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }


    @Override
    public void display() {
        System.out.println("Name : "+getName());
        System.out.println("Age : "+getAge());
        System.out.println("Id : "+id);
    }
}
