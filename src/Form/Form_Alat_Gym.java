package Form;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.text.DateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.Koneksi;
import net.proteanit.sql.DbUtils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Anditeguh
 */
public class Form_Alat_Gym extends javax.swing.JFrame {
    private DefaultTableModel DftTblModel_member;
    private String SQL;
    ResultSet rs = null;

    /**
     * Creates new form Form_Karyawan Gym
     */
    public Form_Alat_Gym() {
        initComponents();
        this.cariData(SQL);
        this.TampilData();
        this.kosong();
    }
    
      public void TampilData(){
        DftTblModel_member = new DefaultTableModel();
        DftTblModel_member.addColumn("Kode Alat Gym");
        DftTblModel_member.addColumn("Nama Alat");
        DftTblModel_member.addColumn("Jenis Alat");
        DftTblModel_member.addColumn("Kegunaan");
        DftTblModel_member.addColumn("Harga Beli");
        DftTblModel_member.addColumn("Stok");
        DftTblModel_member.addColumn("Tanggal Beli");
        table_alat_gym.setModel(DftTblModel_member);
        Connection conn = Koneksi.getCon();
        try {
            java.sql.Statement stmt = conn.createStatement();
            SQL = "select * from tb_alat_gym";
            java.sql.ResultSet res = stmt.executeQuery(SQL);
            while (res.next()) {
                DftTblModel_member.addRow(new Object[]{
                    res.getString("kode_alat_gym"),
                    res.getString("nama_alat"),
                    res.getString("jenis_alat"),
                    res.getString("kegunaan_alat"),
                    res.getString("harga_beli"),
                    res.getString("stok"),
                    res.getString("tgl_beli")
                });
            }
             id_auto();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void id_auto(){
        try{
            Connection con = Koneksi.getCon();
            Statement st = con.createStatement();
            String sql = "select max(right(kode_alat_gym, 4)) as no from tb_alat_gym";
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                if(rs.first()== false){
                    txt_kode_alat_gym.setText("ALT0001");
                } else{
                    rs.last();
                    int set_id = rs.getInt(1)+1;
                    String no = String.valueOf(set_id);
                    int id_next = no.length();
                    for ( int a = 0; a < 4 - id_next; a++){
                        no = "0" + no;
                    }
                    txt_kode_alat_gym.setText("ALT" + no.toString());
                }
            }
        } catch(SQLException e){
            Logger.getLogger(Form_Instruktur.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
      private void cariData(String key){
        try{
            Object[] judul_kolom = {"Kode Alat Gym",
                                    "Nama Alat", "Jenis Alat", 
                                    "Kegunaan", "Harga Beli",
                                    "Stok", "Tanggal Beli"};
            DftTblModel_member=new DefaultTableModel(null,judul_kolom);
            table_alat_gym.setModel(DftTblModel_member);
            
            Connection conn=(Connection)Koneksi.getCon();
            Statement stt=conn.createStatement();
            DftTblModel_member.getDataVector().removeAllElements();
            
            rs =stt.executeQuery("SELECT * from tb_alat_gym WHERE kode_alat_gym LIKE '%"+key+"%'"
                    + " OR nama_alat LIKE '%"+key+"%'"
                    + " OR jenis_alat LIKE '%"+key+"%' "
                    + "OR kegunaan_alat LIKE '%"+key+"%'"
                    + " OR harga_beli LIKE '%"+key+"%' "
                    + "OR stok LIKE '%"+key+"%' "
                    + "OR tgl_beli LIKE '%"+key+"%'");  
            while(rs.next()){
                Object[] data={
                    rs.getString("kode_alat_gym"),
                    rs.getString("nama_alat"),
                    rs.getString("jenis_alat"),
                    rs.getString("kegunaan_alat"),
                    rs.getString("harga_beli"),
                    rs.getString("stok"),
                    rs.getString("tgl_beli")
                };
               DftTblModel_member.addRow(data);
               
            }  
        } catch (Exception ex) {
        System.err.println(ex.getMessage());
        }
    }
    
   public void kosong(){
        txt_nama_alat.setText(null);
        txt_jenis_alat.setText(null);
        txt_kegunaan.setText(null);
        txt_harga_beli.setText(null);
        txt_stok.setText(null);
              
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
        txt_kode_alat_gym = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_nama_alat = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_jenis_alat = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btn_save = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        btn_exit = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        txt_tanggal_beli = new com.toedter.calendar.JDateChooser();
        jLabel14 = new javax.swing.JLabel();
        txt_kegunaan = new javax.swing.JTextField();
        txt_harga_beli = new javax.swing.JTextField();
        txt_stok = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_alat_gym = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        txt_cari_member = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txt_kode_alat_gym.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txt_kode_alat_gym.setEnabled(false);
        txt_kode_alat_gym.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_kode_alat_gymActionPerformed(evt);
            }
        });

        jLabel1.setText("Kode Alat Gym :");

        jLabel3.setText("Nama Alat :");

        txt_nama_alat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nama_alatActionPerformed(evt);
            }
        });

        jLabel4.setText("Jenis Alat :");

        txt_jenis_alat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_jenis_alatActionPerformed(evt);
            }
        });

        jLabel5.setText("Kegunaan :");

        jLabel12.setText("Harga Beli :");

        jLabel13.setText("Stok :");

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

        btn_exit.setText("EXIT");
        btn_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_exitActionPerformed(evt);
            }
        });

        btn_delete.setText("DELETE");
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_save, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(btn_update, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_exit)
                .addGap(155, 155, 155))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_delete)
                        .addComponent(btn_exit))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_save)
                        .addComponent(btn_update)))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jLabel14.setText("Tanggal Beli :");

        txt_kegunaan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_kegunaanActionPerformed(evt);
            }
        });

        txt_harga_beli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_harga_beliActionPerformed(evt);
            }
        });

        txt_stok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_stokActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel12)
                            .addComponent(jLabel14)
                            .addComponent(jLabel13))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txt_nama_alat, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                                .addComponent(txt_kode_alat_gym)
                                .addComponent(txt_jenis_alat)
                                .addComponent(txt_kegunaan)
                                .addComponent(txt_harga_beli)
                                .addComponent(txt_stok))
                            .addComponent(txt_tanggal_beli, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_kode_alat_gym, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_nama_alat, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_jenis_alat, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_kegunaan, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_harga_beli, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_stok, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_tanggal_beli, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel2.setText("--{List Data Alat Gym}--");

        jLabel7.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel7.setText("--{Input Data Alat Gym}--");

        table_alat_gym.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Kode Alat", "Nama Alat", "Jenis Barang", "Jenis Kegunaan", "Harga Beli", "Stok", "Tanggal Beli"
            }
        ));
        table_alat_gym.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_alat_gymMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_alat_gym);

        jLabel8.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel8.setText("--CARI DATA ALAT GYM--");

        txt_cari_member.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cari_memberActionPerformed(evt);
            }
        });
        txt_cari_member.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_cari_memberKeyReleased(evt);
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
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 695, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(txt_cari_member, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                            .addComponent(txt_cari_member, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton5)
                            .addComponent(jButton6)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(98, 98, 98))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_kode_alat_gymActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_kode_alat_gymActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_kode_alat_gymActionPerformed

    private void txt_nama_alatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nama_alatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nama_alatActionPerformed

    private void txt_jenis_alatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_jenis_alatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_jenis_alatActionPerformed

    private void btn_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveActionPerformed
        // TODO add your handling code here:
        String kode_alat_gym = txt_kode_alat_gym.getText();
        String nama_alat = txt_nama_alat.getText();
        String jenis_alat = txt_jenis_alat.getText();
        String kegunaan = (String) txt_kegunaan.getText();
        String harga_beli =  String.valueOf(txt_harga_beli.getText());
        String stok =  String.valueOf(txt_stok.getText());
        String tampilan = "yyyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
        String Tgl_Beli = String.valueOf(fm.format(txt_tanggal_beli.getDate())); 
        txt_kode_alat_gym.setEditable(false);
        
        try {
            Connection conn = Koneksi.getCon();
            PreparedStatement stmt = conn.prepareStatement("insert into tb_alat_gym values(?,?,?,?,?,?,?)");
            stmt.setString(1, kode_alat_gym);
            stmt.setString(2, nama_alat);
            stmt.setString(3, jenis_alat);
            stmt.setString(4, kegunaan);
            stmt.setString(5, harga_beli);
            stmt.setString(6, stok);
            stmt.setString(7, Tgl_Beli);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan", "Pesan", JOptionPane.INFORMATION_MESSAGE);
            setVisible(false);
            new Form_Alat_Gym().setVisible(true);
            TampilData();
            id_auto();
//            Update_table();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_btn_saveActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        // TODO add your handling code here:
          try {
            String tampilan = "yyyy-MM-dd";
            SimpleDateFormat fm = new SimpleDateFormat(tampilan);
            String Tgl_Beli = String.valueOf(fm.format(txt_tanggal_beli.getDate()));
            Connection conn = Koneksi.getCon();
            PreparedStatement stmt = conn.prepareStatement("update tb_alat_gym set"
                    + " nama_alat=?, jenis_alat=?, kegunaan_alat=?, harga_beli=?,"
                    + " stok=?, tgl_beli=? where kode_alat_gym=?");
            stmt.setString(1, txt_nama_alat.getText());
            stmt.setString(2, txt_jenis_alat.getText());
            stmt.setString(3, (String) txt_kegunaan.getText());
            stmt.setString(4, (String) txt_stok.getText());
            stmt.setString(5, Tgl_Beli);
            stmt.setString(6, txt_harga_beli.getText());
            stmt.setString(7, txt_kode_alat_gym.getText());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil diubah", "Pesan", JOptionPane.INFORMATION_MESSAGE);
            TampilData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Perubahan Data Gagal"+e.getMessage());
        }
    }//GEN-LAST:event_btn_updateActionPerformed

    private void btn_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_exitActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btn_exitActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        // TODO add your handling code here:
          try {
            String sql ="delete from tb_alat_gym where kode_alat_gym='"+txt_kode_alat_gym.getText()+"'";
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
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void txt_cari_memberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cari_memberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cari_memberActionPerformed

    private void txt_cari_memberKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cari_memberKeyReleased
        // TODO add your handling code here:
          String key=txt_cari_member.getText();
        System.out.println(key);

        if(key!=""){
            cariData(key);
        }else{
            TampilData();
        }
    }//GEN-LAST:event_txt_cari_memberKeyReleased

    private void table_alat_gymMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_alat_gymMouseClicked
        // TODO add your handling code here:
        int baris = table_alat_gym.rowAtPoint(evt.getPoint());
        String Kode_Alat_Gym = table_alat_gym.getValueAt(baris, 0).toString();
        txt_kode_alat_gym.setText(Kode_Alat_Gym);
        
        String nama_alat =table_alat_gym.getValueAt(baris, 1).toString();
        txt_nama_alat.setText(nama_alat);
        
        String jenis_alat =table_alat_gym.getValueAt(baris, 2).toString();
        txt_nama_alat.setText(jenis_alat);
        
        String kegunaan_alat =table_alat_gym.getValueAt(baris, 3).toString();
        txt_kegunaan.setText(kegunaan_alat);
       
        String harga_beli = table_alat_gym.getValueAt(baris, 4).toString();
        txt_harga_beli.setText(harga_beli);
        
        String stok =table_alat_gym.getValueAt(baris, 5).toString();
        txt_stok.setText(stok);
       
    }//GEN-LAST:event_table_alat_gymMouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        kosong();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void txt_kegunaanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_kegunaanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_kegunaanActionPerformed

    private void txt_harga_beliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_harga_beliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_harga_beliActionPerformed

    private void txt_stokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_stokActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_stokActionPerformed

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
                new Form_Alat_Gym().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_exit;
    private javax.swing.JButton btn_save;
    private javax.swing.JButton btn_update;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table_alat_gym;
    private javax.swing.JTextField txt_cari_member;
    private javax.swing.JTextField txt_harga_beli;
    private javax.swing.JTextField txt_jenis_alat;
    private javax.swing.JTextField txt_kegunaan;
    private javax.swing.JTextField txt_kode_alat_gym;
    private javax.swing.JTextField txt_nama_alat;
    private javax.swing.JTextField txt_stok;
    private com.toedter.calendar.JDateChooser txt_tanggal_beli;
    // End of variables declaration//GEN-END:variables
}
