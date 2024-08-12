/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.Date;

/**
 *
 * @author Admin
 */
class Subscriber {
    private int id;
    private String type;
    private String name;
    private String address;
    private String phone;
    private Date subscriptionStartDate; //for tracking subscription duration

    public Subscriber(int id,String type,String name,String address,String phone){
        this.id=id;
        this.type=type;
        this.name=name;
        this.address=address;
        this.phone=phone;
        this.subscriptionStartDate = new Date(); //set subscription start date on creation
    }
    public int getId(){
        return id;
    }
    public String getType(){
        return type;
    }
    public String getName(){
        return name;
    }
    public String getAddress(){
        return address;
    }
    public String getPhone(){
        return phone;
    }
    public Date getSubscriptionStartDate(){
        return subscriptionStartDate;
    }
    //method to check if subscriber is golden or not
    public boolean isGolden(){
        return type.equalsIgnoreCase("golden");
    }
    //method to calculate borrowing period based on subscription type
    public int getBorrowingPeriod(){
        if (isGolden()){
            return 3; //months
        } else {
            return 3; //weeks
        }
    }

}
