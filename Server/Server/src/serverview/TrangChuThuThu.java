/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package serverview;

import Controller.DATACONTROLLER;
import Controller.DataControllerImp;
import Model.DocGia;
import Model.ChiTietPhieuMuon;
import Model.NhaXuatBan;
import Model.PhieuMuon;
import Model.TaiLieu;
import Model.DanhMucTaiLieu;
import thongketienphat.ThongKeTienPhat;
import com.microsoft.schemas.office.visio.x2012.main.VisioDocumentDocument1;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import ketnoisql1.KetNoiSql;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Admin
 */
public class TrangChuThuThu extends javax.swing.JFrame implements ActionListener, KeyListener {

    private DefaultTableModel tableModelnxb;
    private DefaultTableModel tableModeldocgia;
    private DefaultTableModel tableModelsach;
    private DefaultTableModel tableModeltheloaisach;
    private DATACONTROLLER dataControllerImp;
    private DefaultTableModel tableModeldanhsachphieumuon;
    private DefaultTableModel tableModeltaophieumuonsach;
    private DefaultTableModel tableModelsachdachon;
    private DefaultTableModel tableModeltrasachchitietpm;
    private DefaultTableModel tableModelthongkedocgia;
    private DefaultTableModel tableModelthongkesach;
    private DefaultTableModel tableModelthongketienphat;
    private List<PhieuMuon> phieuMuons;
    private List<ChiTietPhieuMuon> chiTietPhieuMuons;
    private List<DocGia> banDocs;
    private List<TaiLieu> sachs;
    private List<TaiLieu> sachs1;
    private List<DanhMucTaiLieu> theLoaiSachs;
    private List<String> danhsachtl;
    private List<NhaXuatBan> nhaXuatBans;
    private List<String> danhsachnxb;
    private PhieuMuon phieuMuon;
    private String mathuthu;

    //đổi màu tab
    //private List<Bean> listItem;
    /**
     * Creates new form TrangChuThuThu
     */
    public TrangChuThuThu() {
        initComponents();
        setLocationRelativeTo(null);
        dataControllerImp = new DataControllerImp();
        setTitle("QUẢN LÝ THƯ VIỆN");
        //ImageIcon image = new ImageIcon("image//dmtl.png");
        URL url = this.getClass().getResource("dmtl.png");
        Image img = Toolkit.getDefaultToolkit().createImage(url);
        this.setIconImage(img);
        txttrasachngaytra.setToolTipText("dd/mm/yyyy");
        addButtonGroup();
        addActionListener();
        this.setBackground(Color.yellow);

        sachs1 = new ArrayList<>();
        chiTietPhieuMuons = new ArrayList<>();
        banDocs = new ArrayList<>();
        sachs = new ArrayList<>();
        phieuMuons = new ArrayList<>();
        danhsachtl = new ArrayList<>();
        phieuMuon = new PhieuMuon();
        theLoaiSachs = new ArrayList<>();
        danhsachnxb = new ArrayList<>();
        nhaXuatBans = new ArrayList<>();
        tableModelnxb = (DefaultTableModel) tblnhaxuatban.getModel();
        tableModeldanhsachphieumuon = (DefaultTableModel) tbldanhsachphieumuon.getModel();
        tableModeltaophieumuonsach = (DefaultTableModel) tbltaophieumuonsach.getModel();
        tableModeltrasachchitietpm = (DefaultTableModel) tbltrasachchitietpm.getModel();
        tableModeldocgia = (DefaultTableModel) tbldocgia.getModel();
        tableModelsach = (DefaultTableModel) tblsach.getModel();
        tableModeltheloaisach = (DefaultTableModel) tbltheloaisach.getModel();
        tableModelsachdachon = (DefaultTableModel) tblsachdachon.getModel();
        tableModelthongkedocgia = (DefaultTableModel) tblthongkedocgia.getModel();
        tableModelthongkesach = (DefaultTableModel) tblthongkesach.getModel();
        tableModelthongketienphat = (DefaultTableModel) tblthongketienphat.getModel();
        loadData();
        showData();
        txtmaphieu.setText(PhieuMuon.getsId() + "");
        tableModelsachdachon.setRowCount(0);
        //đổi màu nền
//        listItem.add(new ThongKeTienPhat(jpnNhaXuatBan));
//        listItem.add(new ThongKeTienPhat(jpnQuanLyDanhMuc));
//        listItem.add(new ThongKeTienPhat(jpnQuanLyDocGia));
//        listItem.add(new ThongKeTienPhat(jpnQuanLyTaiLieu));
//        listItem.add(new ThongKeTienPhat(jpnQuanLyMuonTra));
//        listItem.add(new ThongKeTienPhat(jpnThongKeBaoCao));

    }

