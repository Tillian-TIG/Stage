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
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class Ajout_personel extends javax.swing.JPanel {
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

    public Ajout_personel() {
        initComponents();
        date();
        combo_poste();
       // comboservice();
        poste.setEditable(false);
        service.setEditable(false);
        
    }
    public void date()
    {
    Date d = new Date();
    SimpleDateFormat dat = new SimpleDateFormat("EEEE-dd-MMM-yyyy");
    ldate.setText(dat.format(d));
    }
     public void combo_poste ()
     {
         try {
              Class.forName("com.mysql.jdbc.Driver");
                sqlConn = DriverManager.getConnection(dataConn,username,password);
                pst = sqlConn.prepareCall("Select * from role where role_libelle != 'Admin' ");
                 rs = pst.executeQuery();
                 while(rs.next())
                 {
                 poste.addItem(rs.getString("role_libelle"));
                 }
         } catch (ClassNotFoundException | SQLException e) {
         }
     }
      public void comboservice ()
     {
         try {
              Class.forName("com.mysql.jdbc.Driver");
                sqlConn = DriverManager.getConnection(dataConn,username,password);
                pst = sqlConn.prepareCall("Select * from service where service_libelle != 'Caisse' and service_libelle != 'Admin' and etat_service='actif'");
                 rs = pst.executeQuery();
                 while(rs.next())
                 {  
                  service.addItem(rs.getString("service_libelle"));
                 }
         } catch (ClassNotFoundException | SQLException e) {
         }
     }
       public String id_poste ()
     {
         String pos = poste.getSelectedItem().toString();
         String id_poste = null ;
         try {
              Class.forName("com.mysql.jdbc.Driver");
                sqlConn = DriverManager.getConnection(dataConn,username,password);
                pst = sqlConn.prepareCall("Select * from role where role_libelle = ? ");
                    pst.setString(1, pos);
                
                  rs = pst.executeQuery();
                 if (rs.next()){
                      id_poste = rs.getString("rle_id");
                 }
                  
         } catch (ClassNotFoundException | SQLException e) {
         }
        
     return id_poste; 
     }
       
       private String identifiant(String poste) {
    StringBuilder id = new StringBuilder();
    
    // Ajouter les 4 premières lettres du poste en majuscules
    id.append(poste.substring(0, 4).toUpperCase());
    
    // Générer les 4 derniers caractères aléatoirement
    Random random = new Random();
    String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789#";
    for (int i = 0; i < 4; i++) {
        char randomChar = characters.charAt(random.nextInt(characters.length()));
        id.append(randomChar);
    }
    
    return id.toString();
}
       private String password(String poste) {
    StringBuilder id = new StringBuilder();
    
    // Ajouter les 4 premières lettres du poste en majuscules
    id.append(poste.substring(0, 4).toUpperCase());
    
    // Générer les 4 derniers caractères aléatoirement
    Random random = new Random();
    String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789#";
    for (int i = 0; i < 4; i++) {
        char randomChar = characters.charAt(random.nextInt(characters.length()));
        id.append(randomChar);
    }
    
    return id.toString();
}
       public String id_service ()
     {
         String ser = service.getSelectedItem().toString();
         
         String id_service = null ;
         try {
              Class.forName("com.mysql.jdbc.Driver");
                sqlConn = DriverManager.getConnection(dataConn,username,password);
                pst = sqlConn.prepareCall("Select * from service where service_libelle=?");
                    pst.setString(1, ser);
                  rs = pst.executeQuery();
                 if(rs.next())
                 {
                  id_service = rs.getString("id");
                 }
         } catch (ClassNotFoundException | SQLException e) {
         }
        
     return id_service; 
     }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new textfield.PanelBorder();
        nom = new textfield.TextField();
        jLabel4 = new javax.swing.JLabel();
        prenom = new textfield.TextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        numero = new textfield.TextField();
        poste = new combobox.Combobox();
        service = new combobox.Combobox();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        ldate = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        verifie = new javax.swing.JLabel();

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

        poste.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        poste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                posteActionPerformed(evt);
            }
        });

        service.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        service.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                serviceActionPerformed(evt);
            }
        });

        jLabel9.setBackground(new java.awt.Color(234, 233, 233));
        jLabel9.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(25, 110, 129));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("Poste");

        jLabel8.setBackground(new java.awt.Color(234, 233, 233));
        jLabel8.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(25, 110, 129));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("Service");

        jLabel5.setBackground(new java.awt.Color(234, 233, 233));
        jLabel5.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(25, 110, 129));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
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
        jLabel7.setFont(new java.awt.Font("Cambria", 1, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(25, 110, 129));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Ajout du personnel");

        verifie.setText(" ");

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(panelBorder1Layout.createSequentialGroup()
                                    .addGap(201, 201, 201)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE))
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelBorder1Layout.createSequentialGroup()
                                        .addGap(220, 220, 220)
                                        .addComponent(jLabel4))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                                        .addGap(78, 78, 78)
                                        .addComponent(jLabel2))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(service, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                            .addComponent(nom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(prenom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(numero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ldate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(poste, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(verifie, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(168, 168, 168))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel7)
                .addGap(43, 43, 43)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nom, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(prenom, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numero, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(verifie)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(poste, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(service, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                .addGap(29, 29, 29))
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
        if(!(Character.isDigit(c) ))
        {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Saisir uniquement que le numero de telephone");
        }
    }//GEN-LAST:event_numeroKeyTyped

    private void posteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_posteActionPerformed
        // TODO add your handling code here: 
        if(poste.getSelectedItem().toString().equalsIgnoreCase("Caissier") || poste.getSelectedItem().toString().equalsIgnoreCase("Admission")){
                         service.removeAllItems();
                         service.addItem("Service general");
           }
        else if(poste.getSelectedItem().toString().equalsIgnoreCase("Secretaire"))
        {
         service.removeAllItems();
         service.addItem("Cardiologie");
         service.addItem("Dermatologie");
         service.addItem("Ophtamologie");
         service.addItem("General");
            
        }else if(poste.getSelectedItem().toString().equalsIgnoreCase("Infirmier"))
        {
            service.removeAllItems();
        service.addItem("Cardiologie");
         service.addItem("Dermatologie");
         service.addItem("Ophtamologie");
         service.addItem("General");
            
        }else if(poste.getSelectedItem().toString().equalsIgnoreCase("Medecin"))
        {
            service.removeAllItems();
         service.addItem("Cardiologie");
         service.addItem("Dermatologie");
         service.addItem("Ophtamologie");
         service.addItem("General");
            
        }
    }//GEN-LAST:event_posteActionPerformed

    private void serviceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_serviceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_serviceActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int taille_nom = nom.getText().length();
        int taille_prenom = prenom.getText().length();
        int taille_numero = numero.getText().length();
        String id_poste = id_poste();
        String id_service;
       
        

        Date d = new Date();
        SimpleDateFormat dat = new SimpleDateFormat("dd-MM-yyyy");
        String ladate = dat.format(d);
       // java.sql.Date ladate = new java.sql.Date(d.getTime());
        String service = this.service.getSelectedItem().toString();
        String role = poste.getSelectedItem().toString();


        String id = identifiant (poste.getSelectedItem().toString());
        String mdp = password (poste.getSelectedItem().toString());
        
       if(role.equals("Secretaire"))
       {
           id_service = id_service();
       
       }
       else if(role.equals("Infirmier"))
       {
           id_service = id_service();
       }
       else if(role.equals("Caissier"))
       {
           id_service = "5";
       
       }
       else if(role.equals("Admission"))
       {
           id_service = "6";
       
       }
       else
       {
           id_service = id_service();
       } 
        if(numero.getText().equals("") || nom.getText().equals("") || numero.getText().equals(""))
        {
            getToolkit().beep();
            
             JOptionPane.showMessageDialog(null, "Veillez remplir tous les champ d'information \n"
                + "Enregistrement Echoué");
            
        }

        else if (taille_nom == 1)
        {
            getToolkit().beep();
            JOptionPane.showMessageDialog(null, "Nom Invalide");
        }

        else if (taille_prenom == 1)
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
                
                pst_search = sqlConn.prepareCall("Select * from users where nom_users=? and prenom_users=?");
                pst_search.setString(1, nom.getText());
                pst_search.setString(2, prenom.getText());
                rs_2 = pst_search.executeQuery();
                
                pst_num = sqlConn.prepareCall("Select * from users where telephone=? ");
                pst_num.setString(1, numero.getText());
                rs_3 = pst_num.executeQuery();
                if(rs_2.next())
                {
                    getToolkit().beep();
                    
                    //JOptionPane.showMessageDialog(null, nom.getText()+" "+prenom.getText()+" est déja enregistré");
                }
                else if(rs_3.next())
                {
                    getToolkit().beep();
                    
                    JOptionPane.showMessageDialog(null,numero.getText()+"est déja enregistré");
                }
               
                else
                {

                    pst = sqlConn.prepareStatement("insert into users(Role_id,Service_id,nom_users,prenom_users,telephone,date,identifiant,mdp) value(?,?,?,?,?,?,?,?)");
                    pst.setString(1, id_poste);
                    pst.setString(2, id_service);
                    pst.setString(3, nom.getText().toUpperCase());
                    pst.setString(4, prenom.getText());
                    pst.setString(5, numero.getText());
                    pst.setString(6, ladate);
                    pst.setString(7, id);
                    pst.setString(8, mdp);
                    pst.executeUpdate();
                     JOptionPane.showMessageDialog(null, "Enrégistrement éffectué avec succès \n"
                            + " Veillez noter ces informations \n"
                            + " Identifiant: "+ id +" \n"
                            + " Mot de passe: "+ mdp +" \n"
                            + " Pour "
                            + " "+ poste.getSelectedItem().toString()+" "+nom.getText()+" "+prenom.getText());
              nom.setText(" ");
              prenom.setText(" ");
              numero.setText(" ");
              //Form_dash_admin fd =new Form_dash_admin();
              //fd. Form_Home_Caissier();
       }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Ajout_personel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Ajout_personel.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void numeroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_numeroKeyReleased
        // TODO add your handling code here:
         String mobile = numero.getText();
        if(mobile.matches("^[0-9]*$") && mobile.length()==8)
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel ldate;
    private textfield.TextField nom;
    private textfield.TextField numero;
    private textfield.PanelBorder panelBorder1;
    private combobox.Combobox poste;
    private textfield.TextField prenom;
    private combobox.Combobox service;
    private javax.swing.JLabel verifie;
    // End of variables declaration//GEN-END:variables
}
