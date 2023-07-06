import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class Command {
    private String txt;

    // Le integer (key) devrait etre le numero qui suit dans "MedicamentXX" et sera utilisé pour ordonner l'arbre
    // Ceci est pour la fonction "date" et "prescription"
    private TreeMap<Integer, Medicament> commandeMed = new TreeMap<>();

    // Definit lorsque la fonction "date" est appellé
    private String currentDate;

    // Garde en mémoire le stock de médicaments et ordonné avec le numero qui suit dans "MedicamentXX" (integer)
    // Ceci est pour la fonction "approv", "stock" et "prescription"
    private TreeMap<Integer, Medicament> stockMed = new TreeMap<>();


    public Command(String txt) {
        this.txt = txt;
    }

    public void firstWord(String parts) {
        String[] lines = parts.split("\\n");
        String nameLine;
        if (lines[0].trim().equals("")) {
            nameLine = lines[1].trim();
        } else {
            nameLine = lines[0].trim();
        }

        if (nameLine.startsWith("APPROV :")) {
           approv(parts);
        } else if (nameLine.startsWith("DATE")) {
            date();
        } else if (nameLine.startsWith("PRESCRIPTION")) {
            prescription();
        } else if (nameLine.startsWith("STOCK")) {
            stock();
        }
    }


    /*
        Ajouter des médicaments au stock
        Faire attention aux dates d'expiration (même med peut avoir différentes dates)
     */
    public void approv(String parts){
        String modifiedInput = parts.replaceFirst("\n", "");
        String[] partitions = modifiedInput.split("\n");

        System.out.println(Arrays.toString(partitions));

        List<Medicament> medicamentList = new ArrayList<>();
        for (String partition: partitions){
            if (partition.startsWith("APPROV :")) {
                continue;}
            String med = partition.replaceFirst("Medicament","") ;
            String[] medi = med.split(" ");
            //Medicament medicament = new Medicament(medi[2],Integer.parseInt(medi[1]), Integer.parseInt(medi[0]));
            //medicamentList.add(medicament);
            //MedicamentTree medicamentTree = new MedicamentTree();
        }
        System.out.println("approv done");
    }

    /*
        1. Affiche la date
        2. Si commandeMed contient des medicaments:
            - Print les medicaments avec date
            Ex: 2018-01-01 COMMANDES :
                Medicament1 62
                Medicament2 40
                Medicament3 7
                Medicament8 4
                Medicament9 180
            - Vide l'arbre
        3. Si commandeMed est vide:
            - Print currentDate + OK
            Ex: 2013-08-27 OK
     */
    public void date(){
        System.out.println("date done");
    }

    /*
        1. Checker si med est en stock (si en manque, mettre dans commandeMed)
        2. Checker si med ne va pas expiré avant le traitement (attention année bissextile)
        3. Retirer du stock (si =0, alors supprimer med de stock)
     */
    public void prescription(){
        System.out.println("prescription done");
    }

    /*
        Fait juste écrire sur le fichier output le stock
        Vérifier si les meds n'ont pas expirés!
     */
    public void stock(){
        System.out.println("stock done");
    }
}
