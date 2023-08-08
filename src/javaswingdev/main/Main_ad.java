package javaswingdev.main;

import java.awt.Component;
import java.awt.Desktop;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Normalizer.Form;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javaswingdev.form.Form_Dashboard;

import javaswingdev.form.Form_Empty;
import javaswingdev.form.Ajout_personel;
import javaswingdev.form.Form_Dashboard_Sec;
import javaswingdev.form.Form_Dashboard_ad;
import javaswingdev.form.Form_consultation;
import javaswingdev.form.Form_exam;
import javaswingdev.form.Form_liste_consultation;
import javaswingdev.form.Form_liste_operation;
import javaswingdev.form.Mon_compte;
import javaswingdev.form.Mon_compte1;
import javaswingdev.form.Mon_compte_user;
import javaswingdev.menu.EventMenuSelected;
import javaswingdev.message.MessageDialog;
import javax.swing.JOptionPane;
import raven.glasspanepopup.GlassPanePopup;
import sample.message.Message;

public class Main_ad extends javax.swing.JFrame {
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
    public static Main_ad main;
    
    public Main_ad() {
        initComponents();
        init();
          GlassPanePopup.install(this);
    }
    
   
    private void init() {
        main = this;
        titleBar.initJFram(this);
        menu_ad1.addEvent(new EventMenuSelected() {
            @Override
            public void menuSelected(int index, int indexSubMenu) {
                if (index == 0) {
                    affiche(new Form_Dashboard_ad());
                }else if ((index == 1)) {
                     affiche(new Form_liste_consultation());
                
                } else if ((index == 2)&&(indexSubMenu == 1)) {
                     affiche(new Form_consultation());
                   
                } else if ((index == 2)&&(indexSubMenu == 2)) {
                     affiche(new Form_exam());
                   
                }else if ((index == 3)&&(indexSubMenu == 1)) {
                     
                    openPDFByFullName_rapport_acte_today();
                }else if ((index == 3)&&(indexSubMenu == 2)) {
                     //affiche(new Form_Dashboard_ad());
                    openPDFByFullName_rapport_all_acte();
                }else if (index == 4) {
                   
                   
                    inf_perso();
                 //   showForm(new Mon_compte_user());
                }else {
                      MessageDialog obj = new MessageDialog(Main_ad.this);
        obj.showMessage("Avertissement", "Voulez vous déconnecter\nToutes les opérations non sauvegardé seront éffacées.");
        if (obj.getMessageType() == MessageDialog.MessageType.OK) {
            //System.out.println("User click ok");
               login lg = new login();
               setVisible(false);
               lg.setVisible(true);
        } else {
           // System.out.println("User click cancel");
           obj.setVisible(false);
        }
                } 
            }
        });
        menu_ad1.setSelectedIndex(0, 0);
    }
    
    public void showForm(Component com) {
        body.removeAll();
        body.add(com);
        body.repaint();
        body.revalidate();
    }
    