    public TrangChuThuThu(String mathuthu) {
        this();
        this.mathuthu = mathuthu;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupsxdocgia = new javax.swing.ButtonGroup();
        buttonGrouptkdocgia = new javax.swing.ButtonGroup();
        buttonGroupsxsach = new javax.swing.ButtonGroup();
        buttonGrouptimkiemsach = new javax.swing.ButtonGroup();
        buttonGroupmuonsachtimkiemsach = new javax.swing.ButtonGroup();
        buttonGrouptimkiemphieumuon = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jpnQuanLyDocGia = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbldocgia = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        rbtenaz = new javax.swing.JRadioButton();
        rbtenza = new javax.swing.JRadioButton();
        rbdocgiatheomatang = new javax.swing.JRadioButton();
        rbdocgiatheomagiam = new javax.swing.JRadioButton();
        jPanel8 = new javax.swing.JPanel();
        rbtimtendocgia = new javax.swing.JRadioButton();
        rbtimmadocgia = new javax.swing.JRadioButton();
        txttimdocgiatheoten = new javax.swing.JTextField();
        txttimdocgiatheoma = new javax.swing.JTextField();
        btntimdocgia = new javax.swing.JButton();
        btnlammoidocgia = new javax.swing.JButton();
        btnthemdocgia = new javax.swing.JButton();
        btnsuadocgia = new javax.swing.JButton();
        btnxoadocgia = new javax.swing.JButton();
        btnkhoadocgia = new javax.swing.JButton();
        btnmodocgia = new javax.swing.JButton();
        jpnQuanLyTaiLieu = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        rbsxsachtheotentang = new javax.swing.JRadioButton();
        rbsxsachtheosltang = new javax.swing.JRadioButton();
        rbsxsachtheotengiam = new javax.swing.JRadioButton();
        rbsxsachtheoslgiam = new javax.swing.JRadioButton();
        jPanel9 = new javax.swing.JPanel();
        rbtimsachtheoten = new javax.swing.JRadioButton();
        txttimsachtheoten = new javax.swing.JTextField();
        rbtimsachtheoma = new javax.swing.JRadioButton();
        txttimsachtheoma = new javax.swing.JTextField();
        rbtimsachtheodanhmuc = new javax.swing.JRadioButton();
        combodanhmucsach = new javax.swing.JComboBox<>();
        btntimkiemsach = new javax.swing.JButton();
        rbtimsachtheonxb = new javax.swing.JRadioButton();
        combonxb = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblsach = new javax.swing.JTable();
        btnlammoisach = new javax.swing.JButton();
        btnthemsach = new javax.swing.JButton();
        btnsuasach = new javax.swing.JButton();
        btnxoasach = new javax.swing.JButton();
        btnchitiet = new javax.swing.JButton();
        jpnQuanLyDanhMuc = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbltheloaisach = new javax.swing.JTable();
        btnthemtlsach = new javax.swing.JButton();
        btnchinhsuatlsach = new javax.swing.JButton();
        btnlammoitlsach = new javax.swing.JButton();
        btnxoatlsach = new javax.swing.JButton();
        jpnNhaXuatBan = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        tblnhaxuatban = new javax.swing.JTable();
        btnthemnxb = new javax.swing.JButton();
        btnsuanxb = new javax.swing.JButton();
        btnxoanxb = new javax.swing.JButton();
        btnlammoinxb = new javax.swing.JButton();
        jpnQuanLyMuonTra = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtmaphieu = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txttendocgia = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtngaymuon = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtmadocgia = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtsongaymuon = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtsosachmuon = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cbbtrangthaiphieu = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbltaophieumuonsach = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblsachdachon = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        btntaophieumuon = new javax.swing.JButton();
        btnmuonsachchon = new javax.swing.JButton();
        btnmuonsachxoa = new javax.swing.JButton();
        btntaophieumuonlammoi = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbldanhsachphieumuon = new javax.swing.JTable();
        btnxoaphieumuon = new javax.swing.JButton();
        btndsphieumuonlammoi = new javax.swing.JButton();
        rbtimphieutheomaphieu = new javax.swing.JRadioButton();
        rbtimphieutheomadocgia = new javax.swing.JRadioButton();
        rbtimphieutheotendocgia = new javax.swing.JRadioButton();
        txttimkiemphieumuon = new javax.swing.JTextField();
        btntimphieumuon = new javax.swing.JButton();
        btnchitietphieumuon = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txttrasachmaphieumuon = new javax.swing.JTextField();
        btntrasachtim = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbltrasachchitietpm = new javax.swing.JTable();
        btntrasachchon = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txttrasachmaphieu = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txttrasachngaytra = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        combotinhtrangsach = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        txttrasachtienphat = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        btntrasach = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        txttrasachmasach = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txttrasachngaytrehen = new javax.swing.JTextField();
        btntrasachlammoi = new javax.swing.JButton();
        jpnThongKeBaoCao = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        cbbthongkedocgia = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblthongkedocgia = new javax.swing.JTable();
        btnxuatexcel = new javax.swing.JButton();
        btnbieudothongkedocgia = new javax.swing.JButton();
        jPanel18 = new javax.swing.JPanel();
        cbbthongkesach = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblthongkesach = new javax.swing.JTable();
        btnxuatbaocaosach = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        txtnamthongketailieu = new javax.swing.JTextField();
        btnxembieudo = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblthongketienphat = new javax.swing.JTable();
        btnxuatbaocaotienphat = new javax.swing.JButton();
        txtthongketienphattheonam = new javax.swing.JTextField();
        btnthongketienphattheocacnam = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        lbsoluongtailieu = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel26 = new javax.swing.JLabel();
        lbsoluongdocgia1 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel29 = new javax.swing.JLabel();
        lbsoluongphieu = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel31 = new javax.swing.JLabel();
        lbsoluongnxb = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel33 = new javax.swing.JLabel();
        lbsoluongdanhmuc = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        btncapnhat3 = new javax.swing.JButton();
        btndangxuat1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TRANG CHỦ THỦ THƯ");
        setBackground(new java.awt.Color(255, 255, 51));

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));

        jLabel25.setBackground(new java.awt.Color(0, 102, 102));
        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("QUẢN LÝ THƯ VIỆN TRƯỜNG HỌC");
        jLabel25.setOpaque(true);

        jTabbedPane1.setBackground(new java.awt.Color(0, 102, 102));
        jTabbedPane1.setForeground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane1.setFont(new java.awt.Font("Times New Roman", 1, 22)); // NOI18N
        jTabbedPane1.setOpaque(true);

        jpnQuanLyDocGia.setBackground(new java.awt.Color(255, 255, 51));
        jpnQuanLyDocGia.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        tbldocgia.setBackground(new java.awt.Color(255, 255, 51));
        tbldocgia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbldocgia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã độc giả", "Tên độc giả", "Ngày sinh", "Email", "Địa chỉ", "Số điện thoại", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbldocgia.setRowHeight(22);
        jScrollPane2.setViewportView(tbldocgia);

        jPanel7.setBackground(new java.awt.Color(255, 255, 51));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Sắp xếp độc giả", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        rbtenaz.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        rbtenaz.setText("Theo tên a-z");

        rbtenza.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        rbtenza.setText("Theo tên z-a");

        rbdocgiatheomatang.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        rbdocgiatheomatang.setText("Theo mã tăng dần");

        rbdocgiatheomagiam.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        rbdocgiatheomagiam.setText("Theo mã giảm dần");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(rbtenaz, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rbtenza, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(122, 122, 122)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbdocgiatheomatang)
                    .addComponent(rbdocgiatheomagiam))
                .addContainerGap(84, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtenaz)
                    .addComponent(rbdocgiatheomatang))
                .addGap(26, 26, 26)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtenza)
                    .addComponent(rbdocgiatheomagiam))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 51));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Tìm kiếm độc giả", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        rbtimtendocgia.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        rbtimtendocgia.setText("Theo tên độc giả");

        rbtimmadocgia.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        rbtimmadocgia.setText("Theo mã độc giả");

        txttimdocgiatheoten.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txttimdocgiatheoma.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btntimdocgia.setBackground(new java.awt.Color(0, 102, 102));
        btntimdocgia.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btntimdocgia.setForeground(new java.awt.Color(255, 255, 255));
        btntimdocgia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/tk.png"))); // NOI18N
        btntimdocgia.setText("Tìm kiếm");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbtimtendocgia)
                    .addComponent(rbtimmadocgia))
                .addGap(24, 24, 24)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(txttimdocgiatheoma, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                        .addComponent(btntimdocgia)
                        .addGap(20, 20, 20))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(txttimdocgiatheoten, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(btntimdocgia)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtimtendocgia)
                    .addComponent(txttimdocgiatheoten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtimmadocgia)
                    .addComponent(txttimdocgiatheoma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        btnlammoidocgia.setBackground(new java.awt.Color(0, 102, 102));
        btnlammoidocgia.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnlammoidocgia.setForeground(new java.awt.Color(255, 255, 255));
        btnlammoidocgia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/lammoi.png"))); // NOI18N
        btnlammoidocgia.setText("Làm mới");

        btnthemdocgia.setBackground(new java.awt.Color(0, 102, 102));
        btnthemdocgia.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnthemdocgia.setForeground(new java.awt.Color(255, 255, 255));
        btnthemdocgia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/themdocgia.png"))); // NOI18N
        btnthemdocgia.setText("Thêm độc giả");

        btnsuadocgia.setBackground(new java.awt.Color(0, 102, 102));
        btnsuadocgia.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnsuadocgia.setForeground(new java.awt.Color(255, 255, 255));
        btnsuadocgia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/sua.png"))); // NOI18N
        btnsuadocgia.setText("Sửa độc giả");

        btnxoadocgia.setBackground(new java.awt.Color(0, 102, 102));
        btnxoadocgia.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnxoadocgia.setForeground(new java.awt.Color(255, 255, 255));
        btnxoadocgia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/xoadg.png"))); // NOI18N
        btnxoadocgia.setText("Xoá độc giả");

        btnkhoadocgia.setBackground(new java.awt.Color(0, 102, 102));
        btnkhoadocgia.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnkhoadocgia.setForeground(new java.awt.Color(255, 255, 255));
        btnkhoadocgia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/khoatk.png"))); // NOI18N
        btnkhoadocgia.setText("Khoá tài khoản");

        btnmodocgia.setBackground(new java.awt.Color(0, 102, 102));
        btnmodocgia.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnmodocgia.setForeground(new java.awt.Color(255, 255, 255));
        btnmodocgia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/motk.png"))); // NOI18N
        btnmodocgia.setText("Mở tài khoản");

        javax.swing.GroupLayout jpnQuanLyDocGiaLayout = new javax.swing.GroupLayout(jpnQuanLyDocGia);
        jpnQuanLyDocGia.setLayout(jpnQuanLyDocGiaLayout);
        jpnQuanLyDocGiaLayout.setHorizontalGroup(
            jpnQuanLyDocGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnQuanLyDocGiaLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(btnlammoidocgia, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnthemdocgia)
                .addGap(31, 31, 31)
                .addComponent(btnsuadocgia, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btnkhoadocgia)
                .addGap(26, 26, 26)
                .addComponent(btnmodocgia)
                .addGap(27, 27, 27)
                .addComponent(btnxoadocgia, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnQuanLyDocGiaLayout.createSequentialGroup()
                .addGroup(jpnQuanLyDocGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addGroup(jpnQuanLyDocGiaLayout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(52, 52, 52))
        );
        jpnQuanLyDocGiaLayout.setVerticalGroup(
            jpnQuanLyDocGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnQuanLyDocGiaLayout.createSequentialGroup()
                .addGroup(jpnQuanLyDocGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jpnQuanLyDocGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnlammoidocgia)
                    .addComponent(btnthemdocgia)
                    .addComponent(btnsuadocgia)
                    .addComponent(btnxoadocgia)
                    .addComponent(btnkhoadocgia)
                    .addComponent(btnmodocgia))
                .addContainerGap())
        );

        jTabbedPane1.addTab("QUẢN LÝ ĐỘC GIẢ", new javax.swing.ImageIcon(getClass().getResource("/image/qldg1.png")), jpnQuanLyDocGia); // NOI18N

        jpnQuanLyTaiLieu.setBackground(new java.awt.Color(255, 255, 51));

        jPanel6.setBackground(new java.awt.Color(255, 255, 51));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Sắp xếp tài liệu", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        rbsxsachtheotentang.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        rbsxsachtheotentang.setText("Theo tên a-z");

        rbsxsachtheosltang.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        rbsxsachtheosltang.setText("Theo số lượng tăng dần");

        rbsxsachtheotengiam.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        rbsxsachtheotengiam.setText("Theo tên z-a");

        rbsxsachtheoslgiam.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        rbsxsachtheoslgiam.setText("Theo số lượng giảm dần");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(rbsxsachtheotentang)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbsxsachtheosltang))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(rbsxsachtheotengiam)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbsxsachtheoslgiam)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbsxsachtheotentang)
                    .addComponent(rbsxsachtheosltang))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbsxsachtheoslgiam)
                    .addComponent(rbsxsachtheotengiam))
                .addContainerGap())
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 51));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Tìm kiếm tài liệu", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        rbtimsachtheoten.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        rbtimsachtheoten.setText("Theo tên ");

        txttimsachtheoten.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        rbtimsachtheoma.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        rbtimsachtheoma.setText("Theo mã ");

        txttimsachtheoma.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        rbtimsachtheodanhmuc.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        rbtimsachtheodanhmuc.setText("Theo danh mục");

        combodanhmucsach.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btntimkiemsach.setBackground(new java.awt.Color(0, 102, 102));
        btntimkiemsach.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btntimkiemsach.setForeground(new java.awt.Color(255, 255, 255));
        btntimkiemsach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/tk.png"))); // NOI18N
        btntimkiemsach.setText("Tìm kiếm");

        rbtimsachtheonxb.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        rbtimsachtheonxb.setText("Theo NXB");

        combonxb.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(rbtimsachtheoten)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txttimsachtheoten, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(rbtimsachtheoma)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txttimsachtheoma, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbtimsachtheodanhmuc)
                    .addComponent(rbtimsachtheonxb))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(combodanhmucsach, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combonxb, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btntimkiemsach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbtimsachtheoten)
                            .addComponent(txttimsachtheoten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rbtimsachtheodanhmuc)
                            .addComponent(combodanhmucsach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txttimsachtheoma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rbtimsachtheonxb)
                            .addComponent(combonxb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rbtimsachtheoma)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(btntimkiemsach)))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        tblsach.setBackground(new java.awt.Color(255, 255, 51));
        tblsach.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblsach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã tài liệu", "Tên tài liệu", "Danh mục", "Tác giả", "Nhà xuất bản", "Năm xuất bản", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblsach);

        btnlammoisach.setBackground(new java.awt.Color(0, 102, 102));
        btnlammoisach.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnlammoisach.setForeground(new java.awt.Color(255, 255, 255));
        btnlammoisach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/lammoi.png"))); // NOI18N
        btnlammoisach.setText("Làm mới");

        btnthemsach.setBackground(new java.awt.Color(0, 102, 102));
        btnthemsach.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnthemsach.setForeground(new java.awt.Color(255, 255, 255));
        btnthemsach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/themsach.png"))); // NOI18N
        btnthemsach.setText("Thêm tài liệu");

        btnsuasach.setBackground(new java.awt.Color(0, 102, 102));
        btnsuasach.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnsuasach.setForeground(new java.awt.Color(255, 255, 255));
        btnsuasach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/sua.png"))); // NOI18N
        btnsuasach.setText("Sửa tài liệu");

        btnxoasach.setBackground(new java.awt.Color(0, 102, 102));
        btnxoasach.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnxoasach.setForeground(new java.awt.Color(255, 255, 255));
        btnxoasach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/xoasach.png"))); // NOI18N
        btnxoasach.setText("Xoá tài liệu");

        btnchitiet.setBackground(new java.awt.Color(0, 102, 102));
        btnchitiet.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnchitiet.setForeground(new java.awt.Color(255, 255, 255));
        btnchitiet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/chitiet.png"))); // NOI18N
        btnchitiet.setText("Chi tiết");

        javax.swing.GroupLayout jpnQuanLyTaiLieuLayout = new javax.swing.GroupLayout(jpnQuanLyTaiLieu);
        jpnQuanLyTaiLieu.setLayout(jpnQuanLyTaiLieuLayout);
        jpnQuanLyTaiLieuLayout.setHorizontalGroup(
            jpnQuanLyTaiLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnQuanLyTaiLieuLayout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(btnlammoisach)
                .addGap(66, 66, 66)
                .addComponent(btnthemsach)
                .addGap(75, 75, 75)
                .addComponent(btnsuasach)
                .addGap(77, 77, 77)
                .addComponent(btnxoasach)
                .addGap(73, 73, 73)
                .addComponent(btnchitiet, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnQuanLyTaiLieuLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jpnQuanLyTaiLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1018, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpnQuanLyTaiLieuLayout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(89, 89, 89))
        );
        jpnQuanLyTaiLieuLayout.setVerticalGroup(
            jpnQuanLyTaiLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnQuanLyTaiLieuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnQuanLyTaiLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jpnQuanLyTaiLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnlammoisach)
                    .addComponent(btnthemsach)
                    .addComponent(btnsuasach)
                    .addComponent(btnxoasach)
                    .addComponent(btnchitiet))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("QUẢN LÝ TÀI LIỆU", new javax.swing.ImageIcon(getClass().getResource("/image/tailieu1.png")), jpnQuanLyTaiLieu); // NOI18N

        jpnQuanLyDanhMuc.setBackground(new java.awt.Color(255, 255, 51));

        tbltheloaisach.setBackground(new java.awt.Color(255, 255, 51));
        tbltheloaisach.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbltheloaisach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Mã danh mục", "Tên danh mục"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tbltheloaisach);

        btnthemtlsach.setBackground(new java.awt.Color(0, 102, 102));
        btnthemtlsach.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnthemtlsach.setForeground(new java.awt.Color(255, 255, 255));
        btnthemtlsach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/themsach.png"))); // NOI18N
        btnthemtlsach.setText("Thêm danh mục");

        btnchinhsuatlsach.setBackground(new java.awt.Color(0, 102, 102));
        btnchinhsuatlsach.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnchinhsuatlsach.setForeground(new java.awt.Color(255, 255, 255));
        btnchinhsuatlsach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/sua.png"))); // NOI18N
        btnchinhsuatlsach.setText("Chỉnh sửa");

        btnlammoitlsach.setBackground(new java.awt.Color(0, 102, 102));
        btnlammoitlsach.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnlammoitlsach.setForeground(new java.awt.Color(255, 255, 255));
        btnlammoitlsach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/lammoi.png"))); // NOI18N
        btnlammoitlsach.setText("Làm mới");

        btnxoatlsach.setBackground(new java.awt.Color(0, 102, 102));
        btnxoatlsach.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnxoatlsach.setForeground(new java.awt.Color(255, 255, 255));
        btnxoatlsach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/xoa.png"))); // NOI18N
        btnxoatlsach.setText("Xoá");

        javax.swing.GroupLayout jpnQuanLyDanhMucLayout = new javax.swing.GroupLayout(jpnQuanLyDanhMuc);
        jpnQuanLyDanhMuc.setLayout(jpnQuanLyDanhMucLayout);
        jpnQuanLyDanhMucLayout.setHorizontalGroup(
            jpnQuanLyDanhMucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnQuanLyDanhMucLayout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(btnthemtlsach)
                .addGap(118, 118, 118)
                .addComponent(btnchinhsuatlsach, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(128, 128, 128)
                .addComponent(btnlammoitlsach, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 116, Short.MAX_VALUE)
                .addComponent(btnxoatlsach, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65))
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jpnQuanLyDanhMucLayout.setVerticalGroup(
            jpnQuanLyDanhMucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnQuanLyDanhMucLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 131, Short.MAX_VALUE)
                .addGroup(jpnQuanLyDanhMucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnthemtlsach)
                    .addComponent(btnchinhsuatlsach)
                    .addComponent(btnlammoitlsach)
                    .addComponent(btnxoatlsach))
                .addGap(12, 12, 12))
        );

        jTabbedPane1.addTab("QUẢN LÝ DANH MỤC", new javax.swing.ImageIcon(getClass().getResource("/image/danhmuc1.png")), jpnQuanLyDanhMuc); // NOI18N

        jpnNhaXuatBan.setBackground(new java.awt.Color(255, 255, 51));

        tblnhaxuatban.setBackground(new java.awt.Color(255, 255, 51));
        tblnhaxuatban.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblnhaxuatban.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Mã nhà xuất bản", "Tên nhà xuất bản"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane11.setViewportView(tblnhaxuatban);

        btnthemnxb.setBackground(new java.awt.Color(0, 102, 102));
        btnthemnxb.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnthemnxb.setForeground(new java.awt.Color(255, 255, 255));
        btnthemnxb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/themsach.png"))); // NOI18N
        btnthemnxb.setText("Thêm nhà xuất bản");

        btnsuanxb.setBackground(new java.awt.Color(0, 102, 102));
        btnsuanxb.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnsuanxb.setForeground(new java.awt.Color(255, 255, 255));
        btnsuanxb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/sua.png"))); // NOI18N
        btnsuanxb.setText("Sửa thông tin");

        btnxoanxb.setBackground(new java.awt.Color(0, 102, 102));
        btnxoanxb.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnxoanxb.setForeground(new java.awt.Color(255, 255, 255));
        btnxoanxb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/xoa.png"))); // NOI18N
        btnxoanxb.setText("Xoá nhà xuất bản");

        btnlammoinxb.setBackground(new java.awt.Color(0, 102, 102));
        btnlammoinxb.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnlammoinxb.setForeground(new java.awt.Color(255, 255, 255));
        btnlammoinxb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/lammoi.png"))); // NOI18N
        btnlammoinxb.setText("Làm mới");

        javax.swing.GroupLayout jpnNhaXuatBanLayout = new javax.swing.GroupLayout(jpnNhaXuatBan);
        jpnNhaXuatBan.setLayout(jpnNhaXuatBanLayout);
        jpnNhaXuatBanLayout.setHorizontalGroup(
            jpnNhaXuatBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnNhaXuatBanLayout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(btnthemnxb)
                .addGap(86, 86, 86)
                .addComponent(btnsuanxb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                .addComponent(btnxoanxb)
                .addGap(93, 93, 93)
                .addComponent(btnlammoinxb)
                .addGap(76, 76, 76))
            .addGroup(jpnNhaXuatBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 1019, Short.MAX_VALUE))
        );
        jpnNhaXuatBanLayout.setVerticalGroup(
            jpnNhaXuatBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnNhaXuatBanLayout.createSequentialGroup()
                .addContainerGap(541, Short.MAX_VALUE)
                .addGroup(jpnNhaXuatBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnthemnxb)
                    .addComponent(btnsuanxb)
                    .addComponent(btnxoanxb)
                    .addComponent(btnlammoinxb))
                .addGap(28, 28, 28))
            .addGroup(jpnNhaXuatBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpnNhaXuatBanLayout.createSequentialGroup()
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 119, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("NHÀ XUẤT BẢN", new javax.swing.ImageIcon(getClass().getResource("/image/nxb.png")), jpnNhaXuatBan); // NOI18N

        jpnQuanLyMuonTra.setBackground(new java.awt.Color(255, 255, 51));

        jTabbedPane2.setBackground(new java.awt.Color(255, 255, 51));
        jTabbedPane2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jPanel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jPanel13.setBackground(new java.awt.Color(255, 255, 51));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel2.setText("Mã phiếu mượn");

        txtmaphieu.setEditable(false);
        txtmaphieu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel3.setText("Tên độc giả");

        txttendocgia.setEditable(false);
        txttendocgia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel4.setText("Số tài liệu mượn");

        txtngaymuon.setEditable(false);
        txtngaymuon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel5.setText("Mã  độc giả");

        txtmadocgia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel6.setText("Ngày mượn");

        txtsongaymuon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel7.setText("Số ngày mượn");

        txtsosachmuon.setEditable(false);
        txtsosachmuon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel9.setText("Trạng thái");

        cbbtrangthaiphieu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbbtrangthaiphieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chưa trả", "Đã trả" }));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cbbtrangthaiphieu, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel13Layout.createSequentialGroup()
                            .addGap(23, 23, 23)
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtsongaymuon, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtngaymuon, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txttendocgia, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtmaphieu, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtmadocgia, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtsosachmuon, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel13Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel13Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel13Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel13Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel13Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtmaphieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txttendocgia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtmadocgia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtngaymuon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtsongaymuon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtsosachmuon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbbtrangthaiphieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        tbltaophieumuonsach.setBackground(new java.awt.Color(255, 255, 51));
        tbltaophieumuonsach.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbltaophieumuonsach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã tài liệu", "Tên tài liệu", "Nhà xuất bản", "Năm xuất bản", "Số lượng còn"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(tbltaophieumuonsach);

        tblsachdachon.setBackground(new java.awt.Color(255, 255, 51));
        tblsachdachon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblsachdachon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Mã tài liệu", "Tên tài liệu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(tblsachdachon);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel8.setText("Tài liệu đã chọn");

        btntaophieumuon.setBackground(new java.awt.Color(0, 102, 102));
        btntaophieumuon.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btntaophieumuon.setForeground(new java.awt.Color(255, 255, 255));
        btntaophieumuon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/taophieumuon.png"))); // NOI18N
        btntaophieumuon.setText("Tạo phiếu mượn");

        btnmuonsachchon.setBackground(new java.awt.Color(0, 102, 102));
        btnmuonsachchon.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnmuonsachchon.setForeground(new java.awt.Color(255, 255, 255));
        btnmuonsachchon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/chon.png"))); // NOI18N
        btnmuonsachchon.setText("Chọn");

        btnmuonsachxoa.setBackground(new java.awt.Color(0, 102, 102));
        btnmuonsachxoa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnmuonsachxoa.setForeground(new java.awt.Color(255, 255, 255));
        btnmuonsachxoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/xoa.png"))); // NOI18N
        btnmuonsachxoa.setText("Xoá");

        btntaophieumuonlammoi.setBackground(new java.awt.Color(0, 102, 102));
        btntaophieumuonlammoi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btntaophieumuonlammoi.setForeground(new java.awt.Color(255, 255, 255));
        btntaophieumuonlammoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/lammoi.png"))); // NOI18N
        btntaophieumuonlammoi.setText("Làm mới");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(btntaophieumuonlammoi, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 163, Short.MAX_VALUE)
                .addComponent(btntaophieumuon)
                .addGap(168, 168, 168)
                .addComponent(btnmuonsachxoa, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnmuonsachchon, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addComponent(jScrollPane5)
                .addContainerGap())
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 825, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnmuonsachchon)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnmuonsachxoa)
                    .addComponent(btntaophieumuon)
                    .addComponent(btntaophieumuonlammoi))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator1)
            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Tạo phiếu mượn", jPanel12);

        jPanel11.setBackground(new java.awt.Color(255, 255, 51));

        tbldanhsachphieumuon.setBackground(new java.awt.Color(255, 255, 51));
        tbldanhsachphieumuon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbldanhsachphieumuon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã phiếu mượn", "Mã độc giả", "Tên độc giả", "Ngày mượn", "Số ngày mượn", "Số tài liệu mượn", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tbldanhsachphieumuon);

        btnxoaphieumuon.setBackground(new java.awt.Color(0, 102, 102));
        btnxoaphieumuon.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnxoaphieumuon.setForeground(new java.awt.Color(255, 255, 255));
        btnxoaphieumuon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/xoaphieu.png"))); // NOI18N
        btnxoaphieumuon.setText("Xoá phiếu mượn");

        btndsphieumuonlammoi.setBackground(new java.awt.Color(0, 102, 102));
        btndsphieumuonlammoi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btndsphieumuonlammoi.setForeground(new java.awt.Color(255, 255, 255));
        btndsphieumuonlammoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/lammoi.png"))); // NOI18N
        btndsphieumuonlammoi.setText("Làm mới");

        rbtimphieutheomaphieu.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        rbtimphieutheomaphieu.setText("Tìm kiếm theo mã phiếu");

        rbtimphieutheomadocgia.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        rbtimphieutheomadocgia.setText("Tìm kiếm theo mã độc giả");

        rbtimphieutheotendocgia.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        rbtimphieutheotendocgia.setText("Tìm kiếm theo tên độc giả");

        txttimkiemphieumuon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btntimphieumuon.setBackground(new java.awt.Color(0, 102, 102));
        btntimphieumuon.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btntimphieumuon.setForeground(new java.awt.Color(255, 255, 255));
        btntimphieumuon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/tk.png"))); // NOI18N
        btntimphieumuon.setText("Tìm kiếm");

        btnchitietphieumuon.setBackground(new java.awt.Color(0, 102, 102));
        btnchitietphieumuon.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnchitietphieumuon.setForeground(new java.awt.Color(255, 255, 255));
        btnchitietphieumuon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/chitiet.png"))); // NOI18N
        btnchitietphieumuon.setText("Chi tiết");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(200, 200, 200)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(rbtimphieutheotendocgia)
                    .addComponent(rbtimphieutheomadocgia)
                    .addComponent(rbtimphieutheomaphieu, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(58, 58, 58)
                .addComponent(txttimkiemphieumuon, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btntimphieumuon, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(200, 200, 200))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(btndsphieumuonlammoi, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnxoaphieumuon, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(251, 251, 251)
                .addComponent(btnchitietphieumuon, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(89, 89, 89))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rbtimphieutheomaphieu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbtimphieutheomadocgia)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txttimkiemphieumuon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btntimphieumuon)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbtimphieutheotendocgia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnchitietphieumuon)
                    .addComponent(btnxoaphieumuon)
                    .addComponent(btndsphieumuonlammoi))
                .addContainerGap())
        );

        jTabbedPane2.addTab("Danh sách phiếu mượn", jPanel11);

        jPanel15.setBackground(new java.awt.Color(255, 255, 51));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel10.setText("Mã phiếu mượn");

        txttrasachmaphieumuon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btntrasachtim.setBackground(new java.awt.Color(0, 102, 102));
        btntrasachtim.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btntrasachtim.setForeground(new java.awt.Color(255, 255, 255));
        btntrasachtim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/tk.png"))); // NOI18N
        btntrasachtim.setText("Tìm");

        jPanel16.setBackground(new java.awt.Color(255, 255, 51));
        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Chi tiết phiếu mượn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tbltrasachchitietpm.setBackground(new java.awt.Color(255, 255, 51));
        tbltrasachchitietpm.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbltrasachchitietpm.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã phiếu", "Mã  tài liệu", "Ngày trả", "Tiền phạt", "Tình trạng tài liệu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane7.setViewportView(tbltrasachchitietpm);

        btntrasachchon.setBackground(new java.awt.Color(0, 102, 102));
        btntrasachchon.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btntrasachchon.setForeground(new java.awt.Color(255, 255, 255));
        btntrasachchon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/chon.png"))); // NOI18N
        btntrasachchon.setText("Chọn");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 1013, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 23, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btntrasachchon, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(94, 94, 94))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap(8, Short.MAX_VALUE)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btntrasachchon)
                .addContainerGap())
        );

        jPanel17.setBackground(new java.awt.Color(255, 255, 51));
        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Thông tin trả tài liệu", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel11.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel11.setText("Mã phiếu");

        txttrasachmaphieu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel12.setText("Ngày trả");

        txttrasachngaytra.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel13.setText("Tình trạng tài liệu");

        combotinhtrangsach.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        combotinhtrangsach.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bình Thường", "Hư Hỏng 10%", "Hư Hỏng 30%", "Hư Hỏng 50%", "Hư Hỏng 70%", "Mất Sách" }));
        combotinhtrangsach.setSelectedIndex(-1);

        jLabel14.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel14.setText("Tiền phạt (vnđ)");

        txttrasachtienphat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 51, 51));
        jLabel15.setText("Chú ý:");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 0, 0));
        jLabel16.setText("Nếu bị hư hỏng, số tiền phạt sẽ tương ứng với mức độ hư hỏng của tài liệu");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 0, 0));
        jLabel17.setText("Nếu làm mất tài liệu, độc giả sẽ phải đền bù bằng giá trị tài liệu đó");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 0, 0));
        jLabel18.setText("Nếu trả quá hạn, số tiền phạt là 10000đ/ ngày ");

        btntrasach.setBackground(new java.awt.Color(0, 102, 102));
        btntrasach.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btntrasach.setForeground(new java.awt.Color(255, 255, 255));
        btntrasach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/tratl.png"))); // NOI18N
        btntrasach.setText("Trả tài liệu");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel19.setText("Mã tài liệu");

        txttrasachmasach.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel20.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel20.setText("Ngày trễ hẹn");

        txttrasachngaytrehen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btntrasachlammoi.setBackground(new java.awt.Color(0, 102, 102));
        btntrasachlammoi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btntrasachlammoi.setForeground(new java.awt.Color(255, 255, 255));
        btntrasachlammoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/lammoi.png"))); // NOI18N
        btntrasachlammoi.setText("Làm mới");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel17Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addComponent(txttrasachmaphieu, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txttrasachmasach, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txttrasachngaytrehen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txttrasachngaytra, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txttrasachtienphat)
                            .addComponent(combotinhtrangsach, 0, 237, Short.MAX_VALUE))))
                .addGap(95, 95, 95)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(btntrasach, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(165, 165, 165)
                        .addComponent(btntrasachlammoi)
                        .addGap(54, 54, 54))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txttrasachmaphieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttrasachmasach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txttrasachngaytra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txttrasachngaytrehen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(combotinhtrangsach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txttrasachtienphat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(43, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btntrasach)
                            .addComponent(btntrasachlammoi))
                        .addGap(39, 39, 39))))
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(200, 200, 200)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(95, 95, 95)
                .addComponent(txttrasachmaphieumuon, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btntrasachtim, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(107, 107, 107))
            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txttrasachmaphieumuon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btntrasachtim))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Trả tài liệu", jPanel15);

        javax.swing.GroupLayout jpnQuanLyMuonTraLayout = new javax.swing.GroupLayout(jpnQuanLyMuonTra);
        jpnQuanLyMuonTra.setLayout(jpnQuanLyMuonTraLayout);
        jpnQuanLyMuonTraLayout.setHorizontalGroup(
            jpnQuanLyMuonTraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );
        jpnQuanLyMuonTraLayout.setVerticalGroup(
            jpnQuanLyMuonTraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );

        jTabbedPane1.addTab("QUẢN LÝ MƯỢN TRẢ", new javax.swing.ImageIcon(getClass().getResource("/image/muontra1.png")), jpnQuanLyMuonTra); // NOI18N

        jTabbedPane3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jPanel5.setBackground(new java.awt.Color(255, 255, 51));

        cbbthongkedocgia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbbthongkedocgia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Độc giả mượn nhiều nhất", "Độc giả mượn quá hạn", "Độc giả chưa trả tài liệu" }));
        cbbthongkedocgia.setSelectedIndex(-1);

        jLabel21.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel21.setText("Lựa chọn");

        tblthongkedocgia.setBackground(new java.awt.Color(255, 255, 51));
        tblthongkedocgia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblthongkedocgia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane8.setViewportView(tblthongkedocgia);

        btnxuatexcel.setBackground(new java.awt.Color(0, 102, 102));
        btnxuatexcel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnxuatexcel.setForeground(new java.awt.Color(255, 255, 255));
        btnxuatexcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/baocao.png"))); // NOI18N
        btnxuatexcel.setText("Xuất báo cáo");

        btnbieudothongkedocgia.setBackground(new java.awt.Color(0, 102, 102));
        btnbieudothongkedocgia.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnbieudothongkedocgia.setForeground(new java.awt.Color(255, 255, 255));
        btnbieudothongkedocgia.setText("Xem biểu đồ ");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(btnbieudothongkedocgia, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72)
                .addComponent(cbbthongkedocgia, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                .addComponent(btnxuatexcel)
                .addGap(69, 69, 69))
            .addComponent(jScrollPane8)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(cbbthongkedocgia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnxuatexcel)
                    .addComponent(btnbieudothongkedocgia))
                .addGap(36, 36, 36)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(132, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Thống kê độc giả", jPanel5);

        jPanel18.setBackground(new java.awt.Color(255, 255, 51));

        cbbthongkesach.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbbthongkesach.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Top 5 tài liệu được mượn nhiều nhất", "Tài liệu hư hỏng", "Tài liệu bị mất" }));
        cbbthongkesach.setSelectedIndex(-1);

        jLabel22.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel22.setText("Lựa chọn");

        tblthongkesach.setBackground(new java.awt.Color(255, 255, 51));
        tblthongkesach.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblthongkesach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane9.setViewportView(tblthongkesach);

        btnxuatbaocaosach.setBackground(new java.awt.Color(0, 102, 102));
        btnxuatbaocaosach.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnxuatbaocaosach.setForeground(new java.awt.Color(255, 255, 255));
        btnxuatbaocaosach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/baocao.png"))); // NOI18N
        btnxuatbaocaosach.setText("Xuất báo cáo");

        jLabel24.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel24.setText("Năm");

        txtnamthongketailieu.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N

        btnxembieudo.setBackground(new java.awt.Color(0, 102, 102));
        btnxembieudo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnxembieudo.setForeground(new java.awt.Color(255, 255, 255));
        btnxembieudo.setText("Xem biểu đồ");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(txtnamthongketailieu, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(btnxembieudo, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(cbbthongkesach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(btnxuatbaocaosach, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
            .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1019, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(cbbthongkesach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnxuatbaocaosach)
                    .addComponent(jLabel24)
                    .addComponent(txtnamthongketailieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnxembieudo))
                .addContainerGap(514, Short.MAX_VALUE))
            .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel18Layout.createSequentialGroup()
                    .addGap(76, 76, 76)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(87, Short.MAX_VALUE)))
        );

        jTabbedPane3.addTab("Thống kê tài liệu", jPanel18);

        jPanel19.setBackground(new java.awt.Color(255, 255, 51));

        tblthongketienphat.setBackground(new java.awt.Color(255, 255, 51));
        tblthongketienphat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblthongketienphat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane10.setViewportView(tblthongketienphat);

        btnxuatbaocaotienphat.setBackground(new java.awt.Color(0, 102, 102));
        btnxuatbaocaotienphat.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnxuatbaocaotienphat.setForeground(new java.awt.Color(255, 255, 255));
        btnxuatbaocaotienphat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/baocao.png"))); // NOI18N
        btnxuatbaocaotienphat.setText("Xuất báo cáo");

        txtthongketienphattheonam.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnthongketienphattheocacnam.setBackground(new java.awt.Color(0, 102, 102));
        btnthongketienphattheocacnam.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnthongketienphattheocacnam.setForeground(new java.awt.Color(255, 255, 255));
        btnthongketienphattheocacnam.setText("Xem biểu đồ");

        jLabel35.setBackground(new java.awt.Color(0, 102, 102));
        jLabel35.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("Theo năm");
        jLabel35.setOpaque(true);

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(btnthongketienphattheocacnam, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(108, 108, 108)
                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 111, Short.MAX_VALUE)
                .addComponent(txtthongketienphattheonam, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(103, 103, 103)
                .addComponent(btnxuatbaocaotienphat, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85))
            .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1019, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnxuatbaocaotienphat)
                    .addComponent(txtthongketienphattheonam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnthongketienphattheocacnam)
                    .addComponent(jLabel35))
                .addContainerGap(516, Short.MAX_VALUE))
            .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel19Layout.createSequentialGroup()
                    .addGap(76, 76, 76)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(87, Short.MAX_VALUE)))
        );

        jTabbedPane3.addTab("Thống kê tiền phạt", jPanel19);

        jPanel2.setBackground(new java.awt.Color(255, 255, 51));

        jPanel3.setBackground(new java.awt.Color(0, 102, 102));

        jSeparator2.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator2.setOpaque(true);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Tài liệu");

        lbsoluongtailieu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbsoluongtailieu.setForeground(new java.awt.Color(255, 255, 255));
        lbsoluongtailieu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/sach3.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbsoluongtailieu, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbsoluongtailieu, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(0, 102, 102));

        jSeparator3.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator3.setOpaque(true);

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("Độc giả");

        lbsoluongdocgia1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbsoluongdocgia1.setForeground(new java.awt.Color(255, 255, 255));
        lbsoluongdocgia1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/docgia2.png"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(lbsoluongdocgia1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator3)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbsoluongdocgia1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel10.setBackground(new java.awt.Color(0, 102, 102));

        jSeparator4.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator4.setOpaque(true);

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("Phiếu mượn");

        lbsoluongphieu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbsoluongphieu.setForeground(new java.awt.Color(255, 255, 255));
        lbsoluongphieu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/phieumuon3.png"))); // NOI18N

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(lbsoluongphieu, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator4)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbsoluongphieu, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel20.setBackground(new java.awt.Color(0, 102, 102));

        jSeparator5.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator5.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator5.setOpaque(true);

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("NXB");

        lbsoluongnxb.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbsoluongnxb.setForeground(new java.awt.Color(255, 255, 255));
        lbsoluongnxb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/nxb3.png"))); // NOI18N

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                    .addComponent(lbsoluongnxb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator5)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbsoluongnxb, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel21.setBackground(new java.awt.Color(0, 102, 102));

        jSeparator6.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator6.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator6.setOpaque(true);

        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("Danh mục");

        lbsoluongdanhmuc.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbsoluongdanhmuc.setForeground(new java.awt.Color(255, 255, 255));
        lbsoluongdanhmuc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/danhmuc3.png"))); // NOI18N

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbsoluongdanhmuc, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator6)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbsoluongdanhmuc, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        btncapnhat3.setBackground(new java.awt.Color(0, 102, 102));
        btncapnhat3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btncapnhat3.setForeground(new java.awt.Color(255, 255, 255));
        btncapnhat3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/capnhat.png"))); // NOI18N
        btncapnhat3.setText("Cập nhật");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 74, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(70, 70, 70))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(441, 441, 441)
                .addComponent(btncapnhat3, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 211, Short.MAX_VALUE)
                .addComponent(btncapnhat3)
                .addGap(45, 45, 45))
        );

        jTabbedPane3.addTab("Thống kê", jPanel2);

        javax.swing.GroupLayout jpnThongKeBaoCaoLayout = new javax.swing.GroupLayout(jpnThongKeBaoCao);
        jpnThongKeBaoCao.setLayout(jpnThongKeBaoCaoLayout);
        jpnThongKeBaoCaoLayout.setHorizontalGroup(
            jpnThongKeBaoCaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3)
        );
        jpnThongKeBaoCaoLayout.setVerticalGroup(
            jpnThongKeBaoCaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3)
        );

        jTabbedPane1.addTab("THỐNG KÊ,BÁO CÁO", new javax.swing.ImageIcon(getClass().getResource("/image/thongke1.png")), jpnThongKeBaoCao); // NOI18N

        btndangxuat1.setBackground(new java.awt.Color(0, 102, 102));
        btndangxuat1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btndangxuat1.setForeground(new java.awt.Color(255, 255, 255));
        btndangxuat1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/log out.png"))); // NOI18N
        btndangxuat1.setText("Đăng xuất");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(175, 175, 175)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 996, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btndangxuat1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1324, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btndangxuat1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 596, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TrangChuThuThu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TrangChuThuThu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TrangChuThuThu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TrangChuThuThu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TrangChuThuThu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnbieudothongkedocgia;
    private javax.swing.JButton btncapnhat3;
    private javax.swing.JButton btnchinhsuatlsach;
    private javax.swing.JButton btnchitiet;
    private javax.swing.JButton btnchitietphieumuon;
    private javax.swing.JButton btndangxuat1;
    private javax.swing.JButton btndsphieumuonlammoi;
    private javax.swing.JButton btnkhoadocgia;
    private javax.swing.JButton btnlammoidocgia;
    private javax.swing.JButton btnlammoinxb;
    private javax.swing.JButton btnlammoisach;
    private javax.swing.JButton btnlammoitlsach;
    private javax.swing.JButton btnmodocgia;
    private javax.swing.JButton btnmuonsachchon;
    private javax.swing.JButton btnmuonsachxoa;
    private javax.swing.JButton btnsuadocgia;
    private javax.swing.JButton btnsuanxb;
    private javax.swing.JButton btnsuasach;
    private javax.swing.JButton btntaophieumuon;
    private javax.swing.JButton btntaophieumuonlammoi;
    private javax.swing.JButton btnthemdocgia;
    private javax.swing.JButton btnthemnxb;
    private javax.swing.JButton btnthemsach;
    private javax.swing.JButton btnthemtlsach;
    private javax.swing.JButton btnthongketienphattheocacnam;
    private javax.swing.JButton btntimdocgia;
    private javax.swing.JButton btntimkiemsach;
    private javax.swing.JButton btntimphieumuon;
    private javax.swing.JButton btntrasach;
    private javax.swing.JButton btntrasachchon;
    private javax.swing.JButton btntrasachlammoi;
    private javax.swing.JButton btntrasachtim;
    private javax.swing.JButton btnxembieudo;
    private javax.swing.JButton btnxoadocgia;
    private javax.swing.JButton btnxoanxb;
    private javax.swing.JButton btnxoaphieumuon;
    private javax.swing.JButton btnxoasach;
    private javax.swing.JButton btnxoatlsach;
    private javax.swing.JButton btnxuatbaocaosach;
    private javax.swing.JButton btnxuatbaocaotienphat;
    private javax.swing.JButton btnxuatexcel;
    private javax.swing.ButtonGroup buttonGroupmuonsachtimkiemsach;
    private javax.swing.ButtonGroup buttonGroupsxdocgia;
    private javax.swing.ButtonGroup buttonGroupsxsach;
    private javax.swing.ButtonGroup buttonGrouptimkiemphieumuon;
    private javax.swing.ButtonGroup buttonGrouptimkiemsach;
    private javax.swing.ButtonGroup buttonGrouptkdocgia;
    private javax.swing.JComboBox<String> cbbthongkedocgia;
    private javax.swing.JComboBox<String> cbbthongkesach;
    private javax.swing.JComboBox<String> cbbtrangthaiphieu;
    private javax.swing.JComboBox<String> combodanhmucsach;
    private javax.swing.JComboBox<String> combonxb;
    private javax.swing.JComboBox<String> combotinhtrangsach;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JPanel jpnNhaXuatBan;
    private javax.swing.JPanel jpnQuanLyDanhMuc;
    private javax.swing.JPanel jpnQuanLyDocGia;
    private javax.swing.JPanel jpnQuanLyMuonTra;
    private javax.swing.JPanel jpnQuanLyTaiLieu;
    private javax.swing.JPanel jpnThongKeBaoCao;
    private javax.swing.JLabel lbsoluongdanhmuc;
    private javax.swing.JLabel lbsoluongdocgia1;
    private javax.swing.JLabel lbsoluongnxb;
    private javax.swing.JLabel lbsoluongphieu;
    private javax.swing.JLabel lbsoluongtailieu;
    private javax.swing.JRadioButton rbdocgiatheomagiam;
    private javax.swing.JRadioButton rbdocgiatheomatang;
    private javax.swing.JRadioButton rbsxsachtheoslgiam;
    private javax.swing.JRadioButton rbsxsachtheosltang;
    private javax.swing.JRadioButton rbsxsachtheotengiam;
    private javax.swing.JRadioButton rbsxsachtheotentang;
    private javax.swing.JRadioButton rbtenaz;
    private javax.swing.JRadioButton rbtenza;
    private javax.swing.JRadioButton rbtimmadocgia;
    private javax.swing.JRadioButton rbtimphieutheomadocgia;
    private javax.swing.JRadioButton rbtimphieutheomaphieu;
    private javax.swing.JRadioButton rbtimphieutheotendocgia;
    private javax.swing.JRadioButton rbtimsachtheodanhmuc;
    private javax.swing.JRadioButton rbtimsachtheoma;
    private javax.swing.JRadioButton rbtimsachtheonxb;
    private javax.swing.JRadioButton rbtimsachtheoten;
    private javax.swing.JRadioButton rbtimtendocgia;
    private javax.swing.JTable tbldanhsachphieumuon;
    private javax.swing.JTable tbldocgia;
    private javax.swing.JTable tblnhaxuatban;
    private javax.swing.JTable tblsach;
    private javax.swing.JTable tblsachdachon;
    private javax.swing.JTable tbltaophieumuonsach;
    private javax.swing.JTable tbltheloaisach;
    private javax.swing.JTable tblthongkedocgia;
    private javax.swing.JTable tblthongkesach;
    private javax.swing.JTable tblthongketienphat;
    private javax.swing.JTable tbltrasachchitietpm;
    private javax.swing.JTextField txtmadocgia;
    private javax.swing.JTextField txtmaphieu;
    private javax.swing.JTextField txtnamthongketailieu;
    private javax.swing.JTextField txtngaymuon;
    private javax.swing.JTextField txtsongaymuon;
    private javax.swing.JTextField txtsosachmuon;
    private javax.swing.JTextField txttendocgia;
    private javax.swing.JTextField txtthongketienphattheonam;
    private javax.swing.JTextField txttimdocgiatheoma;
    private javax.swing.JTextField txttimdocgiatheoten;
    private javax.swing.JTextField txttimkiemphieumuon;
    private javax.swing.JTextField txttimsachtheoma;
    private javax.swing.JTextField txttimsachtheoten;
    private javax.swing.JTextField txttrasachmaphieu;
    private javax.swing.JTextField txttrasachmaphieumuon;
    private javax.swing.JTextField txttrasachmasach;
    private javax.swing.JTextField txttrasachngaytra;
    private javax.swing.JTextField txttrasachngaytrehen;
    private javax.swing.JTextField txttrasachtienphat;
    // End of variables declaration//GEN-END:variables

    private void addButtonGroup() {
        buttonGroupsxdocgia.add(rbtenaz);
        buttonGroupsxdocgia.add(rbtenza);
        buttonGroupsxdocgia.add(rbdocgiatheomagiam);
        buttonGroupsxdocgia.add(rbdocgiatheomatang);
        buttonGrouptkdocgia.add(rbtimmadocgia);
        buttonGrouptkdocgia.add(rbtimtendocgia);

        buttonGroupsxsach.add(rbsxsachtheoslgiam);
        buttonGroupsxsach.add(rbsxsachtheosltang);
        buttonGroupsxsach.add(rbsxsachtheotengiam);
        buttonGroupsxsach.add(rbsxsachtheotentang);

        // buttonGrouptimkiemsach.add(rbtimsachtheodanhmuc);
        buttonGrouptimkiemsach.add(rbtimsachtheoma);
        buttonGrouptimkiemsach.add(rbtimsachtheoten);
        buttonGrouptimkiemsach.add(rbtimsachtheodanhmuc);
        buttonGrouptimkiemsach.add(rbtimsachtheonxb);
        // Tạo phiếu mượn
//       buttonGroupmuonsachtimkiemsach.add(rbmuonsachtimtheoma);
//       buttonGroupmuonsachtimkiemsach.add(rbmuonsachtimtheoten);
// Danh sách phiếu mượn
        buttonGrouptimkiemphieumuon.add(rbtimphieutheomadocgia);
        buttonGrouptimkiemphieumuon.add(rbtimphieutheomaphieu);
        buttonGrouptimkiemphieumuon.add(rbtimphieutheotendocgia);

    }

    private void addActionListener() {
        //rbslmuontang.addActionListener(this);

        btnchitiet.addActionListener(this);
        btnkhoadocgia.addActionListener(this);
        btnmodocgia.addActionListener(this);
        rbtenaz.addActionListener(this);
        rbtenza.addActionListener(this);
        rbtimmadocgia.addActionListener(this);
        rbtimtendocgia.addActionListener(this);
        btndangxuat1.addActionListener(this);
        btnlammoidocgia.addActionListener(this);
        btnsuadocgia.addActionListener(this);
        btnthemdocgia.addActionListener(this);
        btntimdocgia.addActionListener(this);
        btnxoadocgia.addActionListener(this);
        rbdocgiatheomagiam.addActionListener(this);
        rbdocgiatheomatang.addActionListener(this);
        btntimkiemsach.addActionListener(this);
        btnsuasach.addActionListener(this);
        btnthemsach.addActionListener(this);
        btnxoasach.addActionListener(this);
        btnlammoisach.addActionListener(this);
        rbsxsachtheoslgiam.addActionListener(this);
        rbsxsachtheosltang.addActionListener(this);
        rbsxsachtheotengiam.addActionListener(this);
        rbsxsachtheotentang.addActionListener(this);
        //rbtimsachtheodanhmuc.addActionListener(this);
        rbtimsachtheoma.addActionListener(this);
        rbtimsachtheodanhmuc.addActionListener(this);
        rbtimsachtheoten.addActionListener(this);
        rbtimsachtheonxb.addActionListener(this);

        btnchinhsuatlsach.addActionListener(this);
        btnthemtlsach.addActionListener(this);
        btnlammoitlsach.addActionListener(this);
        btnxoatlsach.addActionListener(this);
        //Quan lý mượn trả       
        btnmuonsachchon.addActionListener(this);
        btnmuonsachxoa.addActionListener(this);
        btntaophieumuon.addActionListener(this);
        btntrasach.addActionListener(this);
        btntrasachtim.addActionListener(this);
        btntrasachchon.addActionListener(this);
        //btntratoanbosach.addActionListener(this);
        combotinhtrangsach.addActionListener(this);
        btntrasachlammoi.addActionListener(this);

        btnxoaphieumuon.addActionListener(this);
        btndsphieumuonlammoi.addActionListener(this);
        btntaophieumuonlammoi.addActionListener(this);
        //Thống kê
        cbbthongkedocgia.addActionListener(this);
        btnxuatexcel.addActionListener(this);
        cbbthongkesach.addActionListener(this);
        btnxuatbaocaosach.addActionListener(this);
        btnxuatbaocaotienphat.addActionListener(this);
        // Danh sách phiếu mượn
        rbtimphieutheomadocgia.addActionListener(this);
        rbtimphieutheomaphieu.addActionListener(this);
        rbtimphieutheotendocgia.addActionListener(this);
        btntimphieumuon.addActionListener(this);
        btnchitietphieumuon.addActionListener(this);
        txtthongketienphattheonam.addKeyListener(this);
        txtthongketienphattheonam.addActionListener(this);
        txtmadocgia.addKeyListener(this);
        //Nhà xuất bản
        btnlammoinxb.addActionListener(this);
        btnthemnxb.addActionListener(this);
        btnxoanxb.addActionListener(this);
        btnsuanxb.addActionListener(this);
        btncapnhat3.addActionListener(this);
        //
        btnxembieudo.addActionListener(this);
        btnbieudothongkedocgia.addActionListener(this);
        btnthongketienphattheocacnam.addActionListener(this);
    }

    private void loadData() {
        banDocs = KetNoiSql.readBandocsFromSql();
        editMaBanDoc();
        sachs = KetNoiSql.readSachFromSql();
        danhsachtl = KetNoiSql.layTenTheLoaiSachSql();
        theLoaiSachs = KetNoiSql.readTheLoaiSachSql();
        phieuMuons = KetNoiSql.readPhieuMuonFromSql();
        editmaPhieuMuon();
        danhsachnxb = KetNoiSql.layTennxbSql();
        nhaXuatBans = KetNoiSql.readnxbSql();
    }

    private void editmaPhieuMuon() {
        var maxId = PhieuMuon.getsId();
        for (PhieuMuon s : phieuMuons) {
            if (s.getMaPM() > maxId) {
                maxId = s.getMaPM();
            }

        }
        PhieuMuon.setsId(maxId + 1);
    }

    private void editMaBanDoc() {
        var maxId = DocGia.getsId();
        for (DocGia s : banDocs) {
            if (s.getMaDocGia() > maxId) {
                maxId = s.getMaDocGia();
            }
        }
        DocGia.setsId(maxId + 1);
    }

    private void showData() {
        showBanDocs();
        showSachs();
        showcombotheloai();
        showtheloaisachs();
        showPhieuMuons();
        showTaoPhieuMuonSachs();
        showcombonxb();
        shownhaxuatbans();
        lbsoluongdocgia1.setText(String.valueOf(KetNoiSql.soluongdocgia()));
        lbsoluongtailieu.setText(String.valueOf(KetNoiSql.soluongtailieu()));
        lbsoluongdanhmuc.setText(String.valueOf(KetNoiSql.soluongdanhmuc()));
        lbsoluongnxb.setText(String.valueOf(KetNoiSql.soluongnxb()));
        lbsoluongphieu.setText(String.valueOf(KetNoiSql.soluongphieumuon()));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var obj = e.getSource();
        if (obj.equals(btndangxuat1)) {
            dispose();
            new DangNhap1().setVisible(true);
        } else if (obj.equals(btnlammoidocgia)) {
            lamMoiDocGia();
        } else if (obj.equals(rbtenaz)
                || obj.equals(rbtenza)
                || obj.equals(rbdocgiatheomagiam)
                || obj.equals(rbdocgiatheomatang)) {
            sapXepBanDocs(obj);
        } else if (obj.equals(btnthemdocgia)) {
            themDocGia();
        } else if (obj.equals(btnxoadocgia)) {
            xoaDocGia();
        } else if (obj.equals(btnsuadocgia)) {
            suaDocGia();
        } else if (obj.equals(btntimdocgia)) {
            timDocGia();
        } else if (obj.equals(btnlammoisach)) {
            lamMoiSach();
        } else if (obj.equals(rbsxsachtheoslgiam)
                || obj.equals(rbsxsachtheosltang)
                || obj.equals(rbsxsachtheotengiam)
                || obj.equals(rbsxsachtheotentang)) {
            sapXepSach(obj);
        } else if (obj.equals(btnthemsach)) {
            themSach();
        } else if (obj.equals(btnxoasach)) {
            xoaSach();
        } else if (obj.equals(btnsuasach)) {
            suaSach();
        } else if (obj.equals(btntimkiemsach)) {
            timSach();
        } //Thể loại sách
        else if (obj.equals(btnthemtlsach)) {
            themTheLoaiSach();
        } else if (obj.equals(btnxoatlsach)) {
            xoaTheLoaiSach();
        } else if (obj.equals(btnchinhsuatlsach)) {
            suaTheLoaiSach();

        } else if (obj.equals(btnlammoitlsach)) {
            lamMoiTheLoaiSach();
        } //quản lý mượn trả
        else if (obj.equals(btnmuonsachchon)) {
            quanlymuontrachon();
        } else if (obj.equals(btnmuonsachxoa)) {
            quanlymuontraxoa();
        } else if (obj.equals(btntaophieumuon)) {
            taophieumuon();
        } else if (obj.equals(btntrasach)) {
            traSach();
        } else if (obj.equals(btntrasachtim)) {
            timMaPhieu();
        } else if (obj.equals(btntrasachchon)) {
            chon();
        } //        } else if (obj.equals(btntratoanbosach)) {
        //            tratoanbosach();
        else if (obj.equals(combotinhtrangsach)) {
            thietlaptienphat();
        } else if (obj.equals(btntrasachlammoi)) {
            lammoi();
        } else if (obj.equals(btnxoaphieumuon)) {
            xoaphieumuon();
        } else if (obj.equals(btndsphieumuonlammoi)) {
            dspmlammoi();
        } else if (obj.equals(btntaophieumuonlammoi)) {
            taopmlammoi();

        } else if (obj.equals(cbbthongkedocgia)) {
            thongkedocgia();
        } else if (obj.equals(btnxuatexcel)) {
            xuatExcel();
        } else if (obj.equals(cbbthongkesach)) {
            thongkesach();
        } else if (obj.equals(btnxuatbaocaosach)) {
            xuatbaocaosach();
        } else if (obj.equals(btntimphieumuon)) {
            timphieumuon();
        } else if (obj.equals(btnchitietphieumuon)) {
            xemctpm();
        } else if (obj.equals(btnxuatbaocaotienphat)) {
            xuatbaocaotienphat();
        } else if (obj.equals(btnkhoadocgia)) {
            khoadocgia();
        } else if (obj.equals(btnmodocgia)) {
            modocgia();
        } else if (obj.equals(btnchitiet)) {
            chitiet();
        } //Nhà xuất bản
        else if (obj.equals(btnthemnxb)) {
            themnhaxuatban();
        } else if (obj.equals(btnxoanxb)) {
            xoanhaxuatban();
        } else if (obj.equals(btnsuanxb)) {
            suanhaxuatban();
        } else if (obj.equals(btnlammoinxb)) {
            lamMoinhaxuatban();
        } else if (obj.equals(btncapnhat3)) {
            capnhat3();
        } else if (obj.equals(btnxembieudo)) {
            xembieudotailieu();
        } else if (obj.equals(btnbieudothongkedocgia)) {
            xembieudodocgia();
        } else if (obj.equals(btnthongketienphattheocacnam)) {
            xembieudotienphat();
        }
    }

    private void showTaoPhieuMuonSachs() {
        tableModeltaophieumuonsach.setRowCount(0);
        for (TaiLieu sach : sachs) {
            showSach1(sach);
        }
    }

    private void showSachs() {
        tableModelsach.setRowCount(0);
        for (TaiLieu sach : sachs) {
            showSach(sach);
        }
    }

    private void showPhieuMuons() {
        tableModeldanhsachphieumuon.setRowCount(0);
        for (PhieuMuon s : phieuMuons) {
            showPhieuMuon(s);
        }
    }

    private void showPhieuMuon(PhieuMuon s) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        var row = new Object[]{s.getMaPM(), s.getMaBanDoc(), s.getTenBanDoc(),
            dateFormat.format(s.getNgayMuon()), String.valueOf(s.getSoNgayMuon()), String.valueOf(s.getSoSachMuon()), s.getTrangThai()};

        tableModeldanhsachphieumuon.addRow(row);
    }

    private void showBanDocs() {
        tableModeldocgia.setRowCount(0);
        for (DocGia banDoc : banDocs) {
            showBanDoc(banDoc);
        }
    }

    private void showtheloaisachs() {
        tableModeltheloaisach.setRowCount(0);
        for (DanhMucTaiLieu tls : theLoaiSachs) {
            showTheLoai(tls);
        }
    }

    private void showTheLoai(DanhMucTaiLieu tls) {
        var row = new Object[]{tls.getMadm(), tls.getTendm()};

        tableModeltheloaisach.addRow(row);
    }
