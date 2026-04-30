package com.airtribe.meditrack.bill;

import com.airtribe.meditrack.Interface.BillingStrategy;
import com.airtribe.meditrack.contants.Constants;

public class DiscountBillingStrategy implements BillingStrategy {

    @Override
    public double calculateTotal(double consultationFee, double additionalCharges) {
        double subtotal = consultationFee + additionalCharges;

        double discount = 100; // flat discount
        double afterDiscount = subtotal - discount;

        double tax = afterDiscount * Constants.TAX_RATE;
        return afterDiscount + tax;
    }
}