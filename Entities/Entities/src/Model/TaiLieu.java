/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Controller.InfoFilter;
import Controller.InfoFilterImp;
import Exception.InvalidGiaException;
import Exception.InvalidNamSanXuatException;
import Exception.InvalidSoLuongException;
import java.io.Serializable;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class TaiLieu implements Serializable{
    
    private String matl ;
    private String tentl;   
    private String madanhmuc;
    private String tacGia;
    private String manxb;
    private String namXuatBan;
    private String soLuongCon;
    private String tomTatND ;
    private String giasach;
     

    public TaiLieu() {
    }

    

    public TaiLieu(String matl, String tentailieu, String madanhmuc, String tacGia, 
            String maNXB, String namXuatBan, String soLuongCon, String tomTatND, String giasach) throws InvalidSoLuongException, InvalidGiaException, InvalidNamSanXuatException {
        this.matl = matl;
        this.tentl = tentailieu;
        this.madanhmuc = madanhmuc;
        this.tacGia = tacGia;
        this.manxb = maNXB;
        setNamXuatBan(namXuatBan);
        setSoLuongCon(soLuongCon);
        this.tomTatND = tomTatND;
        setGiasach(giasach);
    }

    public String getGiasach() {
        return giasach;
    }

    public void setGiasach(String giasach) throws InvalidGiaException {
        var filterImp = new InfoFilterImp();
         try {
            if(filterImp.isGiaValid(giasach)){
                
                this.giasach = giasach ;}
            } catch (InvalidGiaException ex) {
                throw ex;
        }
        
       
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.matl);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        TaiLieu other = (TaiLieu) obj;
        if(other.getMatl().compareToIgnoreCase(this.getMatl())==0){
        return true;
        }
        return Objects.equals(this.matl, other.matl);
    }

    public String getMatl() {
        return matl;
    }

    public void setMatl(String matl) {
        this.matl = matl;
    }

    public String getTentl() {
        return tentl;
    }

    public void setTentl(String tentl) {
        this.tentl = tentl;
    }

    public String getMadanhmuc() {
        return madanhmuc;
    }

    public void setMadanhmuc(String madanhmuc) {
        this.madanhmuc = madanhmuc;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getManxb() {
        return manxb;
    }

    public void setManxb(String manxb) {
        this.manxb = manxb;
    }

    public String getNamXuatBan() {
        return namXuatBan;
    }

    public void setNamXuatBan(String namXuatBan) throws InvalidNamSanXuatException {
        var filterImp = new InfoFilterImp();
        try {
            if(filterImp.isNamsxValid(String.valueOf(namXuatBan))){
                this.namXuatBan = namXuatBan;}
            } catch (InvalidNamSanXuatException ex) {
                throw ex;
        }
    }

    public String getSoLuongCon() {
        return soLuongCon;
    }

    public void setSoLuongCon(String soLuongCon) throws InvalidSoLuongException  {
        var filterImp = new InfoFilterImp();
        try {
            if(filterImp.isSoLuongValid(soLuongCon)){
                this.soLuongCon = soLuongCon;}
            } catch (InvalidSoLuongException ex) {
                throw ex;
        }
    }

    public String getTomTatND() {
        return tomTatND;
    }

    public void setTomTatND(String tomTatND) {
        this.tomTatND = tomTatND;
    }

   
    
    
}
