package javaswingdev.form;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaswingdev.GoogleMaterialDesignIcon;
import javaswingdev.card.ModelCard;
import javaswingdev.main.login;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

public class Form_traitement extends javax.swing.JPanel {
 private static final String username = "root";
 private static final String password = "primatologue";
 private static final String dataConn = "jdbc:mysql://localhost:3306/hopital";
 
 Connection sqlConn = null;
 Connection sqlSearch = null;
 PreparedStatement pst = null;
 PreparedStatement pst_search = null;
 ResultSet rs = null;
 ResultSet rs_2 = null;
 PreparedStatement pst_tab = null;
 ResultSet rs_tab = null;
 int q, i , id, deleteItem;
    public Form_traitement() {
        initComponents();
        init();
//        comboservice();
        nom.setEditable(false);
        prenom.setEditable(false);
        nai.setEditable(false);
        sexe.setEditable(false);
        ta.setEditable(false);
         tp.setEditable(false);
          dg.setEditable(false);
           pa.setEditable(false);
            al.setEditable(false);
             at.setEditable(false);
              mp.setEditable(false);
        lbl_ad();
         table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       
    }

    private void init() {
       table.fixTable(jScrollPane1);
     
     //  table2.fixTable(jScrollPane4);
    
        //  init card data
        //card4.setData(new ModelCard(null, null, null, "$ 300.00", "Report Profit Monthly"));
    }
         public void tab_all_recu ()
    {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
            
            model.setRowCount(0);
            
            try {
                Class.forName("com.mysql.jdbc.Driver");
                sqlConn = DriverManager.getConnection(dataConn,username,password);
                pst_tab = sqlConn.prepareStatement("Select * from recu_consultation,service,consultation,type_analyse where etat='Payer' and id_consultation = idconsultation and idservice = id and type_analyse=idtype_analyse");
                rs_tab = pst_tab.executeQuery () ;
                while (rs_tab.next()) {
                    String column1Data = rs_tab.getString("idrecu_consul");
                    String column2Data = rs_tab.getString("idconsultation");
                    String column3Data = rs_tab.getString("nom_r");
                    String column4Data = rs_tab.getString("prenom_r");
                    String column5Data = rs_tab.getString("contact");            
                    String column6Data = rs_tab.getString("service_libelle");
                    String column7Data = rs_tab.getString("libelle_analyse");
                    String column8Data = rs_tab.getString("prix").concat(" FrCfa");
                    String column9Data = rs_tab.getString("date_recu");
                 
                    // Ajouter d'autres colonnes si nécessaire

                    model.addRow(new Object[]{column1Data, column2Data, column3Data, column4Data, column5Data, column6Data, column7Data, column8Data,column9Data});
               }
            }
            
            catch (ClassNotFoundException | SQLException e) {
            }
    }

               public String lbl_service()
    {
        String num_id = null;
        String id = null;
        id = login.txtid.getText();
          try {
            Class.forName("com.mysql.jdbc.Driver");
                 sqlConn = DriverManager.getConnection(dataConn,username,password);
                 pst = sqlConn.prepareCall("Select * from users,service where identifiant = ? and Service_id = id");
                    pst.setString(1, id);
                        rs = pst.executeQuery();
                            if (rs.next())
                            {
                               num_id = rs.getString("service_libelle");
                             
                            }
                               
        } catch (Exception e) {
        }
          return num_id;
    }
          public void diag_hist (String nbre2)
    {
        String nbre_total = null;
     try {
            Class.forName("com.mysql.jdbc.Driver");
                 sqlConn = DriverManager.getConnection(dataConn,username,password);
                pst = sqlConn.prepareCall("SELECT * FROM dm,service,patient,acte where nume_acte LIKE ?  and service_libelle=? and libelle_acte='Consultation' and dm_ser =id and dm_id_patient=idpatient and nume_acte=num_acte");
                 pst.setString(1, "%"+date2.getText()+"%");
                pst.setString(2, nbre2);
               
                 
                                rs = pst.executeQuery();
                 if (rs.next())
                         
                         {
                             nom.setText(rs.getString("nom_patient"));
                             prenom.setText(rs.getString("prenom_patient"));
                             sexe.setText(rs.getString("sexe"));
                             nai.setText(rs.getString("date_naissance"));
                             ta.setText(rs.getString("ta"));
                             tp.setText(rs.getString("tp").concat(" °C"));
                             dg.setText(rs.getString("pd").concat(" Kg"));
                             pa.setText(rs.getString("pa"));
                             at.setText(rs.getString("adp"));
                              mp.setText(rs.getString("pp"));
                               al.setText(rs.getString("al"));
                       //   nombre.setText(nbre);
                         }
                 else
                 {
                            nom.setText("");
                             prenom.setText("");
                             sexe.setText("");
                             nai.setText("");
                             ta.setText("");
                             tp.setText("");
                             dg.setText("");
                             pa.setText("");
                             at.setText("");
                              mp.setText("");
                               al.setText("");
                 }
                 
        } catch (Exception e) {
        }
        
        
    }
      
