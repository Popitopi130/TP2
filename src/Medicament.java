public class Medicament {
    private String nom;
    private int nbrDeMed;
    private String date;
    private int nbrParCycle;
    private int nbrDeCycle;

    // Pour le stock
    public Medicament(String nom, int nbrDeMed, String date) {
        this.nom = nom;
        this.nbrDeMed = nbrDeMed;
        this.date = date;
    }
    // Pour la liste de commande
    public Medicament(String nom, int nbrParCycle, int nbrDeCycle){
        this.nom = nom;
        this.nbrDeMed = nbrParCycle * nbrDeCycle;
        this.nbrParCycle = nbrParCycle;
        this.nbrDeCycle = nbrDeCycle;
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

    public void setNbrDeMed(int nbrDeMed) {
        this.nbrDeMed = nbrDeMed;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
}