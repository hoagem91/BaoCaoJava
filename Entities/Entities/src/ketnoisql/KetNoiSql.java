/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ketnoisql;

import Exception.InvalidDobException;
import Exception.InvalidEmailException;
import Exception.InvalidNameException;
import Exception.InvalidPhoneNumberException;
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
 
    

   

    //Thể Loại Sách
 public static String laymadanhmucduavaoten(String name) {        
        SQLServerDataSource ds = configDataSource();
        var ma = "";
        try ( var conn = ds.getConnection()) {
            String sql = "select Ma from DanhMucTaiLieu where Ten = N'" + name +"'";
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
            String sql = "select Ten from DanhMucTaiLieu where Ma = N'" + ma +"'";
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
   

   
//Nhà xuất bản 


    public static String laymanxbduavaoten(String name) {        
        SQLServerDataSource ds = configDataSource();
        var ma = "";
        try ( var conn = ds.getConnection()) {
            String sql = "select Ma from NhaXuatBan where Ten = N'" + name +"'";
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
            String sql = "select Ten from NhaXuatBan where Ma = N'" + ma +"'";
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
}
    

    

   
    
   