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
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 *
 * @author Admin
 */
public class InfoFilterImp implements  InfoFilter{

   

    @Override
    public boolean isPhoneNumberValid(String phoneNumber) throws InvalidPhoneNumberException  {
        var regex = "^(03|04|07|08|09)\\d{8}$";
        Pattern pattern =Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        if(matcher.matches()){
        return true;
        }else{
        var msg = "Số điện thoại không hợp lệ "+ phoneNumber;
        throw new InvalidPhoneNumberException(phoneNumber,msg);
        }
    }

    @Override
    public boolean isEmailValid(String email) throws InvalidEmailException  {
        var regex = "^[a-z]+[a-z0-9._]*@gmail.com$";
        Pattern pattern =Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if(matcher.matches()){
        return true;
        }else{
        var msg = "Email không hợp lệ "+ email;
        throw new InvalidEmailException(email,msg);
        }
    }

    @Override
    public boolean isDobValid(String dobString) throws InvalidDobException {
        var regex = "^\\d{2}/{1}\\d{2}/{1}\\d{4}$";
        Pattern pattern =Pattern.compile(regex);
        Matcher matcher = pattern.matcher(dobString);
        if(matcher.matches()){
        return true;
        }else{
        var msg = "Ngày sinh không hợp lệ "+ dobString;
        throw new InvalidDobException(dobString,msg);
        }
    }

    @Override
    public boolean isNameValid(String name) throws InvalidNameException  {
        var regex = "^(\\w+.*[^0-9].*)$";
        Pattern pattern =Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(name);
        if(matcher.matches()){
        return true;
        }else{
        var msg = "Họ tên không hợp lệ "+ name;
        throw new InvalidNameException(name,msg);
        }
    }

    @Override
    public boolean isusernameValid(String name) throws InvalidusernameException {
        var regex = "^([a-z]){3,5}[0-9]{4}$";
        Pattern pattern =Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(name);
        if(matcher.matches()){
        return true;
        }else{
        var msg = "Tên đăng nhập không hợp lệ "+ name;
        throw new InvalidusernameException(name,msg);
        }
    }

    @Override
    public boolean isSoLuongValid(String soluong) throws InvalidSoLuongException {
         var regex = "\\d*[^a-z]";
        Pattern pattern =Pattern.compile(regex);
        Matcher matcher = pattern.matcher(String.valueOf(soluong));
        if(matcher.matches()){
        return true;
        }else{
        var msg = "Bạn đã nhập sai: "+ soluong +"\nSố lượng phải là một giá trị số nguyên >0";
        throw new InvalidSoLuongException(soluong,msg);
        }
    }

    @Override
    public boolean isNamsxValid(String namsx) throws InvalidNamSanXuatException {
         var regex = "^[0-9]{4}$";
        Pattern pattern =Pattern.compile(regex);
        Matcher matcher = pattern.matcher(String.valueOf(namsx));
        if(matcher.matches()){
        return true;
        }else{
        var msg = "Bạn đã nhập sai: "+ namsx +"\nNăm xuất bản phải là một số nguyên có 4 chữ số";
        throw new InvalidNamSanXuatException(namsx,msg);
        }
    }

    @Override
    public boolean isGiaValid(String gia) throws InvalidGiaException {
         var regex = "\\d*[^a-z]";
        Pattern pattern =Pattern.compile(regex);
        Matcher matcher = pattern.matcher(String.valueOf(gia));
        if(matcher.matches()){
        return true;
        }else{
        var msg = "Bạn đã nhập sai: "+ gia +"\nGiá tiền phải là một giá trị nguyên >= 0";
        throw new InvalidGiaException(gia,msg);
        }
    }
    
    
}
