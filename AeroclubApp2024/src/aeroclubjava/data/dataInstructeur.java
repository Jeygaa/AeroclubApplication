/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aeroclubjava.data;

import java.sql.*;
/**
 *
 * @author stephen.bonvoisin
 */
public class dataInstructeur {
    public int num_instructeur;
    public String nom;
    public String prenom;
    public int num_civil;
    public int taux_instructeur;
    public String adresse;
    public String code_postal;
    public String ville;
    public String tel;
    public String portable;
    public String fax;
    public String commentaire;
    public int num_badge;
    public String email;
    
    public void getInstructeur(int num_instructeur){
        Connection connection = null;
        
        try{
            config db = new config();
            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mariadb://"+db.host+"/"+db.dbname, db.login, db.password);
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM instructeurs WHERE num_instructeur = ?;");
            ps.setInt(1, num_instructeur);
            ResultSet rs = ps.executeQuery();
            rs.next();
            
            this.num_instructeur = rs.getInt("num_instructeur");
            this.nom = rs.getString("nom");
            this.prenom = rs.getString("prenom");
            this.num_civil = rs.getInt("num_civil");
            this.taux_instructeur = rs.getInt("taux_instructeur");
            this.adresse = rs.getString("adresse");
            this.code_postal = rs.getString("code_postal");
            this.ville = rs.getString("ville");
            this.tel = rs.getString("tel");
            this.portable = rs.getString("portable");
            this.fax = rs.getString("fax");
            this.commentaire = rs.getString("commentaire");
            this.num_badge = rs.getInt("num_badge");
            this.email = rs.getString("email");
            
        } catch(ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public void insertInstructeur(){
        Connection connection = null;
        
        try{
            config db = new config();
            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mariadb://"+db.host+"/"+db.dbname, db.login, db.password);
            PreparedStatement ps = connection.prepareStatement("INSERT INTO public.instructeurs(nom, prenom, num_civil, taux_instructeur, adresse, code_postal, ville, tel, portable, fax, commentaire, num_badge, email) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);");
            ps.setString(1, this.nom);
            ps.setString(2, this.prenom);
            ps.setInt(3, this.num_civil);
            ps.setInt(4, this.taux_instructeur);
            ps.setString(5, this.adresse);
            ps.setString(6, this.code_postal);
            ps.setString(7, this.ville);
            ps.setString(8, this.tel);
            ps.setString(9, this.portable);
            ps.setString(10, this.fax);
            ps.setString(11, this.commentaire);
            ps.setInt(12, this.num_badge);
            ps.setString(13, this.email);
            ps.executeUpdate();
            
        } catch(ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public void modifyInstructeur(){
        Connection connection = null;
        
        try{
            config db = new config();
            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mariadb://"+db.host+"/"+db.dbname, db.login, db.password);
            PreparedStatement ps = connection.prepareStatement("UPDATE public.instructeurs SET nom=?, prenom=?, num_civil=?, taux_instructeur=?, adresse=?, code_postal=?, ville=?, tel=?, portable=?, fax=?, commentaire=?, num_badge=?, email=? WHERE num_instructeur=?;");
            ps.setString(1, this.nom);
            ps.setString(2, this.prenom);
            ps.setInt(3, this.num_civil);
            ps.setInt(4, this.taux_instructeur);
            ps.setString(5, this.adresse);
            ps.setString(6, this.code_postal);
            ps.setString(7, this.ville);
            ps.setString(8, this.tel);
            ps.setString(9, this.portable);
            ps.setString(10, this.fax);
            ps.setString(11, this.commentaire);
            ps.setInt(12, this.num_badge);
            ps.setString(13, this.email);
            ps.setInt(14, this.num_instructeur);
            ps.executeUpdate();
            
        } catch(ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public void deleteInstructeur(){
        Connection connection = null;
        
        try{
            config db = new config();
            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mariadb://"+db.host+"/"+db.dbname, db.login, db.password);
            PreparedStatement ps = connection.prepareStatement("DELETE FROM instructeurs WHERE num_instructeur=?;");
            ps.setInt(1, this.num_instructeur);
            ps.executeUpdate();
            
        } catch(ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
