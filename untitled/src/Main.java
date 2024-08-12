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