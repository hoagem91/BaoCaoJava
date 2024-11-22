/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.text.ParseException;
import java.util.Objects;
import Exception.InvalidDobException;
import Exception.InvalidEmailException;
import Exception.InvalidNameException;
import Exception.InvalidusernameException;
import Exception.InvalidPhoneNumberException;

/**
 *
 * @author Admin
 */
public class ThuThu extends Person{
    private String mathuthu;
    private String matkhau;

    public String getMathuthu() {
        return mathuthu;
    }

    public void setMathuthu(String mathuthu) {
        this.mathuthu = mathuthu;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public ThuThu() {
    }

    public ThuThu(String mathuthu, String matkhau, String address, 
            String phoneNumber, String fullName, String email, String dob) 
            throws  InvalidPhoneNumberException, 
            InvalidEmailException, InvalidDobException, InvalidNameException, ParseException {
        super( address, phoneNumber, fullName, email, dob);
        this.mathuthu = mathuthu;
        this.matkhau = matkhau;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.mathuthu);
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
        final ThuThu other = (ThuThu) obj;
        return Objects.equals(this.mathuthu, other.mathuthu);
    }
    
    
}
