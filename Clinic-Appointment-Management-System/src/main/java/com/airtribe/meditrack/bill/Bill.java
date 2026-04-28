package com.airtribe.meditrack.bill;

import com.airtribe.meditrack.Interface.BillingStrategy;
import com.airtribe.meditrack.Interface.Payable;
import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Patient;

public class Bill implements Payable {
    private int billId;
    private Patient patient;
    private Doctor doctor;
    private double consultationFee;
    private double additionalCharges;
    private BillingStrategy strategy;

    public Bill(int billId, Patient patient, Doctor doctor, double consultationFee, double additionalCharges, BillingStrategy strategy ) {
        this.billId = billId;
        this.patient = patient;
        this.doctor = doctor;
        this.consultationFee = consultationFee;
        this.additionalCharges = additionalCharges;
        this.strategy =strategy;
    }

    /**
     * Here we do Type casting + Strategy pattern calling
     * @return
     */
    @Override
    public BillSummary generateBill() {
        double consultationFee = doctor.getConsultationFees();
        double subtotal = consultationFee + additionalCharges;
        double totalDouble = strategy.calculateTotal(consultationFee, additionalCharges);
        int total = (int)totalDouble;
        double tax = total - subtotal;
        return new BillSummary(billId, subtotal,tax, total);
    }

}
