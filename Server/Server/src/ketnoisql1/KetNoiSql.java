/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ketnoisql1;

import Exception.InvalidDobException;
import Exception.InvalidEmailException;
import Exception.InvalidGiaException;
import Exception.InvalidNamSanXuatException;
import Exception.InvalidNameException;
import Exception.InvalidPhoneNumberException;
import Exception.InvalidSoLuongException;
import Exception.InvalidusernameException;
import Model.DocGia;
import Model.ChiTietPhieuMuon;
import Model.NhaXuatBan;
import Model.PhieuMuon;
import Model.TaiLieu;
import Model.TaiKhoan;
import Model.DanhMucTaiLieu;
import Model.ThuThu;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import thongketienphat.ThongKeTienPhat;

public class KetNoiSql {

    private static final String server = "ADMIN-PC\\SQLEXPRESS";
    private static final int port = 1433;
    private static final String user = "sa";
    private static final String db = "project";
    private static final String password = "123";

    public static SQLServerDataSource configDataSource() {
        SQLServerDataSource dataSource = new SQLServerDataSource();

        dataSource.setUser(user);
        dataSource.setPassword(password);
        dataSource.setDatabaseName(db);
        dataSource.setPortNumber(port);
        dataSource.setServerName(server);
        dataSource.setEncrypt(false);
        return dataSource;
    }
//    public static void main(String[] args) {
//        System.out.println("đang chạy");
//        System.out.print(tailieuhuhong());
//    }

//        try {
//            String[] columns = {"Mã", "Họ Và Tên", "Ngày sinh", "Địa chỉ"};
//            XSSFWorkbook workbook = new XSSFWorkbook();
//            var sheet = workbook.createSheet("Độc giả");
//            //set font
//            Font headerFont = workbook.createFont();
//            headerFont.setBold(true);
//            headerFont.setFontHeightInPoints((short) 14);
//            headerFont.setColor(IndexedColors.RED.getIndex());
//            CellStyle headerCellStyle = workbook.createCellStyle();
//            headerCellStyle.setFont(headerFont);
//            // tạo hàng
//            Row headerRow = sheet.createRow(0);
//
//// Create cells
//            for (int i = 0; i < columns.length; i++) {
//                Cell cell = headerRow.createCell(i);
//                cell.setCellValue(columns[i]);
//                cell.setCellStyle(headerCellStyle);
//            }
//            int rowNum = 1;
//            //for (DocGia employee : banDocs) {
//                Row row = sheet.createRow(rowNum++);
//
//                // Employee's name (Column A)
//                row.createCell(0)
//                        .setCellValue("Mã 001");
//
//                // Employee's email (Column B)
//                row.createCell(1)
//                        .setCellValue("Nguyễn Bá Cương");
//
//                // Employee's date of birth (Column C)
//                row.createCell(2)
//                        .setCellValue("02/11/2014");
//                row.createCell(3)
//                        .setCellValue("Hà Nội");
//
//            //}
//            FileOutputStream fileOut = new FileOutputStream("C:\\Users\\Admin\\Desktop\\bacuong.xlsx");
//            workbook.write(fileOut);
//            fileOut.close();
//
//            workbook.close();
//        } catch (IOException ex) {
//        }
//            
//            
//       // try {
////            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
////            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
////            var ds = configDataSource();
////            var conn = ds.getConnection();
////            System.out.println("kết nối thành công");
////            long a = 10;
////            long b = a + 5;
////            System.out.println(b);
////            var e = KetNoiSql.readNgayMuonSql((1));
////            var g = (KetNoiSql.readSoNgayMuonSql((1)));
//////          
//////            var ngaymuon = LocalDate.parse(e, dtf);
//////            var ngayphaitra = (ngaymuon.plusDays(g));
//////            var ngaythuctra = LocalDate.parse("02/02/2023", dtf);
//////            var songaytre = ChronoUnit.DAYS.between( ngayphaitra, ngaythuctra);
//////            System.out.println("ngày mượn"+ ngaymuon.format(dtf));
//////            System.out.println("ngày trả"+ ngayphaitra.format(dtf));
//////             System.out.println("ngày thực trả"+ ngaythuctra.format(dtf));
//////            System.out.println("số ngày:" + songaytre);
////            LocalDate date1 = LocalDate.parse("01/03/2025", dtf);
////            LocalDate date2 = LocalDate.parse("25/02/2025", dtf);
////            var numOfDay = ChronoUnit.DAYS.between(date1, date2); // date2 - date1
//////        System.out.println(
//////                " là: " + numOfDay);
////
////        } catch (SQLServerException ex) {
////            System.out.println("thất bại");
////        }
//    }
//   public static int laymathuthu(String tendangnhap, String matkhau) {
//        SQLServerDataSource ds = configDataSource();
//
//        int r = 0;
//        try ( var conn = ds.getConnection()) {
//            var sql = "SELECT Ma FROM dbo.DocGia WHERE Tendangnhap = " + "N'" + tendangnhap + "'" + " AND Matkhau= " + "N'" + matkhau + "'";
//            var ps = conn.prepareStatement(sql);
//            var rs = ps.executeQuery();
//            rs.next();
//            return rs.getInt(1);
//
//        } catch (SQLServerException ex) {
//            ex.printStackTrace();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return 0;
//
//    }
public static DocGia laymotdocgia(int madg) {
        DocGia docGia = new DocGia();
        SQLServerDataSource ds = configDataSource();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try ( var conn = ds.getConnection()) {
            var sql = "SELECT * FROM dbo.DocGia WHERE  LIKE '%" + madg + "%'";
            var ps = conn.prepareStatement(sql);
            var rs = ps.executeQuery();
            while (rs.next()) {
                var ma = rs.getInt(1);
                var password = rs.getString(2);
                var tendn = rs.getString(3);
                var fullName = rs.getString(4);
                var dob = rs.getString(5);
                var email = rs.getString(6);
                var address = rs.getString(7);
                var phoneNumber = rs.getString(8);
                var status = rs.getString(9);
                //var slmuon = rs.getInt(9);
                docGia = new DocGia(ma, password, tendn,
                        address, phoneNumber, fullName, email, dob, status);
               
            }
        } catch (SQLServerException ex) {
            //ex.printStackTrace();
        } catch (SQLException ex) {
            // ex.printStackTrace();
        } catch (InvalidPhoneNumberException ex) {
        } catch (InvalidEmailException ex) {
        } catch (InvalidDobException ex) {
        } catch (InvalidNameException ex) {
        } catch (ParseException ex) {
        } catch (InvalidusernameException ex) {
        }
        return docGia;
    }





//Tính số tiền phạt theo tháng
 public static List<ThongKeTienPhat> thongketienphattheonam() {
        SQLServerDataSource ds = configDataSource();
        List<ThongKeTienPhat> a = new ArrayList<>();
        try ( var conn = ds.getConnection()) {
            var sql = "select Sum (Tienphat) as [số tiền phạt], YEAR (Ngaytra) as [Năm]\n" +
"from ChiTietPhieuMuon\n" +
"group by YEAR (Ngaytra)";
            var ps = conn.prepareStatement(sql);
            var rs = ps.executeQuery();
           while (rs.next()){
           var tienphat = rs.getInt(1);
           var nam = rs.getInt(2);
           ThongKeTienPhat b = new ThongKeTienPhat(tienphat, nam);
           if(b.getNam()!=1900){
           a.add(b);
           }
           }
        } catch (SQLServerException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return a;

    }      
    
 public static int sotailieuhuhong10(int nam) {
        SQLServerDataSource ds = configDataSource();
        int r = 0;
        try ( var conn = ds.getConnection()) {
            var sql = "select count(Matailieu) as [Tổng Tài liệu]\n" +
"from ChiTietPhieuMuon\n" +
"where Tinhtrangtailieu = N'Hư Hỏng 10%' and YEAR (Ngaytra) ='"+ nam+ "'";
            var ps = conn.prepareStatement(sql);
            var rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);

        } catch (SQLServerException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;

    }   
public static int sotailieuhuhong30(int nam) {
        SQLServerDataSource ds = configDataSource();
        int r = 0;
        try ( var conn = ds.getConnection()) {
            var sql = "select count(Matailieu) as [Tổng Tài liệu]\n" +
"from ChiTietPhieuMuon\n" +
"where Tinhtrangtailieu = N'Hư Hỏng 30%' and YEAR (Ngaytra) ='"+ nam+ "'";
            var ps = conn.prepareStatement(sql);
            var rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);

        } catch (SQLServerException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;

    }
public static int sotailieuhuhong50(int nam) {
        SQLServerDataSource ds = configDataSource();
        int r = 0;
        try ( var conn = ds.getConnection()) {
            var sql = "select count(Matailieu) as [Tổng Tài liệu]\n" +
"from ChiTietPhieuMuon\n" +
"where Tinhtrangtailieu = N'Hư Hỏng 50%' and YEAR (Ngaytra) ='"+ nam+ "'";
            var ps = conn.prepareStatement(sql);
            var rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);

        } catch (SQLServerException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;

    }
public static int sotailieuhuhong70(int nam) {
        SQLServerDataSource ds = configDataSource();
        int r = 0;
        try ( var conn = ds.getConnection()) {
            var sql = "select count(Matailieu) as [Tổng Tài liệu]\n" +
"from ChiTietPhieuMuon\n" +
"where Tinhtrangtailieu = N'Hư Hỏng 70%' and YEAR (Ngaytra) ='"+ nam+ "'";
            var ps = conn.prepareStatement(sql);
            var rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);

        } catch (SQLServerException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;

    }
public static int sotailieumat(int nam) {
        SQLServerDataSource ds = configDataSource();
        int r = 0;
        try ( var conn = ds.getConnection()) {
            var sql = "select count(Matailieu) as [Tổng Tài liệu]\n" +
"from ChiTietPhieuMuon\n" +
"where Tinhtrangtailieu = N'Mất Sách' and YEAR (Ngaytra) ='"+ nam+ "'";
            var ps = conn.prepareStatement(sql);
            var rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);

        } catch (SQLServerException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;

    }   
    public static int tinhsotienphatthang2() {
        SQLServerDataSource ds = configDataSource();
        int r = 0;
        try ( var conn = ds.getConnection()) {
            var sql = "select Sum(Tienphat) as [Tổng Tiền Phạt]\n" +
"from ChiTietPhieuMuon\n" +
"where MONTH (Ngaytra) = '02'";
            var ps = conn.prepareStatement(sql);
            var rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);

        } catch (SQLServerException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;

    }
    public static int tinhsotienphatthang3() {
        SQLServerDataSource ds = configDataSource();
        int r = 0;
        try ( var conn = ds.getConnection()) {
            var sql = "select Sum(Tienphat) as [Tổng Tiền Phạt]\n" +
"from ChiTietPhieuMuon\n" +
"where MONTH (Ngaytra) = '03'";
            var ps = conn.prepareStatement(sql);
            var rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);

        } catch (SQLServerException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;

    }   
    public static int soluongdocgia() {
        SQLServerDataSource ds = configDataSource();

        int r = 0;
        try ( var conn = ds.getConnection()) {
            var sql = "select count(DocGia.Madocgia ) as [Số Lượng]\n"
                    + "from DocGia";
            var ps = conn.prepareStatement(sql);
            var rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);

        } catch (SQLServerException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;

    }
public static int docgiachuatra() {
        SQLServerDataSource ds = configDataSource();

        int r = 0;
        try ( var conn = ds.getConnection()) {
            var sql = "select count (Trangthai) as [số độc giả]\n" +
"from PhieuMuon\n" +
"where Trangthai like  N'%Chưa trả%'";
            var ps = conn.prepareStatement(sql);
            var rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);

        } catch (SQLServerException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;

    }
public static int docgiadanggiahan() {
        SQLServerDataSource ds = configDataSource();

        //int r = 0;
        try ( var conn = ds.getConnection()) {
            var sql = "select count (Trangthai) as [số độc giả]\n" +
"from PhieuMuon\n" +
"where Trangthai like  N'%Đang gia hạn%'";
            var ps = conn.prepareStatement(sql);
            var rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);

        } catch (SQLServerException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;

    }
    public static int soluongtailieu() {
        SQLServerDataSource ds = configDataSource();

        int r = 0;
        try ( var conn = ds.getConnection()) {
            var sql = "select count(TaiLieu.Matailieu ) as [Số Lượng]\n"
                    + "from TaiLieu";
            var ps = conn.prepareStatement(sql);
            var rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);

        } catch (SQLServerException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;

    }

    public static int soluongdanhmuc() {
        SQLServerDataSource ds = configDataSource();

        int r = 0;
        try ( var conn = ds.getConnection()) {
            var sql = "select count(DanhMucTaiLieu.Ma ) as [Số Lượng]\n"
                    + "from DanhMucTaiLieu";
            var ps = conn.prepareStatement(sql);
            var rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);

        } catch (SQLServerException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;

    }

    public static int soluongnxb() {
        SQLServerDataSource ds = configDataSource();

        int r = 0;
        try ( var conn = ds.getConnection()) {
            var sql = "select count(NhaXuatBan.Ma ) as [Số Lượng]\n"
                    + "from NhaXuatBan";
            var ps = conn.prepareStatement(sql);
            var rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);

        } catch (SQLServerException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;

    }

    public static int soluongphieumuon() {
        SQLServerDataSource ds = configDataSource();

        int r = 0;
        try ( var conn = ds.getConnection()) {
            var sql = "select count(PhieuMuon.Maphieumuon ) as [Số Lượng]\n"
                    + "from PhieuMuon";
            var ps = conn.prepareStatement(sql);
            var rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);

        } catch (SQLServerException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;

    }

    public static boolean kiemtrathuthu(TaiKhoan taiKhoan) {

        SQLServerDataSource ds = configDataSource();
        List<TaiKhoan> taiKhoanthuthu = new ArrayList<>();
        try ( var conn = ds.getConnection()) {
            var sql = "SELECT Matkhau, Ma FROM dbo.ThuThu";
            var ps = conn.prepareStatement(sql);
            var rs = ps.executeQuery();
            while (rs.next()) {
                var a = rs.getString(1);
                var b = rs.getString(2);
                var taikhoan1 = new TaiKhoan(b, a);
                taiKhoanthuthu.add(taikhoan1);

            }

        } catch (SQLServerException ex) {
        } catch (SQLException ex) {
        }
        for (TaiKhoan s : taiKhoanthuthu) {
            if (s.getTenDangNhap().trim().compareToIgnoreCase(taiKhoan.getTenDangNhap()) == 0
                    && s.getMatKhau().trim().compareToIgnoreCase(taiKhoan.getMatKhau()) == 0) {
                return true;
            }
        }
        return false;
    }

    public static boolean kiemtradocgia(TaiKhoan s) {
        var ds = KetNoiSql.configDataSource();
        List<TaiKhoan> taiKhoanbandoc = new ArrayList<>();
        try ( var conn = ds.getConnection()) {
            var sql = "SELECT Tendangnhap,Matkhau FROM dbo.DocGia ";
            var ps = conn.prepareStatement(sql);
            var rs = ps.executeQuery();
            while (rs.next()) {
                var a = rs.getString(1).trim();
                var b = rs.getString(2).trim();
                var tt = new TaiKhoan(a, b);
                taiKhoanbandoc.add(tt);
            }
            for (TaiKhoan t : taiKhoanbandoc) {
                if (t.getTenDangNhap().compareToIgnoreCase(s.getTenDangNhap()) == 0
                        && t.getMatKhau().compareToIgnoreCase(s.getMatKhau()) == 0) {
                    return true;
                }
            }
        } catch (SQLServerException ex) {
        } catch (SQLException ex) {
        }
        return false;
    }

    //phiếu Mượn
    public static void capnhattrangthaiphieumuonSql(String maphieu, String trangthai) {
        SQLServerDataSource ds = configDataSource();
        //SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        try ( var conn = ds.getConnection()) {
            var sql = "UPDATE dbo.PhieuMuon SET "
                    + "Trangthai= N'" + trangthai + "' WHERE Maphieumuon =?";
            var ps = conn.prepareStatement(sql);
            ps.setString(1, maphieu);
            ps.executeUpdate();

        } catch (SQLServerException ex) {

        } catch (SQLException ex) {

        }
    }

