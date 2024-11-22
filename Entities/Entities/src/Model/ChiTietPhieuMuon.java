/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class ChiTietPhieuMuon implements Serializable{
    private int maPM ;
    private String maSach ;
    private String ngayTra ;
    private String tinhTrangSach ;
    private String tienPhat ;
    
    public ChiTietPhieuMuon() {
        
    }

    public ChiTietPhieuMuon(int maPM, String maSach, String tinhTrangSach, String tienPhat) {
        this.maPM = maPM;
        this.maSach = maSach;
        this.tinhTrangSach = tinhTrangSach;
        this.tienPhat = tienPhat;
    }

    public ChiTietPhieuMuon(int maPM, String maSach, String ngayThucTra,
            String tinhTrangSach, String tienPhat) {
        this.maPM = maPM;
        this.maSach = maSach;
        this.ngayTra = ngayThucTra;
        this.tinhTrangSach = tinhTrangSach;
        this.tienPhat = tienPhat;
    }
    
    
    public ChiTietPhieuMuon(int maPM, String maSach) {
        this.maPM = maPM;
        this.maSach = maSach;
    }

    public int getMaPM() {
        return maPM;
    }

    public String getMaSach() {
        return maSach;
    }

    public String getNgayTra() {
        return ngayTra;
    }

    public String getTinhTrangSach() {
        return tinhTrangSach;
    }

    public String getTienPhat() {
        return tienPhat;
    }

    public void setMaPM(int maPM) {
        this.maPM = maPM;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public void setNgayTra(String ngayThucTra) {
        this.ngayTra = ngayThucTra;
    }

    public void setTinhTrangSach(String tinhTrangSach) {
        this.tinhTrangSach = tinhTrangSach;
    }

    public void setTienPhat(String tienPhat) {
        this.tienPhat = tienPhat;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + this.maPM;
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
        final ChiTietPhieuMuon other = (ChiTietPhieuMuon) obj;
        return this.maPM == other.maPM;
    }
    
}
