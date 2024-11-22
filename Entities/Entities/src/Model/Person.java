/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Controller.InfoFilterImp;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import Exception.InvalidDobException;
import Exception.InvalidEmailException;
import Exception.InvalidNameException;
import Exception.InvalidPhoneNumberException;



/**
 *
 * @author Admin
 */
public class Person implements Serializable{      
    
    private String address;
    private String phoneNumber;
    private FullName fullName;
    private String email;
    private Date dob;
    
    public Person() {
        fullName = new FullName();
    }         
    public Person( String address, String phoneNumber,
            String fullName, String email, String dob)
            throws  InvalidPhoneNumberException,
            InvalidEmailException, InvalidDobException, InvalidNameException, ParseException {
        this();
        
        this.address = address;
        setPhoneNumber(phoneNumber);
        setFullName(fullName);
        setEmail(email);
        setDob(dob);
    }
    
    
    
    
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) throws InvalidPhoneNumberException {
        var infoFilter = new InfoFilterImp();
        try {
            if (infoFilter.isPhoneNumberValid(phoneNumber)) {
                this.phoneNumber = phoneNumber;
            }
        } catch (InvalidPhoneNumberException e) {
            throw e;
        } 
    }
    
    public String getFullName() {
        return fullName.lastName +" "+ fullName.midName +" "+ fullName.firstName;
    }
    
    public void setFullName(String fullName) throws InvalidNameException {
        var infoFilter = new InfoFilterImp();
        
        try {
            if (infoFilter.isNameValid(fullName)) {
                var words = fullName.split("\\s+");
                this.fullName.lastName = words[0];
                this.fullName.firstName = words[words.length - 1];
                this.fullName.midName = "";
                for (int i = 1; i < words.length - 1; i++) {
                    this.fullName.midName += words[i] ; 
                }
            }
        } catch (InvalidNameException e) {
            throw e;
        } 
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) throws InvalidEmailException {
        
        try {
            var infoFilter = new InfoFilterImp();
            if (infoFilter.isEmailValid(email)) {
                this.email = email;
            }
        } catch (InvalidEmailException ex) {
            throw ex;
        }
        
    }
    
    public Date getDob() {
        return dob;
    }
    
    public void setDob(String dob) throws InvalidDobException, ParseException {
        var infoFilter = new InfoFilterImp();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            if (infoFilter.isDobValid(dob)) {
                this.dob = simpleDateFormat.parse(dob);
            }
        } catch (InvalidDobException ex) {
            throw ex;
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        
    }     
          
    public class FullName implements Serializable {
        
        private String firstName;
        private String midName;
        private String lastName;
        
        public FullName() {
            firstName = "";
            midName = "";
            lastName = "";
        }
        
        public FullName(String firstName, String midName, String lastName) {
            this.firstName = firstName;
            this.midName = midName;
            this.lastName = lastName;
        }
        
        public String getFirstName() {
            return firstName;
        }
        
        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }
        
        public String getMidName() {
            return midName;
        }
        
        public void setMidName(String midName) {
            this.midName = midName;
        }
        
        public String getLastName() {
            return lastName;
        }
        
        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
        
    }
}

    

