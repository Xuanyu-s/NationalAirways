/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.national_airways;

/**
 *
 * @author Ace
 */
public class PassengerDetails {
   private final String fullName;
    private final int age;
    private final String ticketNumber;
    private final int luggageCount;
    private final String passengerType;
    private final String destination;
 
    private int queueNumber;
    private String luggageStatus;
    private String boardingStatus;
    private String noShowReason;
 
    public PassengerDetails(String fullName, int age, String ticketNumber, int luggageCount,
                             String passengerType, String destination) {
        this.fullName = fullName;
        this.age = age;
        this.ticketNumber = ticketNumber;
        this.luggageCount = luggageCount;
        this.passengerType = passengerType;
        this.destination = destination;
        this.luggageStatus = "Not Checked";
        this.boardingStatus = "Waiting";
        this.noShowReason = "N/A";
    }
 
    public String getFullName() { return fullName; }
    public int getAge() { return age; }
    public String getTicketNumber() { return ticketNumber; }
    public int getLuggageCount() { return luggageCount; }
    public String getPassengerType() { return passengerType; }
    public String getDestination() { return destination; }
 
    public int getQueueNumber() { return queueNumber; }
    public void setQueueNumber(int queueNumber) { this.queueNumber = queueNumber; }
 
    public String getLuggageStatus() { return luggageStatus; }
    public void setLuggageStatus(String luggageStatus) { this.luggageStatus = luggageStatus; }
 
    public String getBoardingStatus() { return boardingStatus; }
    public void setBoardingStatus(String boardingStatus) { this.boardingStatus = boardingStatus; }
 
    public String getNoShowReason() { return noShowReason; }
    public void setNoShowReason(String noShowReason) { this.noShowReason = noShowReason; }
}
