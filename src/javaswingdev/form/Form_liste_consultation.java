package javaswingdev.form;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import javaswingdev.main.login;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

public class Form_liste_consultation extends javax.swing.JPanel {
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
    public Form_liste_consultation() {
        initComponents();
        init();
    }

    private void init() {
       table.fixTable(jScrollPane1);
       tab_all_consultation();
        table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        //  init card data
//        card1.setData(new ModelCard(GoogleMaterialDesignIcon.PERSON, null, null, membre_personnel_actif(), "Membres total actif"));
  //      card2.setData(new ModelCard(GoogleMaterialDesignIcon.ACCOUNT_BOX, null, null, nombre_service_actif(), "Nombres compte créer"));
    //    card3.setData(new ModelCard(GoogleMaterialDesignIcon.WORK, null, null, nombre_compte_actif(), "Services diponible"));
        //card4.setData(new ModelCard(null, null, null, "$ 300.00", "Report Profit Monthly"));
    }
         

       public void tab_all_consultation ()
    {
         try {
          Class.forName("com.mysql.jdbc.Driver");
    sqlConn = DriverManager.getConnection(dataConn,username,password);
    pst_tab = sqlConn.prepareStatement("SELECT * FROM acte,service,patient,users WHERE id_service = id and id_patient=idpatient and id_users=idUser ORDER BY date_acte DESC");
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
            columnData.add(rs_tab.getString("date_acte"));
           columnData.add(rs_tab.getString("prix").concat(" ").concat("FrCFA"));
            columnData.add(rs_tab.getString("etat").toUpperCase());
     }
        RecordTable.addRow(columnData);
     }
     TableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
        @Override
        public java.awt.Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            java.awt.Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            String etatService = (String) value;
            
