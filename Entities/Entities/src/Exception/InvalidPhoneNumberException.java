/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exception;

/**
 *
 * @author Admin
 */
public class InvalidPhoneNumberException extends Exception{
    private String invalidPhoneNumber;

    public InvalidPhoneNumberException() {
    }

    public InvalidPhoneNumberException(String invalidPhoneNumber) {
        this.invalidPhoneNumber = invalidPhoneNumber;
    }

    public InvalidPhoneNumberException(String invalidPhoneNumber, String message) {
        super(message);
        this.invalidPhoneNumber = invalidPhoneNumber;
    }
    

    public String getInvalidPhoneNumber() {
        return invalidPhoneNumber;
    }

    public void setInvalidPhoneNumber(String invalidPhoneNumber) {
        this.invalidPhoneNumber = invalidPhoneNumber;
    }
    
}
