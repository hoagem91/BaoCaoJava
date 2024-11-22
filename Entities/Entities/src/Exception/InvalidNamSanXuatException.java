/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exception;

/**
 *
 * @author Admin
 */
public class InvalidNamSanXuatException extends Exception{
    private String invalidNamsx;

    public InvalidNamSanXuatException(String message) {
        super(message);
    }

    public InvalidNamSanXuatException(String invalidNamsx, String message) {
        super(message);
        this.invalidNamsx = invalidNamsx;
    }

    public InvalidNamSanXuatException() {
    }

    public String getInvalidNamsx() {
        return invalidNamsx;
    }

    public void setInvalidNamsx(String invalidNamsx) {
        this.invalidNamsx = invalidNamsx;
    }
    
    
}
