package javaswingdev.form;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javaswingdev.main.login;
import javax.swing.JOptionPane;

public class Form_consultation extends javax.swing.JPanel {
    private static final String username = "root";
 private static final String password = "primatologue";
 private static final String dataConn = "jdbc:mysql://localhost:3306/hopital";
 
 Connection sqlConn = null;
 Connection sqlSearch = null;
 PreparedStatement pst = null;
 PreparedStatement pst_search = null;
 PreparedStatement pst_num = null;
 ResultSet rs = null;
 ResultSet rs_2 = null;
 ResultSet rs_3 = null;
 PreparedStatement pst_tab = null;
 ResultSet rs_tab = null;
 int q, i , id, deleteItem;

    public Form_consultation() {
        initComponents();
        date();
      // combo_analyse();
       
        
    }
    public void date()
    {
    Date d = new Date();
    SimpleDateFormat dat = new SimpleDateFormat("EEEE-dd-MMM-yyyy");
    ldate.setText(dat.format(d));
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooser1 = new com.raven.datechooser.DateChooser();
        panelBorder1 = new textfield.PanelBorder();
        nom = new textfield.TextField();
        jLabel4 = new javax.swing.JLabel();
        prenom = new textfield.TextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        numero = new textfield.TextField();
        jLabel5 = new javax.swing.JLabel();
        ldate = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        verifie = new javax.swing.JLabel();
        dn = new textfield.TextField();
        sexe = new combobox.Combobox();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();

        dateChooser1.setTextRefernce(dn);

        setOpaque(false);

        panelBorder1.setBackground(new java.awt.Color(248, 248, 248));

        nom.setLabelText("");
        nom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomActionPerformed(evt);
            }
        });
        nom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nomKeyTyped(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(234, 233, 233));
        jLabel4.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(25, 110, 129));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Nom");

        prenom.setLabelText("");
        prenom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                prenomKeyTyped(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(234, 233, 233));
        jLabel2.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(25, 110, 129));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Prenoms");

        jLabel3.setBackground(new java.awt.Color(234, 233, 233));
        jLabel3.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(25, 110, 129));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Telephone");

        numero.setLabelText("");
        numero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numeroActionPerformed(evt);
            }
        });
        numero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                numeroKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                numeroKeyTyped(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(234, 233, 233));
        jLabel5.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(25, 110, 129));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("Date");

        ldate.setBackground(new java.awt.Color(0, 102, 102));
        ldate.setFont(new java.awt.Font("Cambria", 0, 24)); // NOI18N
        ldate.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        jButton1.setBackground(new java.awt.Color(22, 93, 109));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("ENREGISTRER");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel7.setBackground(new java.awt.Color(25, 110, 129));
        jLabel7.setFont(new java.awt.Font("Cambria", 1, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(25, 110, 129));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Enregistrement");

        verifie.setText(" ");

        dn.setLabelText("");
        dn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                dnKeyTyped(evt);
            }
        });

        sexe.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "M", "F" }));
        sexe.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        sexe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sexeActionPerformed(evt);
            }
        });

        jLabel11.setBackground(new java.awt.Color(234, 233, 233));
        jLabel11.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(25, 110, 129));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel11.setText("Naissance");

        jLabel12.setBackground(new java.awt.Color(234, 233, 233));
        jLabel12.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(25, 110, 129));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setText("Sexe");

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelBorder1Layout.createSequentialGroup()
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelBorder1Layout.createSequentialGroup()
                                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                                .addGap(154, 154, 154)
                                                .addComponent(jLabel3))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                                                .addGap(142, 142, 142)
                                                .addComponent(jLabel4)))
                                        .addGap(15, 15, 15))
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(panelBorder1Layout.createSequentialGroup()
                                    .addGap(172, 172, 172)
                                    .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(panelBorder1Layout.createSequentialGroup()
                                            .addComponent(jLabel2)
                                            .addGap(0, 0, Short.MAX_VALUE)))))
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(prenom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(numero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ldate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(verifie, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(sexe, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE))))
                .addGap(168, 168, 168))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(13, 13, 13)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                        .addComponent(nom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(prenom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dn, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sexe, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(numero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(verifie)))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ldate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void nomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nomActionPerformed

    private void nomKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nomKeyTyped
        // TODO add your handling code here:
       String regex = "^[a-zA-Z']*$";
    Pattern pattern = Pattern.compile(regex);
   
    char c = evt.getKeyChar();
    if (!pattern.matcher(String.valueOf(c)).matches()) {
        getToolkit().beep();
        evt.consume();
        // JOptionPane.showMessageDialog(null, "Saisir uniquement que le prenom");
    }
    }//GEN-LAST:event_nomKeyTyped

    private void prenomKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_prenomKeyTyped
        // TODO add your handling code here:
        String regex = "^[a-zA-Z']*$";
    Pattern pattern = Pattern.compile(regex);
   
    char c = evt.getKeyChar();
    if (!pattern.matcher(String.valueOf(c)).matches()) {
        getToolkit().beep();
        evt.consume();
        // JOptionPane.showMessageDialog(null, "Saisir uniquement que le prenom");
    }
    }//GEN-LAST:event_prenomKeyTyped

    private void numeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numeroActionPerformed

    private void numeroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_numeroKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if(!(Character.isDigit(c)))
        {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Saisir uniquement que le numero de telephone");
        }
    }//GEN-LAST:event_numeroKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int taille_nom = nom.getText().length();
        int taille_prenom = prenom.getText().length();
        int taille_numero = numero.getText().length();
      String id_users = num_id() ;
        
        //int consultation = Integer.valueOf(nbre_con);
        Date d = new Date();
        SimpleDateFormat dat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
       dat.setTimeZone(TimeZone.getTimeZone("UTC")); // Définit la zone horaire à UTC
       String ladate = dat.format(d);
       // java.sql.Date ladate = new java.sql.Date(d.getTime());
       
        
                if(numero.getText().equals("") || nom.getText().equals("") || numero.getText().equals(""))
        {
            getToolkit().beep();
            
             JOptionPane.showMessageDialog(null, "Veillez remplir tous les champ d'information \n"
                + "Enregistrement Echoué");
            
        }

        else if (taille_nom == 2)
        {
            getToolkit().beep();
            JOptionPane.showMessageDialog(null, "Nom Invalide");
        }

        else if (taille_prenom == 2)
        {
            getToolkit().beep();
            JOptionPane.showMessageDialog(null, "Preoms Invalide");
        }
         else if(verifie.getText().equalsIgnoreCase("Numero Invalide"))
                {
                    getToolkit().beep();
                    
                    JOptionPane.showMessageDialog(null,"numero invalide");
                } 
        
        else
        {

            try {

                Class.forName("com.mysql.jdbc.Driver");
                sqlConn = DriverManager.getConnection(dataConn,username,password);
                pst_num = sqlConn.prepareCall("Select * from patient where nom_patient = ? and prenom_patient = ?");
                pst_num.setString(1, nom.getText());
                pst_num.setString(2, prenom.getText());
                rs_3 = pst_num.executeQuery();
                pst_tab = sqlConn.prepareCall("Select * from patient where telephone_patient = ?");
                pst_tab.setString(1, numero.getText());
                rs_tab = pst_tab.executeQuery();
                //************************
                 
                 if(rs_3.next())
                {
                   
                    getToolkit().beep();
                    JOptionPane.showMessageDialog(null,"Ce patient est déja enregistré");
                    
                }
                 else if(rs_tab.next())
                {
                   
                    getToolkit().beep();
                    JOptionPane.showMessageDialog(null,"Le Numéro de télphone appartient à un patient");
                    
                }
                 
                else
                {
                   pst = sqlConn.prepareStatement("insert into patient(nom_patient,prenom_patient,sexe,date_naissance,telephone_patient,personne) value(?,?,?,?,?,?)");
                    pst.setString(1, nom.getText().toUpperCase());
                    pst.setString(2, prenom.getText());
                     pst.setString(3,sexe.getSelectedItem().toString());
                    pst.setString(4, dn.getText());
                    pst.setString(5,numero.getText());
                    pst.setString(6, nom.getText().concat(" ").concat(prenom.getText()));
                    
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Enrégistrement effectué");
                    
                   
                    nom.setText("");
                    prenom.setText("");
                    numero.setText("");
                    
                    
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Form_consultation.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Form_consultation.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    
    
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
      
     
    private void numeroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_numeroKeyReleased
        // TODO add your handling code here:
         String mobile = numero.getText();
        if(mobile.matches("^[0-9]*$") && mobile.length()>=8)
        {
            verifie.setText("Numero Valide");
            verifie.setForeground(Color.green);
        }
        else
            {
            verifie.setText("Numero Invalide");
             verifie.setForeground(Color.red);
        }
        
    }//GEN-LAST:event_numeroKeyReleased

    private void dnKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dnKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_dnKeyTyped

    private void sexeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sexeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sexeActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.datechooser.DateChooser dateChooser1;
    private textfield.TextField dn;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel ldate;
    private textfield.TextField nom;
    private textfield.TextField numero;
    private textfield.PanelBorder panelBorder1;
    private textfield.TextField prenom;
    private combobox.Combobox sexe;
    private javax.swing.JLabel verifie;
    // End of variables declaration//GEN-END:variables
}
