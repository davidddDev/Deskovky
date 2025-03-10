import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JMenuBar;

public class  DeskovkyGUI extends JFrame {
    private JPanel panMain;
    private JTextField tfNazev;
    private JCheckBox cbKoupena;
    private JRadioButton rbOblibenost1;
    private JRadioButton rbOblibenost2;
    private JRadioButton rbOblibenost3;
    private JPanel panOblibenost;
    private JButton btDalsi;
    private JButton btPredchozi;
    private JLabel lbOblibenost;
    private ButtonGroup bgOblibenost;
    private int currentIndex = 0;
    private List<Deskovka> deskovky;
    private JMenuBar menu;

    public DeskovkyGUI(List<Deskovka> deskovky) {
        this.deskovky = deskovky;
        initComponents();
    }

    private void initComponents() {
        panMain = new JPanel();
        setContentPane(panMain);
        setTitle("Deskovky");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        menu = new JMenuBar();
        this.setJMenuBar(menu);

        JMenu souborMenu = new JMenu("Soubor");
        menu.add(souborMenu);

        JMenuItem nactiItem = new JMenuItem("Načti");
        souborMenu.add(nactiItem);

        JMenuItem ulozItem = new JMenuItem("Ulož");
        souborMenu.add(ulozItem);

        JMenu akceMenu = new JMenu("Akce");
        menu.add(akceMenu);

        JMenuItem pridejNovouHruItem = new JMenuItem("Přidej novou hru");
        akceMenu.add(pridejNovouHruItem);

        JMenuItem odeberAktualniHruItem = new JMenuItem("Odeber aktuální hru");
        akceMenu.add(odeberAktualniHruItem);

        JMenuItem seradHryPodleNazvuItem = new JMenuItem("Seřaď hry podle názvu");
        akceMenu.add(seradHryPodleNazvuItem);

        JMenu souhrnMenu = new JMenu("Souhrn");
        menu.add(souhrnMenu);

        JMenuItem zobrazStatistikuItem = new JMenuItem("Zobraz statistiku ");
        souhrnMenu.add(zobrazStatistikuItem);

        zobrazStatistikuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int pocetDeskovychHer = deskovky.size();
                List<String> nazvyNejoblibenejsichHer = new ArrayList<>();
                for (Deskovka deskovka : deskovky) {
                    if (deskovka.getOblibenost() == 3) {
                        nazvyNejoblibenejsichHer.add(deskovka.getNazev());
                    }
                }
                int pocetZakoupenychHer = 0;
                for (Deskovka deskovka : deskovky) {
                    if (deskovka.isJeKoupena()) {
                        pocetZakoupenychHer++;
                    }
                }
                JOptionPane.showMessageDialog(DeskovkyGUI.this,
                        "Počet deskových her v seznamu: " + pocetDeskovychHer + "\n" +
                                "Seznam názvů nejoblíbenějších her: " + nazvyNejoblibenejsichHer + "\n" +
                                "Počet zakoupených her: " + pocetZakoupenychHer);
            }
        });

        panMain.add(tfNazev);
        tfNazev.setEditable(false);

        panMain.add(cbKoupena);


        bgOblibenost = new ButtonGroup();
        bgOblibenost.add(rbOblibenost1);
        bgOblibenost.add(rbOblibenost2);
        bgOblibenost.add(rbOblibenost3);

        panOblibenost = new JPanel();
        panOblibenost.add(lbOblibenost);
        panOblibenost.add(rbOblibenost1);
        panOblibenost.add(rbOblibenost2);
        panOblibenost.add(rbOblibenost3);
        panMain.add(panOblibenost);



        btPredchozi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentIndex--;
                if (currentIndex < 0) {
                    currentIndex = deskovky.size() - 1;
                }
                updateUI();
            }
        });
        panMain.add(btPredchozi);


        btDalsi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentIndex++;
                if (currentIndex >= deskovky.size()) {
                    currentIndex = 0;
                }
                updateUI();
            }
        });
        panMain.add(btDalsi);

        updateUI();

        pack();
    }

    private void updateUI() {
            Deskovka deskovka = deskovky.get(currentIndex);
            tfNazev.setText(deskovka.getNazev());
            cbKoupena.setSelected(deskovka.isJeKoupena());
            rbOblibenost1.setSelected(deskovka.getOblibenost() == 1);
            rbOblibenost2.setSelected(deskovka.getOblibenost() == 2);
            rbOblibenost3.setSelected(deskovka.getOblibenost() == 3);
        }

}