    public static Main_ad getMain() {
        return main;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background = new javax.swing.JPanel();
        panelMenu = new javax.swing.JPanel();
        titleBar = new javaswingdev.swing.titlebar.TitleBar();
        menu_ad1 = new javaswingdev.menu.Menu_ad();
        body = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        background.setBackground(new java.awt.Color(164, 194, 201));

        panelMenu.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelMenuLayout = new javax.swing.GroupLayout(panelMenu);
        panelMenu.setLayout(panelMenuLayout);
        panelMenuLayout.setHorizontalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addComponent(titleBar, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(menu_ad1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelMenuLayout.setVerticalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMenuLayout.createSequentialGroup()
                .addComponent(titleBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menu_ad1, javax.swing.GroupLayout.PREFERRED_SIZE, 669, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        body.setBackground(new java.awt.Color(25, 110, 129));
        body.setForeground(new java.awt.Color(25, 110, 129));
        body.setOpaque(false);
        body.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addComponent(panelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, 1086, Short.MAX_VALUE)
                .addContainerGap())
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

     public void openPDFByFullName_rapport_all_acte() {
    String printRecuFolderPath = "../Print_Rapport_Acte/";
    String fileNamePrefix = "Rapport_tous_Actes";

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
        // Si aucun fichier PDF correspondant n'a été trouvé, afficher le message d'erreur.
        JOptionPane.showMessageDialog(null, "Erreur aucun rapport trouvé");
    } else {
        // La liste des fichiers est null, donc aucun fichier dans le répertoire.
        JOptionPane.showMessageDialog(null, "Erreur aucun fichier dans le répertoire");
    }
} else {
    // Le répertoire spécifié n'existe pas.
    JOptionPane.showMessageDialog(null, "Erreur le répertoire n'existe pas");
}
    } catch (IOException e) {
        System.out.println("Erreur lors de l'ouverture du fichier PDF : " + e);
    }

    // Aucun fichier PDF correspondant trouvé
    
}
      public void openPDFByFullName_rapport_acte_today() {
          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String currentTime = LocalDateTime.now().format(formatter);
    String printRecuFolderPath = "../Print_Rapport_Acte/";
    String fileNamePrefix = "Rapport_Acte_"+currentTime;

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
        // Si aucun fichier PDF correspondant n'a été trouvé, afficher le message d'erreur.
        JOptionPane.showMessageDialog(null, "Erreur aucun rapport trouvé");
    } else {
        // La liste des fichiers est null, donc aucun fichier dans le répertoire.
        JOptionPane.showMessageDialog(null, "Erreur aucun fichier dans le répertoire");
    }
} else {
    // Le répertoire spécifié n'existe pas.
    JOptionPane.showMessageDialog(null, "Erreur le répertoire n'existe pas");
}
    } catch (IOException e) {
        System.out.println("Erreur lors de l'ouverture du fichier PDF : " + e);
    }

    // Aucun fichier PDF correspondant trouvé
    
}
       public void inf_perso()
    {
      String passeword =  login.txtid.getText();
      
      String verifie = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
                sqlConn = DriverManager.getConnection(dataConn,username,password);
                
                pst_search = sqlConn.prepareCall("Select * from users,role,service where identifiant=? and Role_id=rle_id and Service_id=id");
                pst_search.setString(1, passeword);

                rs = pst_search.executeQuery();
                if(rs.next())
                {
                    verifie = rs.getString("inf_perso");
                }
                if(verifie.equals("defaut"))
                {
                     MessageDialog obj = new MessageDialog(Main_ad.this);
        obj.showMessage("Avertissement", "Veuillez initialier vos informations personnelle\nEn cas de perte de vos donnée.");
        if (obj.getMessageType() == MessageDialog.MessageType.OK) {
            //System.out.println("User click ok");
               String inf = JOptionPane.showInputDialog("Veuillez initialiser votre information personnelle\n "
                            + "en cas de perte de vos information");
                    while(inf.equals(" ")|| inf.equals(""))
                    {
                        JOptionPane.showMessageDialog(null,"Information Invalide", "Erreur",JOptionPane.ERROR_MESSAGE);
                        inf = JOptionPane.showInputDialog("Veuillez initialiser votre information personnelle\n "
                            + "en cas de perte de vos information");
                        if(!inf.equals("") || !inf.equals(" "))
                        {
                            break;
                        }
                    }
                    try {
                                 Class.forName("com.mysql.jdbc.Driver");
                sqlConn = DriverManager.getConnection(dataConn,username,password);
                
                pst_search = sqlConn.prepareCall("Select * from users where identifiant = ?");
                pst_search.setString(1, passeword);
                rs = pst_search.executeQuery();
                if(rs.next())
                {
                    pst = sqlConn.prepareStatement("Update users set inf_perso = ?  where idUser = ? ");
                    pst.setString(1, inf);
                    pst.setString(2, rs.getString("idUser"));
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Information personelle mise à jour");
                    affiche(new Mon_compte_user());
                   
                }
            } catch (ClassNotFoundException | SQLException e) {
            }
                    
                    
        } else {
           // System.out.println("User click cancel");
           obj.setVisible(false);
        }
                    
                }
                else 
                {
                     showForm(new Mon_compte_user());
                }
  
        } catch (HeadlessException | ClassNotFoundException | SQLException e) {
        }
        
    }
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
            java.util.logging.Logger.getLogger(Main_ad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main_ad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main_ad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main_ad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main_ad().setVisible(true);
            }
        });
        
    }
    public void affiche(Component form)
    {
      String passeword =  login.txtid.getText();
String passeword2 =  login.txtmdp.getText();
      try {
            Class.forName("com.mysql.jdbc.Driver");
                sqlConn = DriverManager.getConnection(dataConn,username,password);
                
                pst_search = sqlConn.prepareCall("Select * from users where identifiant = ? && mdp = ?");
                pst_search.setString(1, passeword);
                pst_search.setString(2, passeword2);
                rs = pst_search.executeQuery();
                if(!rs.next())
                {
                  Message obj = new Message();
                        obj.message.setText("Une modification a été faite sur vos information \n"
                          + "Veuillez recharger votre compte");
        obj.eventOK(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                login lg = new login();
                    setVisible(false);
                    lg.setVisible(true);
               // System.out.println("Click OK");
                GlassPanePopup.closePopupLast();
            }
        });
        GlassPanePopup.showPopup(obj);
                }
                else
                {
                    showForm(form);
                }
                
            
        } catch (Exception e) {
        }
       
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel background;
    private javax.swing.JPanel body;
    private javaswingdev.menu.Menu_ad menu_ad1;
    private javax.swing.JPanel panelMenu;
    private javaswingdev.swing.titlebar.TitleBar titleBar;
    // End of variables declaration//GEN-END:variables
}