    public static void capnhatsongaymuonchophieumuon(String maphieu, int ngay) {
        SQLServerDataSource ds = configDataSource();
        //SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        try ( var conn = ds.getConnection()) {
            var sql = "UPDATE dbo.PhieuMuon SET "
                    + "Songaymuon = N'" + ngay + "' WHERE Maphieumuon =?";
            var ps = conn.prepareStatement(sql);
            ps.setString(1, maphieu);
            ps.executeUpdate();

        } catch (SQLServerException ex) {

        } catch (SQLException ex) {

        }
    }

    public static void savePhieuMuonSql(PhieuMuon phieuMuon) {
        SQLServerDataSource ds = configDataSource();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try ( var conn = ds.getConnection()) {
            var sql = "INSERT INTO dbo.PhieuMuon VALUES (?,?,?,?,?,?,?,?)";
            var ps = conn.prepareStatement(sql);
            ps.setString(1, String.valueOf(phieuMuon.getMaPM()));
            ps.setInt(2, phieuMuon.getMaBanDoc());
            ps.setString(3, phieuMuon.getTenBanDoc());
            ps.setString(4, simpleDateFormat.format(phieuMuon.getNgayMuon()));
            ps.setInt(5, phieuMuon.getSoNgayMuon());
            ps.setInt(6, phieuMuon.getSoSachMuon());
            ps.setString(7, phieuMuon.getTrangThai());
            ps.setString(8, phieuMuon.getMathuthu());
            ps.executeUpdate();

        } catch (SQLServerException ex) {

        } catch (SQLException ex) {

        }
    }

    public static List<PhieuMuon> readPhieuMuonFromSql() {
        List<PhieuMuon> phieuMuons = new ArrayList<>();
        SQLServerDataSource ds = configDataSource();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try ( var conn = ds.getConnection()) {
            var sql = "SELECT * FROM dbo.PhieuMuon";
            var ps = conn.prepareStatement(sql);
            var rs = ps.executeQuery();
            while (rs.next()) {
                var mapm = Integer.parseInt(rs.getString(1));
                var madg = rs.getInt(2);
                var tendg = rs.getString(3);
                var ngaymuon = rs.getString(4);
                var songaymuon = rs.getInt(5);
                var sosachmuon = rs.getInt(6);
                var trangthai = rs.getString(7);
                var mathuthu = rs.getString(8);
                PhieuMuon phieuMuon = new PhieuMuon(mapm, madg,
                        tendg, simpleDateFormat.parse(ngaymuon),
                        songaymuon, sosachmuon, trangthai, mathuthu);

                phieuMuons.add(phieuMuon);
            }
        } catch (SQLServerException ex) {
            //ex.printStackTrace();
        } catch (SQLException ex) {
            // ex.printStackTrace();
        } catch (ParseException ex) {
        }
        return phieuMuons;
    }

    public static List<Integer> readMaPhieuMuonFromSql() {
        List<Integer> maphieuMuons = new ArrayList<>();
        SQLServerDataSource ds = configDataSource();
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try ( var conn = ds.getConnection()) {
            var sql = "SELECT Maphieumuon FROM dbo.PhieuMuon";
            var ps = conn.prepareStatement(sql);
            var rs = ps.executeQuery();
            while (rs.next()) {
                var mapm = Integer.parseInt(rs.getString(1));
                maphieuMuons.add(mapm);
            }
        } catch (SQLServerException ex) {
            //ex.printStackTrace();
        } catch (SQLException ex) {
            // ex.printStackTrace();
        }
        return maphieuMuons;
    }

    public static String readNgayMuonSql(int maphieumuon) {
        String ngaymuon = "";
        SQLServerDataSource ds = configDataSource();
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try ( var conn = ds.getConnection()) {
            var sql = "SELECT Ngaymuon FROM dbo.PhieuMuon WHERE Maphieumuon like '%" + maphieumuon + "%'";
            var ps = conn.prepareStatement(sql);
            var rs = ps.executeQuery();
            while (rs.next()) {
                ngaymuon = (rs.getString(1));

            }
        } catch (SQLServerException ex) {

        } catch (SQLException ex) {

        }
        return ngaymuon;
    }

    public static int readSoNgayMuonSql(int maphieumuon) {
        int songaymuon = 0;
        SQLServerDataSource ds = configDataSource();
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try ( var conn = ds.getConnection()) {
            var sql = "SELECT Songaymuon FROM dbo.PhieuMuon WHERE Maphieumuon like '%" + maphieumuon + "%'";
            var ps = conn.prepareStatement(sql);
            var rs = ps.executeQuery();
            while (rs.next()) {
                songaymuon = (rs.getInt(1));

            }
        } catch (SQLServerException ex) {

        } catch (SQLException ex) {

        }
        return songaymuon;
    }

    public static void xoaPhieuMuonSql(PhieuMuon pm) {
        SQLServerDataSource ds = configDataSource();
        try ( var conn = ds.getConnection()) {
            var sql = "DELETE FROM dbo.PhieuMuon WHERE Maphieumuon = ?";

            var ps = conn.prepareStatement(sql);
            ps.setString(1, String.valueOf(pm.getMaPM()));

            ps.executeUpdate();

        } catch (SQLServerException ex) {

        } catch (SQLException ex) {

        }
    }

    public static String laytinhtrangsachctpmSql(int maphieumuon, String masach) {
        var tinhtrang = "";
        SQLServerDataSource ds = configDataSource();
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try ( var conn = ds.getConnection()) {
            var sql = "SELECT Tinhtrangtailieu FROM dbo.ChiTietPhieuMuon WHERE Maphieumuon like '%" + maphieumuon + "%' AND Matailieu like '%" + masach + "%'";
            var ps = conn.prepareStatement(sql);
            var rs = ps.executeQuery();
            while (rs.next()) {
                tinhtrang = (rs.getString(1));

            }
        } catch (SQLServerException ex) {

        } catch (SQLException ex) {

        }
        return tinhtrang;
    }
