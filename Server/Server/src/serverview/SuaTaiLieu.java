/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package serverview;

import Exception.InvalidGiaException;
import Exception.InvalidNamSanXuatException;
import Exception.InvalidSoLuongException;
import Model.TaiLieu;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import ketnoisql1.KetNoiSql;


/**
 *
 * @author Admin
 */
public class SuaTaiLieu extends javax.swing.JDialog  implements ActionListener{
private TrangChuThuThu trangChuThuThu;
private TaiLieu sach;

    /**
     * Creates new form SuaSach
     */
    public SuaTaiLieu(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        showcombotl();
        showcombonxb();
        addActionListener();
        trangChuThuThu = (TrangChuThuThu) parent;
    URL url = this.getClass().getResource("dmtl.png");
        Image img = Toolkit.getDefaultToolkit().createImage(url);
        this.setIconImage(img);
    }

    SuaTaiLieu(java.awt.Frame parent, boolean modal, TaiLieu s) {
        this(parent, modal);
        
        this.sach = s;
        
        showData();
    }
        
   

   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txttensach = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        combodanhmuc = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txttacgia = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtnamxb = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtsoluongcon = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtnoidung = new javax.swing.JTextArea();
        btncapnhat = new javax.swing.JButton();
        btnxoa = new javax.swing.JButton();
        btnhuybo = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtgiasach = new javax.swing.JTextField();
        combonxb = new javax.swing.JComboBox<>();
        txtmasach = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CHỈNH SỬA THÔNG TIN TÀI LIỆU");

