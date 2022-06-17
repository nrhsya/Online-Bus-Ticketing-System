package com.hasya.onlinebusticketingsystem;

public class BookingHistoryModel {

    String boarding,dropping,date,time,bus,seatNo,ticketNo,fare;

    BookingHistoryModel()
    {

    }
    public BookingHistoryModel(String boarding, String dropping, String date, String time, String bus, String seatNo, String ticketNo, String fare) {
        this.boarding = boarding;
        this.dropping = dropping;
        this.date = date;
        this.time = time;
        this.bus = bus;
        this.seatNo = seatNo;
        this.ticketNo = ticketNo;
        this.fare = fare;
    }

    public String getBoarding() {
        return boarding;
    }

    public void setBoarding(String boarding) {
        this.boarding = boarding;
    }

    public String getDropping() {
        return dropping;
    }

    public void setDropping(String dropping) {
        this.dropping = dropping;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getBus() {
        return bus;
    }

    public void setBus(String bus) {
        this.bus = bus;
    }

    public String getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }

    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

    public String getFare() {
        return fare;
    }

    public void setFare(String fare) {
        this.fare = fare;
    }
}
