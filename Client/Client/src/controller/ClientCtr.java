/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import Model.DocGia;
import Model.ChiTietPhieuMuon;
import Model.PhieuMuon;
import Model.TaiLieu;
import Model.TaiKhoan;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class ClientCtr {

    private int port;
    private String host;
    private Socket mySocket;
    
    public ClientCtr() {
        
        host = "localhost";
        port = 6666;
        
    }

    public void moSocket() {
        try {
            mySocket = new Socket(host, port);           
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
 public void guiPhieuMuon(PhieuMuon phieuMuon) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(mySocket.getOutputStream());
            oos.writeObject(phieuMuon);
        } catch (IOException ex) {
            ex.printStackTrace();
        }        
    }
    public void guiDocgia(DocGia banDoc) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(mySocket.getOutputStream());
            oos.writeObject(banDoc);
        } catch (IOException ex) {
            ex.printStackTrace();
        }        
    }

    public void guiTaiKhoan(TaiKhoan taiKhoan) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(mySocket.getOutputStream());
            oos.writeObject(taiKhoan);
            oos.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }

    public void guiyeucau(String str) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(mySocket.getOutputStream()) ;
            oos.writeObject(str);            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }

    public String nhanketqua() {
        var res = "";
        try {            
            ObjectInputStream ois = new ObjectInputStream(mySocket.getInputStream());
            res = (String) ois.readObject();
        } catch (IOException ex) {
        } catch (ClassNotFoundException ex) {
        }
        
        return res;
        
    }

    public List<DocGia> nhandanhsachbandoc() {
        List<DocGia> banDocs = new ArrayList<>();
        try {            
            ObjectInputStream ois = new ObjectInputStream(mySocket.getInputStream());
            banDocs = (List<DocGia>) ois.readObject();
        } catch (IOException ex) {
        } catch (ClassNotFoundException ex) {
        }
        
        return banDocs;
        
    }

    public List<TaiLieu> nhandanhsachtailieu() {
        List<TaiLieu> sachs = new ArrayList<>();
        try {            
            ObjectInputStream ois = new ObjectInputStream(mySocket.getInputStream());
            sachs = (List<TaiLieu>) ois.readObject();
        } catch (IOException ex) {
        } catch (ClassNotFoundException ex) {
        }
        
        return sachs;
        
    }

    public void dongketnoi() {
        try {
            mySocket.close();
        } catch (IOException ex) {
        }        
    }
    
    public List<String> nhandanhsachtheloai() {
        List<String> dstl = new ArrayList<>();
        try {            
            ObjectInputStream ois = new ObjectInputStream(mySocket.getInputStream());
            dstl = (List<String>) ois.readObject();
        } catch (IOException ex) {
        } catch (ClassNotFoundException ex) {
        }
        
        return dstl;
        
    }
    public List<String> nhanmasach() {
        List<String> ms = new ArrayList<>();
        try {            
            ObjectInputStream ois = new ObjectInputStream(mySocket.getInputStream());
            ms = (List<String>) ois.readObject();
        } catch (IOException ex) {
        } catch (ClassNotFoundException ex) {
        }
        
        return ms;
    }
    public List<PhieuMuon> nhandanhsachphieumuon() {
        List<PhieuMuon> pm = new ArrayList<>();
        try {            
            ObjectInputStream ois = new ObjectInputStream(mySocket.getInputStream());
            pm = (List<PhieuMuon>) ois.readObject();
        } catch (IOException ex) {
        } catch (ClassNotFoundException ex) {
        }
        
        return pm;
    }
    
    public int nhanmabandoc() {
        int ma = 0;
        try {            
            ObjectInputStream ois = new ObjectInputStream(mySocket.getInputStream());
            ma = (int) ois.readObject();
        } catch (IOException ex) {
        } catch (ClassNotFoundException ex) {
        }
        
        return ma;        
    }

    public void guiChiTietPhieuMuon(ChiTietPhieuMuon ctpm) {
         try {
            ObjectOutputStream oos = new ObjectOutputStream(mySocket.getOutputStream());
            oos.writeObject(ctpm);
        } catch (IOException ex) {
            ex.printStackTrace();
        }   
    }

    public void guiChiTietPhieuMuons(List<ChiTietPhieuMuon> chiTietPhieuMuons) {
         try {
            ObjectOutputStream oos = new ObjectOutputStream(mySocket.getOutputStream());
            oos.writeObject(chiTietPhieuMuons);
        } catch (IOException ex) {
            ex.printStackTrace();
        }   
    }

    public List<String> nhandanhsachnxb() {
        List<String> nxb = new ArrayList<>();
        try {            
            ObjectInputStream ois = new ObjectInputStream(mySocket.getInputStream());
            nxb = (List<String>) ois.readObject();
        } catch (IOException ex) {
        } catch (ClassNotFoundException ex) {
        }
        
        return nxb;
    }

    public void guimadanhmuc(String a) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(mySocket.getOutputStream()) ;
            oos.writeObject(a);            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void guimanxb(String b) {
try {
            ObjectOutputStream oos = new ObjectOutputStream(mySocket.getOutputStream()) ;
            oos.writeObject(b);            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