//Nhà xuất bản

    private void shownhaxuatbans() {
        tableModelnxb.setRowCount(0);
        for (NhaXuatBan tls : nhaXuatBans) {
            shownxb(tls);
        }
    }

    private void shownxb(NhaXuatBan tls) {
        var row = new Object[]{tls.getMa(), tls.getName()};

        tableModelnxb.addRow(row);
    }

    private void showSach(TaiLieu s) {
        var a = KetNoiSql.laytendanhmucduavaoma(s.getMadanhmuc());
        var b = KetNoiSql.laytennxbduavaoma(s.getManxb());

        var row = new Object[]{s.getMatl(), s.getTentl(),
            a, s.getTacGia(), b, s.getNamXuatBan(), s.getSoLuongCon()
        };
        tableModelsach.addRow(row);
    }

    private void showSach1(TaiLieu s) {
        //var a = KetNoiSql.laytendanhmucduavaoma(s.getMadanhmuc());
        var b = KetNoiSql.laytennxbduavaoma(s.getManxb());

        var row = new Object[]{s.getMatl(), s.getTentl(),
            b, s.getNamXuatBan(), s.getSoLuongCon()
        };
        tableModeltaophieumuonsach.addRow(row);
    }

    private void showBanDoc(DocGia s) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        var row = new Object[]{s.getMaDocGia(), s.getFullName(),
            dateFormat.format(s.getDob()),
            s.getEmail(), s.getAddress(), s.getPhoneNumber(), s.getStatus()};
        tableModeldocgia.addRow(row);
    }
