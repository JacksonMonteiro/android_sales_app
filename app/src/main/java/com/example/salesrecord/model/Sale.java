package com.example.salesrecord.model;

public class Sale {
    private String buyer;
    private String buyerCPF;
    private String saleDescription;
    private int saleValue;
    private int paidValue;
    private int currency;

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getBuyerCPF() {
        return buyerCPF;
    }

    public void setBuyerCPF(String buyerCPF) {
        this.buyerCPF = buyerCPF;
    }

    public String getSaleDescription() {
        return saleDescription;
    }

    public void setSaleDescription(String saleDescription) {
        this.saleDescription = saleDescription;
    }

    public int getSaleValue() {
        return saleValue;
    }

    public void setSaleValue(int saleValue) {
        this.saleValue = saleValue;
    }

    public int getPaidValue() {
        return paidValue;
    }

    public void setPaidValue(int paidValue) {
        this.paidValue = paidValue;
    }

    public int getCurrency() {
        return currency;
    }

    public void setCurrency(int currency) {
        this.currency = currency;
    }
}
