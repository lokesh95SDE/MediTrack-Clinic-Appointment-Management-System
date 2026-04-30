package com.airtribe.meditrack.bill;
import com.airtribe.meditrack.Interface.BillingStrategy;
import com.airtribe.meditrack.contants.Constants;

public class NormalBillingStrategy implements BillingStrategy {

    @Override
    public double calculateTotal(double consultationFee, double additionalCharges) {
        double subtotal = consultationFee + additionalCharges;
        double tax = subtotal * Constants.TAX_RATE;
        return subtotal + tax;
    }
}