          public String nombre_operation (String nbre , String nbre2)
    {
        String nbre_total = null;
     try {
            Class.forName("com.mysql.jdbc.Driver");
                 sqlConn = DriverManager.getConnection(dataConn,username,password);
 pst = sqlConn.prepareCall("SELECT COUNT(*) FROM factureoperation where date_paiement_op BETWEEN ? AND ?");
 pst.setString(1, nbre);
 pst.setString(2, nbre2);
                 rs = pst.executeQuery();
                 if (rs.next())
                         
                         {
                              nbre_total = rs.getString("COUNT(*)");
                       //   nombre.setText(nbre);
                         }
        } catch (Exception e) {
        }
        if(nbre_total.equalsIgnoreCase(null))
         {
             return "0";
         }
         else{
         return nbre_total;
    }
    }
        
     
        public String paiement_consultation ()
    {
         String prix_total = null;
     try {
            Class.forName("com.mysql.jdbc.Driver");
                 sqlConn = DriverManager.getConnection(dataConn,username,password);
                 pst = sqlConn.prepareCall("SELECT sum(prix) FROM recu_consultation");
                 rs = pst.executeQuery();
                 if (rs.next())
                         
                         {
                              prix_total = rs.getString("sum(prix)");
                       //   nombre.setText(nbre);
                         }
        } catch (Exception e) {
        }
        return prix_total;
    }
        public String paiement_operation ()
    {
         String prix_total = null;
     try {
            Class.forName("com.mysql.jdbc.Driver");
                 sqlConn = DriverManager.getConnection(dataConn,username,password);
                 pst = sqlConn.prepareCall("SELECT sum(prixOperation) FROM factureoperation");
                 rs = pst.executeQuery();
                 if (rs.next())
                         
                         {
                              prix_total = rs.getString("sum(prixOperation)");
                       //   nombre.setText(nbre);
                         }
        } catch (Exception e) {
        }
        return prix_total;
    }  
     public String id_analyse ()
    {
        String nbre_total = null;
     try {
            Class.forName("com.mysql.jdbc.Driver");
                 sqlConn = DriverManager.getConnection(dataConn,username,password);
                 pst = sqlConn.prepareCall("SELECT * FROM type_analyse where libelle_analyse= ?");
                 pst.setString(1, null);
                 rs = pst.executeQuery();
                 if (rs.next())
                         
                         {
                              nbre_total = rs.getString("idtype_analyse");
                       //   nombre.setText(nbre);
                         }
                 else
                 {
                            nbre_total = "Pas d'examen pour cette acte";
                 }
        } catch (Exception e) {
        }
        return nbre_total;
    }
    public String id_service ()
    {
        String nbre_total = null;
     try {
            Class.forName("com.mysql.jdbc.Driver");
                 sqlConn = DriverManager.getConnection(dataConn,username,password);
                 pst = sqlConn.prepareCall("SELECT * FROM service where service_libelle =?");
                 pst.setString(1, null);
                 rs = pst.executeQuery();
                 if (rs.next())
                         
                         {
                              nbre_total = rs.getString("id");
                       //   nombre.setText(nbre);
                         }
        } catch (Exception e) {
        }
        return nbre_total;
    }
     public String id_ser (String service)
    {
        String nbre_total = null;
     try {
            Class.forName("com.mysql.jdbc.Driver");
                 sqlConn = DriverManager.getConnection(dataConn,username,password);
                 pst = sqlConn.prepareCall("SELECT * FROM service where service_libelle =?");
                 pst.setString(1, service);
                 rs = pst.executeQuery();
                 if (rs.next())
                         
                         {
                              nbre_total = rs.getString("id");
                       //   nombre.setText(nbre);
                         }
        } catch (Exception e) {
        }
        return nbre_total;
    }
     public String num_id()
    {
        String num_id = null;
        String id = null;
        id = login.txtid.getText();
          try {
            Class.forName("com.mysql.jdbc.Driver");
                 sqlConn = DriverManager.getConnection(dataConn,username,password);
                 pst = sqlConn.prepareCall("Select * from users where identifiant = ?");
                    pst.setString(1, id);
                        rs = pst.executeQuery();
                            if (rs.next())
                            {
                               num_id = rs.getString("idUser");
                            }
                               
        } catch (Exception e) {
        }
          return num_id;
    } 
     public String id_patient()
    {
        String num_id = null;
        String name,surname = null;
        name = nom.getText();
        surname = prenom.getText();
          try {
            Class.forName("com.mysql.jdbc.Driver");
                 sqlConn = DriverManager.getConnection(dataConn,username,password);
                 pst = sqlConn.prepareCall("Select * from patient where nom_patient = ? and prenom_patient=?");
                    pst.setString(1, name);
                    pst.setString(2, surname);
                        rs = pst.executeQuery();
                            if (rs.next())
                            {
                               num_id = rs.getString("idpatient");
                            }
                               
        } catch (Exception e) {
        }
          return num_id;
    } 
      public void lbl_ad()
    {
        String num_id = null;
        String id = null;
        id = login.txtid.getText();
          try {
            Class.forName("com.mysql.jdbc.Driver");
                 sqlConn = DriverManager.getConnection(dataConn,username,password);
                 pst = sqlConn.prepareCall("Select * from users where identifiant = ?");
                    pst.setString(1, id);
                        rs = pst.executeQuery();
                            if (rs.next())
                            {
                               num_id = rs.getString("nom_users").concat(" ").concat(rs.getString("prenom_users"));
                               lbl_agent.setText(num_id);
                            }
                               
        } catch (Exception e) {
        }
    }   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new javaswingdev.swing.RoundPanel();
        materialTabbed1 = new tabbed.MaterialTabbed();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javaswingdev.swing.table.Table();
        jLabel10 = new javax.swing.JLabel();
        total = new textfield.TextField1();
        jLabel12 = new javax.swing.JLabel();
        lbl_agent = new textfield.TextField1();
        date2 = new textfield.textsearch();
        panelBorder1 = new com.raven.swing.PanelBorder();
        jLabel5 = new javax.swing.JLabel();
        sexe = new textfield.TextField1();
        jLabel6 = new javax.swing.JLabel();
        nai = new textfield.TextField1();
        jLabel11 = new javax.swing.JLabel();
        ta = new textfield.TextField1();
        jLabel3 = new javax.swing.JLabel();
        nom = new textfield.TextField1();
        jLabel4 = new javax.swing.JLabel();
        prenom = new textfield.TextField1();
        panelBorder2 = new com.raven.swing.PanelBorder();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        add = new javax.swing.JButton();
        al = new textfield.TextField1();
        add1 = new javax.swing.JButton();
        tp = new textfield.TextField1();
        jLabel15 = new javax.swing.JLabel();
        dg = new textfield.TextField1();
        jLabel16 = new javax.swing.JLabel();
        pa = new textfield.TextField1();
        at = new textfield.TextField1();
        jLabel17 = new javax.swing.JLabel();
        mp = new textfield.TextField1();
        jLabel18 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();

