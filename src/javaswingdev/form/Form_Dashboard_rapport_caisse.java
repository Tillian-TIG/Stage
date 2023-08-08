package javaswingdev.form;

import java.awt.Color;
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
import java.util.Date;
import java.util.Vector;
import javaswingdev.GoogleMaterialDesignIcon;
import javaswingdev.card.ModelCard;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

public class Form_Dashboard_rapport_caisse extends javax.swing.JPanel {
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
    public Form_Dashboard_rapport_caisse() {
        initComponents();
        init();
    }

    private void init() {
       table.fixTable(jScrollPane3);
       table2.fixTable(jScrollPane4);
       table1.fixTable(jScrollPane5);
       tab_all_acte();
       card1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       card2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       card3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       table1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       table2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       tab_all_compte();
       tab_2();
       tab_1();
        //  init card data
        card1.setData(new ModelCard(GoogleMaterialDesignIcon.CREDIT_CARD, null, null,"Payement Aujourd'hui(s) ", paiement_consultation()+" Fr Cfa"));
        card3.setData(new ModelCard(GoogleMaterialDesignIcon.CREDIT_CARD, null, null, "Impayer d'Aujourd'hui ", paiement_operation()+" Fr Cfa"));
        card2.setData(new ModelCard(GoogleMaterialDesignIcon.CREDIT_CARD, null, null, "Avoir en caisse ",nbre_consultation_today()+" Fr Cfa"));
        //card4.setData(new ModelCard(null, null, null, "$ 300.00", "Report Profit Monthly"));
    }
    
    
            public void tab_all_acte ()
                    
