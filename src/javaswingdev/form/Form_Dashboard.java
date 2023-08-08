package javaswingdev.form;

import com.itextpdf.text.Document;
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

public class Form_Dashboard extends javax.swing.JPanel {
 private static final String username = "root";
 private static final String password = "primatologue";
 private static final String dataConn = "jdbc:mysql://localhost:3306/hopital";
  private JFrame frame;
 Connection sqlConn = null;
 Connection sqlSearch = null;
 PreparedStatement pst = null;
 PreparedStatement pst_search = null;
 ResultSet rs = null;
 ResultSet rs_2 = null;
 PreparedStatement pst_tab = null;
 ResultSet rs_tab = null;
 int q, i , id, deleteItem;
    public Form_Dashboard() {
        initComponents();
        init();
    }

    private void init() {
       table.fixTable(jScrollPane1);
       table2.fixTable(jScrollPane2);
       table1.fixTable(jScrollPane3);
       tab_all_users();
       tab_all_service();
       tab_all_compte();
       card1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       card2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       card3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       table1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       table2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        //  init card data
        card1.setData(new ModelCard(GoogleMaterialDesignIcon.PERSON, null, null, membre_personnel_actif(), "Membres total actif"));
        card2.setData(new ModelCard(GoogleMaterialDesignIcon.ACCOUNT_BOX, null, null, nombre_service_actif(), "Nombres compte créer"));
        card3.setData(new ModelCard(GoogleMaterialDesignIcon.WORK, null, null, nombre_compte_actif(), "Services diponible"));
        //card4.setData(new ModelCard(null, null, null, "$ 300.00", "Report Profit Monthly"));
    }
         public void tab_all_users ()
    {
         try {
          Class.forName("com.mysql.jdbc.Driver");
    sqlConn = DriverManager.getConnection(dataConn,username,password);
    pst_tab = sqlConn.prepareStatement("Select * from users,role,service where role_libelle != 'Admin' and Role_id=rle_id and Service_id=id");
    rs_tab = pst_tab.executeQuery () ;
    ResultSetMetaData stData = rs_tab.getMetaData () ;
    q = stData. getColumnCount () ;
    DefaultTableModel RecordTable = (DefaultTableModel)table.getModel();
    RecordTable.setRowCount(0);
     while(rs_tab.next()){
     Vector columnData= new Vector();
        for (i=1; i<=q ; i++){
            columnData.add(rs_tab.getString("idUser"));
            columnData.add(rs_tab.getString("nom_users"));
            columnData.add(rs_tab.getString("prenom_users"));
            columnData.add(rs_tab.getString("telephone"));
            columnData.add( rs_tab.getString("role_libelle"));
            columnData.add(rs_tab.getString("service_libelle"));   
             columnData.add(rs_tab.getString("date")); 
             columnData.add(rs_tab.getString("etat").toUpperCase()); 
     }
        RecordTable.addRow(columnData);
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
    int lastColumnIndex = RecordTable.getColumnCount() - 1;
    table.getColumnModel().getColumn(lastColumnIndex).setCellRenderer(cellRenderer);
     
     
     
        } catch (Exception e) {
        }
    }

        public void tab_all_service (){  
try {
    Class.forName("com.mysql.jdbc.Driver");
    sqlConn = DriverManager.getConnection(dataConn, username, password);
    pst_tab = sqlConn.prepareStatement("SELECT * FROM service where service_libelle!= 'Admin'");
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
                cellComponent.setForeground(Color.GREEN); // Change la couleur du texte en vert
            } else {
                cellComponent.setForeground(Color.RED); // Change la couleur du texte en rouge
            }

            return cellComponent;
        }
    };

