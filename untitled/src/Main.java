import java.util.Date;
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
        System.out.print("Enter subscriber email: ");
        String s_email=sc.nextLine();
        System.out.print("Enter subscriber type(regular/golden):");
        String type=sc.nextLine();
      
        Subscriber subscriber = new Subscriber(type,name,address,phoneNumber,s_email);
        System.out.println("New subscriber created Id :"+subscriber.getId());
        
        //subscrbtion strt date 
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
