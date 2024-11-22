/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exception;

/**
 *
 * @author Admin
 */
public class InvalidEmailException extends Exception{
    private String invalidEmail;

    public InvalidEmailException() {
    }

    public InvalidEmailException(String invalidEmail) {
        this.invalidEmail = invalidEmail;
    }

    public InvalidEmailException(String invalidEmail, String message) {
        super(message);
        this.invalidEmail = invalidEmail;
    }

    public String getInvalidEmail() {
        return invalidEmail;
    }

    public void setInvalidEmail(String invalidEmail) {
        this.invalidEmail = invalidEmail;
    }
    
}
