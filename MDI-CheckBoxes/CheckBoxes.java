// MDI-CheckBoxes/CheckBoxes.java
 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CheckBoxes {
    static String[] names = {"Michelangelo", "Caravaggio",
                             "Goya",         "Vermeer"};
    static BufferedImage[] images = new BufferedImage[4];
    static int[] mnemos = {KeyEvent.VK_M, KeyEvent.VK_C,
                           KeyEvent.VK_G, KeyEvent.VK_V};
    boolean[] states = {false,false,false,false};
    ImagePanel  imgPanel = new ImagePanel();

    public static void main(String[] args) {
        try {
            for (int i = 0; i < 4; ++i)
                images[i] = ImageIO.read(
                        new File(names[i]+".jpg"));
        } catch (IOException e) {
            System.out.println("Problems with images...");
            return;
        }
        new CheckBoxes();
    }

    private CheckBoxes() {
        JFrame f = new JFrame("Check Boxes");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new ButtonPanel(), BorderLayout.EAST);
        f.add(imgPanel, BorderLayout.CENTER);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    class ButtonPanel extends JPanel {
        private final Dimension rig = new Dimension(0,20);
        ButtonPanel() {
            setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
            ItemListener lis = new MyItemListener();
            for (int i = 0; i < 4; ++i) {
                add(Box.createRigidArea(rig));
                JCheckBox b = new JCheckBox(names[i]);
                b.setMnemonic(mnemos[i]);
                b.setSelected(false);
                b.addItemListener(lis);
                b.putClientProperty("i",Integer.valueOf(i));
                add(b);
            }
            setBorder(BorderFactory.createEmptyBorder(
                                          20,20,20,20));
        }
    }

    class ImagePanel extends JPanel {
        ImagePanel() {
            setPreferredSize(new Dimension(300,500));
            setBackground(new Color(0,0,102));
            setOpaque(true);
        }
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            int panW = getWidth(),
                panH = getHeight();
            Image im = null;
            for (int i = 0; i < 4; ++i) {
                if (!states[i]) continue;
                Image img = images[i];
                int oriW = img.getWidth(null),
                    oriH = img.getHeight(null);
                if (panW*oriH < panH*oriW)
                    im = img.getScaledInstance(panW/2, -1,
                                       Image.SCALE_SMOOTH);
                else
                    im = img.getScaledInstance(-1, panH/2,
                                       Image.SCALE_SMOOTH);
                int imgW = im.getWidth(null);
                int imgH = im.getHeight(null);
                g.drawImage(im,(i%2)*panW/2+(panW/2-imgW)/2,
                               (i/2)*panH/2+(panH/2-imgH)/2,
                            imgW,imgH,null);
            }
        }
    }

    class MyItemListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            JCheckBox source =
                        (JCheckBox)e.getItemSelectable();
            int i = (Integer)source.getClientProperty("i");
            if (e.getStateChange() == ItemEvent.DESELECTED)
                states[i] = false;
            else
                states[i] = true;
            imgPanel.repaint();
        }
    }
}
