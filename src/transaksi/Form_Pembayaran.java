package transaksi;


import Form.*;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.Koneksi;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Anditeguh
 */
public class Form_Pembayaran extends javax.swing.JFrame {
    private DefaultTableModel DftTblModel_member;
    private String SQL;
    ResultSet rs = null;

    /**
     * Creates new form Form_Karyawan Gym
     */
    public Form_Pembayaran() {
        initComponents();
         this.cariData(SQL);
        this.TampilData();
        this.kosong();
        this.load_data();
        this.load_nama_member();
    }
    
      public void TampilData(){
        
//        try{
//            java.sql.Statement stmt = conn.createStatement();
//            SQL = "select tb_member.id_member, tb_member.nama_member,"
//                    + "tb_pembayaran.id_transaksi,tb_pembayaran.biaya_admin, "
//                    + "tb_pembayaran.biaya_bulanan,tb_pembayaran.total_biaya,"
//                    + "tb_pembayaran.cash,tb_pembayaran.kembalian from tb_member,"
//                    + "tb_pembayaran where tb_member.id_member=tb_pembayaran.id_member";
//            java.sql.ResultSet res = stmt.executeQuery(SQL);
//            final String [] header = {"ID Transaksi", "ID Member","Nama Member",
//                                       "Biaya Admin", "Biaya Bulanan","Total Biaya",
//                                       "Cash", "Kembalian"};
//            res.last();
//            int n = res.getRow();
//            Object [][] data = new Object[n][7];
//            int a = 0;
//            rs.beforeFirst();
//            while (res.next()) {
//                data[a][0] = res.getString("id_transaksi");
//                data[a][1] = res.getString("id_member");
//                data[a][2] = res.getString("nama_member");
//                data[a][3] = res.getString("biaya_admin");
//                data[a][4] = res.getString("biaya_bulanan");
//                data[a][5] = res.getString("total_biaya");
//                data[a][6] = res.getString("cash");
//                data[a][7] = res.getString("kembali"); 
//                a++;
//            }
//           table_pembayaran.setModel(DftTblModel_member);
//           table_pembayaran.setAlignmentX(CENTER_ALIGNMENT);
//            id_auto();
//            load_data();
//        }
//        catch(SQLException e ){
//             System.out.println(e.getMessage());
//        }
          
        DftTblModel_member = new DefaultTableModel();
        DftTblModel_member.addColumn("ID Transaksi");
        DftTblModel_member.addColumn("ID Member");
        DftTblModel_member.addColumn("Nama Member");
        DftTblModel_member.addColumn("Biaya Admin");
        DftTblModel_member.addColumn("Biaya Bulanan");
        DftTblModel_member.addColumn("Total Biaya");
        DftTblModel_member.addColumn("Cash");
        DftTblModel_member.addColumn("Kembalian");
        table_pembayaran.setModel(DftTblModel_member);
        Connection conn = Koneksi.getCon();
        try {
            java.sql.Statement stmt = conn.createStatement();
            SQL = "select * from tb_pembayaran";
            java.sql.ResultSet res = stmt.executeQuery(SQL);
            while (res.next()) {
                DftTblModel_member.addRow(new Object[]{
                    res.getString("id_transaksi"),
                    res.getString("id_member"),
                    res.getString("nama_member"),
                    res.getString("biaya_admin"),
                    res.getString("biaya_bulanan"),
                    res.getString("total_biaya"),
                    res.getString("cash"),
                    res.getString("kembalian")
                });
            }
             id_auto();
             load_data();
             load_nama_member();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void load_data(){
        try{
            txt_id_member.removeAllItems();
            Connection con = Koneksi.getCon();
            Statement st = con.createStatement();
            String sql = "select * from tb_member";
             ResultSet rs = st.executeQuery(sql);
             while(rs.next()){
                 txt_id_member.addItem(rs.getString("id_member"));
             }
            rs.close();        
        } catch(Exception e){
            System.out.println("terjadi kesalahan");
        }
    }  
    
    public void load_nama_member(){
        try{
            txt_nama_member.removeAllItems();
            Connection con = Koneksi.getCon();
            Statement st = con.createStatement();
            String sql = "select * from tb_member";
             ResultSet rs = st.executeQuery(sql);
             while(rs.next()){
                 txt_nama_member.addItem(rs.getString("nama_member"));
             }
            rs.close();        
        } catch(Exception e){
            System.out.println("terjadi kesalahan");
        }
    }  
      
    public void id_auto(){
        try{
            Connection con = Koneksi.getCon();
            Statement st = con.createStatement();
            String sql = "select max(right(id_transaksi, 4)) as no from tb_pembayaran";
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                if(rs.first()== false){
                    txt_id_transaksi.setText("INV0001");
                } else{
                    rs.last();
                    int set_id = rs.getInt(1)+1;
                    String no = String.valueOf(set_id);
                    int id_next = no.length();
                    for ( int a = 0; a < 4 - id_next; a++){
                        no = "0" + no;
                    }
                    txt_id_transaksi.setText("INV" + no.toString());
                }
            }
        } catch(SQLException e){
            Logger.getLogger(Form_Pembayaran.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
      private void cariData(String key){
        try{
            Object[] judul_kolom = {"ID Transaksi", "ID Member", 
                "Nama Member", "Biaya Admin", 
                "Biaya Bulanan", "Total Biaya", "Cash", "Kembalian"};
            DftTblModel_member=new DefaultTableModel(null,judul_kolom);
            table_pembayaran.setModel(DftTblModel_member);
            
            Connection conn=(Connection)Koneksi.getCon();
            Statement stt=conn.createStatement();
            DftTblModel_member.getDataVector().removeAllElements();
            
            rs =stt.executeQuery("SELECT * from tb_pembayaran WHERE id_transaksi LIKE '%"+key+"%'"
                    + " OR id_member LIKE '%"+key+"%'"
                    + " OR nama_member LIKE '%"+key+"%' "
                    + "OR biaya_admin LIKE '%"+key+"%'"
                    + " OR biaya_bulanan LIKE '%"+key+"%' "
                    + "OR total_biaya LIKE '%"+key+"%' "
                    + "OR cash LIKE '%"+key+"%'"
                    + "OR kembalian LIKE '%"+key+"%'");  
            while(rs.next()){
                Object[] data={
                   rs.getString("id_transaksi"),
                    rs.getString("id_member"),
                    rs.getString("nama_member"),
                    rs.getString("biaya_admin"),
                    rs.getString("biaya_bulanan"),
                    rs.getString("total_biaya"),
                    rs.getString("cash"),
                    rs.getString("kembalian")
                };
               DftTblModel_member.addRow(data);
               
            }  
        } catch (Exception ex) {
        System.err.println(ex.getMessage());
        }
    }
    
   public void kosong(){
//        txt_id_transaksi.setText(null);
//            txt_nama_member.setText("");
//        txt_biaya_admin.setText("");
//        txt_biaya_bulanan.setText("");
        txt_id_member.setSelectedItem(this);
//        txt_cash.setText("");
//        txt_total_biaya.setText("");
//        txt_kembalian.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txt_id_transaksi = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        total = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        kembalian = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btn_save = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txt_id_member = new javax.swing.JComboBox<>();
        btn_hitung_total = new javax.swing.JButton();
        btn_hitung_kembalian = new javax.swing.JButton();
        txt_biaya_admin = new javax.swing.JTextField();
        txt_biaya_bulanan = new javax.swing.JTextField();
        txt_cash = new javax.swing.JTextField();
        txt_total_biaya = new javax.swing.JLabel();
        txt_kembalian = new javax.swing.JLabel();
        txt_nama_member = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_pembayaran = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        txt_cari_karyawan = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txt_id_transaksi.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txt_id_transaksi.setEnabled(false);
        txt_id_transaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_id_transaksiActionPerformed(evt);
            }
        });

        jLabel1.setText("ID Transaksi :");

        jLabel3.setText("ID Member :");

        jLabel4.setText("Nama Member :");

        jLabel5.setText("Biaya Admin  :");

        total.setText("Total Biaya :");

        jLabel12.setText("Biaya Bulanan :");

        kembalian.setText("Kembalian :");

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btn_save.setText("SAVE");
        btn_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveActionPerformed(evt);
            }
        });

        btn_update.setText("EDIT");
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });

        jButton3.setText("EXIT");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("DELETE");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(btn_save, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(btn_update, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(jButton3)
                .addGap(28, 28, 28))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton3)
                    .addComponent(btn_update)
                    .addComponent(btn_save))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jLabel9.setText("Cash :");

        txt_id_member.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item1", "Item2" }));

        btn_hitung_total.setText("HITUNG TOTAL");
        btn_hitung_total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hitung_totalActionPerformed(evt);
            }
        });

        btn_hitung_kembalian.setText(" HITUNG KEMBALIAN");
        btn_hitung_kembalian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hitung_kembalianActionPerformed(evt);
            }
        });

        txt_biaya_admin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_biaya_adminActionPerformed(evt);
            }
        });

        txt_biaya_bulanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_biaya_bulananActionPerformed(evt);
            }
        });

        txt_cash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cashActionPerformed(evt);
            }
        });

        txt_total_biaya.setText("Total Biaya :");
        txt_total_biaya.setEnabled(false);

        txt_kembalian.setText("Kembalian :");
        txt_kembalian.setEnabled(false);

        txt_nama_member.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item1", "Item2" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                        .addComponent(txt_biaya_bulanan, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(total)
                            .addComponent(kembalian)
                            .addComponent(jLabel9))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(txt_total_biaya))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_kembalian)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txt_id_transaksi)
                                        .addComponent(txt_id_member, 0, 210, Short.MAX_VALUE)
                                        .addComponent(txt_biaya_admin)
                                        .addComponent(txt_cash)
                                        .addComponent(txt_nama_member, 0, 210, Short.MAX_VALUE)))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_hitung_total, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_hitung_kembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_id_transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_id_member, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_nama_member, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_biaya_admin, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_biaya_bulanan, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(total)
                    .addComponent(btn_hitung_total, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_total_biaya))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txt_cash, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_hitung_kembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_kembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel2.setText("--{List Data PEMBAYARAN}--");

        jLabel7.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel7.setText("--{PEMBAYARAN}--");

        table_pembayaran.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Transaksi", "ID Member", "Nama Member", "Biaya Admin", "Biaya Bulanan", "Total Biaya", "Cash", "Kembalian"
            }
        ));
        table_pembayaran.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_pembayaranMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_pembayaran);

        jLabel8.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel8.setText("--CARI DATA PEMBAYARAN--");

        txt_cari_karyawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cari_karyawanActionPerformed(evt);
            }
        });
        txt_cari_karyawan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_cari_karyawanKeyReleased(evt);
            }
        });

        jButton5.setText("CETAK");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("CLEAR");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 607, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(txt_cari_karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton6)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txt_cari_karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton5)
                            .addComponent(jButton6)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_id_transaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_id_transaksiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_id_transaksiActionPerformed

    private void btn_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveActionPerformed
        // TODO add your handling code here:
        txt_id_transaksi.setEditable(false);
        
        String id_transaksi = txt_id_transaksi.getText();
        String id_member = (String) txt_id_member.getSelectedItem();
        String nama_member = (String) txt_nama_member.getSelectedItem();
        double biaya_admin = Double.parseDouble(txt_biaya_admin.getText());
        double biaya_bulanan = Double.parseDouble( txt_biaya_bulanan.getText());
        double total_biaya = biaya_admin + biaya_bulanan;
        txt_total_biaya.setText(Double.toString(total_biaya));
        
        DecimalFormat angka = new DecimalFormat("###,###");
        double cash = Double.parseDouble(txt_cash.getText());
        
        double kembalian = Double.parseDouble(txt_kembalian.getText());
        kembalian = cash - total_biaya;
        txt_kembalian.setText(Double.toString(kembalian));
      
    
        
        try {
            Connection conn = Koneksi.getCon();
            PreparedStatement stmt = conn.prepareStatement("insert into tb_pembayaran values(?,?,?,?,?,?,?,?)");
            stmt.setString(1, id_transaksi);
            stmt.setString(2, id_member);
            stmt.setString(3, nama_member);
            stmt.setDouble(4, biaya_admin);
            stmt.setDouble(5, biaya_bulanan);
            stmt.setDouble(6, total_biaya);
            stmt.setDouble(7, cash);
            stmt.setDouble(8, kembalian);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan", "Pesan", JOptionPane.INFORMATION_MESSAGE);
            setVisible(false);
            new Form_Pembayaran().setVisible(true);
            TampilData();
            id_auto();
            load_nama_member();
            load_data();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_btn_saveActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        // TODO add your handling code here:
          try {
            Connection conn = Koneksi.getCon();
            PreparedStatement stmt = conn.prepareStatement("update tb_pembayaran"
                    + " set id_member=?, nama_member=?, biaya_admin=?,"
                    + " biaya_bulanan=?, total_biaya=?, cash=?, kembalian=?"
                    + " where id_transaksi=?");
            stmt.setString(1, txt_id_transaksi.getText());
            stmt.setString(2, (String) txt_id_member.getSelectedItem());
            stmt.setString(3, (String) txt_nama_member.getSelectedItem());
            stmt.setDouble(4, Double.parseDouble((String) txt_biaya_admin.getText()));
            stmt.setDouble(5, Double.parseDouble((String)(txt_biaya_bulanan.getText())));
            stmt.setDouble(6, Double.parseDouble(txt_kembalian.getText()));
            stmt.setDouble(7,  Double.parseDouble((String) txt_cash.getText()));
            stmt.setDouble(8,  Double.parseDouble(txt_kembalian .getText()));
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil diubah", "Pesan", JOptionPane.INFORMATION_MESSAGE);
            TampilData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Perubahan Data Gagal"+e.getMessage());
        }
    }//GEN-LAST:event_btn_updateActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
          try {
            String sql ="delete from tb_pembayaran where id_transaksi='"+txt_id_transaksi.getText()+"'";
            java.sql.Connection conn=(Connection)Koneksi.getCon();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "berhasil di hapus");
            TampilData();
            kosong();
