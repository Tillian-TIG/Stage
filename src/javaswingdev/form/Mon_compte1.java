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
import java.util.logging.Level;
import java.util.logging.Logger;
import javaswingdev.main.login;
import javax.swing.JOptionPane;

public class Mon_compte1 extends javax.swing.JPanel {
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

 
    public Mon_compte1() {
        initComponents();
        nom.setEnabled(false);
        service.setEnabled(false);
        date(); 
        affiche();
        verifie();
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

        panelBorder1 = new textfield.PanelBorder();
        nom = new textfield.TextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        numero = new textfield.TextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        ldate = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        verifie = new javax.swing.JLabel();
        identifiant = new textfield.TextField();
        service = new textfield.TextField();
        txtmdp = new textfield.PasswordField();
        verifie1 = new javax.swing.JLabel();
        ntxtmdp = new textfield.PasswordField();
        jLabel10 = new javax.swing.JLabel();
        verifie3 = new javax.swing.JLabel();

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
        jLabel4.setFont(new java.awt.Font("Corbel", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(25, 110, 129));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Identité");

        jLabel2.setBackground(new java.awt.Color(234, 233, 233));
        jLabel2.setFont(new java.awt.Font("Corbel", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(25, 110, 129));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Contact");

        jLabel3.setBackground(new java.awt.Color(234, 233, 233));
        jLabel3.setFont(new java.awt.Font("Corbel", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(25, 110, 129));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Identifiant");

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

        jLabel9.setBackground(new java.awt.Color(234, 233, 233));
        jLabel9.setFont(new java.awt.Font("Corbel", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(25, 110, 129));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Mot de passe");

        jLabel8.setBackground(new java.awt.Color(234, 233, 233));
        jLabel8.setFont(new java.awt.Font("Corbel", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(25, 110, 129));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Service");

        jLabel5.setBackground(new java.awt.Color(234, 233, 233));
        jLabel5.setFont(new java.awt.Font("Corbel", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(25, 110, 129));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Date");

        ldate.setBackground(new java.awt.Color(0, 102, 102));
        ldate.setFont(new java.awt.Font("Cambria", 0, 24)); // NOI18N
        ldate.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        jButton1.setBackground(new java.awt.Color(25, 110, 129));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("VALIDER");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel7.setBackground(new java.awt.Color(25, 110, 129));
        jLabel7.setFont(new java.awt.Font("Corbel", 1, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(25, 110, 129));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Mon Compte");

        verifie.setText(" ");

        identifiant.setLabelText("");
        identifiant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                identifiantActionPerformed(evt);
            }
        });
        identifiant.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                identifiantKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                identifiantKeyTyped(evt);
            }
        });

        service.setLabelText("");
        service.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                serviceActionPerformed(evt);
            }
        });
        service.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                serviceKeyTyped(evt);
            }
        });

        txtmdp.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtmdp.setLabelText("");
        txtmdp.setShowAndHide(true);
        txtmdp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtmdpActionPerformed(evt);
            }
        });
        txtmdp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtmdpKeyReleased(evt);
            }
        });

        verifie1.setText(" ");

        ntxtmdp.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        ntxtmdp.setLabelText("");
        ntxtmdp.setShowAndHide(true);
        ntxtmdp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ntxtmdpActionPerformed(evt);
            }
        });
        ntxtmdp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ntxtmdpKeyReleased(evt);
            }
        });

        jLabel10.setBackground(new java.awt.Color(234, 233, 233));
        jLabel10.setFont(new java.awt.Font("Corbel", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(25, 110, 129));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Nouveau Mot de passe");

        verifie3.setText(" ");

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelBorder1Layout.createSequentialGroup()
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGap(144, 144, 144)
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nom, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                                    .addComponent(numero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(ldate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(verifie, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(identifiant, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(verifie1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(txtmdp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelBorder1Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE))
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(service, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ntxtmdp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(verifie3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(167, 167, 167))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nom, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numero, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(verifie)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(identifiant, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(verifie1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtmdp, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ntxtmdp, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(verifie3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(service, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ldate, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 56, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void nomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nomActionPerformed

    private void nomKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nomKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if(!(Character.isAlphabetic(c) || (c == KeyEvent.VK_SPACE) || c == KeyEvent.VK_DELETE ))
        {
            getToolkit().beep();
            evt.consume();
            //JOptionPane.showMessageDialog(null, "Saisir uniquement que le prenom");
        }
    }//GEN-LAST:event_nomKeyTyped

    private void numeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numeroActionPerformed

    private void numeroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_numeroKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if(!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE)|| c== KeyEvent.VK_DELETE))
        {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Saisir uniquement que le numero de telephone");
        }
    }//GEN-LAST:event_numeroKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Date d = new Date();
        SimpleDateFormat dat = new SimpleDateFormat("dd-MM-yyyy");
        String ladate = dat.format(d);
        //String mdp = affiche();
       // java.sql.Date ladate = new java.sql.Date(d.getTime());
       
       
       
        if(verifie.getText().equalsIgnoreCase("Numero Invalide"))
                {
                    getToolkit().beep();
                    
                    JOptionPane.showMessageDialog(null,"numero invalide");
                }
       else if(verifie1.getText().equalsIgnoreCase("Identifiant Invalide Minimum 8 caractères"))
                {
                    getToolkit().beep();
                    
                    JOptionPane.showMessageDialog(null,"Identifiant Invalide");
                }
       else if(verifie3.getText().equalsIgnoreCase("Mot de passe Invalide Minimum 8 caractères"))
                {
                    getToolkit().beep();
                    
                    JOptionPane.showMessageDialog(null,"Nouveau mot de passe invalide");
                }
        else
        {

            try {

                Class.forName("com.mysql.jdbc.Driver");
                sqlConn = DriverManager.getConnection(dataConn,username,password);
                
                pst_search = sqlConn.prepareCall("Select * from users where mdp =?");
                pst_search.setString(1, txtmdp.getText());
                rs_2 = pst_search.executeQuery();
                
                 if(rs_2.next())
                {
                   pst = sqlConn.prepareStatement("Update users set telephone = ? , identifiant = ? , mdp = ? where idUser = ? ");
                    pst.setString(1, numero.getText());
                    pst.setString(2, identifiant.getText());
                    pst.setString(3, ntxtmdp.getText());
                    pst.setString(4, rs_2.getString("idUser"));
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Information mise à jour avec succès");
            
              //Form_dash_admin fd =new Form_dash_admin();
              //fd. Form_Home_Caissier();
                }
                 else
                 {
                      JOptionPane.showMessageDialog(null, "Erreur de mot de passe \n"
                              + "Recharger votre page pour saisir votre mot de passe et valider votre modification");
                 }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Mon_compte1.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Mon_compte1.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    public String affiche()
    {
      String passeword =  login.txtid.getText();
        try {
            Class.forName("com.mysql.jdbc.Driver");
                sqlConn = DriverManager.getConnection(dataConn,username,password);
                
                pst_search = sqlConn.prepareCall("Select * from users,role,service where identifiant=? and Role_id=rle_id and Service_id=id");
                pst_search.setString(1, passeword);
                rs = pst_search.executeQuery();
                if(rs.next())
                {
                    nom.setText(rs.getString("role_libelle").concat(" ").concat(rs.getString("nom_users").concat(" ").concat(rs.getString("prenom_users"))));
                    numero.setText(rs.getString("telephone"));
                    identifiant.setText(rs.getString("identifiant"));
                    service.setText(rs.getString("service_libelle"));
                    txtmdp.setText(rs.getString("mdp"));
                }
                
            
        } catch (Exception e) {
        }
        return passeword;
    }
    private void numeroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_numeroKeyReleased
        // TODO add your handling code here:
         String mobile = numero.getText();
        if(mobile.matches("^[0-9]*$") && mobile.length()==8)
        {
            verifie.setText("Numero Valide");
            verifie.setForeground(Color.green);
        }
        else if(mobile.trim().isEmpty())
            {
            verifie.setText("Numero Invalide");
             verifie.setForeground(Color.red);
        }
        else 
            {
            verifie.setText("Numero Invalide");
             verifie.setForeground(Color.red);
        }
    }//GEN-LAST:event_numeroKeyReleased

    private void identifiantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_identifiantActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_identifiantActionPerformed

    private void identifiantKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_identifiantKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_identifiantKeyTyped

    private void serviceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_serviceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_serviceActionPerformed

    private void serviceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_serviceKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_serviceKeyTyped

    private void txtmdpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmdpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmdpActionPerformed

    private void identifiantKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_identifiantKeyReleased
        // TODO add your handling code here:
         String mobile = identifiant.getText();
        if(mobile.length()>=8 )
        {
            verifie1.setText("Valide");
            verifie1.setForeground(Color.green);
        }
        else if(mobile.trim().isEmpty())
            {
            verifie1.setText("Identifiant Invalide Minimum 8 caractères");
             verifie1.setForeground(Color.red);
        }
        else
            {
            verifie1.setText("Identifiant Invalide Minimum 8 caractères");
             verifie1.setForeground(Color.red);
        }
    }//GEN-LAST:event_identifiantKeyReleased

    private void txtmdpKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmdpKeyReleased
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtmdpKeyReleased

    private void ntxtmdpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ntxtmdpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ntxtmdpActionPerformed

    private void ntxtmdpKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ntxtmdpKeyReleased
        // TODO add your handling code here:
        String mobile = ntxtmdp.getText();
        if(mobile.length()>=8)
        {
            verifie3.setText("Valide");
            verifie3.setForeground(Color.green);
        }
        else if( mobile.equalsIgnoreCase(" "))
            {
            verifie3.setText("Mot de passe Invalide Minimum 8 caractères");
             verifie3.setForeground(Color.red);
        }
        else 
            {
            verifie3.setText("Mot de passe Invalide Minimum 8 caractères");
             verifie3.setForeground(Color.red);
        }
    }//GEN-LAST:event_ntxtmdpKeyReleased

    public void verifie()
    {
        if(numero.getText().trim().isEmpty())
        {
             verifie.setText("Numero Invalide");
             verifie.setForeground(Color.red);
        }
        if(identifiant.getText().trim().isEmpty())
        {
            verifie1.setText("Identifiant Invalide Minimum 8 caractères");
             verifie1.setForeground(Color.red); 
        }
        if(ntxtmdp.getText().trim().isEmpty())
        {
             verifie3.setText("Mot de passe Invalide Minimum 8 caractères");
             verifie3.setForeground(Color.red);
        }
        if(txtmdp.getText().trim().isEmpty())
        {
             verifie3.setText("Mot de passe Invalide Minimum 8 caractères");
             verifie3.setForeground(Color.red);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private textfield.TextField identifiant;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel ldate;
    private textfield.TextField nom;
    public static textfield.PasswordField ntxtmdp;
    private textfield.TextField numero;
    private textfield.PanelBorder panelBorder1;
    private textfield.TextField service;
    public static textfield.PasswordField txtmdp;
    private javax.swing.JLabel verifie;
    private javax.swing.JLabel verifie1;
    private javax.swing.JLabel verifie3;
    // End of variables declaration//GEN-END:variables
}
