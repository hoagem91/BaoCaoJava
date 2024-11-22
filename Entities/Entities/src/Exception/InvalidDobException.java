/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exception;

/**
 *
 * @author Admin
 */
public class InvalidDobException  extends Exception{
    private String invalidDob;

    public InvalidDobException() {
    }

    public InvalidDobException(String invalidDob) {
        this.invalidDob = invalidDob;
    }

    public InvalidDobException(String invalidDob, String message) {
        super(message);
        this.invalidDob = invalidDob;
    }

    public String getInvalidDob() {
        return invalidDob;
    }

    public void setInvalidDob(String invalidDob) {
        this.invalidDob = invalidDob;
    }
    
}
