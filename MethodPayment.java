package com.example.hotelmanage;

public class MethodPayment{

public static class Cash implements Payment {
    @Override
    public boolean payment(double amount) {
        //through cash
        System.out.println("payment through cash");
        return true;

    }

}

public static class CreditCard implements Payment {
    @Override
    public boolean payment(double amount) {
        //through credit card
        System.out.println("payment through credit card");
        return true;
    }
}

}
