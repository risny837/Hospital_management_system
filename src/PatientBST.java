public class PatientBST {
    private PatientNode root;

    public PatientBST() {
        this.root = null;
    }

    public boolean insert(Patient patient) {
        if (patient == null) {
            return false;
        }

        if (search(patient.getPatientId()) != null) {
            System.out.println("Duplicate Patient ID detected. Patient ID " + patient.getPatientId() + " already exists.");
            return false;
        }

        root = insertNode(root, patient);
        return true;
    }

    private PatientNode insertNode(PatientNode current, Patient patient) {
        if (current == null) {
            return new PatientNode(patient);
        }

        if (patient.getPatientId() < current.getPatient().getPatientId()) {
            current.setLeft(insertNode(current.getLeft(), patient));
        } else if (patient.getPatientId() > current.getPatient().getPatientId()) {
            current.setRight(insertNode(current.getRight(), patient));
        }

        return current;
    }

    public Patient search(int patientId) {
        return searchNode(root, patientId);
    }

    private Patient searchNode(PatientNode current, int patientId) {
        if (current == null) {
            return null;
        }

        if (patientId == current.getPatient().getPatientId()) {
            return current.getPatient();
        }

        if (patientId < current.getPatient().getPatientId()) {
            return searchNode(current.getLeft(), patientId);
        }

        return searchNode(current.getRight(), patientId);
    }

    public boolean delete(int patientId) {
        PatientNode parent = null;
        PatientNode current = root;

        while (current != null && current.getPatient().getPatientId() != patientId) {
            parent = current;
            if (patientId < current.getPatient().getPatientId()) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }

        if (current == null) {
            return false;
        }

        if (current.getLeft() == null && current.getRight() == null) {
            if (parent == null) {
                root = null;
            } else if (parent.getLeft() == current) {
                parent.setLeft(null);
            } else {
                parent.setRight(null);
            }
        } else if (current.getLeft() == null || current.getRight() == null) {
            PatientNode child = (current.getLeft() != null) ? current.getLeft() : current.getRight();
            if (parent == null) {
                root = child;
            } else if (parent.getLeft() == current) {
                parent.setLeft(child);
            } else {
                parent.setRight(child);
            }
        } else {
            PatientNode successorParent = current;
            PatientNode successor = current.getRight();

            while (successor.getLeft() != null) {
                successorParent = successor;
                successor = successor.getLeft();
            }

            current.setPatient(successor.getPatient());

            if (successorParent.getLeft() == successor) {
                successorParent.setLeft(successor.getRight());
            } else {
                successorParent.setRight(successor.getRight());
            }
        }

        return true;
    }

    public void displayInOrder() {
        if (root == null) {
            System.out.println("No patients are currently registered.");
            return;
        }

        System.out.println("\nPatients in ascending Patient ID order:");
        inOrderTraversal(root);
    }

    private void inOrderTraversal(PatientNode node) {
        if (node == null) {
            return;
        }

        inOrderTraversal(node.getLeft());
        System.out.println(node.getPatient());
        inOrderTraversal(node.getRight());
    }

    public boolean isEmpty() {
        return root == null;
    }
}