        setOpaque(false);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        roundPanel1.setRound(10);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Medicament", "Dosage ou Nombre", "Fréquence/jrs", "Durée", "Examens", "Note"
            }
        ));
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        materialTabbed1.addTab("Ordonnance", jScrollPane1);

        jLabel10.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Traitement");

        total.setForeground(new java.awt.Color(204, 51, 0));
        total.setFont(new java.awt.Font("Corbel", 1, 15)); // NOI18N
        total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Médécin Soignant");

        lbl_agent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lbl_agentActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(materialTabbed1, javax.swing.GroupLayout.DEFAULT_SIZE, 1051, Short.MAX_VALUE)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(42, 42, 42)
                .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(lbl_agent, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(materialTabbed1, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
                .addGap(13, 13, 13)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbl_agent, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        date2.setText("N° Acte");
        date2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                date2MouseClicked(evt);
            }
        });
        date2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                date2ActionPerformed(evt);
            }
        });
        date2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                date2KeyReleased(evt);
            }
        });

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Sexe");

        jLabel6.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Naissance");

        jLabel11.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Traitement Anterieure");

        jLabel3.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("NOM");

        nom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nomKeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("PRENOMS");

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(sexe, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nai, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nom, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(prenom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(prenom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sexe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        panelBorder2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("TP");

        jLabel9.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Maux du patient");

        add.setBackground(new java.awt.Color(0, 204, 102));
        add.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        add.setForeground(new java.awt.Color(255, 255, 255));
        add.setText("+");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adddActionPerformed(evt);
            }
        });

        al.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        add1.setBackground(new java.awt.Color(204, 0, 0));
        add1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        add1.setForeground(new java.awt.Color(255, 255, 255));
        add1.setText("-");
        add1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add1dActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("DG");

        jLabel16.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setText("PA");

        pa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText(" Antécédant");

        jLabel18.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText("Allergie");

        javax.swing.GroupLayout panelBorder2Layout = new javax.swing.GroupLayout(panelBorder2);
        panelBorder2.setLayout(panelBorder2Layout);
        panelBorder2Layout.setHorizontalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(mp, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(al, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(add1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBorder2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tp, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dg, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(at, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelBorder2Layout.setVerticalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(at, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(add1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBorder2Layout.createSequentialGroup()
                        .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(al, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1)))
                .addGap(17, 17, 17))
        );

        jLabel1.setFont(new java.awt.Font("Corbel", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("IDENTITE");

        jLabel2.setFont(new java.awt.Font("Corbel", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("TRAITEMENT");

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));

        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));

        jButton1.setBackground(new java.awt.Color(22, 93, 109));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("VALIDER");
        jButton1.setBorder(null);
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelBorder2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(date2, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(date2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelBorder2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void date2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_date2KeyReleased
        // TODO add your handling code here:
        if(date2.getText().equalsIgnoreCase("N° Acte") ||  date2.getText().equalsIgnoreCase(""))
        {
                             nom.setText("");
                             prenom.setText("");
                             sexe.setText("");
                             nai.setText("");
                             ta.setText("");
                             tp.setText("");
                             dg.setText("");
                             pa.setText("");
                             at.setText("");
                              mp.setText("");
                               al.setText("");
          DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);
            total.setText("");
        }
        else
        {
            diag_hist(lbl_service());
        }
     
    }//GEN-LAST:event_date2KeyReleased

    private void date2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_date2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_date2ActionPerformed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:
        init();
    }//GEN-LAST:event_formMouseClicked

  
        public static void addRow(JTable table, Object[] rowData) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addRow(rowData);
    }
    private void adddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adddActionPerformed
        // TODO add your handling code here:
    // int rowCount = table.getRowCount();


     Object[] rowData = { " ", " ", " ", " ", "Pas d'examen","Pas de note" };
               addRow(table,rowData);
    // Ne rien faire si le nombre de lignes est 
        

    }//GEN-LAST:event_adddActionPerformed

    private void date2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_date2MouseClicked
        // TODO add your handling code here:
        date2.setText("");
    }//GEN-LAST:event_date2MouseClicked

    private void add1dActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add1dActionPerformed
        // TODO add your handling code here:
       DefaultTableModel model = (DefaultTableModel) table.getModel();
