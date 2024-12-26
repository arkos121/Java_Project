package com.example.hotelmanage;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import com.example.hotelmanage.*;

public class HotelManagementSystem {

    public static HotelManagementSystem hotelManagementSystem;
    private ConcurrentHashMap<String,Guest> guests;
    private ConcurrentHashMap<String,Room> rooms;
    private ConcurrentHashMap<String,Reservation> reservations;
    private Payment payment;

    private HotelManagementSystem() {
        guests = new ConcurrentHashMap<>();
        rooms =  new ConcurrentHashMap<>();
        reservations = new ConcurrentHashMap<>();
    }

    public static synchronized HotelManagementSystem getInstance() {
        if (hotelManagementSystem == null) {
            hotelManagementSystem = new HotelManagementSystem() ;
;
        }
        return hotelManagementSystem;
    }
    public void addGuest(String guestId, Guest guest) {
        this.guests.put(guestId, guest);
    }
    public Guest getGuest(String guestId) {
        return guests.get(guestId);
    }

    public void addRoom(Room room) {
        this.rooms.put(room.getID(), room);
    }

    public Room getRoom(String roomID) {
        return rooms.get(roomID);
    }

public synchronized Reservation bookroom(Guest guest, Room room, String checkInDate, String checkOutDate) {
       if(room.getStatus() == RoomStatus.AVAILABLE){
           Reservation reservation = new Reservation(guest.getID(), room.getID(), checkInDate, checkOutDate, ReservationStatus.CONFIRMED.toString());
           reservations.put(generateId(room.getID()),reservation);
           room.setStatus( RoomStatus.BOOKED);
           rooms.put(room.getID(), room);
           return reservation;
       }
       else{
           System.out.println("the room is not available");
           return null;
       }
    }

    public void cancelReservation(String ReservationID){
      if(reservations.containsKey(ReservationID)){
          reservations.get(ReservationID).cancelReservation();
      }
      else{
          System.out.println("the reservation is not found");
      }
    }

    public synchronized void checkIN(String ReservationID) {
        if (reservations.get(ReservationID) != null && reservations.get(ReservationID).getStatus() == ReservationStatus.CONFIRMED.toString()) {
            reservations.get(ReservationID).getRoomID();
           Room room = rooms.get(reservations.get(ReservationID).getRoomID());
           room.checkIn(room.getID());
        } else {
            System.out.println("the reservation is not found");
        }
    }

    public synchronized void checkout(String ReservationId){
        if(reservations.get(ReservationId) != null && reservations.get(ReservationId).getStatus() == ReservationStatus.CONFIRMED.toString()){
            Room room = rooms.get(reservations.get(ReservationId).getRoomID());
            double amount = room.getPrice();
            room.setStatus(RoomStatus.AVAILABLE);
            room.checkOut(room.getID());
            payment.payment(amount);
        }
        else{
            System.out.println("the reservation is not found");
        }
    }

    String generateId(String ID){
        return "RES" + UUID.randomUUID().toString().substring(0,8).toUpperCase();
    }


}
