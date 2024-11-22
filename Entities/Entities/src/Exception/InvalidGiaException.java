/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exception;

/**
 *
 * @author Admin
 */
public class InvalidGiaException extends Exception{
    private String invalidGia;

    public InvalidGiaException() {
    }

    public InvalidGiaException(String invalidGia) {
        this.invalidGia = invalidGia;
    }

    public InvalidGiaException(String invalidGia, String message) {
        super(message);
        this.invalidGia = invalidGia;
    }

    public String getInvalidGia() {
        return invalidGia;
    }

    public void setInvalidGia(String invalidGia) {
        this.invalidGia = invalidGia;
    }
    
}
