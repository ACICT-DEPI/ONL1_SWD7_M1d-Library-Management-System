/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librarymangementapp;
import java.util.Date;

/**
 *
 * @author Admin
 */
class Subscriber {
    private static int id=0;
    private String type; 
    private String name;
    private String address;
    private String phone;
    private String s_email;
    private Date subscriptionStartDate;
    
    public Subscriber(String type,String name,String address,String phone,String s_email){
        setId();
        this.setType(type);
        this.setName(name);
        this.setAddress(address);
        this.setPhone(phone);
        this.setEmail(s_email);
        this.subscriptionStartDate = new Date();
    }
  
    public int getId(){
        return id;
    }
    private void setId(){
        Subscriber.id++;
    }
    
    public String getType(){
        return type;
    }
    private void setType(String type){
        this.type=type;
    }
    
    public String getName(){
        return name;
    }
    private void setName(String name){
        if(name==null || "".equals(name)){
            System.out.println("invalid name");
        }else{
            this.name=name;
        }
    }
    public String getAddress(){
        return address;
    }
    private void setAddress(String address){
        this.address=address;
    }

    public String getPhone(){
        return phone;
    }
    private void setPhone(String phone){
        if(phone==null || "".equals(phone) || phone.length()<11){
            System.out.println("invalid phone number");
        }else{
            this.phone=phone;
        }
    }
    
    public String getemail(){
        return s_email;
    }
    private void setEmail(String s_email){
       if(s_email==null || "".equals(s_email) || !(s_email.contains("@"))) {
           System.out.println("Invalid email");
       }else{
           this.s_email=s_email;
       }
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
            return 3;//3 months
        } else {
            return 3*7; //3 weeks
        }
    }
    
}