int selectedRow = table.getSelectedRow();

if (selectedRow != -1) {
    
               // String mt = model.getValueAt(selectedRow, 5).toString();
               // int mtt = Integer.parseInt(mt);
               // int tt = Integer.parseInt(total.getText());
               // int nmt = tt - mtt;
               // total.setText(String.valueOf(nmt));
    // Supprimer la ligne sélectionnée
    model.removeRow(selectedRow);
} else {
    // Afficher un message d'erreur si aucune ligne n'est sélectionnée
    JOptionPane.showMessageDialog(this,
            "Veuillez sélectionner une ligne à supprimer",
            "Aucune ligne sélectionnée",
            JOptionPane.ERROR_MESSAGE);
}
    }//GEN-LAST:event_add1dActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        // TODO add your handling code here:
        //  DefaultTableModel model = (DefaultTableModel) table.getModel();
          //  int selectedRow = table.getSelectedRow();
            //table.add
    
    }//GEN-LAST:event_tableMouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int taille_nom = nom.getText().length();
        int taille_prenom = prenom.getText().length();
        int taille_numero = ta.getText().length();
        
      String n  = "0";
       int g = Integer.parseInt(n);
        Date d = new Date();
       SimpleDateFormat dat = new SimpleDateFormat("dd-MM-yyyy");
       dat.setTimeZone(TimeZone.getTimeZone("UTC")); // Définit la zone horaire à UTC
       String ladate = dat.format(d);
       String id_us= num_id();
      
       String id_pat = id_patient();
           DefaultTableModel model = (DefaultTableModel) table.getModel();
        int rowCount = model.getRowCount();
        // java.sql.Date ladate = new java.sql.Date(d.getTime());
        if(rowCount==0 || total.getText().trim().isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Table d'ordonnance ou ligne de traitement vide");
        }
        else
        {
            try {

                Class.forName("com.mysql.jdbc.Driver");
                sqlConn = DriverManager.getConnection(dataConn,username,password);
                
            
          

                 String num = String.valueOf(g);
            
                // Récupérer les données de chaque colonne de la ligne
               // String num_act= String.valueOf(g);
               
                   
                
                    pst = sqlConn.prepareStatement("update dm set Medecin = ?,traitement=?,date_traitement=?,identite=? where nume_acte=?");
                    pst.setString(1, lbl_agent.getText());
                    pst.setString(2, total.getText());
                    pst.setString(3, ladate);
                    pst.setString(4, nom.getText().concat(" ").concat(prenom.getText()));
                    pst.setString(5, date2.getText());
                    pst.executeUpdate();
                 JOptionPane.showMessageDialog(null, "Traitement enrégistré");
                 generatePDFReport(table);
                  nom.setText("");
                             prenom.setText("");
                             sexe.setText("");
                             nai.setText("");
                             ta.setText("");
                             tp.setText("");
                             dg.setText("");
                             pa.setText("");
                             at.setText("");
                              mp.setText("");
                               al.setText("");
        
            model.setRowCount(0);
            total.setText("");
                 
            
                
            } catch (ClassNotFoundException ex) {
               
            } catch (SQLException ex) {
         Logger.getLogger(Form_traitement.class.getName()).log(Level.SEVERE, null, ex);
     }
    } 
    }//GEN-LAST:event_jButton1ActionPerformed

    private void paActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_paActionPerformed

    private void nomKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nomKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_nomKeyReleased

    private void totalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalActionPerformed

    private void lbl_agentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lbl_agentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl_agentActionPerformed

     public void print(JTable table) {
        TableModel model = table.getModel();
        try {
            boolean complete = table.print(JTable.PrintMode.FIT_WIDTH);
            if (complete) {
                System.out.println("Impression terminée !");
            } else {
                System.out.println("Impression annulée.");
            }
        } catch (PrinterException pe) {
            System.out.println("Erreur lors de l'impression : " + pe);
        }
    }
     
     public void generatePDFReport(JTable table) {
        try {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            int rowCount = model.getRowCount();
            int columnCount = model.getColumnCount();

            // Création du dossier "Print_Facture" à la racine du projet si nécessaire
            String printFactureFolderPath = "../Print_Ordonnance/";
            createDirectoryIfNotExists(printFactureFolderPath);

            // Génération du nom du fichier PDF avec la date et l'heure actuelle
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String currentTime = LocalDateTime.now().format(formatter);
            String fileName = "Rapport_Ordonnance_"+date2.getText()+"_"+nom.getText()+"_"+prenom.getText()+"_" + currentTime + ".pdf";
            String filePath = printFactureFolderPath + fileName;

            // Création du document PDF
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, new FileOutputStream(filePath));

            // Ouverture du document
            document.open();

            // Titre "Facture de paiement"
            Paragraph title = new Paragraph("Ordonnance pour acte N°_"+date2.getText());
            title.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(title);
            Paragraph nm = new Paragraph("Date "+currentTime);
            nm.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(nm);
            
            // Espacement entre les éléments
            document.add(new Paragraph(" "));

            // Titre "Tableau de Facturation"
            Paragraph title1 = new Paragraph("");
            title1.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(title1);

            // Création du premier tableau
            PdfPTable pdfTable = new PdfPTable(columnCount);
            pdfTable.setWidthPercentage(100);

            // Ajout des en-têtes de colonne du premier tableau
            for (int i = 0; i < columnCount; i++) {
                pdfTable.addCell(model.getColumnName(i));
            }

            // Ajout des données du JTable dans le premier tableau
            for (int i = 0; i < rowCount; i++) {
                for (int j = 0; j < columnCount; j++) {
                    pdfTable.addCell(model.getValueAt(i, j).toString());
                }
            }

            // Ajout du premier tableau au document
            document.add(pdfTable);
            document.add(new Paragraph(" "));
            Paragraph Traite = new Paragraph("Traitement : "+total .getText());
            Traite.setAlignment(Paragraph.ALIGN_LEFT);
            document.add(Traite);
            
            document.add(new Paragraph(" "));
            Paragraph Total = new Paragraph("Medecin "+lbl_agent.getText());
            Total.setAlignment(Paragraph.ALIGN_LEFT);
            document.add(Total);
            
            Paragraph c = new Paragraph("**BONNE GUERISON**");
            
            c.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(c);
            // Fermeture du document
            document.close();
            // Fermeture du document
            document.close();

            // Ouverture automatique du fichier PDF
            openPDFFile(filePath);

           // System.out.println("Facture générée avec succès !");
        } catch (Exception e) {
           // System.out.println("Une erreur s'est produite lors de la génération du PDF : " + e);
        }
    }

    
