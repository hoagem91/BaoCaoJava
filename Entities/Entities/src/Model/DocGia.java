/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;


import Controller.InfoFilterImp;
import java.text.ParseException;
import java.util.Objects;
import Exception.InvalidDobException;
import Exception.InvalidEmailException;
import Exception.InvalidNameException;
import Exception.InvalidPhoneNumberException;
import Exception.InvalidusernameException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Admin
 */
public class DocGia  extends Person{
    private int maDocGia;
    private static int sId = 1000;
    private String tenDangNhap;
    private String matkhau;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getMaDocGia() {
        return maDocGia;
    }

    public void setMaDocGia(int maDocGia) {
        this.maDocGia = maDocGia;
    }
   

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public static int getsId() {
        return sId;
    }

    public static void setsId(int sId) {
        DocGia.sId = sId;
    }

    public void setTenDangNhap(String tenDangNhap) throws InvalidusernameException {
        try {
            var imp = new InfoFilterImp();
            if(imp.isusernameValid(tenDangNhap)){
                this.tenDangNhap = tenDangNhap;
            }
        } catch (InvalidusernameException ex) {
            throw ex;
        }
    }

   

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    
    public DocGia() {
        maDocGia = sId++;
    }

    public DocGia(int maBanDoc, String matkhau,String tendangNhap,
            String address, String phoneNumber, String fullName, String email, String dob, String status)
            throws  InvalidPhoneNumberException, InvalidEmailException, InvalidDobException, InvalidNameException, ParseException, InvalidusernameException {
        super( address, phoneNumber, fullName, email, dob);
        setTenDangNhap(tendangNhap);
        this.maDocGia = maBanDoc;
        this.matkhau = matkhau;
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.maDocGia;
        hash = 67 * hash + Objects.hashCode(this.tenDangNhap);
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
        final DocGia other = (DocGia) obj;
        if (this.maDocGia != other.maDocGia) {
            return false;
        }
        return Objects.equals(this.tenDangNhap, other.tenDangNhap);
    }

   

   

   
    
    
}
