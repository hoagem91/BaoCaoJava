/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Admin
 */
public class PhieuMuon implements Serializable{

    private int maPM;
    private static  int sId = 1;
    private int maBanDoc;
    private String tenBanDoc;
    private Date ngayMuon;
    private int soNgayMuon;
    private int soSachMuon;   
    private String trangThai;
    private String mathuthu;

    public PhieuMuon() {
        trangThai = "Chưa trả";
        //maPM =  sId++;
    }

    public PhieuMuon(int maPM, int maBanDoc, String tenBanDoc, Date ngayMuon,
            int soNgayMuon, int soSachMuon,  String trangThai, String mathuthu) {
        this.maPM = maPM;
        this.maBanDoc = maBanDoc;
        this.tenBanDoc = tenBanDoc;
        this.ngayMuon = ngayMuon;
        this.soNgayMuon = soNgayMuon;
        this.soSachMuon = soSachMuon;
        this.mathuthu = mathuthu;
        this.trangThai = trangThai;
    }

    public String getMathuthu() {
        return mathuthu;
    }

    public void setMathuthu(String mathuthu) {
        this.mathuthu = mathuthu;
    }

    public String getTenBanDoc() {
        return tenBanDoc;
    }

    public void setTenBanDoc(String tenBanDoc) {
        this.tenBanDoc = tenBanDoc;
    }

    public int getSoSachMuon() {
        return soSachMuon;
    }

    public void setSoSachMuon(int soSachMuon) {
        this.soSachMuon = soSachMuon;
    }

    

    public int getMaPM() {
        return maPM;
    }

    public Date getNgayMuon() {
        return ngayMuon;
    }

    public int getSoNgayMuon() {
        return soNgayMuon;
    }

    public int getMaBanDoc() {
        return maBanDoc;
    }

    public static int getsId() {
        return sId;
    }

    public static void setsId(int sId) {
        PhieuMuon.sId = sId;
    }

  

    public String getTrangThai() {
        return trangThai;
    }

    public void setMaPM(int maPM) {
        this.maPM = maPM;
    }

    public void setNgayMuon(Date ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    public void setSoNgayMuon(int soNgayMuon) {
        this.soNgayMuon = soNgayMuon;
    }

    public void setMaBanDoc(int maTaiKhoan) {
        this.maBanDoc = maTaiKhoan;
    }

   

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.maPM);
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
        final PhieuMuon other = (PhieuMuon) obj;
        return Objects.equals(this.maPM, other.maPM);
    }
    
}