// Quản Lý Độc Giả

    private void sapXepBanDocs(Object obj) {
//        if (obj.equals(rbslmuontang)) {
//            dataControllerImp.sxdocgiatheoslmuontang(banDocs);
        if (obj.equals(rbtenaz)) {
            dataControllerImp.sxdocgiatheotentangdan(banDocs);

        } else if (obj.equals(rbtenza)) {
            dataControllerImp.sxdocgiatheotengiamdan(banDocs);

        } else if (obj.equals(rbdocgiatheomagiam)) {
            dataControllerImp.sxdocgiatheomagiamdan(banDocs);

        } else if (obj.equals(rbdocgiatheomatang)) {
            dataControllerImp.sxdocgiatheomatangdan(banDocs);

        }

        showBanDocs();
    }

    private void lamMoiDocGia() {
        buttonGroupsxdocgia.clearSelection();
        buttonGrouptkdocgia.clearSelection();
        txttimdocgiatheoma.setText("");
        txttimdocgiatheoten.setText("");
        tableModeldocgia.setRowCount(0);
        banDocs.clear();
        banDocs.addAll(KetNoiSql.readBandocsFromSql());
        showBanDocs();

    }

    private void timDocGia() {
        banDocs.clear();
        banDocs.addAll(KetNoiSql.readBandocsFromSql());
        if (rbtimtendocgia.isSelected()) {

            if (txttimdocgiatheoten.getText().isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn"
                        + " tên độc giả cần tìm kiếm");

            } else {
                var s1 = dataControllerImp.timdocgiatheoten(banDocs,
                        txttimdocgiatheoten.getText());
                if (s1.size() > 0) {
                    JOptionPane.showMessageDialog(rootPane,
                            "Tìm thấy " + s1.size() + " kết quả");
                    tableModeldocgia.setRowCount(0);
                    for (DocGia s : s1) {
                        showBanDoc(s);
                    }
                } else {
                    banDocs.clear();
                    showBanDocs();
                    JOptionPane.showMessageDialog(rootPane,
                            "Không tìm thấy kết quả nào");
                }

            }
        } else if (rbtimmadocgia.isSelected()) {
//            

            if (!txttimdocgiatheoma.getText().isEmpty()) {

                var s2 = dataControllerImp.
                        timdocgiatheoma(banDocs,
                                Integer.parseInt(txttimdocgiatheoma.getText()));
                if (s2.size() > 0) {
                    tableModeldocgia.setRowCount(0);
                    for (DocGia s : s2) {
                        showBanDoc(s);
                    }
                    JOptionPane.showMessageDialog(rootPane,
                            "Tìm thấy " + s2.size() + " kết quả");
                } else {
                    banDocs.clear();
                    showBanDocs();
                    JOptionPane.showMessageDialog(rootPane,
                            "Không tìm thấy kết quả nào");
                }

            } else {
                JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập"
                        + " mã độc giả cần tìm kiếm");
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn"
                    + " cách thức tìm kiếm");
        }
    }

    private void xoaDocGia() {
        var index = tbldocgia.getSelectedRow();
        if (index > -1) {
            int result = JOptionPane.showConfirmDialog(rootPane,
                    "Bạn có chắc chắn muón xoá độc giả này không?",
                    "Xác Nhận", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                tableModeldocgia.removeRow(index);
                tableModeldocgia.fireTableDataChanged();
                KetNoiSql.xoaDocGiaSql(banDocs.get(index));
//                banDocs.remove(index);
//                KetNoiSql.xoaDocGiaSql(banDocs.get(index));               

            }

        } else {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn"
                    + " một độc giả cần xoá khỏi danh sách");
        }
    }

    private void suaDocGia() {
        var index = tbldocgia.getSelectedRow();
       // var a = Integer.parseInt(tbldocgia.getValueAt(index, 0).toString().trim());
        if (index > -1) {
            
            DocGia banDoc = banDocs.get(index);
            //var banDoc = KetNoiSql.laymotdocgia(a);
            SuaDocGia sdg = new SuaDocGia(this, true, banDoc);
            sdg.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn"
                    + "  một độc giả cần thay đổi thông tin");
        }
    }

    void editDocGiaCallBack(DocGia banDoc) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        var index = tbldocgia.getSelectedRow();
        //student = students.get(index);     
        Object[] row = {banDoc.getMaDocGia(), banDoc.getFullName(),
            dateFormat.format(banDoc.getDob()), banDoc.getEmail(), banDoc.getAddress(),
            banDoc.getPhoneNumber(), banDoc.getStatus()
        };

        tableModeldocgia.removeRow(index);
        tableModeldocgia.insertRow(index, row);
        KetNoiSql.suaDocGiaSql(banDoc);

    }

    private void themDocGia() {
        ThemDocGia dk = new ThemDocGia(this, true, banDocs);
        dk.setVisible(true);

    }

    void themDocgiaCallBack(DocGia banDoc) {
        banDocs.add(banDoc);
        showBanDocs();
        KetNoiSql.saveBanDocDataToSql(banDoc);
    }
