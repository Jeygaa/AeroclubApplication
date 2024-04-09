/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aeroclubjava;

import aeroclubjava.data.config;
import aeroclubjava.data.dataMembre;
import java.sql.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author stephen.bonvoisin
 */
public class Membre extends javax.swing.JInternalFrame {

    /**
     * Creates new form Membre
     */
    public Membre() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI ui =(BasicInternalFrameUI)this.getUI();
        ui.setNorthPane(null);
    }
    
    public void AffichageMembre() {
        Connection connection = null;
        
        try {
            
            config db = new config();
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://"+db.host+"/"+db.dbname, db.login, db.password);
            
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM membres ORDER BY num_membre ASC;");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                String num_membre = rs.getString("num_membre");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                
                String data[] = {num_membre, nom, prenom};
                DefaultTableModel tblModel = (DefaultTableModel)MembreTable.getModel();
                tblModel.addRow(data);
            }
            
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
    
    public void openShowMembre() {
        
        int selectedRow = MembreTable.getSelectedRow();
        
        if(selectedRow != -1) {
            
            int num_membre = Integer.parseInt(String.valueOf(MembreTable.getValueAt(selectedRow, 0)));

            dataMembre membre = new dataMembre();
            
            membre.getMembre(num_membre);
            
            num_membreJLabel.setText(String.valueOf(membre.num_membre));
            nomJLabel.setText(membre.nom);
            prenomJLabel.setText(membre.prenom);
            adresseJLabel.setText(membre.adresse);
            code_postalJLabel.setText(membre.code_postal);
            villeJLabel.setText(membre.ville);
            num_civilJLabel.setText(String.valueOf(membre.num_civil));
            telJLabel.setText(membre.tel);
            portableJLabel.setText(membre.portable);
            emailJLabel.setText(membre.email);
            commentaireJLabel.setText(membre.commentaire);
            date_vmJLabel.setText(String.valueOf(membre.date_vm));
            validite_vmJLabel.setText(String.valueOf(membre.validite_vm));
            date_seqJLabel.setText(String.valueOf(membre.date_seq));
            validite_seqJLabel.setText(String.valueOf(membre.validite_seq));
            num_badgeJLabel.setText(membre.num_badge);
            num_qualifJLabel.setText(String.valueOf(membre.num_qualif));
            date_naissanceJLabel.setText(String.valueOf(membre.date_naissance));
            lieu_naissanceJLabel.setText(membre.lieu_naissance);
            carte_federaleJLabel.setText(membre.carte_federale);
            date_attestationJLabel.setText(String.valueOf(membre.date_attestation));
            date_theorique_bbJLabel.setText(String.valueOf(membre.date_theorique_bb));
            date_theorique_pplaJLabel.setText(String.valueOf(membre.date_theorique_ppla));
            date_bbJLabel.setText(String.valueOf(membre.date_bb));
            date_pplaJLabel.setText(String.valueOf(membre.date_ppla));
            numero_bbJLabel.setText(membre.numero_bb);
            numero_pplaJLabel.setText(membre.numero_ppla);
            date_entreeJLabel.setText(String.valueOf(membre.date_entree));
            membre_actifJLabel.setText(String.valueOf(membre.membre_actif));
            membre_inscritJLabel.setText(String.valueOf(membre.membre_inscrit));
            loginJLabel.setText(membre.login);

            showMembreJDialog.setVisible(true);
            
        }
    }
    
    public void openAddMembre() {
        
        addMembre_loginJTextField.setText("");
        addMembre_passwordJPasswordField.setText("");
        addMembre_nomJTextField.setText("");
        addMembre_prenomJTextField.setText("");
        addMembre_adresseJTextField.setText("");
        addMembre_code_postalJTextField.setText("");
        addMembre_villeJTextField.setText("");
        addMembre_num_civilJTextField.setText("");
        addMembre_telJTextField.setText("");
        addMembre_portableJTextField.setText("");
        addMembre_emailJTextField.setText("");
        addMembre_commentaireJTextField.setText("");
        addMembre_date_vmJDateChooser.setDate(null);
        addMembre_validite_vmJDateChooser.setDate(null);
        addMembre_date_seqJDateChooser.setDate(null);
        addMembre_validite_seqJDateChooser.setDate(null);
        addMembre_num_badgeJTextField.setText("");
        addMembre_num_qualifJTextField.setText("");
        addMembre_professionJTextField.setText("");
        addMembre_date_naissanceJDateChooser.setDate(null);
        addMembre_lieu_naissanceJTextField.setText("");
        addMembre_carte_federaleJTextField.setText("");
        addMembre_date_attestationJDateChooser.setDate(null);
        addMembre_date_theorique_bbJDateChooser.setDate(null);
        addMembre_date_theorique_pplaJDateChooser.setDate(null);
        addMembre_date_bbJDateChooser.setDate(null);
        addMembre_date_pplaJDateChooser.setDate(null);
        addMembre_numero_bbJTextField.setText("");
        addMembre_numera_pplaJTextField.setText("");
        addMembre_date_entreeJDateChooser.setDate(null);
        addMembre_membre_actifJCheckBox.setSelected(false);
        addMembre_membre_inscritJCheckBox.setSelected(false);
        
        addMembreJDialog.setVisible(true);
        
    }
    
    public void addMembre() {
        
        dataMembre membre = new dataMembre();
        
        membre.nom = addMembre_nomJTextField.getText();
        membre.prenom = addMembre_prenomJTextField.getText();
        membre.adresse = addMembre_adresseJTextField.getText();
        membre.code_postal = addMembre_code_postalJTextField.getText();
        membre.ville = addMembre_villeJTextField.getText();
        membre.num_civil = Integer.parseInt(addMembre_num_civilJTextField.getText());
        membre.tel = addMembre_telJTextField.getText();
        membre.portable = addMembre_portableJTextField.getText();
        membre.email = addMembre_emailJTextField.getText();
        membre.commentaire = addMembre_commentaireJTextField.getText();
        membre.date_vm = new java.sql.Date(addMembre_date_vmJDateChooser.getDate().getTime());
        membre.validite_vm = new java.sql.Date(addMembre_validite_vmJDateChooser.getDate().getTime());
        membre.date_seq = new java.sql.Date(addMembre_date_seqJDateChooser.getDate().getTime());
        membre.validite_seq = new java.sql.Date(addMembre_validite_seqJDateChooser.getDate().getTime());
        membre.num_badge = addMembre_num_badgeJTextField.getText();
        membre.num_qualif = Integer.parseInt(addMembre_num_qualifJTextField.getText());
        membre.profession = addMembre_professionJTextField.getText();
        membre.date_naissance = new java.sql.Date(addMembre_date_naissanceJDateChooser.getDate().getTime());
        membre.lieu_naissance = addMembre_lieu_naissanceJTextField.getText();
        membre.carte_federale = addMembre_carte_federaleJTextField.getText();
        membre.date_attestation = new java.sql.Date(addMembre_date_attestationJDateChooser.getDate().getTime());
        membre.date_theorique_bb = new java.sql.Date(addMembre_date_theorique_bbJDateChooser.getDate().getTime());
        membre.date_theorique_ppla = new java.sql.Date(addMembre_date_theorique_pplaJDateChooser.getDate().getTime());
        membre.date_bb = new java.sql.Date(addMembre_date_bbJDateChooser.getDate().getTime());
        membre.date_ppla = new java.sql.Date(addMembre_date_pplaJDateChooser.getDate().getTime());
        membre.numero_bb = addMembre_numero_bbJTextField.getText();
        membre.numero_ppla = addMembre_numera_pplaJTextField.getText();
        membre.date_entree = new java.sql.Date(addMembre_date_entreeJDateChooser.getDate().getTime());
        membre.membre_actif = addMembre_membre_actifJCheckBox.isSelected() ? 1 : 0;
        membre.membre_inscrit = addMembre_membre_inscritJCheckBox.isSelected() ? 1 : 0;
        membre.login = addMembre_loginJTextField.getText();
        membre.password = addMembre_passwordJPasswordField.getText();
        
        membre.insertMembre();
        
        addMembreJDialog.dispose();
        
    }
    
    public void openModifyMembre() {
        
        int selectedRow = MembreTable.getSelectedRow();
        
        if(selectedRow != -1) {
            
            int num_membre = Integer.parseInt(String.valueOf(MembreTable.getValueAt(selectedRow, 0)));

            dataMembre membre = new dataMembre();
            
            membre.getMembre(num_membre);
            
            modifyMembre_loginJTextField.setText(membre.login);
            modifyMembre_nomJTextField.setText(membre.nom);
            modifyMembre_prenomJTextField.setText(membre.prenom);
            modifyMembre_adresseJTextField.setText(membre.adresse);
            modifyMembre_code_postalJTextField.setText(membre.code_postal);
            modifyMembre_villeJTextField.setText(membre.ville);
            modifyMembre_num_civilJTextField.setText(String.valueOf(membre.num_civil));
            modifyMembre_telJTextField.setText(membre.tel);
            modifyMembre_portableJTextField.setText(membre.portable);
            modifyMembre_emailJTextField.setText(membre.email);
            modifyMembre_commentaireJTextField.setText(membre.commentaire);
            modifyMembre_date_vmJTextField.setText(String.valueOf(membre.date_vm));
            modifyMembre_validite_vmJTextField.setText(String.valueOf(membre.validite_vm));
            modifyMembre_date_seqJTextField.setText(String.valueOf(membre.date_seq));
            modifyMembre_validite_seqJTextField.setText(String.valueOf(membre.validite_seq));
            modifyMembre_num_badgeJTextField.setText(membre.num_badge);
            modifyMembre_num_qualifJTextField.setText(String.valueOf(membre.num_qualif));
            modifyMembre_date_naissanceJTextField.setText(String.valueOf(membre.date_naissance));
            modifyMembre_lieu_naissanceJTextField.setText(membre.lieu_naissance);
            modifyMembre_carte_federaleJTextField.setText(membre.carte_federale);
            modifyMembre_date_attestationJTextField.setText(String.valueOf(membre.date_attestation));
            modifyMembre_date_theorique_bbJTextField.setText(String.valueOf(membre.date_theorique_bb));
            modifyMembre_date_theorique_pplaJTextField.setText(String.valueOf(membre.date_theorique_ppla));
            modifyMembre_date_bbJTextField.setText(String.valueOf(membre.date_bb));
            modifyMembre_date_pplaJTextField.setText(String.valueOf(membre.date_ppla));
            modifyMembre_numero_bbJTextField.setText(membre.numero_bb);
            modifyMembre_numera_pplaJTextField.setText(membre.numero_ppla);
            modifyMembre_date_entreeJTextField.setText(String.valueOf(membre.date_entree));
            modifyMembre_membre_actifJCheckBox.setSelected((membre.membre_actif == 1) ? true : false);
            modifyMembre_membre_inscritJCheckBox.setSelected((membre.membre_inscrit == 1) ? true : false);

            modifyMembreJDialog.setVisible(true);
        
        }
    }
    
    public void modifyMembre() {
        
        dataMembre membre = new dataMembre();
        
        membre.num_membre = Integer.parseInt(String.valueOf(MembreTable.getValueAt(MembreTable.getSelectedRow(), 0)));
        membre.nom = modifyMembre_nomJTextField.getText();
        membre.prenom = modifyMembre_prenomJTextField.getText();
        membre.adresse = modifyMembre_adresseJTextField.getText();
        membre.code_postal = modifyMembre_code_postalJTextField.getText();
        membre.ville = modifyMembre_villeJTextField.getText();
        membre.num_civil = Integer.parseInt(modifyMembre_num_civilJTextField.getText());
        membre.tel = modifyMembre_telJTextField.getText();
        membre.portable = modifyMembre_portableJTextField.getText();
        membre.email = modifyMembre_emailJTextField.getText();
        membre.commentaire = modifyMembre_commentaireJTextField.getText();
        membre.date_vm = java.sql.Date.valueOf(modifyMembre_date_vmJTextField.getText());
        membre.validite_vm = java.sql.Date.valueOf(modifyMembre_validite_vmJTextField.getText());
        membre.date_seq = java.sql.Date.valueOf(modifyMembre_date_seqJTextField.getText());
        membre.validite_seq = java.sql.Date.valueOf(modifyMembre_validite_seqJTextField.getText());
        membre.num_badge = modifyMembre_num_badgeJTextField.getText();
        membre.num_qualif = Integer.parseInt(modifyMembre_num_qualifJTextField.getText());
        membre.profession = modifyMembre_professionJTextField.getText();
        membre.date_naissance = java.sql.Date.valueOf(modifyMembre_date_naissanceJTextField.getText());
        membre.lieu_naissance = modifyMembre_lieu_naissanceJTextField.getText();
        membre.carte_federale = modifyMembre_carte_federaleJTextField.getText();
        membre.date_attestation = java.sql.Date.valueOf(modifyMembre_date_attestationJTextField.getText());
        membre.date_theorique_bb = java.sql.Date.valueOf(modifyMembre_date_theorique_bbJTextField.getText());
        membre.date_theorique_ppla = java.sql.Date.valueOf(modifyMembre_date_theorique_pplaJTextField.getText());
        membre.date_bb = java.sql.Date.valueOf(modifyMembre_date_bbJTextField.getText());
        membre.date_ppla = java.sql.Date.valueOf(modifyMembre_date_pplaJTextField.getText());
        membre.numero_bb = modifyMembre_numero_bbJTextField.getText();
        membre.numero_ppla = modifyMembre_numera_pplaJTextField.getText();
        membre.date_entree = java.sql.Date.valueOf(modifyMembre_date_entreeJTextField.getText());
        membre.membre_actif = modifyMembre_membre_actifJCheckBox.isSelected() ? 1 : 0;
        membre.membre_inscrit = modifyMembre_membre_inscritJCheckBox.isSelected() ? 1 : 0;
        membre.login = modifyMembre_loginJTextField.getText();
        membre.password = modifyMembre_passwordJPasswordField.getText();
        
        membre.modifyMembre();
        
        modifyMembreJDialog.dispose();
    }
    
    public void openDeleteMembre() {
        
        int selectedRow = MembreTable.getSelectedRow();
        
        if(selectedRow != -1) {
            
            deleteMembreJDialog.setVisible(true);
            
        }
    }
    
    public void deleteMembre() {
        
        dataMembre membre = new dataMembre();
        
        membre.num_membre = Integer.parseInt((String) MembreTable.getValueAt(MembreTable.getSelectedRow(), 0));
        
        membre.deleteMembre();
        
        deleteMembreJDialog.dispose();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        showMembreJDialog = new javax.swing.JDialog();
        num_membreJLabel = new javax.swing.JLabel();
        nomJLabel = new javax.swing.JLabel();
        prenomJLabel = new javax.swing.JLabel();
        adresseJLabel = new javax.swing.JLabel();
        code_postalJLabel = new javax.swing.JLabel();
        villeJLabel = new javax.swing.JLabel();
        num_civilJLabel = new javax.swing.JLabel();
        telJLabel = new javax.swing.JLabel();
        portableJLabel = new javax.swing.JLabel();
        commentaireJLabel = new javax.swing.JLabel();
        date_vmJLabel = new javax.swing.JLabel();
        validite_vmJLabel = new javax.swing.JLabel();
        date_seqJLabel = new javax.swing.JLabel();
        validite_seqJLabel = new javax.swing.JLabel();
        num_badgeJLabel = new javax.swing.JLabel();
        num_qualifJLabel = new javax.swing.JLabel();
        professionJLabel = new javax.swing.JLabel();
        date_naissanceJLabel = new javax.swing.JLabel();
        carte_federaleJLabel = new javax.swing.JLabel();
        date_attestationJLabel = new javax.swing.JLabel();
        date_theorique_bbJLabel = new javax.swing.JLabel();
        date_theorique_pplaJLabel = new javax.swing.JLabel();
        date_bbJLabel = new javax.swing.JLabel();
        date_pplaJLabel = new javax.swing.JLabel();
        numero_bbJLabel = new javax.swing.JLabel();
        numero_pplaJLabel = new javax.swing.JLabel();
        date_entreeJLabel = new javax.swing.JLabel();
        emailJLabel = new javax.swing.JLabel();
        lieu_naissanceJLabel = new javax.swing.JLabel();
        membre_actifJLabel = new javax.swing.JLabel();
        membre_inscritJLabel = new javax.swing.JLabel();
        loginJLabel = new javax.swing.JLabel();
        addMembreJDialog = new javax.swing.JDialog();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        addMembre_nomJTextField = new javax.swing.JTextField();
        addMembre_prenomJTextField = new javax.swing.JTextField();
        addMembre_adresseJTextField = new javax.swing.JTextField();
        addMembre_code_postalJTextField = new javax.swing.JTextField();
        addMembre_villeJTextField = new javax.swing.JTextField();
        addMembre_num_civilJTextField = new javax.swing.JTextField();
        addMembre_telJTextField = new javax.swing.JTextField();
        addMembre_portableJTextField = new javax.swing.JTextField();
        addMembre_emailJTextField = new javax.swing.JTextField();
        addMembre_commentaireJTextField = new javax.swing.JTextField();
        addMembre_num_badgeJTextField = new javax.swing.JTextField();
        addMembre_num_qualifJTextField = new javax.swing.JTextField();
        addMembre_professionJTextField = new javax.swing.JTextField();
        addMembre_lieu_naissanceJTextField = new javax.swing.JTextField();
        addMembre_carte_federaleJTextField = new javax.swing.JTextField();
        addMembre_numero_bbJTextField = new javax.swing.JTextField();
        addMembre_numera_pplaJTextField = new javax.swing.JTextField();
        addMembre_loginJTextField = new javax.swing.JTextField();
        addMembreButton = new javax.swing.JButton();
        addMembre_date_vmJDateChooser = new com.toedter.calendar.JDateChooser();
        addMembre_validite_vmJDateChooser = new com.toedter.calendar.JDateChooser();
        addMembre_date_seqJDateChooser = new com.toedter.calendar.JDateChooser();
        addMembre_validite_seqJDateChooser = new com.toedter.calendar.JDateChooser();
        addMembre_date_naissanceJDateChooser = new com.toedter.calendar.JDateChooser();
        addMembre_date_attestationJDateChooser = new com.toedter.calendar.JDateChooser();
        addMembre_date_theorique_bbJDateChooser = new com.toedter.calendar.JDateChooser();
        addMembre_date_theorique_pplaJDateChooser = new com.toedter.calendar.JDateChooser();
        addMembre_date_bbJDateChooser = new com.toedter.calendar.JDateChooser();
        addMembre_date_pplaJDateChooser = new com.toedter.calendar.JDateChooser();
        addMembre_date_entreeJDateChooser = new com.toedter.calendar.JDateChooser();
        addMembre_membre_actifJCheckBox = new javax.swing.JCheckBox();
        addMembre_membre_inscritJCheckBox = new javax.swing.JCheckBox();
        addMembre_passwordJPasswordField = new javax.swing.JPasswordField();
        modifyMembreJDialog = new javax.swing.JDialog();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        modifyMembre_nomJTextField = new javax.swing.JTextField();
        modifyMembre_prenomJTextField = new javax.swing.JTextField();
        modifyMembre_adresseJTextField = new javax.swing.JTextField();
        modifyMembre_code_postalJTextField = new javax.swing.JTextField();
        modifyMembre_villeJTextField = new javax.swing.JTextField();
        modifyMembre_num_civilJTextField = new javax.swing.JTextField();
        modifyMembre_telJTextField = new javax.swing.JTextField();
        modifyMembre_portableJTextField = new javax.swing.JTextField();
        modifyMembre_emailJTextField = new javax.swing.JTextField();
        modifyMembre_commentaireJTextField = new javax.swing.JTextField();
        modifyMembre_num_badgeJTextField = new javax.swing.JTextField();
        modifyMembre_num_qualifJTextField = new javax.swing.JTextField();
        modifyMembre_professionJTextField = new javax.swing.JTextField();
        modifyMembre_lieu_naissanceJTextField = new javax.swing.JTextField();
        modifyMembre_carte_federaleJTextField = new javax.swing.JTextField();
        modifyMembre_numero_bbJTextField = new javax.swing.JTextField();
        modifyMembre_numera_pplaJTextField = new javax.swing.JTextField();
        modifyMembre_loginJTextField = new javax.swing.JTextField();
        modifyMembreButton = new javax.swing.JButton();
        modifyMembre_membre_actifJCheckBox = new javax.swing.JCheckBox();
        modifyMembre_membre_inscritJCheckBox = new javax.swing.JCheckBox();
        modifyMembre_passwordJPasswordField = new javax.swing.JPasswordField();
        modifyMembre_date_vmJTextField = new javax.swing.JTextField();
        modifyMembre_validite_vmJTextField = new javax.swing.JTextField();
        modifyMembre_date_seqJTextField = new javax.swing.JTextField();
        modifyMembre_validite_seqJTextField = new javax.swing.JTextField();
        modifyMembre_date_naissanceJTextField = new javax.swing.JTextField();
        modifyMembre_date_attestationJTextField = new javax.swing.JTextField();
        modifyMembre_date_theorique_bbJTextField = new javax.swing.JTextField();
        modifyMembre_date_theorique_pplaJTextField = new javax.swing.JTextField();
        modifyMembre_date_bbJTextField = new javax.swing.JTextField();
        modifyMembre_date_pplaJTextField = new javax.swing.JTextField();
        modifyMembre_date_entreeJTextField = new javax.swing.JTextField();
        deleteMembreJDialog = new javax.swing.JDialog();
        deleteMembreButton = new javax.swing.JButton();
        cancelMembreButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        MembreTable = new javax.swing.JTable();
        showMembre = new javax.swing.JButton();
        addMembre = new javax.swing.JButton();
        modifyMembre = new javax.swing.JButton();
        deleteMembre = new javax.swing.JButton();

        showMembreJDialog.setMinimumSize(new java.awt.Dimension(600, 600));

        num_membreJLabel.setText("jLabel2");

        nomJLabel.setText("jLabel3");

        prenomJLabel.setText("jLabel4");

        adresseJLabel.setText("jLabel5");

        code_postalJLabel.setText("jLabel6");

        villeJLabel.setText("jLabel7");

        num_civilJLabel.setText("jLabel8");

        telJLabel.setText("jLabel9");

        portableJLabel.setText("jLabel10");

        commentaireJLabel.setText("jLabel11");

        date_vmJLabel.setText("jLabel12");

        validite_vmJLabel.setText("jLabel13");

        date_seqJLabel.setText("jLabel14");

        validite_seqJLabel.setText("jLabel15");

        num_badgeJLabel.setText("jLabel16");

        num_qualifJLabel.setText("jLabel17");

        professionJLabel.setText("jLabel18");

        date_naissanceJLabel.setText("jLabel19");

        carte_federaleJLabel.setText("jLabel20");

        date_attestationJLabel.setText("jLabel21");

        date_theorique_bbJLabel.setText("jLabel22");

        date_theorique_pplaJLabel.setText("jLabel23");

        date_bbJLabel.setText("jLabel24");

        date_pplaJLabel.setText("jLabel25");

        numero_bbJLabel.setText("jLabel26");

        numero_pplaJLabel.setText("jLabel27");

        date_entreeJLabel.setText("jLabel28");

        emailJLabel.setText("jLabel29");

        lieu_naissanceJLabel.setText("jLabel30");

        membre_actifJLabel.setText("jLabel31");

        membre_inscritJLabel.setText("jLabel32");

        loginJLabel.setText("jLabel2");

        javax.swing.GroupLayout showMembreJDialogLayout = new javax.swing.GroupLayout(showMembreJDialog.getContentPane());
        showMembreJDialog.getContentPane().setLayout(showMembreJDialogLayout);
        showMembreJDialogLayout.setHorizontalGroup(
            showMembreJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(showMembreJDialogLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(showMembreJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(showMembreJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(telJLabel)
                        .addComponent(num_civilJLabel)
                        .addComponent(villeJLabel)
                        .addComponent(code_postalJLabel)
                        .addComponent(adresseJLabel)
                        .addComponent(prenomJLabel)
                        .addComponent(nomJLabel)
                        .addComponent(num_membreJLabel))
                    .addComponent(portableJLabel)
                    .addComponent(emailJLabel))
                .addGap(109, 109, 109)
                .addGroup(showMembreJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(commentaireJLabel)
                    .addComponent(date_naissanceJLabel)
                    .addComponent(date_vmJLabel)
                    .addComponent(validite_vmJLabel)
                    .addComponent(date_seqJLabel)
                    .addComponent(validite_seqJLabel)
                    .addComponent(num_badgeJLabel)
                    .addComponent(num_qualifJLabel)
                    .addComponent(professionJLabel)
                    .addComponent(lieu_naissanceJLabel)
                    .addComponent(membre_inscritJLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 108, Short.MAX_VALUE)
                .addGroup(showMembreJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(carte_federaleJLabel)
                    .addComponent(date_attestationJLabel)
                    .addComponent(date_theorique_bbJLabel)
                    .addComponent(date_theorique_pplaJLabel)
                    .addComponent(date_bbJLabel)
                    .addComponent(date_pplaJLabel)
                    .addComponent(numero_bbJLabel)
                    .addComponent(numero_pplaJLabel)
                    .addComponent(date_entreeJLabel)
                    .addComponent(membre_actifJLabel)
                    .addComponent(loginJLabel))
                .addGap(27, 27, 27))
        );
        showMembreJDialogLayout.setVerticalGroup(
            showMembreJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(showMembreJDialogLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(showMembreJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(num_membreJLabel)
                    .addComponent(commentaireJLabel)
                    .addComponent(carte_federaleJLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(showMembreJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nomJLabel)
                    .addComponent(date_vmJLabel)
                    .addComponent(date_attestationJLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(showMembreJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(prenomJLabel)
                    .addComponent(validite_vmJLabel)
                    .addComponent(date_theorique_bbJLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(showMembreJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(adresseJLabel)
                    .addComponent(date_seqJLabel)
                    .addComponent(date_theorique_pplaJLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(showMembreJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(code_postalJLabel)
                    .addComponent(validite_seqJLabel)
                    .addComponent(date_bbJLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(showMembreJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(villeJLabel)
                    .addComponent(num_badgeJLabel)
                    .addComponent(date_pplaJLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(showMembreJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(num_civilJLabel)
                    .addComponent(num_qualifJLabel)
                    .addComponent(numero_bbJLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(showMembreJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(telJLabel)
                    .addComponent(professionJLabel)
                    .addComponent(numero_pplaJLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(showMembreJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(portableJLabel)
                    .addComponent(date_naissanceJLabel)
                    .addComponent(date_entreeJLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(showMembreJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailJLabel)
                    .addComponent(lieu_naissanceJLabel)
                    .addComponent(membre_actifJLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(showMembreJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(membre_inscritJLabel)
                    .addComponent(loginJLabel))
                .addContainerGap())
        );

        addMembreJDialog.setMinimumSize(new java.awt.Dimension(600, 600));

        jLabel2.setText("nom");

        jLabel3.setText("prenom");

        jLabel4.setText("adresse");

        jLabel5.setText("code_postal");

        jLabel6.setText("ville");

        jLabel7.setText("num_civil");

        jLabel8.setText("tel");

        jLabel9.setText("portable");

        jLabel10.setText("email");

        jLabel11.setText("commentaire");

        jLabel12.setText("date_vm");

        jLabel13.setText("validite_vm");

        jLabel14.setText("date_seq");

        jLabel15.setText("validite_seq");

        jLabel16.setText("num_badge");

        jLabel17.setText("num_qualif");

        jLabel18.setText("profession");

        jLabel19.setText("date_naissance");

        jLabel20.setText("lieu_naissance");

        jLabel21.setText("carte_federale");

        jLabel22.setText("date_attestation");

        jLabel23.setText("date_theorique_bb");

        jLabel24.setText("date_theorique_ppla");

        jLabel25.setText("date_bb");

        jLabel26.setText("date_ppla");

        jLabel27.setText("numero_bb");

        jLabel28.setText("numero_ppla");

        jLabel29.setText("date_entree");

        jLabel30.setText("membre_actif");

        jLabel31.setText("membre_inscrit");

        jLabel32.setText("login");

        jLabel33.setText("password");

        addMembre_nomJTextField.setText("jTextField1");

        addMembre_prenomJTextField.setText("jTextField2");

        addMembre_adresseJTextField.setText("jTextField3");

        addMembre_code_postalJTextField.setText("jTextField4");

        addMembre_villeJTextField.setText("jTextField5");

        addMembre_num_civilJTextField.setText("jTextField6");

        addMembre_telJTextField.setText("jTextField7");

        addMembre_portableJTextField.setText("jTextField8");

        addMembre_emailJTextField.setText("jTextField9");

        addMembre_commentaireJTextField.setText("jTextField10");

        addMembre_num_badgeJTextField.setText("jTextField15");

        addMembre_num_qualifJTextField.setText("jTextField16");

        addMembre_professionJTextField.setText("jTextField17");

        addMembre_lieu_naissanceJTextField.setText("jTextField19");

        addMembre_carte_federaleJTextField.setText("jTextField20");

        addMembre_numero_bbJTextField.setText("jTextField26");

        addMembre_numera_pplaJTextField.setText("jTextField27");

        addMembre_loginJTextField.setText("jTextField31");

        addMembreButton.setText("Ajouter");
        addMembreButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addMembreButtonActionPerformed(evt);
            }
        });

        addMembre_membre_actifJCheckBox.setText("jCheckBox1");

        addMembre_membre_inscritJCheckBox.setText("jCheckBox2");

        addMembre_passwordJPasswordField.setText("jPasswordField1");

        javax.swing.GroupLayout addMembreJDialogLayout = new javax.swing.GroupLayout(addMembreJDialog.getContentPane());
        addMembreJDialog.getContentPane().setLayout(addMembreJDialogLayout);
        addMembreJDialogLayout.setHorizontalGroup(
            addMembreJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addMembreJDialogLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(addMembreJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addMembreJDialogLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(addMembre_prenomJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(addMembreJDialogLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(addMembre_adresseJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(addMembreJDialogLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(addMembre_code_postalJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(addMembreJDialogLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(addMembre_villeJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(addMembreJDialogLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(addMembre_num_civilJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(addMembreJDialogLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(addMembre_telJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(addMembreJDialogLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(addMembre_portableJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(addMembreJDialogLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addMembre_emailJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(addMembreJDialogLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(addMembre_nomJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(addMembreJDialogLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addMembre_commentaireJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(addMembreJDialogLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(addMembre_date_vmJDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(128, 128, 128)
                .addGroup(addMembreJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addMembreJDialogLayout.createSequentialGroup()
                        .addGroup(addMembreJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(addMembreJDialogLayout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(addMembre_date_attestationJDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel33))
                            .addGroup(addMembreJDialogLayout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(addMembre_carte_federaleJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel32))
                            .addGroup(addMembreJDialogLayout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(addMembre_lieu_naissanceJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel31))
                            .addGroup(addMembreJDialogLayout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(addMembre_date_naissanceJDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel30))
                            .addGroup(addMembreJDialogLayout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(addMembre_professionJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel29))
                            .addGroup(addMembreJDialogLayout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(addMembre_num_qualifJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel28))
                            .addGroup(addMembreJDialogLayout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(addMembre_num_badgeJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel27))
                            .addGroup(addMembreJDialogLayout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(addMembre_validite_seqJDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel26))
                            .addGroup(addMembreJDialogLayout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(addMembre_date_seqJDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel25))
                            .addGroup(addMembreJDialogLayout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(addMembre_validite_vmJDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(113, 113, 113)
                                .addComponent(jLabel24)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(addMembreJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addMembre_numero_bbJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addMembre_numera_pplaJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addMembre_loginJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addMembre_date_theorique_pplaJDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addMembre_date_bbJDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addMembre_date_pplaJDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addMembre_date_entreeJDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addMembre_membre_actifJCheckBox)
                            .addComponent(addMembre_membre_inscritJCheckBox)
                            .addComponent(addMembre_passwordJPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(92, Short.MAX_VALUE))
                    .addGroup(addMembreJDialogLayout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(addMembre_date_theorique_bbJDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addMembreButton)
                        .addGap(29, 29, 29))))
        );
        addMembreJDialogLayout.setVerticalGroup(
            addMembreJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addMembreJDialogLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(addMembreJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addMembreJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel13)
                        .addComponent(jLabel24)
                        .addComponent(addMembre_nomJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(addMembre_validite_vmJDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(addMembre_date_theorique_pplaJDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addMembreJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(addMembreJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel14)
                        .addComponent(jLabel25)
                        .addComponent(addMembre_prenomJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(addMembre_date_seqJDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addMembre_date_bbJDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addMembreJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(addMembreJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jLabel15)
                        .addComponent(jLabel26)
                        .addComponent(addMembre_adresseJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(addMembre_validite_seqJDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addMembre_date_pplaJDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addMembreJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel16)
                    .addComponent(jLabel27)
                    .addComponent(addMembre_code_postalJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addMembre_num_badgeJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addMembre_numero_bbJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addMembreJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel17)
                    .addComponent(jLabel28)
                    .addComponent(addMembre_villeJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addMembre_num_qualifJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addMembre_numera_pplaJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addMembreJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addMembreJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(jLabel18)
                        .addComponent(jLabel29)
                        .addComponent(addMembre_num_civilJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(addMembre_professionJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(addMembre_date_entreeJDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addMembreJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(addMembreJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(jLabel19)
                        .addComponent(jLabel30)
                        .addComponent(addMembre_telJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(addMembre_membre_actifJCheckBox))
                    .addComponent(addMembre_date_naissanceJDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addMembreJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel20)
                    .addComponent(jLabel31)
                    .addComponent(addMembre_portableJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addMembre_lieu_naissanceJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addMembre_membre_inscritJCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addMembreJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel21)
                    .addComponent(jLabel32)
                    .addComponent(addMembre_emailJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addMembre_carte_federaleJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addMembre_loginJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addMembreJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addMembreJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(jLabel22)
                        .addComponent(jLabel33)
                        .addComponent(addMembre_commentaireJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(addMembre_passwordJPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(addMembre_date_attestationJDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(addMembreJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addMembreJDialogLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(addMembreJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(addMembreJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel23)
                                .addComponent(addMembre_date_theorique_bbJDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(addMembre_date_vmJDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addMembreJDialogLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addMembreButton)
                        .addGap(17, 17, 17))))
        );

        modifyMembreJDialog.setMinimumSize(new java.awt.Dimension(600, 600));

        jLabel34.setText("nom");

        jLabel35.setText("prenom");

        jLabel36.setText("adresse");

        jLabel37.setText("code_postal");

        jLabel38.setText("ville");

        jLabel39.setText("num_civil");

        jLabel40.setText("tel");

        jLabel41.setText("portable");

        jLabel42.setText("email");

        jLabel43.setText("commentaire");

        jLabel44.setText("date_vm");

        jLabel45.setText("validite_vm");

        jLabel46.setText("date_seq");

        jLabel47.setText("validite_seq");

        jLabel48.setText("num_badge");

        jLabel49.setText("num_qualif");

        jLabel50.setText("profession");

        jLabel51.setText("date_naissance");

        jLabel52.setText("lieu_naissance");

        jLabel53.setText("carte_federale");

        jLabel54.setText("date_attestation");

        jLabel55.setText("date_theorique_bb");

        jLabel56.setText("date_theorique_ppla");

        jLabel57.setText("date_bb");

        jLabel58.setText("date_ppla");

        jLabel59.setText("numero_bb");

        jLabel60.setText("numero_ppla");

        jLabel61.setText("date_entree");

        jLabel62.setText("membre_actif");

        jLabel63.setText("membre_inscrit");

        jLabel64.setText("login");

        jLabel65.setText("password");

        modifyMembre_nomJTextField.setText("jTextField1");

        modifyMembre_prenomJTextField.setText("jTextField2");

        modifyMembre_adresseJTextField.setText("jTextField3");

        modifyMembre_code_postalJTextField.setText("jTextField4");

        modifyMembre_villeJTextField.setText("jTextField5");

        modifyMembre_num_civilJTextField.setText("jTextField6");

        modifyMembre_telJTextField.setText("jTextField7");

        modifyMembre_portableJTextField.setText("jTextField8");

        modifyMembre_emailJTextField.setText("jTextField9");

        modifyMembre_commentaireJTextField.setText("jTextField10");

        modifyMembre_num_badgeJTextField.setText("jTextField15");

        modifyMembre_num_qualifJTextField.setText("jTextField16");

        modifyMembre_professionJTextField.setText("jTextField17");

        modifyMembre_lieu_naissanceJTextField.setText("jTextField19");

        modifyMembre_carte_federaleJTextField.setText("jTextField20");

        modifyMembre_numero_bbJTextField.setText("jTextField26");

        modifyMembre_numera_pplaJTextField.setText("jTextField27");

        modifyMembre_loginJTextField.setText("jTextField31");

        modifyMembreButton.setText("Modifier");
        modifyMembreButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyMembreButtonActionPerformed(evt);
            }
        });

        modifyMembre_membre_actifJCheckBox.setText("jCheckBox1");

        modifyMembre_membre_inscritJCheckBox.setText("jCheckBox2");

        modifyMembre_passwordJPasswordField.setText("jPasswordField1");

        modifyMembre_date_vmJTextField.setText("jTextField1");

        modifyMembre_validite_vmJTextField.setText("jTextField1");

        modifyMembre_date_seqJTextField.setText("jTextField1");

        modifyMembre_validite_seqJTextField.setText("jTextField1");

        modifyMembre_date_naissanceJTextField.setText("jTextField1");

        modifyMembre_date_attestationJTextField.setText("jTextField1");
        modifyMembre_date_attestationJTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyMembre_date_attestationJTextFieldActionPerformed(evt);
            }
        });

        modifyMembre_date_theorique_bbJTextField.setText("jTextField1");

        modifyMembre_date_theorique_pplaJTextField.setText("jTextField1");

        modifyMembre_date_bbJTextField.setText("jTextField1");

        modifyMembre_date_pplaJTextField.setText("jTextField1");

        modifyMembre_date_entreeJTextField.setText("jTextField1");

        javax.swing.GroupLayout modifyMembreJDialogLayout = new javax.swing.GroupLayout(modifyMembreJDialog.getContentPane());
        modifyMembreJDialog.getContentPane().setLayout(modifyMembreJDialogLayout);
        modifyMembreJDialogLayout.setHorizontalGroup(
            modifyMembreJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modifyMembreJDialogLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(modifyMembreJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(modifyMembreJDialogLayout.createSequentialGroup()
                        .addComponent(jLabel35)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(modifyMembre_prenomJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(modifyMembreJDialogLayout.createSequentialGroup()
                        .addComponent(jLabel36)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(modifyMembre_adresseJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(modifyMembreJDialogLayout.createSequentialGroup()
                        .addComponent(jLabel37)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(modifyMembre_code_postalJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(modifyMembreJDialogLayout.createSequentialGroup()
                        .addComponent(jLabel38)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(modifyMembre_villeJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(modifyMembreJDialogLayout.createSequentialGroup()
                        .addComponent(jLabel39)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(modifyMembre_num_civilJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(modifyMembreJDialogLayout.createSequentialGroup()
                        .addComponent(jLabel40)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(modifyMembre_telJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(modifyMembreJDialogLayout.createSequentialGroup()
                        .addComponent(jLabel41)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(modifyMembre_portableJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(modifyMembreJDialogLayout.createSequentialGroup()
                        .addComponent(jLabel42)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(modifyMembre_emailJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(modifyMembreJDialogLayout.createSequentialGroup()
                        .addComponent(jLabel34)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(modifyMembre_nomJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(modifyMembreJDialogLayout.createSequentialGroup()
                        .addComponent(jLabel43)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(modifyMembre_commentaireJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(modifyMembreJDialogLayout.createSequentialGroup()
                        .addComponent(jLabel44)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(modifyMembre_date_vmJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(128, 128, 128)
                .addGroup(modifyMembreJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(modifyMembreJDialogLayout.createSequentialGroup()
                        .addGroup(modifyMembreJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(modifyMembreJDialogLayout.createSequentialGroup()
                                .addComponent(jLabel54)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(modifyMembre_date_attestationJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel65))
                            .addGroup(modifyMembreJDialogLayout.createSequentialGroup()
                                .addComponent(jLabel53)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(modifyMembre_carte_federaleJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel64))
                            .addGroup(modifyMembreJDialogLayout.createSequentialGroup()
                                .addComponent(jLabel52)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(modifyMembre_lieu_naissanceJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel63))
                            .addGroup(modifyMembreJDialogLayout.createSequentialGroup()
                                .addComponent(jLabel51)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(modifyMembre_date_naissanceJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel62))
                            .addGroup(modifyMembreJDialogLayout.createSequentialGroup()
                                .addComponent(jLabel50)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(modifyMembre_professionJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel61))
                            .addGroup(modifyMembreJDialogLayout.createSequentialGroup()
                                .addComponent(jLabel49)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(modifyMembre_num_qualifJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel60))
                            .addGroup(modifyMembreJDialogLayout.createSequentialGroup()
                                .addComponent(jLabel48)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(modifyMembre_num_badgeJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel59))
                            .addGroup(modifyMembreJDialogLayout.createSequentialGroup()
                                .addComponent(jLabel47)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(modifyMembre_validite_seqJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel58))
                            .addGroup(modifyMembreJDialogLayout.createSequentialGroup()
                                .addComponent(jLabel46)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(modifyMembre_date_seqJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel57))
                            .addGroup(modifyMembreJDialogLayout.createSequentialGroup()
                                .addComponent(jLabel45)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(modifyMembre_validite_vmJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(127, 127, 127)
                                .addComponent(jLabel56)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(modifyMembreJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(modifyMembre_numero_bbJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(modifyMembre_numera_pplaJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(modifyMembre_loginJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(modifyMembre_membre_actifJCheckBox)
                            .addComponent(modifyMembre_membre_inscritJCheckBox)
                            .addComponent(modifyMembre_passwordJPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(modifyMembre_date_theorique_pplaJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(modifyMembre_date_bbJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(modifyMembre_date_pplaJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(modifyMembre_date_entreeJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(92, Short.MAX_VALUE))
                    .addGroup(modifyMembreJDialogLayout.createSequentialGroup()
                        .addComponent(jLabel55)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(modifyMembre_date_theorique_bbJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(modifyMembreButton)
                        .addGap(29, 29, 29))))
        );
        modifyMembreJDialogLayout.setVerticalGroup(
            modifyMembreJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modifyMembreJDialogLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(modifyMembreJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(jLabel45)
                    .addComponent(jLabel56)
                    .addComponent(modifyMembre_nomJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modifyMembre_validite_vmJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modifyMembre_date_theorique_pplaJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(modifyMembreJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(jLabel46)
                    .addComponent(jLabel57)
                    .addComponent(modifyMembre_prenomJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modifyMembre_date_seqJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modifyMembre_date_bbJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(modifyMembreJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(jLabel47)
                    .addComponent(jLabel58)
                    .addComponent(modifyMembre_adresseJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modifyMembre_validite_seqJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modifyMembre_date_pplaJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(modifyMembreJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(jLabel48)
                    .addComponent(jLabel59)
                    .addComponent(modifyMembre_code_postalJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modifyMembre_num_badgeJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modifyMembre_numero_bbJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(modifyMembreJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(jLabel49)
                    .addComponent(jLabel60)
                    .addComponent(modifyMembre_villeJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modifyMembre_num_qualifJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modifyMembre_numera_pplaJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(modifyMembreJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(jLabel50)
                    .addComponent(jLabel61)
                    .addComponent(modifyMembre_num_civilJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modifyMembre_professionJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modifyMembre_date_entreeJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(modifyMembreJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(jLabel51)
                    .addComponent(jLabel62)
                    .addComponent(modifyMembre_telJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modifyMembre_membre_actifJCheckBox)
                    .addComponent(modifyMembre_date_naissanceJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(modifyMembreJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(jLabel52)
                    .addComponent(jLabel63)
                    .addComponent(modifyMembre_portableJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modifyMembre_lieu_naissanceJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modifyMembre_membre_inscritJCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(modifyMembreJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(jLabel53)
                    .addComponent(jLabel64)
                    .addComponent(modifyMembre_emailJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modifyMembre_carte_federaleJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modifyMembre_loginJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(modifyMembreJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(jLabel54)
                    .addComponent(jLabel65)
                    .addComponent(modifyMembre_commentaireJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modifyMembre_passwordJPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modifyMembre_date_attestationJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(modifyMembreJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(modifyMembreJDialogLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(modifyMembreJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel55)
                            .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(modifyMembre_date_vmJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(modifyMembre_date_theorique_bbJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, modifyMembreJDialogLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(modifyMembreButton)
                        .addGap(17, 17, 17))))
        );

        deleteMembreJDialog.setMinimumSize(new java.awt.Dimension(600, 600));

        deleteMembreButton.setText("Supprimer");
        deleteMembreButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteMembreButtonActionPerformed(evt);
            }
        });

        cancelMembreButton.setText("Annuler");
        cancelMembreButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelMembreButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout deleteMembreJDialogLayout = new javax.swing.GroupLayout(deleteMembreJDialog.getContentPane());
        deleteMembreJDialog.getContentPane().setLayout(deleteMembreJDialogLayout);
        deleteMembreJDialogLayout.setHorizontalGroup(
            deleteMembreJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(deleteMembreJDialogLayout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(deleteMembreButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 110, Short.MAX_VALUE)
                .addComponent(cancelMembreButton)
                .addGap(67, 67, 67))
        );
        deleteMembreJDialogLayout.setVerticalGroup(
            deleteMembreJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(deleteMembreJDialogLayout.createSequentialGroup()
                .addGap(127, 127, 127)
                .addGroup(deleteMembreJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteMembreButton)
                    .addComponent(cancelMembreButton))
                .addContainerGap(150, Short.MAX_VALUE))
        );

        setPreferredSize(new java.awt.Dimension(900, 800));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Membre");

        MembreTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nom", "Prenom"
            }
        ));
        jScrollPane2.setViewportView(MembreTable);

        showMembre.setText("Afficher");
        showMembre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showMembreActionPerformed(evt);
            }
        });

        addMembre.setText("Ajouter");
        addMembre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addMembreActionPerformed(evt);
            }
        });

        modifyMembre.setText("Modifier");
        modifyMembre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyMembreActionPerformed(evt);
            }
        });

        deleteMembre.setText("Supprimer");
        deleteMembre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteMembreActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(315, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(310, 310, 310))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGap(157, 157, 157)
                .addComponent(showMembre, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(addMembre, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(modifyMembre, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(deleteMembre, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(showMembre, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addMembre, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modifyMembre, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteMembre, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void showMembreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showMembreActionPerformed
        // TODO add your handling code here:
        openShowMembre();
    }//GEN-LAST:event_showMembreActionPerformed

    private void addMembreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addMembreActionPerformed
        // TODO add your handling code here:
        openAddMembre();
    }//GEN-LAST:event_addMembreActionPerformed

    private void modifyMembreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifyMembreActionPerformed
        // TODO add your handling code here:
        openModifyMembre();
    }//GEN-LAST:event_modifyMembreActionPerformed

    private void deleteMembreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteMembreActionPerformed
        // TODO add your handling code here:
        openDeleteMembre();
    }//GEN-LAST:event_deleteMembreActionPerformed

    private void addMembreButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addMembreButtonActionPerformed
        // TODO add your handling code here:
        addMembre();
    }//GEN-LAST:event_addMembreButtonActionPerformed

    private void modifyMembreButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifyMembreButtonActionPerformed
        // TODO add your handling code here:
        modifyMembre();
    }//GEN-LAST:event_modifyMembreButtonActionPerformed

    private void deleteMembreButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteMembreButtonActionPerformed
        // TODO add your handling code here:
        deleteMembre();
    }//GEN-LAST:event_deleteMembreButtonActionPerformed

    private void cancelMembreButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelMembreButtonActionPerformed
        // TODO add your handling code here:
        deleteMembreJDialog.dispose();
    }//GEN-LAST:event_cancelMembreButtonActionPerformed

    private void modifyMembre_date_attestationJTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifyMembre_date_attestationJTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_modifyMembre_date_attestationJTextFieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable MembreTable;
    private javax.swing.JButton addMembre;
    private javax.swing.JButton addMembreButton;
    private javax.swing.JDialog addMembreJDialog;
    private javax.swing.JTextField addMembre_adresseJTextField;
    private javax.swing.JTextField addMembre_carte_federaleJTextField;
    private javax.swing.JTextField addMembre_code_postalJTextField;
    private javax.swing.JTextField addMembre_commentaireJTextField;
    private com.toedter.calendar.JDateChooser addMembre_date_attestationJDateChooser;
    private com.toedter.calendar.JDateChooser addMembre_date_bbJDateChooser;
    private com.toedter.calendar.JDateChooser addMembre_date_entreeJDateChooser;
    private com.toedter.calendar.JDateChooser addMembre_date_naissanceJDateChooser;
    private com.toedter.calendar.JDateChooser addMembre_date_pplaJDateChooser;
    private com.toedter.calendar.JDateChooser addMembre_date_seqJDateChooser;
    private com.toedter.calendar.JDateChooser addMembre_date_theorique_bbJDateChooser;
    private com.toedter.calendar.JDateChooser addMembre_date_theorique_pplaJDateChooser;
    private com.toedter.calendar.JDateChooser addMembre_date_vmJDateChooser;
    private javax.swing.JTextField addMembre_emailJTextField;
    private javax.swing.JTextField addMembre_lieu_naissanceJTextField;
    private javax.swing.JTextField addMembre_loginJTextField;
    private javax.swing.JCheckBox addMembre_membre_actifJCheckBox;
    private javax.swing.JCheckBox addMembre_membre_inscritJCheckBox;
    private javax.swing.JTextField addMembre_nomJTextField;
    private javax.swing.JTextField addMembre_num_badgeJTextField;
    private javax.swing.JTextField addMembre_num_civilJTextField;
    private javax.swing.JTextField addMembre_num_qualifJTextField;
    private javax.swing.JTextField addMembre_numera_pplaJTextField;
    private javax.swing.JTextField addMembre_numero_bbJTextField;
    private javax.swing.JPasswordField addMembre_passwordJPasswordField;
    private javax.swing.JTextField addMembre_portableJTextField;
    private javax.swing.JTextField addMembre_prenomJTextField;
    private javax.swing.JTextField addMembre_professionJTextField;
    private javax.swing.JTextField addMembre_telJTextField;
    private com.toedter.calendar.JDateChooser addMembre_validite_seqJDateChooser;
    private com.toedter.calendar.JDateChooser addMembre_validite_vmJDateChooser;
    private javax.swing.JTextField addMembre_villeJTextField;
    private javax.swing.JLabel adresseJLabel;
    private javax.swing.JButton cancelMembreButton;
    private javax.swing.JLabel carte_federaleJLabel;
    private javax.swing.JLabel code_postalJLabel;
    private javax.swing.JLabel commentaireJLabel;
    private javax.swing.JLabel date_attestationJLabel;
    private javax.swing.JLabel date_bbJLabel;
    private javax.swing.JLabel date_entreeJLabel;
    private javax.swing.JLabel date_naissanceJLabel;
    private javax.swing.JLabel date_pplaJLabel;
    private javax.swing.JLabel date_seqJLabel;
    private javax.swing.JLabel date_theorique_bbJLabel;
    private javax.swing.JLabel date_theorique_pplaJLabel;
    private javax.swing.JLabel date_vmJLabel;
    private javax.swing.JButton deleteMembre;
    private javax.swing.JButton deleteMembreButton;
    private javax.swing.JDialog deleteMembreJDialog;
    private javax.swing.JLabel emailJLabel;
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
    private javax.swing.JLabel jLabel23;
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
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lieu_naissanceJLabel;
    private javax.swing.JLabel loginJLabel;
    private javax.swing.JLabel membre_actifJLabel;
    private javax.swing.JLabel membre_inscritJLabel;
    private javax.swing.JButton modifyMembre;
    private javax.swing.JButton modifyMembreButton;
    private javax.swing.JDialog modifyMembreJDialog;
    private javax.swing.JTextField modifyMembre_adresseJTextField;
    private javax.swing.JTextField modifyMembre_carte_federaleJTextField;
    private javax.swing.JTextField modifyMembre_code_postalJTextField;
    private javax.swing.JTextField modifyMembre_commentaireJTextField;
    private javax.swing.JTextField modifyMembre_date_attestationJTextField;
    private javax.swing.JTextField modifyMembre_date_bbJTextField;
    private javax.swing.JTextField modifyMembre_date_entreeJTextField;
    private javax.swing.JTextField modifyMembre_date_naissanceJTextField;
    private javax.swing.JTextField modifyMembre_date_pplaJTextField;
    private javax.swing.JTextField modifyMembre_date_seqJTextField;
    private javax.swing.JTextField modifyMembre_date_theorique_bbJTextField;
    private javax.swing.JTextField modifyMembre_date_theorique_pplaJTextField;
    private javax.swing.JTextField modifyMembre_date_vmJTextField;
    private javax.swing.JTextField modifyMembre_emailJTextField;
    private javax.swing.JTextField modifyMembre_lieu_naissanceJTextField;
    private javax.swing.JTextField modifyMembre_loginJTextField;
    private javax.swing.JCheckBox modifyMembre_membre_actifJCheckBox;
    private javax.swing.JCheckBox modifyMembre_membre_inscritJCheckBox;
    private javax.swing.JTextField modifyMembre_nomJTextField;
    private javax.swing.JTextField modifyMembre_num_badgeJTextField;
    private javax.swing.JTextField modifyMembre_num_civilJTextField;
    private javax.swing.JTextField modifyMembre_num_qualifJTextField;
    private javax.swing.JTextField modifyMembre_numera_pplaJTextField;
    private javax.swing.JTextField modifyMembre_numero_bbJTextField;
    private javax.swing.JPasswordField modifyMembre_passwordJPasswordField;
    private javax.swing.JTextField modifyMembre_portableJTextField;
    private javax.swing.JTextField modifyMembre_prenomJTextField;
    private javax.swing.JTextField modifyMembre_professionJTextField;
    private javax.swing.JTextField modifyMembre_telJTextField;
    private javax.swing.JTextField modifyMembre_validite_seqJTextField;
    private javax.swing.JTextField modifyMembre_validite_vmJTextField;
    private javax.swing.JTextField modifyMembre_villeJTextField;
    private javax.swing.JLabel nomJLabel;
    private javax.swing.JLabel num_badgeJLabel;
    private javax.swing.JLabel num_civilJLabel;
    private javax.swing.JLabel num_membreJLabel;
    private javax.swing.JLabel num_qualifJLabel;
    private javax.swing.JLabel numero_bbJLabel;
    private javax.swing.JLabel numero_pplaJLabel;
    private javax.swing.JLabel portableJLabel;
    private javax.swing.JLabel prenomJLabel;
    private javax.swing.JLabel professionJLabel;
    private javax.swing.JButton showMembre;
    private javax.swing.JDialog showMembreJDialog;
    private javax.swing.JLabel telJLabel;
    private javax.swing.JLabel validite_seqJLabel;
    private javax.swing.JLabel validite_vmJLabel;
    private javax.swing.JLabel villeJLabel;
    // End of variables declaration//GEN-END:variables
}
