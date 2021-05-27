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
public class Form_Karyawan extends javax.swing.JFrame {
    private DefaultTableModel DftTblModel_member;
    private String SQL;
    ResultSet rs = null;

    /**
     * Creates new form Form_Karyawan Gym
     */
    public Form_Karyawan() {
        initComponents();
         this.cariData(SQL);
        this.TampilData();
        this.kosong();
    }
    
      public void TampilData(){
        DftTblModel_member = new DefaultTableModel();
        DftTblModel_member.addColumn("ID Karyawan");
        DftTblModel_member.addColumn("Nama Karyawan");
        DftTblModel_member.addColumn("No Telefon");
        DftTblModel_member.addColumn("Jenis Kelamin");
        DftTblModel_member.addColumn("Jabatan");
        DftTblModel_member.addColumn("Email");
        DftTblModel_member.addColumn("Tanggal Bekerja");
        table_karyawan.setModel(DftTblModel_member);
        Connection conn = Koneksi.getCon();
        try {
            java.sql.Statement stmt = conn.createStatement();
            SQL = "select * from tb_karyawan";
            java.sql.ResultSet res = stmt.executeQuery(SQL);
            while (res.next()) {
                DftTblModel_member.addRow(new Object[]{
                    res.getString("id_karyawan"),
                    res.getString("nama_karyawan"),
                    res.getString("no_telefon"),
                    res.getString("jenis_kelamin"),
                    res.getString("jabatan"),
                    res.getString("email"),
                    res.getString("tanggal_bekerja")
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
            String sql = "select max(right(id_karyawan, 4)) as no from tb_karyawan";
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                if(rs.first()== false){
                    txt_id_karyawan.setText("KRY0001");
                } else{
                    rs.last();
                    int set_id = rs.getInt(1)+1;
                    String no = String.valueOf(set_id);
                    int id_next = no.length();
                    for ( int a = 0; a < 4 - id_next; a++){
                        no = "0" + no;
                    }
                    txt_id_karyawan.setText("KRY" + no.toString());
                }
            }
        } catch(SQLException e){
            Logger.getLogger(Form_Instruktur.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
      private void cariData(String key){
        try{
            Object[] judul_kolom = {"ID Karyawan", "Nama Karyawan", "No Telefon", "Jenis Kelamin", "Jabatan", "Email", "Tanggal Bekerja"};
            DftTblModel_member=new DefaultTableModel(null,judul_kolom);
            table_karyawan.setModel(DftTblModel_member);
            
            Connection conn=(Connection)Koneksi.getCon();
            Statement stt=conn.createStatement();
            DftTblModel_member.getDataVector().removeAllElements();
            
            rs =stt.executeQuery("SELECT * from tb_karyawan WHERE id_karyawan LIKE '%"+key+"%'"
                    + " OR nama_karyawan LIKE '%"+key+"%'"
                    + " OR no_telefon LIKE '%"+key+"%' "
                    + "OR jenis_kelamin LIKE '%"+key+"%'"
                    + " OR jabatan LIKE '%"+key+"%' "
                    + "OR email LIKE '%"+key+"%' "
                    + "OR tanggal_bekerja LIKE '%"+key+"%'");  
            while(rs.next()){
                Object[] data={
                    rs.getString("id_karyawan"),
                    rs.getString("nama_karyawan"),
                    rs.getString("no_telefon"),
                    rs.getString("jenis_kelamin"),
                    rs.getString("jabatan"),
                    rs.getString("email"),
                    rs.getString("tanggal_bekerja")
                };
               DftTblModel_member.addRow(data);
               
            }  
        } catch (Exception ex) {
        System.err.println(ex.getMessage());
        }
    }
    
   public void kosong(){
        txt_nama_karyawan.setText(null);
        txt_jabatan.setSelectedItem(this);
        txt_jenis_kelamin.setSelectedItem(this);
        txt_no_telefon.setText(null);
        txt_email.setText(null);
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
        txt_id_karyawan = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_nama_karyawan = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_no_telefon = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_email = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btn_save = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        txt_tanggal_bekerja = new com.toedter.calendar.JDateChooser();
        txt_jenis_kelamin = new javax.swing.JComboBox<>();
        txt_jabatan = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_karyawan = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        txt_cari_karyawan = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txt_id_karyawan.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txt_id_karyawan.setEnabled(false);
        txt_id_karyawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_id_karyawanActionPerformed(evt);
            }
        });

        jLabel1.setText("ID Karyawan :");

        jLabel3.setText("Nama Karyawan :");

        txt_nama_karyawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nama_karyawanActionPerformed(evt);
            }
        });

        jLabel4.setText("No Telp :");

