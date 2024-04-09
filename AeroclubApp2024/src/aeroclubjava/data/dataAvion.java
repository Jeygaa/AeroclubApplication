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
public class dataAvion {
    public int num_avion;
    public String type_avion;
    public int taux;
    public int forfait1;
    public int forfait2;
    public int forfait3;
    public int heures_forfait1;
    public int heures_forfait2;
    public int heures_forfait3;
    public int reduction_semaine;
    public String immatriculation;
    public String description;
    public String image_avion;
    
   
    public void getAvion(int num_avion){
        Connection connection = null;
        
        try {
            config db = new config();
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://"+db.host+"/"+db.dbname, db.login, db.password);
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM avions WHERE num_avion = ?;");
            ps.setInt(1, num_avion);
            ResultSet rs = ps.executeQuery();
            rs.next();
            
            this.num_avion = rs.getInt("num_avion");
            this.type_avion = rs.getString("type");
            this.taux = rs.getInt("taux");
            this.forfait1 = rs.getInt("forfait1");
            this.forfait2 = rs.getInt("forfait2");
            this.forfait3 = rs.getInt("forfait3");
            this.heures_forfait1 = rs.getInt("heures_forfait1");
            this.heures_forfait2 = rs.getInt("heures_forfait2");
            this.heures_forfait3 = rs.getInt("heures_forfait3");
            this.reduction_semaine = rs.getInt("reduction_semaine");
            this.immatriculation = rs.getString("immatriculation");
            this.description = rs.getString("description");
            this.image_avion = rs.getString("image");
            
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
    
    public void insertAvion(){
        Connection connection = null;
        
        try{
            config db = new config();
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://"+db.host+"/"+db.dbname, db.login, db.password);
            PreparedStatement ps = connection.prepareStatement("INSERT INTO public.avions(type, taux, forfait1, forfait2, forfait3, heures_forfait1, heures_forfait2, heures_forfait3, reduction_semaine, immatriculation, description, image) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, this.type_avion);
            ps.setInt(2, this.taux);
            ps.setInt(3, this.forfait1);
            ps.setInt(4, this.forfait2);
            ps.setInt(5, this.forfait3);
            ps.setInt(6, this.heures_forfait1);
            ps.setInt(7, this.heures_forfait2);
            ps.setInt(8, this.heures_forfait3);
            ps.setInt(9, this.reduction_semaine);
            ps.setString(10, this.immatriculation);
            ps.setString(11, this.description);
            ps.setString(12, this.image_avion);
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
    
    public void modifyAvion(){
        Connection connection = null;
        
        try{
            config db = new config();
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://"+db.host+"/"+db.dbname, db.login, db.password);
            PreparedStatement ps = connection.prepareStatement("UPDATE public.avions SET type=?, taux=?, forfait1=?, forfait2=?, forfait3=?, heures_forfait1=?, heures_forfait2=?, heures_forfait3=?, reduction_semaine=?, immatriculation=?, description=? WHERE num_avion=?;");
            ps.setString(1, this.type_avion);
            ps.setInt(2, this.taux);
            ps.setInt(3, this.forfait1);
            ps.setInt(4, this.forfait2);
            ps.setInt(5, this.forfait3);
            ps.setInt(6, this.heures_forfait1);
            ps.setInt(7, this.heures_forfait2);
            ps.setInt(8, this.heures_forfait3);
            ps.setInt(9, this.reduction_semaine);
            ps.setString(10, this.immatriculation);
            ps.setString(11, this.description);
            ps.setInt(12, this.num_avion);
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
    
    public void deleteAvion(){
        Connection connection = null;
        
        try{
            config db = new config();
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://"+db.host+"/"+db.dbname, db.login, db.password);
            PreparedStatement ps = connection.prepareStatement("DELETE FROM avions WHERE num_avion = ?;");
            ps.setInt(1, this.num_avion);
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

