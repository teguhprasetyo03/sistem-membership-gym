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
public class Form_Absensi extends javax.swing.JFrame {
    private DefaultTableModel DftTblModel_member;
    private String SQL;
    ResultSet rs = null;

    /**
     * Creates new form Form_Karyawan Gym
     */
    public Form_Absensi() {
        initComponents();
         this.cariData(SQL);
        this.TampilData();
        this.kosong();
        this.load_data();
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
        DftTblModel_member.addColumn("ID Presensi");
        DftTblModel_member.addColumn("Nama Member");
        DftTblModel_member.addColumn("Jenis Kelamin");
        DftTblModel_member.addColumn("Hari Masuk");
        DftTblModel_member.addColumn("Jam Masuk");
        DftTblModel_member.addColumn("Tanggal Masuk");
        table_pembayaran.setModel(DftTblModel_member);
        Connection conn = Koneksi.getCon();
        try {
            java.sql.Statement stmt = conn.createStatement();
            SQL = "select * from tb_absensi";
            java.sql.ResultSet res = stmt.executeQuery(SQL);
            while (res.next()) {
                DftTblModel_member.addRow(new Object[]{
                    res.getString("id_presensi"),
                    res.getString("nama_member"),
                    res.getString("jenis_kelamin"),
                    res.getString("hari_masuk"),
                    res.getString("jam_masuk"),
                    res.getString("tgl_masuk")
                });
            }
             id_auto();
             load_data();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void load_data(){
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
            String sql = "select max(right(id_presensi, 4)) as no from tb_absensi";
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                if(rs.first()== false){
                    txt_id_presensi.setText("PRE0001");
                } else{
                    rs.last();
                    int set_id = rs.getInt(1)+1;
                    String no = String.valueOf(set_id);
                    int id_next = no.length();
                    for ( int a = 0; a < 4 - id_next; a++){
                        no = "0" + no;
                    }
                    txt_id_presensi.setText("PRE" + no.toString());
                }
            }
        } catch(SQLException e){
            Logger.getLogger(Form_Absensi.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
      private void cariData(String key){
        try{
            Object[] judul_kolom = {"ID Presensi", "Nama Member", 
                "Jenis Kelamin", "Hari Masuk", 
                "Jam Masuk", "Tanggal Masuk"};
            DftTblModel_member=new DefaultTableModel(null,judul_kolom);
            table_pembayaran.setModel(DftTblModel_member);
            
            Connection conn=(Connection)Koneksi.getCon();
            Statement stt=conn.createStatement();
            DftTblModel_member.getDataVector().removeAllElements();
            
            rs =stt.executeQuery("SELECT * from tb_absensi WHERE id_presensi LIKE '%"+key+"%'"
                    + " OR nama_member LIKE '%"+key+"%'"
                    + " OR jenis_kelamin LIKE '%"+key+"%' "
                    + "OR hari_masuk LIKE '%"+key+"%'"
                    + " OR jam_masuk LIKE '%"+key+"%' "
                    + "OR tgl_masuk LIKE '%"+key+"%'");  
            while(rs.next()){
                Object[] data={
                    rs.getString("id_presensi"),
                    rs.getString("nama_member"),
                    rs.getString("jenis_kelamin"),
                    rs.getString("hari_masuk"),
                    rs.getString("jam_masuk"),
                    rs.getString("tgl_masuk")
                };
               DftTblModel_member.addRow(data);
               
            }  
        } catch (Exception ex) {
        System.err.println(ex.getMessage());
        }
    }
    
   public void kosong(){
//        txt_id_transaksi.setText(null);
        txt_jam_masuk.setText("");
//        txt_biaya_admin.setText("");
//        txt_biaya_bulanan.setText("");
        txt_nama_member.setSelectedItem(this);
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
        txt_id_presensi = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        total = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btn_save = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txt_nama_member = new javax.swing.JComboBox<>();
        txt_jenis_kelamin = new javax.swing.JComboBox<>();
        txt_tanggal_masuk = new com.toedter.calendar.JDateChooser();
        txt_hari_masuk = new javax.swing.JComboBox<>();
        txt_jam_masuk = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_pembayaran = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        txt_cari_karyawan = new javax.swing.JTextField();
        btn_cetak = new javax.swing.JButton();
        btn_clear = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txt_id_presensi.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txt_id_presensi.setEnabled(false);
        txt_id_presensi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_id_presensiActionPerformed(evt);
            }
        });

        jLabel1.setText("ID Presensi :");

        jLabel3.setText("Nama Member :");

        jLabel5.setText("Jenis Kelamin :");

        total.setText("Jam Masuk :");

        jLabel12.setText("Hari Masuk :");

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
                .addContainerGap()
                .addComponent(btn_save, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_update, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        jLabel9.setText("Tanggal Masuk :");

        txt_nama_member.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item1", "Item2" }));

        txt_jenis_kelamin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Laki-Laki", "Perempuan", "Transgender/Waria" }));

        txt_hari_masuk.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Senin", "Selasa", "Rabu", "Kamis", "Jum'at", "Sabtu", "Minggu" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(18, 18, 18)
                                .addComponent(txt_hari_masuk, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5)
                                    .addComponent(total)
                                    .addComponent(jLabel9))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_jam_masuk, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txt_id_presensi)
                                        .addComponent(txt_nama_member, 0, 210, Short.MAX_VALUE))
                                    .addComponent(txt_tanggal_masuk, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_jenis_kelamin, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_id_presensi, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_nama_member, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_jenis_kelamin, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_hari_masuk, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(total)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txt_jam_masuk, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_tanggal_masuk, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel2.setText("--{List Data ABSENSI}--");

        jLabel7.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel7.setText("--{ABSENSI}--");

        table_pembayaran.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID Presensi", "Nama Member", "Jenis Kelamin", "Hari Masuk", "Jam Masuk", "Tanggal Masuk"
            }
        ));
        table_pembayaran.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_pembayaranMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_pembayaran);

        jLabel8.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel8.setText("--CARI DATA ABSENSI--");

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

        btn_cetak.setText("CETAK");
        btn_cetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cetakActionPerformed(evt);
            }
        });

        btn_clear.setText("CLEAR");
        btn_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clearActionPerformed(evt);
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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(txt_cari_karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_cetak, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_clear)))
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
                            .addComponent(btn_cetak)
                            .addComponent(btn_clear)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_id_presensiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_id_presensiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_id_presensiActionPerformed

    private void btn_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveActionPerformed
        // TODO add your handling code here:
        txt_id_presensi.setEditable(false);
        
        String id_absensi = txt_id_presensi.getText();
        String nama_member = (String) txt_nama_member.getSelectedItem();
        String jenis_kelamin = (String) txt_jenis_kelamin.getSelectedItem();
        String hari_masuk = (String) txt_hari_masuk.getSelectedItem();
        String jam_masuk = (String) txt_jam_masuk.getText();
        String tampilan = "yyyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
        String tgl_masuk = String.valueOf(fm.format(txt_tanggal_masuk.getDate())); 

        try {
            Connection conn = Koneksi.getCon();
            PreparedStatement stmt = conn.prepareStatement("insert into tb_absensi values(?,?,?,?,?,?)");
            stmt.setString(1, id_absensi);
            stmt.setString(2, nama_member);
            stmt.setString(3, jenis_kelamin);
            stmt.setString(4, hari_masuk);
            stmt.setString(5, jam_masuk);
            stmt.setString(6, tgl_masuk);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan", "Pesan", JOptionPane.INFORMATION_MESSAGE);
            setVisible(false);
            new Form_Absensi().setVisible(true);
            TampilData();
            id_auto();
            load_data();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_btn_saveActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        // TODO add your handling code here:
          try {
            String tampilan = "yyyy-MM-dd";
            SimpleDateFormat fm = new SimpleDateFormat(tampilan);
            String tgl_masuk = String.valueOf(fm.format(txt_tanggal_masuk.getDate())); 
            Connection conn = Koneksi.getCon();
            PreparedStatement stmt = conn.prepareStatement("update tb_absensi"
                    + " nama_member=?, jenis_kelamin=?,"
                    + " hari_masuk=?, jam_masuk=?, tgl_masuk=?"
                    + " where id_presensi=?");
            stmt.setString(1, txt_id_presensi.getText());
            stmt.setString(2, (String) txt_nama_member.getSelectedItem());
            stmt.setString(3, (String) txt_jenis_kelamin.getSelectedItem());
            stmt.setString(4, (String) txt_hari_masuk.getSelectedItem());
            stmt.setString(5, (String) txt_jam_masuk.getText());
            stmt.setString(6, tgl_masuk);
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
            String sql ="delete from tb_absensi where id_presensi='"+txt_id_presensi.getText()+"'";
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
        txt_id_presensi.setText(ID_Karyawan);
        
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

    private void btn_cetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cetakActionPerformed
        // TODO add your handling code here:
           try {
             HashMap parameter = new HashMap();
             Class.forName("com.mysql.jdbc.Driver");
             Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:8889/gms","root","root");
             File file = new File("src/report/report_data_absensi.jasper");
             JasperReport jr = (JasperReport) JRLoader.loadObject(file);
             JasperPrint jp = JasperFillManager.fillReport(jr, parameter, conn);
             JasperViewer.viewReport(jp, false);
             JasperViewer.setDefaultLookAndFeelDecorated(true);
        } catch (Exception e) {
           javax.swing.JOptionPane.showMessageDialog(null,
                   "Data tidak dapat dicetak !!!"+"\n"+ e.getMessage(), "Cetak Data",
                   javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_cetakActionPerformed

    private void btn_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearActionPerformed
        // TODO add your handling code here:
        kosong();
    }//GEN-LAST:event_btn_clearActionPerformed

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
                new Form_Absensi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cetak;
    private javax.swing.JButton btn_clear;
    private javax.swing.JButton btn_save;
    private javax.swing.JButton btn_update;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table_pembayaran;
    private javax.swing.JLabel total;
    private javax.swing.JTextField txt_cari_karyawan;
    private javax.swing.JComboBox<String> txt_hari_masuk;
    private javax.swing.JTextField txt_id_presensi;
    private javax.swing.JTextField txt_jam_masuk;
    private javax.swing.JComboBox<String> txt_jenis_kelamin;
    private javax.swing.JComboBox<String> txt_nama_member;
    private com.toedter.calendar.JDateChooser txt_tanggal_masuk;
    // End of variables declaration//GEN-END:variables
}
