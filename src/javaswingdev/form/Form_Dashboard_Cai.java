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
import java.sql.SQLException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Vector;
import javaswingdev.GoogleMaterialDesignIcon;
import javaswingdev.card.ModelCard;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

public class Form_Dashboard_Cai extends javax.swing.JPanel {
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
    public Form_Dashboard_Cai() {
        initComponents();
        init();
    }

    private void init() {
       table.fixTable(jScrollPane1);
      
       table1.fixTable(jScrollPane3);
       card1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       card2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       card3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
      
       table1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       
       
       tab_all_acte();
       tab_payement();
       
       
        //  init card data
        card1.setData(new ModelCard(GoogleMaterialDesignIcon.CREDIT_CARD, null, null,"Paiement(s) Consultation "/*+nbre_consultation_total()*/, paiement_consultation()+" Fr Cfa"));
        card2.setData(new ModelCard(GoogleMaterialDesignIcon.CREDIT_CARD, null, null, "Paiement(s) Examen "/*+nbre_operation_total()*/, paiement_operation()+" Fr Cfa"));
        card3.setData(new ModelCard(GoogleMaterialDesignIcon.CREDIT_CARD, null, null, "Paiement d'Aujourd'hui "/*+ nombre_paiement_today()*/,nbre_consultation_today()+"Consultation et "+nbre_operation_today()+" Examn"));
        //card4.setData(new ModelCard(null, null, null, "$ 300.00", "Report Profit Monthly"));
    }
    
