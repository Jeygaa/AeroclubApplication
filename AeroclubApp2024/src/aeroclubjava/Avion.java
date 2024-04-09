/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aeroclubjava;

import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.sql.*;
import aeroclubjava.data.config;
import aeroclubjava.data.dataAvion;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author stephen.bonvoisin
 */
public class Avion extends javax.swing.JInternalFrame {

    /**
     * Creates new form Membre
     */
    public Avion() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI ui =(BasicInternalFrameUI)this.getUI();
        ui.setNorthPane(null);
    }
    
    public void AffichageAvion() {
        Connection connection = null;
        
        try {
            
            config db = new config();
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://"+db.host+"/"+db.dbname, db.login, db.password);
            
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM avions ORDER BY num_avion ASC;");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                String num_avion = rs.getString("num_avion");
                String type_avion = rs.getString("type");
                String immatriculation = rs.getString("immatriculation");
                
                String data[] = {num_avion, type_avion, immatriculation};
                DefaultTableModel tblModel = (DefaultTableModel)AvionTable.getModel();
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
    
    public void openShowAvion() {
        
        int selectedRow = AvionTable.getSelectedRow();
        
        if(selectedRow != -1) {
            
            int num_avion = Integer.parseInt(String.valueOf(AvionTable.getValueAt(selectedRow, 0)));

            dataAvion avion = new dataAvion();
            avion.getAvion(num_avion);
            
            num_avionJLabel.setText(String.valueOf(avion.num_avion));
            type_avionJLabel.setText(String.valueOf(avion.type_avion));
            tauxJLabel.setText(String.valueOf(avion.taux));
            forfait1JLabel.setText(String.valueOf(avion.forfait1));
            forfait2JLabel.setText(String.valueOf(avion.forfait2));
            forfait3JLabel.setText(String.valueOf(avion.forfait3));
            heures_forfait1JLabel.setText(String.valueOf(avion.heures_forfait1));
            heures_forfait2JLabel.setText(String.valueOf(avion.heures_forfait2));
            heures_forfait3JLabel.setText(String.valueOf(avion.heures_forfait3));
            reduction_semaineJLabel.setText(String.valueOf(avion.reduction_semaine));
            immatriculationJLabel.setText(String.valueOf(avion.immatriculation));
            descriptionJLabel.setText(String.valueOf(avion.description));

            showAvionJDialog.setVisible(true);
            
        }
    }
    
    public void openAddAvion() {
        
        addAvion_type_avionJTextField.setText("");
        addAvion_tauxJTextField.setText("");
        addAvion_forfait1JTextField.setText("");
        addAvion_forfait2JTextField.setText("");
        addAvion_forfait3JTextField.setText("");
        addAvion_heures_forfait1JTextField.setText("");
        addAvion_heures_forfait2JTextField.setText("");
        addAvion_heures_forfait3JTextField.setText("");
        addAvion_reduction_semaineJTextField.setText("");
        addAvion_immatriculationJTextField.setText("");
        addAvion_descriptionJTextField.setText("");
        
        addAvionJDialog.setVisible(true);
        
    }
    
    public void addAvion() {
        
        dataAvion avion = new dataAvion();
        
        avion.type_avion = addAvion_type_avionJTextField.getText();
        avion.taux = Integer.parseInt(addAvion_tauxJTextField.getText());
        avion.forfait1 = Integer.parseInt(addAvion_forfait1JTextField.getText());
        avion.forfait2 = Integer.parseInt(addAvion_forfait2JTextField.getText());
        avion.forfait3 = Integer.parseInt(addAvion_forfait3JTextField.getText());
        avion.heures_forfait1 = Integer.parseInt(addAvion_heures_forfait1JTextField.getText());
        avion.heures_forfait2 = Integer.parseInt(addAvion_heures_forfait2JTextField.getText());
        avion.heures_forfait3 = Integer.parseInt(addAvion_heures_forfait3JTextField.getText());
        avion.reduction_semaine = Integer.parseInt(addAvion_reduction_semaineJTextField.getText());
        avion.immatriculation = addAvion_immatriculationJTextField.getText();
        avion.description = addAvion_descriptionJTextField.getText();
        
        avion.insertAvion();
        
        addAvionJDialog.dispose();
        
    }
    
    public void openModifyAvion() {
        
        int selectedRow = AvionTable.getSelectedRow();
        
        if(selectedRow != -1) {
            
            int num_avion = Integer.parseInt(String.valueOf(AvionTable.getValueAt(selectedRow, 0)));

            dataAvion avion = new dataAvion();
            
            avion.getAvion(num_avion);
            
            modifyAvion_type_avionJTextField.setText(String.valueOf(avion.type_avion));
            modifyAvion_tauxJTextField.setText(String.valueOf(avion.taux));
            modifyAvion_forfait1JTextField.setText(String.valueOf(avion.forfait1));
            modifyAvion_forfait2JTextField.setText(String.valueOf(avion.forfait2));
            modifyAvion_forfait3JTextField.setText(String.valueOf(avion.forfait3));
            modifyAvion_heures_forfait1JTextField.setText(String.valueOf(avion.heures_forfait1));
            modifyAvion_heures_forfait2JTextField.setText(String.valueOf(avion.heures_forfait2));
            modifyAvion_heures_forfait3JTextField.setText(String.valueOf(avion.heures_forfait3));
            modifyAvion_reduction_semaineJTextField.setText(String.valueOf(avion.reduction_semaine));
            modifyAvion_immatriculationJTextField.setText(String.valueOf(avion.immatriculation));
            modifyAvion_descriptionJTextField.setText(String.valueOf(avion.description));
            
            modifyAvionJDialog.setVisible(true);
        
        }
        
    }
    
    public void modifyAvion() {
        
        dataAvion avion = new dataAvion();
        
        avion.num_avion = Integer.parseInt(String.valueOf(AvionTable.getValueAt(AvionTable.getSelectedRow(), 0)));
        avion.type_avion = modifyAvion_type_avionJTextField.getText();
        avion.taux = Integer.parseInt(modifyAvion_tauxJTextField.getText());
        avion.forfait1 = Integer.parseInt(modifyAvion_forfait1JTextField.getText());
        avion.forfait2 = Integer.parseInt(modifyAvion_forfait2JTextField.getText());
        avion.forfait3 = Integer.parseInt(modifyAvion_forfait3JTextField.getText());
        avion.heures_forfait1 = Integer.parseInt(modifyAvion_heures_forfait1JTextField.getText());
        avion.heures_forfait2 = Integer.parseInt(modifyAvion_heures_forfait2JTextField.getText());
        avion.heures_forfait3 = Integer.parseInt(modifyAvion_heures_forfait3JTextField.getText());
        avion.reduction_semaine = Integer.parseInt(modifyAvion_reduction_semaineJTextField.getText());
        avion.immatriculation = modifyAvion_immatriculationJTextField.getText();
        avion.description = modifyAvion_descriptionJTextField.getText();
        
        avion.modifyAvion();
        
        modifyAvionJDialog.dispose();
        
    }
    
    public void openDeleteAvion() {
        
        int selectedRow = AvionTable.getSelectedRow();
        
        if(selectedRow != -1) {
            
            deleteAvionJDialog.setVisible(true);
            
        }
    }
    
    public void deleteAvion() {
        
        dataAvion avion = new dataAvion();
        
        avion.num_avion = Integer.parseInt((String) AvionTable.getValueAt(AvionTable.getSelectedRow(), 0));
        
        avion.deleteAvion();
        
        deleteAvionJDialog.dispose();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        showAvionJDialog = new javax.swing.JDialog();
        num_avionJLabel = new javax.swing.JLabel();
        type_avionJLabel = new javax.swing.JLabel();
        tauxJLabel = new javax.swing.JLabel();
        forfait1JLabel = new javax.swing.JLabel();
        forfait2JLabel = new javax.swing.JLabel();
        forfait3JLabel = new javax.swing.JLabel();
        heures_forfait1JLabel = new javax.swing.JLabel();
        heures_forfait2JLabel = new javax.swing.JLabel();
        heures_forfait3JLabel = new javax.swing.JLabel();
        reduction_semaineJLabel = new javax.swing.JLabel();
        immatriculationJLabel = new javax.swing.JLabel();
        descriptionJLabel = new javax.swing.JLabel();
        addAvionJDialog = new javax.swing.JDialog();
        addAvion_type_avionJLabel = new javax.swing.JLabel();
        addAvion_type_avionJTextField = new javax.swing.JTextField();
        addAvion_tauxJLabel = new javax.swing.JLabel();
        addAvion_tauxJTextField = new javax.swing.JTextField();
        addAvion_forfait1JLabel = new javax.swing.JLabel();
        addAvion_forfait1JTextField = new javax.swing.JTextField();
        addAvion_forfait2JLabel = new javax.swing.JLabel();
        addAvion_forfait2JTextField = new javax.swing.JTextField();
        addAvion_forfait3JLabel = new javax.swing.JLabel();
        addAvion_forfait3JTextField = new javax.swing.JTextField();
        addAvion_heures_forfait1JLabel = new javax.swing.JLabel();
        addAvion_heures_forfait1JTextField = new javax.swing.JTextField();
        addAvion_heures_forfait2JLabel = new javax.swing.JLabel();
        addAvion_heures_forfait2JTextField = new javax.swing.JTextField();
        addAvion_heures_forfait3JLabel = new javax.swing.JLabel();
        addAvion_heures_forfait3JTextField = new javax.swing.JTextField();
        addAvion_reduction_semaineJTextField = new javax.swing.JTextField();
        addAvion_reduction_semaineJLabel = new javax.swing.JLabel();
        addAvion_immatriculationJTextField = new javax.swing.JTextField();
        addAvion_immatriculationJLabel = new javax.swing.JLabel();
        addAvion_descriptionJTextField = new javax.swing.JTextField();
        addAvion_descriptionJLabel = new javax.swing.JLabel();
        addAvion_Button = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        modifyAvionJDialog = new javax.swing.JDialog();
        modifyAvion_type_avionJLabel = new javax.swing.JLabel();
        modifyAvion_type_avionJTextField = new javax.swing.JTextField();
        modifyAvion_tauxJLabel = new javax.swing.JLabel();
        modifyAvion_tauxJTextField = new javax.swing.JTextField();
        modifyAvion_forfait1JLabel = new javax.swing.JLabel();
        modifyAvion_forfait1JTextField = new javax.swing.JTextField();
        modifyAvion_forfait2JLabel = new javax.swing.JLabel();
        modifyAvion_forfait2JTextField = new javax.swing.JTextField();
        modifyAvion_forfait3JLabel = new javax.swing.JLabel();
        modifyAvion_forfait3JTextField = new javax.swing.JTextField();
        modifyAvion_heures_forfait1JLabel = new javax.swing.JLabel();
        modifyAvion_heures_forfait1JTextField = new javax.swing.JTextField();
        modifyAvion_heures_forfait2JLabel = new javax.swing.JLabel();
        modifyAvion_heures_forfait2JTextField = new javax.swing.JTextField();
        modifyAvion_heures_forfait3JLabel = new javax.swing.JLabel();
        modifyAvion_heures_forfait3JTextField = new javax.swing.JTextField();
        modifyAvion_reduction_semaineJTextField = new javax.swing.JTextField();
        modifyAvion_reduction_semaineJLabel = new javax.swing.JLabel();
        modifyAvion_immatriculationJTextField = new javax.swing.JTextField();
        modifyAvion_immatriculationJLabel = new javax.swing.JLabel();
        modifyAvion_descriptionJTextField = new javax.swing.JTextField();
        modifyAvion_descriptionJLabel = new javax.swing.JLabel();
        modifyAvionButton = new javax.swing.JButton();
        deleteAvionJDialog = new javax.swing.JDialog();
        submitAvionButton = new javax.swing.JButton();
        cancelAvionButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        AvionTable = new javax.swing.JTable();
        AddAvion = new javax.swing.JButton();
        ModifyAvion = new javax.swing.JButton();
        ShowAvion = new javax.swing.JButton();
        DeleteAvion = new javax.swing.JButton();

        showAvionJDialog.setMinimumSize(new java.awt.Dimension(600, 600));

        num_avionJLabel.setText("jLabel2");

        type_avionJLabel.setText("jLabel3");

        tauxJLabel.setText("jLabel4");

        forfait1JLabel.setText("jLabel5");

        forfait2JLabel.setText("jLabel6");

        forfait3JLabel.setText("jLabel7");

        heures_forfait1JLabel.setText("jLabel8");

        heures_forfait2JLabel.setText("jLabel9");

        heures_forfait3JLabel.setText("jLabel10");

        reduction_semaineJLabel.setText("jLabel11");

        immatriculationJLabel.setText("jLabel12");

        descriptionJLabel.setText("jLabel13");

        javax.swing.GroupLayout showAvionJDialogLayout = new javax.swing.GroupLayout(showAvionJDialog.getContentPane());
        showAvionJDialog.getContentPane().setLayout(showAvionJDialogLayout);
        showAvionJDialogLayout.setHorizontalGroup(
            showAvionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(showAvionJDialogLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(showAvionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, showAvionJDialogLayout.createSequentialGroup()
                        .addComponent(num_avionJLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(heures_forfait1JLabel))
                    .addGroup(showAvionJDialogLayout.createSequentialGroup()
                        .addComponent(forfait3JLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 252, Short.MAX_VALUE)
                        .addComponent(descriptionJLabel))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, showAvionJDialogLayout.createSequentialGroup()
                        .addComponent(type_avionJLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(heures_forfait2JLabel))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, showAvionJDialogLayout.createSequentialGroup()
                        .addComponent(tauxJLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(heures_forfait3JLabel))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, showAvionJDialogLayout.createSequentialGroup()
                        .addComponent(forfait1JLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(reduction_semaineJLabel))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, showAvionJDialogLayout.createSequentialGroup()
                        .addComponent(forfait2JLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(immatriculationJLabel)))
                .addGap(34, 34, 34))
        );
        showAvionJDialogLayout.setVerticalGroup(
            showAvionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(showAvionJDialogLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(showAvionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(num_avionJLabel)
                    .addComponent(heures_forfait1JLabel))
                .addGap(18, 18, 18)
                .addGroup(showAvionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(type_avionJLabel)
                    .addComponent(heures_forfait2JLabel))
                .addGap(18, 18, 18)
                .addGroup(showAvionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(heures_forfait3JLabel)
                    .addComponent(tauxJLabel))
                .addGap(18, 18, 18)
                .addGroup(showAvionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(reduction_semaineJLabel)
                    .addComponent(forfait1JLabel))
                .addGap(18, 18, 18)
                .addGroup(showAvionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(immatriculationJLabel)
                    .addComponent(forfait2JLabel))
                .addGap(18, 18, 18)
                .addGroup(showAvionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(forfait3JLabel)
                    .addComponent(descriptionJLabel))
                .addContainerGap(90, Short.MAX_VALUE))
        );

        addAvionJDialog.setMinimumSize(new java.awt.Dimension(600, 600));

        addAvion_type_avionJLabel.setText("Type");

        addAvion_type_avionJTextField.setMinimumSize(new java.awt.Dimension(65, 22));
        addAvion_type_avionJTextField.setPreferredSize(new java.awt.Dimension(100, 20));
        addAvion_type_avionJTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addAvion_type_avionJTextFieldActionPerformed(evt);
            }
        });

        addAvion_tauxJLabel.setText("Taux");

        addAvion_tauxJTextField.setPreferredSize(new java.awt.Dimension(100, 20));
        addAvion_tauxJTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addAvion_tauxJTextFieldActionPerformed(evt);
            }
        });

        addAvion_forfait1JLabel.setText("Forfait1");

        addAvion_forfait1JTextField.setPreferredSize(new java.awt.Dimension(100, 20));

        addAvion_forfait2JLabel.setText("Forfait2");

        addAvion_forfait2JTextField.setPreferredSize(new java.awt.Dimension(100, 20));

        addAvion_forfait3JLabel.setText("Forfait3");

        addAvion_forfait3JTextField.setPreferredSize(new java.awt.Dimension(100, 20));

        addAvion_heures_forfait1JLabel.setText("Heures_Forfait1");

        addAvion_heures_forfait1JTextField.setPreferredSize(new java.awt.Dimension(100, 20));

        addAvion_heures_forfait2JLabel.setText("Heures_Forfait2");

        addAvion_heures_forfait2JTextField.setMinimumSize(new java.awt.Dimension(100, 20));
        addAvion_heures_forfait2JTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addAvion_heures_forfait2JTextFieldActionPerformed(evt);
            }
        });

        addAvion_heures_forfait3JLabel.setText("Heures_Forfait3");

        addAvion_heures_forfait3JTextField.setPreferredSize(new java.awt.Dimension(100, 20));

        addAvion_reduction_semaineJTextField.setMinimumSize(new java.awt.Dimension(100, 20));

        addAvion_reduction_semaineJLabel.setText("Reduction_Semaine");

        addAvion_immatriculationJTextField.setPreferredSize(new java.awt.Dimension(100, 20));

        addAvion_immatriculationJLabel.setText("Immatriculation");

        addAvion_descriptionJTextField.setPreferredSize(new java.awt.Dimension(100, 20));

        addAvion_descriptionJLabel.setText("Description");

        addAvion_Button.setText("Ajouter");
        addAvion_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addAvion_ButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout addAvionJDialogLayout = new javax.swing.GroupLayout(addAvionJDialog.getContentPane());
        addAvionJDialog.getContentPane().setLayout(addAvionJDialogLayout);
        addAvionJDialogLayout.setHorizontalGroup(
            addAvionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addAvionJDialogLayout.createSequentialGroup()
                .addGroup(addAvionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(addAvionJDialogLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addAvion_Button))
                    .addGroup(addAvionJDialogLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(addAvionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(addAvionJDialogLayout.createSequentialGroup()
                                .addGroup(addAvionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(addAvionJDialogLayout.createSequentialGroup()
                                        .addGroup(addAvionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(addAvionJDialogLayout.createSequentialGroup()
                                                .addComponent(addAvion_heures_forfait1JLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(addAvion_heures_forfait1JTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(addAvionJDialogLayout.createSequentialGroup()
                                                .addComponent(addAvion_forfait3JLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(addAvion_forfait3JTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(addAvionJDialogLayout.createSequentialGroup()
                                                .addComponent(addAvion_forfait1JLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(addAvion_forfait1JTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(addAvionJDialogLayout.createSequentialGroup()
                                                .addComponent(addAvion_tauxJLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(addAvion_tauxJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(addAvionJDialogLayout.createSequentialGroup()
                                        .addComponent(addAvion_type_avionJLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(addAvion_type_avionJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGroup(addAvionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(addAvion_heures_forfait3JLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(addAvion_heures_forfait2JLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(addAvion_reduction_semaineJLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(addAvion_descriptionJLabel, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(addAvionJDialogLayout.createSequentialGroup()
                                .addComponent(addAvion_forfait2JLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(addAvion_forfait2JTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(addAvion_immatriculationJLabel)))
                        .addGap(12, 12, 12)
                        .addGroup(addAvionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(addAvion_immatriculationJTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(addAvion_heures_forfait2JTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(addAvion_heures_forfait3JTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(addAvion_reduction_semaineJTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(addAvion_descriptionJTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(27, 27, 27))
        );
        addAvionJDialogLayout.setVerticalGroup(
            addAvionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addAvionJDialogLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(addAvionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addAvion_type_avionJLabel)
                    .addComponent(addAvion_type_avionJTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addAvion_heures_forfait2JLabel)
                    .addComponent(addAvion_heures_forfait2JTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(addAvionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addAvionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(addAvion_tauxJLabel)
                        .addComponent(addAvion_tauxJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(addAvionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(addAvion_heures_forfait3JLabel)
                        .addComponent(addAvion_heures_forfait3JTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(addAvionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addAvion_forfait1JLabel)
                    .addComponent(addAvion_forfait1JTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addAvion_reduction_semaineJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addAvion_reduction_semaineJLabel))
                .addGap(18, 18, 18)
                .addGroup(addAvionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addAvion_forfait2JLabel)
                    .addComponent(addAvion_forfait2JTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addAvion_immatriculationJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addAvion_immatriculationJLabel))
                .addGap(18, 18, 18)
                .addGroup(addAvionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addAvion_forfait3JLabel)
                    .addComponent(addAvion_forfait3JTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addAvion_descriptionJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addAvion_descriptionJLabel))
                .addGap(18, 18, 18)
                .addGroup(addAvionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addAvion_heures_forfait1JLabel)
                    .addComponent(addAvion_heures_forfait1JTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addComponent(addAvion_Button)
                .addGap(21, 21, 21))
        );

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        modifyAvionJDialog.setMinimumSize(new java.awt.Dimension(600, 600));

        modifyAvion_type_avionJLabel.setText("Type");

        modifyAvion_type_avionJTextField.setPreferredSize(new java.awt.Dimension(100, 20));
        modifyAvion_type_avionJTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyAvion_type_avionJTextFieldActionPerformed(evt);
            }
        });

        modifyAvion_tauxJLabel.setText("Taux");

        modifyAvion_tauxJTextField.setPreferredSize(new java.awt.Dimension(100, 20));

        modifyAvion_forfait1JLabel.setText("Forfait1");

        modifyAvion_forfait1JTextField.setPreferredSize(new java.awt.Dimension(100, 20));

        modifyAvion_forfait2JLabel.setText("Forfait2");

        modifyAvion_forfait2JTextField.setPreferredSize(new java.awt.Dimension(100, 20));

        modifyAvion_forfait3JLabel.setText("Forfait3");

        modifyAvion_forfait3JTextField.setPreferredSize(new java.awt.Dimension(100, 20));

        modifyAvion_heures_forfait1JLabel.setText("Heures_Forfait1");

        modifyAvion_heures_forfait1JTextField.setPreferredSize(new java.awt.Dimension(100, 20));

        modifyAvion_heures_forfait2JLabel.setText("Heures_Forfait2");

        modifyAvion_heures_forfait2JTextField.setPreferredSize(new java.awt.Dimension(100, 20));
        modifyAvion_heures_forfait2JTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyAvion_heures_forfait2JTextFieldActionPerformed(evt);
            }
        });

        modifyAvion_heures_forfait3JLabel.setText("Heures_Forfait3");

        modifyAvion_heures_forfait3JTextField.setPreferredSize(new java.awt.Dimension(100, 20));

        modifyAvion_reduction_semaineJTextField.setPreferredSize(new java.awt.Dimension(100, 20));

        modifyAvion_reduction_semaineJLabel.setText("Reduction_Semaine");

        modifyAvion_immatriculationJTextField.setPreferredSize(new java.awt.Dimension(100, 20));

        modifyAvion_immatriculationJLabel.setText("Immatriculation");

        modifyAvion_descriptionJTextField.setPreferredSize(new java.awt.Dimension(100, 20));

        modifyAvion_descriptionJLabel.setText("Description");

        modifyAvionButton.setText("Modifer");
        modifyAvionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyAvionButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout modifyAvionJDialogLayout = new javax.swing.GroupLayout(modifyAvionJDialog.getContentPane());
        modifyAvionJDialog.getContentPane().setLayout(modifyAvionJDialogLayout);
        modifyAvionJDialogLayout.setHorizontalGroup(
            modifyAvionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, modifyAvionJDialogLayout.createSequentialGroup()
                .addGroup(modifyAvionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(modifyAvionJDialogLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(modifyAvionButton))
                    .addGroup(modifyAvionJDialogLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(modifyAvionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(modifyAvionJDialogLayout.createSequentialGroup()
                                .addGroup(modifyAvionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(modifyAvionJDialogLayout.createSequentialGroup()
                                        .addComponent(modifyAvion_type_avionJLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(modifyAvion_type_avionJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(modifyAvionJDialogLayout.createSequentialGroup()
                                        .addComponent(modifyAvion_heures_forfait1JLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(modifyAvion_heures_forfait1JTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(modifyAvionJDialogLayout.createSequentialGroup()
                                        .addComponent(modifyAvion_forfait3JLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(modifyAvion_forfait3JTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(modifyAvionJDialogLayout.createSequentialGroup()
                                        .addComponent(modifyAvion_forfait1JLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(modifyAvion_forfait1JTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(modifyAvionJDialogLayout.createSequentialGroup()
                                        .addComponent(modifyAvion_tauxJLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(modifyAvion_tauxJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(modifyAvionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(modifyAvion_heures_forfait3JLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(modifyAvion_heures_forfait2JLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(modifyAvion_reduction_semaineJLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(modifyAvion_descriptionJLabel, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(modifyAvionJDialogLayout.createSequentialGroup()
                                .addComponent(modifyAvion_forfait2JLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(modifyAvion_forfait2JTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(modifyAvion_immatriculationJLabel)))
                        .addGap(12, 12, 12)
                        .addGroup(modifyAvionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(modifyAvion_immatriculationJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                            .addComponent(modifyAvion_heures_forfait2JTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(modifyAvion_heures_forfait3JTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(modifyAvion_reduction_semaineJTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(modifyAvion_descriptionJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))))
                .addGap(27, 27, 27))
        );
        modifyAvionJDialogLayout.setVerticalGroup(
            modifyAvionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modifyAvionJDialogLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(modifyAvionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(modifyAvion_type_avionJLabel)
                    .addComponent(modifyAvion_type_avionJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modifyAvion_heures_forfait2JLabel)
                    .addComponent(modifyAvion_heures_forfait2JTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(modifyAvionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(modifyAvionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(modifyAvion_tauxJLabel)
                        .addComponent(modifyAvion_tauxJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(modifyAvionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(modifyAvion_heures_forfait3JLabel)
                        .addComponent(modifyAvion_heures_forfait3JTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(modifyAvionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(modifyAvion_forfait1JLabel)
                    .addComponent(modifyAvion_forfait1JTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modifyAvion_reduction_semaineJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modifyAvion_reduction_semaineJLabel))
                .addGap(18, 18, 18)
                .addGroup(modifyAvionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(modifyAvion_forfait2JLabel)
                    .addComponent(modifyAvion_forfait2JTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modifyAvion_immatriculationJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modifyAvion_immatriculationJLabel))
                .addGap(18, 18, 18)
                .addGroup(modifyAvionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(modifyAvion_forfait3JLabel)
                    .addComponent(modifyAvion_forfait3JTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modifyAvion_descriptionJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modifyAvion_descriptionJLabel))
                .addGap(18, 18, 18)
                .addGroup(modifyAvionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(modifyAvion_heures_forfait1JLabel)
                    .addComponent(modifyAvion_heures_forfait1JTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addComponent(modifyAvionButton)
                .addGap(21, 21, 21))
        );

        deleteAvionJDialog.setMinimumSize(new java.awt.Dimension(600, 600));

        submitAvionButton.setText("Supprimer");
        submitAvionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitAvionButtonActionPerformed(evt);
            }
        });

        cancelAvionButton.setText("Annuler");
        cancelAvionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelAvionButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout deleteAvionJDialogLayout = new javax.swing.GroupLayout(deleteAvionJDialog.getContentPane());
        deleteAvionJDialog.getContentPane().setLayout(deleteAvionJDialogLayout);
        deleteAvionJDialogLayout.setHorizontalGroup(
            deleteAvionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(deleteAvionJDialogLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(submitAvionButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 137, Short.MAX_VALUE)
                .addComponent(cancelAvionButton)
                .addGap(56, 56, 56))
        );
        deleteAvionJDialogLayout.setVerticalGroup(
            deleteAvionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(deleteAvionJDialogLayout.createSequentialGroup()
                .addGap(129, 129, 129)
                .addGroup(deleteAvionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(submitAvionButton)
                    .addComponent(cancelAvionButton))
                .addContainerGap(148, Short.MAX_VALUE))
        );

        setPreferredSize(new java.awt.Dimension(900, 800));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Avion");
        jLabel1.setMaximumSize(new java.awt.Dimension(53, 17));
        jLabel1.setMinimumSize(new java.awt.Dimension(53, 17));

        AvionTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nom", "Immatriculation"
            }
        ));
        jScrollPane2.setViewportView(AvionTable);

        AddAvion.setText("Ajouter");
        AddAvion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddAvionMouseClicked(evt);
            }
        });
        AddAvion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddAvionActionPerformed(evt);
            }
        });

        ModifyAvion.setText("Modifier");
        ModifyAvion.setPreferredSize(new java.awt.Dimension(72, 23));
        ModifyAvion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ModifyAvionMouseClicked(evt);
            }
        });
        ModifyAvion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModifyAvionActionPerformed(evt);
            }
        });

        ShowAvion.setText("Afficher");
        ShowAvion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShowAvionActionPerformed(evt);
            }
        });

        DeleteAvion.setText("Supprimer");
        DeleteAvion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteAvionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(165, Short.MAX_VALUE)
                .addComponent(ShowAvion, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(AddAvion, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ModifyAvion, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(DeleteAvion, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(169, 169, 169))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddAvion, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ModifyAvion, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ShowAvion, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DeleteAvion, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addAvion_heures_forfait2JTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addAvion_heures_forfait2JTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addAvion_heures_forfait2JTextFieldActionPerformed

    private void modifyAvion_heures_forfait2JTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifyAvion_heures_forfait2JTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_modifyAvion_heures_forfait2JTextFieldActionPerformed

    private void AddAvionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddAvionMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_AddAvionMouseClicked

    private void addAvion_type_avionJTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addAvion_type_avionJTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addAvion_type_avionJTextFieldActionPerformed

    private void addAvion_tauxJTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addAvion_tauxJTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addAvion_tauxJTextFieldActionPerformed

    private void modifyAvion_type_avionJTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifyAvion_type_avionJTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_modifyAvion_type_avionJTextFieldActionPerformed

    private void ModifyAvionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ModifyAvionMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_ModifyAvionMouseClicked

    private void addAvion_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addAvion_ButtonActionPerformed
        // TODO add your handling code here:
        addAvion();
    }//GEN-LAST:event_addAvion_ButtonActionPerformed

    private void modifyAvionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifyAvionButtonActionPerformed
        // TODO add your handling code here:
        modifyAvion();
    }//GEN-LAST:event_modifyAvionButtonActionPerformed

    private void ShowAvionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShowAvionActionPerformed
        // TODO add your handling code here:
        openShowAvion();
    }//GEN-LAST:event_ShowAvionActionPerformed

    private void ModifyAvionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModifyAvionActionPerformed
        // TODO add your handling code here:
        openModifyAvion();
    }//GEN-LAST:event_ModifyAvionActionPerformed

    private void AddAvionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddAvionActionPerformed
        // TODO add your handling code here:
        openAddAvion();
    }//GEN-LAST:event_AddAvionActionPerformed

    private void DeleteAvionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteAvionActionPerformed
        // TODO add your handling code here:
        openDeleteAvion();
    }//GEN-LAST:event_DeleteAvionActionPerformed

    private void submitAvionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitAvionButtonActionPerformed
        // TODO add your handling code here:
        deleteAvion();
    }//GEN-LAST:event_submitAvionButtonActionPerformed

    private void cancelAvionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelAvionButtonActionPerformed
        // TODO add your handling code here:
        deleteAvionJDialog.dispose();
    }//GEN-LAST:event_cancelAvionButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddAvion;
    private javax.swing.JTable AvionTable;
    private javax.swing.JButton DeleteAvion;
    private javax.swing.JButton ModifyAvion;
    private javax.swing.JButton ShowAvion;
    private javax.swing.JDialog addAvionJDialog;
    private javax.swing.JButton addAvion_Button;
    private javax.swing.JLabel addAvion_descriptionJLabel;
    private javax.swing.JTextField addAvion_descriptionJTextField;
    private javax.swing.JLabel addAvion_forfait1JLabel;
    private javax.swing.JTextField addAvion_forfait1JTextField;
    private javax.swing.JLabel addAvion_forfait2JLabel;
    private javax.swing.JTextField addAvion_forfait2JTextField;
    private javax.swing.JLabel addAvion_forfait3JLabel;
    private javax.swing.JTextField addAvion_forfait3JTextField;
    private javax.swing.JLabel addAvion_heures_forfait1JLabel;
    private javax.swing.JTextField addAvion_heures_forfait1JTextField;
    private javax.swing.JLabel addAvion_heures_forfait2JLabel;
    private javax.swing.JTextField addAvion_heures_forfait2JTextField;
    private javax.swing.JLabel addAvion_heures_forfait3JLabel;
    private javax.swing.JTextField addAvion_heures_forfait3JTextField;
    private javax.swing.JLabel addAvion_immatriculationJLabel;
    private javax.swing.JTextField addAvion_immatriculationJTextField;
    private javax.swing.JLabel addAvion_reduction_semaineJLabel;
    private javax.swing.JTextField addAvion_reduction_semaineJTextField;
    private javax.swing.JLabel addAvion_tauxJLabel;
    private javax.swing.JTextField addAvion_tauxJTextField;
    private javax.swing.JLabel addAvion_type_avionJLabel;
    private javax.swing.JTextField addAvion_type_avionJTextField;
    private javax.swing.JButton cancelAvionButton;
    private javax.swing.JDialog deleteAvionJDialog;
    private javax.swing.JLabel descriptionJLabel;
    private javax.swing.JLabel forfait1JLabel;
    private javax.swing.JLabel forfait2JLabel;
    private javax.swing.JLabel forfait3JLabel;
    private javax.swing.JLabel heures_forfait1JLabel;
    private javax.swing.JLabel heures_forfait2JLabel;
    private javax.swing.JLabel heures_forfait3JLabel;
    private javax.swing.JLabel immatriculationJLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton modifyAvionButton;
    private javax.swing.JDialog modifyAvionJDialog;
    private javax.swing.JLabel modifyAvion_descriptionJLabel;
    private javax.swing.JTextField modifyAvion_descriptionJTextField;
    private javax.swing.JLabel modifyAvion_forfait1JLabel;
    private javax.swing.JTextField modifyAvion_forfait1JTextField;
    private javax.swing.JLabel modifyAvion_forfait2JLabel;
    private javax.swing.JTextField modifyAvion_forfait2JTextField;
    private javax.swing.JLabel modifyAvion_forfait3JLabel;
    private javax.swing.JTextField modifyAvion_forfait3JTextField;
    private javax.swing.JLabel modifyAvion_heures_forfait1JLabel;
    private javax.swing.JTextField modifyAvion_heures_forfait1JTextField;
    private javax.swing.JLabel modifyAvion_heures_forfait2JLabel;
    private javax.swing.JTextField modifyAvion_heures_forfait2JTextField;
    private javax.swing.JLabel modifyAvion_heures_forfait3JLabel;
    private javax.swing.JTextField modifyAvion_heures_forfait3JTextField;
    private javax.swing.JLabel modifyAvion_immatriculationJLabel;
    private javax.swing.JTextField modifyAvion_immatriculationJTextField;
    private javax.swing.JLabel modifyAvion_reduction_semaineJLabel;
    private javax.swing.JTextField modifyAvion_reduction_semaineJTextField;
    private javax.swing.JLabel modifyAvion_tauxJLabel;
    private javax.swing.JTextField modifyAvion_tauxJTextField;
    private javax.swing.JLabel modifyAvion_type_avionJLabel;
    private javax.swing.JTextField modifyAvion_type_avionJTextField;
    private javax.swing.JLabel num_avionJLabel;
    private javax.swing.JLabel reduction_semaineJLabel;
    private javax.swing.JDialog showAvionJDialog;
    private javax.swing.JButton submitAvionButton;
    private javax.swing.JLabel tauxJLabel;
    private javax.swing.JLabel type_avionJLabel;
    // End of variables declaration//GEN-END:variables
}
