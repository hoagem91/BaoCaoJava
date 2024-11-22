/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exception;

/**
 *
 * @author Admin
 */
public class InvalidSoLuongException extends Exception{
    private String  invalidsoluong;

    public InvalidSoLuongException() {
    }

    public InvalidSoLuongException(String message) {
        super(message);
    }

    public InvalidSoLuongException(String invalidsoluong, String message) {
        super(message);
        this.invalidsoluong = invalidsoluong;
    }

    public String getInvalidsoluong() {
        return invalidsoluong;
    }

    public void setInvalidsoluong(String invalidsoluong) {
        this.invalidsoluong = invalidsoluong;
    }
    
}
