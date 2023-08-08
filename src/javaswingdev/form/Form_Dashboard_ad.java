package javaswingdev.form;

import java.awt.Color;
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
import java.awt.Component;
import java.awt.Cursor;
import java.awt.print.PrinterException;
import java.io.File;
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
import java.util.Vector;
import javaswingdev.GoogleMaterialDesignIcon;
import javaswingdev.card.ModelCard;
import javaswingdev.main.login;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

public class Form_Dashboard_ad extends javax.swing.JPanel {
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
    public Form_Dashboard_ad() {
        initComponents();
        init();
    }

    private void init() {
       table.fixTable(jScrollPane1);
       table2.fixTable(jScrollPane3);
       table1.fixTable(jScrollPane2);
       table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        table2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
         table1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       tab_all_ex();
       tab_all_ex_today();
       
       tab_all_examen();
       
        //  init card data
        card1.setData(new ModelCard(GoogleMaterialDesignIcon.HEALING, null, null, membre_personnel_actif(), "Consultation(s)"));
        card3.setData(new ModelCard(GoogleMaterialDesignIcon.HEALING, null, null, nombre_service_actif(), "Acte(s) d'Aujourd'hui"));
        card2.setData(new ModelCard(GoogleMaterialDesignIcon.HEALING, null, null, nombre_compte_actif(), "Examen(s)"));
        //card4.setData(new ModelCard(null, null, null, "$ 300.00", "Report Profit Monthly"));
    }
    public String serv_lib()
    {
        String num_id = null;
        String id = null;
        id = login.txtid.getText();
          try {
            Class.forName("com.mysql.jdbc.Driver");
                 sqlConn = DriverManager.getConnection(dataConn,username,password);
                 pst = sqlConn.prepareCall("Select * from users,service where identifiant = ? and Service_id=id");
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
    
    
          public void search (String nbre,String nbre2)
    {
        String serv = serv_lib();
         try {
          Class.forName("com.mysql.jdbc.Driver");
    sqlConn = DriverManager.getConnection(dataConn,username,password);
     pst_tab = sqlConn.prepareStatement("Select * from acte,service,patient where date_acte BETWEEN ? AND ? and id_service = id and id_patient=idpatient");
     pst_tab.setString(1, nbre);
     pst_tab.setString(2, nbre2);
     rs_tab = pst_tab.executeQuery () ;
    ResultSetMetaData stData = rs_tab.getMetaData () ;
    q = stData. getColumnCount () ;
    DefaultTableModel RecordTable = (DefaultTableModel)table1.getModel();
    RecordTable.setRowCount(0);
     while(rs_tab.next()){
     Vector columnData= new Vector();
        for (i=1; i<=q ; i++){
           columnData.add(rs_tab.getString("idacte"));
            columnData.add(rs_tab.getString("nom_patient"));
            columnData.add(rs_tab.getString("prenom_patient"));
            columnData.add(rs_tab.getString("telephone_patient"));
            columnData.add(rs_tab.getString("service_libelle"));   
            columnData.add(rs_tab.getString("date_acte"));
     }
        RecordTable.addRow(columnData);
     }
        } catch (Exception e) {
        }
    }
          
          public String search_nbre (String nbre,String nbre2)
    {
        String nbre3 = null;
         String serv = serv_lib();
         try {
          Class.forName("com.mysql.jdbc.Driver");
    sqlConn = DriverManager.getConnection(dataConn,username,password);
     pst_tab = sqlConn.prepareStatement("Select COUNT(*) from acte,service where date_acte BETWEEN ? AND ? and id_service = id");
     pst_tab.setString(1, nbre);
     pst_tab.setString(2, nbre2);
      
     rs_tab = pst_tab.executeQuery () ;
     while(rs_tab.next()){
     
         nbre3= rs_tab.getString("COUNT(*)");
     }
        } catch (Exception e) {
        }
         if(nbre3.equalsIgnoreCase(null))
         {
             return "0";
         }
         else{
         return nbre3;
    }
    }
          public void updatecard ( String nbre1, String nbre2)
          {
              card1.setData(new ModelCard(GoogleMaterialDesignIcon.HEALING, null, null, search_nbre(nbre1, nbre2)+" Total Acte(s) ","entre "+nbre1+" et "+nbre2)); 
          }
         public void tab_all_examen (){  
 Date d = new Date();
        SimpleDateFormat forma =  new SimpleDateFormat("dd-MM-yyyy");
        String ldte = forma.format(d);
         try {
          Class.forName("com.mysql.jdbc.Driver");
    sqlConn = DriverManager.getConnection(dataConn,username,password);
     pst_tab = sqlConn.prepareStatement("Select * from acte,patient,service where id_service = id and id_patient=idpatient and libelle_acte='Examen' and date_acte=? ORDER BY idacte DESC");
     pst_tab.setString(1, ldte);
     rs_tab = pst_tab.executeQuery();
    ResultSetMetaData stData = rs_tab.getMetaData () ;
    q = stData. getColumnCount () ;
    DefaultTableModel RecordTable = (DefaultTableModel)table2.getModel();
    RecordTable.setRowCount(0);
     while(rs_tab.next()){
     Vector columnData= new Vector();
        for (i=1; i<=q ; i++){
            columnData.add(rs_tab.getString("num_acte"));
            columnData.add(rs_tab.getString("nom_patient"));
            columnData.add(rs_tab.getString("prenom_patient"));
            columnData.add(rs_tab.getString("telephone_patient"));
            columnData.add(rs_tab.getString("service_libelle"));
            columnData.add(rs_tab.getString("examen"));
            columnData.add(rs_tab.getString("date_acte"));
     }
        RecordTable.addRow(columnData);
     }
        } catch (Exception e) {
        }
 }
          
         public void search_operation (String nbre, String nbre2){  
 String serv = serv_lib();
         try {
          Class.forName("com.mysql.jdbc.Driver");
    sqlConn = DriverManager.getConnection(dataConn,username,password);
     pst_tab = sqlConn.prepareStatement("Select * from acte,service,patient where date_acte BETWEEN ? AND ? and id_service = id and id_patient=idpatient");
     pst_tab.setString(1, nbre);
     pst_tab.setString(2, nbre2);
     rs_tab = pst_tab.executeQuery () ;
    ResultSetMetaData stData = rs_tab.getMetaData () ;
    q = stData. getColumnCount () ;
    DefaultTableModel RecordTable = (DefaultTableModel)table1.getModel();
    RecordTable.setRowCount(0);
     while(rs_tab.next()){
     Vector columnData= new Vector();
        for (i=1; i<=q ; i++){
           columnData.add(rs_tab.getString("idacte"));
            columnData.add(rs_tab.getString("nom_patient"));
            columnData.add(rs_tab.getString("prenom_patient"));
            columnData.add(rs_tab.getString("telephone_patient"));
            columnData.add(rs_tab.getString("service_libelle"));   
             columnData.add(rs_tab.getString("examen"));   
            columnData.add(rs_tab.getString("date_acte"));
     }
        RecordTable.addRow(columnData);
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
 pst = sqlConn.prepareCall("SELECT COUNT(*) FROM acte where date_acte BETWEEN ? AND ?");
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
    public void updatecard2 ( String nbre1, String nbre2)
          {
              card2.setData(new ModelCard(GoogleMaterialDesignIcon.HEALING, null, null, nombre_operation(nbre1, nbre2)+" Examen(s) ","entre "+nbre1+" et "+nbre2)); 
          }
         
        public void tab_all_service_barre_etat ()
    {
        
try {
    Class.forName("com.mysql.jdbc.Driver");
    sqlConn = DriverManager.getConnection(dataConn, username, password);
    pst_tab = sqlConn.prepareStatement("SELECT * FROM service where service_libelle!='Admin'");
    rs_tab = pst_tab.executeQuery();
    ResultSetMetaData stData = rs_tab.getMetaData();
    int q = stData.getColumnCount();
    DefaultTableModel recordTable = (DefaultTableModel) table2.getModel();
    recordTable.setRowCount(0);

    while (rs_tab.next()) {
        Vector<Object> columnData = new Vector<>();
        for (int i = 1; i <= q; i++) {
            columnData.add(rs_tab.getString("service_libelle"));
            columnData.add(rs_tab.getString("Date_creation"));
            columnData.add(rs_tab.getString("etat_service").toUpperCase());
        }
        recordTable.addRow(columnData);
    }

    // Personnalisation du rendu des cellules
    TableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
        @Override
        public java.awt.Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            java.awt.Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            String etatService = (String) value;
            if (etatService.equalsIgnoreCase("Actif")) {
                cellComponent.setBackground(Color.GREEN);
            } else {
                cellComponent.setBackground(Color.RED);
            }

            return cellComponent;
        }
    };

    // Appliquer le rendu personnalisé à la dernière colonne
    int lastColumnIndex = recordTable.getColumnCount() - 1;
    table2.getColumnModel().getColumn(lastColumnIndex).setCellRenderer(cellRenderer);
} catch (Exception e) {
    e.printStackTrace();
}
    }
        
        public String membre_personnel_actif ()
    {
        Date d = new Date();
        SimpleDateFormat forma =  new SimpleDateFormat("dd-MM-yyyy");
        String ldte = forma.format(d);
        String serv = serv_lib();
        String nbre_total = null;
     try {
            Class.forName("com.mysql.jdbc.Driver");
                 sqlConn = DriverManager.getConnection(dataConn,username,password);
                pst = sqlConn.prepareCall("SELECT COUNT(*) FROM acte,service where libelle_acte ='Consultation' and date_acte=? and id_service=id");
               pst.setString(1, ldte);
                 rs = pst.executeQuery();
                 if (rs.next())
                         
                         {
                              nbre_total = rs.getString("COUNT(*)");
                       //   nombre.setText(nbre);
                         }
        } catch (Exception e) {
        }
        return nbre_total;
    }
        public String nombre_service_actif ()
    {
        String nbre_total = null;
         Date d = new Date();
        SimpleDateFormat forma =  new SimpleDateFormat("dd-MM-yyyy");
        String ladate = forma.format(d);
     try {
            Class.forName("com.mysql.jdbc.Driver");
                 sqlConn = DriverManager.getConnection(dataConn,username,password);
 pst = sqlConn.prepareCall("SELECT COUNT(*) FROM acte where date_acte =? ");
 pst.setString(1, ladate);
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
     else
     {   
        return nbre_total;
    }
    }  
        public String nombre_compte_actif ()
    {
        Date d = new Date();
        SimpleDateFormat forma =  new SimpleDateFormat("dd-MM-yyyy");
        String ldte = forma.format(d);
        String nbre_total = null;
     try {
            Class.forName("com.mysql.jdbc.Driver");
                 sqlConn = DriverManager.getConnection(dataConn,username,password);
 pst = sqlConn.prepareCall("SELECT COUNT(*) FROM acte where libelle_acte='Examen' and date_acte=?");
 pst.setString(1, ldte);
                 rs = pst.executeQuery();
                 if (rs.next())
                         
                         {
                              nbre_total = rs.getString("COUNT(*)");
                       //   nombre.setText(nbre);
                         }
        } catch (Exception e) {
        }
        return nbre_total;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooser1 = new com.raven.datechooser.DateChooser();
        dateChooser2 = new com.raven.datechooser.DateChooser();
        roundPanel1 = new javaswingdev.swing.RoundPanel();
        materialTabbed1 = new tabbed.MaterialTabbed();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javaswingdev.swing.table.Table();
        jScrollPane2 = new javax.swing.JScrollPane();
        table1 = new javaswingdev.swing.table.Table();
        jScrollPane3 = new javax.swing.JScrollPane();
        table2 = new javaswingdev.swing.table.Table();
        date2 = new textfield.textsearch();
        date1 = new textfield.textsearch();
        jLabel2 = new javax.swing.JLabel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        card1 = new javaswingdev.card.Card();
        card2 = new javaswingdev.card.Card();
        card3 = new javaswingdev.card.Card();

        dateChooser1.setTextRefernce(date1);

        dateChooser2.setTextRefernce(date2);

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
                "#Examen", "Nom", "Prenoms", "Numero", "Service", "Acte", "Examen", "Date"
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

        materialTabbed1.addTab("Aujourd'hui", jScrollPane1);

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#Examen", "Nom", "Prenoms", "Numero", "Service", "Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table1);

        materialTabbed1.addTab("Consultation", jScrollPane2);

        table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#Examen", "Nom", "Prenoms", "Numero", "Service", "Examen", "Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table2MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(table2);

        materialTabbed1.addTab("Examen(s)", jScrollPane3);

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(materialTabbed1, javax.swing.GroupLayout.DEFAULT_SIZE, 836, Short.MAX_VALUE)
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(materialTabbed1, javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)
                .addContainerGap())
        );

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

        date1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                date1KeyReleased(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("au");

        jLayeredPane1.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        card1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                card1MouseClicked(evt);
            }
        });
        jLayeredPane1.add(card1);

        card2.setColor1(new java.awt.Color(95, 211, 226));
        card2.setColor2(new java.awt.Color(26, 166, 170));
        card2.setIcon(javaswingdev.GoogleMaterialDesignIcon.PIE_CHART);
        card2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                card2MouseClicked(evt);
            }
        });
        jLayeredPane1.add(card2);

        card3.setColor1(new java.awt.Color(95, 243, 140));
        card3.setColor2(new java.awt.Color(3, 157, 27));
        card3.setIcon(javaswingdev.GoogleMaterialDesignIcon.RING_VOLUME);
        card3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                card3MouseClicked(evt);
            }
        });
        jLayeredPane1.add(card3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roundPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(date1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addGap(7, 7, 7)
                        .addComponent(date2, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLayeredPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(date1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(date2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(13, 13, 13)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

     public void tab_all_ex ()
    {
         Date d = new Date();
        SimpleDateFormat forma =  new SimpleDateFormat("dd-MM-yyyy");
        String ldte = forma.format(d);
         try {
          Class.forName("com.mysql.jdbc.Driver");
    sqlConn = DriverManager.getConnection(dataConn,username,password);
     pst_tab = sqlConn.prepareStatement("Select * from acte,patient,service where id_service = id and id_patient=idpatient ORDER BY idacte DESC");
     rs_tab = pst_tab.executeQuery();
    ResultSetMetaData stData = rs_tab.getMetaData () ;
    q = stData. getColumnCount () ;
    DefaultTableModel RecordTable = (DefaultTableModel)table1.getModel();
    RecordTable.setRowCount(0);
     while(rs_tab.next()){
     Vector columnData= new Vector();
        for (i=1; i<=q ; i++){
            columnData.add(rs_tab.getString("num_acte"));
            columnData.add(rs_tab.getString("nom_patient"));
            columnData.add(rs_tab.getString("prenom_patient"));
            columnData.add(rs_tab.getString("telephone_patient"));
            columnData.add(rs_tab.getString("service_libelle"));   
            columnData.add(rs_tab.getString("date_acte"));
     }
        RecordTable.addRow(columnData);
     }
        } catch (Exception e) {
        }
    }
      public void tab_all_ex_today ()
    {
         Date d = new Date();
        SimpleDateFormat forma =  new SimpleDateFormat("dd-MM-yyyy");
        String ldte = forma.format(d);
         try {
          Class.forName("com.mysql.jdbc.Driver");
    sqlConn = DriverManager.getConnection(dataConn,username,password);
     pst_tab = sqlConn.prepareStatement("Select * from acte,patient,service where id_service = id and id_patient=idpatient and date_acte =? ORDER BY idacte DESC");
     pst_tab.setString(1, ldte);
     rs_tab = pst_tab.executeQuery();
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
            columnData.add(rs_tab.getString("date_acte"));
     }
        RecordTable.addRow(columnData);
     }
        } catch (Exception e) {
        }
    }
    private void card1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_card1MouseClicked
        // TODO add your handling code here:
         frame = new JFrame("Exit");
       if(JOptionPane.showConfirmDialog(frame, "Générer le rapport de tous les actes de l'hopital ?","Avertissement",
               JOptionPane.YES_NO_OPTION)== JOptionPane.YES_OPTION)
       {
          generatePDFReport_card1(table1);
       }
       // print(table);
    }//GEN-LAST:event_card1MouseClicked

    private void date2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_date2KeyReleased
        // TODO add your handling code here:
        search(date1.getText(),date2.getText());
        search_nbre(date1.getText(), date2.getText());
        updatecard(date1.getText(), date2.getText());
        search_operation(date1.getText(), date2.getText());
        nombre_operation(date1.getText(), date2.getText());
        updatecard2(date1.getText(), date2.getText());
    }//GEN-LAST:event_date2KeyReleased

    private void date2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_date2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_date2ActionPerformed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:
        init();
    }//GEN-LAST:event_formMouseClicked

    private void date1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_date1KeyReleased
        // TODO add your handling code here:
         search(date1.getText(),date2.getText());
        search_nbre(date1.getText(), date2.getText());
        updatecard(date1.getText(), date2.getText());
        search_operation(date1.getText(), date2.getText());
        nombre_operation(date1.getText(), date2.getText());
        updatecard2(date1.getText(), date2.getText());
    }//GEN-LAST:event_date1KeyReleased
