/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sample.message;

/**
 *
 * @author Trafiquant de Tigbé
 */
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;

public class CustomPopup extends JDialog implements ActionListener {
    private JPanel contentPane;
    private int targetHeight;

    public CustomPopup(JPanel panel) {
        setUndecorated(true);
        setContentPane(panel);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        targetHeight = panel.getPreferredSize().height;
        setBounds(getX(), getY(), getWidth(), 0);
        setOpacity(0.0f);
        setVisible(true);

        animatePopup();
    }

    private void animatePopup() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (getHeight() < targetHeight) {
                        int newHeight = getHeight() + 5;
                        setBounds(getX(), getY(), getWidth(), newHeight);
                        setOpacity(newHeight / (float) targetHeight);
                        Thread.sleep(10);
                    }
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dispose();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(new Color(0, 0, 0, 100));
        g2d.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
        g2d.dispose();
    }

    public static void showPopup(JPanel panel) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CustomPopup(panel);
            }
        });
    }
}


