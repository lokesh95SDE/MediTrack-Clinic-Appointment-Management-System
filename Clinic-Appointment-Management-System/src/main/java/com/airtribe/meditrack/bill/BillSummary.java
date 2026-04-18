package com.airtribe.meditrack.bill;

public record BillSummary(int billId, double subTotal, double tax, double total) {

    public void printSummary(){
        System.out.println("----- BILL SUMMARY -----");
        System.out.println("Bill ID: " + billId);
        System.out.println("Subtotal: " + subTotal);
        System.out.println("Tax: " + tax);
        System.out.println("Total: " + total);
    }
}
