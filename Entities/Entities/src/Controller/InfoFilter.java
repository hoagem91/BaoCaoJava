/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Exception.InvalidDobException;
import Exception.InvalidEmailException;
import Exception.InvalidGiaException;
import Exception.InvalidNamSanXuatException;
import Exception.InvalidNameException;
import Exception.InvalidPhoneNumberException;
import Exception.InvalidSoLuongException;
import Exception.InvalidusernameException;


/**
 *
 * @author Admin
 */
public interface InfoFilter {
   

    boolean isPhoneNumberValid(String phoneNumber) throws InvalidPhoneNumberException;

    boolean isEmailValid(String email) throws InvalidEmailException;

    boolean isDobValid(String dobString) throws InvalidDobException;

    boolean isNameValid(String name) throws InvalidNameException;
     boolean isusernameValid(String name) throws InvalidusernameException;
     boolean isSoLuongValid(String soluong) throws InvalidSoLuongException;
    boolean isNamsxValid(String namsx) throws InvalidNamSanXuatException;
    boolean isGiaValid(String gia) throws InvalidGiaException;
}
