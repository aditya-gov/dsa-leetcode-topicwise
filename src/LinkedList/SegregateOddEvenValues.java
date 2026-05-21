package LinkedList;

public class SegregateOddEven {

    static class Node {
        int data;
        Node next;
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static Node segregateOddEvenValues(Node head) {
        Node evenDummy = new Node(0);
        Node oddDummy = new Node(0);
        Node evenTail = evenDummy;
        Node oddTail = oddDummy;

        Node curr = head;
        while (curr != null) {
            if (curr.data % 2 == 0) {
                evenTail.next = curr;
                evenTail = evenTail.next;
            } else {
                oddTail.next = curr;
                oddTail = oddTail.next;
            }
            curr = curr.next;
        }

        evenTail.next = oddDummy.next;
        oddTail.next = null;

        return evenDummy.next;
    }

    public static void printList(Node head) {
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
    }

    public static void main(String[] args) {
        // 0->1->4->6->9->10->11
        Node head = new Node(0);
        head.next = new Node(1);
        head.next.next = new Node(4);
        head.next.next.next = new Node(6);
        head.next.next.next.next = new Node(9);
        head.next.next.next.next.next = new Node(10);
        head.next.next.next.next.next.next = new Node(11);

        head = segregateOddEvenValues(head);
        printList(head);
    }
}
