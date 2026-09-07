import java.util.Date;
import java.util.Scanner;

public class Main {
    private static final Scanner input = new Scanner(System.in);
    private static final PatientBST patientBST = new PatientBST();
    private static final EmergencyQueue emergencyQueue = new EmergencyQueue();
    private static final TreatmentStack treatmentStack = new TreatmentStack();
    private static int nextPatientId = 1001;
    private static int nextTreatmentId = 5001;
    private static int nextVisitId = 2001;

    public static void main(String[] args) {
        loadSampleData();

        while (true) {
            printMenu();
            int choice = readInt("Enter your choice: ");

            if (choice == 14) {
                System.out.println("Exiting the Mini Hospital Emergency System. Goodbye!");
                break;
            }

            switch (choice) {
                case 1:
                    registerPatient();
                    break;
                case 2:
                    searchPatient();
                    break;
                case 3:
                    deletePatient();
                    break;
                case 4:
                    patientBST.displayInOrder();
                    break;
                case 5:
                    addToEmergencyQueue();
                    break;
                case 6:
                    emergencyQueue.displayQueue();
                    break;
                case 7:
                    callNextPatient();
                    break;
                case 8:
                    completeTreatment();
                    break;
                case 9:
                    treatmentStack.displayStack();
                    break;
                case 10:
                    addPatientVisit();
                    break;
                case 11:
                    removePatientVisit();
                    break;
                case 12:
                    searchPatientVisit();
                    break;
                case 13:
                    displayAllPatientVisitHistory();
                    break;
                default:
                    System.out.println("Invalid menu choice. Please try again.");
                    break;
            }
        }

        input.close();
    }

    private static void printMenu() {
        System.out.println("\n========================================");
        System.out.println("MINI HOSPITAL EMERGENCY SYSTEM");
        System.out.println("========================================");
        System.out.println("1. Register New Patient");
        System.out.println("2. Search Patient");
        System.out.println("3. Delete Patient");
        System.out.println("4. Display All Patients");
        System.out.println("5. Add Patient to Emergency Queue");
        System.out.println("6. View Emergency Queue");
        System.out.println("7. Call Next Patient for Treatment");
        System.out.println("8. Complete Treatment");
        System.out.println("9. View Treatment History");
        System.out.println("10. Add Patient Visit");
        System.out.println("11. Remove Patient Visit");
        System.out.println("12. Search Patient Visit");
        System.out.println("13. Display Patient Visit History");
        System.out.println("14. Exit");
    }

    private static void loadSampleData() {
        System.out.println("Loading sample patient records...");

        patientBST.insert(new Patient(101, "Alice Johnson", 28, "0712345678", "Fever"));
        patientBST.insert(new Patient(105, "Bob Smith", 45, "0723456789", "Chest Pain"));
        patientBST.insert(new Patient(103, "Charlie Brown", 33, "0734567890", "Fracture"));
        patientBST.insert(new Patient(110, "Diana Lee", 19, "0745678901", "Asthma"));
        patientBST.insert(new Patient(102, "Ethan Green", 52, "0756789012", "High Blood Pressure"));

        patientBST.displayInOrder();

        emergencyQueue.enqueue(patientBST.search(105));
        emergencyQueue.enqueue(patientBST.search(102));
        emergencyQueue.enqueue(patientBST.search(110));
        emergencyQueue.displayQueue();

        patientBST.search(103).addVisit(new Visit(2001, new Date(), "Dr. Patel", "Fracture check", "Cast applied"));
        patientBST.search(103).addVisit(new Visit(2002, new Date(), "Dr. Kim", "Follow-up", "Physiotherapy"));

        treatmentStack.push(new TreatmentRecord(5001, 105, "Bob Smith", "Dr. Patel", "ECG and monitoring", new Date()));
        treatmentStack.push(new TreatmentRecord(5002, 110, "Diana Lee", "Dr. Kim", "Asthma inhaler", new Date()));
        treatmentStack.displayStack();
    }

    private static void registerPatient() {
        System.out.println("\nRegister New Patient");
        int id = readInt("Enter Patient ID: ");

        if (patientBST.search(id) != null) {
            System.out.println("Duplicate Patient ID. A patient with this ID already exists.");
            return;
        }

        String name = readNonEmptyString("Enter Patient Name: ");
        int age = readAge();
        String contact = readNonEmptyString("Enter Contact Number: ");
        String condition = readNonEmptyString("Enter Medical Condition: ");

        Patient patient = new Patient(id, name, age, contact, condition);
        if (patientBST.insert(patient)) {
            System.out.println("Patient registered successfully.");
        }
    }

