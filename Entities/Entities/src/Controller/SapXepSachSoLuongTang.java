/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.TaiLieu;
import java.util.Comparator;


/**
 *
 * @author Admin
 */
public class SapXepSachSoLuongTang implements Comparator<TaiLieu>{

    @Override
    public int compare(TaiLieu o1, TaiLieu o2) {
        if(Integer.parseInt(o1.getSoLuongCon())> Integer.parseInt(o2.getSoLuongCon())){
        return 1;} 
        else if(Integer.parseInt(o1.getSoLuongCon())< Integer.parseInt(o2.getSoLuongCon())){
            return -1;
        } else {
        return 0;}
    }
    
}
