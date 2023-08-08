package javaswingdev.form;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
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

public class Form_update_compte extends javax.swing.JPanel {
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
    public Form_update_compte() {
        initComponents();
        init();
    }

    private void init() {
       table1.fixTable(jScrollPane3);
       tab_all_compte();
       
        //  init card data
//        card1.setData(new ModelCard(GoogleMaterialDesignIcon.PERSON, null, null, membre_personnel_actif(), "Membres total actif"));
  //      card2.setData(new ModelCard(GoogleMaterialDesignIcon.ACCOUNT_BOX, null, null, nombre_service_actif(), "Nombres compte créer"));
    //    card3.setData(new ModelCard(GoogleMaterialDesignIcon.WORK, null, null, nombre_compte_actif(), "Services diponible"));
        //card4.setData(new ModelCard(null, null, null, "$ 300.00", "Report Profit Monthly"));
    }
         

        public void tab_all_compte (){  
try {
    Class.forName("com.mysql.jdbc.Driver");
    sqlConn = DriverManager.getConnection(dataConn, username, password);
    pst_tab = sqlConn.prepareStatement("SELECT * FROM users,role where Role_id=rle_id and role_libelle!='Admin'");
    rs_tab = pst_tab.executeQuery();
    ResultSetMetaData stData = rs_tab.getMetaData();
    int q = stData.getColumnCount();
    DefaultTableModel recordTable = (DefaultTableModel) table1.getModel();
    recordTable.setRowCount(0);

    while (rs_tab.next()) {
        Vector<Object> columnData = new Vector<>();
        for (int i = 1; i <= q; i++) {
            columnData.add(rs_tab.getString("idUser"));
            columnData.add(rs_tab.getString("date"));
            columnData.add(rs_tab.getString("nom_users").toUpperCase().concat(" ").concat(rs_tab.getString("prenom_users")));
            columnData.add(rs_tab.getString("role_libelle"));
            columnData.add(rs_tab.getString("etat"));
            columnData.add(rs_tab.getString("motif"));
            columnData.add(rs_tab.getString("date_hs"));
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
            if (etatService.equalsIgnoreCase("En Service")) {
                cellComponent.setForeground(Color.GREEN); // Change la couleur du texte en vert
            } else {
                cellComponent.setForeground(Color.RED); // Change la couleur du texte en rouge
            }

            return cellComponent;
        }
    };

    // Appliquer le rendu personnalisé à la dernière colonne
    int lastColumnIndex = recordTable.getColumnCount() - 1;
    table1.getColumnModel().getColumn(4).setCellRenderer(cellRenderer);
}catch (Exception e) {
    e.printStackTrace();
}
 }
            
       
      public void selectab(String nbre,String motif)
        {
               // TODO add your handling code here:
        Date d = new Date();
        SimpleDateFormat dat = new SimpleDateFormat("dd-MM-yyyy");
        String ladate = dat.format(d);
       // java.sql.Date ladate = new java.sql.Date(d.getTime());
       
            try {

                Class.forName("com.mysql.jdbc.Driver");
                sqlConn = DriverManager.getConnection(dataConn,username,password);
                    pst = sqlConn.prepareStatement("Update users set etat = ? , date_hs = ?,motif=? where idUser = ? ");
                    pst.setString(1, "Hors Service");
                    pst.setString(2, ladate);
                    pst.setString(3, motif);
                    pst.setString(4, nbre);
                    
                    pst.executeUpdate();
                     JOptionPane.showMessageDialog(null, "Compte Mis Hors Service \n");
           
              //Form_dash_admin fd =new Form_dash_admin();
              //fd. Form_Home_Caissier();
       
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Form_update_compte.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Form_update_compte.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
               
      public void update(String nbre,String motif)
        {
               // TODO add your handling code here:
        Date d = new Date();
        SimpleDateFormat dat = new SimpleDateFormat("dd-MM-yyyy");
        String ladate = dat.format(d);
       // java.sql.Date ladate = new java.sql.Date(d.getTime());
       
            try {

                Class.forName("com.mysql.jdbc.Driver");
                sqlConn = DriverManager.getConnection(dataConn,username,password);
                    pst = sqlConn.prepareStatement("Update users set etat = ? , date_hs = ?,motif=? where idUser = ? ");
                    pst.setString(1, "En Service");
                    pst.setString(2, ladate);
                    pst.setString(3, motif);
                    pst.setString(4, nbre);
                    
                    pst.executeUpdate();
                     JOptionPane.showMessageDialog(null, "Compte Mis en Service \n");
           
              //Form_dash_admin fd =new Form_dash_admin();
              //fd. Form_Home_Caissier();
       
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Form_update_compte.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Form_update_compte.class.getName()).log(Level.SEVERE, null, ex);
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

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        roundPanel1.setRound(10);

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "#Compte", "Date mis en service", "Propriétaire", "Poste", "Etat du compte", "Motif d'hors service", "Date mis hors service"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
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
        textsearch1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textsearch1ActionPerformed(evt);
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

            tab_all_compte();

        }
        else{
            DefaultTableModel model = (DefaultTableModel) table1.getModel();

            model.setRowCount(0);

            try {
                Class.forName("com.mysql.jdbc.Driver");
                sqlConn = DriverManager.getConnection(dataConn,username,password);
                String sql = "SELECT * FROM users,role WHERE nom_users LIKE ? and Role_id=rle_id and role_libelle!='Admin' ";
                PreparedStatement statement = sqlConn.prepareStatement(sql);
                statement.setString(1, "%" + textsearch1.getText() + "%");
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {                    
                    String column1Data = resultSet.getString("idUser");
                    String column2Data = resultSet.getString("date");
                    String column3Data = resultSet.getString("nom_users").toUpperCase().concat(" ").concat(resultSet.getString("prenom_users"));
                    String column4Data = resultSet.getString("role_libelle");
                    String column5Data = resultSet.getString("etat");
                    String column6Data = resultSet.getString("motif");
                    String column7Data = resultSet.getString("date_hs");

                    // Ajouter d'autres colonnes si nécessaire

                    model.addRow(new Object[]{column1Data, column2Data, column3Data, column4Data, column5Data, column6Data,column7Data});

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
                String poste = model.getValueAt(selectedRowIndex, 3).toString();
                  String etat = model.getValueAt(selectedRowIndex, 4).toString();
                   String identite = model.getValueAt(selectedRowIndex, 2).toString();
                    String motif = model.getValueAt(selectedRowIndex, 5).toString();
                  if(etat.equalsIgnoreCase("En Service"))
                  {
                      frame = new JFrame("Avertissement");
                    if(JOptionPane.showConfirmDialog(frame, "Confirmer la mise hors service du compte de " +nbre,"Avertissement",
                            JOptionPane.YES_NO_OPTION)== JOptionPane.YES_OPTION)
                    {
                        String mtif =  JOptionPane.showInputDialog(null, "Motif de la mise en hors service", "Avertissement", HEIGHT);
                      if(mtif.equalsIgnoreCase(""))
                      {
                          JOptionPane.showMessageDialog(null, "Motif de la mise en hors service invalide");
                      }
                      else {
                          String nw_etat = "Hors Service";
                      selectab(nbre,mtif);
                      tab_all_compte();
                     // JOptionPane.showMessageDialog(null, "Compte mis hors service");
                          generatePDFReport(nbre, identite,poste, nw_etat, mtif, lbl_ad());
                     }
                    }
                      //JOptionPane.showMessageDialog(null, "Compte déja mis hors service");
                  }
                  else
                  {
                      frame = new JFrame("Avertissement");
                    if(JOptionPane.showConfirmDialog(frame, "Confirmer la remise en service du compte de " +nbre,"Avertissement",
                            JOptionPane.YES_NO_OPTION)== JOptionPane.YES_OPTION)
                    {
                        String mtif =  JOptionPane.showInputDialog(null, "Motif de la mise en service du compte", "Avertissement", HEIGHT);
                      if(mtif.equalsIgnoreCase(""))
                      {
                          JOptionPane.showMessageDialog(null, "Motif de la mise service invalide");
                      }
                      else
                      {
                           String nw_etat = "En Service";
                      update(nbre,motif);
                      tab_all_compte();
                      //JOptionPane.showMessageDialog(null, "Compte remis en service");
                      generatePDFReport(nbre, identite,poste, nw_etat, mtif, lbl_ad());
                      }
                      
                     }
                      
                  }
              //  JOptionPane.showMessageDialog(null, nbre);
              
                
    }//GEN-LAST:event_table1MouseClicked

    private void textsearch1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textsearch1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textsearch1ActionPerformed
public String lbl_ad()
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
    
    public void generatePDFReport(String num, String constante1,String constante2,String constante3, String constante4,String admin) {
        try (Connection connection = DriverManager.getConnection(dataConn, username, password);) {

            // Création du dossier "Print_recu" à la racine du projet si nécessaire
            String printRecuFolderPath = "./Print_Rapport_etat_compte/";
            createDirectoryIfNotExists(printRecuFolderPath);

            // Génération du nom du fichier PDF avec la date et l'heure actuelle
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String currentTime = LocalDateTime.now().format(formatter);
            String fileName = "Rapport_etat_compte_"+ num + "_" + currentTime + ".pdf";
            String filePath = printRecuFolderPath + fileName;

            // Création du document PDF
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, new FileOutputStream(filePath));

            // Ouverture du document
            document.open();

            // Titre "Analyse de Prise de Constante"
            Paragraph title = new Paragraph("Rapport_etat_compte");
            title.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(title);

             Paragraph nm = new Paragraph("Rapport d'etat du compte N° "+num);
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
          //  Paragraph name = new Paragraph(prenomPatient + " " + nomPatient);
           // name.setAlignment(Paragraph.ALIGN_LEFT);
          //  document.add(name);

            // Espacement entre les éléments
          //  document.add(new Paragraph(" "));

            // Premier tableau - Constantes
            PdfPTable table1 = new PdfPTable(5);
            table1.setWidthPercentage(100);

            // Ajout des en-têtes de colonne du premier tableau
            table1.addCell("Id Compte");
            table1.addCell("Identite");
            table1.addCell("Poste");
            table1.addCell("Etat du compte");
            table1.addCell("Motif");

            // Ajout des données de constantes dans le premier tableau
            table1.addCell(num);
            table1.addCell(constante1);
            table1.addCell(constante2);   
            table1.addCell(constante3);
            table1.addCell(constante4);

            // Ajout du premier tableau au document
            document.add(table1);

            

            // Deuxième tableau - Informations
           

            // Espacement entre les éléments
            document.add(new Paragraph(" "));

            // Pied de page avec le nom de l'infirmier
            Paragraph footer = new Paragraph("Administrateur: " + admin);
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
    private javax.swing.JScrollPane jScrollPane3;
    private tabbed.MaterialTabbed materialTabbed1;
    private javaswingdev.swing.RoundPanel roundPanel1;
    private javaswingdev.swing.table.Table table1;
    private textfield.textsearch textsearch1;
    // End of variables declaration//GEN-END:variables
}
