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
    
    public AccountDetails(String userName, String passWord, String roles){
        
        this.userName = userName;
        this.password = passWord;
        this.roles = roles;
        
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
    
}
