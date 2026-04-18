package com.airtribe.meditrack.bill;

import com.airtribe.meditrack.contants.Constants;
import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Patient;

public class Bill implements Payable{
    private int billId;
    private Patient patient;
    private Doctor doctor;
    private double consultationFee;
    private double additionalCharges;

    public Bill(int billId, Patient patient, Doctor doctor, double consultationFee, double additionalCharges) {
        this.billId = billId;
        this.patient = patient;
        this.doctor = doctor;
        this.consultationFee = consultationFee;
        this.additionalCharges = additionalCharges;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public double getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(double consultationFee) {
        this.consultationFee = consultationFee;
    }

    public double getAdditionalCharges() {
        return additionalCharges;
    }

    public void setAdditionalCharges(double additionalCharges) {
        this.additionalCharges = additionalCharges;
    }

    @Override
    public BillSummary GenerateBill() {
        double subTotal = consultationFee+additionalCharges;
        double tax = subTotal * Constants.TAX_RATE;
        double total = tax + subTotal;
        return new BillSummary(billId, subTotal,tax, total);
    }

}
