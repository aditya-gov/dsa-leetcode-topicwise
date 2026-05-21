package LinkedList;

/**
 * <a href="https://leetcode.com/problems/linked-list-cycle-ii/">...</a>
 */
public class CycleDetection2 {

    static class Node {
        int data;
        Node next;
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static int findCycleNode(Node head) {
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                Node slowPtr = head;
                while (slow != slowPtr) {
                    slow = slow.next;
                    slowPtr = slowPtr.next;
                }
                return slow.data;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        // 3,2,0,-4
        Node head = new Node(3);
        head.next = new Node(2);
        head.next.next = new Node(0);
        head.next.next.next = new Node(-4);
        head.next.next.next = head.next;
        System.out.println(findCycleNode(head));
    }
}
