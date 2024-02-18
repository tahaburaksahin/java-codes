// MEI-Windows/Windows.java
 
import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Windows extends JFrame {

      // state of the window -> descriptions
    HashMap<Integer,String> wstat =
                        new HashMap<Integer,String>();
      // listener of window state changes
    WindowStateListener wsl = new  WindowStateListener() {
        { // initializing block
          // NORMAL, ICONIFIED... are int constants in Frame
            wstat.put(NORMAL, "Normal");
            wstat.put(ICONIFIED, "Minimized");
            wstat.put(MAXIMIZED_VERT, "Max. vertically");
            wstat.put(MAXIMIZED_HORIZ,"Max. horizontally");
            wstat.put(MAXIMIZED_BOTH,"Maximized");
        }
        @Override
        public void windowStateChanged(WindowEvent e) {
            String old = wstat.get(e.getOldState());
            String now =  wstat.get(e.getNewState());
            System.out.println(
                        "CHANGE: " + old + " -> " + now);
        }
    };

    public static void main(String args[]) {
        new Windows();
    }

    public Windows() {
        addWindowStateListener(wsl);

        add(new JLabel("Buttons below change " +
                       "the state of the window..."));

        JPanel p = new JPanel();
        for (int k : wstat.keySet()) {
            String opis = wstat.get(k);
            JButton b = new JButton(opis);
            b.setActionCommand(""+k);
            b.addActionListener(e -> {
                setExtendedState(
                    Integer.parseInt(e.getActionCommand()));
            });
            p.add(b);
        }
        add(p, BorderLayout.SOUTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
