package com.example.hotelmanage;

// import com.example.hotelmanage.Room;
// import com.example.hotelmanage.RoomStatus;
import com.example.hotelmanage.*;

public class HotelManagementSystemDemo {
    public static void run(){
        HotelManagementSystem hotelManagementSystem = HotelManagementSystem.getInstance();

        Guest g1 = new Guest("0001","RAMESH","arkos@gmail.com","9934567892");
        Guest g2 = new Guest("0002","SURESH","su4es@gmail.com","9303909309");
        hotelManagementSystem.addGuest(g1.getID(),g1);
        hotelManagementSystem.addGuest(g2.getID(), g2);

        Room r1 = new Room("001","DELUXE",2000,RoomStatus.AVAILABLE);
        Room r2 = new Room("002","DELUXE",2000,RoomStatus.AVAILABLE);
        hotelManagementSystem.addRoom(r1);
        hotelManagementSystem.addRoom(r2);

        String checkinDate = "01-01-2023";
        String checkoutDate = "03-01-2023";
        Reservation reservation = hotelManagementSystem.bookroom(g1,r1,checkinDate,checkoutDate);
        if(reservation != null){
            System.out.println("the reservation is done for the guest "+g1.getName());
        }
        else{
            System.out.println("the reservation is not done");
        }

        hotelManagementSystem.checkIN(reservation.getRoomID());
        Payment payment = new MethodPayment.CreditCard();
        payment.payment(r1.getPrice());

        hotelManagementSystem.checkout(reservation.getRoomID());

        hotelManagementSystem.cancelReservation(reservation.getRoomID());




    }
    public static void main(String[] args) {
        run();
        
    }
}