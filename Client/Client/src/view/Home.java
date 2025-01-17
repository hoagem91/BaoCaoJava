/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;


import Controller.DATACONTROLLER;
import Controller.DataControllerImp;
import Model.TaiLieu;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;



/**
 *
 * @author Admin
 */
//Sách Giáo Trình
//Sách Khoa học công nghệ – Kinh tế;
//Sách Văn hóa xã hội - Lịch sử
//Sách Chính trị- Pháp luật
//Tạp chí
//Sách Thiếu nhi
//Sách Văn học nghệ thuật
//Sách Truyện, tiểu thuyết
//Sách Tâm lý, tâm linh, tôn giáo
public class Home extends javax.swing.JFrame implements ActionListener {

    private DefaultTableModel tableModelSach;
    //private TaiLieu sach;
    private List<TaiLieu> sachs;
    private DATACONTROLLER datacontroller;

    /**
     * Creates new form Home
     */
    public Home() {
        initComponents();
        datacontroller = new DataControllerImp();
//        addButtongroup();
        setLocationRelativeTo(null);
        //setSize(1000,700);
        addActionListener();
       // tableModelSach = (DefaultTableModel) tblsach.getModel();
//        loadData();
        //showData();
URL url = this.getClass().getResource("dmtl.png");
        Image img = Toolkit.getDefaultToolkit().createImage(url);
        this.setIconImage(img);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGrouptimkiem = new javax.swing.ButtonGroup();
        jLabel13 = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        btnlogin = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("QUẢN LÝ THƯ VIỆN");
        setResizable(false);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("PHẦN MỀM QUẢN LÝ THƯ VIỆN");

        jTabbedPane2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setText("** Phần mềm quản lý thư viện  có nhiệm vụ đáp ứng nhu cầu thông tin phục vụ công tác học tập, giảng dạy và nghiên cứu khoa học cho cán bộ, giảng viên, sinh viên  trong và ngoài  trường.\n Hộp tìm kiếm  sẽ là một công cụ tuyệt vời để bạn tìm kiếm tài liệu học tập, giảng dạy và nghiên cứu nếu bạn muốn:\n- Tìm kiếm một nhan đề tài liệu bản in cụ thể, bất kể đó là sách hay một bài báo nghiên cứu sẵn có.\n- Tìm kiếm kết hợp cả sách, bài báo, tài liệu số, cơ sở dữ liệu … theo một chủ đề nào đó hoặc đơn giản là bạn khởi đầu cho một nghiên cứu.\nHệ thống quản lý thư viện hy vọng sẽ làm hài lòng quý bạn đọc!\n");
        jTextArea1.setWrapStyleWord(true);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1079, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("THÔNG TIN GIỚI THIỆU", jPanel1);

        jTextArea2.setColumns(20);
        jTextArea2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextArea2.setLineWrap(true);
        jTextArea2.setRows(5);
        jTextArea2.setText("Nhấn vào nút đăng nhập phía bên dưới màn hình.\nLúc này, một form  đăng nhập sẽ hiển thị ra.\nNếu bạn chưa có tài khoản, hãy nhấn vào nút đăng ký để tạo tài khoản mới nhé!\nNếu bạn đã tạo tài khoản, hãy điền đầy đủ thông tin tên đăng nhập, mật khẩu vào ô tương ứng, sau đó click tiếp nút đăng nhập để truy cập vào hệ thống!");
        jTextArea2.setWrapStyleWord(true);
        jScrollPane2.setViewportView(jTextArea2);

        btnlogin.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnlogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/login1.png"))); // NOI18N
        btnlogin.setText("Đăng nhập ");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1085, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(471, 471, 471)
                .addComponent(btnlogin, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnlogin)
                .addContainerGap())
        );

        jTabbedPane2.addTab("HƯỚNG DẪN ĐĂNG NHẬP", jPanel6);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane2))
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
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnlogin;
    private javax.swing.ButtonGroup buttonGrouptimkiem;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    // End of variables declaration//GEN-END:variables

    private void addActionListener() {
        btnlogin.addActionListener(this);
        //btntimkiem.addActionListener(this);
        //rbtimtheodm.addActionListener(this);
       // rbtimtheoten.addActionListener(this);
        //rbtimtheotl.addActionListener(this);
        //btnlammoi.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var obj = e.getSource();
        if (obj.equals(btnlogin)) {
            this.dispose();
            DangNhap dn = new DangNhap();
            dn.setVisible(true);
        } 
//        else if (obj.equals(btntimkiem)) {
//            timkiem();
//        }else if (obj.equals(btnlammoi)) {
//            lammoi();
//        }
    }

