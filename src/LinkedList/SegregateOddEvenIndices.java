package LinkedList;

public class SegregateOddEvenIndices {

    static class Node {
        int data;
        Node next;
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static Node segregateOddEvenIndices(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node odd = head;
        Node even = head.next;
        Node evenHead = even; // Anchor to the start of the even list

        while (even != null && even.next != null) {
            // Link odd nodes together
            odd.next = even.next;
            odd = odd.next;

            // Link even nodes together
            even.next = odd.next;
            even = even.next;
        }
        // Attach the even list to the tail of the odd list
        odd.next = evenHead;
        return head;
    }
}
