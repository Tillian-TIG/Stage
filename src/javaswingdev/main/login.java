package javaswingdev.main;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Pattern;
import javaswingdev.form.Form_Dashboard;

import javaswingdev.form.Form_Empty;
import javaswingdev.form.Ajout_personel;
import javaswingdev.menu.EventMenuSelected;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import raven.glasspanepopup.GlassPanePopup;
import sample.message.Message;

public class login extends javax.swing.JFrame {
    
    private static login main;
    
    public login() {
        initComponents();
        init();
          GlassPanePopup.install(this);
          jButton1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
          nw_mdp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
    
    private void init() {
        main = this;
        titleBar.initJFram(this);
    }
    
    
    
    public static login getMain() {
        return main;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        curvesPanel1 = new splashscreen.CurvesPanel();
        titleBar = new javaswingdev.swing.titlebar.TitleBar();
        panelBorder1 = new com.raven.swing.PanelBorder();
        txtid = new textfield.TextField();
        jLabel1 = new javax.swing.JLabel();
        txtmdp = new textfield.PasswordField();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        nw_mdp = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        panelBorder1.setBackground(new java.awt.Color(246, 246, 246));

        txtid.setLineColor(new java.awt.Color(3, 155, 216));
        txtid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtidKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Corbel", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(22, 93, 109));
        jLabel1.setText("Identifiant");

        txtmdp.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtmdp.setLabelText("");
        txtmdp.setLineColor(new java.awt.Color(3, 155, 216));
        txtmdp.setShowAndHide(true);
        txtmdp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtmdpActionPerformed(evt);
            }
        });
        txtmdp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtmdpKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Corbel", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(22, 93, 109));
        jLabel2.setText("Mot de passe");

        jButton1.setBackground(new java.awt.Color(22, 93, 109));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Connexion");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel7.setBackground(new java.awt.Color(234, 233, 233));
        jLabel7.setFont(new java.awt.Font("Corbel", 1, 34)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(22, 93, 109));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Se Connecter");

        nw_mdp.setFont(new java.awt.Font("Segoe UI Semibold", 2, 14)); // NOI18N
        nw_mdp.setForeground(new java.awt.Color(22, 93, 109));
        nw_mdp.setText("Mot de passe oublié ?");
        nw_mdp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nw_mdpMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel2)
                                .addComponent(txtmdp, javax.swing.GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
                                .addComponent(txtid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(nw_mdp, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtmdp, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nw_mdp)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout curvesPanel1Layout = new javax.swing.GroupLayout(curvesPanel1);
        curvesPanel1.setLayout(curvesPanel1Layout);
        curvesPanel1Layout.setHorizontalGroup(
            curvesPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(curvesPanel1Layout.createSequentialGroup()
                .addComponent(titleBar, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 997, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, curvesPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        curvesPanel1Layout.setVerticalGroup(
            curvesPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(curvesPanel1Layout.createSequentialGroup()
                .addComponent(titleBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 159, Short.MAX_VALUE)
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(197, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(curvesPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(curvesPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtmdpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmdpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmdpActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(txtid.getText().trim().isEmpty() || txtmdp.getText().trim().isEmpty())
        {
            Message obj = new Message();
                        obj.message.setText("Erreur de connexion");
        obj.eventOK(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               // System.out.println("Click OK");
                GlassPanePopup.closePopupLast();
            }
        });
        GlassPanePopup.showPopup(obj);
        }
        else
        {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hopital","root","primatologue");
                String sql = "Select * from users,role where identifiant = ? and mdp = ?  and users.Role_id = role.rle_id ";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1,txtid.getText());
                pst.setString(2, txtmdp.getText());
                ResultSet rs = pst.executeQuery();
                if(rs.next())
                {
                     if(rs.getString("etat").equalsIgnoreCase("hors service"))
                     {
                          Message obj = new Message();
                        obj.message.setText("Ce Compte est hors service");
        obj.eventOK(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               // System.out.println("Click OK");
                GlassPanePopup.closePopupLast();
            }
        });
        GlassPanePopup.showPopup(obj);
                       // JOptionPane.showMessageDialog(null, "Identifiant ou Mot de passe Incorrect ");
                        // txtid.setText("");
                        txtmdp.setText("");
                     }
                      else if( txtid.getText().equals(rs.getString("identifiant")) && txtmdp.getText().equals(rs.getString("mdp"))  )
                      {
                        String poste = rs.getString("role_libelle");
                        if(poste.equals("Secretaire"))
                        {
                            Main_sec menu1 = new Main_sec();
                            menu1.setVisible(true);
                            setVisible(false);
                            
                        } else if(poste.equals("Caissier"))
                        {
                            Main_cai menu1 = new Main_cai();
                            menu1.setVisible(true);
                            setVisible(false);
                            
                        }else if(poste.equals("Admission"))
                        {
                            Main_ad menu1 = new Main_ad();
                            menu1.setVisible(true);
                            setVisible(false);
                            
                        }else if(poste.equals("Infirmier"))
                        {
                            Main_inf menu1 = new Main_inf();
                            menu1.setVisible(true);
                            setVisible(false);
                            
                        }else if(poste.equals("Medecin"))
                        {
                            Main_med menu1 = new Main_med();
                            menu1.setVisible(true);
                            setVisible(false);
                            
                        }
                        else
                        {
                            Main menu = new Main();
                            menu.setVisible(true);
                           // mn.identifiant.setText(txtid.getText()
                            setVisible(false);
                            
                            
                        }
                        
                      }
                     else{
                        Message obj = new Message();
                        obj.message.setText("Identifiant ou Mot de passe Incorrect");
        obj.eventOK(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               // System.out.println("Click OK");
                GlassPanePopup.closePopupLast();
            }
        });
        GlassPanePopup.showPopup(obj);
                       // JOptionPane.showMessageDialog(null, "Identifiant ou Mot de passe Incorrect ");
                        // txtid.setText("");
                        txtmdp.setText("");
                    }
                }
                 else{
                     Message obj = new Message();
                        obj.message.setText("Identifiant ou Mot de passe Incorrect");
        obj.eventOK(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               // System.out.println("Click OK");
                GlassPanePopup.closePopupLast();
            }
        });
        GlassPanePopup.showPopup(obj);
                       // JOptionPane.showMessageDialog(null, "Identifiant ou Mot de passe Incorrect ");
                        // txtid.setText("");
                        txtmdp.setText("");
                }
             con.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void nw_mdpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nw_mdpMouseClicked
        // TODO add your handling code here:
        String id = txtid.getText();
       JPasswordField passwordField = new JPasswordField(20);
        int option = JOptionPane.showOptionDialog(null, passwordField, "Saisir votre information personnelle",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

        if (option == JOptionPane.OK_OPTION) {
            char[] password = passwordField.getPassword();
            // Traitez le mot de passe ici (convertissez-le en chaîne de caractères ou effectuez des opérations supplémentaires)
            String passwordString = new String(password);
           // JOptionPane.showMessageDialog(null, "Mot de passe saisi : " + passwordString);
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hopital","root","primatologue");
                String sql = "Select * from users,role where identifiant = ? and inf_perso = ?  and users.Role_id = role.rle_id ";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1,id);
                pst.setString(2, passwordString);
                ResultSet rs = pst.executeQuery();
                if(rs.next())
                {
                            JPasswordField passwordField2 = new JPasswordField(20);
                int option2 = JOptionPane.showOptionDialog(null, passwordField2, "Réinitialiser le mot de passe",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

                if (option2 == JOptionPane.OK_OPTION) {
                    char[] password2 = passwordField2.getPassword();
                    // Traitez le mot de passe ici (convertissez-le en chaîne de caractères ou effectuez des opérations supplémentaires)
                    String passwordString2 = new String(password2);
                    try {
                                Class.forName("com.mysql.jdbc.Driver");
                        Connection con2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/hopital","root","primatologue");
                        String sql2 = "Update users set mdp = ? where identifiant = ? and inf_perso =?";
                        
                        PreparedStatement pst2 = con2.prepareStatement(sql2);
                        pst2.setString(1,passwordString2);
                        pst2.setString(2,id);
                        pst2.setString(3, passwordString);
                        pst2.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Mot de passe réinitialiser");
                        txtmdp.setText("");
                    } catch (Exception e) {
                    }

                }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Erreur d'Information personnelle ou Identifient");
                }
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_nw_mdpMouseClicked

    private void txtidKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtidKeyTyped
        // TODO add your handling code here:
        String regex = "^[a-zA-Z'0123456789@]*$";
    Pattern pattern = Pattern.compile(regex);
   
    char c = evt.getKeyChar();
    if (!pattern.matcher(String.valueOf(c)).matches()) {
        getToolkit().beep();
        evt.consume();
        // JOptionPane.showMessageDialog(null, "Saisir uniquement que le prenom");
    }
    }//GEN-LAST:event_txtidKeyTyped

    private void txtmdpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmdpKeyTyped
        // TODO add your handling code here:
         String regex = "^[a-zA-Z'0123456789@]*$";
    Pattern pattern = Pattern.compile(regex);
   
    char c = evt.getKeyChar();
    if (!pattern.matcher(String.valueOf(c)).matches()) {
        getToolkit().beep();
        evt.consume();
        // JOptionPane.showMessageDialog(null, "Saisir uniquement que le prenom");
    }
    }//GEN-LAST:event_txtmdpKeyTyped

    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                 new splashscreen.SplashScreen(null, true).setVisible(true);
                new login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private splashscreen.CurvesPanel curvesPanel1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel nw_mdp;
    private com.raven.swing.PanelBorder panelBorder1;
    private javaswingdev.swing.titlebar.TitleBar titleBar;
    public static textfield.TextField txtid;
    public static textfield.PasswordField txtmdp;
    // End of variables declaration//GEN-END:variables
}