// Quản lý Sách

    private void lamMoiSach() {
        buttonGroupsxsach.clearSelection();
        buttonGrouptimkiemsach.clearSelection();
        txttimsachtheoma.setText("");
        txttimsachtheoten.setText("");
        combonxb.removeAllItems();
        combodanhmucsach.removeAllItems();
        danhsachnxb.clear();
        danhsachnxb.addAll(KetNoiSql.layTennxbSql());
        showcombonxb();
        combonxb.setSelectedIndex(-1);
        danhsachtl.clear();
        danhsachtl.addAll(KetNoiSql.layTenTheLoaiSachSql());
        showcombotheloai();
        combodanhmucsach.setSelectedIndex(-1);
        tableModelsach.setRowCount(0);
        sachs.clear();
        sachs.addAll(KetNoiSql.readSachFromSql());
        showSachs();
    }

    private void sapXepSach(Object obj) {
        if (obj.equals(rbsxsachtheoslgiam)) {
            dataControllerImp.sxtailieutheosoluonggiam(sachs);
        } else if (obj.equals(rbsxsachtheosltang)) {
            dataControllerImp.sxtailieutheosoluongtang(sachs);

        } else if (obj.equals(rbsxsachtheotengiam)) {
            dataControllerImp.sxtailieutheotengiamdan(sachs);

        } else if (obj.equals(rbsxsachtheotentang)) {
            dataControllerImp.sxtailieutheotentangdan(sachs);
        }
        showSachs();

    }

    private void themSach() {
        ThemTaiLieu ts = new ThemTaiLieu(this, true, sachs);
        ts.setVisible(true);
    }

    private void xoaSach() {
        var index = tblsach.getSelectedRow();
        if (index > -1) {
            int result = JOptionPane.showConfirmDialog(rootPane,
                    "Bạn có chắc chắn muón xoá thông tin tài liệu này không?",
                    "Xác Nhận", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                tableModelsach.removeRow(index);
                tableModelsach.fireTableDataChanged();
                KetNoiSql.xoaSachSql(sachs.get(index));

            }

        } else {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn"
                    + " thông tin của một tài liệu cần xoá bỏ");
        }
    }

    private void suaSach() {
        var index = tblsach.getSelectedRow();
        var a = tblsach.getValueAt(index, 0).toString().trim();
        if (index > -1) {
           // TaiLieu s = sachs.get(index);
           var s = KetNoiSql.layMotSachFromSql(a);
            SuaTaiLieu suaSach = new SuaTaiLieu(this, true, s);
            suaSach.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn"
                    + " thông tin của một tài liệu cần chỉnh sửa");
        }
    }

    private void timSach() {
        sachs.clear();
        sachs.addAll(KetNoiSql.readSachFromSql());
        if (rbtimsachtheoten.isSelected()) {

            if (txttimsachtheoten.getText().isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập"
                        + " tên tài liệu cần tìm kiếm");

            } else {
                var s1 = dataControllerImp.timtailieutheoten(sachs,
                        txttimsachtheoten.getText());
                if (s1.size() > 0) {
                    JOptionPane.showMessageDialog(rootPane,
                            "Tìm thấy " + s1.size() + " kết quả");
                    tableModelsach.setRowCount(0);
                    for (TaiLieu s : s1) {
                        showSach(s);
                    }
                } else {
                    sachs.clear();
                    showSachs();
                    JOptionPane.showMessageDialog(rootPane,
                            "Không tìm thấy kết quả nào");
                }

            }
        } else if (rbtimsachtheoma.isSelected()) {
//            

            if (!txttimsachtheoma.getText().isEmpty()) {

                var s2 = dataControllerImp.
                        timtailieutheoma(sachs, txttimsachtheoma.getText());
                if (s2.size() > 0) {
                    tableModelsach.setRowCount(0);
                    for (TaiLieu s : s2) {
                        showSach(s);
                    }
                    JOptionPane.showMessageDialog(rootPane,
                            "Tìm thấy " + s2.size() + " kết quả");
                } else {
                    sachs.clear();
                    showSachs();
                    JOptionPane.showMessageDialog(rootPane,
                            "Không tìm thấy kết quả nào");
                }

            } else {
                JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập"
                        + " mã tài liệu cần tìm kiếm");
            }
        } else if (rbtimsachtheodanhmuc.isSelected()) {

            if (combodanhmucsach.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn"
                        + " danh mục cần tìm kiếm");
            } else {
                var major1 = combodanhmucsach.getSelectedItem().toString();
                var s3 = dataControllerImp.timtailieutheodanhmuc(sachs, major1);
                if (s3.size() > 0) {
                    sachs.clear();
                    sachs.addAll(s3);
                    showSachs();
                    JOptionPane.showMessageDialog(rootPane,
                            "Tìm thấy " + s3.size() + " kết quả");
                } else {
                    sachs.clear();
                    showSachs();
                    JOptionPane.showMessageDialog(rootPane,
                            "Không tìm thấy kết quả nào");
                }
            }

        } else if (rbtimsachtheonxb.isSelected()) {
            if (combonxb.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn"
                        + " tên một nhà xuất bản cần tìm kiếm");
            } else {
                var major1 = combonxb.getSelectedItem().toString();
                var s3 = dataControllerImp.timtailieutheonxb(sachs, major1);
                if (s3.size() > 0) {
                    sachs.clear();
                    sachs.addAll(s3);
                    showSachs();
                    JOptionPane.showMessageDialog(rootPane,
                            "Tìm thấy " + s3.size() + " kết quả");
                } else {
                    sachs.clear();
                    showSachs();
                    JOptionPane.showMessageDialog(rootPane,
                            "Không tìm thấy kết quả nào");
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn"
                    + " cách thức tìm kiếm");
        }

    }

    void themSachCallBack(TaiLieu sach
    ) {
        sachs.add(sach);
        showSachs();
        KetNoiSql.saveSachSql(sach);
    }

    void suathongtinsach(TaiLieu sach) {
        var a = KetNoiSql.laytendanhmucduavaoma(sach.getMadanhmuc());
        var b = KetNoiSql.laytennxbduavaoma(sach.getManxb());
        var index = tblsach.getSelectedRow();
        var row = new Object[]{sach.getMatl(), sach.getTentl(),
            a, sach.getTacGia(), b, String.valueOf(sach.getNamXuatBan()),
            String.valueOf(sach.getSoLuongCon())};
        tableModelsach.removeRow(index);
        tableModelsach.insertRow(index, row);
        KetNoiSql.suathongtinsachSql(sach);
    }
    //Danh mục thể loại

    private void showcombotheloai() {
        for (String str : danhsachtl) {
            combodanhmucsach.addItem(str);
        }
    }

    private void showcombonxb() {
        for (String str : danhsachnxb) {
            combonxb.addItem(str);
        }
    }

    private void themTheLoaiSach() {
        ThemDanhMucTaiLieu themmoi = new ThemDanhMucTaiLieu(theLoaiSachs, this, true);
        themmoi.setVisible(true);
    }

    private void xoaTheLoaiSach() {
        var index = tbltheloaisach.getSelectedRow();
        if (index > -1) {
            int result = JOptionPane.showConfirmDialog(rootPane,
                    "Bạn có chắc chắn muón xoá danh mục tài liệu này không?",
                    "Xác Nhận", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                tableModeltheloaisach.removeRow(index);
                tableModeltheloaisach.fireTableDataChanged();
                KetNoiSql.xoatheloaisachSql(theLoaiSachs.get(index));
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn"
                    + " một danh mục cần xoá bỏ");
        }
    }

    private void suaTheLoaiSach() {
        var index = tbltheloaisach.getSelectedRow();
        if (index > -1) {
            DanhMucTaiLieu s = theLoaiSachs.get(index);
            SuaDanhMucTaiLieu sua = new SuaDanhMucTaiLieu(s, this, true);
            sua.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn"
                    + "  một danh mục cần chỉnh sửa!");
        }
    }

    private void lamMoiTheLoaiSach() {
        tableModeltheloaisach.setRowCount(0);
        theLoaiSachs.clear();
        theLoaiSachs.addAll(KetNoiSql.readTheLoaiSachSql());
        showtheloaisachs();
    }

    void themTheloaiSachCallBack(DanhMucTaiLieu theLoaiSach) {
        theLoaiSachs.add(theLoaiSach);
        showtheloaisachs();
        KetNoiSql.themTheLoaiSach(theLoaiSach);
    }

    void suathongtintheloaisach(DanhMucTaiLieu theLoaiSach) {
        var index = tbltheloaisach.getSelectedRow();
        var row = new Object[]{String.valueOf(theLoaiSach.getMadm()), theLoaiSach.getTendm()
        };
        tableModeltheloaisach.removeRow(index);
        tableModeltheloaisach.insertRow(index, row);
        KetNoiSql.capNhatTheLoaiSach(theLoaiSach);
    }
