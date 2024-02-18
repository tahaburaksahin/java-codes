// MED-DrawBetter/DrawBetter.java
 
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawBetter extends JFrame {

    public static void main(String[] args) {
        new DrawBetter();
    }

    DrawBetter() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        MyPanelBetter panel = new MyPanelBetter();
          // panel will listen to mouse and keys
        panel.addMouseListener(panel);
        panel.addMouseMotionListener(panel);
        panel.addKeyListener(panel);

        panel.setBackground(Color.BLACK);
        panel.setOpaque(true);
        panel.setPreferredSize(new Dimension(300,200));
        add(panel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

class MyPanelBetter extends JPanel
        implements MouseListener,
                   MouseMotionListener,
                   KeyListener {
    int xOLD, yOLD;
    Graphics  pg;
    Color fore = Color.YELLOW;
    ArrayList<Segment> segs = new ArrayList<Segment>();

      // MouseListener implementation
    public void mousePressed(MouseEvent e) {
        xOLD = e.getX();
        yOLD = e.getY();
        pg.setColor(fore);
    }
    public void mouseReleased(MouseEvent ignore) { }
    public void mouseClicked(MouseEvent  ignore) { }
    public void mouseEntered(MouseEvent  ignore) { }
    public void mouseExited(MouseEvent   ignore) { }

      // MouseMotionListener implementation
    public void mouseDragged(MouseEvent e)  {
        int x = e.getX(), y = e.getY();
        pg.drawLine(xOLD,yOLD,x,y);
        segs.add(new Segment(fore,x,y,xOLD,yOLD));
        xOLD = x;
        yOLD = y;
    }
    public void mouseMoved(MouseEvent ignore)  { }

      // KeyListener implementation
    public void keyPressed(KeyEvent e)  {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_R: fore = Color.RED;    break;
            case KeyEvent.VK_G: fore = Color.GREEN;  break;
            case KeyEvent.VK_B: fore = Color.BLUE;   break;
            case KeyEvent.VK_Y: fore = Color.YELLOW; break;
        }
    }
    public void keyReleased(KeyEvent ignore) { }
    public void keyTyped(KeyEvent    ignore) { }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (pg != null) pg.dispose();
        pg = getGraphics();
        for (Segment s : segs)
            s.drawYourself(g);
        requestFocus();     // so panel listens to keys...
    }
}

class Segment {
    private Color color;
    private int x,y,xOLD,yOLD;

    Segment(Color c, int xn, int yn, int xo, int yo) {
        color = c;
        x     = xn;
        y     = yn;
        xOLD  = xo;
        yOLD  = yo;
    }
    void drawYourself(Graphics g) {
        g.setColor(color);
        g.drawLine(xOLD,yOLD,x,y);
    }
}
