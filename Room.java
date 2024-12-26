package com.example.hotelmanage;


public class Room {

    private String ID;
    private String RoomType;
    private Integer price;
    private RoomStatus status;

    public Room(String ID, String RoomType, Integer price, RoomStatus status) {
        this.ID = ID;
        this.RoomType = RoomType;
        this.price = price;
        status = RoomStatus.AVAILABLE;
    }

    public String getID() {
        return ID;
    }

    public String getRoomType() {
        return RoomType;
    }

    public Integer getPrice() {
        return price;
    }

    public RoomStatus getStatus() {
        return status;
    }


    public synchronized void checkAvailabilty(String ID) {
        if (getStatus() == RoomStatus.AVAILABLE) {
            status = RoomStatus.UNAVAILABLE;
        } else {
            System.out.println("the room is not available");
        }
    }

    public synchronized void checkIn(String ID) {
        if (getStatus() == RoomStatus.UNAVAILABLE) {
            status = RoomStatus.BOOKED;
        } else {
            System.out.println("the booking is not done ");
        }
    }

    public synchronized void checkOut(String ID) {
        if (getStatus() == RoomStatus.BOOKED) {
            status = RoomStatus.AVAILABLE;
        } else {
            System.out.println("the checkout is not completed");
        }

    }

    public void setStatus(RoomStatus roomStatus) {
        this.status = roomStatus;
    }
}