import MyPositionList.LinkedPositionalList;
import MyPositionList.Position;


public class Main {
    public static void main(String[] args) {
        LinkedPositionalList<Integer> list = new LinkedPositionalList<>();


        // Adding elements to the list
        Position<Integer> first = list.addFist(1);
        Position<Integer> second = list.addAfter(first, 2);
        Position<Integer> third = list.addAfter(second, 3);
        Position<Integer> fourth = list.addLast(4);

        // Printing the list
        System.out.println("Initial list:");
        printList(list);

        // Removing an element
        System.out.println("\nRemoving element at position 3:");
        list.remove(third);
        printList(list);

        // Modifying an element
        System.out.println("\nSetting element at position 2 to 10:");
        list.set(second, 10);
        printList(list);
    }

    private static void printList(LinkedPositionalList<Integer> list) {
        Position<Integer> trav = list.first();
        while(trav != null){
            System.out.print(trav.getElement() + " ");
            trav = list.after(trav);
        }
    }
}