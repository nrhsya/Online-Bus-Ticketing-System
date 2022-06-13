//class for Card DB related
package com.hasya.onlinebusticketingsystem;

public class Card {
    private String cardNum;
    private String expiryDate;
    private String cvv;

    //empty constructor
    public Card() {}

    public Card(String cardNum, String expiryDate, String cvv) {
        this.cardNum = cardNum;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
}
