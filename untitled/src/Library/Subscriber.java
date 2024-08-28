package Library;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Subscriber {
    private static int nextId = 0; // TODO: fix the restart error
    private int id;
    private String type;
    private String name;
    private String address;
    private String phone;
    private String s_email;
    private String password;
    private LocalDate subscriptionStartDate;
    private ArrayList<String> toReadList;

    public Subscriber(String type, String name, String address, String phone, String s_email, String password) {
        this.setId();
        this.setType(type);
        this.setName(name);
        this.setAddress(address);
        this.setPhone(phone);
        this.setEmail(s_email);
        this.setPassword(password);
        this.subscriptionStartDate = LocalDate.now();
        this.toReadList = new ArrayList<>();
    }

    public Subscriber(String type, String name, String address, String phone,
                      String s_email, String password, LocalDate date, ArrayList<String> readList) {
        this.setId();
        this.setType(type);
        this.setName(name);
        this.setAddress(address);
        this.setPhone(phone);
        this.setEmail(s_email);
        this.setPassword(password);
        this.subscriptionStartDate = date;
        this.toReadList = readList;
    }

    public int getId(){
        return id;
    }

    private void setId(){
        this.id = nextId++;
    }

    public String getType(){
        return type;
    }

    private void setType(String type){
        this.type = type;
    }

    public String getName(){
        return name;
    }

    private void setName(String name){
        if (name == null || name.trim().isEmpty()){
            System.out.println("Invalid name");
        } else{
            this.name = name;
        }
    }

    public String getPassword(){
        return password;
    }

    private void setPassword(String pass){
        if (pass == null || pass.trim().isEmpty()){
            System.out.println("Invalid password");
        } else{
            this.password = pass;
        }
    }

    public String getAddress(){
        return address;
    }

    private void setAddress(String address){
        this.address = address;
    }

    public String getPhone(){
        return phone;
    }

    private void setPhone(String phone){
        if (phone == null || phone.length() < 11){
            System.out.println("Invalid phone number");
        } else{
            this.phone = phone;
        }
    }

    public String getEmail(){
        return s_email;
    }

    private void setEmail(String s_email){
        if (s_email == null || !s_email.contains("@")){
            System.out.println("Invalid email");
        } else{
            this.s_email = s_email;
        }
    }

    public LocalDate getSubscriptionStartDate(){
        return subscriptionStartDate;
    }

    //method to check if subscriber is golden or not
    public boolean isGolden(){
        return type.equalsIgnoreCase("golden");
    }

    //method to calculate borrowing period based on subscription type
    public int getBorrowingPeriod(){
        if (isGolden()) {
            return 3 ;//3 months
        } else{
            return 3 * 7;//3 weeks
        }
    }

    private LocalDate calculateReturnDate(){
        return LocalDate.now().plusDays(getBorrowingPeriod());
    }

    //calculate late fees
    public double calculateLateFees(LocalDate actualReturnDate){
        LocalDate returnDate = calculateReturnDate();
        long daysLate = ChronoUnit.DAYS.between(returnDate, actualReturnDate);
        if (daysLate > 0) {
            return daysLate * 2.0; //2$ per late day
        } else{
            return 0.0;
        }
    }

    //borrow method
    public void borrow(String bookName){
        LocalDate returnDate = calculateReturnDate();
        System.out.println("Borrowed book: \"" + bookName + "\"");
        System.out.println("Return date: " + returnDate);
        LocalDate actualReturnDate = LocalDate.now();
        double lateFees = calculateLateFees(actualReturnDate);
        if (lateFees > 0){
            System.out.println("Late fees: $"+lateFees);
        } else{
            System.out.println("No late fees");
        }
    }

    // to read list
    public void addToReadList(String bookName){
        toReadList.add(bookName);
        System.out.println("\"" + bookName + "\" has been added to your to-read list.");
    }

    public void toReadList(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to add a book to your to-read list? (yes/no)");
        String response = scanner.nextLine();
        if(response.equalsIgnoreCase("yes")){
            System.out.println("Enter the name of the book:");
            String bookName = scanner.nextLine();
            addToReadList(bookName);
        } else {
            System.out.println("No book was added to your to-read list.");
        }
    }

    public static List<Subscriber> loadAllSubscribers() {
        List<Subscriber> subscribers = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("subscribers.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", -1); // Split by comma, including empty values
                if (parts.length >= 8) { // Ensure there are enough columns
                    int id = Integer.parseInt(parts[0].trim());
                    String type = parts[1].trim();
                    String name = parts[2].trim();
                    String address = parts[3].trim();
                    String phone = parts[4].trim();
                    String email = parts[5].trim();
                    String password = parts[6].trim();
                    LocalDate subscriptionStartDate = LocalDate.parse(parts[7].trim());

                    // Handle multiple-value element (toReadList)
                    ArrayList<String> toReadList = new ArrayList<>();
                    if (parts.length > 8) {
                        String[] toReadItems = parts[8].split(";"); // Assuming ; separates items in the list
                        for (String item : toReadItems) {
                            toReadList.add(item.trim());
                        }
                    }

                    Subscriber subscriber = new Subscriber(type, name, address, phone, email, password,
                            subscriptionStartDate, toReadList);

                    // Manually set the ID and update nextId to avoid duplicates
                    subscriber.id = id;
                    if (id >= nextId) {
                        nextId = id + 1;
                    }

                    subscribers.add(subscriber);
                } else {
                    System.out.println("Skipping malformed line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while loading subscriber data: " + e.getMessage());
        }
        return subscribers;
    }

    //method to send a reminder email before the due date
    public void sendReminder() {
        LocalDate returnDate = calculateReturnDate();
        LocalDate reminderDate = returnDate.minusDays(3); //Send reminder 3 days before due date

        if (LocalDate.now().isAfter(reminderDate) && LocalDate.now().isBefore(returnDate)) {
            // Simulate sending an email
            System.out.println("Reminder: Your borrowed book is due on " + returnDate + ". Please return it on time to avoid late fees.");
            System.out.println("Sending reminder to " + s_email);
            //integrate with an email API here
        } else {
            System.out.println("No reminder needed at this time.");
        }
    }
}
