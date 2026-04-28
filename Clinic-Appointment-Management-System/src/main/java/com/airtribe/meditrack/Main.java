package com.airtribe.meditrack;

import com.airtribe.meditrack.entity.*;
import com.airtribe.meditrack.enums.Specialization;
import com.airtribe.meditrack.service.AppointmentService;
import com.airtribe.meditrack.service.DoctorService;
import com.airtribe.meditrack.service.PatientService;
import com.airtribe.meditrack.util.DateUtil;
import com.airtribe.meditrack.bill.BillingStrategyFactory;
import com.airtribe.meditrack.bill.Bill;
import com.airtribe.meditrack.bill.BillSummary;
import com.airtribe.meditrack.Interface.BillingStrategy;
import com.airtribe.meditrack.exception.InvalidDataException;
import com.airtribe.meditrack.exception.AppointmentNotFoundException;

import java.time.LocalDate;
import java.util.*;

public class Main {

    private static final Scanner sc = new Scanner(System.in);
    private static DoctorService doctorService = new DoctorService();
    private static PatientService patientService = new PatientService();
    private static AppointmentService appointmentService = new AppointmentService();

    public static void main(String[] args) {

        // Sample data
        doctorService.addDoctor("Dr. M.S Selvaraj", 45, Specialization.CARDIOLOGIST, 600);
        doctorService.addDoctor("Dr. Priya Sharma", 38, Specialization.DERMATOLOGIST, 500);
        patientService.addPatient("Lokesh", 32);
        patientService.addPatient("Anjali", 28);

        while (true) {
            System.out.println("\n===== MEDITRACK CLINIC APPOINTMENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Doctor Management");
            System.out.println("2. Patient Management");
            System.out.println("3. Appointment Management");
            System.out.println("4. Billing");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            try {
                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        doctorMenu();
                        break;
                    case 2:
                        patientMenu();
                        break;
                    case 3:
                        appointmentMenu();
                        break;
                    case 4:
                        billingMenu();
                        break;
                    case 5:
                        System.out.println("Exiting MediTrack. Goodbye!");
                        sc.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private static void doctorMenu() {
        while (true) {
            System.out.println("\n===== DOCTOR MANAGEMENT =====");
            System.out.println("1. Add Doctor");
            System.out.println("2. View All Doctors");
            System.out.println("3. Back to Main Menu");
            System.out.print("Enter your choice: ");

            try {
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        addDoctor();
                        break;
                    case 2:
                        doctorService.viewAllDoctors();
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private static void addDoctor() {
        System.out.print("Enter Doctor Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Doctor Age: ");
        int age = Integer.parseInt(sc.nextLine());
        System.out.print("Enter Doctor Specialization (CARDIOLOGIST, DERMATOLOGIST, PEDIATRICIAN, NEUROLOGIST, ORTHOPEDIC): ");
        String specializationStr = sc.nextLine().toUpperCase();
        Specialization specialization = null;
        try {
            specialization = Specialization.valueOf(specializationStr);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid specialization. Please choose from the given options.");
            return;
        }
        System.out.print("Enter Consultation Fee: ");
        double consultationFee = Double.parseDouble(sc.nextLine());

            doctorService.addDoctor(name, age, specialization, consultationFee);
            System.out.println("Doctor added successfully!");
    }



    private static void patientMenu() {
        while (true) {
            System.out.println("\n===== PATIENT MANAGEMENT =====");
            System.out.println("1. Add Patient");
            System.out.println("2. View All Patients");
            System.out.println("3. Back to Main Menu");
            System.out.print("Enter your choice: ");

            try {
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        addPatient();
                        break;
                    case 2:
                        patientService.viewAllPatients();
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private static void addPatient() {
        System.out.print("Enter Patient Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Patient Age: ");
        int age = Integer.parseInt(sc.nextLine());
        patientService.addPatient(name, age);
        System.out.println("Patient added successfully!");
    }



    private static void appointmentMenu() {
        while (true) {
            System.out.println("\n===== APPOINTMENT MANAGEMENT =====");
            System.out.println("1. Create Appointment");
            System.out.println("2. View All Appointments");
            System.out.println("3. View Appointments by Patient");
            System.out.println("4. Cancel Appointment");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");

            try {
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        createAppointment();
                        break;
                    case 2:
                        appointmentService.viewAllAppointments();
                        break;
                    case 3:
                        viewAppointmentsByPatient();
                        break;
                    case 4:
                        cancelAppointment();
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            } catch (AppointmentNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void createAppointment() {
        patientService.viewAllPatients();
        if (patientService.getAllPatients().isEmpty()) {
            System.out.println("No patients available to create an appointment.");
            return;
        }
        System.out.print("Enter Patient Name for appointment: ");
        String patientName = sc.nextLine();
        Patient patient = patientService.searchPatient(patientName);
        if (patient == null) {
            System.out.println("Patient not found with ID: " + patientName);
            return;
        }

        doctorService.viewAllDoctors();
        if (doctorService.getAllDoctors().isEmpty()) {
            System.out.println("No doctors available to create an appointment.");
            return;
        }
        System.out.print("Enter Doctor ID for appointment: ");
        String doctorId = sc.nextLine();
        Doctor doctor = doctorService.getDoctorDetails(doctorId);
        if (doctor == null) {
            System.out.println("Doctor not found with ID: " + doctorId);
            return;
        }

        System.out.print("Enter Appointment Date (DD-MM-YYYY): ");
        String dateStr = sc.nextLine();
        LocalDate appointmentDate = DateUtil.parse(dateStr);
        if (appointmentDate == null) {
            System.out.println("Invalid date format. Please use DD-MM-YYYY.");
            return;
        }

        appointmentService.createAppointment(patient, doctor, appointmentDate);
        System.out.println("Appointment created successfully!");
    }



    private static void viewAppointmentsByPatient() {
        System.out.print("Enter Patient Name: ");
        String name = sc.nextLine();
        appointmentService.getAppointmentsByPatient(name);
    }

    private static void cancelAppointment() throws AppointmentNotFoundException {
        System.out.print("Enter Patient id: ");
        int id = Integer.parseInt(sc.nextLine());
        appointmentService.cancelAppointment(id);
    }

    private static void billingMenu() {
        while (true) {
            System.out.println("\n===== BILLING =====");
            System.out.println("1. Generate Bill for an Appointment");
            System.out.println("2. Back to Main Menu");
            System.out.print("Enter your choice: ");

            try {
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        generateBillForAppointment();
                        break;
                    case 2:
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private static void generateBillForAppointment() {


        System.out.print("Enter Appointment ID to generate bill: ");
        String appointmentId = sc.nextLine();
        Appointment appointment = appointmentService.getAppointmentById(appointmentId);

        if (appointment == null) {
            System.out.println("Appointment not found with ID: " + appointmentId);
            return;
        }

        System.out.print("Enter Additional Charges (e.g., for medicines, tests): ");
        double extraCharges = Double.parseDouble(sc.nextLine());

        System.out.println("Select Billing Type:");
        System.out.println("1. Normal");
        System.out.println("2. Insurance");
        System.out.println("3. Discount");
        System.out.print("Enter billing type choice: ");
        int billingTypeChoice = Integer.parseInt(sc.nextLine());

        BillingStrategy strategy = BillingStrategyFactory.getStrategy(billingTypeChoice);
        if (strategy == null) {
            System.out.println("Invalid billing type selected.");
            return;
        }

        Bill bill = new Bill(appointment.getId(), appointment.getPatient(), appointment.getDoctor(), appointment.getDoctor().getConsultationFees(),extraCharges, strategy);
        BillSummary summary = bill.generateBill();
        summary.printSummary();
    }
}
