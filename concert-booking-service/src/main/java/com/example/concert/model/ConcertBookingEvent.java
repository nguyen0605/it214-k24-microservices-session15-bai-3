package com.example.concert.model;

public class ConcertBookingEvent {
    private String correlationId;
    private String concertCode;
    private String customerEmail;
    private int ticketQuantity;

    public String getCorrelationId() { return correlationId; }
    public void setCorrelationId(String correlationId) { this.correlationId = correlationId; }
    public String getConcertCode() { return concertCode; }
    public void setConcertCode(String concertCode) { this.concertCode = concertCode; }
    public String getCustomerEmail() { return customerEmail; }
    public void setCustomerEmail(String customerEmail) { this.customerEmail = customerEmail; }
    public int getTicketQuantity() { return ticketQuantity; }
    public void setTicketQuantity(int ticketQuantity) { this.ticketQuantity = ticketQuantity; }
}