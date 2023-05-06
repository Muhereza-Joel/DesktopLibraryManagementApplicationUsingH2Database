/*
 * Created by JFormDesigner on Mon Nov 21 13:18:47 EAT 2022
 */

package mmu.lms.frames;

import com.formdev.flatlaf.FlatIntelliJLaf;

import java.awt.*;
import javax.swing.*;

/**
 * @author Muhereza Joel
 */
public class SplashScreen extends JWindow{
    public SplashScreen() {
        initComponents();
        ImageIcon icon = new ImageIcon("images/mmu logo.PNG");
        setIconImage(icon.getImage());
        setSize(420, 420);
        setLocationRelativeTo(this);
        setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        panel1 = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        label4 = new JLabel();
        label3 = new JLabel();

        //======== this ========
        setBackground(new Color(0x2675bf));
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== panel1 ========
        {
            panel1.setBackground(new Color(0x2675bf));
            panel1.setLayout(null);

            //---- label1 ----
            label1.setFont(new Font("Segoe UI", Font.PLAIN, 36));
            label1.setIcon(new ImageIcon("C:\\Users\\STELLA\\Desktop\\Projects\\EmbededDB\\images\\mmulogosmall.png"));
            label1.setMaximumSize(new Dimension(100, 100));
            label1.setMinimumSize(new Dimension(100, 100));
            label1.setBackground(UIManager.getColor("TabbedPane.focus"));
            label1.setOpaque(true);
            label1.setHorizontalAlignment(SwingConstants.CENTER);
            panel1.add(label1);
            label1.setBounds(25, 15, 55, 50);

            //---- label2 ----
            label2.setText("MMU LMS");
            label2.setFont(new Font("Segoe UI", Font.PLAIN, 24));
            label2.setForeground(Color.white);
            panel1.add(label2);
            label2.setBounds(new Rectangle(new Point(25, 70), label2.getPreferredSize()));

            //---- label4 ----
            label4.setText("V 1.5");
            label4.setForeground(Color.white);
            panel1.add(label4);
            label4.setBounds(new Rectangle(new Point(30, 105), label4.getPreferredSize()));

            //---- label3 ----
            label3.setText("Loading.......");
            label3.setForeground(Color.white);
            label3.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            panel1.add(label3);
            label3.setBounds(325, 370, 90, label3.getPreferredSize().height);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel1.getComponentCount(); i++) {
                    Rectangle bounds = panel1.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel1.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel1.setMinimumSize(preferredSize);
                panel1.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(panel1);
        panel1.setBounds(0, 0, 435, 420);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel panel1;
    private JLabel label1;
    private JLabel label2;
    private JLabel label4;
    private JLabel label3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
