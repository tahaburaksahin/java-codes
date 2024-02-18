// MEG-MovFigure/MoveFig.java
 
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MoveFig extends JFrame {

    public static void main(String[] args) {
        new MoveFig();
    }

    public MoveFig() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(new Figure());
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

class Figure extends JPanel {

    private final int step = 4, maxR = 150, minR = 10;
    private int radius = 10, xcoor = 10, ycoor = 10;
    private int width,height;
    private Color kolor = Color.red;
    private boolean blk = false, cir = true, fil = true;

    Figure() {
        setBackground(Color.black);
        setPreferredSize(new Dimension(400,400));

        addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                if ( e.isMetaDown() ) {
                    blk = true;
                } else {
                    xcoor  = e.getX();
                    ycoor  = e.getY();
                    blk = false;
                }
                repaint();
            }
        });

        addKeyListener(new KeyAdapter()
        {
            public void keyPressed(KeyEvent e)
            {
                switch ( e.getKeyCode() ) {
                    case KeyEvent.VK_UP :
                        ycoor = (height+ycoor-step)%height;
                        break;
                    case KeyEvent.VK_DOWN :
                        ycoor = (ycoor+step)%height;
                        break;
                    case KeyEvent.VK_LEFT :
                        xcoor = (width+xcoor-step)%width;
                        break;
                    case KeyEvent.VK_RIGHT :
                        xcoor = (xcoor+step)%width;
                        break;
                    case KeyEvent.VK_ADD :
                        radius = Math.min(maxR,radius+step);
                        break;
                    case KeyEvent.VK_SUBTRACT :
                        radius = Math.max(minR,radius-step);
                        break;
                    case KeyEvent.VK_ENTER :
                        cir = !cir;
                        break;
                    case KeyEvent.VK_R :
                        kolor = Color.red;
                        break;
                    case KeyEvent.VK_G :
                        kolor = Color.green;
                        break;
                    case KeyEvent.VK_B :
                        kolor = Color.blue;
                        break;
                    case KeyEvent.VK_Y :
                        kolor = Color.yellow;
                        break;
                    case KeyEvent.VK_SPACE :
                        fil = !fil;
                        break;
                    default:
                        return;
                }
                repaint();
            }
        });
    }

    public void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(
            RenderingHints.KEY_TEXT_ANTIALIASING,
            RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        super.paintComponent(g);
        requestFocus();

        int x, y, b;

        width  = getWidth();
        height = getHeight();

        if ( !blk ) {
            g.setColor(kolor);
            x = xcoor - radius;
            y = ycoor - radius;
            b = 2*radius;
            if ( cir ) {
                if ( fil ) { g.fillOval(x,y,b,  b);   }
                else       { g.drawOval(x,y,b-1,b-1); }
            } else {
                if ( fil ) { g.fillRect(x,y,b,  b  ); }
                else       { g.drawRect(x,y,b-1,b-1); }
            }
        }
    }
}