       public void tab_payement ()
    {
        Date d = new Date();
        SimpleDateFormat form = new SimpleDateFormat("dd-MM-yyyy");
        String ladate = form.format(d);
        String nbre_today = null;
                      DefaultTableModel model = (DefaultTableModel) table.getModel();

            model.setRowCount(0);
            
            try {
                Class.forName("com.mysql.jdbc.Driver");
                sqlConn = DriverManager.getConnection(dataConn,username,password);
                pst_tab = sqlConn.prepareStatement("Select * from recu_consultation,service,patient where date_recu =? and recu_id_patient = idpatient and  idservice = id ORDER BY idrecu_consul DESC");
                pst_tab.setString(1, ladate);
                rs_tab = pst_tab.executeQuery () ;
                while (rs_tab.next()) {
                    String column1Data = rs_tab.getString("recu_id_acte");
                    String column2Data = rs_tab.getString("nom_patient");
                    String column3Data = rs_tab.getString("prenom_patient");
                    String column4Data = rs_tab.getString("telephone_patient");            
                    String column5Data = rs_tab.getString("service_libelle");
                    String column6Data = rs_tab.getString("acte");
                   
                    String column7Data = rs_tab.getString("montant_net").concat(" FrCfa");
                     String column8Data = rs_tab.getString("etat_recu");
                     String column9Data = rs_tab.getString("date_recu");
                 
                    // Ajouter d'autres colonnes si nécessaire

                    model.addRow(new Object[]{column1Data,column2Data, column3Data, column4Data, column5Data, column6Data, column7Data,column8Data,column9Data});
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
    table.getColumnModel().getColumn(7).setCellRenderer(cellRenderer);
}catch (Exception e) {
    e.printStackTrace();
}
    } 
   
    
            public void tab_all_acte ()
    {
        Date d = new Date();
        SimpleDateFormat form = new SimpleDateFormat("dd-MM-yyyy");
        String ladate = form.format(d);
        String nbre_today = null;
                      DefaultTableModel model = (DefaultTableModel) table1.getModel();

            model.setRowCount(0);
            
            try {
                Class.forName("com.mysql.jdbc.Driver");
                sqlConn = DriverManager.getConnection(dataConn,username,password);
                pst_tab = sqlConn.prepareStatement("Select * from acte,service,patient where etat = 'Payer' and id_patient = idpatient and id_service = id ORDER BY num_acte DESC");
                rs_tab = pst_tab.executeQuery () ;
                while (rs_tab.next()) {
                    String column1Data = rs_tab.getString("num_acte");
                    String column2Data = rs_tab.getString("nom_patient");
                    String column3Data = rs_tab.getString("prenom_patient");
                    String column4Data = rs_tab.getString("telephone_patient");            
                    String column5Data = rs_tab.getString("service_libelle");
                    String column6Data = rs_tab.getString("libelle_acte");
                    String column7Data = rs_tab.getString("examen");
                    String column8Data = rs_tab.getString("prix").concat(" FrCfa");
                    String column9Data = rs_tab.getString("etat").toUpperCase();
                     String column10Data = rs_tab.getString("date_acte");
                 
                    // Ajouter d'autres colonnes si nécessaire

                    model.addRow(new Object[]{column1Data,column2Data, column3Data, column4Data, column5Data, column6Data, column7Data,column8Data,column9Data,column10Data});
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
    table1.getColumnModel().getColumn(8).setCellRenderer(cellRenderer);
}catch (Exception e) {
    e.printStackTrace();
}
    } 
   
          public void search (String nbre,String nbre2)
    {
        
         try {
          Class.forName("com.mysql.jdbc.Driver");
    sqlConn = DriverManager.getConnection(dataConn,username,password);
     //String sql = "SELECT * FROM  factureconsultation,service,type_analyse WHERE date BETWEEN ? AND ? and idservice = id and type_analyse=idtype_analyse";
     pst_tab = sqlConn.prepareStatement("Select * from acte,service,patient where date_acte BETWEEN ? AND ? and etat = 'Payer' and id_patient = idpatient and id_service = id");
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
               columnData.add(rs_tab.getString("num_acte"));
            columnData.add(rs_tab.getString("nom_patient"));
            columnData.add(rs_tab.getString("prenom_patient"));
            columnData.add(rs_tab.getString("telephone_patient"));
            columnData.add(rs_tab.getString("service_libelle"));
              columnData.add(rs_tab.getString("libelle_acte"));
            columnData.add(rs_tab.getString("examen"));
            columnData.add(rs_tab.getString("prix").concat(" FrCfa"));
            columnData.add(rs_tab.getString("etat").toUpperCase());
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
        
         try {
          Class.forName("com.mysql.jdbc.Driver");
    sqlConn = DriverManager.getConnection(dataConn,username,password);
     pst_tab = sqlConn.prepareStatement("Select COUNT(*) from acte where date_acte BETWEEN ? AND ? and etat='Payer'");
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
              card1.setData(new ModelCard(GoogleMaterialDesignIcon.PAYMENT, null, null, search_nbre(nbre1, nbre2)+" Facture(s) Acte(s) ","entre "+nbre1+" et "+nbre2)); 
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
    public void updatecard2 ( String nbre1, String nbre2)
          {
              card3.setData(new ModelCard(GoogleMaterialDesignIcon.PAYMENT, null, null, nombre_operation(nbre1, nbre2)+" Facture(s) d'Operation ","entre "+nbre1+" et "+nbre2)); 
          }
         
         
        public String paiement_consultation ()
    {
        Date d = new Date();
        SimpleDateFormat form = new SimpleDateFormat("dd-MM-yyyy");
        String ladate = form.format(d);
         String prix_total = null;
     try {
            Class.forName("com.mysql.jdbc.Driver");
                 sqlConn = DriverManager.getConnection(dataConn,username,password);
                 pst = sqlConn.prepareCall("SELECT sum(prix) FROM acte where etat='Payer' and libelle_acte='Consultation' and date_acte= ? ");
                 pst.setString(1, ladate);
                 rs = pst.executeQuery();
                 if (rs.next())
                         
                         {
                              prix_total = rs.getString("sum(prix)");
                       //   nombre.setText(nbre);
                         }
        } catch (Exception e) {
        }
     
        if (prix_total == null) {
        prix_total = "0";
    }
    
    return prix_total;
    }
    
          public String paiement_total ()
    {
        Date d = new Date();
        SimpleDateFormat form = new SimpleDateFormat("dd-MM-yyyy");
        String ladate = form.format(d);
         String prix_total = null;
     try {
            Class.forName("com.mysql.jdbc.Driver");
                 sqlConn = DriverManager.getConnection(dataConn,username,password);
                 pst = sqlConn.prepareCall("SELECT sum(prix) FROM acte where etat='Payer'");
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
    
        
       public String paiement_operation() {
    Date d = new Date();
    SimpleDateFormat form = new SimpleDateFormat("dd-MM-yyyy");
    String ladate = form.format(d);
    String prix_total = null;
    
    try {
        Class.forName("com.mysql.jdbc.Driver");
        sqlConn = DriverManager.getConnection(dataConn, username, password);
        pst = sqlConn.prepareCall("SELECT sum(prix) FROM acte WHERE libelle_acte!='Consultation' AND date_acte=? AND etat='Payer'");
        pst.setString(1, ladate);
        rs = pst.executeQuery();
        if (rs.next()) {
            prix_total = rs.getString("sum(prix)");
        }
    } catch (Exception e) {
        // Gérer l'exception ici (afficher un message d'erreur, journaliser, etc.)
    }
    
    // Vérifier si prix_total est null et le remplacer par "0" si c'est le cas
    if (prix_total == null) {
        prix_total = "0";
    }
    
    return prix_total;
}

        public String nombre_paiement_today ()
    {
        int nbre_total = Integer.parseInt(nbre_consultation_today()) + Integer.parseInt(nbre_operation_today());
        String v = String.valueOf(nbre_total);
        return v;
    }
         public String nbre_consultation_today ()
    {
         Date d = new Date();
        SimpleDateFormat form = new SimpleDateFormat("dd-MM-yyyy");
        String ladate = form.format(d);
        String nbre_today = null;
     try {
            Class.forName("com.mysql.jdbc.Driver");
                 sqlConn = DriverManager.getConnection(dataConn,username,password);
                 pst = sqlConn.prepareCall("SELECT COUNT(*) FROM acte where date_acte = ? and libelle_acte='Consultation' and etat='Payer'");
                 pst.setString(1, ladate);
                 rs = pst.executeQuery();
                 if (rs.next())
                         
                         {
                             
                              nbre_today = rs.getString("COUNT(*)");
                       //   nombre.setText(nbre);
                         }
        } catch (Exception e) {
        }
        return nbre_today;
    }
          public String nbre_operation_today ()
    {
         Date d = new Date();
        SimpleDateFormat form = new SimpleDateFormat("dd-MM-yyyy");
        String ladate = form.format(d);
        String nbre_today = null;
     try {
            Class.forName("com.mysql.jdbc.Driver");
                 sqlConn = DriverManager.getConnection(dataConn,username,password);
                 pst = sqlConn.prepareCall("SELECT COUNT(*) FROM acte where date_acte = ? and libelle_acte!='Consultation' and etat='Payer'");
                 pst.setString(1, ladate);
                 rs = pst.executeQuery();
                 if (rs.next())
                         
                         {
                             
                              nbre_today = rs.getString("COUNT(*)");
                       //   nombre.setText(nbre);
                         }
        } catch (Exception e) {
        }
        return nbre_today;
    }
          public String nbre_consultation_total ()
    {
        String nbre_total = null;
     try {
            Class.forName("com.mysql.jdbc.Driver");
                 sqlConn = DriverManager.getConnection(dataConn,username,password);
                 pst = sqlConn.prepareCall("SELECT COUNT(*) FROM acte where etat='Payer'");
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
    public String nbre_operation_total ()
    {
        String nbre_total = null;
     try {
            Class.forName("com.mysql.jdbc.Driver");
                 sqlConn = DriverManager.getConnection(dataConn,username,password);
                 pst = sqlConn.prepareCall("SELECT COUNT(*) FROM recuconsultation where acte!='Consultation'");
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
        jScrollPane3 = new javax.swing.JScrollPane();
        table1 = new javaswingdev.swing.table.Table();
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
                "#Acte", "Nom", "Prenom", "Numero", "Service", "Acte", "Montant", "Payement", "Date paiement"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table);

        materialTabbed1.addTab("Paiement", jScrollPane1);

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#Acte", "Nom", "Prenom", "Numero", "Service", "Acte", "Examen", "Montant", "Etat", "Date paiement"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(table1);
        if (table1.getColumnModel().getColumnCount() > 0) {
            table1.getColumnModel().getColumn(6).setHeaderValue("Examen");
            table1.getColumnModel().getColumn(8).setHeaderValue("Etat");
        }

        materialTabbed1.addTab("Rapport de paiement", jScrollPane3);

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

    private void card1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_card1MouseClicked
        // TODO add your handling code here:
         frame = new JFrame("Exit");
       if(JOptionPane.showConfirmDialog(frame, "Générer le rapport de tous les paiements de l'hopital ?","Avertissement",
               JOptionPane.YES_NO_OPTION)== JOptionPane.YES_OPTION)
       {
          generatePDFReport_card1(table1);
       }

    }//GEN-LAST:event_card1MouseClicked

    private void date2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_date2KeyReleased
        // TODO add your handling code here:
        search(date1.getText(),date2.getText());
        search_nbre(date1.getText(), date2.getText());
        updatecard(date1.getText(), date2.getText());
      
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
       
    }//GEN-LAST:event_date1KeyReleased

    private void card2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_card2MouseClicked
        // TODO add your handling code here:
         frame = new JFrame("Exit");
       if(JOptionPane.showConfirmDialog(frame, "Générer le rapport de paiements d'aujourd'hui?","Avertissement",
               JOptionPane.YES_NO_OPTION)== JOptionPane.YES_OPTION)
       {
          generatePDFReport(table);
       }
    }//GEN-LAST:event_card2MouseClicked

    public void generatePDFReport(JTable table) {
        try {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            int rowCount = model.getRowCount();
            int columnCount = model.getColumnCount();

            // Création du dossier "Print_Facture" à la racine du projet si nécessaire
            String printFactureFolderPath = "../Print_Rapport_Paiement/";
            createDirectoryIfNotExists(printFactureFolderPath);

            // Génération du nom du fichier PDF avec la date et l'heure actuelle
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String currentTime = LocalDateTime.now().format(formatter);
            String fileName = "Rapport_Paiement_" + currentTime + ".pdf";
            String filePath = printFactureFolderPath + fileName;

            // Création du document PDF
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, new FileOutputStream(filePath));

            // Ouverture du document
            document.open();

            // Titre "Facture de paiement"
            Paragraph title = new Paragraph("Rapport des paiements");
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
            Paragraph Total = new Paragraph("Paiement total date "+currentTime+" : "+nombre_paiement_today()+" Fr Cfa ");
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

    // Méthode pour créer un dossier si celui-ci n'existe pas
    public void generatePDFReport_card1(JTable table) {
        try {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            int rowCount = model.getRowCount();
            int columnCount = model.getColumnCount();

            // Création du dossier "Print_Facture" à la racine du projet si nécessaire
            String printFactureFolderPath = "../Print_Rapport_Paiement/";
            createDirectoryIfNotExists(printFactureFolderPath);

            // Génération du nom du fichier PDF avec la date et l'heure actuelle
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String currentTime = LocalDateTime.now().format(formatter);
            String fileName = "Rapport_des_paiements.pdf";
            String filePath = printFactureFolderPath + fileName;

            // Création du document PDF
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, new FileOutputStream(filePath));

            // Ouverture du document
            document.open();

            // Titre "Facture de paiement"
            Paragraph title = new Paragraph("Rapport de tous les paiement(s)");
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
            
            document.add(new Paragraph(" "));
            Paragraph Total = new Paragraph("Somme total "+paiement_total()+" Fr Cfa ");
            Total.setAlignment(Paragraph.ALIGN_LEFT);
            document.add(Total);
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
    private javax.swing.JScrollPane jScrollPane3;
    private tabbed.MaterialTabbed materialTabbed1;
    private javaswingdev.swing.RoundPanel roundPanel1;
    private javaswingdev.swing.table.Table table;
    private javaswingdev.swing.table.Table table1;
    // End of variables declaration//GEN-END:variables
}
