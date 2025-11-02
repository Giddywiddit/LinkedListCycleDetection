/**
 * LinkedListCycleDetection.java
 * ------------------------------------------------------
 * Educational Java implementation of Floyd's Cycle Detection Algorithm
 * (also known as the "Tortoise and Hare" algorithm)
 *
 * Author: Gideon Odutayo
 * License: MIT
 * Repository: https://github.com/Giddywddit/LinkedListCycleDetection
 */

class Node {
    String data;
    Node next;

    public Node(String data) {
        this.data = data;
        this.next = null;
    }
}

class LinkedList {
    Node head;

    public void add(String data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    public void createLoop(int position) {
        if (position <= 0 || head == null) {
            System.out.println("Invalid position for creating a loop.");
            return;
        }

        Node temp = head;
        Node loopNode = null;
        int count = 1;

        while (temp.next != null) {
            if (count == position) {
                loopNode = temp;
            }
            temp = temp.next;
            count++;
        }
        temp.next = loopNode;
        System.out.println("Loop created at node: " + loopNode.data);
    }

    public Node detectCycle() {
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                System.out.println("Cycle detected!");
                return findCycleStart(slow);
            }
        }
        System.out.println("No cycle detected.");
        return null;
    }

    private Node findCycleStart(Node intersection) {
        Node pointer1 = head, pointer2 = intersection;
        while (pointer1 != pointer2) {
            pointer1 = pointer1.next;
            pointer2 = pointer2.next;
        }
        return pointer1;
    }

    public void printCyclicList(Node cycleStart) {
        if (cycleStart == null) {
            System.out.println("No cycle detected.");
            return;
        }
        Node temp = head;
        do {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        } while (temp != cycleStart);
        System.out.println(temp.data + " (cycle starts here)");
    }

    public void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }
}

public class LinkedListCycleDetection {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");
        list.add("F");

        System.out.println("Initial List:");
        list.printList();

        list.createLoop(3);

        Node cycleStart = list.detectCycle();
        if (cycleStart != null) {
            System.out.println("Cycle starts at node: " + cycleStart.data);
        }

        System.out.println("Printing cyclic list:");
        list.printCyclicList(cycleStart);
    }
}
+