    {
         Date d = new Date();
        SimpleDateFormat form = new SimpleDateFormat("dd-MM-yyyy");
        String ladate = form.format(d);
                      DefaultTableModel model = (DefaultTableModel) table.getModel();

            model.setRowCount(0);
            
            try {
                Class.forName("com.mysql.jdbc.Driver");
                sqlConn = DriverManager.getConnection(dataConn,username,password);
                pst_tab = sqlConn.prepareStatement("Select * from recu_consultation,service,patient where date_recu = ? and recu_id_patient = idpatient and idservice = id ORDER BY recu_id_acte DESC");
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
                    String column8Data = rs_tab.getString("etat_recu").toUpperCase();
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
   
             public void tab_2 ()
                    
    {
         Date d = new Date();
        SimpleDateFormat form = new SimpleDateFormat("dd-MM-yyyy");
        String ladate = form.format(d);
                      DefaultTableModel model = (DefaultTableModel) table2.getModel();

            model.setRowCount(0);
            
            try {
               Class.forName("com.mysql.jdbc.Driver");
                sqlConn = DriverManager.getConnection(dataConn,username,password);
                pst_tab = sqlConn.prepareStatement("Select * from acte,service,patient where etat = 'Non Payer' and date_acte=? and id_patient = idpatient and id_service = id ORDER BY num_acte DESC");
                pst_tab.setString(1, ladate);
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
    table2.getColumnModel().getColumn(8).setCellRenderer(cellRenderer);
}catch (Exception e) {
    e.printStackTrace();
}
    } 
   
              public void tab_1 ()
                    
    {
         Date d = new Date();
        SimpleDateFormat form = new SimpleDateFormat("dd-MM-yyyy");
        String ladate = form.format(d);
                      DefaultTableModel model = (DefaultTableModel) table1.getModel();

            model.setRowCount(0);
            
            try {
                Class.forName("com.mysql.jdbc.Driver");
                sqlConn = DriverManager.getConnection(dataConn,username,password);
                pst_tab = sqlConn.prepareStatement("Select * from recu_consultation,service,patient where recu_id_patient = idpatient and idservice = id ORDER BY recu_id_acte DESC");
                rs_tab = pst_tab.executeQuery () ;
                while (rs_tab.next()) {
                    String column1Data = rs_tab.getString("recu_id_acte");
                    String column2Data = rs_tab.getString("nom_patient");
                    String column3Data = rs_tab.getString("prenom_patient");
                    String column4Data = rs_tab.getString("telephone_patient");            
                    String column5Data = rs_tab.getString("service_libelle");
                    String column6Data = rs_tab.getString("acte");
                   
                    String column7Data = rs_tab.getString("montant_net").concat(" FrCfa");
                    String column8Data = rs_tab.getString("etat_recu").toUpperCase();
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
    table1.getColumnModel().getColumn(7).setCellRenderer(cellRenderer);
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
              card1.setData(new ModelCard(GoogleMaterialDesignIcon.PAYMENT, null, null, search_nbre(nbre1, nbre2)+" Facture(s) de Consultaion(s) ","entre "+nbre1+" et "+nbre2)); 
          }
         public void tab_all_compte (){  
            DefaultTableModel model = (DefaultTableModel) table2.getModel();
            
            model.setRowCount(0);
            
            try {
                Class.forName("com.mysql.jdbc.Driver");
                sqlConn = DriverManager.getConnection(dataConn,username,password);
                pst_tab = sqlConn.prepareStatement("Select * from factureoperation,operation,service,dm where id_Operation = idOperation and id_fac_dm = idDm and id_fac_op_ser = id ");
                rs_tab = pst_tab.executeQuery () ;
                while (rs_tab.next()) {
                    String column1Data = rs_tab.getString("idFacture");
                    String column2Data = rs_tab.getString("idOperation");
                    String column3Data = rs_tab.getString("nom");
                    String column4Data = rs_tab.getString("prenom");
                    String column5Data = rs_tab.getString("contact");
                    String column6Data = rs_tab.getString("service_libelle");
                    String column7Data = rs_tab.getString("libelleOperation");
                    String column8Data = rs_tab.getString("prixOperation");
                    String column9Data = rs_tab.getString("date_paiement_op");
                    
                     
                 

                    // Ajouter d'autres colonnes si nécessaire

                    model.addRow(new Object[]{column1Data, column2Data, column3Data, column4Data, column5Data, column6Data, column7Data,column8Data,column9Data});
               }
            }
            
            catch (ClassNotFoundException | SQLException e) {
            }
       
 }
          
         public void search_operation (String nbre, String nbre2){  
DefaultTableModel model = (DefaultTableModel) table2.getModel();
            
            model.setRowCount(0);
            
            try {
                Class.forName("com.mysql.jdbc.Driver");
                sqlConn = DriverManager.getConnection(dataConn,username,password);
                pst_tab = sqlConn.prepareStatement("SELECT * FROM  factureoperation,operation,dm,service WHERE date_paiement_op BETWEEN ? AND ? and id_Operation = idOperation and id_fac_dm=idDm and id_fac_op_ser = id ");
                pst_tab.setString(1, nbre);
                pst_tab.setString(2, nbre2);
                rs_tab = pst_tab.executeQuery () ;
                while (rs_tab.next()) {
                    String column1Data = rs_tab.getString("idFacture");
                    String column2Data = rs_tab.getString("idOperation");
                    String column3Data = rs_tab.getString("nom");
                    String column4Data = rs_tab.getString("prenom");
                    String column5Data = rs_tab.getString("contact");
                    String column6Data = rs_tab.getString("service_libelle");
                    String column7Data = rs_tab.getString("libelleOperation");
                    String column8Data = rs_tab.getString("prixOperation");
                    String column9Data = rs_tab.getString("date_paiement_op");
                     

                    // Ajouter d'autres colonnes si nécessaire

                    model.addRow(new Object[]{column1Data, column2Data, column3Data, column4Data, column5Data, column6Data,column7Data,column8Data,column9Data});
               }
            }
            
            catch (ClassNotFoundException | SQLException e) {
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
    public void updatecard2 ( String nbre1, String nbre2)
          {
              card3.setData(new ModelCard(GoogleMaterialDesignIcon.PAYMENT, null, null, nombre_operation(nbre1, nbre2)+" Facture(s) d'Operation ","entre "+nbre1+" et "+nbre2)); 
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
        
        public String paiement_consultation ()
    {
        Date d = new Date();
        SimpleDateFormat form = new SimpleDateFormat("dd-MM-yyyy");
        String ladate = form.format(d);
         String prix_total = null;
     try {
            Class.forName("com.mysql.jdbc.Driver");
                 sqlConn = DriverManager.getConnection(dataConn,username,password);
                 pst = sqlConn.prepareCall("SELECT sum(montant_net) FROM recu_consultation where date_recu = ?");
                 pst.setString(1, ladate);
                 rs = pst.executeQuery();
                 if (rs.next())
                         
                         {
                              prix_total = rs.getString("sum(montant_net)");
                       //   nombre.setText(nbre);
                         }
        } catch (Exception e) {
        }
     
      if (prix_total == null) {
        prix_total = "0";
    }
    
    return prix_total;
       
    }
    
        public String paiement_operation ()
    {
         String prix_total = null;
     try {
            Class.forName("com.mysql.jdbc.Driver");
                 sqlConn = DriverManager.getConnection(dataConn,username,password);
                 pst = sqlConn.prepareCall("SELECT sum(prix) FROM acte where etat = 'Non Payer'");
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
                 pst = sqlConn.prepareCall("SELECT SUM(montant_net) FROM recu_consultation");
              
                 rs = pst.executeQuery();
                 if (rs.next())
                         
                         {
                             
                              nbre_today = rs.getString("SUM(montant_net)");
                       //   nombre.setText(nbre);
                         }
        } catch (Exception e) {
        }
    
      if ( nbre_today == null) {
        nbre_today = "0";
    }
    
    return  nbre_today;
      
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
                 pst = sqlConn.prepareCall("SELECT COUNT(*) FROM factureoperation where  date_paiement_op = ?");
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
        Date d = new Date();
        SimpleDateFormat form = new SimpleDateFormat("dd-MM-yyyy");
        String ladate = form.format(d);
        String nbre_total = null;
     try {
            Class.forName("com.mysql.jdbc.Driver");
                 sqlConn = DriverManager.getConnection(dataConn,username,password);
                 pst = sqlConn.prepareCall("SELECT COUNT(*) FROM recu_consultation where date_recu=?");
                 pst.setString(1, ladate);
                 rs = pst.executeQuery();
                 if (rs.next())
                         
                         {
                              nbre_total = rs.getString("COUNT(*)");
                       //   nombre.setText(nbre);
                         }
        } catch (Exception e) {
        }
     if (nbre_total.equalsIgnoreCase(null))
     {
         nbre_total = "0";
        return nbre_total; 
     }
     else 
     { 
        return nbre_total; 
     }  
    }
    public String nbre_operation_total ()
    {
        String nbre_total = null;
     try {
            Class.forName("com.mysql.jdbc.Driver");
                 sqlConn = DriverManager.getConnection(dataConn,username,password);
                 pst = sqlConn.prepareCall("SELECT COUNT(*) FROM acte where etat = 'Non Payer'");
                 rs = pst.executeQuery();
                 if (rs.next())
                         
                         {
                              nbre_total = rs.getString("COUNT(*)");
                       //   nombre.setText(nbre);
                         }
        } catch (Exception e) {
        }
        if (nbre_total.equalsIgnoreCase(null))
     {
         nbre_total = "0";
        return nbre_total; 
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
        jScrollPane3 = new javax.swing.JScrollPane();
        table = new javaswingdev.swing.table.Table();
        jScrollPane4 = new javax.swing.JScrollPane();
        table2 = new javaswingdev.swing.table.Table();
        jScrollPane5 = new javax.swing.JScrollPane();
        table1 = new javaswingdev.swing.table.Table();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        card2 = new javaswingdev.card.Card();
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
                "N°Acte", "Nom", "Prenom", "Numero", "Acte", "Service", "Montant", "Etat", "Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
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
        jScrollPane3.setViewportView(table);

        materialTabbed1.addTab("Paiement d'Aujourd'hui", jScrollPane3);

        table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N°Acte", "Nom", "Prenom", "Numero", "Acte", "Examen", "Service", "Montant", "Etat", "Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
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
        jScrollPane4.setViewportView(table2);

        materialTabbed1.addTab("Impayer d'Aujourd'hui", jScrollPane4);

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N°Acte", "Nom", "Prenom", "Numero", "Acte", "Service", "Montant", "Etat", "Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
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
        jScrollPane5.setViewportView(table1);

        materialTabbed1.addTab("Caisse", jScrollPane5);

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
                .addComponent(materialTabbed1, javax.swing.GroupLayout.DEFAULT_SIZE, 566, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLayeredPane1.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        card2.setColor1(new java.awt.Color(95, 211, 226));
        card2.setColor2(new java.awt.Color(26, 166, 170));
        card2.setIcon(javaswingdev.GoogleMaterialDesignIcon.PIE_CHART);
        jLayeredPane1.add(card2);

        card1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                card1MouseClicked(evt);
            }
        });
        jLayeredPane1.add(card1);

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
                    .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLayeredPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void card1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_card1MouseClicked
        // TODO add your handling code here:
        String newpath = "doc";
        File directory = new File(newpath);
        if(!directory.exists())
        {
            directory.mkdirs();
        }
        File sourceFile = null;
        File destinationFile = null;

    }//GEN-LAST:event_card1MouseClicked

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:
        init();
    }//GEN-LAST:event_formMouseClicked

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tableMouseClicked

    private void table2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_table2MouseClicked

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
    private javaswingdev.card.Card card2;
    private javaswingdev.card.Card card3;
    private com.raven.datechooser.DateChooser dateChooser1;
    private com.raven.datechooser.DateChooser dateChooser2;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private tabbed.MaterialTabbed materialTabbed1;
    private javaswingdev.swing.RoundPanel roundPanel1;
    private javaswingdev.swing.table.Table table;
    private javaswingdev.swing.table.Table table1;
    private javaswingdev.swing.table.Table table2;
    // End of variables declaration//GEN-END:variables
}
