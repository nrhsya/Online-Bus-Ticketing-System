package com.hasya.onlinebusticketingsystem;

public class Payment {
    String name, age, paymentTotal, paymentMethod, paymentDate;

    Payment() {

    }

    public Payment(String name, String age, String paymentTotal, String paymentMethod, String paymentDate) {
        this.name = name;
        this.age = age;
        this.paymentTotal = paymentTotal;
        this.paymentMethod = paymentMethod;
        this.paymentDate = paymentDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPaymentTotal() {
        return paymentTotal;
    }

    public void setPaymentTotal(String paymentTotal) {
        this.paymentTotal = paymentTotal;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }
}
