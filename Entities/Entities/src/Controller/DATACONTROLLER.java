/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.DocGia;
import Model.PhieuMuon;
import Model.TaiLieu;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface DATACONTROLLER {
         //Tài liệu
     public List<TaiLieu> timtailieutheoten(List<TaiLieu> tailieus, String tentailieu);
    public List<TaiLieu> timtailieutheodanhmuc(List<TaiLieu> taiLieus, String tendanhmuc);
    public List<TaiLieu> timtailieutheoma(List<TaiLieu> taiLieus, String ma);
     public List<TaiLieu> timtailieutheonxb(List<TaiLieu> taiLieus, String nxb);
     public List<TaiLieu> timtailieutheodanhmuc1(List<TaiLieu> taiLieus, String tendanhmuc);
     public List<TaiLieu> timtailieutheonxb1(List<TaiLieu> taiLieus, String nxb);
    void sxtailieutheotentangdan(List<TaiLieu> taiLieus);
     void sxtailieutheotengiamdan(List<TaiLieu> taiLieus);
     void sxtailieutheosoluongtang(List<TaiLieu> taiLieus);
     void sxtailieutheosoluonggiam(List<TaiLieu> taiLieus);  
     
    //Độc giả
    public List<DocGia> timdocgiatheoten(List<DocGia> docgias, String name);
     public List<DocGia> timdocgiatheoma(List<DocGia> docgias, int id);
    
    void sxdocgiatheotentangdan(List<DocGia> Docgias);
    void sxdocgiatheotengiamdan(List<DocGia> Docgias);  
   public void sxdocgiatheomagiamdan(List<DocGia> banDocs);
   public void sxdocgiatheomatangdan(List<DocGia> banDocs);

                           
    
    //phiếu mượn
    public List<PhieuMuon> timphieutheomadocgia(List<PhieuMuon> phieuMuons, String madocgia);
    public List<PhieuMuon> timphieutheomaphieu(List<PhieuMuon> phieuMuons, String maphieu);
    public List<PhieuMuon> timphieutheotendocgia(List<PhieuMuon> phieuMuons, String tendocgia);

}
    
