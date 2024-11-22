/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.DocGia;
import Model.PhieuMuon;
import Model.TaiLieu;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ketnoisql.KetNoiSql;
/**
 *
 * @author Admin
 */
public class DataControllerImp implements DATACONTROLLER{   
//  Tài liệu
 @Override
    public List<TaiLieu> timtailieutheoten(List<TaiLieu> taiLieus, String name) {
         var regex = ".*"+ name +".*";
        Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        Matcher matcher;
        List<TaiLieu> s1 = new ArrayList<>();
        for (int i = 0; i < taiLieus.size(); i++) {
            var tentl = taiLieus.get(i).getTentl();            
            matcher= pattern.matcher(tentl);
            if(matcher.matches())
            {
                s1.add(taiLieus.get(i));
            }
        }
        return s1;
        
    }
    @Override
    public List<TaiLieu> timtailieutheodanhmuc(List<TaiLieu> taiLieus, String tendm) {
        List<TaiLieu> s1= new ArrayList<>();
       var regex = ".*"+ tendm +".*";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher ;
        for (TaiLieu s: taiLieus) {
            var tendanhmuc = KetNoiSql.laytendanhmucduavaoma(s.getMadanhmuc());
           matcher = pattern.matcher(tendanhmuc);
            if(matcher.matches())
            { s1.add(s);}
        }     
       
       
        return s1;
    }
     @Override
    public List<TaiLieu> timtailieutheodanhmuc1(List<TaiLieu> taiLieus, String tendanhmuc) {
        List<TaiLieu> s1= new ArrayList<>();
       var regex = ".*"+ tendanhmuc +".*";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher ;
        for (TaiLieu s: taiLieus) {           
           matcher = pattern.matcher(s.getMadanhmuc());
            if(matcher.matches())
            { s1.add(s);}
        }     
       
       
        return s1;
    }

@Override
    public List<TaiLieu> timtailieutheoma(List<TaiLieu> taiLieus, String ma) {
        var regex = ".*"+ ma +".*";
        Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        Matcher matcher;
        List<TaiLieu> s1 = new ArrayList<>();
        for (int i = 0; i < taiLieus.size(); i++) {           
            matcher= pattern.matcher(taiLieus.get(i).getMatl());
            if(matcher.matches())
            {
               s1.add(taiLieus.get(i));
            }
        }
        return s1;
        
    }
     @Override
    public List<TaiLieu> timtailieutheonxb(List<TaiLieu> taiLieus, String tennxb) {
        List<TaiLieu> s1= new ArrayList<>();
       var regex = ".*"+ tennxb +".*";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher ;
        for (TaiLieu s: taiLieus) {
            var tennhaxuatban = KetNoiSql.laytennxbduavaoma(s.getManxb());
           matcher = pattern.matcher(tennhaxuatban);
            if(matcher.matches())
            { s1.add(s);}
        }                 
        return s1;
    }
  @Override
    public List<TaiLieu> timtailieutheonxb1(List<TaiLieu> taiLieus, String nxb) {
        List<TaiLieu> s1= new ArrayList<>();
       var regex = ".*"+ nxb +".*";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher ;
        for (TaiLieu s: taiLieus) {
           
           matcher = pattern.matcher(s.getManxb());
            if(matcher.matches())
            { s1.add(s);}
        }                 
        return s1;
    }   
    
    @Override
    public void sxtailieutheotentangdan(List<TaiLieu> taiLieus) {
        Collections.sort(taiLieus, new SapXepSachTenTangDan());
    }

    @Override
    public void sxtailieutheotengiamdan(List<TaiLieu>taiLieus) {
         Collections.sort(taiLieus, new SapXepSachTenGiamDan());
    }

    @Override
    public void sxtailieutheosoluongtang(List<TaiLieu> taiLieus) {
         Collections.sort(taiLieus, new SapXepSachSoLuongTang());
    }

    @Override
    public void sxtailieutheosoluonggiam(List<TaiLieu> taiLieus) {
         Collections.sort(taiLieus, new SapXepSachSoLuongGiam());
    }
    //Độc giả
    
    @Override
    public void sxdocgiatheotentangdan(List<DocGia> docGias) {
        Collections.sort(docGias, new SapXepDocGiaTenTangDan());
    }

    @Override
    public void sxdocgiatheotengiamdan(List<DocGia> docGias) {
        Collections.sort(docGias, new SapXepDocGiaTenGiamDan());
    }
@Override
    public void sxdocgiatheomagiamdan(List<DocGia> docGias) {
         Collections.sort(docGias, new SapXepBanDocTheoMaGiam());
    }

    @Override
    public void sxdocgiatheomatangdan(List<DocGia> docGias) {
         Collections.sort(docGias, new SapXepBanDocTheoMaTang());
    }   
    
    @Override
    public List<DocGia> timdocgiatheoten(List<DocGia> banDocs, String name) {
         var regex = ".*"+ name +".*";
        Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        Matcher matcher;
        List<DocGia> banDocs1 = new ArrayList<>();
        for (int i = 0; i < banDocs.size(); i++) {
            var fullname = banDocs.get(i).getFullName();
            var name1 = fullname.substring(fullname.lastIndexOf(" "));
            matcher= pattern.matcher(name1);
            if(matcher.matches())
            {
                banDocs1.add(banDocs.get(i));
            }
        }
        return banDocs1;
    }

    @Override
    public List<DocGia> timdocgiatheoma(List<DocGia> banDocs, int id) {       
        List<DocGia> banDocs1 = new ArrayList<>();
        for (int i = 0; i < banDocs.size(); i++) {                     
            if(banDocs.get(i).getMaDocGia()== id)
            {
                banDocs1.add(banDocs.get(i));
            }
        }
        return banDocs1;
    }         
    @Override
    public List<PhieuMuon> timphieutheomadocgia(List<PhieuMuon> phieuMuons, String madocgia) {
        var regex = ".*"+ madocgia +".*";
        Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        Matcher matcher;
        List<PhieuMuon> s1 = new ArrayList<>();
        for (int i = 0; i < phieuMuons.size(); i++) {           
            matcher= pattern.matcher(String.valueOf(phieuMuons.get(i).getMaBanDoc()));
            if(matcher.matches())
            {
               s1.add(phieuMuons.get(i));
            }
        }
        return s1;
    }

    @Override
    public List<PhieuMuon> timphieutheomaphieu(List<PhieuMuon> phieuMuons, String maphieu) {
        var regex = ".*"+ maphieu +".*";
        Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        Matcher matcher;
        List<PhieuMuon> s1 = new ArrayList<>();
        for (int i = 0; i < phieuMuons.size(); i++) {           
            matcher= pattern.matcher(String.valueOf(phieuMuons.get(i).getMaPM()));
            if(matcher.matches())
            {
               s1.add(phieuMuons.get(i));
            }
        }
        return s1;
    }

    @Override
    public List<PhieuMuon> timphieutheotendocgia(List<PhieuMuon> phieuMuons, String tendocgia) {
        var regex = ".*"+ tendocgia +".*";
        Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        Matcher matcher;
        List<PhieuMuon> s1 = new ArrayList<>();
        for (int i = 0; i < phieuMuons.size(); i++) {           
            matcher= pattern.matcher(String.valueOf(phieuMuons.get(i).getTenBanDoc()));
            if(matcher.matches())
            {
               s1.add(phieuMuons.get(i));
            }
        }
        return s1;
    }

   

   
    
    

  
    
}