//            this.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txt_cari_karyawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cari_karyawanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cari_karyawanActionPerformed

    private void txt_cari_karyawanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cari_karyawanKeyReleased
        // TODO add your handling code here:
          String key=txt_cari_karyawan.getText();
        System.out.println(key);

        if(key!=""){
            cariData(key);
        }else{
            TampilData();
        }
    }//GEN-LAST:event_txt_cari_karyawanKeyReleased

    private void table_pembayaranMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_pembayaranMouseClicked
        // TODO add your handling code here:
        int baris = table_pembayaran.rowAtPoint(evt.getPoint());
        String ID_Karyawan = table_pembayaran.getValueAt(baris, 0).toString();
        txt_id_transaksi.setText(ID_Karyawan);
        
//        String nama =table_pembayaran.getValueAt(baris, 1).toString();
//        txt_nama_instruktur.setText(nama);
//        
//        String no_telefon =table_pembayaran.getValueAt(baris, 2).toString();
//        txt_nama_member.setText(no_telefon);
//       
//        String jk = table_pembayaran.getValueAt(baris, 3).toString();
//        txt_jenis_kelamin.setSelectedItem(jk);
//        
//        String jabatan = table_pembayaran.getValueAt(baris, 4 ).toString();
//        txt_bidang.setSelectedItem(jabatan);
//        
//        String email =table_pembayaran.getValueAt(baris, 5).toString();
//        txt_total_biaya.setText(email);
        
        
    }//GEN-LAST:event_table_pembayaranMouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
           try {
             HashMap parameter = new HashMap();
             Class.forName("com.mysql.jdbc.Driver");
             Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:8889/gms","root","root");
             File file = new File("src/report/report_data_pembayaran.jasper");
             JasperReport jr = (JasperReport) JRLoader.loadObject(file);
             JasperPrint jp = JasperFillManager.fillReport(jr, parameter, conn);
             JasperViewer.viewReport(jp, false);
             JasperViewer.setDefaultLookAndFeelDecorated(true);
        } catch (Exception e) {
           javax.swing.JOptionPane.showMessageDialog(null,
                   "Data tidak dapat dicetak !!!"+"\n"+ e.getMessage(), "Cetak Data",
                   javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        kosong();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void btn_hitung_totalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hitung_totalActionPerformed
        // TODO add your handling code here:
        double biaya_admin = Double.parseDouble(txt_biaya_admin.getText());
        double biaya_bulanan = Double.parseDouble( txt_biaya_bulanan.getText());
        double total_biaya = biaya_admin + biaya_bulanan;
        txt_total_biaya.setText(Double.toString(total_biaya));
        
        DecimalFormat angka = new DecimalFormat("###,###");
        double cash = Double.parseDouble(txt_cash.getText());
        
        double kembalian = Double.parseDouble(txt_kembalian.getText());
        kembalian = cash - total_biaya;
        txt_kembalian.setText(Double.toString(kembalian));
        
    }//GEN-LAST:event_btn_hitung_totalActionPerformed

    private void btn_hitung_kembalianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hitung_kembalianActionPerformed
        // TODO add your handling code here:
        double biaya_admin = Double.parseDouble(txt_biaya_admin.getText());
        double biaya_bulanan = Double.parseDouble( txt_biaya_bulanan.getText());
        double total_biaya = biaya_admin + biaya_bulanan;
        txt_total_biaya.setText(Double.toString(total_biaya));
        
        DecimalFormat angka = new DecimalFormat("###,###");
        double cash = Double.parseDouble(txt_cash.getText());
        double kembalian = cash - total_biaya;
        txt_kembalian.setText(Double.toString(kembalian));
        
    }//GEN-LAST:event_btn_hitung_kembalianActionPerformed

    private void txt_biaya_adminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_biaya_adminActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_biaya_adminActionPerformed

    private void txt_biaya_bulananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_biaya_bulananActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_biaya_bulananActionPerformed

    private void txt_cashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cashActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cashActionPerformed

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
            java.util.logging.Logger.getLogger(Form_Alat_Gym.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form_Alat_Gym.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form_Alat_Gym.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form_Alat_Gym.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form_Pembayaran().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_hitung_kembalian;
    private javax.swing.JButton btn_hitung_total;
    private javax.swing.JButton btn_save;
    private javax.swing.JButton btn_update;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel kembalian;
    private javax.swing.JTable table_pembayaran;
    private javax.swing.JLabel total;
    private javax.swing.JTextField txt_biaya_admin;
    private javax.swing.JTextField txt_biaya_bulanan;
    private javax.swing.JTextField txt_cari_karyawan;
    private javax.swing.JTextField txt_cash;
    private javax.swing.JComboBox<String> txt_id_member;
    private javax.swing.JTextField txt_id_transaksi;
    private javax.swing.JLabel txt_kembalian;
    private javax.swing.JComboBox<String> txt_nama_member;
    private javax.swing.JLabel txt_total_biaya;
    // End of variables declaration//GEN-END:variables
}
