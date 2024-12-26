package com.example.hotelmanage;

public class Reservation {
    private String guestID;
    private String roomID;
    private String checkInDate;
    private String checkOutDate;
    private String Status;
    private ReservationStatus reservationStatus;

    public Reservation(String guestID, String roomID, String checkInDate, String checkOutDate, String Status) {
        this.guestID = guestID;
        this.roomID = roomID;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.Status = Status;
    }

    public synchronized void cancelReservation() {
     if(getStatus() == ReservationStatus.CONFIRMED.toString()){
         Status = ReservationStatus.CANCELLED.toString();
     }
     else{
         System.out.println("the reservation is not cancelled");
     }
    }



    public String getCheckInDate() {
        return checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public String getStatus() {
        return Status;
    }

    public String getGuestID() {
        return guestID;
    }

    public String getRoomID() {
        return roomID;
    }

}


