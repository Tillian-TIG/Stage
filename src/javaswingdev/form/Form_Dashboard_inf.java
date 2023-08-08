package javaswingdev.form;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
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
import java.util.Date;
import java.util.Vector;
import javaswingdev.GoogleMaterialDesignIcon;
import javaswingdev.card.ModelCard;
import javaswingdev.main.login;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

public class Form_Dashboard_inf extends javax.swing.JPanel {
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
    public Form_Dashboard_inf() {
        initComponents();
        init();
    }

    private void init() {
       table.fixTable(jScrollPane1);
         table1.fixTable(jScrollPane2);
       card1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//       card2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       card3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       
       tab_all_ex();
       tab_all_ex_today();
    
       
      
        //  init card data
        card1.setData(new ModelCard(GoogleMaterialDesignIcon.HEALING, null, null, membre_personnel_actif(), "Consultation(s) "));
        //card2.setData(new ModelCard(GoogleMaterialDesignIcon.HEALING, null, null, nombre_service_actif(), "Acte(s)"));
        card3.setData(new ModelCard(GoogleMaterialDesignIcon.HEALING, null, null, nombre_service_actif2(), "Examen(s)"));
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
     pst_tab = sqlConn.prepareStatement("Select * from acte,service,patient where date_acte BETWEEN ? AND ? and service_libelle=?  and id_service = id and id_patient=idpatient");
     pst_tab.setString(1, nbre);
     pst_tab.setString(2, nbre2);
     pst_tab.setString(3, serv);
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
            columnData.add(rs_tab.getString("date_acte"));
            columnData.add(rs_tab.getString("diagnostic"));
            
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
     pst_tab = sqlConn.prepareStatement("Select COUNT(*) from acte,service where date_acte BETWEEN ? AND ? and service_libelle=? and libelle_acte='Consultation' and id_service = id");
     pst_tab.setString(1, nbre);
     pst_tab.setString(2, nbre2);
      pst_tab.setString(3, serv);
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
         
              public String search_nbre_exam (String nbre,String nbre2)
    {
        String nbre3 = null;
         String serv = serv_lib();
         try {
          Class.forName("com.mysql.jdbc.Driver");
    sqlConn = DriverManager.getConnection(dataConn,username,password);
     pst_tab = sqlConn.prepareStatement("Select COUNT(*) from acte,service where date_acte BETWEEN ? AND ? and service_libelle=? and libelle_acte='Examen' and id_service = id");
     pst_tab.setString(1, nbre);
     pst_tab.setString(2, nbre2);
      pst_tab.setString(3, serv);
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
      public String search_nbre_total (String nbre,String nbre2)
    {
        String nbre3 = null;
         String serv = serv_lib();
         try {
          Class.forName("com.mysql.jdbc.Driver");
    sqlConn = DriverManager.getConnection(dataConn,username,password);
     pst_tab = sqlConn.prepareStatement("Select COUNT(*) from acte,service where date_acte BETWEEN ? AND ? and service_libelle=? and id_service = id");
     pst_tab.setString(1, nbre);
     pst_tab.setString(2, nbre2);
      pst_tab.setString(3, serv);
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
              card1.setData(new ModelCard(GoogleMaterialDesignIcon.HEALING, null, null, search_nbre(nbre1, nbre2)+" Consultation(s) ","entre "+nbre1+" et "+nbre2)); 
            //  card2.setData(new ModelCard(GoogleMaterialDesignIcon.HEALING, null, null, search_nbre_exam(nbre1, nbre2)+" Examen(s) ","entre "+nbre1+" et "+nbre2)); 
              card3.setData(new ModelCard(GoogleMaterialDesignIcon.HEALING, null, null, search_nbre_total(nbre1, nbre2)+" Acte total ","entre "+nbre1+" et "+nbre2)); 
          
          }
           
        
        public String membre_personnel_actif ()
    {
        String serv = serv_lib();
        String nbre_total = null;
         Date d = new Date();
        SimpleDateFormat forma =  new SimpleDateFormat("dd-MM-yyyy");
        String ldte = forma.format(d);
     try {
            Class.forName("com.mysql.jdbc.Driver");
                 sqlConn = DriverManager.getConnection(dataConn,username,password);
 pst = sqlConn.prepareCall("SELECT COUNT(*) FROM acte,service where id_service=id and date_acte = ? and service_libelle = ? and libelle_acte='Consultation' and diagnostic ='Non Fait' and etat='Payer'");
    pst.setString(1, ldte);
    pst.setString(2, serv);
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
        String serv = serv_lib();
        String nbre_total = null;
         Date d = new Date();
        SimpleDateFormat forma =  new SimpleDateFormat("dd-MM-yyyy");
        String ldte = forma.format(d);
     try {
            Class.forName("com.mysql.jdbc.Driver");
                 sqlConn = DriverManager.getConnection(dataConn,username,password);
 pst = sqlConn.prepareCall("SELECT COUNT(*) FROM acte,service where id_service=id and date_acte = ? and service_libelle = ? and diagnostic ='Non Fait' and etat='Payer'");
    pst.setString(1, ldte);
    pst.setString(2, serv);
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
       
           public String nombre_service_actif2 ()
    {
        String serv = serv_lib();
        String nbre_total = null;
         Date d = new Date();
        SimpleDateFormat forma =  new SimpleDateFormat("dd-MM-yyyy");
        String ldte = forma.format(d);
     try {
            Class.forName("com.mysql.jdbc.Driver");
                 sqlConn = DriverManager.getConnection(dataConn,username,password);
 pst = sqlConn.prepareCall("SELECT COUNT(*) FROM acte,service where id_service=id and date_acte = ? and service_libelle = ? and libelle_acte='Examen' and diagnostic ='Non Fait' and etat='Payer'");
    pst.setString(1, ldte);
    pst.setString(2, serv);
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
          String serv = serv_lib();
        String nbre_total = null;
         Date d = new Date();
        SimpleDateFormat forma =  new SimpleDateFormat("dd-MM-yyyy");
        String ldte = forma.format(d);
     try {
            Class.forName("com.mysql.jdbc.Driver");
                 sqlConn = DriverManager.getConnection(dataConn,username,password);
 pst = sqlConn.prepareCall("SELECT COUNT(*) FROM acte,service where id_service=id and date_acte = ? and service_libelle = ? and diagnostic ='Non Fait' and etat='Payer'");
    pst.setString(1, ldte);
    pst.setString(2, serv);
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
        jLayeredPane1 = new javax.swing.JLayeredPane();
        card1 = new javaswingdev.card.Card();
        card3 = new javaswingdev.card.Card();

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
                "#N°Acte", "Nom", "Prenoms", "Numero", "Service", "Acte", "Date", "Action"
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
                "#N°Acte", "Nom", "Prenoms", "Numero", "Service", "Acte", "Date", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
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

        materialTabbed1.addTab("Prélèvement", jScrollPane2);

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

        jLayeredPane1.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        card1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                card1MouseClicked(evt);
            }
        });
        jLayeredPane1.add(card1);

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
                    .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLayeredPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

      public void tab_all_ex ()
    {
        String serv = serv_lib();
         Date d = new Date();
        SimpleDateFormat forma =  new SimpleDateFormat("dd-MM-yyyy");
        String ldte = forma.format(d);
         try {
          Class.forName("com.mysql.jdbc.Driver");
    sqlConn = DriverManager.getConnection(dataConn,username,password);
     pst_tab = sqlConn.prepareStatement("Select * from acte,patient,service where service_libelle= ? and etat = 'Payer' and id_service = id and id_patient=idpatient");
     pst_tab.setString(1, serv);
     //   pst_tab.setString(2, ldte);
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
            columnData.add(rs_tab.getString("libelle_acte"));
            columnData.add(rs_tab.getString("date_acte"));
             columnData.add(rs_tab.getString("diagnostic"));
     }
        RecordTable.addRow(columnData);
     }
     TableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
        @Override
        public java.awt.Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            java.awt.Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            String etatService = (String) value;
            
            if (etatService.equalsIgnoreCase("Fait")) {
                cellComponent.setForeground(Color.GREEN); // Change la couleur du texte en vert
            } else {
                cellComponent.setForeground(Color.RED); // Change la couleur du texte en rouge
            }
            

            return cellComponent;
        }
    };

    // Appliquer le rendu personnalisé à la dernière colonne
    int lastColumnIndex = RecordTable.getColumnCount() - 1;
    
    table1.getColumnModel().getColumn(lastColumnIndex).setCellRenderer(cellRenderer);
        } catch (Exception e) {
        }
    }
   
     public void tab_all_ex_today ()
    {
        String serv = serv_lib();
         Date d = new Date();
        SimpleDateFormat forma =  new SimpleDateFormat("dd-MM-yyyy");
        String ldte = forma.format(d);
         try {
          Class.forName("com.mysql.jdbc.Driver");
    sqlConn = DriverManager.getConnection(dataConn,username,password);
     pst_tab = sqlConn.prepareStatement("Select * from acte,patient,service where service_libelle= ? and etat = 'Payer' and date_acte=? and  id_service = id and id_patient=idpatient");
     pst_tab.setString(1, serv);
       pst_tab.setString(2, ldte);
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
            columnData.add(rs_tab.getString("date_acte"));
             columnData.add(rs_tab.getString("diagnostic"));
     }
        RecordTable.addRow(columnData);
     }
     TableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
        @Override
        public java.awt.Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            java.awt.Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            String etatService = (String) value;
            
            if (etatService.equalsIgnoreCase("Fait")) {
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
        } catch (Exception e) {
        }
    }
    private void card1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_card1MouseClicked
        // TODO add your handling code here:
        
        //print(table);
    }//GEN-LAST:event_card1MouseClicked

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:
        init();
    }//GEN-LAST:event_formMouseClicked

    private void card3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_card3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_card3MouseClicked

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        // TODO add your handling code here:
        tab_all_ex();
    }//GEN-LAST:event_tableMouseClicked

    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_table1MouseClicked

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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javaswingdev.card.Card card1;
    private javaswingdev.card.Card card3;
    private com.raven.datechooser.DateChooser dateChooser1;
    private com.raven.datechooser.DateChooser dateChooser2;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private tabbed.MaterialTabbed materialTabbed1;
    private javaswingdev.swing.RoundPanel roundPanel1;
    private javaswingdev.swing.table.Table table;
    private javaswingdev.swing.table.Table table1;
    // End of variables declaration//GEN-END:variables
}
