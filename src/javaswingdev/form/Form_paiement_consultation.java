package javaswingdev.form;

import java.awt.Color;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Cursor;
import java.awt.Desktop;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.io.File;
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
import javaswingdev.main.recu;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

public class Form_paiement_consultation extends javax.swing.JPanel {
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
    public Form_paiement_consultation() {
        initComponents();
        init();
        verifie();
      //  verifie2();
        net.setEditable(false);
        reste.setEditable(false);
        nconsul.setEditable(false);
        identite.setEditable(false);
        service.setEditable(false);
        analyse.setEditable(false);
        agentad.setEditable(false);
        caissier.setEditable(false);
        caissier.setText(agentcai());
    }

    private void init() {
       table.fixTable(jScrollPane1);
       tab_all_consultation();
        table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       valider.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
      
       
        //  init card data
//        card1.setData(new ModelCard(GoogleMaterialDesignIcon.PERSON, null, null, membre_personnel_actif(), "Membres total actif"));
  //      card2.setData(new ModelCard(GoogleMaterialDesignIcon.ACCOUNT_BOX, null, null, nombre_service_actif(), "Nombres compte créer"));
    //    card3.setData(new ModelCard(GoogleMaterialDesignIcon.WORK, null, null, nombre_compte_actif(), "Services diponible"));
        //card4.setData(new ModelCard(null, null, null, "$ 300.00", "Report Profit Monthly"));
    }
         

       public void tab_all_consultation ()
    {
          DefaultTableModel model = (DefaultTableModel) table.getModel();
            
            model.setRowCount(0);
            
            try {
                Class.forName("com.mysql.jdbc.Driver");
                sqlConn = DriverManager.getConnection(dataConn,username,password);
                 pst_tab = sqlConn.prepareStatement("Select * from acte,service,patient where id_service = id and id_patient=idpatient and etat='Non Payer' ORDER BY idacte ASC ");
               // pst_tab = sqlConn.prepareStatement("Select * from service,consultation,service where  id_consultation = idconsultation and idservice = id");
                rs_tab = pst_tab.executeQuery () ;
                
                while (rs_tab.next()) {
                    String column1Data = rs_tab.getString("num_acte");
                    String column2Data = rs_tab.getString("nom_patient");
                    String column3Data = rs_tab.getString("prenom_patient");
                    String column4Data = rs_tab.getString("telephone_patient");            
                    String column5Data = rs_tab.getString("service_libelle");
                    String column6Data = rs_tab.getString("libelle_acte");
                    String column7Data = rs_tab.getString("examen");
                    String column8Data = rs_tab.getString("prix");
                    String column9Data = rs_tab.getString("date_acte");
                     String column10Data = rs_tab.getString("etat").toUpperCase();
                 
                    // Ajouter d'autres colonnes si nécessaire

                    model.addRow(new Object[]{column1Data, column2Data, column3Data, column4Data, column5Data, column6Data, column7Data,column8Data,column9Data,column10Data});
               }
            TableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
        @Override
        public java.awt.Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            java.awt.Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            String etatService = (String) value;
            if (etatService.equalsIgnoreCase("PAYER")) {
                cellComponent.setForeground(Color.GREEN); // Change la couleur du texte en vert
            } else {
                cellComponent.setForeground(Color.RED); // Change la couleur du texte en rouge
            }

            return cellComponent;
        }
    };

