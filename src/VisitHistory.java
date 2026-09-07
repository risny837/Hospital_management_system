public class VisitHistory {
    private VisitNode head;

    public VisitHistory() {
        this.head = null;
    }

    public void addVisit(Visit visit) {
        if (visit == null) {
            System.out.println("Cannot add null visit.");
            return;
        }

        VisitNode newNode = new VisitNode(visit);

        if (head == null) {
            head = newNode;
        } else {
            VisitNode current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }

        System.out.println("Visit added to patient history: " + visit.getVisitId());
    }

    public boolean removeVisit(int visitId) {
        if (head == null) {
            System.out.println("No visit history available.");
            return false;
        }

        if (head.getVisit().getVisitId() == visitId) {
            head = head.getNext();
            System.out.println("Visit removed successfully.");
            return true;
        }

        VisitNode current = head;
        VisitNode previous = null;

        while (current != null && current.getVisit().getVisitId() != visitId) {
            previous = current;
            current = current.getNext();
        }

        if (current == null) {
            System.out.println("Visit not found with ID: " + visitId);
            return false;
        }

        previous.setNext(current.getNext());
        System.out.println("Visit removed successfully.");
        return true;
    }

    public Visit searchVisit(int visitId) {
        VisitNode current = head;

        while (current != null) {
            if (current.getVisit().getVisitId() == visitId) {
                return current.getVisit();
            }
            current = current.getNext();
        }

        return null;
    }

    public void displayVisits() {
        if (head == null) {
            System.out.println("Patient visit history is empty.");
            return;
        }

        System.out.println("\nPatient visit history:");
        VisitNode current = head;
        int count = 1;

        while (current != null) {
            System.out.println(count + ". " + current.getVisit());
            current = current.getNext();
            count++;
        }
    }

    public boolean isEmpty() {
        return head == null;
    }
}
