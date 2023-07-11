import java.time.LocalDate;
import java.util.ArrayList;

public class Medicament {
    private String key;
    private String nom;
    private int nbrDeMed;
    private String dateStr;
    private LocalDate dateDate;
    private int nbrParCycle;
    private int nbrDeCycle;

    // Pour le stock
    public Medicament(String nom, int nbrDeMed, String date) {
        this.nom = nom;
        this.nbrDeMed = nbrDeMed;
        this.dateStr = date;
        this.dateDate = LocalDate.parse(date);

        this.key = nom + " " + dateStr;
    }
    // Pour la liste de commande
    public Medicament(String nom, int nbrParCycle, int nbrDeCycle, String date){
        this.nom = nom;
        this.nbrParCycle = nbrParCycle;
        this.nbrDeCycle = nbrDeCycle;
        this.dateStr = date;
        this.dateDate = LocalDate.parse(date);

        this.nbrDeMed = nbrParCycle * nbrDeCycle;
        this.key = nom;
    }

    public String getKey() {
        return key;
    }

    public LocalDate getDateDate() {
        return dateDate;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNbrDeMed() {
        return nbrDeMed;
    }

    public void addNbrDeMed(int nbrDeMed) {
        this.nbrDeMed += nbrDeMed;
    }

    public int getNbrParCycle() {
        return nbrParCycle;
    }

    public void addNbrParCycle(int nbrParCycle) {
        this.nbrParCycle += nbrParCycle;
    }

    public int getNbrDeCycle() {
        return nbrDeCycle;
    }

    public void addNbrDeCycle(int nbrDeCycle) {
        this.nbrDeCycle += nbrDeCycle;
    }

    @Override
    public String toString() {
        return "Medicament{" +
                "nom='" + nom + '\'' +
                ", nbrDeMed=" + nbrDeMed +
                ", dateStr='" + dateStr + '\'' +
                '}' + "\n";
    }
}