//   

//Thủ Thư
    public static List<ThuThu> readThuthusFromSql() {
        List<ThuThu> thuthus = new ArrayList<>();
        SQLServerDataSource ds = configDataSource();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try ( var conn = ds.getConnection()) {
            var sql = "SELECT * FROM dbo.ThuThu";
            var ps = conn.prepareStatement(sql);
            var rs = ps.executeQuery();
            while (rs.next()) {
                var id = rs.getString(1);
                var password = rs.getString(2);
                var fullName = rs.getString(3);
                var dob = rs.getString(4);
                var address = rs.getString(5);
                var phoneNumber = rs.getString(6);
                var email = rs.getString(7);
                ThuThu thuthu = new ThuThu(id, password,
                        address, phoneNumber, fullName, email, dob);

                thuthus.add(thuthu);
            }
        } catch (SQLServerException ex) {
            //ex.printStackTrace();
        } catch (SQLException ex) {
            // ex.printStackTrace();
        } catch (InvalidPhoneNumberException ex) {
        } catch (InvalidEmailException ex) {
        } catch (InvalidDobException ex) {
        } catch (InvalidNameException ex) {
        } catch (ParseException ex) {
        }
        return thuthus;
    }
//Sách

    public static List<TaiLieu> readSachFromSql1() {
        List<TaiLieu> sachs = new ArrayList<>();
        SQLServerDataSource ds = configDataSource();

        try ( var conn = ds.getConnection()) {
            var sql = "SELECT * FROM dbo.TaiLieu";
            var ps = conn.prepareStatement(sql);
            var rs = ps.executeQuery();
            while (rs.next()) {
                var ma = rs.getString(1);
                var ten = rs.getString(2);
                var madm = rs.getString(3);
                var tacgia = rs.getString(4);
                var manxb = rs.getString(5);
                var namxb = rs.getString(6);
                var slcon = rs.getString(7);
                var nd = rs.getString(8);
                var gia = rs.getString(9);
                var a = KetNoiSql.laytendanhmucduavaoma(madm);
                var b = KetNoiSql.laytennxbduavaoma(manxb);
                TaiLieu sach = new TaiLieu(ma, ten, a,
                        tacgia, b, namxb, slcon, nd, gia);

                sachs.add(sach);
            }
        } catch (SQLServerException ex) {
            //ex.printStackTrace();
        } catch (SQLException ex) {
            // ex.printStackTrace();
        } catch (InvalidSoLuongException ex) {
        } catch (InvalidGiaException ex) {
        } catch (InvalidNamSanXuatException ex) {
        }
        return sachs;
    }

    public static List<TaiLieu> readSachFromSql() {
        List<TaiLieu> sachs = new ArrayList<>();
        SQLServerDataSource ds = configDataSource();

        try ( var conn = ds.getConnection()) {
            var sql = "SELECT * FROM dbo.TaiLieu";
            var ps = conn.prepareStatement(sql);
            var rs = ps.executeQuery();
            while (rs.next()) {
                var ma = rs.getString(1);
                var ten = rs.getString(2);
                
                var tentl = rs.getString(3);
                var tacgia = rs.getString(4);
                var nhaxb = rs.getString(5);
                var namxb = rs.getString(6);
                var slcon = rs.getString(7);
                var nd = rs.getString(8);
                var gia = rs.getString(9);
                TaiLieu sach = new TaiLieu(ma, ten, tentl,
                        tacgia, nhaxb, namxb, slcon, nd, gia);

                sachs.add(sach);
            }
        } catch (SQLServerException ex) {
            //ex.printStackTrace();
        } catch (SQLException ex) {
            // ex.printStackTrace();
        } catch (InvalidSoLuongException ex) {
        } catch (InvalidGiaException ex) {
        } catch (InvalidNamSanXuatException ex) {
        }
        return sachs;
    }

    public static TaiLieu layMotSachFromSql(String masach) {
        TaiLieu sach = null;
        SQLServerDataSource ds = configDataSource();
        try ( var conn = ds.getConnection()) {
            var sql = "SELECT * FROM dbo.TaiLieu WHERE Matailieu LIKE '%" + masach + "%' ";
            var ps = conn.prepareStatement(sql);
            var rs = ps.executeQuery();
            while (rs.next()) {
                var ma = rs.getString(1);
                var ten = rs.getString(2);
                
                var tentl = rs.getString(3);
                var tacgia = rs.getString(4);
                var nhaxb = rs.getString(5);
                var namxb = rs.getString(6);
                var slcon = rs.getString(7);
                var nd = rs.getString(8);
                var gia = rs.getString(9);
                sach = new TaiLieu(ma, ten, tentl,
                        tacgia, nhaxb, namxb, slcon, nd, gia);

            }
        } catch (SQLServerException ex) {

        } catch (SQLException ex) {

        } catch (InvalidSoLuongException ex) {
        } catch (InvalidGiaException ex) {
        } catch (InvalidNamSanXuatException ex) {
        }
        return sach;
    }

    public static void xoaSachSql(TaiLieu sach) {
        SQLServerDataSource ds = configDataSource();
        try ( var conn = ds.getConnection()) {
            var sql = "DELETE FROM dbo.TaiLieu WHERE Matailieu = ?";

            var ps = conn.prepareStatement(sql);
            ps.setString(1, sach.getMatl());

            ps.executeUpdate();

        } catch (SQLServerException ex) {

        } catch (SQLException ex) {

        }
    }

    public static void saveSachSql(TaiLieu sach) {
        SQLServerDataSource ds = configDataSource();
        try ( var conn = ds.getConnection()) {
            var sql = "INSERT INTO dbo.TaiLieu VALUES (?,?,?,?,?,?,?,?,?)";
            var ps = conn.prepareStatement(sql);
            ps.setString(1, sach.getMatl());
            ps.setString(2, sach.getTentl());           
            ps.setString(3, sach.getMadanhmuc());
            ps.setString(4, sach.getTacGia());
            ps.setString(5, sach.getManxb());
            ps.setString(6, (sach.getNamXuatBan()));
            ps.setString(7, (sach.getSoLuongCon()));
            ps.setString(8, sach.getTomTatND());
            ps.setString(9, (sach.getGiasach()));
            ps.executeUpdate();

        } catch (SQLServerException ex) {
ex.printStackTrace();
        } catch (SQLException ex) {

        }
    }

    public static void suathongtinsachSql(TaiLieu sach) {
        SQLServerDataSource ds = configDataSource();
        try ( var conn = ds.getConnection()) {
            var sql = "UPDATE dbo.TaiLieu SET Matailieu=?,Tentailieu=?,Madanhmuc=?,Tacgia=?,"
                    + "Manhaxuatban=?,Namxuatban=?,Soluongcon=?,Tomtatnoidung=?,Gia = ?"
                    + " WHERE Matailieu=?";
            var ps = conn.prepareStatement(sql);

            ps.setString(1, sach.getMatl());
            ps.setString(2, sach.getTentl());
            //ps.setString(3, sach.getTendanhmuc());
            ps.setString(3, sach.getMadanhmuc());
            ps.setString(4, sach.getTacGia());
            ps.setString(5, sach.getManxb());
            ps.setString(6, (sach.getNamXuatBan()));
            ps.setString(7, (sach.getSoLuongCon()));
            ps.setString(8, sach.getTomTatND());
            ps.setString(9, (sach.getGiasach()));
            ps.setString(10, sach.getMatl());
            ps.executeUpdate();

        } catch (SQLServerException ex) {

        } catch (SQLException ex) {

        }
    }

    public static void capnhatsoluongconcuasachSql(String masach, int soluongcon) {
        SQLServerDataSource ds = configDataSource();
        try ( var conn = ds.getConnection()) {
            var sql = "UPDATE dbo.TaiLieu SET Soluongcon= ? WHERE Matailieu=?";
            var ps = conn.prepareStatement(sql);

            ps.setString(1, String.valueOf(soluongcon));

            ps.setString(2, masach);
            ps.executeUpdate();

        } catch (SQLServerException ex) {

        } catch (SQLException ex) {

        }
    }

    public static void capnhatsosachcontrongsachs(String masach, int sosachcon) {
        SQLServerDataSource ds = configDataSource();
        //SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        try ( var conn = ds.getConnection()) {
            var sql = "UPDATE dbo.TaiLieu SET "
                    + "Soluongcon=? WHERE Matailieu=?";
            var ps = conn.prepareStatement(sql);
            ps.setString(1, String.valueOf(sosachcon));
            
            ps.setString(2, masach);
            ps.executeUpdate();

        } catch (SQLServerException ex) {

        } catch (SQLException ex) {

        }
    }

    public static ResultSet sachmuonnhieunhat() {
        SQLServerDataSource ds = configDataSource();
        var sql = "select  top 5 with ties TaiLieu.Matailieu, TaiLieu.Tentailieu, Count( ChiTietPhieuMuon.Matailieu) as [Số lượt mượn]\n"
                + "from TaiLieu, ChiTietPhieuMUon\n"
                + "where TaiLieu.Matailieu = ChiTietPhieuMuon.Matailieu\n"
                + "group by TaiLieu.Matailieu, TaiLieu.Tentailieu \n"
                + "order by [Số lượt mượn] desc";
        try {
            PreparedStatement pre = ds.getConnection().prepareStatement(sql);
            ResultSet result = pre.executeQuery();

            return result;
        } catch (SQLException ex) {
        }
        return null;
    }

    public static ResultSet tailieuhuhong() {
        SQLServerDataSource ds = configDataSource();
        var sql = "select TaiLieu.Matailieu, Tentailieu, Madanhmuc, Tacgia, Manhaxuatban, Namxuatban ,COUNT(ChiTietPhieuMuon.Tinhtrangtailieu) as [Số Lượng Hỏng]\n" +
" from TaiLieu, ChiTietPhieuMuon\n" +
"where TaiLieu.Matailieu = ChiTietPhieuMuon.Matailieu and ChiTietPhieuMuon.Tinhtrangtailieu like N'%Hư Hỏng%'\n" +
"group by TaiLieu.Matailieu, Tentailieu, Madanhmuc, Tacgia, Manhaxuatban, Namxuatban\n" +
" order by [Số Lượng Hỏng] desc";
        try {
            PreparedStatement ps = ds.getConnection().prepareStatement(sql);
            ResultSet result = ps.executeQuery();
            return result;
        } catch (SQLException ex) {
        }
        return null;

    }

    public static ResultSet tailieubimat() {
        SQLServerDataSource ds = configDataSource();
        var sql = "select  TaiLieu.Matailieu, Tentailieu, Madanhmuc, Tacgia, Manhaxuatban, Namxuatban ,COUNT(ChiTietPhieuMuon.Tinhtrangtailieu) as [Số Lượng Mất]\n"
                + "from TaiLieu, Chitietphieumuon\n"
                + "where TaiLieu.Matailieu = ChiTietPhieuMuon.Matailieu and ChiTietPhieuMuon.Tinhtrangtailieu like N'%Mất%Sách%'\n"
                + "group by TaiLieu.Matailieu, Tentailieu, Madanhmuc, Tacgia, Manhaxuatban, Namxuatban\n"
                + "order by [Số Lượng Mất] desc";
        try {
            PreparedStatement ps = ds.getConnection().prepareStatement(sql);
            ResultSet result = ps.executeQuery();
            return result;
        } catch (SQLException ex) {
        }
        return null;

    }

    public static int laysoluongconcuaSachSql(String masach) {
        int soluongcon = 0;
        SQLServerDataSource ds = configDataSource();
        try ( var conn = ds.getConnection()) {
            var sql = "SELECT Soluongcon FROM dbo.TaiLieu WHERE Matailieu LIKE '%" + masach + "%' ";
            var ps = conn.prepareStatement(sql);
            var rs = ps.executeQuery();
            while (rs.next()) {

                soluongcon = rs.getInt(1);

            }
        } catch (SQLServerException ex) {

        } catch (SQLException ex) {

        }
        return soluongcon;
    }
