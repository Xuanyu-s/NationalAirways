/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.national_airways;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
 
/**
 *
 * @author Ace
 */
public class AccountStorage {
 
    public static ArrayList<AccountDetails> accounts = new ArrayList<>();
 
    private static final String FILE_PATH = "accounts.csv";
 
    static {
        loadAccounts();
    }
 
    public static void loadAccounts() {
        accounts.clear();
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) {
                    continue;
                }
                String[] parts = line.split(",", 4);
                if (parts.length == 4) {
                    accounts.add(new AccountDetails(parts[0], parts[1], parts[2], parts[3]));
                } else if (parts.length == 3) {
                    accounts.add(new AccountDetails(parts[0], parts[1], parts[2]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    public static void saveAccounts() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (AccountDetails account : accounts) {
                writer.write(account.getuserName() + "," + account.getpassWord() + ","
                        + account.getroles() + "," + account.getStatus());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