//Quản lý mượn trả

    private void quanlymuontrachon() {
        var index = tbltaophieumuonsach.getSelectedRow();
        if (index > -1) {
            var s = sachs.get(index);
            if (Integer.parseInt(s.getSoLuongCon()) > 0) {
                if (sachs1.contains(s)) {
                    JOptionPane.showMessageDialog(rootPane, "Bạn đã mượn một tài liệu này rồi\n "
                            + "Vui lòng chọn tài liệu khác trong thư viện!");
                } else {
                    sachs1.add(s);
                    showSachs1();
                    thietlapsosachmuon();
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Tài liệu này hiện"
                        + " trong thư viện đã hết\n Độc giả vui lòng lựa chọn tài liệu khác!");
            }

        } else {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn"
                    + "  một tài liệu cần mượn");
        }

    }

    private void showSachs1() {
        tableModelsachdachon.setRowCount(0);
        for (TaiLieu s : sachs1) {
            showSachdachon(s);
        }
    }

    private void showSachdachon(TaiLieu s) {
        var row = new Object[]{s.getMatl(), s.getTentl()
        };
        tableModelsachdachon.addRow(row);
    }
    //

    private void quanlymuontraxoa() {
        var index = tblsachdachon.getSelectedRow();
        if (index > -1) {
            sachs1.remove(index);
            tableModelsachdachon.removeRow(index);
            tableModelsachdachon.fireTableDataChanged();
        } else {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn"
                    + "  một tài liệu độc giả không muốn mượn!");
        }
        thietlapsosachmuon();
    }

    private void thietlapsosachmuon() {
        var a = tableModelsachdachon.getRowCount();
        //var c = lbgioihan.getText();
        txtsosachmuon.setText(String.valueOf(a));
        if (a > 3) {
            txtsosachmuon.setForeground(Color.red);
            JOptionPane.showMessageDialog(rootPane, "Bạn đã mượn quá "
                    + "  số tài liệu cho phép của thư viện!");
        } else {
            txtsosachmuon.setForeground(Color.BLACK);
        }
    }

    private void taophieumuon() {
        var maPhieu = txtmaphieu.getText();
        var hoten = txttendocgia.getText();
        var maDocGia = txtmadocgia.getText();
        var sosachmuon = txtsosachmuon.getText();
        var format = "dd/MM/yyyy ";
        var date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        var currentTime = dateFormat.format(date);
        var ngaymuon = txtngaymuon.getText();
        var songaymuon = txtsongaymuon.getText();

        if (maPhieu.isEmpty() || hoten.isEmpty() || maDocGia.isEmpty() || sosachmuon.isEmpty()
                || songaymuon.isEmpty() || cbbtrangthaiphieu == null) {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng kiểm tra lại các thông "
                    + "  tin trước khi  mượn tài liệu!");
        } else {
            var trangthai = cbbtrangthaiphieu.getSelectedItem().toString();
            if (Integer.parseInt(songaymuon) > 14) {
                JOptionPane.showMessageDialog(this,
                        "Vui lòng kiểm tra lại số ngày mượn",
                        "Thất bại", JOptionPane.ERROR_MESSAGE);

            }
            if (Integer.parseInt(sosachmuon) > 3) {
                JOptionPane.showMessageDialog(this,
                        "Vui lòng kiểm tra lại số lượng tài liệu đã mượn",
                        "Thất bại", JOptionPane.ERROR_MESSAGE);
            } else {
                txtngaymuon.setText(currentTime);
                var ngaymuon1 = txtngaymuon.getText();
                try {

                    PhieuMuon phieuMuon = new PhieuMuon(Integer.parseInt(maPhieu), Integer.parseInt(maDocGia), hoten, dateFormat.parse(ngaymuon1),
                            Integer.parseInt(songaymuon), Integer.parseInt(sosachmuon), trangthai, mathuthu);
                    if (phieuMuons.contains(phieuMuon)) {
                        JOptionPane.showMessageDialog(rootPane,
                                "Phiếu mượn có mã " + maPhieu + " đã tồn tại, vui lòng thay đổi mã phiếu!");
                    } else {
                        KetNoiSql.savePhieuMuonSql(phieuMuon);
                        var y = Integer.parseInt(sosachmuon);
                        var x = Integer.parseInt(maDocGia);
                        JOptionPane.showMessageDialog(rootPane, "Tạo phiếu mượn thành công.\n "
                                + "Mời bạn đến thư viện nhận sách!");
                        var maphieumuon = Integer.parseInt(maPhieu);
                        for (int i = 0; i < tableModelsachdachon.getRowCount(); i++) {
                            ChiTietPhieuMuon ctpm = new ChiTietPhieuMuon(maphieumuon,
                                    String.valueOf(tblsachdachon.getValueAt(i, 0)), "", "", "");
                            KetNoiSql.savachitietphieumuonSql(ctpm);
                        }
                        var masachmuons = KetNoiSql.laymasachtuchitietphieumuon(maphieumuon);
                        for (int i = 0; i < sachs.size(); i++) {
                            for (int j = 0; j < masachmuons.size(); j++) {
                                if (sachs.get(i).getMatl().compareToIgnoreCase(masachmuons.get(j)) == 0) {
                                    //dem = dem + 1;
                                    KetNoiSql.capnhatsoluongconcuasachSql(sachs.get(i).getMatl(), Integer.parseInt(sachs.get(i).getSoLuongCon()) - 1);
                                }

                            }

                        }

                    }

                } catch (ParseException ex) {
                }

            }

        }

    }

    private void timMaPhieu() {
        var a = txttrasachmaphieumuon.getText();
        var maphieumuons = KetNoiSql.readMaPhieuMuonFromSql();

        if (a.isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập mã phiếu cần tìm kiếm "
            );
        } else {
            if (maphieumuons.contains(Integer.parseInt(a))) {
                chiTietPhieuMuons = KetNoiSql.readChiTietPhieuMuonSql(Integer.parseInt(a));
                showChiTietPhieuMuons();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Mã phiếu này chưa tồn tại \n"
                        + "Vui lòng kiểm tra lại! "
                );
                txttrasachmaphieumuon.setText("");
                txttrasachmaphieumuon.requestFocus();
            }

        }
    }

    private void showChiTietPhieuMuons() {
        tableModeltrasachchitietpm.setRowCount(0);
        for (ChiTietPhieuMuon s : chiTietPhieuMuons) {
            showchitietpm(s);
        }
    }

    private void showchitietpm(ChiTietPhieuMuon s) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
            var a = s.getNgayTra();
            var b = dateFormat1.parse(a);
            var c = dateFormat.format(b);
            if (a.equals("1900-01-01")) {
                c = "";
            }
            var row = new Object[]{String.valueOf(s.getMaPM()), s.getMaSach(),
                (c), String.valueOf(s.getTienPhat()), s.getTinhTrangSach(),};

            tableModeltrasachchitietpm.addRow(row);
        } catch (ParseException ex) {
        }
    }

    private void chon() {
        var index = tbltrasachchitietpm.getSelectedRow();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        //SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        var date = new Date();
        if (index > -1) {
            var s = chiTietPhieuMuons.get(index);
            txttrasachmaphieu.setText(String.valueOf(s.getMaPM()));
            txttrasachmasach.setText(s.getMaSach());
            txttrasachngaytra.setText(dateFormat.format(date));
            var a = txttrasachmaphieu.getText();
            var f = txttrasachmasach.getText();
            var b = txttrasachngaytra.getText();
            //var d = dateFormat1.parse(b);
            //var k = dateFormat.format(d);
            //Tính số ngày trả quá hạn
            var e = KetNoiSql.readNgayMuonSql(Integer.parseInt(a));
            var g = (KetNoiSql.readSoNgayMuonSql(Integer.parseInt(a)));
            var ngaymuon = LocalDate.parse(e, dtf);
            var ngayphaitra = (ngaymuon.plusDays(g));
            var ngaythuctra = LocalDate.parse(b, dtf);
            long songaytre = ChronoUnit.DAYS.between(ngayphaitra, ngaythuctra);
            txttrasachngaytrehen.setText(String.valueOf(songaytre));
        } else {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn một tài liệu cần trả "
            );
        }

    }
    // Hư Hỏng 10%
//Hư Hỏng 30%
//Hư Hỏng 50%
//Hư Hỏng 70%

    private void thietlaptienphat() {

        if (combotinhtrangsach.getSelectedItem() != null && !txttrasachmaphieu.getText().isBlank() && !txttrasachmasach.getText().isBlank()
                && !txttrasachngaytra.getText().isBlank() && !txttrasachngaytrehen.getText().isBlank()) {
            int songaytre = Integer.parseInt(txttrasachngaytrehen.getText());
            var c = combotinhtrangsach.getSelectedItem().toString();
            var masach = txttrasachmasach.getText();
            var soluongcon = KetNoiSql.laysoluongconcuaSachSql(masach);
            var sach = KetNoiSql.layMotSachFromSql(masach);
            if (songaytre < 0) {
                if (c.equals("Bình Thường")) {
                    var tienphat = 0;
                    txttrasachtienphat.setText(String.valueOf(tienphat));
                    KetNoiSql.capnhatsoluongconcuasachSql(masach, soluongcon + 1);
                } else if (c.equals("Hư Hỏng 10%")) {
                    var tienphat = Integer.parseInt(sach.getGiasach()) / 10;

                    txttrasachtienphat.setText(String.valueOf(tienphat));
                } else if (c.equals("Hư Hỏng 30%")) {
                    var tienphat = Integer.parseInt(sach.getGiasach()) * 3 / 10;

                    txttrasachtienphat.setText(String.valueOf(tienphat));
                } else if (c.equals("Hư Hỏng 50%")) {
                    var tienphat = Integer.parseInt(sach.getGiasach()) / 2;

                    txttrasachtienphat.setText(String.valueOf(tienphat));
                } else if (c.equals("Hư Hỏng 70%")) {
                    var tienphat = Integer.parseInt(sach.getGiasach()) * 7 / 10;

                    txttrasachtienphat.setText(String.valueOf(tienphat));
                } else if (c.equals("Mất Sách")) {
                    var tienphat = sach.getGiasach();

                    txttrasachtienphat.setText(String.valueOf(tienphat));
                }
            } else {
                if (c.equals("Bình Thường")) {
                    var tienphat = songaytre * 10000;
                    txttrasachtienphat.setText(String.valueOf(tienphat));
                    KetNoiSql.capnhatsoluongconcuasachSql(masach, soluongcon + 1);
                } else if (c.equals("Hư Hỏng 10%")) {
                    var tienphat = songaytre * 10000 + Integer.parseInt(sach.getGiasach()) / 10;
                    txttrasachtienphat.setText(String.valueOf(tienphat));
                } else if (c.equals("Hư Hỏng 30%")) {
                    var tienphat = songaytre * 10000 + Integer.parseInt(sach.getGiasach()) * 3 / 10;
                    txttrasachtienphat.setText(String.valueOf(tienphat));
                } else if (c.equals("Hư Hỏng 50%")) {
                    var tienphat = songaytre * 10000 + Integer.parseInt(sach.getGiasach()) / 2;
                    txttrasachtienphat.setText(String.valueOf(tienphat));
                } else if (c.equals("Hư Hỏng 70%")) {
                    var tienphat = songaytre * 10000 + Integer.parseInt(sach.getGiasach()) * 7 / 10;
                    txttrasachtienphat.setText(String.valueOf(tienphat));
                } else if (c.equals("Mất Sách")) {
                    var tienphat = songaytre * 10000 + sach.getGiasach();
                    txttrasachtienphat.setText(String.valueOf(tienphat));
                }
            }

        }
//        else {
//            
//            JOptionPane.showMessageDialog(rootPane, ""
//                    + "Vui lòng kiểm tra lại thông tin ở các ô nhập liệu");
//        }

    }

    private void tratoanbosach() {

    }

    private void traSach() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        var index = tbltrasachchitietpm.getSelectedRow();
        var a = txttrasachmaphieu.getText();
        var b = txttrasachmasach.getText();
        var c = txttrasachngaytra.getText();
        var d = txttrasachtienphat.getText();

        if (a.isEmpty() || b.isEmpty() || c.isEmpty() || d.isEmpty() || combotinhtrangsach.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(rootPane, "Trả tài liệu thất bại\nVui lòng kiểm tra lại!");

        } else {
            var e = combotinhtrangsach.getSelectedItem().toString();
            ChiTietPhieuMuon ctpm = new ChiTietPhieuMuon(Integer.parseInt(a), b, (c), e, d);
            var row = new Object[]{String.valueOf(ctpm.getMaPM()), ctpm.getMaSach(), ctpm.getNgayTra(),
                String.valueOf(ctpm.getTienPhat()), ctpm.getTinhTrangSach()};
            tableModeltrasachchitietpm.removeRow(index);
            tableModeltrasachchitietpm.insertRow(index, row);
            KetNoiSql.capnhatChiTietPM(ctpm);
            // Cập nhật trạng thái đã trả hay chưa trả cho phiếu mượn
            var listmasach = KetNoiSql.laymasachtuchitietphieumuon(ctpm.getMaPM());
            var trangthaiphieu = "Đã trả";
            for (int i = 0; i < listmasach.size(); i++) {
                var trangthaisach = KetNoiSql.laytinhtrangsachctpmSql(ctpm.getMaPM(), listmasach.get(i));
                if (trangthaisach.isBlank()) {
                    trangthaiphieu = "Chưa trả";
                    break;
                }

            }
            KetNoiSql.capnhattrangthaiphieumuonSql(String.valueOf(ctpm.getMaPM()), trangthaiphieu);
            txttrasachmaphieu.setText("");
            txttrasachmasach.setText("");
            txttrasachngaytra.setText("");
            txttrasachngaytrehen.setText("");
            txttrasachtienphat.setText("");
            combotinhtrangsach.setSelectedIndex(-1);
        }

    }

    private void lammoi() {
        txttrasachmaphieumuon.setText("");
        txttrasachmaphieumuon.requestFocus();
        tableModeltrasachchitietpm.setRowCount(0);
        txttrasachmaphieu.setText("");
        txttrasachmasach.setText("");
        txttrasachngaytra.setText("");
        txttrasachngaytrehen.setText("");
        txttrasachtienphat.setText("");
        combotinhtrangsach.setSelectedIndex(0);

    }

    private void xoaphieumuon() {
        var index = tbldanhsachphieumuon.getSelectedRow();
        if (index > -1) {
            int result = JOptionPane.showConfirmDialog(rootPane,
                    "Bạn có chắc chắn muón xoá thông tin phiếu mượn này không?",
                    "Xác Nhận", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                tableModeldanhsachphieumuon.removeRow(index);
                tableModeldanhsachphieumuon.fireTableDataChanged();
                KetNoiSql.xoaPhieuMuonSql(phieuMuons.get(index));
                KetNoiSql.xoaChiTietPM(phieuMuons.get(index).getMaPM());

            }

        } else {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn"
                    + " thông tin của một phiếu mượn cần xoá bỏ");
        }
    }

    private void dspmlammoi() {
        phieuMuons = KetNoiSql.readPhieuMuonFromSql();
        showPhieuMuons();
        txttimkiemphieumuon.setText("");
        buttonGrouptimkiemphieumuon.clearSelection();
    }

    private void taopmlammoi() {
        var str = "";
        editmaPhieuMuon();
        txtmaphieu.setText(PhieuMuon.getsId() + "");
        txttendocgia.setText(str);
        txtmadocgia.setText(str);
        txtsosachmuon.setText(str);
        txtngaymuon.setText(str);
        txtsongaymuon.setText(str);
        cbbtrangthaiphieu.setSelectedIndex(0);
        sachs.clear();
        sachs.addAll(KetNoiSql.readSachFromSql());
        showTaoPhieuMuonSachs();
        sachs1.clear();
        showSachs1();
    }
