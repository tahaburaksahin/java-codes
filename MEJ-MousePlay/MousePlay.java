// MEJ-MousePlay/MousePlay.java
 
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.event.MouseInputListener;

class MousePlay extends JFrame
              // MouseInput = Mouse + MouseMotion
            implements MouseInputListener {

    private Container cp = getContentPane();
    private int currIndex = 0;  // consecutive characters
    private int diffX =0,
                diffY =0;       // used for dragging
    private boolean isDragged;

      // borders for labels
    Border normal  = BorderFactory
                    .createLineBorder(Color.black),
           pointed = BorderFactory
                    .createLineBorder(Color.red, 2),
           dragged = BorderFactory
                    .createLineBorder(Color.blue, 4);

    public static void main(String[] a) {
        new MousePlay();
    }

    public MousePlay() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        cp.setLayout(null); // no layout!

        cp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isMetaDown()) { // right button
                    if (e.isControlDown())
                        removeAllComponents(); // removing
                    else
                        toggleVisibility();    // hiding
                }
                  // left click adds labels
                else addLabel(e.getX(), e.getY());
            }
        });
        setSize(300, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

      // label where clicked
    private void addLabel(int x, int y) {
        JLabel l = new JLabel("" + (char)('A'+currIndex++));
        l.setBounds(x, y, 50, 50);
        l.setBorder(normal);
        l.setFont(new Font("Dialog", Font.BOLD, 24));
        l.setHorizontalAlignment(JLabel.CENTER);
        l.setVerticalAlignment(JLabel.CENTER);
        l.addMouseListener(this);
        l.addMouseMotionListener(this);
        cp.add(l);
    }

      // toggle visiblity of all subcomponents
    private void toggleVisibility() {
        for (Component comp : cp.getComponents())
            comp.setVisible(!comp.isVisible());
    }

      // removing subcomponents
    private void removeAllComponents() {
        cp.removeAll();
        cp.repaint();
    }


    @Override // change of border (if no dragging)
    public void mouseEntered(MouseEvent e) {
        if (isDragged) return;
        ((JComponent) e.getSource()).setBorder(pointed);
    }
    @Override // restoring border
    public void mouseExited(MouseEvent e) {
        if (isDragged) return;
        ((JComponent) e.getSource()).setBorder(normal);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        isDragged = true; // perhaps start dragging
        ((JComponent) e.getSource()).setBorder(dragged);
        diffX = e.getX();
        diffY = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        isDragged = false;
        if (e.isControlDown()) {// if Ctrl - remove label
            cp.remove(e.getComponent());
            cp.repaint();
            return;
        }
        ((JComponent) e.getSource()).setBorder(pointed);
    }

    @Override // dragging
    public void mouseDragged(MouseEvent e) {
        Component c = e.getComponent();
          // moving the component (label)
        c.setLocation(c.getX() + e.getX() - diffX,
                      c.getY() + e.getY() - diffY);
    }

      // empty
    public void mouseClicked(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {}
}