    // Appliquer le rendu personnalisé à la dernière colonne
    int lastColumnIndex = recordTable.getColumnCount() - 1;
    table2.getColumnModel().getColumn(lastColumnIndex).setCellRenderer(cellRenderer);
}catch (Exception e) {
    e.printStackTrace();
}
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
            columnData.add(rs_tab.getString("etat").toUpperCase());
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
    table1.getColumnModel().getColumn(lastColumnIndex).setCellRenderer(cellRenderer);
}catch (Exception e) {
    e.printStackTrace();
}
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
        String nbre_total = null;
     try {
            Class.forName("com.mysql.jdbc.Driver");
                 sqlConn = DriverManager.getConnection(dataConn,username,password);
 pst = sqlConn.prepareCall("SELECT COUNT(*) FROM users,role where role_libelle!='Admin' and etat = 'En Service' and Role_id = rle_id");
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
     try {
            Class.forName("com.mysql.jdbc.Driver");
                 sqlConn = DriverManager.getConnection(dataConn,username,password);
 pst = sqlConn.prepareCall("SELECT COUNT(*) FROM service where service_libelle!='Admin' and etat_service = 'Actif'");
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
        public String nombre_compte_actif ()
    {
        String nbre_total = null;
     try {
            Class.forName("com.mysql.jdbc.Driver");
                 sqlConn = DriverManager.getConnection(dataConn,username,password);
 pst = sqlConn.prepareCall("SELECT COUNT(*) FROM users,role where role_libelle!='Admin' and etat = 'En Service' and Role_id = rle_id");
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
        jScrollPane2 = new javax.swing.JScrollPane();
        table2 = new javaswingdev.swing.table.Table();
        jScrollPane3 = new javax.swing.JScrollPane();
        table1 = new javaswingdev.swing.table.Table();
        textsearch1 = new textfield.textsearch();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        card1 = new javaswingdev.card.Card();
        card2 = new javaswingdev.card.Card();
        card3 = new javaswingdev.card.Card();

        setOpaque(false);

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        roundPanel1.setRound(10);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#Matricule", "Nom", "Prenoms", "Numero", "Poste", "Service", "Date de service", "Etat"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table);

        materialTabbed1.addTab("Membre du personnel", jScrollPane1);

        table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nom du service", "Date de création", "Etat du service"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(table2);

        materialTabbed1.addTab("Liste des services", jScrollPane2);

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "#Compte", "Date mise en service", "Propriétaire", "Poste", "Etat du compte"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(table1);

        materialTabbed1.addTab("Liste des comptes", jScrollPane3);

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
                .addComponent(materialTabbed1, javax.swing.GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE)
                .addContainerGap())
        );

        textsearch1.setText("Rechercher un membre ");
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
        jLayeredPane1.add(card2);

        card3.setColor1(new java.awt.Color(95, 243, 140));
        card3.setColor2(new java.awt.Color(3, 157, 27));
        card3.setIcon(javaswingdev.GoogleMaterialDesignIcon.RING_VOLUME);
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
                        .addComponent(textsearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLayeredPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(textsearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void card1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_card1MouseClicked
        // TODO add your handling code here:
        
          frame = new JFrame("Exit");
       if(JOptionPane.showConfirmDialog(frame, "Générer le rapport du personnelle de l'hopital ?","Avertissement",
               JOptionPane.YES_NO_OPTION)== JOptionPane.YES_OPTION)
       {
          generatePDFReport(table);
       }
         
            
    }//GEN-LAST:event_card1MouseClicked

    private void textsearch1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textsearch1MouseClicked
        // TODO add your handling code here:
        textsearch1.setText("");
    }//GEN-LAST:event_textsearch1MouseClicked

    private void textsearch1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textsearch1KeyReleased
        // TODO add your handling code here:
        //  String name = Login_form.txtid.getText();
        if (textsearch1.getText().trim().isEmpty() || textsearch1.getText().equalsIgnoreCase("Rechercher un membre ")){
            String id = null;
            id = textsearch1.getText();

            tab_all_users();

        }
        else{
            DefaultTableModel model = (DefaultTableModel) table.getModel();

            model.setRowCount(0);

            try {
                Class.forName("com.mysql.jdbc.Driver");
                sqlConn = DriverManager.getConnection(dataConn,username,password);
                String sql = "SELECT * FROM users,role,service WHERE nom_users LIKE ? and Role_id=rle_id and Service_id=id and role_libelle!='Admin' and service_libelle!='Admin' ";
                PreparedStatement statement = sqlConn.prepareStatement(sql);
                statement.setString(1, "%" + textsearch1.getText() + "%");
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String column1Data = resultSet.getString("idUser");
                    String column2Data = resultSet.getString("nom_users");
                    String column3Data = resultSet.getString("prenom_users");
                    String column4Data = resultSet.getString("telephone");
                    String column5Data = resultSet.getString("role_libelle");
                    String column6Data = resultSet.getString("service_libelle");
                    String column7Data = resultSet.getString("date");
                    String column8Data = resultSet.getString("etat");

                    // Ajouter d'autres colonnes si nécessaire

                    model.addRow(new Object[]{column1Data, column2Data, column3Data, column4Data, column5Data, column6Data,column7Data,column8Data});

                }
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_textsearch1KeyReleased
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
        return  num_id;
    } 
   public void generatePDFReport(JTable table) {
        try {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            int rowCount = model.getRowCount();
            int columnCount = model.getColumnCount();

            // Création du dossier "Print_Facture" à la racine du projet si nécessaire
            String printFactureFolderPath = "../Print_Rapport_Personnelle/";
            createDirectoryIfNotExists(printFactureFolderPath);

            // Génération du nom du fichier PDF avec la date et l'heure actuelle
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String currentTime = LocalDateTime.now().format(formatter);
            String fileName = "Rapport_du_personnelle_" + currentTime + ".pdf";
            String filePath = printFactureFolderPath + fileName;

            // Création du document PDF
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, new FileOutputStream(filePath));

            // Ouverture du document
            document.open();

            // Titre "Facture de paiement"
            Paragraph title = new Paragraph("Rapport du Personnelle");
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
            Paragraph Total = new Paragraph(" Fait par l'Administrateur "+currentTime+" : "+lbl_ad()+"");
            Total.setAlignment(Paragraph.ALIGN_LEFT);
            document.add(Total);
            // Fermeture du document
            document.close();
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
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private tabbed.MaterialTabbed materialTabbed1;
    private javaswingdev.swing.RoundPanel roundPanel1;
    private javaswingdev.swing.table.Table table;
    private javaswingdev.swing.table.Table table1;
    private javaswingdev.swing.table.Table table2;
    private textfield.textsearch textsearch1;
    // End of variables declaration//GEN-END:variables
}
