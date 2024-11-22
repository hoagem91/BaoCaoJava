/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exception;

/**
 *
 * @author Admin
 */
public class InvalidNameException extends Exception{
    private String invalidName;

    public InvalidNameException() {
    }

    public InvalidNameException(String invalidName) {
        this.invalidName = invalidName;
    }

    public InvalidNameException(String invalidName, String message) {
        super(message);
        this.invalidName = invalidName;
    }

    public String getInvalidName() {
        return invalidName;
    }

    public void setInvalidName(String invalidName) {
        this.invalidName = invalidName;
    }
    
    
}
