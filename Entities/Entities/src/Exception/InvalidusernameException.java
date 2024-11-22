/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exception;

/**
 *
 * @author Admin
 */
public class InvalidusernameException extends Exception{
    private String invalidId;

    public InvalidusernameException() {
    }

    public InvalidusernameException(String invalidId, String message) {
        super(message);
        this.invalidId = invalidId;
    }

    public String getInvalidId() {
        return invalidId;
    }

    public void setInvalidId(String invalidId) {
        this.invalidId = invalidId;
    }
    
}