//Độc Giả

    public static ResultSet docgiachuatrasach() {
        SQLServerDataSource ds = configDataSource();
        var sql = "select Maphieumuon, Madocgia,Tendocgia,Ngaymuon,Songaymuon, Sotailieumuon, Trangthai\n"
                + "from PhieuMuon\n"
                + "where Trangthai like N'%Đang gia hạn%' or Trangthai like N'%Chưa trả%'";
        try {
            PreparedStatement ps = ds.getConnection().prepareStatement(sql);
            ResultSet result = ps.executeQuery();

            return result;
        } catch (SQLException ex) {
        }
        return null;
    }

    public static ResultSet docgiaquahantra() {
        SQLServerDataSource ds = configDataSource();
        var sql = "select DocGia.Madocgia ,DocGia.Tendocgia,PhieuMuon.Ngaymuon, PhieuMuon.Songaymuon, ChiTietPhieuMuon.Ngaytra from DocGia, PhieuMuon, ChiTietPhieuMuon\n"
                + "where DocGia.Madocgia = PhieuMuon.Madocgia and PhieuMuon.Maphieumuon = ChiTietPhieuMuon.Maphieumuon";
        try {
            PreparedStatement pre = ds.getConnection().prepareStatement(sql);
            ResultSet result = pre.executeQuery();
            return result;
        } catch (SQLException ex) {
        }
        return null;
    }

    public static ResultSet docgiamuonnhieunhat() {
        SQLServerDataSource ds = configDataSource();
        var sql = "select top 5 with ties DocGia.Madocgia, DocGia.Tendocgia,DocGia.Ngaysinh,DocGia.Email,DocGia.Diachi,DocGia.Sodienthoai, COUNT(PhieuMuon.Madocgia) as [số lần mượn] from\n"
                + "PhieuMuon join DocGia on PhieuMuon.Madocgia = DocGia.Madocgia\n"
                + "group by DocGia.Madocgia, DocGia.Tendocgia,DocGia.Ngaysinh,DocGia.Email,DocGia.Diachi,DocGia.Sodienthoai\n"
                + "order by [số lần mượn] desc";
        try {
            PreparedStatement pre = ds.getConnection().prepareStatement(sql);
            ResultSet result = pre.executeQuery();

            return result;
        } catch (SQLException ex) {
        }
        return null;

    }

    public static int laymadocgia(String tendangnhap, String matkhau) {
        SQLServerDataSource ds = configDataSource();

        int r = 0;
        try ( var conn = ds.getConnection()) {
            var sql = "SELECT Madocgia FROM dbo.DocGia WHERE Tendangnhap = " + "N'" + tendangnhap + "'" + " AND Matkhau= " + "N'" + matkhau + "'";
            var ps = conn.prepareStatement(sql);
            var rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);

        } catch (SQLServerException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;

    }

    public static String laystatusdocgia(String tendangnhap, String matkhau) {
        SQLServerDataSource ds = configDataSource();

        String r = "";
        try ( var conn = ds.getConnection()) {
            var sql = "SELECT Trangthai FROM dbo.DocGia WHERE Tendangnhap= " + "N'" + tendangnhap + "'" + " AND Matkhau= " + "N'" + matkhau + "'";
            var ps = conn.prepareStatement(sql);
            var rs = ps.executeQuery();
            rs.next();
            return rs.getString(1);

        } catch (SQLServerException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return "";

    }

    public static List<DocGia> readBandocsFromSql() {
        List<DocGia> bandocs = new ArrayList<>();
        SQLServerDataSource ds = configDataSource();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try ( var conn = ds.getConnection()) {
            var sql = "SELECT * FROM dbo.DocGia";
            var ps = conn.prepareStatement(sql);
            var rs = ps.executeQuery();
            while (rs.next()) {
                var ma = rs.getInt(1);
                var password = rs.getString(2);
                var tendn = rs.getString(3);
                var fullName = rs.getString(4);
                var dob = rs.getString(5);
                var email = rs.getString(6);
                var address = rs.getString(7);
                var phoneNumber = rs.getString(8);
                var status = rs.getString(9);
                //var slmuon = rs.getInt(9);
                DocGia bandoc = new DocGia(ma, password, tendn,
                        address, phoneNumber, fullName, email, dob, status);
                bandocs.add(bandoc);
            }
        } catch (SQLServerException ex) {
            //ex.printStackTrace();
        } catch (SQLException ex) {
            // ex.printStackTrace();
        } catch (InvalidPhoneNumberException ex) {
        } catch (InvalidEmailException ex) {
        } catch (InvalidDobException ex) {
        } catch (InvalidNameException ex) {
        } catch (ParseException ex) {
        } catch (InvalidusernameException ex) {
        }
        return bandocs;
    }

    public static void saveBanDocDataToSql(DocGia banDoc) {
        SQLServerDataSource ds = configDataSource();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        // for (DocGia banDoc : banDocs) {
        try ( var conn = ds.getConnection()) {
            var sql = "INSERT INTO dbo.DocGia(Madocgia,Matkhau,Tendangnhap,Tendocgia,Ngaysinh,"
                    + "Email,Diachi,Sodienthoai, Trangthai"
                    + ") VALUES(?,?,?,?,?,?,?,?,?)";
            var ps = conn.prepareStatement(sql);

            ps.setInt(1, banDoc.getMaDocGia());
            ps.setString(2, banDoc.getMatkhau());
            ps.setString(3, banDoc.getTenDangNhap());
            ps.setString(4, banDoc.getFullName());
            ps.setString(5, simpleDateFormat.format(banDoc.getDob()));
            ps.setString(6, banDoc.getEmail());
            ps.setString(7, banDoc.getAddress());
            ps.setString(8, banDoc.getPhoneNumber());
            ps.setString(9, banDoc.getStatus());

            ps.executeUpdate();

        } catch (SQLServerException ex) {

        } catch (SQLException ex) {

        }

    }

    public static void xoaDocGiaSql(DocGia banDoc) {
        SQLServerDataSource ds = configDataSource();
        try ( var conn = ds.getConnection()) {
            var sql = "DELETE FROM dbo.DocGia WHERE Madocgia = ?";

            var ps = conn.prepareStatement(sql);
            ps.setInt(1, banDoc.getMaDocGia());

            ps.executeUpdate();

        } catch (SQLServerException ex) {

        } catch (SQLException ex) {

        }
    }

    public static void suaDocGiaSql(DocGia banDoc) {
        SQLServerDataSource ds = configDataSource();
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        try ( var conn = ds.getConnection()) {
            var sql = "UPDATE dbo.DocGia SET Tendocgia=?,Ngaysinh=?,"
                    + "Email=?,Diachi=?,Sodienthoai=?,"
                    + "Matkhau=?,Tendangnhap=?, Trangthai = ?"
                    + " WHERE Madocgia=?";
            var ps = conn.prepareStatement(sql);
            ps.setString(1, banDoc.getFullName());
            ps.setString(2, sd.format(banDoc.getDob()));
            ps.setString(3, banDoc.getEmail());
            ps.setString(4, banDoc.getAddress());
            ps.setString(5, banDoc.getPhoneNumber());

            ps.setString(6, banDoc.getMatkhau());
            ps.setString(7, banDoc.getTenDangNhap());
            ps.setString(8, banDoc.getStatus());
            ps.setInt(9, banDoc.getMaDocGia());
            ps.executeUpdate();

        } catch (SQLServerException ex) {

        } catch (SQLException ex) {

        }
    }

    public static void capnhatstatusdocgia(int ma, String status) {
        SQLServerDataSource ds = configDataSource();
        try ( var conn = ds.getConnection()) {
            var sql = "UPDATE dbo.DocGia SET "
                    + " Trangthai = ?"
                    + " WHERE Madocgia=?";
            var ps = conn.prepareStatement(sql);
            ps.setString(1, status);
            ps.setInt(2, ma);
            ps.executeUpdate();

        } catch (SQLServerException ex) {

        } catch (SQLException ex) {

        }
    }

    public static String laytendocgiaduavaoma(String ma) {
        SQLServerDataSource ds = configDataSource();
        var ten = "";
        try ( var conn = ds.getConnection()) {
            String sql = "select Tendocgia from DocGia where Madocgia = N'" + ma + "'";
            var ps = conn.prepareStatement(sql);
            var rs = ps.executeQuery();
            while (rs.next()) {
                ten = rs.getString(1);
            }
        } catch (SQLServerException ex) {
        } catch (SQLException ex) {
        }
        return ten;
    }
//    public static void capnhatsosachmuonchoBanDoc(int maDocGia, int sosachmuon) {
//        SQLServerDataSource ds = configDataSource();
//        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
//        try ( var conn = ds.getConnection()) {
//            var sql = "UPDATE dbo.Bandoc SET "
//                    + "[Số lượng mượn]=? WHERE [Mã bạn đọc]=?";
//            var ps = conn.prepareStatement(sql);
//            ps.setInt(1, sosachmuon);
//            ps.setInt(2, maDocGia);
//            ps.executeUpdate();
//
//        } catch (SQLServerException ex) {
//
//        } catch (SQLException ex) {
//
//        }
//    }
    //Thể Loại Sách

    public static String laymadanhmucduavaoten(String name) {
        SQLServerDataSource ds = configDataSource();
        var ma = "";
        try ( var conn = ds.getConnection()) {
            String sql = "select Ma from DanhMucTaiLieu where Ten = N'" + name + "'";
            var ps = conn.prepareStatement(sql);
            var rs = ps.executeQuery();
            while (rs.next()) {
                ma = rs.getString(1);
            }
        } catch (SQLServerException ex) {
        } catch (SQLException ex) {
        }
        return ma;
    }

    public static String laytendanhmucduavaoma(String ma) {
        SQLServerDataSource ds = configDataSource();
        var ten = "";
        try ( var conn = ds.getConnection()) {
            String sql = "select Ten from DanhMucTaiLieu where Ma = N'" + ma + "'";
            var ps = conn.prepareStatement(sql);
            var rs = ps.executeQuery();
            while (rs.next()) {
                ten = rs.getString(1);
            }
        } catch (SQLServerException ex) {
        } catch (SQLException ex) {
        }
        return ten;
    }

    public static List<DanhMucTaiLieu> readTheLoaiSachSql() {
        List<DanhMucTaiLieu> tls = new ArrayList<>();
        SQLServerDataSource ds = configDataSource();
        try ( var conn = ds.getConnection()) {
            String sql = "select * from dbo.DanhMucTaiLieu";
            var ps = conn.prepareStatement(sql);
            var rs = ps.executeQuery();
            while (rs.next()) {
                var matl = rs.getString(1);
                var tentl = rs.getString(2);
                DanhMucTaiLieu tl = new DanhMucTaiLieu(matl, tentl);
                tls.add(tl);
            }
        } catch (SQLServerException ex) {
        } catch (SQLException ex) {
        }
        return tls;
    }

    public static List<String> layTenTheLoaiSachSql() {
        List<String> tendanhmucs = new ArrayList<>();
        SQLServerDataSource ds = configDataSource();
        try ( var conn = ds.getConnection()) {
            String sql = "select Ten from dbo.DanhMucTaiLieu";
            var ps = conn.prepareStatement(sql);
            var rs = ps.executeQuery();
            while (rs.next()) {
                var tentl = rs.getString(1);
                tendanhmucs.add(tentl);

            }
        } catch (SQLServerException ex) {
        } catch (SQLException ex) {
        }
        return tendanhmucs;
    }

    public static List<String> laymadanhmuc() {
        List<String> madanhmucs = new ArrayList<>();
        SQLServerDataSource ds = configDataSource();
        try ( var conn = ds.getConnection()) {
            String sql = "select Ma from dbo.DanhMucTaiLieu";
            var ps = conn.prepareStatement(sql);
            var rs = ps.executeQuery();
            while (rs.next()) {
                var ma = rs.getString(1);
                madanhmucs.add(ma);

            }
        } catch (SQLServerException ex) {
        } catch (SQLException ex) {
        }
        return madanhmucs;
    }

    public static void themTheLoaiSach(DanhMucTaiLieu tl) {
        SQLServerDataSource ds = configDataSource();
        String sql = "INSERT INTO dbo.DanhMucTaiLieu VALUES (?,?)";
        try {
            PreparedStatement preparedStatement = ds.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, tl.getMadm());
            preparedStatement.setString(2, tl.getTendm());

            preparedStatement.executeUpdate();
        } catch (Exception e) {

        }
    }

    public static void xoatheloaisachSql(DanhMucTaiLieu tl) {
        SQLServerDataSource ds = configDataSource();
        try ( var conn = ds.getConnection()) {
            var sql = "DELETE FROM dbo.DanhMucTaiLieu WHERE Ma = ?";

            var ps = conn.prepareStatement(sql);
            ps.setString(1, tl.getMadm());

            ps.executeUpdate();

        } catch (SQLServerException ex) {

        } catch (SQLException ex) {

        }
    }

    public static void capNhatTheLoaiSach(DanhMucTaiLieu danhmuc) {
        SQLServerDataSource ds = configDataSource();

        String sql = "UPDATE dbo.DanhMucTaiLieu SET ma = ?, "
                + "Ten = ? WHERE  Ma = ?";
        try {
            PreparedStatement preparedStatement = ds.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, danhmuc.getMadm());
            preparedStatement.setString(2, danhmuc.getTendm());
            preparedStatement.setString(3, danhmuc.getMadm());

            preparedStatement.executeUpdate();
        } catch (Exception e) {

        }
    }