//Độc giả mượn nhiều nhất
//Độc giả mượn quá hạn
//Độc giả chưa trả sách

    private void thongkedocgia() {
        var chon = cbbthongkedocgia.getSelectedItem().toString();
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sd1 = new SimpleDateFormat("yyyy-MM-dd");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        if (chon.compareToIgnoreCase("Độc giả mượn nhiều nhất") == 0) {
            try {
                ResultSet muonnhieunhat = KetNoiSql.docgiamuonnhieunhat();

                tableModelthongkedocgia.setColumnCount(0);
                tableModelthongkedocgia.addColumn("Mã độc giả");
                tableModelthongkedocgia.addColumn("Tên độc giả");
                tableModelthongkedocgia.addColumn("Ngày sinh");
                tableModelthongkedocgia.addColumn("Email");
                tableModelthongkedocgia.addColumn("Địa chỉ");
                tableModelthongkedocgia.addColumn("Số điện thoại");
                tableModelthongkedocgia.addColumn("Số lần mượn");
                tableModelthongkedocgia.setRowCount(0);
                if (muonnhieunhat != null) {
                    while (muonnhieunhat.next()) {
                        tableModelthongkedocgia.addRow(new Object[]{String.valueOf(muonnhieunhat.getInt(1)), muonnhieunhat.getString(2),
                            muonnhieunhat.getString(3), muonnhieunhat.getString(4), muonnhieunhat.getString(5), muonnhieunhat.getString(6), muonnhieunhat.getInt(7)});
                    }
                }

            } catch (SQLException ex) {
            }
        } else if (chon.compareToIgnoreCase("Độc giả mượn quá hạn") == 0) {
            try {
                ResultSet quahan = KetNoiSql.docgiaquahantra();

                tableModelthongkedocgia.setColumnCount(0);
                tableModelthongkedocgia.addColumn("Mã độc giả");
                tableModelthongkedocgia.addColumn("Tên độc giả");
                tableModelthongkedocgia.addColumn("Ngày mượn");
                tableModelthongkedocgia.addColumn("Số ngày mượn");
                tableModelthongkedocgia.addColumn("Ngày thực trả");
                tableModelthongkedocgia.addColumn("Số ngày quá hạn");

                tableModelthongkedocgia.setRowCount(0);
                if (quahan != null) {
                    while (quahan.next()) {
                        var a = quahan.getString(3);
                        var b = quahan.getInt(4);
                        var c = quahan.getString(5);
                        if (!c.isEmpty()) {
                            var d = sd1.parse(c);
                            var e = sd.format(d);
                            var ngaymuon = LocalDate.parse(a, dtf);
                            var ngayphaitra = (ngaymuon.plusDays(b));
                            var ngaythuctra = LocalDate.parse(e, dtf);
                            long songaytre = ChronoUnit.DAYS.between(ngayphaitra, ngaythuctra);
                            if (songaytre > 0) {
                                tableModelthongkedocgia.addRow(new Object[]{quahan.getString(1), quahan.getString(2), (quahan.getString(3)), quahan.getInt(4), e, songaytre});
                            }
                        }
                    }
                }
            } catch (SQLException ex) {
            } catch (ParseException ex) {
            }
        } else if (chon.compareToIgnoreCase("Độc giả chưa trả tài liệu") == 0) {
            try {
                ResultSet chuatrasach = KetNoiSql.docgiachuatrasach();

                tableModelthongkedocgia.setColumnCount(0);
                tableModelthongkedocgia.addColumn("Mã phiếu mượn");
                tableModelthongkedocgia.addColumn("Mã độc giả");
                tableModelthongkedocgia.addColumn("Tên độc giả");
                tableModelthongkedocgia.addColumn("Ngày mượn");
                tableModelthongkedocgia.addColumn("Số ngày mượn");
                tableModelthongkedocgia.addColumn("Số tài liệu mượn");
                tableModelthongkedocgia.addColumn("Trạng thái");

                tableModelthongkedocgia.setRowCount(0);
                if (chuatrasach != null) {
                    while (chuatrasach.next()) {
                        tableModelthongkedocgia.addRow(new Object[]{String.valueOf(chuatrasach.getInt(1)), chuatrasach.getString(2), chuatrasach.getString(3),
                            chuatrasach.getString(4), chuatrasach.getString(5), chuatrasach.getString(6), chuatrasach.getString(7)});
                    }
                }
            } catch (SQLException ex) {
            }
        }
    }

    private void xuatExcel() {

        try {
            String[] columns = {"Mã độc giả", "Tên độc giả", "Ngày sinh", "Email", "Địa chỉ", "Số điện thoại", "Số lần mượn"};
            String[] columns1 = {"Mã độc giả", "Tên độc giả", "Ngày mượn", "Số ngày mượn", "Ngày thực trả", "Số ngày quá hạn"};
            String[] columns2 = {"Mã phiếu mượn", "Mã độc giả", "Tên độc giả", "Ngày mượn", "Số ngày mượn", "Số tài liệu mượn", "Trạng thái"};
            var workbook = new XSSFWorkbook();

            //set font
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short) 14);
            headerFont.setColor(IndexedColors.RED.getIndex());
            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);
            // tạo hàng
            var chon = cbbthongkedocgia.getSelectedItem().toString();
            SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/uuuu");
            if (chon.compareToIgnoreCase("Độc giả mượn nhiều nhất") == 0) {
                var sheet = workbook.createSheet("Độc giả mượn nhiều nhất");

                ResultSet muonnhieunhat = KetNoiSql.docgiamuonnhieunhat();
                Row headerRow = sheet.createRow(0);
                for (int i = 0; i < columns.length; i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(columns[i]);
                    cell.setCellStyle(headerCellStyle);
                }
                int rowNum = 1;
                while (muonnhieunhat.next()) {
                    Row row = sheet.createRow(rowNum++);
                    row.createCell(0).setCellValue(String.valueOf(muonnhieunhat.getInt(1)));
                    row.createCell(1).setCellValue(muonnhieunhat.getString(2));
                    row.createCell(2).setCellValue(muonnhieunhat.getString(3));
                    row.createCell(3).setCellValue(muonnhieunhat.getString(4));
                    row.createCell(4).setCellValue(muonnhieunhat.getString(5));
                    row.createCell(5).setCellValue(muonnhieunhat.getString(6));
                    row.createCell(6).setCellValue(String.valueOf(muonnhieunhat.getInt(7)));

                }
                FileOutputStream fileOut = new FileOutputStream("D:\\docgiamuonnhieu.xlsx");
                workbook.write(fileOut);
                fileOut.close();
                workbook.close();

            } else if (chon.compareToIgnoreCase("Độc giả mượn quá hạn") == 0) {

                SimpleDateFormat sd1 = new SimpleDateFormat("yyyy-MM-dd");
                var sheet1 = workbook.createSheet("Độc giả mượn quá hạn");
                ResultSet m = KetNoiSql.docgiaquahantra();
                Row headerRow = sheet1.createRow(0);
                for (int i = 0; i < columns1.length; i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(columns1[i]);
                    cell.setCellStyle(headerCellStyle);
                }
                int rowNum = 1;
                while (m.next()) {
                    var a = m.getString(3);
                    var b = m.getInt(4);
                    var c = m.getString(5);
                    if (!c.isEmpty()) {
                        var d = sd1.parse(c);
                        var e = sd.format(d);
                        //Tính số ngày trễ
                        var ngaymuon = LocalDate.parse(a, dtf);
                        var ngayphaitra = (ngaymuon.plusDays(b));
                        var ngaythuctra = LocalDate.parse(e, dtf);
                        long songaytre = ChronoUnit.DAYS.between(ngayphaitra, ngaythuctra);
                        //
                        if (songaytre > 0) {
                            Row row = sheet1.createRow(rowNum++);
                            row.createCell(0).setCellValue(String.valueOf(m.getString(1)));
                            row.createCell(1).setCellValue(String.valueOf(m.getString(2)));
                            row.createCell(2).setCellValue(m.getString(3));
                            row.createCell(3).setCellValue(String.valueOf(m.getInt(4)));
                            row.createCell(4).setCellValue(String.valueOf(e));
                            row.createCell(5).setCellValue(String.valueOf(songaytre));
                        }
                    }

                }
                FileOutputStream fileOut = new FileOutputStream("D:\\docgiamuonquahan.xlsx");
                workbook.write(fileOut);
                fileOut.close();
                workbook.close();

            } else if (chon.compareToIgnoreCase("Độc giả chưa trả tài liệu") == 0) {
                var sheet2 = workbook.createSheet("Độc giả chưa trả tài liệu");
                ResultSet c = KetNoiSql.docgiachuatrasach();
                Row headerRow = sheet2.createRow(0);
                for (int i = 0; i < columns2.length; i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(columns2[i]);
                    cell.setCellStyle(headerCellStyle);
                }
                int rowNum = 1;
                while (c.next()) {
                    Row row = sheet2.createRow(rowNum++);
                    row.createCell(0).setCellValue(String.valueOf(c.getInt(1)));
                    row.createCell(1).setCellValue(c.getString(2));
                    row.createCell(2).setCellValue(c.getString(3));
                    row.createCell(3).setCellValue(c.getString(4));
                    row.createCell(4).setCellValue(c.getString(5));
                    row.createCell(5).setCellValue(c.getString(6));
                    row.createCell(6).setCellValue(c.getString(7));

                }
                FileOutputStream fileOut = new FileOutputStream("D:\\docgiachuatratailieu.xlsx");
                workbook.write(fileOut);
                fileOut.close();
                workbook.close();
            }

            JOptionPane.showMessageDialog(rootPane, "Tạo file"
                    + " báo cáo thành công!");

        } catch (IOException ex) {
        } catch (SQLException ex) {
        } catch (ParseException ex) {
            Logger.getLogger(TrangChuThuThu.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private Workbook getWorkbook(String excelFilePath) {
        Workbook workbook = null;
        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook();
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook();
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }
        return workbook;
    }
//Sách được mượn nhiều nhất
//Sách hư hỏng
//Sách bị mất

    private void thongkesach() {
        var chon = cbbthongkesach.getSelectedItem().toString();
        if (chon.compareToIgnoreCase("Top 5 tài liệu được mượn nhiều nhất") == 0) {
            try {
                ResultSet muonnhieunhat = KetNoiSql.sachmuonnhieunhat();

                tableModelthongkesach.setColumnCount(0);
                tableModelthongkesach.addColumn("Mã tài liệu");
                tableModelthongkesach.addColumn("Tên tài liệu");
                tableModelthongkesach.addColumn("Số lượt mượn");
                tableModelthongkesach.setRowCount(0);
                while (muonnhieunhat.next()) {
                    tableModelthongkesach.addRow(new Object[]{String.valueOf(muonnhieunhat.getString(1)), muonnhieunhat.getString(2), muonnhieunhat.getInt(3)});
                }
            } catch (SQLException ex) {
            }
        } else if (chon.compareToIgnoreCase("Tài liệu hư hỏng") == 0) {
            try {
                ResultSet huhong = KetNoiSql.tailieuhuhong();

                tableModelthongkesach.setColumnCount(0);
                tableModelthongkesach.addColumn("Mã Tài liệu");
                tableModelthongkesach.addColumn("Tên Tài Liệu");
                tableModelthongkesach.addColumn("Danh Mục");
                tableModelthongkesach.addColumn("Tác Giả");
                tableModelthongkesach.addColumn("Nhà Xuất Bản");
                tableModelthongkesach.addColumn("Năm Xuất Bản");
                tableModelthongkesach.addColumn("Số Lượng Hỏng");
                tableModelthongkesach.setRowCount(0);

                if (huhong != null) {
                    while (huhong.next()) {
                        tableModelthongkesach.addRow(new Object[]{huhong.getString(1), huhong.getString(2),
                            KetNoiSql.laytendanhmucduavaoma(huhong.getString(3)), huhong.getString(4), KetNoiSql.laytennxbduavaoma(huhong.getString(5)), huhong.getString(6), huhong.getString(7)});
                    }
                }
                if (tableModelthongkesach.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(rootPane, "Không có "
                            + " tài liệu nào bị hỏng!");

                }
            } catch (SQLException ex) {
            }

        } else if (chon.compareToIgnoreCase("Tài liệu bị mất") == 0) {
            try {
                ResultSet b = KetNoiSql.tailieubimat();

                tableModelthongkesach.setColumnCount(0);
                tableModelthongkesach.addColumn("Mã Tài liệu");
                tableModelthongkesach.addColumn("Tên Tài Liệu");
                tableModelthongkesach.addColumn("Danh Mục");
                tableModelthongkesach.addColumn("Tác Giả");
                tableModelthongkesach.addColumn("Nhà Xuất Bản");
                tableModelthongkesach.addColumn("Năm Xuất Bản");
                tableModelthongkesach.addColumn("Số Lượng Mất");
                tableModelthongkesach.setRowCount(0);

                if (b != null) {
                    while (b.next()) {
                        tableModelthongkesach.addRow(new Object[]{b.getString(1), (b.getString(2)),
                            KetNoiSql.laytendanhmucduavaoma(b.getString(3)), (b.getString(4)), KetNoiSql.laytennxbduavaoma(b.getString(5)), b.getInt(6), b.getInt(7)});
                    }
                }
                if (tableModelthongkesach.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(rootPane, "Không có "
                            + " tài liệu nào bị mất!");
                }
            } catch (SQLException ex) {
            }
        }
    }

    private void xuatbaocaosach() {
        try {
            String[] columns = {"Mã Tài Liệu", "Tên Tài Liệu", "Số Lượt Mượn"};
            String[] columns1 = {"Mã Tài liệu", "Tên Tài Liệu", "Danh Mục", "Tác Giả", "Nhà Xuất Bản",
                "Năm Xuất Bản", "Số Lượng Hỏng"};
            String[] columns2 = {"Mã Tài liệu", "Tên Tài Liệu", "Danh Mục", "Tác Giả", "Nhà Xuất Bản",
                "Năm Xuất Bản", "Số Lượng Mất"};
            var workbook = new XSSFWorkbook();

            //set font
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short) 14);
            headerFont.setColor(IndexedColors.RED.getIndex());
            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);
            // tạo hàng
            var chon = cbbthongkesach.getSelectedItem().toString();
            if (chon.compareToIgnoreCase("Top 5 tài liệu được mượn nhiều nhất") == 0) {
                var sheet = workbook.createSheet("Top 5 tài liệu được mượn nhiều nhất");

                ResultSet muonnhieunhat = KetNoiSql.sachmuonnhieunhat();
                Row headerRow = sheet.createRow(0);
                for (int i = 0; i < columns.length; i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(columns[i]);
                    cell.setCellStyle(headerCellStyle);
                }
                int rowNum = 1;
                while (muonnhieunhat.next()) {
                    Row row = sheet.createRow(rowNum++);
                    row.createCell(0).setCellValue(String.valueOf(muonnhieunhat.getString(1)));
                    row.createCell(1).setCellValue(muonnhieunhat.getString(2));
                    row.createCell(2).setCellValue(String.valueOf(muonnhieunhat.getInt(3)));

                }
                FileOutputStream fileOut = new FileOutputStream("D:\\sachmuonnhieu.xlsx");
                workbook.write(fileOut);
                fileOut.close();
                workbook.close();

            } else if (chon.compareToIgnoreCase("Tài liệu hư hỏng") == 0) {
                var sheet1 = workbook.createSheet("Tài liệu hư hỏng");
                ResultSet m = KetNoiSql.tailieuhuhong();
                Row headerRow = sheet1.createRow(0);
                for (int i = 0; i < columns1.length; i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(columns1[i]);
                    cell.setCellStyle(headerCellStyle);
                }
                int rowNum = 1;
                while (m.next()) {

                    Row row = sheet1.createRow(rowNum++);
                    row.createCell(0).setCellValue(String.valueOf(m.getString(1)));
                    row.createCell(1).setCellValue(m.getString(2));
                    row.createCell(2).setCellValue(String.valueOf(m.getString(3)));
                    row.createCell(3).setCellValue(String.valueOf(m.getString(4)));
                    row.createCell(4).setCellValue(String.valueOf(m.getString(5)));
                    row.createCell(5).setCellValue(String.valueOf(m.getInt(6)));
                    row.createCell(6).setCellValue(String.valueOf(m.getInt(7)));
                }
                FileOutputStream fileOut = new FileOutputStream("D:\\tailieuhuhong.xlsx");
                workbook.write(fileOut);
                fileOut.close();
                workbook.close();

            } else if (chon.compareToIgnoreCase("Tài liệu bị mất") == 0) {
                var sheet2 = workbook.createSheet("Tài liệu bị mất");
                ResultSet m = KetNoiSql.tailieubimat();
                Row headerRow = sheet2.createRow(0);
                for (int i = 0; i < columns2.length; i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(columns2[i]);
                    cell.setCellStyle(headerCellStyle);
                }
                int rowNum = 1;
                while (m.next()) {
                    Row row = sheet2.createRow(rowNum++);
                    row.createCell(0).setCellValue(String.valueOf(m.getString(1)));
                    row.createCell(1).setCellValue(m.getString(2));
                    row.createCell(2).setCellValue(String.valueOf(m.getString(3)));
                    row.createCell(3).setCellValue(String.valueOf(m.getString(4)));
                    row.createCell(4).setCellValue(String.valueOf(m.getString(5)));
                    row.createCell(5).setCellValue(String.valueOf(m.getInt(6)));
                    row.createCell(6).setCellValue(String.valueOf(m.getInt(7)));

                }
                FileOutputStream fileOut = new FileOutputStream("D:\\tailieubimat.xlsx");
                workbook.write(fileOut);
                fileOut.close();
                workbook.close();

            }

            JOptionPane.showMessageDialog(rootPane, "Tạo file"
                    + " báo cáo thành công!");

        } catch (IOException ex) {
        } catch (SQLException ex) {
        }

    }

    private void timphieumuon() {
        phieuMuons.clear();
        phieuMuons.addAll(KetNoiSql.readPhieuMuonFromSql());
        // tìm phiếu theo mã độc giả
        if (rbtimphieutheomadocgia.isSelected()) {

            if (txttimkiemphieumuon.getText().isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập"
                        + " mã độc giả vào ô tìm kiếm");

            } else {
                var s1 = dataControllerImp.timphieutheomadocgia(phieuMuons,
                        txttimkiemphieumuon.getText());
                if (s1.size() > 0) {
                    JOptionPane.showMessageDialog(rootPane,
                            "Tìm thấy " + s1.size() + " kết quả");
                    tableModeldanhsachphieumuon.setRowCount(0);
                    for (PhieuMuon s : s1) {
                        showPhieuMuon(s);
                    }
                } else {
                    phieuMuons.clear();
                    showPhieuMuons();
                    JOptionPane.showMessageDialog(rootPane,
                            "Không tìm thấy kết quả nào");
                }

            }
        } //Tìm phiếu theo mã phiếu
        else if (rbtimphieutheomaphieu.isSelected()) {
            if (txttimkiemphieumuon.getText().isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập"
                        + " mã phiếu vào ô tìm kiếm");

            } else {
                var s1 = dataControllerImp.timphieutheomaphieu(phieuMuons,
                        txttimkiemphieumuon.getText());
                if (s1.size() > 0) {
                    JOptionPane.showMessageDialog(rootPane,
                            "Tìm thấy " + s1.size() + " kết quả");
                    tableModeldanhsachphieumuon.setRowCount(0);
                    for (PhieuMuon s : s1) {
                        showPhieuMuon(s);
                    }
                } else {
                    phieuMuons.clear();
                    showPhieuMuons();
                    JOptionPane.showMessageDialog(rootPane,
                            "Không tìm thấy kết quả nào");
                }

            }

        } // tìm phiếu theo tên độc giả
        else if (rbtimphieutheotendocgia.isSelected()) {
            if (txttimkiemphieumuon.getText().isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập"
                        + " tên độc giả vào ô tìm kiếm");

            } else {
                var s1 = dataControllerImp.timphieutheotendocgia(phieuMuons,
                        txttimkiemphieumuon.getText());
                if (s1.size() > 0) {
                    JOptionPane.showMessageDialog(rootPane,
                            "Tìm thấy " + s1.size() + " kết quả");
                    tableModeldanhsachphieumuon.setRowCount(0);
                    for (PhieuMuon s : s1) {
                        showPhieuMuon(s);
                    }
                } else {
                    phieuMuons.clear();
                    showPhieuMuons();
                    JOptionPane.showMessageDialog(rootPane,
                            "Không tìm thấy kết quả nào");
                }

            }

        } else {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn"
                    + " cách thức tìm kiếm");
        }
    }

    private void xemctpm() {
        var index = tbldanhsachphieumuon.getSelectedRow();
        if (index > -1) {
            PhieuMuon pm = phieuMuons.get(index);
            Chitietphieumuon sdg = new Chitietphieumuon(this, true, pm);
            sdg.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn"
                    + "  một phiếu mượn từ danh sách");
        }
    }

    private void xuatbaocaotienphat() {
        try {
            String[] columns = {"Tháng", "Tổng Tiền Phạt(VNđ)"};
            var workbook = new XSSFWorkbook();

            //set font
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short) 14);
            headerFont.setColor(IndexedColors.RED.getIndex());
            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);
            // tạo hàng
            var chon = txtthongketienphattheonam.getText().toString();
            var sheet = workbook.createSheet("Thống Kê Tiền Phạt Năm " + chon);

            ResultSet tienphat = KetNoiSql.thongketienphat(chon);
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
                cell.setCellStyle(headerCellStyle);
            }
            int rowNum = 1;

            if (tienphat != null) {
                while (tienphat.next()) {
                    Row row = sheet.createRow(rowNum++);
                    row.createCell(0).setCellValue(String.valueOf(tienphat.getString(1)));
                    row.createCell(1).setCellValue(tienphat.getInt(2));

                }
            }
            FileOutputStream fileOut = new FileOutputStream("D:\\thongketienphattheonam.xlsx");
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
            JOptionPane.showMessageDialog(rootPane, "Xuất báo cáo thành công");
        } catch (Exception e) {
        }

    }

    private void thongketienphat(String chon) {

        try {
            ResultSet tienphat = KetNoiSql.thongketienphat(chon);
            tableModelthongketienphat.setColumnCount(0);
            tableModelthongketienphat.addColumn("Tháng");
            tableModelthongketienphat.addColumn("Tổng tiền phạt (đơn vị VNđ)");
            tableModelthongketienphat.setRowCount(0);

            if (tienphat != null) {
                while (tienphat.next()) {
                    tableModelthongketienphat.addRow(new Object[]{String.valueOf(tienphat.getString(1)), tienphat.getInt(2)});
                }
            }
//             if (tableModelthongketienphat.getRowCount() == 0) {
//                JOptionPane.showMessageDialog(rootPane, "Không có độc giả nào vi phạm"
//                        + "  trong năm " + chon);
//            }
        } catch (SQLException ex) {
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        var chon = (txtthongketienphattheonam.getText());
        var madocgia = txtmadocgia.getText();
        if (chon.isBlank()) {
        } else {
            thongketienphat(chon);
        }
        if (madocgia.isBlank()) {
        } else {
            var a = KetNoiSql.laytendocgiaduavaoma(madocgia);
            txttendocgia.setText(a);
        }
    }

    private void khoadocgia() {
//         var bandocsss = KetNoiSql.readBandocsFromSql();
//            tableModeldocgia.setRowCount(0);
//            for (DocGia ss : bandocsss) {
//                showBanDoc(ss);            
//            }
        var index = tbldocgia.getSelectedRow();
        if (index > -1) {
            var docgia = banDocs.get(index);
            if (docgia.getStatus().trim().equals("Khoá")) {
                JOptionPane.showMessageDialog(rootPane, "Tài khoản này đã bị khoá\n Cần chọn một tài khoản khác!"
                );
            } else if (docgia.getStatus().trim().equals("Mở")) {
                KetNoiSql.capnhatstatusdocgia(docgia.getMaDocGia(), "Khoá");
                banDocs.clear();
                banDocs.addAll(KetNoiSql.readBandocsFromSql());
                showBanDocs();
                JOptionPane.showMessageDialog(rootPane, "Khoá tài khoản thành công!"
                );

            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Cần chọn"
                    + "  một độc giả muốn khoá tài khoản!");
        }
    }

    private void modocgia() {
//         var bandocsss = KetNoiSql.readBandocsFromSql();
//            tableModeldocgia.setRowCount(0);
//            for (DocGia ss : bandocsss) {
//                showBanDoc(ss);            
//            }
        var index = tbldocgia.getSelectedRow();
        if (index > -1) {
            //var bandocsss = KetNoiSql.readBandocsFromSql();
            var docgia = banDocs.get(index);
            if (docgia.getStatus().trim().equals("Mở")) {
                JOptionPane.showMessageDialog(rootPane, "Tài khoản này chưa bị khoá\n Cần chọn một tài khoản đang bị khoá!"
                );
            } else if (docgia.getStatus().trim().equals("Khoá")) {
                KetNoiSql.capnhatstatusdocgia(docgia.getMaDocGia(), "Mở");
                banDocs.clear();
                banDocs.addAll(KetNoiSql.readBandocsFromSql());
                showBanDocs();
                JOptionPane.showMessageDialog(rootPane, "Mở tài khoản thành công!"
                );

            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn"
                    + "  một độc giả để mở lại tài khoản!");
        }

    }

    private void chitiet() {
        var index = tblsach.getSelectedRow();
        var a = tblsach.getValueAt(index, 0).toString().trim();
        
        if (index > -1) {
            //var s1 = sachs.get(index);
            var s1 = KetNoiSql.layMotSachFromSql(a);
            XemChiTietSach xemChiTietSach = new XemChiTietSach(this, true, s1);
            xemChiTietSach.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn"
                    + "  một tài liệu cần xem!");
        }
    }

    private void themnhaxuatban() {
        ThemNhaXuatBan themmoi = new ThemNhaXuatBan(nhaXuatBans, this, true);
        themmoi.setVisible(true);
    }

    private void xoanhaxuatban() {
        var index = tblnhaxuatban.getSelectedRow();
        if (index > -1) {
            int result = JOptionPane.showConfirmDialog(rootPane,
                    "Bạn có chắc chắn muón xoá nhà xuất bản này không?",
                    "Xác Nhận", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                tableModelnxb.removeRow(index);
                tableModelnxb.fireTableDataChanged();
                KetNoiSql.xoanxbSql(nhaXuatBans.get(index));
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn"
                    + " nhà xuất bản cần xoá bỏ");
        }
    }

    private void suanhaxuatban() {
        var index = tblnhaxuatban.getSelectedRow();
        if (index > -1) {
            NhaXuatBan s = nhaXuatBans.get(index);
            SuaNhaXuatBan sua = new SuaNhaXuatBan(s, this, true);
            sua.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn"
                    + "  thông tin một nhà xuất bản cần chỉnh sửa!");
        }
    }

    private void lamMoinhaxuatban() {
        tableModelnxb.setRowCount(0);
        nhaXuatBans.clear();
        nhaXuatBans.addAll(KetNoiSql.readnxbSql());
        shownhaxuatbans();
    }

    void themNXBCallBack(NhaXuatBan nhaXuatBan) {
        nhaXuatBans.add(nhaXuatBan);
        shownhaxuatbans();
        KetNoiSql.themnxb(nhaXuatBan);
    }

    void suathongtinnxb(NhaXuatBan nhaXuatBan) {
        var index = tblnhaxuatban.getSelectedRow();
        var row = new Object[]{String.valueOf(nhaXuatBan.getMa()), nhaXuatBan.getName()
        };
        tableModelnxb.removeRow(index);
        tableModelnxb.insertRow(index, row);
        KetNoiSql.capNhatnxb(nhaXuatBan);
    }

