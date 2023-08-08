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

public class Form_exam extends javax.swing.JPanel {
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
    public Form_exam() {
        initComponents();
        init();
        comboservice();
        nom.setEditable(false);
        prenom.setEditable(false);
        nai.setEditable(false);
        sexe.setEditable(false);
        numero.setEditable(false);
        exam();
        total.setEditable(false);
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

          public void comboservice ()
     {
         
          //String id = null;
        //id = login.txtid.getText();
         try {
              Class.forName("com.mysql.jdbc.Driver");
                sqlConn = DriverManager.getConnection(dataConn,username,password);
                pst = sqlConn.prepareCall("Select * from service where service_libelle != 'Caisse' and service_libelle != 'Admin' and service_libelle!='Admission' and etat_service='actif'");
               //  pst.setString(1, id);
                rs = pst.executeQuery();
                 while(rs.next())
                 {
                  service.addItem(rs.getString("service_libelle"));
                 }
         } catch (ClassNotFoundException | SQLException e) {
         }
     }   
         
          public void combo_analyse (String serv)
     {
         
          //String id = null;
        //id = login.txtid.getText();
         try {
              Class.forName("com.mysql.jdbc.Driver");
                sqlConn = DriverManager.getConnection(dataConn,username,password);
                pst = sqlConn.prepareCall("Select * from type_analyse,service where service_libelle=? and service_libelle != 'Caisse' and service_libelle != 'Admin' and service_libelle != 'Admission' and etat_service='actif' and id_serv = id");
                 pst.setString(1, serv);
                rs = pst.executeQuery();
                 while(rs.next())
                 {
                  lblexam.addItem(rs.getString("libelle_analyse"));
                 }
         } catch (ClassNotFoundException | SQLException e) {
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
                 pst.setString(1, lblexam.getSelectedItem().toString());
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
                 pst.setString(1, service.getSelectedItem().toString());
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
        jLabel14 = new javax.swing.JLabel();
        date2 = new textfield.textsearch();
        panelBorder1 = new com.raven.swing.PanelBorder();
        nom = new textfield.TextField1();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        prenom = new textfield.TextField1();
        jLabel5 = new javax.swing.JLabel();
        sexe = new textfield.TextField1();
        jLabel6 = new javax.swing.JLabel();
        nai = new textfield.TextField1();
        jLabel11 = new javax.swing.JLabel();
        numero = new textfield.TextField1();
        panelBorder2 = new com.raven.swing.PanelBorder();
        acte = new combobox.Combobox();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        service = new combobox.Combobox();
        lblexam = new combobox.Combobox();
        jLabel9 = new javax.swing.JLabel();
        add = new javax.swing.JButton();
        mt = new textfield.TextField1();
        add1 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
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
                "Nom", "Prenom", "Numero", "Acte", "Examen", "Service", "Montant", "Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        materialTabbed1.addTab("ACTE", jScrollPane1);

        jLabel10.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("TOTAL à payer");

        total.setForeground(new java.awt.Color(204, 51, 0));
        total.setText("0");

        jLabel12.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Agent Admission");

        jLabel14.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(204, 51, 0));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("FrCfa");

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(materialTabbed1, javax.swing.GroupLayout.DEFAULT_SIZE, 1051, Short.MAX_VALUE)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(lbl_agent, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(materialTabbed1, javax.swing.GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)
                .addGap(13, 13, 13)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbl_agent, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14)))
                .addContainerGap())
        );

        date2.setText(" Rechercher patient");
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

        nom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nomKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("NOM");

        jLabel4.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("PRENOMS");

        jLabel5.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Sexe");

        jLabel6.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Naissance");

        jLabel11.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Numero");

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
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nai, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nom, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)))
                .addGap(18, 18, 18)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(prenom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(numero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
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
                    .addComponent(numero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        panelBorder2.setBackground(new java.awt.Color(255, 255, 255));

        acte.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Consultation", "Examen" }));
        acte.setFont(new java.awt.Font("Cambria", 0, 17)); // NOI18N
        acte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                acteMouseClicked(evt);
            }
        });
        acte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acteActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("ACTE");

        jLabel8.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("SERVICE");

        service.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        service.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                serviceMouseClicked(evt);
            }
        });
        service.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                serviceActionPerformed(evt);
            }
        });

        lblexam.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        lblexam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblexamMouseClicked(evt);
            }
        });
        lblexam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lblexamActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("EXAMEN ");

        add.setBackground(new java.awt.Color(0, 204, 102));
        add.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        add.setForeground(new java.awt.Color(255, 255, 255));
        add.setText("+");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adddActionPerformed(evt);
            }
        });

        mt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        mt.setText(" 5000");

        add1.setBackground(new java.awt.Color(204, 0, 0));
        add1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        add1.setForeground(new java.awt.Color(255, 255, 255));
        add1.setText("-");
        add1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add1dActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Corbel", 1, 15)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("FrCfa");

        javax.swing.GroupLayout panelBorder2Layout = new javax.swing.GroupLayout(panelBorder2);
        panelBorder2.setLayout(panelBorder2Layout);
        panelBorder2Layout.setHorizontalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(acte, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8))
                    .addGroup(panelBorder2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblexam, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder2Layout.createSequentialGroup()
                        .addComponent(mt, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(add1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(service, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelBorder2Layout.setVerticalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(acte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(service, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(add1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBorder2Layout.createSequentialGroup()
                        .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblexam, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addGap(1, 1, 1)))
                .addGap(17, 17, 17))
        );

        jLabel1.setFont(new java.awt.Font("Corbel", 1, 12)); // NOI18N
        jLabel1.setText("IDENTITE");

        jLabel2.setFont(new java.awt.Font("Corbel", 1, 12)); // NOI18N
        jLabel2.setText("FACTURATION");

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
        if(date2.getText().equalsIgnoreCase("Rechercher patient") ||  date2.getText().equalsIgnoreCase(""))
        {
         nom.setText("");
         prenom.setText("");
         nai.setText("");
         sexe.setText("");
         numero.setText("");
          DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);
            total.setText("0");
        }
        else
        {
             try {
          Class.forName("com.mysql.jdbc.Driver");
    sqlConn = DriverManager.getConnection(dataConn,username,password);
     //String sql = "SELECT * FROM  factureconsultation,service,type_analyse WHERE date BETWEEN ? AND ? and idservice = id and type_analyse=idtype_analyse";
     pst_tab = sqlConn.prepareStatement("SELECT * FROM patient WHERE  personne LIKE ?");
     pst_tab.setString(1, "%"+date2.getText()+"%");
     rs_tab = pst_tab.executeQuery () ;
     if(rs_tab.next())
     {
         nom.setText(rs_tab.getString("nom_patient"));
         prenom.setText(rs_tab.getString("prenom_patient"));
         nai.setText(rs_tab.getString("date_naissance"));
         sexe.setText(rs_tab.getString("sexe"));
         numero.setText(rs_tab.getString("telephone_patient"));
     }
     else
     {
         nom.setText("");
         prenom.setText("");
         nai.setText("");
         sexe.setText("");
         numero.setText("");
          DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);
            total.setText("0");
     }
        } catch (Exception e) {
        }
        }
     
    }//GEN-LAST:event_date2KeyReleased

    private void date2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_date2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_date2ActionPerformed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:
        init();
    }//GEN-LAST:event_formMouseClicked

    private void acteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_acteMouseClicked
        // TODO add your handling code here:
       
    }//GEN-LAST:event_acteMouseClicked

    private void acteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acteActionPerformed
        // TODO add your handling code here:
        exam();
    }//GEN-LAST:event_acteActionPerformed

    public void exam()
    {
        if(acte.getSelectedItem().equals("Consultation"))
        {
            lblexam.removeAllItems();
            lblexam.setEnabled(false);
            lblexam.addItem("Pas d'examen");
            mt.setText("2000");
        }
        else
        {
            lblexam.setEnabled(true);
             mt.setText("5000");
          if(service.getSelectedItem().toString().equalsIgnoreCase("Cardiologie")&& acte.getSelectedItem().toString().equalsIgnoreCase("Examen"))
        {
            lblexam.removeAllItems();
            lblexam.addItem("Analyse cardio vasculaire");
             lblexam.addItem("Electrocardiogramme ECG");
              lblexam.addItem("Echocardiographie");
        }
        else if(service.getSelectedItem().toString().equalsIgnoreCase("Ophtamologie")&& acte.getSelectedItem().toString().equalsIgnoreCase("Examen"))
        {
            lblexam.removeAllItems();
            lblexam.addItem("Analyse oculaire");
             lblexam.addItem("Examen de la vue");
              lblexam.addItem("Tonométrie");
        }
        else if(service.getSelectedItem().toString().equalsIgnoreCase("Dermatologie")&& acte.getSelectedItem().toString().equalsIgnoreCase("Examen"))
        {
            lblexam.removeAllItems();
            lblexam.addItem("Analyse cutané");
             lblexam.addItem("Traitement de l'acné");
              lblexam.addItem("Tumeurs cutanees");
               lblexam.addItem("Maladies inflammatoires de la peau");
        }
        else if (service.getSelectedItem().toString().equalsIgnoreCase("General")&& acte.getSelectedItem().toString().equalsIgnoreCase("Examen"))
        {
            lblexam.removeAllItems();
            lblexam.addItem("Traitement médical");
             lblexam.addItem("Consultation en médecine interne");
        }
        }
    }
    
    private void serviceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_serviceMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_serviceMouseClicked

    private void serviceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_serviceActionPerformed
        // TODO add your handling code here:
        if(service.getSelectedItem().toString().equalsIgnoreCase("Cardiologie")&& acte.getSelectedItem().toString().equalsIgnoreCase("Examen"))
        {
            lblexam.removeAllItems();
            lblexam.addItem("Analyse cardio vasculaire");
             lblexam.addItem("Electrocardiogramme ECG");
              lblexam.addItem("Echocardiographie");
        }
        else if(service.getSelectedItem().toString().equalsIgnoreCase("Ophtamologie")&& acte.getSelectedItem().toString().equalsIgnoreCase("Examen"))
        {
            lblexam.removeAllItems();
            lblexam.addItem("Analyse oculaire");
             lblexam.addItem("Examen de la vue");
              lblexam.addItem("Tonométrie");
        }
        else if(service.getSelectedItem().toString().equalsIgnoreCase("Dermatologie")&& acte.getSelectedItem().toString().equalsIgnoreCase("Examen"))
        {
            lblexam.removeAllItems();
            lblexam.addItem("Analyse cutané");
             lblexam.addItem("Traitement de l'acné");
              lblexam.addItem("Tumeurs cutanees");
               lblexam.addItem("Maladies inflammatoires de la peau");
        }
        else if (service.getSelectedItem().toString().equalsIgnoreCase("General")&& acte.getSelectedItem().toString().equalsIgnoreCase("Examen"))
        {
            lblexam.removeAllItems();
            lblexam.addItem("Traitement médical");
             lblexam.addItem("Consultation en médecine interne");
        }
        
    }//GEN-LAST:event_serviceActionPerformed

    private void lblexamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblexamMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblexamMouseClicked

    private void lblexamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblexamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblexamActionPerformed
    public static void addRow(JTable table, Object[] rowData) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addRow(rowData);
    }
    private void adddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adddActionPerformed
        // TODO add your handling code here:
         DefaultTableModel model = (DefaultTableModel) table.getModel();
        int rowCount_2 = model.getRowCount();
        boolean consultationTrouvee = false;
        for (int row = 0; row < rowCount_2; row++) {
    String cellValue = model.getValueAt(row, 3).toString();
    if (cellValue.equalsIgnoreCase("Consultation")) {
        consultationTrouvee = true;
        break; // Sort de la boucle dès que la valeur est trouvée
    }
}
       Date d = new Date();
        SimpleDateFormat dat = new SimpleDateFormat("dd-MM-yyyy");    
       String ladate = dat.format(d);
       int tte = Integer.parseInt(mt.getText());
       int tt = Integer.parseInt(total.getText());
        if(nom.getText().trim().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Nom du patient requis");
        }
        else if(consultationTrouvee)
        {
            JOptionPane.showMessageDialog(null, "Finir votre consultation");
        }
        else
        {
            try {
          Class.forName("com.mysql.jdbc.Driver");
    sqlConn = DriverManager.getConnection(dataConn,username,password);
     //String sql = "SELECT * FROM  factureconsultation,service,type_analyse WHERE date BETWEEN ? AND ? and idservice = id and type_analyse=idtype_analyse";
     pst_tab = sqlConn.prepareStatement("SELECT * FROM patient WHERE  nom_patient = ?");
     pst_tab.setString(1, nom.getText());
     rs_tab = pst_tab.executeQuery () ;
     if(rs_tab.next())
     {
        String name = rs_tab.getString("nom_patient");
        String surname = rs_tab.getString("prenom_patient");
        String tel = rs_tab.getString("telephone_patient");
        String act = acte.getSelectedItem().toString();
         String exam = lblexam.getSelectedItem().toString();
        String ser = service.getSelectedItem().toString();
        String mont = mt.getText();
         int rowCount = table.getRowCount();
         Object[] rowData = {name,surname,tel,act,exam,ser,mont,ladate};
               addRow(table,rowData);
              // total.setText(mont);
         int sm = tte + tt;
         total.setText(String.valueOf(sm));
        // System.out.println(sm);
     }
        } catch (Exception e) {
        }
        }
        

    }//GEN-LAST:event_adddActionPerformed

    private void date2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_date2MouseClicked
        // TODO add your handling code here:
        date2.setText("");
    }//GEN-LAST:event_date2MouseClicked

    private void nomKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nomKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_nomKeyReleased

    private void add1dActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add1dActionPerformed
        // TODO add your handling code here:
       DefaultTableModel model = (DefaultTableModel) table.getModel();
