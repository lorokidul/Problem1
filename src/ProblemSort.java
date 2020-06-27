import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ProblemSort {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        System.out.println("Enter number of elements: ");
        int n = s.nextInt();
        int arr[] = new int[n];

        System.out.println("Enter elements: ");
        for (int i = 0; i < n; i++) {
            arr[i] = s.nextInt();
        }

        System.out.print("Array: [");
        for (int item : arr) {
            System.out.print(" " + item);
        }
        System.out.print(" ]");

        ArraySorter sorter = new ArraySorter();
        sorter.initMap(arr);
        sorter.sort();

    }
}

class Link {
    private int value;
    private int frequency;
    private Link next;

    public Link(int value, int frequency) {
        this.value = value;
        this.frequency = frequency;
    }

    public int getValue() {
        return this.value;
    }

    public int getFrequency() {
        return this.frequency;
    }

    public Link getNext() {
        return this.next;
    }

    public void setNext(Link link) {
        this.next = link;
    }

    public void printLink() {

        System.out.print("{" + value + "|" + frequency + "}->");

    }

}

class LinkList {

    private Link first = null;
    private int size = 0;

    public void LinkList() {
        first = null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void setLinks(Link prev, Link current, Link newLink) {
        if (prev == null) {
            newLink.setNext(first);
            first = newLink;
        } else {
            prev.setNext(newLink);
            newLink.setNext(current);
        }
    }

    public void insert(int val, int frequency) {
        Link newLink = new Link(val, frequency);
        Link prev = null;

        Link current = first;

        // sort by frequency
        while (current != null && frequency > current.getFrequency()) {
            prev = current;
            current = current.getNext();
        }
        if (current == null || frequency < current.getFrequency()) {
            setLinks(prev, current, newLink);
        } else {
            // sort by value within same frequency
            while (current != null && frequency == current.getFrequency() && val > current.getValue()) {
                prev = current;
                current = current.getNext();
            }

            setLinks(prev, current, newLink);


        }
        size++;
    }

    public void printList() {
        System.out.println("List (first-->last): ");
        Link current = first;
        while (current != null) {
            current.printLink();
            current = current.getNext();
        }
        System.out.println("NULL");
    }

}

class ArraySorter {

    public ArraySorter() {
    }

    HashMap<Integer, Integer> itemFreq = new HashMap<>();


    LinkList list = new LinkList();

    public void printMap() {
        System.out.println("\nItem-Frequency:");
        for (Map.Entry entry : itemFreq.entrySet()) {
            System.out.println(entry);
        }
        System.out.println("N unique elements:" + itemFreq.size());
    }

    public void initMap(int[] arr) {

        for (int item : arr) {
            Integer val = item;
            Integer frequency = 1;
            if (itemFreq.containsKey(val)) {
                Integer oldFreq = itemFreq.get(val);
                itemFreq.remove(val);
                itemFreq.put(val, oldFreq + 1);
            } else {
                itemFreq.put(item, frequency);
            }
        }

        printMap();

    }

    public void sort() {

        for (Map.Entry entry : itemFreq.entrySet()) {
            list.insert((int) entry.getKey(), (int) entry.getValue());
        }
        System.out.println("RESULT:");
        list.printList();
    }
}



