package javaswingdev.form;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
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
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

public class Form_prise_contante_ophto extends javax.swing.JPanel {
 private static final String username = "root";
 private static final String password = "primatologue";
 private static final String dataConn = "jdbc:mysql://localhost:3306/hopital";
 private String[] groupe1Selections = new String[4];
 private String[] groupe2Selections = new String[3];
 private String[] groupe3Selections = new String[4];
 
 Connection sqlConn = null;
 Connection sqlSearch = null;
 PreparedStatement pst = null;
 PreparedStatement pst_search = null;
 ResultSet rs = null;
 ResultSet rs_2 = null;
 PreparedStatement pst_tab = null;
 ResultSet rs_tab = null;
 int q, i , id, deleteItem;
    public Form_prise_contante_ophto() {
        initComponents();
        init();
      
        nom.setEditable(false);
        prenom.setEditable(false);
        nai.setEditable(false);
        sexe.setEditable(false);
        infirmier.setEditable(false);
            acte.setEditable(false);
          nacte.setEditable(false);
          
        lbl_ad();
       
    }

    private void init() {
       
     
     //  table2.fixTable(jScrollPane4);
    
        //  init card data
        //card4.setData(new ModelCard(null, null, null, "$ 300.00", "Report Profit Monthly"));
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
                             infirmier.setText(num_id);
                            }
                               
        } catch (Exception e) {
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
         public String lbl_date_acte()
    {
        String num_id = null;
        String id = null;
        id = login.txtid.getText();
          try {
            Class.forName("com.mysql.jdbc.Driver");
                 sqlConn = DriverManager.getConnection(dataConn,username,password);
                 pst = sqlConn.prepareCall("Select * from acte where num_acte = ? and libelle_acte='Consultation'");
                    pst.setString(1, nacte.getText());
                        rs = pst.executeQuery();
                            if (rs.next())
                            {
                               num_id = rs.getString("date_acte");
                             
                            }
                               
        } catch (Exception e) {
        }
          return num_id;
    } 
            
              public void diag (String nbre , String nbre2)
    {
        String nbre_total = null;
     try {
            Class.forName("com.mysql.jdbc.Driver");
                 sqlConn = DriverManager.getConnection(dataConn,username,password);
                pst = sqlConn.prepareCall("SELECT * FROM acte,service,patient where num_acte LIKE ? and service_libelle=? and libelle_acte='Consultation' and diagnostic='Non fait' and id_service=id and id_patient=idpatient");
                pst.setString(1, "%"+nbre+"%");
                pst.setString(2, nbre2);
                 
                                rs = pst.executeQuery();
                 if (rs.next())
                         
                         {
                             nacte.setText(rs.getString("num_acte"));
                             acte.setText(rs.getString("libelle_acte"));
                             nom.setText(rs.getString("nom_patient"));
                             prenom.setText(rs.getString("prenom_patient"));
                             nai.setText(rs.getString("date_naissance"));
                             sexe.setText(rs.getString("sexe"));
                             numero.setText(rs.getString("telephone_patient"));
                       //   nombre.setText(nbre);
                         }
        } catch (Exception e) {
        }
        
        
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
      
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new javaswingdev.swing.RoundPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        pp = new textfield.TextField1();
        jLabel13 = new javax.swing.JLabel();
        pi = new textfield.TextField1();
        jLabel14 = new javax.swing.JLabel();
        adp = new textfield.TextField1();
        jLabel15 = new javax.swing.JLabel();
        mdv = new textfield.TextField1();
        tp = new textfield.TextField1();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        pd = new textfield.TextField1();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        pa1 = new textfield.TextField1();
        pa2 = new textfield.TextField1();
        jLabel22 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        ta = new textfield.TextField1();
        jLabel24 = new javax.swing.JLabel();
        rf = new textfield.TextField1();
        jLabel21 = new javax.swing.JLabel();
        pa3 = new textfield.TextField1();
        jLabel25 = new javax.swing.JLabel();
        pa4 = new textfield.TextField1();
        jLabel26 = new javax.swing.JLabel();
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        panelBorder6 = new com.raven.swing.PanelBorder();
        nacte = new textfield.TextField1();
        jLabel7 = new javax.swing.JLabel();
        acte = new textfield.TextField1();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        infirmier = new textfield.TextField1();

        setOpaque(false);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        roundPanel1.setRound(10);

        jLabel10.setFont(new java.awt.Font("Corbel", 1, 20)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("ETAT DU PATIENT");

        jLabel12.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText(" PLAINTE DU PATIENT");

        pp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ppKeyTyped(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("PRESSION INTRAOCULAIRE");

        pi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                piKeyTyped(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("ANTECEDENT DU PATIENT");

        adp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adpActionPerformed(evt);
            }
        });
        adp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                adpKeyTyped(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("MODE DE VIE");

        mdv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                mdvKeyTyped(evt);
            }
        });

        tp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tpKeyTyped(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setText("TEMPERATURE");

        jLabel17.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText("°C");

        jLabel18.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText("POIDS");

        pd.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pdKeyTyped(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel19.setText("KG");

        jLabel20.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel20.setText("PRESSION ARTERIELLE");

        pa1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pa1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pa1KeyTyped(evt);
            }
        });

        pa2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pa2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pa2KeyTyped(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel22.setText("/");

        jButton1.setBackground(new java.awt.Color(25, 110, 129));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("VALIDER");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel23.setText("TRAITEMENT ANTERIEURE");

        ta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                taKeyTyped(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel24.setText("REFRACTION");

        rf.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        rf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                rfKeyTyped(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel21.setText("VISION");

        pa3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pa3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pa3KeyTyped(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel25.setText("/");

        pa4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pa4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pa4KeyTyped(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel26.setText("mmHg");

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(224, 224, 224)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, roundPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(18, 18, 18)
                                .addComponent(mdv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(adp, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(roundPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addGap(18, 18, 18)
                                        .addComponent(pi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel24)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(rf, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel21)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(pa3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel25)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(pa4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(roundPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel16)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(tp, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel17)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel18)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(pd, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel19)
                                        .addGap(28, 28, 28)
                                        .addComponent(jLabel20)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(pa1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel22)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(pa2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel26)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(2, 2, 2))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, roundPanel1Layout.createSequentialGroup()
                        .addGap(221, 221, 221)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(ta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(pp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(223, 223, 223))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pa1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pa2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(pa3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(pa4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(pi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(rf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(adp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mdv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        date2.setText(" N°Acte");
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
        jLabel3.setText("NOM PATIENT");

        jLabel4.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("PRENOMS PATIENT");

        jLabel5.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("SEXE");

        jLabel6.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("NAISSANCE");

        jLabel11.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("NUMERO PATIENT");

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(nai, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(nom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(sexe, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(numero, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(prenom, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                    .addComponent(numero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        jLabel1.setFont(new java.awt.Font("Corbel", 1, 12)); // NOI18N
        jLabel1.setText("IDENTITE");

        jLabel2.setFont(new java.awt.Font("Corbel", 1, 12)); // NOI18N
        jLabel2.setText("ANALYSE");

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));

        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));

        panelBorder6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("N° ACTE");

        jLabel8.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("ACTE");

        jLabel9.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("INFIRLIER TRAITANT");

        javax.swing.GroupLayout panelBorder6Layout = new javax.swing.GroupLayout(panelBorder6);
        panelBorder6.setLayout(panelBorder6Layout);
        panelBorder6Layout.setHorizontalGroup(
            panelBorder6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder6Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(nacte, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(acte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelBorder6Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(infirmier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelBorder6Layout.setVerticalGroup(
            panelBorder6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nacte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(infirmier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2)
                        .addGap(18, 18, 18)
                        .addComponent(date2, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(panelBorder6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel1))
                    .addComponent(date2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelBorder6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void date2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_date2KeyReleased
        // TODO add your handling code here:
        if(date2.getText().equalsIgnoreCase(" N°Acte") ||  date2.getText().equalsIgnoreCase(""))
        {
         nom.setText("");
         prenom.setText("");
         nai.setText("");
         sexe.setText("");
         numero.setText("");
         nacte.setText("");
         acte.setText("");
       //   DefaultTableModel model = (DefaultTableModel) table.getModel();
            //model.setRowCount(0);
           // total.setText("0");
        }
        else
        {
             diag(date2.getText(), lbl_service());
        }
     
    }//GEN-LAST:event_date2KeyReleased

    private void date2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_date2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_date2ActionPerformed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:
        init();
    }//GEN-LAST:event_formMouseClicked

    
    private void date2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_date2MouseClicked
        // TODO add your handling code here:
        date2.setText("");
    }//GEN-LAST:event_date2MouseClicked

    private void nomKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nomKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_nomKeyReleased

    private void tpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tpKeyTyped
        // TODO add your handling code here:
         char c = evt.getKeyChar();
        if(!(Character.isDigit(c) ))
        {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Saisir uniquement des chiffres");
        }
    }//GEN-LAST:event_tpKeyTyped

    private void pdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pdKeyTyped
        // TODO add your handling code here:
         char c = evt.getKeyChar();
        if(!(Character.isDigit(c) ))
        {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Saisir uniquement des chiffres");
        }
    }//GEN-LAST:event_pdKeyTyped

    private void pa1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pa1KeyTyped
        // TODO add your handling code here:
         char c = evt.getKeyChar();
        if(!(Character.isDigit(c) ))
        {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Saisir uniquement des chiffres");
        }
    }//GEN-LAST:event_pa1KeyTyped

    private void pa2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pa2KeyTyped
        // TODO add your handling code here:
         char c = evt.getKeyChar();
        if(!(Character.isDigit(c) ))
        {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Saisir uniquement des chiffres");
        }
    }//GEN-LAST:event_pa2KeyTyped

    private void ppKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ppKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if(!(Character.isAlphabetic(c)))
        {
            getToolkit().beep();
            evt.consume();
            //JOptionPane.showMessageDialog(null, "Saisir uniquement que le prenom");
        }
    }//GEN-LAST:event_ppKeyTyped

    private void piKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_piKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if(!(Character.isAlphabetic(c)  ))
        {
            getToolkit().beep();
            evt.consume();
            //JOptionPane.showMessageDialog(null, "Saisir uniquement que le prenom");
        }
    }//GEN-LAST:event_piKeyTyped

    private void adpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_adpKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if(!(Character.isAlphabetic(c) ))
        {
            getToolkit().beep();
            evt.consume();
            //JOptionPane.showMessageDialog(null, "Saisir uniquement que le prenom");
        }
    }//GEN-LAST:event_adpKeyTyped

    private void mdvKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mdvKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if(!(Character.isAlphabetic(c) ))
        {
            getToolkit().beep();
            evt.consume();
            //JOptionPane.showMessageDialog(null, "Saisir uniquement que le prenom");
        }
    }//GEN-LAST:event_mdvKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(tp.getText().trim().isEmpty()||pd.getText().trim().isEmpty()||pa1.getText().trim().isEmpty()||pa2.getText().trim().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Information imcomplet pour la prise de constante");
        }
        else if(33>Integer.parseInt(tp.getText()) || Integer.parseInt(tp.getText())>40)
        {
            JOptionPane.showMessageDialog(null, "Erreur de temperature");
        }
        else if(Integer.parseInt(tp.getText())>999)
        {
            JOptionPane.showMessageDialog(null, "Erreur de poids");
        }
        else if(Integer.parseInt(pa1.getText())<Integer.parseInt(pa2.getText()) || Integer.parseInt(pa1.getText())>170 || Integer.parseInt(pa1.getText())<90 )
        {
            JOptionPane.showMessageDialog(null, "Erreur de pression artérielle");
        }
        else
        {
            update();
            JOptionPane.showMessageDialog(null, "Analyse de constante enrégistré");
            String pa = pa1.getText().concat("/").concat(pa2.getText());
             String vs = pa3.getText().concat("/").concat(pa4.getText());
            generatePDFReport(nacte.getText(),nom.getText(), prenom.getText(),tp.getText(), pd.getText(), pa,pi.getText(),rf.getText(),vs, adp.getText(), mdv.getText(), pp.getText(), ta.getText(), infirmier.getText());
                                    nacte.setText("");
                                   acte.setText("");
                                   nom.setText("");
                                   prenom.setText("");
                                   nai.setText("");
                                   sexe.setText("");
                                   numero.setText("");
                                   date2.setText("N°Acte");
                                   tp.setText("");
                                   pd.setText("");
                                   pa1.setText("");
                                   pa2.setText("");
                                   pi.setText("");
                                   adp.setText("");
                                   mdv.setText("");
                                   pp.setText("");
                                    pi.setText("");
                                     rf.setText("");
                                      pa3.setText("");
                                       pa4.setText("");
                                   
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void taKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_taKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_taKeyTyped

    private void adpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_adpActionPerformed

    private void rfKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rfKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_rfKeyTyped

    private void pa3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pa3KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_pa3KeyTyped

    private void pa4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pa4KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_pa4KeyTyped

     public void update()
    {
        try {
                Date d = new Date();
            SimpleDateFormat dat = new SimpleDateFormat("dd-MM-yyyy");
            String ladate = dat.format(d);
             Class.forName("com.mysql.jdbc.Driver");
         sqlConn = DriverManager.getConnection(dataConn,username,password);
          pst_search = sqlConn.prepareStatement("Update acte set diagnostic = 'Fait',date_diag=? where num_acte = ? and libelle_acte = 'Consultation'");
                                   pst_search.setString(1, ladate);
                                   pst_search.setString(2,nacte.getText());
                                   pst_search.executeUpdate();
                                       
        } catch (Exception e) {
        }
    }
    
     
   
    
  
    public void generatePDFReport(String num, String nomPatient, String prenomPatient, String constante1,String constante2,String constante3,String constante4,String constante5,String constante6,String informations1,String informations2,String informations3,String informations4,
            String infirmier) {
        try (Connection connection = DriverManager.getConnection(dataConn, username, password);) {

            // Création du dossier "Print_recu" à la racine du projet si nécessaire
            String printRecuFolderPath = "./Print_Analyse_constante_ophtamologie/";
            createDirectoryIfNotExists(printRecuFolderPath);

            // Génération du nom du fichier PDF avec la date et l'heure actuelle
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
            String currentTime = LocalDateTime.now().format(formatter);
            String fileName = "Analyse_Prise_Constante_ohptamologie_" + nomPatient + "_" + prenomPatient + "_" + num + "_" + currentTime + ".pdf";
            String filePath = printRecuFolderPath + fileName;

            // Création du document PDF
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, new FileOutputStream(filePath));

            // Ouverture du document
            document.open();

            // Titre "Analyse de Prise de Constante"
            Paragraph title = new Paragraph("Analyse de Prise de Constante ");
            title.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(title);

            Paragraph nm = new Paragraph("CONSULTATION N° "+num+" Servcie Ophtamologie date "+lbl_date_acte() );
            nm.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(nm);
            DateTimeFormatter formatter_2 = DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm:ss");
            String currentTime_2 = LocalDateTime.now().format(formatter_2);
            Paragraph tt = new Paragraph(" "+currentTime_2+" ");
            tt.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(tt);
            
            // Espacement entre les éléments
            document.add(new Paragraph(" "));
            

            // Ajout du nom et prénom du patient
            Paragraph name = new Paragraph(prenomPatient + " " + nomPatient);
            name.setAlignment(Paragraph.ALIGN_LEFT);
            document.add(name);

            // Espacement entre les éléments
            document.add(new Paragraph(" "));

            // Premier tableau - Constantes
            PdfPTable table1 = new PdfPTable(3);
            table1.setWidthPercentage(100);

            // Ajout des en-têtes de colonne du premier tableau
            table1.addCell("Température");
            table1.addCell("Poids");
            table1.addCell("Pression Artérielle");
            table1.addCell("Pression Intraoculaire");
            table1.addCell("Refraction");
            table1.addCell("Vision");

            // Ajout des données de constantes dans le premier tableau
            table1.addCell(constante1.concat(" °C"));
            table1.addCell(constante2.concat(" Kg"));
            table1.addCell(constante3.concat(" mmHG"));
            table1.addCell(constante4);
            table1.addCell(constante5);
            table1.addCell(constante6);

            // Ajout du premier tableau au document
            document.add(table1);

            // Espacement entre les éléments
            document.add(new Paragraph(" "));

            // Deuxième tableau - Informations
            PdfPTable table2 = new PdfPTable(4);
            table2.setWidthPercentage(100);

            // Ajout des en-têtes de colonne du deuxième tableau
            table2.addCell("Antécédents du patient");
            table2.addCell("Mode de vie");
            table2.addCell("Plainte du patient");
            table2.addCell("Traitement antérieur");

            // Ajout des données d'informations dans le deuxième tableau
            table2.addCell(informations1);
            table2.addCell(informations2);
            table2.addCell(informations3);
            table2.addCell(informations4);

            // Ajout du deuxième tableau au document
            document.add(table2);

            // Espacement entre les éléments
            document.add(new Paragraph(" "));

            // Pied de page avec le nom de l'infirmier
            Paragraph footer = new Paragraph("Infirmier: " + infirmier);
            footer.setAlignment(Paragraph.ALIGN_LEFT);
            document.add(footer);

            // Fermeture du document
            document.close();

            // Ouverture automatique du fichier PDF
            openPDFFile(filePath);

            System.out.println("Analyse de Prise de Constante générée avec succès !");
        } catch (SQLException | DocumentException e) {
            System.out.println("Erreur lors de la génération de l'analyse de Prise de Constante : " + e);
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
    private textfield.TextField1 acte;
    private textfield.TextField1 adp;
    private textfield.textsearch date2;
    private textfield.TextField1 infirmier;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private textfield.TextField1 mdv;
    private textfield.TextField1 nacte;
    private textfield.TextField1 nai;
    private textfield.TextField1 nom;
    private textfield.TextField1 numero;
    private textfield.TextField1 pa1;
    private textfield.TextField1 pa2;
    private textfield.TextField1 pa3;
    private textfield.TextField1 pa4;
    private com.raven.swing.PanelBorder panelBorder1;
    private com.raven.swing.PanelBorder panelBorder6;
    private textfield.TextField1 pd;
    private textfield.TextField1 pi;
    private textfield.TextField1 pp;
    private textfield.TextField1 prenom;
    private textfield.TextField1 rf;
    private javaswingdev.swing.RoundPanel roundPanel1;
    private textfield.TextField1 sexe;
    private textfield.TextField1 ta;
    private textfield.TextField1 tp;
    // End of variables declaration//GEN-END:variables
}
