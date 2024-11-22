/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.DocGia;
import java.util.Comparator;

/**
 *
 * @author Admin
 */
public class SapXepBanDocTheoMaTang implements Comparator<DocGia> {

    @Override
    public int compare(DocGia o1, DocGia o2) {
        if(o1.getMaDocGia()> o2.getMaDocGia()){
        return 1;} 
        else if(o1.getMaDocGia()< o2.getMaDocGia()){
            return -1;
        } else {
        return 0;}
    }
    
}
