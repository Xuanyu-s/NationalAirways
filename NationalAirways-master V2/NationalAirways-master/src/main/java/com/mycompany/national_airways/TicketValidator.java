/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.national_airways;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;
/**
 *
 * @author Ace
 */
public class TicketValidator {
    private static final Pattern TICKET_PATTERN = Pattern.compile("^[A-Z]{3}-\\d{8}-\\d{4}$");
 
    private TicketValidator() { }
 
    public static boolean isValidFormat(String ticketNumber) {
        if (ticketNumber == null) return false;
        return TICKET_PATTERN.matcher(ticketNumber.trim()).matches();
    }
 
    public static boolean isValidDate(String ticketNumber) {
        if (!isValidFormat(ticketNumber)) return false;
        String datePart = ticketNumber.trim().split("-")[1];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        sdf.setLenient(false);
        try {
            sdf.parse(datePart);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
 
    public static boolean isValid(String ticketNumber) {
        return isValidFormat(ticketNumber) && isValidDate(ticketNumber);
    }
}
