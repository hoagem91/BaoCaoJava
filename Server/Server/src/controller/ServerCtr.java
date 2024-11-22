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
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import ketnoisql1.KetNoiSql;
import serverview.DangNhap1;

/**
 *
 * @author Admin
 */
public class ServerCtr {

    private int port;
    private String host;
    private ServerSocket myServer;
    private ArrayList<Socket> list;
    private List<DocGia> banDocs;
    private List<TaiLieu> sachs;
    private List<String> dsdm;
    private List<String> dsnxb;
    private List<PhieuMuon> pm;
    private List<ChiTietPhieuMuon> ctpms;
    // private Socket s;
    private List<String> masach;

    public ServerCtr() {
        new DangNhap1().setVisible(true);
        port = 6666;
        host = "localhost";
        list = new ArrayList<>();
        banDocs = new ArrayList<>();
        sachs = new ArrayList<>();
        dsdm = new ArrayList<>();
        dsnxb = new ArrayList<>();
        pm = new ArrayList<>();
        masach = new ArrayList<>();       
        TaiKhoan tk = new TaiKhoan("", "");
        String res = "";
        DocGia banDoc = new DocGia();
        PhieuMuon phieuMuon = new PhieuMuon();
        ChiTietPhieuMuon ctpm = new ChiTietPhieuMuon();
        ctpms = new ArrayList<>();
        moSocket();
        while (true) {
            try {
                Socket s = myServer.accept();
                list.add(s);
                Object ss = nhanphanhoi(s);
                if (tk.getClass() == ss.getClass()) {
                    tk = (TaiKhoan) ss;
                    if (KetNoiSql.kiemtradocgia((TaiKhoan) tk)) {
                        String status = KetNoiSql.laystatusdocgia(tk.getTenDangNhap(), tk.getMatKhau()).trim();
                        guiketqua(status);
                        int ma = KetNoiSql.laymadocgia(tk.getTenDangNhap(), tk.getMatKhau());
                        guimadocgia(ma);
                    } else {
                        guiketqua("Thất bại");
                    }
                }
                if (res.getClass() == ss.getClass()) {
                    res = (String) ss;
                    if (res.equals("ok")) {
                        guidanhsachtailieu(KetNoiSql.readSachFromSql1());
                        guidanhsachtheloai(KetNoiSql.layTenTheLoaiSachSql());
                        guidanhsachphieumuon(KetNoiSql.readPhieuMuonFromSql());
                        guidanhsachBanDoc(KetNoiSql.readBandocsFromSql());
                        guidanhsachnxb(KetNoiSql.layTennxbSql());
                    } else if (res.equals("oke")) {
                        guidanhsachtailieu(KetNoiSql.readSachFromSql1());
                    } else if (res.equals("oki")) {
                        guidanhsachBanDoc(KetNoiSql.readBandocsFromSql());
                    }
                     else if (res.equals("okk")) {
                        guidanhsachphieumuon(KetNoiSql.readPhieuMuonFromSql()) ;
                         guidanhsachtailieu(KetNoiSql.readSachFromSql1());
                    }
                    for (int i = 0; i < KetNoiSql.readPhieuMuonFromSql().size(); i++) {
                        var maphieu = String.valueOf(KetNoiSql.readPhieuMuonFromSql().get(i).getMaPM()).trim();
                        if (res.compareToIgnoreCase(maphieu) == 0) {
                            KetNoiSql.capnhatsongaymuonchophieumuon(maphieu, KetNoiSql.readPhieuMuonFromSql().get(i).getSoNgayMuon() + 7);
                            KetNoiSql.capnhattrangthaiphieumuonSql(maphieu, "Đang gia hạn");
                            guiketqua("capnhatphieumuonok");

                        }
                    }
                    for (int i = 0; i < KetNoiSql.laymadanhmuc().size(); i++) {
                        var madm = KetNoiSql.laymadanhmuc().get(i).trim();
                        if (res.compareToIgnoreCase(madm) == 0) {
                            var a = KetNoiSql.laytendanhmucduavaoma(res);
                            guiketqua(a);

                        }
                    }
                    for (int i = 0; i < KetNoiSql.laymanxbSql().size(); i++) {
                        var manxb = KetNoiSql.laymanxbSql().get(i).trim();
                        if (res.compareToIgnoreCase(manxb) == 0) {
                            var a = KetNoiSql.laytennxbduavaoma(res);
                            guiketqua(a);

                        }
                    }
                }
                if (banDoc.getClass() == ss.getClass()) {
                    banDoc = (DocGia) ss;
                    if (KetNoiSql.readBandocsFromSql().contains(banDoc)) {
                        KetNoiSql.suaDocGiaSql(banDoc);
                        guiketqua("suadocgiathanhcong");
                    } else {
                        KetNoiSql.saveBanDocDataToSql(banDoc);
                    }

                }
                if (phieuMuon.getClass() == ss.getClass()) {
                    phieuMuon = (PhieuMuon) ss;
                    if (KetNoiSql.readPhieuMuonFromSql().contains(phieuMuon)) {
                        var a = KetNoiSql.laymasachtuchitietphieumuon(phieuMuon.getMaPM());
                        guimasach(a);
                    } else {
                        KetNoiSql.savePhieuMuonSql(phieuMuon);
                        guiketqua("taophieumuonthanhcong");
                    }
                }
                if (ctpms.getClass() == ss.getClass()) {
                    ctpms = (List<ChiTietPhieuMuon>) ss;
                    for (int i = 0; i < ctpms.size(); i++) {
                        KetNoiSql.savachitietphieumuonSql(ctpms.get(i));
                        masach = KetNoiSql.laymasachtuchitietphieumuon(ctpms.get(i).getMaPM());
                    }
                    for (int i = 0; i < KetNoiSql.readSachFromSql().size(); i++) {
                        for (int j = 0; j < masach.size(); j++) {
                            if (KetNoiSql.readSachFromSql().get(i).getMatl().compareToIgnoreCase(masach.get(j)) == 0) {
                                KetNoiSql.capnhatsoluongconcuasachSql(KetNoiSql.readSachFromSql().get(i).getMatl(), Integer.parseInt(KetNoiSql.readSachFromSql().get(i).getSoLuongCon()) - 1);
                            }

                        }

                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    public void guimadocgia(int ma) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(list.get(list.size() - 1).getOutputStream());
            oos.writeObject(ma);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void guiketqua(String res) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream((list.get(list.size() - 1)).getOutputStream()
            );
            oos.writeObject(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void guimasach(List<String> masachs) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream((list.get(list.size() - 1)).getOutputStream());
            oos.writeObject(masachs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void guidanhsachBanDoc(List<DocGia> banDocs) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream((list.get(list.size() - 1)).getOutputStream());
            oos.writeObject(banDocs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void guidanhsachtailieu(List<TaiLieu> sachs) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream((list.get(list.size() - 1)).getOutputStream());
            oos.writeObject(sachs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void guidanhsachphieumuon(List<PhieuMuon> pm) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream((list.get(list.size() - 1)).getOutputStream());
            oos.writeObject(pm);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void guidanhsachtheloai(List<String> dsdm) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream((list.get(list.size() - 1)).getOutputStream());
            oos.writeObject(dsdm);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executebandoc(Socket s) {
        try {
            DocGia ss = nhanBanDoc(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public void kiemtrayeucau(Socket s) {
//        try {
//            String yeucau = nhanyeucau(s);
//            if (yeucau.equals("ok")) {
//                guidanhsachtailieu(sachs);
//                guidanhsachtheloai(dsdm);
//                guidanhsachphieumuon(pm);
//                guidanhsachBanDoc(banDocs);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void kiemtrataikhoan(Socket s) {
//        try {
//            TaiKhoan ss = nhantTaiKhoan(s);
//            if (KetNoiSql.kiemtradocgia(ss)) {
//                guiketqua("ok");
//            }
//            if (KetNoiSql.kiemtradocgia(ss) == false) {
//                guiketqua("Thất bại");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    public void moSocket() {
        try {
            myServer = new ServerSocket(port);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public Object nhanphanhoi(Socket s) {
        Object phanhoi = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            phanhoi = (Object) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return phanhoi;

    }

    public String nhanyeucau(Socket s) {
        String yeucau = "";
        try {
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            yeucau = (String) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return yeucau;

    }

    public TaiKhoan nhantTaiKhoan(Socket s) {
        TaiKhoan tk = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            tk = (TaiKhoan) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tk;

    }

    public DocGia nhanBanDoc(Socket s) {
        DocGia banDoc = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            banDoc = (DocGia) ois.readObject();
        } catch (Exception e) {

        }

        return banDoc;

    }

    public PhieuMuon nhanPhieuMuon(Socket s) {
        PhieuMuon phieuMuon = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            phieuMuon = (PhieuMuon) ois.readObject();
        } catch (Exception e) {

        }

        return phieuMuon;

    }

    private void guidanhsachnxb(List<String> layTennxbSql) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream((list.get(list.size() - 1)).getOutputStream());
            oos.writeObject(layTennxbSql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