    // Appliquer le rendu personnalisé à la dernière colonne
    int lastColumnIndex = model.getColumnCount() - 1;
    table.getColumnModel().getColumn(lastColumnIndex).setCellRenderer(cellRenderer);
}catch (Exception e) {
    e.printStackTrace();
}
    } 
      public void selectab(String nbre)
        {
              int selectedRowIndex = table.getSelectedRow();
                TableModel model = table.getModel();  
                String mt = model.getValueAt(selectedRowIndex, 6).toString();
                net.setText(mt);
               // TODO add your handling code here:
        Date d = new Date();
        SimpleDateFormat dat = new SimpleDateFormat("dd-MM-yyyy");
        String ladate = dat.format(d);
       // java.sql.Date ladate = new java.sql.Date(d.getTime());
       
     //   String id_users = num_id() ;
        
       

        }
       public String agentcai()
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
                            }
                               
        } catch (Exception e) {
        }
          return num_id;
    }         
    
        public String agentad(String id)
    {
        String num_id = null;
       // String id = null;
        //id = login.txtid.getText();
          try {
            Class.forName("com.mysql.jdbc.Driver");
                 sqlConn = DriverManager.getConnection(dataConn,username,password);
                 pst = sqlConn.prepareCall("Select * from users where idUser = ? ");
                    pst.setString(1, id);
                        rs = pst.executeQuery();
                            if (rs.next())
                            {
                               num_id = rs.getString("nom_users").concat(" ").concat(rs.getString("prenom_users"));
                            }
                               
        } catch (Exception e) {
        }
          return num_id;
    }         
    
        public String service(String id)
    {
        String num_id = null;
       // String id = null;
        //id = login.txtid.getText();
          try {
            Class.forName("com.mysql.jdbc.Driver");
                 sqlConn = DriverManager.getConnection(dataConn,username,password);
                 pst = sqlConn.prepareCall("Select * from consultation,service where id = ? and id_service=id ");
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
    
      public String idexamen()
    {
        String num_id = null;
        String id = null;
        id = analyse.getText();
          try {
            Class.forName("com.mysql.jdbc.Driver");
                 sqlConn = DriverManager.getConnection(dataConn,username,password);
                 pst = sqlConn.prepareCall("Select * from type_analyse where libelle_analyse = ?");
                    pst.setString(1, id);
                        rs = pst.executeQuery();
                            if (rs.next())
                            {
                               num_id = rs.getString("idtype_analyse");
                            }
                               
        } catch (Exception e) {
        }
          return num_id;
    }         
    
       
       
       public String somme (String nbre)
    {
        String nbre_total = null;
        

     try {
            Class.forName("com.mysql.jdbc.Driver");
                 sqlConn = DriverManager.getConnection(dataConn,username,password);
 pst = sqlConn.prepareCall("SELECT SUM(prix) FROM acte where num_acte LIKE ? and etat='Non Payer'");
            pst.setString(1, "%"+nbre+"%");
                 rs = pst.executeQuery();
                 if (rs.next())
                         
                         {
                              nbre_total = rs.getString("SUM(prix)");
                       //   nombre.setText(nbre);
                         }
        } catch (Exception e) {
        }
        return nbre_total;
    }   
       
       public void valide (String nbre)
    {
        String nbre_total = null;
        if(nbre.equalsIgnoreCase("")|| nbre.equals(nbre_total))
        {
             nconsul.setText("");
                    identite.setText("");
                        analyse.setText("");
                            service.setText("");
                                agentad.setText("");
                                  
        }
        
        else 
        {
             try {
            Class.forName("com.mysql.jdbc.Driver");
                 sqlConn = DriverManager.getConnection(dataConn,username,password);
 pst = sqlConn.prepareCall("SELECT * FROM acte,service,patient,users where num_acte LIKE ? and id_service=id and id_patient=idpatient and id_users=idUser");
            pst.setString(1, "%"+nbre+"%");
                 rs = pst.executeQuery();
                 if (rs.next())  
                         {
                            verse.setText("");
                             reste.setText("");
                String idcon = rs.getString("num_acte");
                 String patient = rs.getString("nom_patient").concat(" ").concat(rs.getString("prenom_patient"));
                  String exam = rs.getString("libelle_acte");
                   String lble_ser = rs.getString("service_libelle");
                  // String cai = agentcai();
                   String agad = agentad(rs.getString("id_users"));
                 nconsul.setText(idcon);
                    identite.setText(patient);
                        analyse.setText(exam);
                            service.setText(lble_ser);
                            agentad.setText(agad);
                             caissier.setText(agentcai());
                            
                         }
        } catch (Exception e) {
        }
        }
    }   
      
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new javaswingdev.swing.RoundPanel();
        materialTabbed1 = new tabbed.MaterialTabbed();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javaswingdev.swing.table.Table();
        textsearch1 = new textfield.textsearch();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        panelBorder1 = new com.raven.swing.PanelBorder();
        jLabel4 = new javax.swing.JLabel();
        nconsul = new textfield.TextField1();
        jLabel5 = new javax.swing.JLabel();
        identite = new textfield.TextField1();
        jLabel9 = new javax.swing.JLabel();
        service = new textfield.TextField1();
        analyse = new textfield.TextField1();
        jLabel11 = new javax.swing.JLabel();
        agentad = new textfield.TextField1();
        jLabel12 = new javax.swing.JLabel();
        caissier = new textfield.TextField1();
        jLabel13 = new javax.swing.JLabel();
        panelBorder2 = new com.raven.swing.PanelBorder();
        net = new textfield.TextField1();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        reste = new textfield.TextField1();
        jLabel7 = new javax.swing.JLabel();
        verse = new textfield.TextField1();
        valider = new javax.swing.JButton();

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
                "#Num Acte", "Nom", "Prenom", "Numero", "Service", "Acte", "Examen", "Montant", "Date examen", "Etat"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
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

        materialTabbed1.addTab("Reçu de paiement des consultations", jScrollPane1);

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(materialTabbed1, javax.swing.GroupLayout.DEFAULT_SIZE, 910, Short.MAX_VALUE)
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(materialTabbed1, javax.swing.GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE)
                .addContainerGap())
        );

        textsearch1.setText("Recherche");
        textsearch1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textsearch1MouseClicked(evt);
            }
        });
        textsearch1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textsearch1ActionPerformed(evt);
            }
        });
        textsearch1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textsearch1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textsearch1KeyTyped(evt);
            }
        });

        jLabel1.setText("PAIEMENT");

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("N° Acte");

        nconsul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nconsulActionPerformed(evt);
            }
        });
        nconsul.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nconsulKeyReleased(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("IDENTITE");

        jLabel9.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("CAISSIER");

        analyse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                analyseActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("AGENT  AD");

        agentad.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("SERVICE");

        caissier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                caissierActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("ACTE");

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(panelBorder1Layout.createSequentialGroup()
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(nconsul, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelBorder1Layout.createSequentialGroup()
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(service, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(agentad, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(analyse, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(identite, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(caissier, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nconsul, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(identite, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(service, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(analyse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(agentad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(caissier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        panelBorder2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("NET A PAYER");

        jLabel6.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("MONNAIE");

        reste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resteActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("VERSER");

        verse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verseActionPerformed(evt);
            }
        });
        verse.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                verseKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                verseKeyTyped(evt);
            }
        });

        valider.setBackground(new java.awt.Color(42, 135, 54));
        valider.setFont(new java.awt.Font("Corbel", 1, 18)); // NOI18N
        valider.setForeground(new java.awt.Color(255, 255, 255));
        valider.setText("VALIDER");
        valider.setBorder(null);
        valider.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                validerMouseClicked(evt);
            }
        });
        valider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                validerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBorder2Layout = new javax.swing.GroupLayout(panelBorder2);
        panelBorder2.setLayout(panelBorder2Layout);
        panelBorder2Layout.setHorizontalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(valider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelBorder2Layout.createSequentialGroup()
                        .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelBorder2Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(reste, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                            .addComponent(net, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(verse, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelBorder2Layout.setVerticalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(net, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(verse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(reste, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(valider, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roundPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(textsearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(panelBorder2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textsearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelBorder2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void textsearch1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textsearch1MouseClicked
        // TODO add your handling code here:
        textsearch1.setText("");
    }//GEN-LAST:event_textsearch1MouseClicked

    private void textsearch1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textsearch1KeyReleased
        // TODO add your handling code here:
        //  String name = Login_form.txtid.getText();
        if (textsearch1.getText().trim().isEmpty() || textsearch1.getText().equalsIgnoreCase("recherche")){
            nconsul.setText("");
                    identite.setText("");
                        analyse.setText("");
                            service.setText("");
                                agentad.setText("");
            net.setText("");
            tab_all_consultation();

        }
        else{
            DefaultTableModel model = (DefaultTableModel) table.getModel();

            model.setRowCount(0);

            try {
          Class.forName("com.mysql.jdbc.Driver");
    sqlConn = DriverManager.getConnection(dataConn,username,password);
     //String sql = "SELECT * FROM  factureconsultation,service,type_analyse WHERE date BETWEEN ? AND ? and idservice = id and type_analyse=idtype_analyse";
     pst_tab = sqlConn.prepareStatement("SELECT * FROM acte,service,patient where num_acte LIKE ? and etat='Non Payer' and id_service=id and id_patient=idpatient");
     pst_tab.setString(1, "%"+textsearch1.getText()+"%");
     rs_tab = pst_tab.executeQuery () ;
    ResultSetMetaData stData = rs_tab.getMetaData () ;
    q = stData. getColumnCount () ;
    DefaultTableModel RecordTable = (DefaultTableModel)table.getModel();
    RecordTable.setRowCount(0);
     while(rs_tab.next()){
     Vector columnData= new Vector();
        for (i=1; i<=q ; i++){
            columnData.add(rs_tab.getString("num_acte"));
            columnData.add(rs_tab.getString("nom_patient"));
            columnData.add(rs_tab.getString("prenom_patient"));
            columnData.add(rs_tab.getString("telephone_patient"));
            columnData.add(rs_tab.getString("service_libelle"));
            columnData.add(rs_tab.getString("libelle_acte"));
            columnData.add(rs_tab.getString("examen"));
            columnData.add(rs_tab.getString("prix"));
             columnData.add(rs_tab.getString("date_acte"));
             columnData.add(rs_tab.getString("etat").toUpperCase());
            
     }
        RecordTable.addRow(columnData);
        net.setText(somme(textsearch1.getText()));
        valide(textsearch1.getText());
     }
        TableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
        @Override
        public java.awt.Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            java.awt.Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            String etatService = (String) value;
            if (etatService.equalsIgnoreCase("PAYER")) {
                cellComponent.setForeground(Color.GREEN); // Change la couleur du texte en vert
            } else {
                cellComponent.setForeground(Color.RED); // Change la couleur du texte en rouge
            }

            return cellComponent;
        }
    };

    // Appliquer le rendu personnalisé à la dernière colonne
    int lastColumnIndex = RecordTable.getColumnCount() - 1;
    table.getColumnModel().getColumn(lastColumnIndex).setCellRenderer(cellRenderer);
}catch (Exception e) {
    e.printStackTrace();
}
        }
    }//GEN-LAST:event_textsearch1KeyReleased

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:
         // nconsul.setText("");
         //           identite.setText("");
         //               analyse.setText("");
         //                   service.setText("");
         //                       agentad.setText("");
         //   net.setText("");
        //tab_all_consultation();
        
    }//GEN-LAST:event_formMouseClicked

    private void textsearch1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textsearch1KeyTyped
        // TODO add your handling code here:
         char c = evt.getKeyChar();
        if(!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE)|| c== KeyEvent.VK_DELETE))
        {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Saisir uniquement que le numero de la consultation");
        }
    }//GEN-LAST:event_textsearch1KeyTyped

    private void analyseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_analyseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_analyseActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        // TODO add your handling code here:
       // net.setText("");
        int selectedRowIndex = table.getSelectedRow();
                                    TableModel model = table.getModel();
                                   
                                    String num_acte = model.getValueAt(selectedRowIndex, 0).toString();
                                    somme(num_acte);
                                    textsearch1.setText(num_acte);
              try {
          Class.forName("com.mysql.jdbc.Driver");
    sqlConn = DriverManager.getConnection(dataConn,username,password);
     //String sql = "SELECT * FROM  factureconsultation,service,type_analyse WHERE date BETWEEN ? AND ? and idservice = id and type_analyse=idtype_analyse";
     pst_tab = sqlConn.prepareStatement("SELECT * FROM acte,service,patient where num_acte LIKE ? and etat='Non Payer' and id_service=id and id_patient=idpatient");
     pst_tab.setString(1, "%"+textsearch1.getText()+"%");
     rs_tab = pst_tab.executeQuery () ;
    ResultSetMetaData stData = rs_tab.getMetaData () ;
    q = stData. getColumnCount () ;
    DefaultTableModel RecordTable = (DefaultTableModel)table.getModel();
    RecordTable.setRowCount(0);
     while(rs_tab.next()){
     Vector columnData= new Vector();
        for (i=1; i<=q ; i++){
            columnData.add(rs_tab.getString("num_acte"));
            columnData.add(rs_tab.getString("nom_patient"));
            columnData.add(rs_tab.getString("prenom_patient"));
            columnData.add(rs_tab.getString("telephone_patient"));
            columnData.add(rs_tab.getString("service_libelle"));
            columnData.add(rs_tab.getString("libelle_acte"));
            columnData.add(rs_tab.getString("examen"));
            columnData.add(rs_tab.getString("prix"));
             columnData.add(rs_tab.getString("date_acte"));
             columnData.add(rs_tab.getString("etat").toUpperCase());
            
     }
        RecordTable.addRow(columnData);
        net.setText(somme(textsearch1.getText()));
        valide(textsearch1.getText());
     }
        TableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
        @Override
        public java.awt.Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            java.awt.Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            String etatService = (String) value;
            if (etatService.equalsIgnoreCase("PAYER")) {
                cellComponent.setForeground(Color.GREEN); // Change la couleur du texte en vert
            } else {
                cellComponent.setForeground(Color.RED); // Change la couleur du texte en rouge
            }

            return cellComponent;
        }
    };

    // Appliquer le rendu personnalisé à la dernière colonne
    int lastColumnIndex = RecordTable.getColumnCount() - 1;
    table.getColumnModel().getColumn(lastColumnIndex).setCellRenderer(cellRenderer);
}catch (Exception e) {
    e.printStackTrace();
}
    }//GEN-LAST:event_tableMouseClicked

    private void verseKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_verseKeyReleased
        // TODO add your handling code here:
        
        
        if(verse.getText().equals(""))
        {
            valider.setEnabled(false);
            //verse.setText("0");
            reste.setForeground(Color.red);
            reste.setText("0");
            
        }
        else if(net.getText().equalsIgnoreCase(""))
        {
            valider.setEnabled(false);
            //verse.setText("0");
            reste.setForeground(Color.red);
            reste.setText("0");
        }
        
        else{
        int nt = Integer.parseInt(net.getText());
        int mt = Integer.parseInt(verse.getText());
        if(mt<nt)
        {
            valider.setEnabled(false);
            reste.setForeground(Color.red);
            reste.setText("Montant insufisant");
        }
        else{
            int rest = nt - mt;
            if(rest<0)
        {
            reste.setForeground(Color.red);
            valider.setEnabled(true);
            rest=rest*-1;
            String rst= String.valueOf(rest);
            reste.setText(rst);
        }
            else
            {
                reste.setForeground(Color.green);
            valider.setEnabled(true);
            String rst= String.valueOf(rest);
            reste.setText(rst);
            }
        }
        
    }
    }//GEN-LAST:event_verseKeyReleased

    public void verifie()
    {
         if(verse.getText().trim().isEmpty())
        {
            valider.setEnabled(false);
        }
    }
    
       
    private void verseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_verseActionPerformed

    private void verseKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_verseKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if(!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE)|| c== KeyEvent.VK_DELETE))
        {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Saisir uniquement que le montant");
        }
    }//GEN-LAST:event_verseKeyTyped
