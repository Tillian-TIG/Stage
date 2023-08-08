package javaswingdev.form;

import java.awt.Color;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javaswingdev.GoogleMaterialDesignIcon;
import javaswingdev.card.ModelCard;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

public class Form_service extends javax.swing.JPanel {
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
    public Form_service() {
        initComponents();
        init();
    }

    private void init() {
       table1.fixTable(jScrollPane3);
       tab_all_service();
       
        //  init card data
//        card1.setData(new ModelCard(GoogleMaterialDesignIcon.PERSON, null, null, membre_personnel_actif(), "Membres total actif"));
  //      card2.setData(new ModelCard(GoogleMaterialDesignIcon.ACCOUNT_BOX, null, null, nombre_service_actif(), "Nombres compte créer"));
    //    card3.setData(new ModelCard(GoogleMaterialDesignIcon.WORK, null, null, nombre_compte_actif(), "Services diponible"));
        //card4.setData(new ModelCard(null, null, null, "$ 300.00", "Report Profit Monthly"));
    }
         

        public void tab_all_service (){  
try {
    Class.forName("com.mysql.jdbc.Driver");
    sqlConn = DriverManager.getConnection(dataConn, username, password);
    pst_tab = sqlConn.prepareStatement("SELECT * FROM service where service_libelle!= 'Admin' and service_libelle != 'Caisse' and service_libelle != 'Admission' and service_libelle != 'Secretaire'");
    rs_tab = pst_tab.executeQuery();
    ResultSetMetaData stData = rs_tab.getMetaData();
    int q = stData.getColumnCount();
    DefaultTableModel recordTable = (DefaultTableModel) table1.getModel();
    recordTable.setRowCount(0);

    while (rs_tab.next()) {
        Vector<Object> columnData = new Vector<>();
        for (int i = 1; i <= q; i++) {
            columnData.add(rs_tab.getString("id"));
            columnData.add(rs_tab.getString("service_libelle"));
            columnData.add(rs_tab.getString("Date_creation"));
            columnData.add(rs_tab.getString("etat_service").toUpperCase());
            columnData.add(rs_tab.getString("Date_hor_service"));
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
                cellComponent.setForeground(Color.GREEN); // Change la couleur du texte en vert
            } else {
                cellComponent.setForeground(Color.RED); // Change la couleur du texte en rouge
            }

            return cellComponent;
        }
    };

    // Appliquer le rendu personnalisé à la dernière colonne
    int lastColumnIndex = recordTable.getColumnCount() - 1;
    table1.getColumnModel().getColumn(3).setCellRenderer(cellRenderer);
}catch (Exception e) {
    e.printStackTrace();
}
 }
        
      public void selectab(String nbre)
        {
               // TODO add your handling code here:
        Date d = new Date();
        SimpleDateFormat dat = new SimpleDateFormat("dd-MM-yyyy");
        String ladate = dat.format(d);
       // java.sql.Date ladate = new java.sql.Date(d.getTime());
       
            try {

                Class.forName("com.mysql.jdbc.Driver");
                sqlConn = DriverManager.getConnection(dataConn,username,password);
                    pst = sqlConn.prepareStatement("Update service set etat_service = ? , date_hor_service = ? where id = ? ");
                    pst.setString(1, "Non Actif");
                    pst.setString(2, ladate);
                    pst.setString(3, nbre);
                    
                    pst.executeUpdate();
                     JOptionPane.showMessageDialog(null, "Service hors actif \n");
           
              //Form_dash_admin fd =new Form_dash_admin();
              //fd. Form_Home_Caissier();
       
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Form_service.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Form_service.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
               
        
       
      
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new javaswingdev.swing.RoundPanel();
        materialTabbed1 = new tabbed.MaterialTabbed();
        jScrollPane3 = new javax.swing.JScrollPane();
        table1 = new javaswingdev.swing.table.Table();
        textsearch1 = new textfield.textsearch();

        setOpaque(false);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        roundPanel1.setRound(10);

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "#Service", "Nom du service", "Date Création", "Etat du Service", "Date mis hors service"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
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
        jScrollPane3.setViewportView(table1);

        materialTabbed1.addTab("Liste des comptes", jScrollPane3);

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(materialTabbed1, javax.swing.GroupLayout.DEFAULT_SIZE, 848, Short.MAX_VALUE)
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(materialTabbed1, javax.swing.GroupLayout.DEFAULT_SIZE, 691, Short.MAX_VALUE)
                .addContainerGap())
        );

        textsearch1.setText("Recherche");
        textsearch1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textsearch1MouseClicked(evt);
            }
        });
        textsearch1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textsearch1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(479, Short.MAX_VALUE)
                        .addComponent(textsearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textsearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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
            String id = null;
            id = textsearch1.getText();

            tab_all_service();

        }
        else{
            DefaultTableModel model = (DefaultTableModel) table1.getModel();

            model.setRowCount(0);

            try {
                Class.forName("com.mysql.jdbc.Driver");
                sqlConn = DriverManager.getConnection(dataConn,username,password);
                String sql = "SELECT * FROM service WHERE service_libelle LIKE ? and service_libelle!='Admin' ";
                PreparedStatement statement = sqlConn.prepareStatement(sql);
                statement.setString(1, "%" + textsearch1.getText() + "%");
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {                    
                    String column1Data = resultSet.getString("id");
                    String column2Data = resultSet.getString("service_libelle");
                    String column3Data = resultSet.getString("Date_creation");
                    String column4Data = resultSet.getString("etat_service");
                    String column5Data = resultSet.getString("date_hor_service");
                   

                    // Ajouter d'autres colonnes si nécessaire

                    model.addRow(new Object[]{column1Data, column2Data, column3Data, column4Data, column5Data});

                }
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_textsearch1KeyReleased
private JFrame frame;
    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked
        // TODO add your handling code here:
        //selectab(table1);
        int selectedRowIndex = table1.getSelectedRow();
                TableModel model = table1.getModel();  
                String nbre = model.getValueAt(selectedRowIndex, 0).toString();
                 String lbe = model.getValueAt(selectedRowIndex, 1).toString();
                  String etat = model.getValueAt(selectedRowIndex, 3).toString();
                   String id = model.getValueAt(selectedRowIndex, 2).toString();
                  if(etat.equalsIgnoreCase("Non Actif"))
                  {
                      JOptionPane.showMessageDialog(null, "le Service est déja non actif");
                  }
                  else
                  {
                      frame = new JFrame("Avertissement");
                    if(JOptionPane.showConfirmDialog(frame, "Confirmer la mise en hors actif du service " +lbe,"Avertissement",
                            JOptionPane.YES_NO_OPTION)== JOptionPane.YES_OPTION)
                    {
                      selectab(nbre);
                      tab_all_service();
                      JOptionPane.showMessageDialog(null, "Service mis hors actif");
                     }
                      
                  }
              //  JOptionPane.showMessageDialog(null, nbre);
              
                
    }//GEN-LAST:event_table1MouseClicked

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:
        tab_all_service();
    }//GEN-LAST:event_formMouseClicked

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
    private javax.swing.JScrollPane jScrollPane3;
    private tabbed.MaterialTabbed materialTabbed1;
    private javaswingdev.swing.RoundPanel roundPanel1;
    private javaswingdev.swing.table.Table table1;
    private textfield.textsearch textsearch1;
    // End of variables declaration//GEN-END:variables
}