            if (etatService.equalsIgnoreCase("Payer")) {
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
      public void selectab(String nbre)
        {
               // TODO add your handling code here:
        Date d = new Date();
        SimpleDateFormat dat = new SimpleDateFormat("dd-MM-yyyy");
        String ladate = dat.format(d);
       // java.sql.Date ladate = new java.sql.Date(d.getTime());
       
        String id_users = num_id() ;
        
      //  String nbre_con = nbre_con();
      
            try {

                Class.forName("com.mysql.jdbc.Driver");
                sqlConn = DriverManager.getConnection(dataConn,username,password);
                    pst = sqlConn.prepareStatement("Select * from consultation,service where idconsultation=? and id_service = id");
                    pst.setString(1, nbre);
                    rs = pst.executeQuery();
                    if(rs.next())
                    {
                        if(rs.getString("diagnostic").equalsIgnoreCase("non fait"))
                        {
                            JOptionPane.showMessageDialog(null, "Vous avez une consultation non traité");
                        }
                       // else if(nbre_con.equalsIgnoreCase("10")){
                         //       JOptionPane.showMessageDialog(null, "Nombre maximal de consultation atteint pour aujourd'hui");
                           //     }
                        else{
                   pst = sqlConn.prepareStatement("insert into consultation(id_users,id_service,examen,nomPatient,prenomPatient,sexe,date_naissance,telephone,prix,date_consultation) value(?,?,?,?,?,?,?,?,?,?)");
                    pst.setString(1, id_users);
                    pst.setString(2,rs.getString("id_service"));
                    pst.setString(3, rs.getString("examen"));
                    pst.setString(4, rs.getString("nomPatient"));
                    pst.setString(5, rs.getString("prenomPatient"));
                    pst.setString(6,rs.getString("sexe"));
                    pst.setString(7,rs.getString("date_naissance"));
                    pst.setString(8,rs.getString("telephone"));
                    pst.setString(9, "5000");
                    pst.setString(10, ladate);

                    //pst.executeUpdate();rs.
                     pst.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Enrégistrement éffectué avec succès \n");
                        }
                    }
                   
           
              //Form_dash_admin fd =new Form_dash_admin();
              //fd. Form_Home_Caissier();
       
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Form_liste_consultation.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Form_liste_consultation.class.getName()).log(Level.SEVERE, null, ex);
            }

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
     public String nbre_con ()
    {
        String nbre_total = null;
        Date d = new Date();
        SimpleDateFormat dat = new SimpleDateFormat("dd-MM-yyyy");
        String ladate = dat.format(d);

     try {
            Class.forName("com.mysql.jdbc.Driver");
                 sqlConn = DriverManager.getConnection(dataConn,username,password);
 pst = sqlConn.prepareCall("SELECT COUNT(*) FROM consultation where date_consultation=?");
            pst.setString(i, ladate);
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

        roundPanel1 = new javaswingdev.swing.RoundPanel();
        materialTabbed1 = new tabbed.MaterialTabbed();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javaswingdev.swing.table.Table();
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

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#N°Acte", "Nom ", "Prenoms ", "Numero", "Service", "Acte", "Examen", "Date de l'acte", "Prix", "Paiement"
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

        materialTabbed1.addTab("Liste de actes", jScrollPane1);

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(materialTabbed1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(materialTabbed1, javax.swing.GroupLayout.DEFAULT_SIZE, 697, Short.MAX_VALUE)
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
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 483, Short.MAX_VALUE)
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
                .addGap(8, 8, 8))
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

            tab_all_consultation();

        }
        else{
            DefaultTableModel model = (DefaultTableModel) table.getModel();

            model.setRowCount(0);

             try {
          Class.forName("com.mysql.jdbc.Driver");
    sqlConn = DriverManager.getConnection(dataConn,username,password);
     pst_tab = sqlConn.prepareStatement("Select * from acte,patient,service where nom_patient LIKE ? and id_service = id and id_patient=idpatient and id_service=id;");
     pst_tab.setString(1,"%"+textsearch1.getText()+"%");
     
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
            columnData.add(rs_tab.getString("date_acte"));
            columnData.add(rs_tab.getString("prix").concat(" ").concat("FrCFA"));
            columnData.add(rs_tab.getString("etat").toUpperCase());
     }
        RecordTable.addRow(columnData);
     }
      TableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
        @Override
        public java.awt.Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            java.awt.Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            String etatService = (String) value;
            if (etatService.equalsIgnoreCase("Payer")) {
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
    }//GEN-LAST:event_textsearch1KeyReleased

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:
        tab_all_consultation();
    }//GEN-LAST:event_formMouseClicked
private JFrame frame;
    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        // TODO add your handling code here:
         int selectedRowIndex = table.getSelectedRow();
                TableModel model = table.getModel();
                String num = model.getValueAt(selectedRowIndex, 0).toString();
                String nom = model.getValueAt(selectedRowIndex, 1).toString();
                 String prenom = model.getValueAt(selectedRowIndex, 2).toString();
                 openPDFByFullName(nom, prenom,num);
    }//GEN-LAST:event_tableMouseClicked

     public void openPDFByFullName(String nom, String prenom, String num) {
    String printRecuFolderPath = "../Print_Facture/";
    String fileNamePrefix = "Facture_paiement_" + nom + "_" + prenom + "_" + num;

    try {
        // Recherche du fichier PDF correspondant dans le dossier "Print_recu"
        Path folderPath = Paths.get(printRecuFolderPath);
        if (Files.exists(folderPath)) {
            File[] files = folderPath.toFile().listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.getName().startsWith(fileNamePrefix) && file.getName().endsWith(".pdf")) {
                        // Fichier PDF trouvé, ouverture automatique
                        Desktop.getDesktop().open(file);
                        return;
                    }
                }
            }
        }
    } catch (IOException e) {
        System.out.println("Erreur lors de l'ouverture du fichier PDF : " + e);
    }

    // Aucun fichier PDF correspondant trouvé
    System.out.println("Aucun reçu trouvé pour " + nom + " " + prenom + " avec le numéro " + num);
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private tabbed.MaterialTabbed materialTabbed1;
    private javaswingdev.swing.RoundPanel roundPanel1;
    private javaswingdev.swing.table.Table table;
    private textfield.textsearch textsearch1;
    // End of variables declaration//GEN-END:variables
}