private JFrame frame;
    public void update()
    {
        try {
             Class.forName("com.mysql.jdbc.Driver");
         sqlConn = DriverManager.getConnection(dataConn,username,password);
          pst_search = sqlConn.prepareStatement("Update acte set etat = 'Payer' where num_acte = ? ");
                                   pst_search.setString(1,nconsul.getText());
                                   pst_search.executeUpdate();
                                        tab_all_consultation();
                                        net.setEditable(false);
                                            reste.setText("");
                                            nconsul.setText("");
                                            identite.setText("");
                                            service.setText("");
                                            analyse.setText("");
                                            agentad.setText("");
                                            //caissier.setText("");
                                            verse.setText("");
                                            net.setText("");
                                            verse.setText("");
                                            textsearch1.setText("");
//                                            verifie2();
                                            verifie();
        } catch (Exception e) {
        }
    }
    private void nconsulKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nconsulKeyReleased
        // TODO add your handling code here:
        if(nconsul.getText().equals(""))
        {
            verse.setEditable(false);
        }
        else
        {
            verse.setEditable(true);
        }
    }//GEN-LAST:event_nconsulKeyReleased

    private void resteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_resteActionPerformed

    private void nconsulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nconsulActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nconsulActionPerformed

    private void caissierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_caissierActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_caissierActionPerformed

    private void textsearch1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textsearch1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textsearch1ActionPerformed

    private void validerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_validerMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_validerMouseClicked

    private void validerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_validerActionPerformed
       

        String n  = "0";
        int g = Integer.parseInt(n);
        Date d = new Date();
        SimpleDateFormat dat = new SimpleDateFormat("dd-MM-yyyy");
        dat.setTimeZone(TimeZone.getTimeZone("UTC")); // Définit la zone horaire à UTC
        String ladate = dat.format(d);
       ;
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int rowCount = model.getRowCount();
        // java.sql.Date ladate = new java.sql.Date(d.getTime());
       if(JOptionPane.showConfirmDialog(frame, "Confirmer le paiement pour l'examen de "+identite.getText(),"Avertissement",
            JOptionPane.YES_NO_OPTION)== JOptionPane.YES_OPTION)
    {
        
             try {
            Class.forName("com.mysql.jdbc.Driver");
            sqlConn = DriverManager.getConnection(dataConn,username,password);
            pst = sqlConn.prepareCall("Select * from acte,patient where num_acte = ? and idpatient=id_patient");
            pst.setString(1, nconsul.getText());
            rs=pst.executeQuery();
            if(rs.next())
            {
                pst_tab = sqlConn.prepareStatement("insert into recu_consultation(montant_net,date_recu,acte,montant_verse,reste,caissier,idservice,recu_id_acte,recu_id_patient) value(?,?,?,?,?,?,?,?,?)");
                pst_tab.setString(1,net.getText() );
                pst_tab.setString(2, ladate);
                pst_tab.setString(3, analyse.getText());
                pst_tab.setString(4,verse.getText());
                pst_tab.setString(5,reste.getText());
                pst_tab.setString(6, caissier.getText());
                pst_tab.setString(7,rs.getString("id_service"));
                pst_tab.setString(8, nconsul.getText());
                pst_tab.setString(9, rs.getString("id_patient"));
                pst_tab.executeUpdate();
               
                JOptionPane.showMessageDialog(null, "Payement effectué");
               
                generatePDFReport(nconsul.getText(),nconsul.getText());
                 update();
            }

            } catch (ClassNotFoundException ex) {

            } catch (SQLException ex) {
                Logger.getLogger(Form_paiement_consultation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_validerActionPerformed
//private JFrame frame;
 







public void generatePDFReport(String numActe, String recuIdActe) {
    String queryPatient = "SELECT nom_patient, prenom_patient FROM recu_consultation,patient WHERE recu_id_acte = ? and recu_id_patient=idpatient ";
    String queryActe = "SELECT num_acte, libelle_acte, examen, prix, date_acte,service_libelle FROM acte,service WHERE num_acte = ? and id_service=id";
    String queryRecu = "SELECT montant_net, montant_verse, reste, date_recu FROM recu_consultation WHERE recu_id_acte = ?";
    String queryCaissier = "SELECT caissier FROM recu_consultation WHERE recu_id_acte = ?";

    try (Connection connection = DriverManager.getConnection(dataConn,username,password) ;
         PreparedStatement pstPatient = connection.prepareStatement(queryPatient);
         PreparedStatement pstActe = connection.prepareStatement(queryActe);
         PreparedStatement pstRecu = connection.prepareStatement(queryRecu);
         PreparedStatement pstCaissier = connection.prepareStatement(queryCaissier)) {

        // Récupération des données du patient
        pstPatient.setString(1, recuIdActe);
        ResultSet patientResultSet = pstPatient.executeQuery();
        String nomPatient = "";
        String prenomPatient = "";
        String num_ad = numActe;
        if (patientResultSet.next()) {
            nomPatient = patientResultSet.getString("nom_patient");
            prenomPatient = patientResultSet.getString("prenom_patient");
        }

        // Récupération des données de l'acte
        pstActe.setString(1, numActe);
        ResultSet acteResultSet = pstActe.executeQuery();

        // Récupération des données du reçu
        pstRecu.setString(1, recuIdActe);
        ResultSet recuResultSet = pstRecu.executeQuery();

        // Récupération du nom du caissier
        pstCaissier.setString(1, recuIdActe);
        ResultSet caissierResultSet = pstCaissier.executeQuery();
        String caissier = "";
        if (caissierResultSet.next()) {
            caissier = caissierResultSet.getString("caissier");
        }

        // Création du dossier "Print_recu" à la racine du projet si nécessaire
        String printRecuFolderPath = "./Print_recu/";
        createDirectoryIfNotExists(printRecuFolderPath);

        // Génération du nom du fichier PDF avec la date et l'heure actuelle
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String currentTime = LocalDateTime.now().format(formatter);
        String fileName = "Recu_paiement_" + nomPatient + "_" + prenomPatient + "_" +num_ad + "_" + currentTime + ".pdf";
        String filePath = printRecuFolderPath + fileName;

        // Création du document PDF
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, new FileOutputStream(filePath));

        // Ouverture du document
        document.open();

        // Titre "Recu de paiement"
        Paragraph title = new Paragraph("CONFIRMATION DE PAYEMENT");
        title.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(title);
            Paragraph nm = new Paragraph("Reçu de l'acte N° "+nconsul.getText());
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
                + ""
                + " \n");
        title1.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(title1);

        // Création du premier tableau
        PdfPTable table1 = new PdfPTable(6);
        table1.setWidthPercentage(100);

        // Ajout des en-têtes de colonne du premier tableau
        table1.addCell("Numéro d'acte");
        table1.addCell("Libellé de l'acte");
        table1.addCell("Examen");
        table1.addCell("Service");
        table1.addCell("Prix");
        table1.addCell("Date de l'acte");

        // Ajout des données de l'acte dans le premier tableau
        while (acteResultSet.next()) {
            table1.addCell(acteResultSet.getString("num_acte"));
            table1.addCell(acteResultSet.getString("libelle_acte"));
            table1.addCell(acteResultSet.getString("examen"));
            table1.addCell(acteResultSet.getString("service_libelle"));
            table1.addCell(acteResultSet.getString("prix").concat(" FrCfa"));
            table1.addCell(acteResultSet.getString("date_acte"));
        }

        // Ajout du premier tableau au document
        document.add(table1);

        // Espacement entre les éléments
        document.add(new Paragraph(" "));

        // Titre "Versement"
        Paragraph title2 = new Paragraph("Payer \n"
                + ""
                + " \n");
        title2.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(title2);

        // Création du deuxième tableau
        PdfPTable table2 = new PdfPTable(4);
        table2.setWidthPercentage(100);

        // Ajout des en-têtes de colonne du deuxième tableau
        table2.addCell("Montant net");
        table2.addCell("Montant versé");
        table2.addCell("Monnaie");
        table2.addCell("Date du reçu");

        // Ajout des données du reçu dans le deuxième tableau
        while (recuResultSet.next()) {
            table2.addCell(recuResultSet.getString("montant_net").concat(" FrCfa"));
            table2.addCell(recuResultSet.getString("montant_verse").concat(" FrCfa"));
            table2.addCell(recuResultSet.getString("reste").concat(" FrCfa"));
            table2.addCell(recuResultSet.getString("date_recu"));
        }

        // Ajout du deuxième tableau au document
        document.add(table2);

        // Espacement entre les éléments
        document.add(new Paragraph(" "));

        // Pied de page avec le nom du caissier
        Paragraph footer = new Paragraph("Caissier: " + caissier);
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
    private textfield.TextField1 agentad;
    private textfield.TextField1 analyse;
    private textfield.TextField1 caissier;
    private textfield.TextField1 identite;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private tabbed.MaterialTabbed materialTabbed1;
    public static textfield.TextField1 nconsul;
    private textfield.TextField1 net;
    private com.raven.swing.PanelBorder panelBorder1;
    private com.raven.swing.PanelBorder panelBorder2;
    private textfield.TextField1 reste;
    private javaswingdev.swing.RoundPanel roundPanel1;
    private textfield.TextField1 service;
    private javaswingdev.swing.table.Table table;
    private textfield.textsearch textsearch1;
    private javax.swing.JButton valider;
    private textfield.TextField1 verse;
    // End of variables declaration//GEN-END:variables
}
