package com.airtribe.meditrack.Interface;

public interface BillingStrategy {
    double calculateTotal(double consultationFee, double additionalCharges);
}