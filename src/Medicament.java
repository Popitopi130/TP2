import java.util.ArrayList;

public class Medicament {
    private String key;
    private String nom;
    private int nbrDeMed;
    private String dateStr;
    private int annee;
    private int mois;
    private int jour;
    private int nbrParCycle;
    private int nbrDeCycle;

    // Pour le stock
    public Medicament(String nom, int nbrDeMed, String date) {
        this.nom = nom;
        this.nbrDeMed = nbrDeMed;
        this.dateStr = date;

        String[] content = dateStr.split("-");
        this.annee = Integer.parseInt(content[0]);
        this.mois = Integer.parseInt(content[1]);
        this.jour = Integer.parseInt(content[2]);

        this.key = nom + " " + dateStr;
    }
    // Pour la liste de commande
    public Medicament(String nom, int nbrParCycle, int nbrDeCycle){
        this.nom = nom;
        this.nbrDeMed = nbrParCycle * nbrDeCycle;
        this.nbrParCycle = nbrParCycle;
        this.nbrDeCycle = nbrDeCycle;
    }

    public String getKey() {
        return key;
    }


    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public int getMois() {
        return mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    public int getJour() {
        return jour;
    }

    public void setJour(int jour) {
        this.jour = jour;
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

    public void setNbrParCycle(int nbrParCycle) {
        this.nbrParCycle = nbrParCycle;
    }

    public int getNbrDeCycle() {
        return nbrDeCycle;
    }

    public void setNbrDeCycle(int nbrDeCycle) {
        this.nbrDeCycle = nbrDeCycle;
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