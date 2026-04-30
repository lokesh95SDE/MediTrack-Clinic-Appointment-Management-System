package com.airtribe.meditrack.bill;

import com.airtribe.meditrack.Interface.BillingStrategy;
import com.airtribe.meditrack.contants.Constants;

public class InsuranceBillingStrategy implements BillingStrategy {
    private double coveragePercentage;

    public InsuranceBillingStrategy(double coveragePercentage) {
        this.coveragePercentage = coveragePercentage;
    }

    @Override
    public double calculateTotal(double consultationFee, double additionalCharges) {
        double subtotal = consultationFee + additionalCharges;

        double discount = subtotal * coveragePercentage;
        double afterDiscount = subtotal - discount;

        double tax = afterDiscount * Constants.TAX_RATE;
        return afterDiscount + tax;
    }
}
