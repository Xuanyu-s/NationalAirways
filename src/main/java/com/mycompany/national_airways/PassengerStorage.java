/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.national_airways;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.SwingUtilities;

/**
 *
 * @author Ace
 */
public class PassengerStorage {
    private static final PassengerStorage INSTANCE = new PassengerStorage();
     private static final DateTimeFormatter LOG_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
 
    private final List<PassengerDetails> passengers = new ArrayList<>();
    private final List<String> auditLogs = new ArrayList<>();
    private final List<Runnable> listeners = new ArrayList<>();
    private final AtomicInteger queueCounter = new AtomicInteger(0);
 
    private PassengerStorage() { }
 
    public static PassengerStorage getInstance() {
        return INSTANCE;
    }
 
    public synchronized int addPassenger(PassengerDetails passenger) {
        int queueNumber = queueCounter.incrementAndGet();
        passenger.setQueueNumber(queueNumber);
        passenger.setBoardingStatus("Waiting");
        passengers.add(passenger);
        notifyListeners();
        return queueNumber;
    }
 
    public synchronized List<PassengerDetails> getAllPassengers() {
        return Collections.unmodifiableList(new ArrayList<>(passengers));
    }
 
    public synchronized boolean ticketExists(String ticketNumber) {
        for (PassengerDetails p : passengers) {
            if (p.getTicketNumber().equalsIgnoreCase(ticketNumber)) {
                return true;
            }
        }
        return false;
    }
 
    public synchronized void updateBoardingStatus(int queueNumber, String status) {
        for (PassengerDetails p : passengers) {
            if (p.getQueueNumber() == queueNumber) {
                p.setBoardingStatus(status);
                break;
            }
        }
        notifyListeners();
    }
 
    public synchronized void updateLuggageStatus(int queueNumber, String status) {
        for (PassengerDetails p : passengers) {
            if (p.getQueueNumber() == queueNumber) {
                p.setLuggageStatus(status);
                break;
            }
        }
        notifyListeners();
    }
 
    public synchronized PassengerDetails getNextWaitingPassenger() {
        PassengerDetails next = null;
        for (PassengerDetails p : passengers) {
            if ("Waiting".equals(p.getBoardingStatus())) {
                if (next == null || p.getQueueNumber() < next.getQueueNumber()) {
                    next = p;
                }
            }
        }
        return next;
    }
 
    public synchronized PassengerDetails getCurrentBoardingPassenger() {
        for (PassengerDetails p : passengers) {
            if ("Now Boarding".equals(p.getBoardingStatus())) {
                return p;
            }
        }
        return null;
    }
 
    public synchronized void setNoShowReason(int queueNumber, String reason) {
        for (PassengerDetails p : passengers) {
            if (p.getQueueNumber() == queueNumber) {
                p.setNoShowReason(reason);
                break;
            }
        }
        notifyListeners();
    }
 
    public synchronized void recordAuditLog(String entry) {
        String timestamp = LocalDateTime.now().format(LOG_TIME_FORMAT);
        auditLogs.add("[" + timestamp + "] " + entry);
        notifyListeners();
    }
 
    public synchronized List<String> getAuditLogs() {
        return Collections.unmodifiableList(new ArrayList<>(auditLogs));
    }
 
    public void addListener(Runnable listener) {
        listeners.add(listener);
    }
 
    public void removeListener(Runnable listener) {
        listeners.remove(listener);
    }
 
    private void notifyListeners() {
        List<Runnable> snapshot = new ArrayList<>(listeners);
        for (Runnable r : snapshot) {
            SwingUtilities.invokeLater(r);
        }
    }
}