//    public class LabelEvent implements MouseListener {
//
//        private JPanel node;
//
//        public LabelEvent(JPanel node) {
//            this.node = node;
//        }
//
//        @Override
//        public void mouseClicked(MouseEvent e) {
//            thaydoinen(node);
//        }
//
//        @Override
//        public void mousePressed(MouseEvent e) {
//            node.setBackground(Color.red);
//            node.setBackground(Color.red);
//        }
//
//        @Override
//        public void mouseReleased(MouseEvent e) {
//        }
//
//        @Override
//        public void mouseEntered(MouseEvent e) {
//            node.setBackground(Color.red);
//            node.setBackground(Color.red);
//        }
//
//        @Override
//        public void mouseExited(MouseEvent e) {
//            if (!node.equalsIgnoreCase(kind)) {
//                Jpanel.setBackground(Color.YELLOW);
//                Jlabel.setBackground(Color.YELLOW);
//            }
//        }
//
//    }
//
//    public void thaydoinen(JPanel kind) {
//        for (ThongKeTienPhat item : listItem) {
//            if (item.getJpn().equals(kind)) {
//                item.getJpn().setBackground(Color.red);
//            } else {
//                item.getJpn().setBackground(Color.yellow);
//            }
//
//        }
//    }
    private void capnhat3() {
        lbsoluongdocgia1.setText("");
        lbsoluongtailieu.setText("");
        lbsoluongdanhmuc.setText("");
        lbsoluongnxb.setText("");
        lbsoluongphieu.setText("");
        //
        lbsoluongdocgia1.setText(String.valueOf(KetNoiSql.soluongdocgia()));
        lbsoluongtailieu.setText(String.valueOf(KetNoiSql.soluongtailieu()));
        lbsoluongdanhmuc.setText(String.valueOf(KetNoiSql.soluongdanhmuc()));
        lbsoluongnxb.setText(String.valueOf(KetNoiSql.soluongnxb()));
        lbsoluongphieu.setText(String.valueOf(KetNoiSql.soluongphieumuon()));
    }
//Tạo biểu đồ

    private void xembieudotailieu() {
        if (txtnamthongketailieu.getText().isBlank()) {
            JOptionPane.showMessageDialog(rootPane, "Bạn phải nhập vào năm cần thống kê");
        } else {
            var a = Integer.parseInt(txtnamthongketailieu.getText().trim());
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            dataset.addValue(KetNoiSql.sotailieuhuhong10(a), "Số lượng", "Hư hỏng 10% ");
            dataset.addValue(KetNoiSql.sotailieuhuhong30(a), "Số lượng", "Hư hỏng 30%");
            dataset.addValue(KetNoiSql.sotailieuhuhong50(a), "Số lượng", "Hư hỏng 50%");
            dataset.addValue(KetNoiSql.sotailieuhuhong70(a), "Số lượng", "Hư hỏng 70%");
            dataset.addValue(KetNoiSql.sotailieumat(a), "Số lượng", "Mất sách");

            JFreeChart barChart = ChartFactory.createBarChart(
                    "BIỂU ĐỒ THỐNG KÊ TÀI LIỆU THEO NĂM " + a,
                    "Tình Trạng Tài Liệu", "Số Lượng",
                    dataset, PlotOrientation.VERTICAL, false, true, false);
            CategoryPlot categoryPlot = barChart.getCategoryPlot();
            categoryPlot.setBackgroundPaint(Color.WHITE);
            ChartPanel chartPanel = new ChartPanel(barChart);
            ChartFrame f = new ChartFrame("BIỂU ĐỒ THỐNG KÊ TÀI LIỆU", barChart);
//            URL url = f.getClass().getResource("dmtl.png");
//            Image img = Toolkit.getDefaultToolkit().createImage(url);
//            f.setIconImage(img);
            f.setVisible(true);
            f.setSize(900, 600);
            f.setLocationRelativeTo(null);

        }
    }

    private void xembieudodocgia() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(KetNoiSql.docgiachuatra(), "Số lượng", "Chưa trả tài liệu");
        dataset.addValue(KetNoiSql.docgiadanggiahan(), "Số lượng", "Đang gia hạn");
        dataset.addValue(KetNoiSql.soluongdocgia(), "Số lượng", "Tổng số độc giả");

        JFreeChart barChart = ChartFactory.createBarChart(
                "BIỂU ĐỒ THỐNG KÊ ĐỘC GIẢ",
                "Độc Giả", "Số Lượng",
                dataset, PlotOrientation.VERTICAL, false, true, false);
        CategoryPlot categoryPlot = barChart.getCategoryPlot();
        categoryPlot.setBackgroundPaint(Color.WHITE);
        ChartPanel chartPanel = new ChartPanel(barChart);
        ChartFrame f = new ChartFrame("BIỂU ĐỒ THỐNG KÊ ĐỘC GIẢ", barChart);
//        URL url = f.getClass().getResource("dmtl.png");
//        Image img = Toolkit.getDefaultToolkit().createImage(url);
//        f.setIconImage(img);
        f.setVisible(true);
        f.setSize(900, 600);
        f.setLocationRelativeTo(null);
    }

    private void xembieudotienphat() {
        List<ThongKeTienPhat> tienPhats = new ArrayList<>();
        tienPhats = KetNoiSql.thongketienphattheonam();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < tienPhats.size(); i++) {
            dataset.addValue(tienPhats.get(i).getTienphat(), "Số tiền", String.valueOf(tienPhats.get(i).getNam()));
        }
        JFreeChart barChart = ChartFactory.createBarChart(
                "BIỂU ĐỒ THỐNG KÊ TIỀN PHẠT QUA CÁC NĂM",
                "Năm", "Số tiền(vnđ)",
                dataset, PlotOrientation.VERTICAL, false, true, false);
        CategoryPlot categoryPlot = barChart.getCategoryPlot();
        categoryPlot.setBackgroundPaint(Color.WHITE);
        ChartPanel chartPanel = new ChartPanel(barChart);
        ChartFrame f = new ChartFrame("BIỂU ĐỒ THỐNG KÊ TIỀN PHẠT", barChart);
//        URL url = f.getClass().getResource("dmtl.png");
//        Image img = Toolkit.getDefaultToolkit().createImage(url);
//        f.setIconImage(img);
        f.setVisible(true);
        f.setSize(900, 600);
        f.setLocationRelativeTo(null);
    }

}