private JFrame frame;
    private void card2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_card2MouseClicked
        // TODO add your handling code here:
         frame = new JFrame("Exit");
       if(JOptionPane.showConfirmDialog(frame, "Générer le rapport d'acte d'aujourd'hui?","Avertissement",
               JOptionPane.YES_NO_OPTION)== JOptionPane.YES_OPTION)
       {
          generatePDFReport(table);
       }
       
      //  print(table2);
      //tab_all_users();
    }//GEN-LAST:event_card2MouseClicked

    private void card3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_card3MouseClicked
        // TODO add your handling code here:
        frame = new JFrame("Exit");
       if(JOptionPane.showConfirmDialog(frame, "Générer le rapport  les examens de l'hopital ?","Avertissement",
               JOptionPane.YES_NO_OPTION)== JOptionPane.YES_OPTION)
       {
          generatePDFReport2(table2);
       }
    }//GEN-LAST:event_card3MouseClicked

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        // TODO add your handling code here:
        tab_all_ex();
    }//GEN-LAST:event_tableMouseClicked

    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_table1MouseClicked

    private void table2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_table2MouseClicked

   public void generatePDFReport(JTable table) {
        try {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            int rowCount = model.getRowCount();
            int columnCount = model.getColumnCount();

            // Création du dossier "Print_Facture" à la racine du projet si nécessaire
            String printFactureFolderPath = "../Print_Rapport_Acte/";
            createDirectoryIfNotExists(printFactureFolderPath);

            // Génération du nom du fichier PDF avec la date et l'heure actuelle
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String currentTime = LocalDateTime.now().format(formatter);
            String fileName = "Rapport_Acte_Consultation" + currentTime + ".pdf";
            String filePath = printFactureFolderPath + fileName;

            // Création du document PDF
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, new FileOutputStream(filePath));

            // Ouverture du document
            document.open();

            // Titre "Facture de paiement"
            Paragraph title = new Paragraph("Rapport Acte(s) Consultation");
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

            // Fermeture du document
            document.close();

            // Ouverture automatique du fichier PDF
            openPDFFile(filePath);

            System.out.println("Facture générée avec succès !");
        } catch (Exception e) {
            System.out.println("Une erreur s'est produite lors de la génération du PDF : " + e);
        }
    }

    // Méthode pour créer un dossier si celui-ci n'existe pas
    public void generatePDFReport_card1(JTable table) {
        try {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            int rowCount = model.getRowCount();
            int columnCount = model.getColumnCount();

            // Création du dossier "Print_Facture" à la racine du projet si nécessaire
            String printFactureFolderPath = "../Print_Rapport_Acte/";
            createDirectoryIfNotExists(printFactureFolderPath);

            // Génération du nom du fichier PDF avec la date et l'heure actuelle
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String currentTime = LocalDateTime.now().format(formatter);
            String fileName = "Rapport_tous_Actes.pdf";
            String filePath = printFactureFolderPath + fileName;

            // Création du document PDF
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, new FileOutputStream(filePath));

            // Ouverture du document
            document.open();

            // Titre "Facture de paiement"
            Paragraph title = new Paragraph("Rapport Acte(s) Médicaux");
            title.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(title);
            //Paragraph nm = new Paragraph("Date "+currentTime);
            //nm.setAlignment(Paragraph.ALIGN_CENTER);
            //document.add(nm);
            
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

            // Fermeture du document
            document.close();

            // Ouverture automatique du fichier PDF
            openPDFFile(filePath);

            System.out.println("Facture générée avec succès !");
        } catch (Exception e) {
            System.out.println("Une erreur s'est produite lors de la génération du PDF : " + e);
        }
    }

   public void generatePDFReport2(JTable table) {
        try {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            int rowCount = model.getRowCount();
            int columnCount = model.getColumnCount();

            // Création du dossier "Print_Facture" à la racine du projet si nécessaire
            String printFactureFolderPath = "../Print_Rapport_Acte/";
            createDirectoryIfNotExists(printFactureFolderPath);

            // Génération du nom du fichier PDF avec la date et l'heure actuelle
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String currentTime = LocalDateTime.now().format(formatter);
            String fileName = "Rapport_Acte_Examen" + currentTime + ".pdf";
            String filePath = printFactureFolderPath + fileName;

            // Création du document PDF
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, new FileOutputStream(filePath));

            // Ouverture du document
            document.open();

            // Titre "Facture de paiement"
            Paragraph title = new Paragraph("Rapport Acte(s) Examen(s)");
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

            // Fermeture du document
            document.close();

            // Ouverture automatique du fichier PDF
            openPDFFile(filePath);

            System.out.println("Facture générée avec succès !");
        } catch (Exception e) {
            System.out.println("Une erreur s'est produite lors de la génération du PDF : " + e);
        }
    }

    
    private void createDirectoryIfNotExists(String folderPath) {
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    // Méthode pour ouvrir le fichier PDF généré automatiquement
    private void openPDFFile(String filePath) {
        try {
            File file = new File(filePath);
            Desktop.getDesktop().open(file);
        } catch (IOException e) {
            System.out.println("Impossible d'ouvrir le fichier PDF : " + e);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javaswingdev.card.Card card1;
    private javaswingdev.card.Card card2;
    private javaswingdev.card.Card card3;
    private textfield.textsearch date1;
    private textfield.textsearch date2;
    private com.raven.datechooser.DateChooser dateChooser1;
    private com.raven.datechooser.DateChooser dateChooser2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private tabbed.MaterialTabbed materialTabbed1;
    private javaswingdev.swing.RoundPanel roundPanel1;
    private javaswingdev.swing.table.Table table;
    private javaswingdev.swing.table.Table table1;
    private javaswingdev.swing.table.Table table2;
    // End of variables declaration//GEN-END:variables
}