private void createDirectoryIfNotExists(String directoryPath) {
    File directory = new File(directoryPath);
    if (!directory.exists()) {
        directory.mkdirs();
    }
}

private void openPDFFile(String filePath) {
    try {
        File file = new File(filePath);
        if (file.exists()) {
            Desktop.getDesktop().open(file);
        }
    } catch (IOException e) {
        System.out.println("Erreur lors de l'ouverture du fichier PDF : " + e);
    }
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add;
    private javax.swing.JButton add1;
    private textfield.TextField1 al;
    private textfield.TextField1 at;
    private textfield.textsearch date2;
    private textfield.TextField1 dg;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private textfield.TextField1 lbl_agent;
    private tabbed.MaterialTabbed materialTabbed1;
    private textfield.TextField1 mp;
    private textfield.TextField1 nai;
    private textfield.TextField1 nom;
    private textfield.TextField1 pa;
    private com.raven.swing.PanelBorder panelBorder1;
    private com.raven.swing.PanelBorder panelBorder2;
    private textfield.TextField1 prenom;
    private javaswingdev.swing.RoundPanel roundPanel1;
    private textfield.TextField1 sexe;
    private textfield.TextField1 ta;
    private javaswingdev.swing.table.Table table;
    private textfield.TextField1 total;
    private textfield.TextField1 tp;
    // End of variables declaration//GEN-END:variables
}
