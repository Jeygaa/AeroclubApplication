/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aeroclubjava;

import aeroclubjava.data.config;
import aeroclubjava.data.dataInstructeur;
import java.sql.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author stephen.bonvoisin
 */
public class Instructeur extends javax.swing.JInternalFrame {

    /**
     * Creates new form Membre
     */
    public Instructeur() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI ui =(BasicInternalFrameUI)this.getUI();
        ui.setNorthPane(null);
    }
    
    public void AffichageInstructeur() {
        Connection connection = null;
        
        try {
            
            config db = new config();
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://"+db.host+"/"+db.dbname, db.login, db.password);
            
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM instructeurs ORDER BY num_instructeur ASC;");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                String num_instructeur = rs.getString("num_instructeur");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                
                String data[] = {num_instructeur, nom, prenom};
                DefaultTableModel tblModel = (DefaultTableModel)InstructeurTable.getModel();
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
    
    public void openShowInstructeur() {
        
        int selectedRow = InstructeurTable.getSelectedRow();
        
        if(selectedRow != -1) {
            
            int num_instructeur = Integer.parseInt(String.valueOf(InstructeurTable.getValueAt(selectedRow, 0)));

            dataInstructeur instructeur = new dataInstructeur();
            instructeur.getInstructeur(num_instructeur);
            
            num_instructeurJLabel.setText(String.valueOf(instructeur.num_instructeur));
            nomJLabel.setText(String.valueOf(instructeur.nom));
            prenomJLabel.setText(String.valueOf(instructeur.prenom));
            num_civilJLabel.setText(String.valueOf(instructeur.num_civil));
            taux_instructeurJLabel.setText(String.valueOf(instructeur.taux_instructeur));
            adresseJLabel.setText(String.valueOf(instructeur.adresse));
            code_postalJLabel.setText(String.valueOf(instructeur.code_postal));
            villeJLabel.setText(String.valueOf(instructeur.ville));
            telJLabel.setText(String.valueOf(instructeur.tel));
            portableJLabel.setText(String.valueOf(instructeur.portable));
            faxJLabel.setText(String.valueOf(instructeur.fax));
            commentaireJLabel.setText(String.valueOf(instructeur.commentaire));
            num_badgeJLabel.setText(String.valueOf(instructeur.num_badge));
            emailJLabel.setText(String.valueOf(instructeur.email));

            showInstructeurJDialog.setVisible(true);
            
        }
    }

    public void openAddInstructeur() {
        
        addInstructeur_nomJTextField.setText("");
        addInstructeur_prenomJTextField.setText("");
        addInstructeur_num_civilJTextField.setText("");
        addInstructeur_taux_instructeurJTextField.setText("");
        addInstructeur_adresseJTextField.setText("");
        addInstructeur_code_postalJTextField.setText("");
        addInstructeur_villeJTextField.setText("");
        addInstructeur_telJTextField.setText("");
        addInstructeur_portableJTextField.setText("");
        addInstructeur_faxJTextField.setText("");
        addInstructeur_commentaireJTextField.setText("");
        addInstructeur_num_badgeJTextField.setText("");
        addInstructeur_emailJTextField.setText("");
        
        addInstructeurJDialog.setVisible(true);
        
    }
    
    public void addInstructeur() {
        
        dataInstructeur instructeur = new dataInstructeur();
        
        instructeur.nom = addInstructeur_nomJTextField.getText();
        instructeur.prenom = addInstructeur_prenomJTextField.getText();
        instructeur.num_civil = Integer.parseInt(addInstructeur_num_civilJTextField.getText());
        instructeur.taux_instructeur = Integer.parseInt(addInstructeur_taux_instructeurJTextField.getText());
        instructeur.adresse = addInstructeur_adresseJTextField.getText();
        instructeur.code_postal = addInstructeur_code_postalJTextField.getText();
        instructeur.ville = addInstructeur_villeJTextField.getText();
        instructeur.tel = addInstructeur_telJTextField.getText();
        instructeur.portable = addInstructeur_portableJTextField.getText();
        instructeur.fax = addInstructeur_faxJTextField.getText();
        instructeur.commentaire = addInstructeur_commentaireJTextField.getText();
        instructeur.num_badge = Integer.parseInt(addInstructeur_num_badgeJTextField.getText());
        instructeur.email = addInstructeur_emailJTextField.getText();
        
        instructeur.insertInstructeur();
        
        addInstructeurJDialog.dispose();
        
    }
    
    public void openModifyInstructeur() {
        
        int selectedRow = InstructeurTable.getSelectedRow();
        
        if(selectedRow != -1) {
            
            int num_instructeur = Integer.parseInt(String.valueOf(InstructeurTable.getValueAt(selectedRow, 0)));

            dataInstructeur instructeur = new dataInstructeur();
            
            instructeur.getInstructeur(num_instructeur);
            
            modifyInstructeur_num_civilJTextField.setText(String.valueOf(instructeur.num_civil));
            modifyInstructeur_taux_instructeurJTextField.setText(String.valueOf(instructeur.taux_instructeur));
            modifyInstructeur_adresseJTextField.setText(String.valueOf(instructeur.adresse));
            modifyInstructeur_code_postalJTextField.setText(String.valueOf(instructeur.code_postal));
            modifyInstructeur_villeJTextField.setText(String.valueOf(instructeur.ville));
            modifyInstructeur_telJTextField.setText(String.valueOf(instructeur.tel));
            modifyInstructeur_portableJTextField.setText(String.valueOf(instructeur.portable));
            modifyInstructeur_faxJTextField.setText(String.valueOf(instructeur.fax));
            modifyInstructeur_commentaireJTextField.setText(String.valueOf(instructeur.commentaire));
            modifyInstructeur_num_badgeJTextField.setText(String.valueOf(instructeur.num_badge));
            modifyInstructeur_emailJTextField.setText(String.valueOf(instructeur.email));
            
            modifyInstructeurJDialog.setVisible(true);
        
        }
    }
    
    public void modifyInstructeur() {
        
        dataInstructeur instructeur = new dataInstructeur();
        
        instructeur.num_civil = Integer.parseInt(String.valueOf(InstructeurTable.getValueAt(InstructeurTable.getSelectedRow(), 0)));
        instructeur.taux_instructeur = Integer.parseInt(modifyInstructeur_taux_instructeurJTextField.getText());
        instructeur.adresse = modifyInstructeur_adresseJTextField.getText();
        instructeur.code_postal = modifyInstructeur_code_postalJTextField.getText();
        instructeur.ville = modifyInstructeur_villeJTextField.getText();
        instructeur.tel = modifyInstructeur_telJTextField.getText();
        instructeur.portable = modifyInstructeur_portableJTextField.getText();
        instructeur.fax = modifyInstructeur_faxJTextField.getText();
        instructeur.commentaire = modifyInstructeur_commentaireJTextField.getText();
        instructeur.num_badge = Integer.parseInt(modifyInstructeur_num_badgeJTextField.getText());
        instructeur.email = modifyInstructeur_emailJTextField.getText();
        
        instructeur.modifyInstructeur();
        
        modifyInstructeurJDialog.dispose();
        
    }
    
    public void openDeleteInstructeur() {
        
        int selectedRow = InstructeurTable.getSelectedRow();
        
        if(selectedRow != -1) {
            
            deleteInstructeurJDialog.setVisible(true);
            
        }
    }
    
    public void deleteInstructeur() {
        
        dataInstructeur instructeur = new dataInstructeur();
        
        instructeur.num_instructeur = Integer.parseInt((String) InstructeurTable.getValueAt(InstructeurTable.getSelectedRow(), 0));
        
        instructeur.deleteInstructeur();
        
        deleteInstructeurJDialog.dispose();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        showInstructeurJDialog = new javax.swing.JDialog();
        num_instructeurJLabel = new javax.swing.JLabel();
        nomJLabel = new javax.swing.JLabel();
        prenomJLabel = new javax.swing.JLabel();
        num_civilJLabel = new javax.swing.JLabel();
        taux_instructeurJLabel = new javax.swing.JLabel();
        adresseJLabel = new javax.swing.JLabel();
        code_postalJLabel = new javax.swing.JLabel();
        villeJLabel = new javax.swing.JLabel();
        telJLabel = new javax.swing.JLabel();
        portableJLabel = new javax.swing.JLabel();
        faxJLabel = new javax.swing.JLabel();
        commentaireJLabel = new javax.swing.JLabel();
        num_badgeJLabel = new javax.swing.JLabel();
        emailJLabel = new javax.swing.JLabel();
        addInstructeurJDialog = new javax.swing.JDialog();
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
        addInstructeur_nomJTextField = new javax.swing.JTextField();
        addInstructeur_prenomJTextField = new javax.swing.JTextField();
        addInstructeur_num_civilJTextField = new javax.swing.JTextField();
        addInstructeur_taux_instructeurJTextField = new javax.swing.JTextField();
        addInstructeur_adresseJTextField = new javax.swing.JTextField();
        addInstructeur_code_postalJTextField = new javax.swing.JTextField();
        addInstructeur_villeJTextField = new javax.swing.JTextField();
        addInstructeur_telJTextField = new javax.swing.JTextField();
        addInstructeur_portableJTextField = new javax.swing.JTextField();
        addInstructeur_faxJTextField = new javax.swing.JTextField();
        addInstructeur_commentaireJTextField = new javax.swing.JTextField();
        addInstructeur_num_badgeJTextField = new javax.swing.JTextField();
        addInstructeur_emailJTextField = new javax.swing.JTextField();
        addInstructeurButton = new javax.swing.JButton();
        modifyInstructeurJDialog = new javax.swing.JDialog();
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
        modifyInstructeur_nomJTextField = new javax.swing.JTextField();
        modifyInstructeur_prenomJTextField = new javax.swing.JTextField();
        modifyInstructeur_num_civilJTextField = new javax.swing.JTextField();
        modifyInstructeur_taux_instructeurJTextField = new javax.swing.JTextField();
        modifyInstructeur_adresseJTextField = new javax.swing.JTextField();
        modifyInstructeur_code_postalJTextField = new javax.swing.JTextField();
        modifyInstructeur_villeJTextField = new javax.swing.JTextField();
        modifyInstructeur_telJTextField = new javax.swing.JTextField();
        modifyInstructeur_portableJTextField = new javax.swing.JTextField();
        modifyInstructeur_faxJTextField = new javax.swing.JTextField();
        modifyInstructeur_commentaireJTextField = new javax.swing.JTextField();
        modifyInstructeur_num_badgeJTextField = new javax.swing.JTextField();
        modifyInstructeur_emailJTextField = new javax.swing.JTextField();
        modifyInstructeurButton = new javax.swing.JButton();
        deleteInstructeurJDialog = new javax.swing.JDialog();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        InstructeurTable = new javax.swing.JTable();
        showInstucteur = new javax.swing.JButton();
        addInstructeur = new javax.swing.JButton();
        modifyInstructeur = new javax.swing.JButton();
        deleteInstructeur = new javax.swing.JButton();

        showInstructeurJDialog.setMinimumSize(new java.awt.Dimension(600, 600));

        num_instructeurJLabel.setText("jLabel2");

        nomJLabel.setText("jLabel3");

        prenomJLabel.setText("jLabel4");

        num_civilJLabel.setText("jLabel5");

        taux_instructeurJLabel.setText("jLabel6");

        adresseJLabel.setText("jLabel7");

        code_postalJLabel.setText("jLabel8");

        villeJLabel.setText("jLabel9");

        telJLabel.setText("jLabel10");

        portableJLabel.setText("jLabel11");

        faxJLabel.setText("jLabel12");

        commentaireJLabel.setText("jLabel13");

        num_badgeJLabel.setText("jLabel14");

        emailJLabel.setText("jLabel15");

        javax.swing.GroupLayout showInstructeurJDialogLayout = new javax.swing.GroupLayout(showInstructeurJDialog.getContentPane());
        showInstructeurJDialog.getContentPane().setLayout(showInstructeurJDialogLayout);
        showInstructeurJDialogLayout.setHorizontalGroup(
            showInstructeurJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(showInstructeurJDialogLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(showInstructeurJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(showInstructeurJDialogLayout.createSequentialGroup()
                        .addComponent(code_postalJLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 262, Short.MAX_VALUE)
                        .addComponent(emailJLabel))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, showInstructeurJDialogLayout.createSequentialGroup()
                        .addComponent(adresseJLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(num_badgeJLabel))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, showInstructeurJDialogLayout.createSequentialGroup()
                        .addComponent(taux_instructeurJLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(commentaireJLabel))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, showInstructeurJDialogLayout.createSequentialGroup()
                        .addComponent(num_civilJLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(faxJLabel))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, showInstructeurJDialogLayout.createSequentialGroup()
                        .addComponent(prenomJLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(portableJLabel))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, showInstructeurJDialogLayout.createSequentialGroup()
                        .addComponent(nomJLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(telJLabel))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, showInstructeurJDialogLayout.createSequentialGroup()
                        .addComponent(num_instructeurJLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(villeJLabel)))
                .addGap(29, 29, 29))
        );
        showInstructeurJDialogLayout.setVerticalGroup(
            showInstructeurJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(showInstructeurJDialogLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(showInstructeurJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(num_instructeurJLabel)
                    .addComponent(villeJLabel))
                .addGap(18, 18, 18)
                .addGroup(showInstructeurJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nomJLabel)
                    .addComponent(telJLabel))
                .addGap(18, 18, 18)
                .addGroup(showInstructeurJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(prenomJLabel)
                    .addComponent(portableJLabel))
                .addGap(18, 18, 18)
                .addGroup(showInstructeurJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(num_civilJLabel)
                    .addComponent(faxJLabel))
                .addGap(18, 18, 18)
                .addGroup(showInstructeurJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(taux_instructeurJLabel)
                    .addComponent(commentaireJLabel))
                .addGap(18, 18, 18)
                .addGroup(showInstructeurJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(adresseJLabel)
                    .addComponent(num_badgeJLabel))
                .addGap(18, 18, 18)
                .addGroup(showInstructeurJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(code_postalJLabel)
                    .addComponent(emailJLabel))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        addInstructeurJDialog.setMinimumSize(new java.awt.Dimension(600, 600));

        jLabel2.setText("nom");

        jLabel3.setText("prenom");

        jLabel4.setText("num_civil");

        jLabel5.setText("taux_instructeur");

        jLabel6.setText("adresse");

        jLabel7.setText("code_postal");

        jLabel8.setText("ville");

        jLabel9.setText("tel");

        jLabel10.setText("portable");

        jLabel11.setText("fax");

        jLabel12.setText("commentaire");

        jLabel13.setText("num_badge");

        jLabel14.setText("email");

        addInstructeur_nomJTextField.setText("jTextField1");

        addInstructeur_prenomJTextField.setText("jTextField2");

        addInstructeur_num_civilJTextField.setText("jTextField3");

        addInstructeur_taux_instructeurJTextField.setText("jTextField4");

        addInstructeur_adresseJTextField.setText("jTextField5");

        addInstructeur_code_postalJTextField.setText("jTextField6");

        addInstructeur_villeJTextField.setText("jTextField7");

        addInstructeur_telJTextField.setText("jTextField8");

        addInstructeur_portableJTextField.setText("jTextField9");

        addInstructeur_faxJTextField.setText("jTextField10");

        addInstructeur_commentaireJTextField.setText("jTextField11");

        addInstructeur_num_badgeJTextField.setText("jTextField12");

        addInstructeur_emailJTextField.setText("jTextField13");

        addInstructeurButton.setText("Ajouter");
        addInstructeurButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addInstructeurButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout addInstructeurJDialogLayout = new javax.swing.GroupLayout(addInstructeurJDialog.getContentPane());
        addInstructeurJDialog.getContentPane().setLayout(addInstructeurJDialogLayout);
        addInstructeurJDialogLayout.setHorizontalGroup(
            addInstructeurJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addInstructeurJDialogLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(addInstructeurJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addInstructeurJDialogLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(addInstructeur_villeJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(addInstructeurJDialogLayout.createSequentialGroup()
                        .addGroup(addInstructeurJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(addInstructeurJDialogLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(addInstructeur_prenomJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(addInstructeurJDialogLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(addInstructeur_num_civilJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(addInstructeurJDialogLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(addInstructeur_taux_instructeurJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(addInstructeurJDialogLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(addInstructeur_code_postalJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(addInstructeurJDialogLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(addInstructeur_nomJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(addInstructeurJDialogLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(addInstructeur_adresseJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(69, 69, 69)
                        .addGroup(addInstructeurJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(addInstructeurJDialogLayout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(addInstructeur_emailJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(addInstructeurJDialogLayout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(addInstructeur_num_badgeJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(addInstructeurJDialogLayout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(addInstructeur_commentaireJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(addInstructeurJDialogLayout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(addInstructeur_faxJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(addInstructeurJDialogLayout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(addInstructeur_portableJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(addInstructeurJDialogLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(addInstructeur_telJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addInstructeurJDialogLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addInstructeurButton)
                .addGap(22, 22, 22))
        );
        addInstructeurJDialogLayout.setVerticalGroup(
            addInstructeurJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addInstructeurJDialogLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(addInstructeurJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel9)
                    .addComponent(addInstructeur_nomJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addInstructeur_telJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addInstructeurJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel10)
                    .addComponent(addInstructeur_prenomJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addInstructeur_portableJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addInstructeurJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel11)
                    .addComponent(addInstructeur_num_civilJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addInstructeur_faxJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addInstructeurJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel12)
                    .addComponent(addInstructeur_taux_instructeurJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addInstructeur_commentaireJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addInstructeurJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel13)
                    .addComponent(addInstructeur_adresseJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addInstructeur_num_badgeJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addInstructeurJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel14)
                    .addComponent(addInstructeur_code_postalJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addInstructeur_emailJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addInstructeurJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(addInstructeur_villeJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(addInstructeurButton)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        modifyInstructeurJDialog.setMinimumSize(new java.awt.Dimension(600, 600));

        jLabel15.setText("nom");

        jLabel16.setText("prenom");

        jLabel17.setText("num_civil");

        jLabel18.setText("taux_instructeur");

        jLabel19.setText("adresse");

        jLabel20.setText("code_postal");

        jLabel21.setText("ville");

        jLabel22.setText("tel");

        jLabel23.setText("portable");

        jLabel24.setText("fax");

        jLabel25.setText("commentaire");

        jLabel26.setText("num_badge");

        jLabel27.setText("email");

        modifyInstructeur_nomJTextField.setText("jTextField1");

        modifyInstructeur_prenomJTextField.setText("jTextField2");

        modifyInstructeur_num_civilJTextField.setText("jTextField3");

        modifyInstructeur_taux_instructeurJTextField.setText("jTextField4");

        modifyInstructeur_adresseJTextField.setText("jTextField5");

        modifyInstructeur_code_postalJTextField.setText("jTextField6");

        modifyInstructeur_villeJTextField.setText("jTextField7");

        modifyInstructeur_telJTextField.setText("jTextField8");

        modifyInstructeur_portableJTextField.setText("jTextField9");

        modifyInstructeur_faxJTextField.setText("jTextField10");

        modifyInstructeur_commentaireJTextField.setText("jTextField11");

        modifyInstructeur_num_badgeJTextField.setText("jTextField12");

        modifyInstructeur_emailJTextField.setText("jTextField13");

        modifyInstructeurButton.setText("Modifier");
        modifyInstructeurButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyInstructeurButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout modifyInstructeurJDialogLayout = new javax.swing.GroupLayout(modifyInstructeurJDialog.getContentPane());
        modifyInstructeurJDialog.getContentPane().setLayout(modifyInstructeurJDialogLayout);
        modifyInstructeurJDialogLayout.setHorizontalGroup(
            modifyInstructeurJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modifyInstructeurJDialogLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(modifyInstructeurJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(modifyInstructeurJDialogLayout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(modifyInstructeur_villeJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(modifyInstructeurJDialogLayout.createSequentialGroup()
                        .addGroup(modifyInstructeurJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(modifyInstructeurJDialogLayout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(modifyInstructeur_prenomJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(modifyInstructeurJDialogLayout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(modifyInstructeur_num_civilJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(modifyInstructeurJDialogLayout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(modifyInstructeur_taux_instructeurJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(modifyInstructeurJDialogLayout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(modifyInstructeur_code_postalJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(modifyInstructeurJDialogLayout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(modifyInstructeur_nomJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(modifyInstructeurJDialogLayout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(modifyInstructeur_adresseJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(69, 69, 69)
                        .addGroup(modifyInstructeurJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(modifyInstructeurJDialogLayout.createSequentialGroup()
                                .addComponent(jLabel27)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(modifyInstructeur_emailJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(modifyInstructeurJDialogLayout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(modifyInstructeur_num_badgeJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(modifyInstructeurJDialogLayout.createSequentialGroup()
                                .addComponent(jLabel25)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(modifyInstructeur_commentaireJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(modifyInstructeurJDialogLayout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(modifyInstructeur_faxJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(modifyInstructeurJDialogLayout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(modifyInstructeur_portableJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(modifyInstructeurJDialogLayout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(modifyInstructeur_telJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, modifyInstructeurJDialogLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(modifyInstructeurButton)
                .addGap(22, 22, 22))
        );
        modifyInstructeurJDialogLayout.setVerticalGroup(
            modifyInstructeurJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modifyInstructeurJDialogLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(modifyInstructeurJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel22)
                    .addComponent(modifyInstructeur_nomJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modifyInstructeur_telJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(modifyInstructeurJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel23)
                    .addComponent(modifyInstructeur_prenomJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modifyInstructeur_portableJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(modifyInstructeurJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel24)
                    .addComponent(modifyInstructeur_num_civilJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modifyInstructeur_faxJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(modifyInstructeurJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel25)
                    .addComponent(modifyInstructeur_taux_instructeurJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modifyInstructeur_commentaireJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(modifyInstructeurJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jLabel26)
                    .addComponent(modifyInstructeur_adresseJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modifyInstructeur_num_badgeJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(modifyInstructeurJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jLabel27)
                    .addComponent(modifyInstructeur_code_postalJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modifyInstructeur_emailJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(modifyInstructeurJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(modifyInstructeur_villeJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(modifyInstructeurButton)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        deleteInstructeurJDialog.setMinimumSize(new java.awt.Dimension(600, 600));

        jButton1.setText("Supprimer");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Annuler");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout deleteInstructeurJDialogLayout = new javax.swing.GroupLayout(deleteInstructeurJDialog.getContentPane());
        deleteInstructeurJDialog.getContentPane().setLayout(deleteInstructeurJDialogLayout);
        deleteInstructeurJDialogLayout.setHorizontalGroup(
            deleteInstructeurJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(deleteInstructeurJDialogLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 132, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(55, 55, 55))
        );
        deleteInstructeurJDialogLayout.setVerticalGroup(
            deleteInstructeurJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(deleteInstructeurJDialogLayout.createSequentialGroup()
                .addGap(137, 137, 137)
                .addGroup(deleteInstructeurJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(140, Short.MAX_VALUE))
        );

        setPreferredSize(new java.awt.Dimension(900, 800));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Instructeur");

        InstructeurTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nom", "Prenom"
            }
        ));
        jScrollPane1.setViewportView(InstructeurTable);

        showInstucteur.setText("Afficher");
        showInstucteur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showInstucteurActionPerformed(evt);
            }
        });

        addInstructeur.setText("Ajouter");
        addInstructeur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addInstructeurActionPerformed(evt);
            }
        });

        modifyInstructeur.setText("Modifier");
        modifyInstructeur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyInstructeurActionPerformed(evt);
            }
        });

        deleteInstructeur.setText("Supprimer");
        deleteInstructeur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteInstructeurActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(156, 156, 156)
                .addComponent(showInstucteur, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(addInstructeur, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(modifyInstructeur, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(deleteInstructeur, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(153, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(showInstucteur, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addInstructeur, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modifyInstructeur, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteInstructeur, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void deleteInstructeurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteInstructeurActionPerformed
        // TODO add your handling code here:
        openDeleteInstructeur();
    }//GEN-LAST:event_deleteInstructeurActionPerformed

    private void modifyInstructeurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifyInstructeurActionPerformed
        // TODO add your handling code here:
        openModifyInstructeur();
    }//GEN-LAST:event_modifyInstructeurActionPerformed

    private void addInstructeurButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addInstructeurButtonActionPerformed
        // TODO add your handling code here:
        addInstructeur();
    }//GEN-LAST:event_addInstructeurButtonActionPerformed

    private void modifyInstructeurButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifyInstructeurButtonActionPerformed
        // TODO add your handling code here:
        modifyInstructeur();
    }//GEN-LAST:event_modifyInstructeurButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        deleteInstructeur();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        deleteInstructeurJDialog.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void showInstucteurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showInstucteurActionPerformed
        // TODO add your handling code here:
        openShowInstructeur();
    }//GEN-LAST:event_showInstucteurActionPerformed

    private void addInstructeurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addInstructeurActionPerformed
        // TODO add your handling code here:
        openAddInstructeur();
    }//GEN-LAST:event_addInstructeurActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable InstructeurTable;
    private javax.swing.JButton addInstructeur;
    private javax.swing.JButton addInstructeurButton;
    private javax.swing.JDialog addInstructeurJDialog;
    private javax.swing.JTextField addInstructeur_adresseJTextField;
    private javax.swing.JTextField addInstructeur_code_postalJTextField;
    private javax.swing.JTextField addInstructeur_commentaireJTextField;
    private javax.swing.JTextField addInstructeur_emailJTextField;
    private javax.swing.JTextField addInstructeur_faxJTextField;
    private javax.swing.JTextField addInstructeur_nomJTextField;
    private javax.swing.JTextField addInstructeur_num_badgeJTextField;
    private javax.swing.JTextField addInstructeur_num_civilJTextField;
    private javax.swing.JTextField addInstructeur_portableJTextField;
    private javax.swing.JTextField addInstructeur_prenomJTextField;
    private javax.swing.JTextField addInstructeur_taux_instructeurJTextField;
    private javax.swing.JTextField addInstructeur_telJTextField;
    private javax.swing.JTextField addInstructeur_villeJTextField;
    private javax.swing.JLabel adresseJLabel;
    private javax.swing.JLabel code_postalJLabel;
    private javax.swing.JLabel commentaireJLabel;
    private javax.swing.JButton deleteInstructeur;
    private javax.swing.JDialog deleteInstructeurJDialog;
    private javax.swing.JLabel emailJLabel;
    private javax.swing.JLabel faxJLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton modifyInstructeur;
    private javax.swing.JButton modifyInstructeurButton;
    private javax.swing.JDialog modifyInstructeurJDialog;
    private javax.swing.JTextField modifyInstructeur_adresseJTextField;
    private javax.swing.JTextField modifyInstructeur_code_postalJTextField;
    private javax.swing.JTextField modifyInstructeur_commentaireJTextField;
    private javax.swing.JTextField modifyInstructeur_emailJTextField;
    private javax.swing.JTextField modifyInstructeur_faxJTextField;
    private javax.swing.JTextField modifyInstructeur_nomJTextField;
    private javax.swing.JTextField modifyInstructeur_num_badgeJTextField;
    private javax.swing.JTextField modifyInstructeur_num_civilJTextField;
    private javax.swing.JTextField modifyInstructeur_portableJTextField;
    private javax.swing.JTextField modifyInstructeur_prenomJTextField;
    private javax.swing.JTextField modifyInstructeur_taux_instructeurJTextField;
    private javax.swing.JTextField modifyInstructeur_telJTextField;
    private javax.swing.JTextField modifyInstructeur_villeJTextField;
    private javax.swing.JLabel nomJLabel;
    private javax.swing.JLabel num_badgeJLabel;
    private javax.swing.JLabel num_civilJLabel;
    private javax.swing.JLabel num_instructeurJLabel;
    private javax.swing.JLabel portableJLabel;
    private javax.swing.JLabel prenomJLabel;
    private javax.swing.JDialog showInstructeurJDialog;
    private javax.swing.JButton showInstucteur;
    private javax.swing.JLabel taux_instructeurJLabel;
    private javax.swing.JLabel telJLabel;
    private javax.swing.JLabel villeJLabel;
    // End of variables declaration//GEN-END:variables
}
