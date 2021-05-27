/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package koneksi;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 *
 * @author Anditeguh
 */
public class Koneksi {
    
    public static Connection getCon(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:8889/gms","root","root");
            return con;
        } 
        catch(Exception e){
            System.err.println("koneksi gagal "+e.getMessage()); //perintah menampilkan error pada koneksi
            return null;
        }
    }

    public static PreparedStatement getCon(String sql) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
