public class TreatmentStack {
    private StackNode top;

    public TreatmentStack() {
        this.top = null;
    }

    public void push(TreatmentRecord record) {
        if (record == null) {
            System.out.println("Cannot push a null treatment record.");
            return;
        }

        StackNode newNode = new StackNode(record);
        newNode.setNext(top);
        top = newNode;
        System.out.println("Treatment record pushed onto stack: " + record.getTreatmentId());
    }

    public TreatmentRecord pop() {
        if (isEmpty()) {
            System.out.println("Treatment stack is empty. No records to pop.");
            return null;
        }

        TreatmentRecord popped = top.getRecord();
        top = top.getNext();
        System.out.println("Popped latest treatment: " + popped.getTreatmentId());
        return popped;
    }

    public void displayStack() {
        if (isEmpty()) {
            System.out.println("Treatment stack is empty.");
            return;
        }

        System.out.println("\nTreatment history (LIFO order):");
        StackNode current = top;
        int count = 1;

        while (current != null) {
            System.out.println(count + ". " + current.getRecord());
            current = current.getNext();
            count++;
        }
    }

    public boolean isEmpty() {
        return top == null;
    }

    public TreatmentRecord peek() {
        if (isEmpty()) {
            return null;
        }

        return top.getRecord();
    }
}