//    private void loadData() {
//        sachs = KetNoiSql.readSachFromSql();
//    }

//    private void showData() {
//        showSachs();
//       // showcombotl();
//    }
//
//    private void showSachs() {
//        tableModelSach.setRowCount(0);
//        for (TaiLieu sach1 : sachs) {
//            showSach(sach1);
//        }
//    }
//
//    private void showSach(TaiLieu s) {
//        var row = new Object[]{s.getTenSach(),s.getTentheloai() ,s.getTacGia(), s.getNXB(), s.getNamXuatBan(),s.getSoLuongCon()};
//        tableModelSach.addRow(row);
//    }
//
//    private void addButtongroup() {
//        //buttonGrouptimkiem.add(rbtimtheodm);
//        buttonGrouptimkiem.add(rbtimtheoten);
//        buttonGrouptimkiem.add(rbtimtheotl);
//    }

//    private void timkiem() {
//        sachs.clear();
//        // students.addAll(dataController.<Student>readDataFromFile(DataController.STUDENT_FILE));
//        sachs.addAll(KetNoiSql.readSachFromSql());
//        if (rbtimtheoten.isSelected()) {
//
//            if (txttimtheoten.getText().isEmpty()) {
//                JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn"
//                        + " tên sách cần tìm kiếm");
//
//            } else {
//                var sachs1 = datacontroller.searchSachByName(sachs, txttimtheoten.getText());
//                if (sachs1.size() > 0) {
//                    JOptionPane.showMessageDialog(rootPane,
//                            "Tìm thấy " + sachs1.size() + " kết quả");
//                    tableModelSach.setRowCount(0);
//                    for (TaiLieu s : sachs1) {
//                        showSach(s);
//                    }
//                } else {
//                    sachs.clear();
//                    showSachs();
//                    JOptionPane.showMessageDialog(rootPane,
//                            "Không tìm thấy kết quả nào");
//                }
//
//            }}
//         else if (rbtimtheodm.isSelected()) {
//            var dm = combodm.getSelectedItem().toString();
//            var sachs2 = datacontroller.searchSachBydm(sachs, dm);
//
//            if (sachs2.size() > 0) {
//                tableModelSach.setRowCount(0);
//                for (TaiLieu s : sachs2) {
//                    showSach(s);
//                }
//                JOptionPane.showMessageDialog(rootPane,
//                        "Tìm thấy " + sachs2.size() + " kết quả");
//            } else {
//                sachs.clear();
//                showSachs();
//                JOptionPane.showMessageDialog(rootPane,
//                        "Không tìm thấy kết quả nào");
//            }

//         else if (rbtimtheotl.isSelected()) {
//            
//            var major1 = combotl.getSelectedItem().toString();
//            var s3 = datacontroller.searchSachBytl(sachs, major1);
//            if (s3.size() > 0) {
//                sachs.clear();
//                sachs.addAll(s3);
//                showSachs();
//                JOptionPane.showMessageDialog(rootPane,
//                        "Tìm thấy " + s3.size() + " kết quả");
//            } else {
//                sachs.clear();
//                showSachs();
//                JOptionPane.showMessageDialog(rootPane,
//                        "Không tìm thấy kết quả nào");
//            }
//        } else {
//            JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn"
//                    + " cách thức tìm kiếm");
//        }
//    }

//    private void lammoi() {
//       buttonGrouptimkiem.clearSelection();
//       txttimtheoten.setText("");
//        sachs.clear();
//        tableModelSach.setRowCount(0);
//        sachs.addAll(KetNoiSql.readSachFromSql());
//        showSachs();
//    }

//    private void showcombotl() {
//        var theloais = KetNoiSql.layTenTheLoaiSachSql();
//        for (String str : theloais) {
//            combotl.addItem(str);
//        }
//    }
}