        jLabel1.setBackground(new java.awt.Color(255, 255, 51));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("THÔNG TIN TÀI LIỆU");
        jLabel1.setOpaque(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 51));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Mã tài liệu");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Tên tài liệu");

        txttensach.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Tên danh mục");

        combodanhmuc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Tác giả");

        txttacgia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Nhà xuất bản");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("Năm xuất bản");

        txtnamxb.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Số lượng còn");

        txtsoluongcon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Tóm tắt nội dung");

        txtnoidung.setColumns(20);
        txtnoidung.setLineWrap(true);
        txtnoidung.setRows(5);
        txtnoidung.setWrapStyleWord(true);
        jScrollPane1.setViewportView(txtnoidung);

        btncapnhat.setBackground(new java.awt.Color(0, 102, 102));
        btncapnhat.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btncapnhat.setForeground(new java.awt.Color(255, 255, 255));
        btncapnhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/capnhat.png"))); // NOI18N
        btncapnhat.setText("Cập nhật");

        btnxoa.setBackground(new java.awt.Color(0, 102, 102));
        btnxoa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnxoa.setForeground(new java.awt.Color(255, 255, 255));
        btnxoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/xoa.png"))); // NOI18N
        btnxoa.setText("Xoá dữ liệu");

        btnhuybo.setBackground(new java.awt.Color(0, 102, 102));
        btnhuybo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnhuybo.setForeground(new java.awt.Color(255, 255, 255));
        btnhuybo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/huybo.png"))); // NOI18N
        btnhuybo.setText("Huỷ bỏ");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Giá (vnđ)");

        txtgiasach.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        combonxb.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtmasach.setEditable(false);
        txtmasach.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(btncapnhat))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(btnxoa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                        .addComponent(btnhuybo, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtsoluongcon)
                            .addComponent(txtnamxb)
                            .addComponent(combodanhmuc, 0, 309, Short.MAX_VALUE)
                            .addComponent(txttensach)
                            .addComponent(txttacgia)
                            .addComponent(combonxb, 0, 309, Short.MAX_VALUE)
                            .addComponent(txtgiasach)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                            .addComponent(txtmasach))
                        .addGap(55, 55, 55))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtmasach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txttensach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(combodanhmuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(txttacgia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(combonxb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtnamxb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtsoluongcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtgiasach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btncapnhat)
                    .addComponent(btnxoa)
                    .addComponent(btnhuybo))
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            java.util.logging.Logger.getLogger(SuaTaiLieu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SuaTaiLieu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SuaTaiLieu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SuaTaiLieu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SuaTaiLieu dialog = new SuaTaiLieu(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncapnhat;
    private javax.swing.JButton btnhuybo;
    private javax.swing.JButton btnxoa;
    private javax.swing.JComboBox<String> combodanhmuc;
    private javax.swing.JComboBox<String> combonxb;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtgiasach;
    private javax.swing.JTextField txtmasach;
    private javax.swing.JTextField txtnamxb;
    private javax.swing.JTextArea txtnoidung;
    private javax.swing.JTextField txtsoluongcon;
    private javax.swing.JTextField txttacgia;
    private javax.swing.JTextField txttensach;
    // End of variables declaration//GEN-END:variables

    private void addActionListener() {
        btncapnhat.addActionListener(this);
        btnhuybo.addActionListener(this);
        btnxoa.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
         var obj = e.getSource();
        if (obj.equals(btncapnhat)) {
            capNhatSach();
        } else if (obj.equals(btnhuybo)) {
            this.dispose();
        } else if (obj.equals(btnxoa)) {
            clearInputData();
        }
    }

    private void capNhatSach() {
        var tensach = txttensach.getText();
        var ma = txtmasach.getText();
        var nd = txtnoidung.getText();
       
        var tl = combodanhmuc.getSelectedItem().toString();
        var tacgia = txttacgia.getText();
        var nxb = combonxb.getSelectedItem().toString();
        var namxb = txtnamxb.getText();
        var soluong = txtsoluongcon.getText();
        var gia = txtgiasach.getText();
         if (tensach.isEmpty() || ma.isEmpty() || nd.isEmpty() || tacgia.isEmpty()
                || nxb.isEmpty() || namxb.isEmpty() || soluong.isEmpty()|| gia.isEmpty() ) {
            JOptionPane.showMessageDialog(rootPane, "Các ô "
                    + "nhập liệu không được để trống");
        }else{
            try {
                sach.setTentl(tensach);
                var a = KetNoiSql.laymadanhmucduavaoten(tl);
                sach.setMadanhmuc(a);
                sach.setNamXuatBan((namxb));
                sach.setSoLuongCon((soluong));
                sach.setTomTatND(nd);
                sach.setTacGia(tacgia);
                sach.setGiasach((gia));
                var b = KetNoiSql.laymanxbduavaoten(nxb);
                sach.setManxb(b);
               
                
                
                trangChuThuThu.suathongtinsach(sach);
                JOptionPane.showMessageDialog(rootPane,
                        "Cập nhật thông tin tài liệu thành công");
                dispose();
            } catch (InvalidNamSanXuatException ex) {
                JOptionPane.showMessageDialog(rootPane,
                        ex.getMessage());
            } catch (InvalidSoLuongException ex) {
                 JOptionPane.showMessageDialog(rootPane,
                        ex.getMessage());
            } catch (InvalidGiaException ex) {
                 JOptionPane.showMessageDialog(rootPane,
                        ex.getMessage());
            }
             
         }
    }

    private void clearInputData() {
     var str = "";
        //txtmasach.setText(str);
        txtnamxb.setText(str);
        combonxb.setSelectedIndex(-1); 
        txtnoidung.setText(str);
        txtsoluongcon.setText(str);
        txttacgia.setText(str);
        txttensach.setText(str);
        txtgiasach.setText(str);
        combodanhmuc.setSelectedIndex(-1);   
    
        
    }

    private void showData() {
        var a = KetNoiSql.laytendanhmucduavaoma(sach.getMadanhmuc());
        var b = KetNoiSql.laytennxbduavaoma(sach.getManxb());
        txtmasach.setText(sach.getMatl());
        txtnamxb.setText(String.valueOf(sach.getNamXuatBan()));
        txtnoidung.setText(sach.getTomTatND());
        txtsoluongcon.setText(String.valueOf(sach.getSoLuongCon()));
        txttacgia.setText(sach.getTacGia());
        txttensach.setText(sach.getTentl());
        txtgiasach.setText(String.valueOf(sach.getGiasach()));      
        for (int i = 0; i < combodanhmuc.getMaximumRowCount(); i++) {
            if (combodanhmuc.getItemAt(i).
                    equalsIgnoreCase(a)) {
                combodanhmuc.setSelectedIndex(i);
                break;
            }
        }
         for (int i = 0; i < combonxb.getMaximumRowCount(); i++) {
            if (combonxb.getItemAt(i).
                    compareTo(b) == 0) {
                combonxb.setSelectedIndex(i);
                break;
            }
        }
    }

    private void showcombotl() {
      
        var theloais = KetNoiSql.layTenTheLoaiSachSql();
        for (String str : theloais) {
            combodanhmuc.addItem(str);
        }
    }
    private void showcombonxb() {
      
        var nxb = KetNoiSql.layTennxbSql();
        for (String str : nxb) {
            combonxb.addItem(str);
        }
    }
    }
    

