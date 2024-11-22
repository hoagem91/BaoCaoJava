/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thongketienphat;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Admin
 */
public class ThongKeTienPhat {
   
    private int tienphat;
   private int nam;

    public int getTienphat() {
        return tienphat;
    }

    public void setTienphat(int tienphat) {
        this.tienphat = tienphat;
    }

    public int getNam() {
        return nam;
    }

    public void setNam(int nam) {
        this.nam = nam;
    }

    public ThongKeTienPhat(int tienphat, int nam) {
        this.tienphat = tienphat;
        this.nam = nam;
    }

    public ThongKeTienPhat() {
    }

   
    
    
}