int selectedRow = table.getSelectedRow();

if (selectedRow != -1) {
    
                String mt = model.getValueAt(selectedRow, 6).toString();
                int mtt = Integer.parseInt(mt);
                int tt = Integer.parseInt(total.getText());
                int nmt = tt - mtt;
                total.setText(String.valueOf(nmt));
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
         
    }//GEN-LAST:event_tableMouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int taille_nom = nom.getText().length();
        int taille_prenom = prenom.getText().length();
        int taille_numero = numero.getText().length();
        
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
        if(rowCount==0)
        {
            JOptionPane.showMessageDialog(null,"Table de facture vide");
        }
        else
        {
            try {

                Class.forName("com.mysql.jdbc.Driver");
                sqlConn = DriverManager.getConnection(dataConn,username,password);
                 pst_tab = sqlConn.prepareStatement("SELECT * FROM acte WHERE num_acte = ? ");
            pst_tab.setString(1, n);
            rs_tab = pst_tab.executeQuery();
            while (true) {
                    if (!rs_tab.next()) {
                        break; // Sort de la boucle si aucune valeur égale à g n'est trouvée
                    }
                    
                    g = g+1;
                    pst_tab.setString(1, String.valueOf(g));
                    rs_tab = pst_tab.executeQuery();
                }
            if(rowCount == 0){
                JOptionPane.showMessageDialog(null, "Table de facture vide");
            }
            else
            {
                 String num = String.valueOf(g);
                for (int row = 0; row < rowCount; row++) {
                // Récupérer les données de chaque colonne de la ligne
               // String num_act= String.valueOf(g);
                Object v3 = model.getValueAt(row, 3);  // Colonne 3
                Object v4 = model.getValueAt(row, 4);
                // Colonne 4
                String v5 = model.getValueAt(row, 5).toString(); 
                 String id_ser = id_ser(v5);// Colonne 6
                Object v6 = model.getValueAt(row, 6);  // Colonne 6
                   
                
                    pst = sqlConn.prepareStatement("insert into acte(libelle_acte,num_acte,examen,prix,date_acte,id_patient,id_service,id_users,identite_patient) value(?,?,?,?,?,?,?,?,?)");
                   
                    pst.setObject(1, v3);
                    pst.setString(2,num);
                    pst.setObject(3, v4);
                    pst.setObject(4, v6);
                    pst.setString(5, ladate);
                    pst.setString(6, id_pat);
                    pst.setString(7, id_ser);
                    pst.setString(8, id_us);
                    pst.setString(9, nom.getText().concat(" ").concat(prenom.getText()));
                 
                    pst.executeUpdate();
                   
                    
                }
                 JOptionPane.showMessageDialog(null, "Facture enregistrée");
                 generatePDFReport(num, num,num);
                 nom.setText("");
         prenom.setText("");
         nai.setText("");
         sexe.setText("");
         numero.setText("");
        
            model.setRowCount(0);
            total.setText("0");
            date2.setText("");
                 
            }
                
            } catch (ClassNotFoundException ex) {
               
            } catch (SQLException ex) {
         Logger.getLogger(Form_exam.class.getName()).log(Level.SEVERE, null, ex);
     }
    } 
    }//GEN-LAST:event_jButton1ActionPerformed

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
     
     public void generatePDFReport(String numActe, String recuIdActe, String num) {
  //  String queryPatient = "SELECT nom_patient, prenom_patient FROM recu_consultation,patient WHERE recu_id_acte = ? and recu_id_patient=idpatient ";
    String queryActe = "SELECT num_acte, libelle_acte, examen, prix, date_acte FROM acte WHERE num_acte = ?";
    String queryRecu = "SELECT SUM(prix) FROM acte WHERE num_acte = ?";
   // String queryCaissier = "SELECT nom_users, FROM recu_consultation WHERE recu_id_acte = ?";

    try (Connection connection = DriverManager.getConnection(dataConn,username,password) ;
       //  PreparedStatement pstPatient = connection.prepareStatement(queryPatient);
         PreparedStatement pstActe = connection.prepareStatement(queryActe);
         PreparedStatement pstRecu = connection.prepareStatement(queryRecu);
     //    PreparedStatement pstCaissier = connection.prepareStatement(queryCaissier)
            ) {

        // Récupération des données du patient
      //  pstPatient.setString(1, recuIdActe);
     //   ResultSet patientResultSet = pstPatient.executeQuery();
        String nomPatient = nom.getText();
        String prenomPatient = prenom.getText();
       
          
        

        // Récupération des données de l'acte
        pstActe.setString(1, numActe);
        ResultSet acteResultSet = pstActe.executeQuery();

        // Récupération des données du reçu
        pstRecu.setString(1, recuIdActe);
        ResultSet recuResultSet = pstRecu.executeQuery();

        // Récupération du nom du caissier
//        pstCaissier.setString(1, recuIdActe);
    //    ResultSet caissierResultSet = pstCaissier.executeQuery();
        

        // Création du dossier "Print_recu" à la racine du projet si nécessaire
        String printRecuFolderPath = "../Print_Facture/";
        createDirectoryIfNotExists(printRecuFolderPath);

        // Génération du nom du fichier PDF avec la date et l'heure actuelle
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String currentTime = LocalDateTime.now().format(formatter);
        String fileName = "Facture_paiement_" + nomPatient + "_" + prenomPatient + "_" + num + "_" + currentTime + ".pdf";
        String filePath = printRecuFolderPath + fileName;

        // Création du document PDF
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, new FileOutputStream(filePath));

        // Ouverture du document
        document.open();

        // Titre "Recu de paiement"
        Paragraph title = new Paragraph("Facture de paiement");
        title.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(title);
            Paragraph nm = new Paragraph("Facture de l'acte N° "+num);
            nm.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(nm);
            DateTimeFormatter formatter_2 = DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm:ss");
            String currentTime_2 = LocalDateTime.now().format(formatter_2);
            Paragraph tt = new Paragraph(" "+currentTime_2+" ");
            tt.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(tt);
        // Espacement entre les éléments
        document.add(new Paragraph(" "));

        // Ajout du nom et prénom
        Paragraph name = new Paragraph(prenomPatient + " " + nomPatient);
        name.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(name);

        // Espacement entre les éléments
        document.add(new Paragraph(" "));

        // Titre "Liste des Factures"
        Paragraph title1 = new Paragraph("Tableau de Facturation \n"
                + " "
                + "\n");
        title1.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(title1);

        // Création du premier tableau
        PdfPTable table1 = new PdfPTable(5);
        table1.setWidthPercentage(100);

        // Ajout des en-têtes de colonne du premier tableau
        table1.addCell("Numéro d'acte");
        table1.addCell("Libellé de l'acte");
        table1.addCell("Examen");
        table1.addCell("Prix");
        table1.addCell("Date de l'acte");

        // Ajout des données de l'acte dans le premier tableau
        while (acteResultSet.next()) {
            table1.addCell(acteResultSet.getString("num_acte"));
            table1.addCell(acteResultSet.getString("libelle_acte"));
            table1.addCell(acteResultSet.getString("examen"));
            table1.addCell(acteResultSet.getString("prix").concat(" FrCfa"));
            table1.addCell(acteResultSet.getString("date_acte"));
        }

        // Ajout du premier tableau au document
        document.add(table1);

        // Espacement entre les éléments
        document.add(new Paragraph(" "));

        // Titre "Versement"
        Paragraph title2 = new Paragraph("Net à payer \n"
                + " "
                + "\n");
        
        title2.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(title2);

        // Création du deuxième tableau
        PdfPTable table2 = new PdfPTable(1);
        table2.setWidthPercentage(100);

        // Ajout des en-têtes de colonne du deuxième tableau
        table2.addCell("Montant net à payer");
  

        // Ajout des données du reçu dans le deuxième tableau
        while (recuResultSet.next()) {
            table2.addCell(recuResultSet.getString("SUM(prix)").concat(" FrCfa"));
            
        }

        // Ajout du deuxième tableau au document
        document.add(table2);

        // Espacement entre les éléments
        document.add(new Paragraph(" "));

        // Pied de page avec le nom du caissier
        Paragraph footer = new Paragraph("Agent Admission: " + lbl_agent.getText());
        footer.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(footer);

        // Fermeture du document
        document.close();

        // Ouverture automatique du fichier PDF
        openPDFFile(filePath);

        System.out.println("Reçu de paiement généré avec succès !");
    } catch (SQLException | DocumentException e) {
        System.out.println("Erreur lors de la génération du reçu de paiement : " + e);
    } catch (Exception e) {
        System.out.println("Une erreur inattendue s'est produite : " + e);
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
    private combobox.Combobox acte;
    private javax.swing.JButton add;
    private javax.swing.JButton add1;
    private textfield.textsearch date2;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private textfield.TextField1 lbl_agent;
    private combobox.Combobox lblexam;
    private tabbed.MaterialTabbed materialTabbed1;
    private textfield.TextField1 mt;
    private textfield.TextField1 nai;
    private textfield.TextField1 nom;
    private textfield.TextField1 numero;
    private com.raven.swing.PanelBorder panelBorder1;
    private com.raven.swing.PanelBorder panelBorder2;
    private textfield.TextField1 prenom;
    private javaswingdev.swing.RoundPanel roundPanel1;
    private combobox.Combobox service;
    private textfield.TextField1 sexe;
    private javaswingdev.swing.table.Table table;
    private textfield.TextField1 total;
    // End of variables declaration//GEN-END:variables
}
