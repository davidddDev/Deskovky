import java.util.List;

public class Main {
    private static final String nazevSouboru = "deskovky.txt";
    private static final String oddelovac = "#";

    public static void main(String[] args) {
        EvidenceDeskovek evidenceDeskovek = new EvidenceDeskovek();
        try {
            evidenceDeskovek.vypisDeskovkyZeSouboru(nazevSouboru, oddelovac);
        } catch (DeskovkyException e) {
            System.out.println(e.getMessage());
        }

        List<Deskovka> deskovky = evidenceDeskovek.getDeskovky();
        DeskovkyGUI deskovkyGUI = new DeskovkyGUI(deskovky);
        deskovkyGUI.setVisible(true);
    }
}