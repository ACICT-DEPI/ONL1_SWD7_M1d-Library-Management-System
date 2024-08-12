import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // split this main to admin and subscriber
        String str = "";
        Scanner sc = new Scanner(System.in);
        System.out.print("Welcome to our library!\n");
        do {
            str = sc.nextLine();
        } while ("quit".compareTo(str) != 0);
    }
}
/*
    public static void main(String[] args) {
        //Subscriber
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter subscriber name:");
        String name=sc.nextLine();
        System.out.print("Enter subscriber address:");
        String address=sc.nextLine();
        System.out.print("Enter subscriber phone number:");
        String phoneNumber=sc.nextLine();
        System.out.print("Enter subscriber type (regular/golden):");
        String type=sc.nextLine();

        Subscriber subscriber = new Subscriber(getNextId(),type,name,address,phoneNumber);
        System.out.println("New subscriber created:");
        System.out.println(subscriber);
    }
    private static int nextId = 1;
    private static int getNextId() {
        return nextId++;
    }
 */