package LinkedList;

public class RemoveNthNodeFromEnd {

    static class Node {
        int data;
        Node next;
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static Node removeNthNodeFromEnd(Node head, int n) {
        // Create a dummy node to handle edge cases like removing the head
        Node dummy = new Node(0);
        dummy.next = head;

        Node slow = dummy, fast = dummy;

        // Move the fast pointer so that there is a gap of n nodes between fast and slow
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }
        // Move both pointers until fast reaches the end of the list
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        // Skip the nth node from the end
        slow.next = slow.next.next;

        // Return the updated list's head
        return dummy.next;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head = removeNthNodeFromEnd(head, 3);
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
    }

}
