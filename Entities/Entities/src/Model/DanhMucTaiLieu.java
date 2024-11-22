/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Objects;

/**
 *
 * @author Admin
 */
public class DanhMucTaiLieu {
    private String madm ;
    private String tendm;
   

    public DanhMucTaiLieu() {
        
    }

    public DanhMucTaiLieu(String madm, String tendm) {
        this.madm = madm;
        this.tendm = tendm;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.madm);
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
        final DanhMucTaiLieu other = (DanhMucTaiLieu) obj;
        return Objects.equals(this.madm, other.madm);
    }

    

    public String getMadm() {
        return madm;
    }

    public void setMadm(String madm) {
        this.madm = madm;
    }

    public String getTendm() {
        return tendm;
    }

    public void setTendm(String tendm) {
        this.tendm = tendm;
    }


   
}
