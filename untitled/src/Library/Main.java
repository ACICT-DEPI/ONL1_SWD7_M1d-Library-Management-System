package Library;

import java.util.Date;
import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // split this main to admin and user
        String str;
        Scanner sc = new Scanner(System.in);
        System.out.print("Welcome to our library!\n");
        List<Library> allBranches = Library.loadAllBranches();
        do {
            System.out.println("Type what you want \n1 Login\n2 Register\n3 quit");
            str = sc.nextLine();
            int choice = Character.getNumericValue(str.charAt(0));
            switch (choice) {
                case 1:
                    // run the login function
                    System.out.println("Login");
                    if (Subscriber.handleLogin()) {
                        System.out.println("Login successful");
                    } else {
                        System.out.println("Login unsuccessful\n try to register first " +
                                "or make sure of your password");
                    }
                    break;
                case 2:
                    // run the register functions
                    System.out.println("Register");
                    if (Subscriber.handleRegister()) {
                        System.out.println("Register successful\nlogin now");
                    }
                    break;
                case 3:
                    str = "quit";
                    break;
            }
        } while ("quit".compareTo(str) != 0);
        System.out.println("Thanks for visiting our library");
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
}
 */
