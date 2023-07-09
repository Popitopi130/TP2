import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Array;
import java.util.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Command {
    private String txt;

    // Le integer (key) devrait etre le numero qui suit dans "MedicamentXX" et sera utilisé pour ordonner l'arbre
    // Ceci est pour la fonction "date" et "prescription"
    private TreeMap<String, Medicament> commandeMed = new TreeMap<>();

    // Definit lorsque la fonction "date" est appellé
    private String currentDate;

    // Garde en mémoire le stock de médicaments et ordonné avec le numero qui suit dans "MedicamentXX" (integer)
    // Ceci est pour la fonction "approv", "stock" et "prescription"
    private TreeMap<String, Medicament> stockMed = new TreeMap<>();


    public Command(String txt) {
        this.txt = txt;
    }

    public void firstWord(String parts) throws IOException {

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
            prescription(parts);
        } else if (nameLine.startsWith("STOCK")) {
            stock();
        }
    }

    public void writeToTestFile(String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("test1.txt", true))) {
            writer.write(content);
            writer.newLine();
        }
    }

    /*
        Ajouter des médicaments au stock
        Faire attention aux dates d'expiration (même med peut avoir différentes dates)

        *** IL RESTE A ECRIRE SUR LE FICHIER OUTPUT ***
     */
    public void approv(String parts) throws IOException {
        String modifiedInput = parts.replaceFirst("\n", "");
        String[] partitions = modifiedInput.split("\n");



        List<Medicament> medicamentList = new ArrayList<>();
        for (String partition: partitions){
            if (partition.startsWith("APPROV :")) {
                continue;}
            String[] med = partition.split("[ \t]");
            Medicament medicament = new Medicament(med[0], Integer.parseInt(med[1]),med[2]);

            // Nom + date est deja dans stockMed
            if (stockMed.containsKey(medicament.getnom())) {
                stockMed.get(medicament.getKey()).addNbrDeMed(medicament.getNbrDeMed());
                continue;
            }
            stockMed.put(medicament.getKey(), medicament);
        }

        // System.out.println(stockMed.toString());
        System.out.println("approv done");
       writeToTestFile("APPROV OK");


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
    public void prescription(String parts) {
        String modifiedInput = parts.replaceFirst("\n", "");
        String[] partitions = modifiedInput.split("\n");
        List<Medicament> medicamentList = new ArrayList<>();
        for (String partition : partitions) {
            if (partition.startsWith("PRESCRIPTION :")) {
                continue;
            }

            System.out.println("prescription done");

        }
    }








    public void stock() throws IOException {
    // *********rentrer la bonne date
        String currentDate1 = "2017-10-26";
        LocalDate date1 = LocalDate.parse(currentDate1);
        writeToTestFile("\n"+"STOCK "+ currentDate1);

        List<String> medList = new ArrayList<>();
        for (Map.Entry<String, Medicament> entry : stockMed.entrySet()) {
            String key = entry.getKey();
            Medicament medicament = entry.getValue();
            if (LocalDate.parse(medicament.getDateStr()).isAfter(date1)) {
                writeToTestFile(medicament.getnom() +" "+ medicament.getNbrDeMed() +" "+ medicament.getDateStr());
            }
        }
        writeToTestFile("\n");

        System.out.println("stock done");
    }

    public static long calculateDaysBetweenDates(LocalDate startDate, LocalDate endDate) {
        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);

        // Adjust for leap years between 2000 and 2025
        int leapYears = countLeapYears(startDate, endDate);
        daysBetween -= leapYears;

        return daysBetween;
    }

    public static int countLeapYears(LocalDate startDate, LocalDate endDate) {
        int startYear = startDate.getYear();
        int endYear = endDate.getYear();

        int leapYears = 0;
        for (int year = startYear; year <= endYear; year++) {
            if (isLeapYear(year)) {
                leapYears++;
            }
        }

        return leapYears;
    }

    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }









public void writeStuffOn(String txt){
        /*
        Viens du tp1 pour aider

    public static void writeFile(ArrayList<Building> buildings, String outputFile){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
            // Write truck position with location building (index 0)
            writer.write("Truck position: (" +
                    buildings.get(0).getLatitude() + "," + buildings.get(0).getLongitude() + ")");
            writer.write(String.format("\n%-30s%-30s%-30s",
                    "Distance:" + (int) buildings.get(0).getDistance(),
                    "Number of boxes:" + buildings.get(0).getAvailableBoxes(),
                    "Position:(" + buildings.get(0).getLatitude() + "," + buildings.get(0).getLongitude() + ")"));

            //Write other buildings of the list
            int n = buildings.size();
            for (int i = 1; i < n; i++) {
                writer.write(String.format("\n%-30s%-30s%-30s",
                        "Distance:%.1f".formatted(buildings.get(i).getDistance()),
                        "Number of boxes:" + buildings.get(i).getAvailableBoxes(),
                        "Position:(" + buildings.get(i).getLatitude() + "," + buildings.get(i).getLongitude() + ")"));
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
         */
    }
}