//Nhà xuất bản 

    public static String laymanxbduavaoten(String name) {
        SQLServerDataSource ds = configDataSource();
        var ma = "";
        try ( var conn = ds.getConnection()) {
            String sql = "select Ma from NhaXuatBan where Ten = N'" + name + "'";
            var ps = conn.prepareStatement(sql);
            var rs = ps.executeQuery();
            while (rs.next()) {
                ma = rs.getString(1);
            }
        } catch (SQLServerException ex) {
        } catch (SQLException ex) {
        }
        return ma;
    }

    public static String laytennxbduavaoma(String ma) {
        SQLServerDataSource ds = configDataSource();
        var ten = "";
        try ( var conn = ds.getConnection()) {
            String sql = "select Ten from NhaXuatBan where Ma = N'" + ma + "'";
            var ps = conn.prepareStatement(sql);
            var rs = ps.executeQuery();
            while (rs.next()) {
                ten = rs.getString(1);
            }
        } catch (SQLServerException ex) {
        } catch (SQLException ex) {
        }
        return ten;
    }

    public static List<NhaXuatBan> readnxbSql() {
        List<NhaXuatBan> nxbs = new ArrayList<>();
        SQLServerDataSource ds = configDataSource();
        try ( var conn = ds.getConnection()) {
            String sql = "select * from dbo.NhaXuatBan";
            var ps = conn.prepareStatement(sql);
            var rs = ps.executeQuery();
            while (rs.next()) {
                var ma = rs.getString(1);
                var ten = rs.getString(2);
                NhaXuatBan tl = new NhaXuatBan(ma, ten);
                nxbs.add(tl);
            }
        } catch (SQLServerException ex) {
        } catch (SQLException ex) {
        }
        return nxbs;
    }

    public static List<String> layTennxbSql() {
        List<String> nxList = new ArrayList<>();
        SQLServerDataSource ds = configDataSource();
        try ( var conn = ds.getConnection()) {
            String sql = "select Ten from dbo.NhaXuatBan";
            var ps = conn.prepareStatement(sql);
            var rs = ps.executeQuery();
            while (rs.next()) {
                var tentl = rs.getString(1);
                nxList.add(tentl);

            }
        } catch (SQLServerException ex) {
        } catch (SQLException ex) {
        }
        return nxList;
    }

    public static List<String> laymanxbSql() {
        List<String> nxList = new ArrayList<>();
        SQLServerDataSource ds = configDataSource();
        try ( var conn = ds.getConnection()) {
            String sql = "select Ma from dbo.NhaXuatBan";
            var ps = conn.prepareStatement(sql);
            var rs = ps.executeQuery();
            while (rs.next()) {
                var ma = rs.getString(1);
                nxList.add(ma);

            }
        } catch (SQLServerException ex) {
        } catch (SQLException ex) {
        }
        return nxList;
    }

    public static void themnxb(NhaXuatBan tl) {
        SQLServerDataSource ds = configDataSource();
        String sql = "INSERT INTO dbo.NhaXuatBan VALUES (?,?)";
        try {
            PreparedStatement preparedStatement = ds.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, tl.getMa());
            preparedStatement.setString(2, tl.getName());

            preparedStatement.executeUpdate();
        } catch (Exception e) {

        }
    }

    public static void xoanxbSql(NhaXuatBan tl) {
        SQLServerDataSource ds = configDataSource();
        try ( var conn = ds.getConnection()) {
            var sql = "DELETE FROM dbo.NhaXuatBan WHERE Ma = ?";

            var ps = conn.prepareStatement(sql);
            ps.setString(1, tl.getMa());

            ps.executeUpdate();

        } catch (SQLServerException ex) {
           

        } catch (SQLException ex) {

        }
    }

    public static void capNhatnxb(NhaXuatBan danhmuc) {
        SQLServerDataSource ds = configDataSource();

        String sql = "UPDATE dbo.NhaXuatBan SET Ma  = ?, "
                + "Ten = ? WHERE Ma = ?";
        try {
            PreparedStatement preparedStatement = ds.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, danhmuc.getMa());
            preparedStatement.setString(2, danhmuc.getName());
            preparedStatement.setString(3, danhmuc.getMa());

            preparedStatement.executeUpdate();
        } catch (Exception e) {

        }
    }

    //Chi Tiết Phiếu Mượn   
    public static List<String> laymasachtuchitietphieumuon(int maphieu) {
        List<String> masachs = new ArrayList<>();
        SQLServerDataSource ds = configDataSource();
        try ( var conn = ds.getConnection()) {
            String sql = "select Matailieu from dbo.Chitietphieumuon WHERE Maphieumuon = " + maphieu;
            var ps = conn.prepareStatement(sql);
            var rs = ps.executeQuery();
            while (rs.next()) {

                var masach = rs.getString(1);
                masachs.add(masach);

            }
        } catch (SQLServerException ex) {
        } catch (SQLException ex) {
        }
        return masachs;
    }

    public static void savachitietphieumuonSql(ChiTietPhieuMuon ctpm) {
        SQLServerDataSource ds = configDataSource();
        SimpleDateFormat sd = new SimpleDateFormat();
        String sql = "INSERT INTO Chitietphieumuon (Maphieumuon,Matailieu,Ngaytra,Tienphat,Tinhtrangtailieu) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = ds.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, ctpm.getMaPM());
            preparedStatement.setString(2, ctpm.getMaSach());
            preparedStatement.setString(3, (ctpm.getNgayTra()));
            preparedStatement.setString(4, ctpm.getTienPhat());
            preparedStatement.setString(5, ctpm.getTinhTrangSach());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<ChiTietPhieuMuon> readChiTietPhieuMuonSql(int maPhieuMuon) {
        List<ChiTietPhieuMuon> ctpms = new ArrayList<ChiTietPhieuMuon>();
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        SQLServerDataSource ds = configDataSource();
        try ( var conn = ds.getConnection()) {
            String sql = "SELECT * FROM dbo.Chitietphieumuon WHERE Maphieumuon ='" + maPhieuMuon + "'";
            var ps = conn.prepareStatement(sql);
            var rs = ps.executeQuery();
            while (rs.next()) {
                var maPm = rs.getInt(1);
                var maSach = rs.getString(2);
                var ngayTra = rs.getString(3);
                var tienPhat = rs.getString(4);
                var tinhTrangSach = rs.getString(5);
                ChiTietPhieuMuon ctpm = new ChiTietPhieuMuon(maPm, maSach,
                        (ngayTra), tinhTrangSach, tienPhat);
                ctpms.add(ctpm);
            }
        } catch (SQLServerException ex) {
        } catch (SQLException ex) {
        }
        return ctpms;
    }

    public static void capnhatChiTietPM(ChiTietPhieuMuon ctpm) {
        try {
            SQLServerDataSource ds = configDataSource();
            SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat sd1 = new SimpleDateFormat("yyyy-MM-dd");

            String sql = "UPDATE dbo.Chitietphieumuon SET Ngaytra = ?,Tienphat=? ,Tinhtrangtailieu = ?"
                    + " WHERE Maphieumuon = ? and Matailieu = ?";
            var a = sd.parse(ctpm.getNgayTra());
            var b = sd1.format(a);
            try {
                PreparedStatement preparedStatement = ds.getConnection().prepareStatement(sql);
                preparedStatement.setString(1, b);
                preparedStatement.setString(2, ctpm.getTienPhat());
                preparedStatement.setString(3, ctpm.getTinhTrangSach());
                preparedStatement.setInt(4, ctpm.getMaPM());
                preparedStatement.setString(5, ctpm.getMaSach());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {

            }
        } catch (ParseException ex) {

        }
    }

    public static void xoaChiTietPM(int maphieu) {
        SQLServerDataSource ds = configDataSource();

        try ( var conn = ds.getConnection()) {
            String sql = "DELETE dbo.Chitietphieumuon  WHERE Maphieumuon LIKE '%" + maphieu + "%' ";
            var ps = conn.prepareStatement(sql);
            ps.executeUpdate();

        } catch (SQLServerException ex) {

        } catch (SQLException ex) {

        }
    }

    public static ResultSet thongketienphat(String chon) {
        SQLServerDataSource ds = configDataSource();
        var sql = "select MONTH ( Ngaytra) as Tháng, Sum( Tienphat) as [Tổng tiền phạt]\n"
                + "from Chitietphieumuon\n"
                + "where YEAR (Ngaytra) = '" + chon + "'\n"
                + "group by MONTH ( Ngaytra)";
        try {
            PreparedStatement pre = ds.getConnection().prepareStatement(sql);
            ResultSet result = pre.executeQuery();
            return result;
        } catch (SQLException ex) {
        }
        return null;
    }

}

//    public static PhieuMuon layphieumuonduavaomabandoc(int madocgia) {
//        PhieuMuon phieuMuon ;
//        SQLServerDataSource ds = configDataSource();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
//
//        try ( var conn = ds.getConnection()) {
//          var sql = "SELECT * FROM dbo.PhieuMuon WHERE [Mã Độc  Giả] =  " ;
//            var ps = conn.prepareStatement(sql);
//            var rs = ps.executeQuery();
//            while (rs.next()) {
//                var mapm = Integer.parseInt(rs.getString(1));
//                var madg = rs.getInt(2);
//                var tendg = rs.getString(3);
//                var ngaymuon = rs.getString(4);
//                var songaymuon = rs.getInt(5);
//                var sosachmuon = rs.getInt(6);
//                var trangthai = rs.getString(7);
//                 phieuMuon = new PhieuMuon(mapm, madg,
//                        tendg, simpleDateFormat.parse(ngaymuon),
//                        songaymuon, sosachmuon, trangthai);
//
//                phieuMuons.add(phieuMuon);
//            }
//        } catch (SQLServerException ex) {
//            //ex.printStackTrace();
//        } catch (SQLException ex) {
//            // ex.printStackTrace();
//        } catch (ParseException ex) {
//        }
//        return phieuMuons;
//    }
