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
public class SapXepSachTenGiamDan implements Comparator<TaiLieu>{

    @Override
    public int compare(TaiLieu o1, TaiLieu o2) {
       var a = o1.getTentl().trim().substring(0, 2);
      var b = o2.getTentl().trim().substring(0, 2);
        return b.compareToIgnoreCase(a);
    }
    
}
