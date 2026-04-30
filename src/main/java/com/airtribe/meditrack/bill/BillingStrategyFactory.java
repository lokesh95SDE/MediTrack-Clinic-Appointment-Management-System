package com.airtribe.meditrack.bill;

import com.airtribe.meditrack.Interface.BillingStrategy;

public class BillingStrategyFactory {
    public static BillingStrategy getStrategy(int type) {

        return switch (type) {
            case 1 -> new NormalBillingStrategy();
            case 2 -> new InsuranceBillingStrategy(0.2);
            case 3 -> new DiscountBillingStrategy();
            default -> new NormalBillingStrategy();
        };
    }
}