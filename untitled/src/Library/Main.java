package Library;

import java.util.Date;
import java.util.Scanner;
import java.util.List;

public class Main {
    private List<Library> allBranches;
    private List<Subscriber> allSubscribers;

    public Main() {
        this.allBranches = Library.loadAllBranches();
        this.allSubscribers = Subscriber.loadAllSubscribers();
    }
    public static void main(String[] args) {
        // split this main to admin and user
        String str;
        Main libraryApp = new Main();
        Scanner sc = new Scanner(System.in);
        System.out.print("Welcome to our library!\n");
        do {
            System.out.println("Type what you want: \n1. Login\n2. Register\n3. Quit");
            str = sc.nextLine();
            int choice = Character.getNumericValue(str.charAt(0));
            switch (choice) {
                case 1 -> {
                    // Login
                    if (libraryApp.handleLogin(sc)) {
                        System.out.println("Login successful");
                        libraryApp.directToMainPage(sc);
                    } else {
                        System.out.println("Login unsuccessful. Try to register first or check your password.");
                    }
                }
                case 2 -> {
                    // Register
                    if (libraryApp.handleRegister(sc)) {
                        System.out.println("Register successful. Please login now.");
                    }
                }
                case 3 -> str = "quit";
                default -> System.out.println("Invalid option. Please try again.");
            }
        } while ("quit".compareTo(str) != 0);
        System.out.println("Thanks for visiting our library");
    }

    //login
    private boolean handleLogin(Scanner sc) {
        System.out.print("Enter your email: ");
        String email = sc.nextLine().trim();
        System.out.print("Enter your password: ");
        String password = sc.nextLine();

        Subscriber foundSubscriber = null;

        // Iterate over the list of subscribers to find the one with the matching email
        for (Subscriber subscriber : allSubscribers) {
            if (subscriber.getEmail().equalsIgnoreCase(email)) {
                foundSubscriber = subscriber;
                break;
            }
        }

        // Check if a subscriber was found and if the password matches
        if (foundSubscriber != null && foundSubscriber.getPassword().equals(password)) {
            System.out.println("Login successful! Welcome back, " + foundSubscriber.getName());
            return true;
        } else {
            System.out.println("Login failed. No subscriber found with the given email or wrong password.");
            return false;
        }
    }

    // Handle user registration
    private boolean handleRegister(Scanner sc) {
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        System.out.print("Enter your address: ");
        String address = sc.nextLine();
        System.out.print("Enter your phone number: ");
        String phoneNumber = sc.nextLine();
        System.out.print("Enter your email: ");
        String email = sc.nextLine();
        System.out.print("Enter your password: ");
        String password = sc.nextLine();
        System.out.print("Enter your type (regular/golden): ");
        String type = sc.nextLine();

        Subscriber subscriber = new Subscriber(type, name, address, phoneNumber, email, password);
        allSubscribers.add(subscriber);
        System.out.println("New subscriber registered with Id: " + subscriber.getId());
        return true;
    }

    private void directToMainPage(Scanner sc) {
        LibraryView mainPage;
        boolean isAdmin = checkIfAdmin();
        if (isAdmin) {
            mainPage = new AdminMainPage();
        } else {
            mainPage = new UserMainPage(allBranches);
        }

        String option;
        do {
            mainPage.showMenu();
            System.out.print("Enter your choice: ");
            option = sc.nextLine();
            int choice = Integer.parseInt(option);
            mainPage.handleSelection(sc, choice);
        } while (!option.equals("4"));  // where 4 is the quit option
    }

    private boolean checkIfAdmin() {
        // TODO: implement the logic
        return false;
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
        System.out.print("Enter subscriber email: ");
        String s_email=sc.nextLine();
        System.out.print("Enter subscriber type(regular/golden):");
        String type=sc.nextLine();
      
        Subscriber subscriber = new Subscriber(type,name,address,phoneNumber,s_email);
        System.out.println("New subscriber created Id :"+subscriber.getId());
        
        //subscription start date
        Date subscriptionStartDate = subscriber.getSubscriptionStartDate(); 
        System.out.println("Subscription start date: " + subscriptionStartDate);
        
        //borrow period
        int borrowingPeriod=subscriber.getBorrowingPeriod();
        if(borrowingPeriod == 3){
            System.out.println("Borrowing period for"+" "+name+" : 3 months");
        } 
        else{
            System.out.println("Borrowing period for"+" "+name+" : 3 weeks");
        }
    }

    // Options
    private static void libraryOptions(Subscriber subscriber, Scanner sc) {
        LocalDate subscriptionStartDate = subscriber.getSubscriptionStartDate();
        System.out.println("Subscription start date: " + subscriptionStartDate);

        // Borrowing period
        int borrowingPeriod = subscriber.getBorrowingPeriod();
        if (borrowingPeriod == 3) {
            System.out.println("Borrowing period for " + subscriber.getName() + " : 3 months");
        } else {
            System.out.println("Borrowing period for " + subscriber.getName() + " : 3 weeks");
        }

        // To-read list
        subscriber.toReadList();

        System.out.println("Do you want to borrow a book? (yes/no)");
        String borrowResponse = sc.nextLine();
        if (borrowResponse.equalsIgnoreCase("yes")) {
            System.out.println("Enter the name of the book you want to borrow: ");
            String bookName = sc.nextLine();
            subscriber.borrow(bookName);
        } else {
            System.out.println("No book borrowed.");
        }

        // Send reminder if due date is approaching
        subscriber.sendReminder();
    }

}
 */
