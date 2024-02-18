// MEH-ItemSel/ItemSel.java
 
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.JComboBox;
import javax.swing.JFrame;

public class ItemSel extends JFrame
                     implements ItemListener {

    JComboBox<String> cbCountry;
    JComboBox<String> cbTown;
    HashMap<String, Set<String>> countryTowns;

    public static void main(String args[]) {
        new ItemSel();
    }

    public ItemSel() {
        String[] countries = {"Poland", "UK", "Spain"};
        String[][]   towns =
            {
                {"Warszawa", "Kielce", "Szczecin"},
                {"London", "York"},
                {"Madrid", "Barcelona"}
            };
        cbCountry = new JComboBox<>(countries);
        cbTown    = new JComboBox<>(towns[0]);
        countryTowns = new HashMap<String, Set<String>>();
        for (int i=0; i < countries.length; i++) {
            String key = countries[i];
            TreeSet<String> tSet = new TreeSet<String>();
            for (int j =0; j < towns[i].length; j++) {
                tSet.add(towns[i][j]);
            }
            countryTowns.put(key, tSet);
        }

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());
        cbCountry.addItemListener(this);
        add(cbCountry);
        add(cbTown);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            String country = (String) e.getItem();
            Set<String> towns =  countryTowns.get(country);
            cbTown.removeAllItems();
            for(String t : towns) cbTown.addItem(t);
        }
    }
}
