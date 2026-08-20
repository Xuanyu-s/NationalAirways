/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.national_airways;

/**
 *
 * @author Ace
 */
public class AccountDetails {
    
     
    String userName;
    String password;
    String roles;
    String status;
    
    public AccountDetails(String userName, String passWord, String roles){
        this(userName, passWord, roles, "Active");
    }
    
    public AccountDetails(String userName, String passWord, String roles, String status){
        
        this.userName = userName;
        this.password = passWord;
        this.roles = roles;
        this.status = (status == null || status.isBlank()) ? "Active" : status;
        
    }
        
    public String getuserName() {
        return userName;
    }
    
    public void setuserName(String userName) {
        this.userName = userName;
    }
    
    public String getpassWord() {
        return password;
    }
    
    public void setpassWord(String passWord) {
        this.password = passWord;
    }
    
    public String getroles() {
        return roles;
    }
    
    public void setroles(String roles) {
        this.roles = roles;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
}