        txt_no_telefon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_no_telefonActionPerformed(evt);
            }
        });

        jLabel5.setText("Jenis Kelamin :");

        jLabel6.setText("Email :");

        txt_email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_emailActionPerformed(evt);
            }
        });

        jLabel12.setText("Jabatan :");

        jLabel13.setText("Tanggal Bekerja :");

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_save, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(btn_update, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addGap(155, 155, 155))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton4)
                        .addComponent(jButton3))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_save)
                        .addComponent(btn_update)))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        txt_jenis_kelamin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Laki-Laki", "Perempuan", "Transgender/Waria" }));

        txt_jabatan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kasir", "Staff" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(txt_jabatan, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel13))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_jenis_kelamin, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txt_nama_karyawan)
                                        .addComponent(txt_id_karyawan)
                                        .addComponent(txt_no_telefon)
                                        .addComponent(txt_email)
                                        .addComponent(txt_tanggal_bekerja, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_id_karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_nama_karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_no_telefon, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_jenis_kelamin, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_jabatan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_tanggal_bekerja, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel2.setText("--{List Data Karyawan Gym}--");

        jLabel7.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel7.setText("--{Input Data Karyawan Gym}--");

        table_karyawan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Karyawan", "Nama Karyawan", "No Telp", "Jenis Kelamin", "Jabatan", "Email", "Tanggal Bekerja"
            }
        ));
        table_karyawan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_karyawanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_karyawan);

        jLabel8.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel8.setText("--CARI DATA KARYAWAN--");

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
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 704, Short.MAX_VALUE)
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
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_id_karyawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_id_karyawanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_id_karyawanActionPerformed

    private void txt_nama_karyawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nama_karyawanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nama_karyawanActionPerformed

    private void txt_no_telefonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_no_telefonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_no_telefonActionPerformed

    private void txt_emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_emailActionPerformed

    private void btn_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveActionPerformed
        // TODO add your handling code here:
        String id_karyawan = txt_id_karyawan.getText();
        String nama_karyawan = txt_nama_karyawan.getText();
        String no_telefon = txt_no_telefon.getText();
        String jenis_kelamin = (String) txt_jenis_kelamin.getSelectedItem();
        String jabatan = (String) txt_jabatan.getSelectedItem();
        String email = txt_email.getText();
        String tampilan = "yyyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
        String Tgl_Bekerja = String.valueOf(fm.format(txt_tanggal_bekerja.getDate())); 
        txt_id_karyawan.setEditable(false);
        
        try {
            Connection conn = Koneksi.getCon();
            PreparedStatement stmt = conn.prepareStatement("insert into tb_karyawan values(?,?,?,?,?,?,?)");
            stmt.setString(1, id_karyawan);
            stmt.setString(2, nama_karyawan);
            stmt.setString(3, no_telefon);
            stmt.setString(4, jenis_kelamin);
            stmt.setString(5, jabatan);
            stmt.setString(6, email);
            stmt.setString(7, Tgl_Bekerja);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan", "Pesan", JOptionPane.INFORMATION_MESSAGE);
            setVisible(false);
            new Form_Karyawan().setVisible(true);
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
            String Tgl_Bekerja = String.valueOf(fm.format(txt_tanggal_bekerja.getDate())); 
            Connection conn = Koneksi.getCon();
            PreparedStatement stmt = conn.prepareStatement("update tb_karyawan set nama_karyawan=?, no_telefon=?, jenis_kelamin=?, jabatan=?, email=?, tanggal_bekerja=? where id_karyawan=?");
            stmt.setString(1, txt_nama_karyawan.getText());
            stmt.setString(2, txt_no_telefon.getText());
            stmt.setString(3, (String) txt_jenis_kelamin.getSelectedItem());
            stmt.setString(4, (String) txt_jabatan.getSelectedItem());
            stmt.setString(5, txt_email.getText());
            stmt.setString(6, Tgl_Bekerja);
            stmt.setString(7, txt_id_karyawan.getText());
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
            String sql ="delete from tb_karyawan where id_karyawan='"+txt_id_karyawan.getText()+"'";
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

    private void table_karyawanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_karyawanMouseClicked
        // TODO add your handling code here:
        int baris = table_karyawan.rowAtPoint(evt.getPoint());
        String ID_Karyawan = table_karyawan.getValueAt(baris, 0).toString();
        txt_id_karyawan.setText(ID_Karyawan);
        
        String nama =table_karyawan.getValueAt(baris, 1).toString();
        txt_nama_karyawan.setText(nama);
        
        String no_telefon =table_karyawan.getValueAt(baris, 2).toString();
        txt_no_telefon.setText(no_telefon);
       
        String jk = table_karyawan.getValueAt(baris, 3).toString();
        txt_jenis_kelamin.setSelectedItem(jk);
        
        String jabatan = table_karyawan.getValueAt(baris, 4 ).toString();
        txt_jabatan.setSelectedItem(jabatan);
        
        String email =table_karyawan.getValueAt(baris, 5).toString();
        txt_email.setText(email);
        
        
    }//GEN-LAST:event_table_karyawanMouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        kosong();
    }//GEN-LAST:event_jButton6ActionPerformed

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
                new Form_Karyawan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_save;
    private javax.swing.JButton btn_update;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table_karyawan;
    private javax.swing.JTextField txt_cari_karyawan;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_id_karyawan;
    private javax.swing.JComboBox<String> txt_jabatan;
    private javax.swing.JComboBox<String> txt_jenis_kelamin;
    private javax.swing.JTextField txt_nama_karyawan;
    private javax.swing.JTextField txt_no_telefon;
    private com.toedter.calendar.JDateChooser txt_tanggal_bekerja;
    // End of variables declaration//GEN-END:variables
}
