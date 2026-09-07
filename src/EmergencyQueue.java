public class EmergencyQueue {
    private QueueNode front;
    private QueueNode rear;

    public EmergencyQueue() {
        this.front = null;
        this.rear = null;
    }

    public void enqueue(Patient patient) {
        if (patient == null) {
            System.out.println("Cannot enqueue null patient.");
            return;
        }

        QueueNode newNode = new QueueNode(patient);

        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            rear.setNext(newNode);
            rear = newNode;
        }

        System.out.println("Patient added to emergency queue: " + patient.getName() + " (ID: " + patient.getPatientId() + ")");
    }

    public Patient dequeue() {
        if (isEmpty()) {
            System.out.println("Emergency queue is empty. No patient available for treatment.");
            return null;
        }

        Patient removedPatient = front.getPatient();
        front = front.getNext();

        if (front == null) {
            rear = null;
        }

        System.out.println("Called next patient for treatment: " + removedPatient.getName() + " (ID: " + removedPatient.getPatientId() + ")");
        return removedPatient;
    }

    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("Emergency queue is empty.");
            return;
        }

        System.out.println("\nEmergency queue (FIFO order):");
        QueueNode current = front;
        int position = 1;

        while (current != null) {
            System.out.println(position + ". " + current.getPatient());
            current = current.getNext();
            position++;
        }
    }

    public boolean isEmpty() {
        return front == null;
    }

    public Patient peek() {
        if (isEmpty()) {
            return null;
        }

        return front.getPatient();
    }
}
