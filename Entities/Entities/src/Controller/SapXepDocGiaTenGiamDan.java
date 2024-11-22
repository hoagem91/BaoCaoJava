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
public class SapXepDocGiaTenGiamDan implements Comparator<DocGia> {

    @Override
    public int compare(DocGia o1, DocGia o2) {
       
        var str=  o1.getFullName();
        var name = str.substring(str.lastIndexOf(" "));
         var str1=  o2.getFullName();
        var name1 = str1.substring(str1.lastIndexOf(" "));
        return name1.compareTo(name);
    }
    }
    

