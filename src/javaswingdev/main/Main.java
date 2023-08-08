package javaswingdev.main;

import java.awt.Component;
import java.awt.Desktop;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javaswingdev.form.Form_Dashboard;

import javaswingdev.form.Form_Empty;
import javaswingdev.form.Ajout_personel;
import javaswingdev.form.Form_Dashboard_rapport_caisse;
import javaswingdev.form.Form_compte;
import javaswingdev.form.Form_update_compte;
import javaswingdev.form.Form_service;
import javaswingdev.form.Mon_compte;
import javaswingdev.menu.EventMenuSelected;
import javaswingdev.message.MessageDialog;
import javax.swing.JOptionPane;
import raven.glasspanepopup.GlassPanePopup;
import sample.message.Message;

public class Main extends javax.swing.JFrame {
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
    public static Main main;
    
    public Main() {
        initComponents();
        init();
         GlassPanePopup.install(this);
    }
    
    private void init() {
        main = this;
        titleBar.initJFram(this);
        menu.addEvent(new EventMenuSelected() {
            @Override
            public void menuSelected(int index, int indexSubMenu) {
                if (index == 0) {
                    //showForm(new Form_Dashboard());
                    affiche(new Form_Dashboard());
                }else if (index == 1) {
                    //showForm(new Form_Dashboard_rapport_caisse());
                    affiche(new Form_Dashboard_rapport_caisse());
                }else if (index == 2) {
                    openPDFByFullName_rapport_personnelle();
                }else if (index == 3) {
                    //showForm(new Ajout_personel());
                     affiche(new Ajout_personel());
                } else if ((index == 4)&&(indexSubMenu == 1)) {
                   // showForm(new Form_update_compte());
                     affiche(new Form_update_compte());
                }else if ((index == 4)&&(indexSubMenu == 2)) {
                   // showForm(new Form_compte());
                     affiche(new Form_compte());
                } else if (index == 5) {
                   // showForm(new Form_service());
                     affiche(new Form_service());
                }else if (index == 6) {
                     affiche(new Mon_compte());
                  //  showForm(new Mon_compte());
                }else {
                      MessageDialog obj = new MessageDialog(Main.this);
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
        menu.setSelectedIndex(0, 0);
    }
     public void openPDFByFullName_rapport_personnelle() {
          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String currentTime = LocalDateTime.now().format(formatter);
    String printRecuFolderPath = "../Print_Rapport_Personnelle/";
    String fileNamePrefix = "Rapport_du_personnelle_"+currentTime;

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
     
    
    public void showForm(Component com) {
        body.removeAll();
        body.add(com);
        body.repaint();
        body.revalidate();
    }
    
    public static Main getMain() {
        return main;
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
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background = new javax.swing.JPanel();
        panelMenu = new javax.swing.JPanel();
        titleBar = new javaswingdev.swing.titlebar.TitleBar();
        menu = new javaswingdev.menu.Menu();
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
            .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelMenuLayout.setVerticalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMenuLayout.createSequentialGroup()
                .addComponent(titleBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 677, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel background;
    private javax.swing.JPanel body;
    private javaswingdev.menu.Menu menu;
    private javax.swing.JPanel panelMenu;
    private javaswingdev.swing.titlebar.TitleBar titleBar;
    // End of variables declaration//GEN-END:variables
}