    private static void searchPatient() {
        System.out.println("\nSearch Patient");
        int id = readInt("Enter Patient ID to search: ");
        Patient patient = patientBST.search(id);

        if (patient == null) {
            System.out.println("Patient not found with ID: " + id);
            return;
        }

        System.out.println("Patient found:");
        System.out.println(patient);
    }

    private static void deletePatient() {
        System.out.println("\nDelete Patient");
        int id = readInt("Enter Patient ID to delete: ");

        if (patientBST.delete(id)) {
            System.out.println("Patient with ID " + id + " deleted successfully.");
        } else {
            System.out.println("Patient with ID " + id + " was not found.");
        }
    }

    private static void addToEmergencyQueue() {
        System.out.println("\nAdd Patient to Emergency Queue");
        int id = readInt("Enter Patient ID: ");
        Patient patient = patientBST.search(id);

        if (patient == null) {
            System.out.println("Patient not found. Please register the patient first.");
            return;
        }

        emergencyQueue.enqueue(patient);
    }

    private static void callNextPatient() {
        if (emergencyQueue.isEmpty()) {
            System.out.println("Emergency queue is empty.");
            return;
        }

        emergencyQueue.dequeue();
    }

    private static void completeTreatment() {
        System.out.println("\nComplete Treatment");
        int patientId = readInt("Enter Patient ID: ");
        Patient patient = patientBST.search(patientId);

        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }

        String doctorName = readNonEmptyString("Enter Doctor Name: ");
        String treatment = readNonEmptyString("Enter Treatment/Diagnosis: ");

        TreatmentRecord record = new TreatmentRecord(nextTreatmentId++, patientId, patient.getName(), doctorName, treatment, new Date());
        treatmentStack.push(record);
    }

    private static void addPatientVisit() {
        System.out.println("\nAdd Patient Visit");
        int patientId = readInt("Enter Patient ID: ");
        Patient patient = patientBST.search(patientId);

        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }

        String doctorName = readNonEmptyString("Enter Doctor Name: ");
        String diagnosis = readNonEmptyString("Enter Diagnosis: ");
        String treatment = readNonEmptyString("Enter Treatment: ");

        Visit visit = new Visit(nextVisitId++, new Date(), doctorName, diagnosis, treatment);
        patient.addVisit(visit);
        System.out.println("Visit added successfully for patient " + patient.getName());
    }

    private static void removePatientVisit() {
        System.out.println("\nRemove Patient Visit");
        int patientId = readInt("Enter Patient ID: ");
        Patient patient = patientBST.search(patientId);

        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }

        int visitId = readInt("Enter Visit ID to remove: ");
        if (patient.getVisitHistory().removeVisit(visitId)) {
            System.out.println("Visit removed successfully.");
        }
    }

    private static void searchPatientVisit() {
        System.out.println("\nSearch Patient Visit");
        int patientId = readInt("Enter Patient ID: ");
        Patient patient = patientBST.search(patientId);

        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }

        int visitId = readInt("Enter Visit ID: ");
        Visit visit = patient.getVisitHistory().searchVisit(visitId);

        if (visit == null) {
            System.out.println("Visit not found.");
            return;
        }

        System.out.println("Visit found:");
        System.out.println(visit);
    }

    private static void displayAllPatientVisitHistory() {
        System.out.println("\nDisplay Patient Visit History");
        int patientId = readInt("Enter Patient ID: ");
        Patient patient = patientBST.search(patientId);

        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }

        patient.getVisitHistory().displayVisits();
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = input.nextLine().trim();

            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private static String readNonEmptyString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = input.nextLine().trim();

            if (!value.isEmpty()) {
                return value;
            }

            System.out.println("This field cannot be empty. Please enter a value.");
        }
    }

    private static int readAge() {
        while (true) {
            int age = readInt("Enter Age: ");
            if (age > 0 && age < 150) {
                return age;
            }
            System.out.println("Invalid age. Please enter an age between 1 and 149.");
        }
    }
}
