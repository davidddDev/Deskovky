import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EvidenceDeskovek {
    private List<Deskovka> deskovky = new ArrayList<>();

    public void pridejDeskovku(Deskovka deskovka) {
        deskovky.add(deskovka);
    }

    public List<Deskovka> getDeskovky() {
        return new ArrayList<>(deskovky);
    }

    public void vypisDeskovkyZeSouboru(String nazevSouboru, String oddelovac) throws DeskovkyException {
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(nazevSouboru)))) {
            while (scanner.hasNextLine()) {
                String radek = scanner.nextLine();
                pridejDeskovku(parseDeskovka(radek, oddelovac));
            }
        } catch (Exception e) {
            throw new DeskovkyException("Chyba při načítání deskovek ze souboru.");
        }
        for (Deskovka deskovka : deskovky) {
            System.out.println(deskovka.getNazev() + " - " + deskovka.isJeKoupena() + " - " + deskovka.getOblibenost());
        }
    }

    private Deskovka parseDeskovka(String radek, String oddelovac) throws DeskovkyException{
            String[] deskovka = radek.split(oddelovac);
            if (deskovka.length != 3) {
                throw new DeskovkyException("Chyba při načítání deskovek ze souboru.");
            }
            String nazev = deskovka[0];
            boolean jeKoupena = deskovka[1].equalsIgnoreCase("true");
            int oblibenost = Integer.parseInt(deskovka[2]);
            return new Deskovka(nazev, jeKoupena, oblibenost);
        }

    }

