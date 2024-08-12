import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String str = "";
        Scanner sc = new Scanner(System.in);
        do {
            str = sc.nextLine();
            System.out.println("Welcome to our library!");
        } while ("quit".compareTo(str) != 0);
    }
}