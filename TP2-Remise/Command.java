import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Array;
import java.time.format.DateTimeFormatter;
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
    private int prescription = 1;


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
            date(parts);
        } else if (nameLine.startsWith("PRESCRIPTION")) {
            prescription(parts);
        } else if (nameLine.startsWith("STOCK")) {
            stock();
        }
    }

    /*
        Ajouter des médicaments au stock
        Faire attention aux dates d'expiration (même med peut avoir différentes dates)
     */

    public void approv(String parts) {
        String modifiedInput = parts.replaceFirst("\n", "");
        String[] partitions = modifiedInput.split("\n");

        for (String partition: partitions){
            if (partition.startsWith("APPROV :")) {
                continue;}
            String[] med = partition.split("[ \t]");
            Medicament medicament = new Medicament(med[0], Integer.parseInt(med[1]),med[2]);

            // Nom + date est deja dans stockMed
            if (stockMed.containsKey(medicament.getKey())) {
                stockMed.get(medicament.getKey()).addNbrDeMed(medicament.getNbrDeMed());
                continue;
            }
            stockMed.put(medicament.getKey(), medicament);
        }
        writeToOutputFile("APPROV OK\n");
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
        \n\n a la fin
     */

    public void date(String parts){
        String modifiedInput = parts.replace("\n", "");
        String[] partitions = modifiedInput.split(" +");

        currentDate = partitions[1];

        if (commandeMed.isEmpty()) {
            writeToOutputFile(currentDate + " OK\n\n");
        } else {
            writeToOutputFile(currentDate + " COMMANDES :\n");
            for (Map.Entry<String, Medicament> entry : commandeMed.entrySet()) {
                Medicament entryValue = entry.getValue();

                writeToOutputFile(entryValue.getNom() + " " + entryValue.getNbrDeMed() + "\n");
            }
            commandeMed.clear();
            writeToOutputFile("\n");
        }

        ArrayList<Medicament> toRemove = new ArrayList<>();
        for (Map.Entry<String, Medicament> entry : stockMed.entrySet()) {
            Medicament medicament = entry.getValue();
            if (medicament.getDateDate().isBefore(LocalDate.parse(currentDate))) {
                toRemove.add(medicament);
            }
        }
        toRemove.forEach(medicament -> stockMed.remove(medicament.getKey()));
    }


    /*


        1. Checker si med est en stock (si en manque, mettre dans commandeMed)
        2. Checker si med ne va pas expiré avant le traitement (attention année bissextile)
        3. Retirer du stock (si =0, alors supprimer med de stock)
     */

    public void prescription(String parts) {
        String modifiedInput = parts.replaceFirst("\n", "");
        String[] partitions = modifiedInput.split("\n");

        writeToOutputFile("PRESCRIPTION " + prescription + "\n");
        prescription++;

        StringBuilder outputText = new StringBuilder();

        for (String partition : partitions) {
            boolean inStock = false;
            boolean inCommand = false;
            Medicament theMedInStock = new Medicament("none", 0,0,currentDate);
            Medicament theMedInCommand = new Medicament("none", 0,0,currentDate);
            if (partition.startsWith("PRESCRIPTION")) {
                continue;
            }

            String[] med = partition.split("\\s+");
            Medicament prescription = new Medicament(med[0], Integer.parseInt(med[1]), Integer.parseInt(med[2]), currentDate);

            for (Map.Entry<String, Medicament> entry : stockMed.entrySet()) {
                if (prescription.getNom().equals(entry.getValue().getNom()) &&
                        prescription.getNbrDeMed() <= entry.getValue().getNbrDeMed()) {
                    inStock = true;
                    theMedInStock = entry.getValue();
                    break;
                }
            }

            for (Map.Entry<String, Medicament> entry : commandeMed.entrySet()) {
                if (entry.getValue().getNom().equals(prescription.getNom())) {
                    inCommand = true;
                    theMedInCommand = entry.getValue();
                    break;
                }
            }

            if (inStock &&
                    prescription.getNbrDeCycle() <= calculateDaysBetweenDates(currentDate, theMedInStock.getDateStr())) {
                theMedInStock.addNbrDeMed(-prescription.getNbrDeMed());
                if (theMedInStock.getNbrDeMed() <= 0) {
                    stockMed.remove(theMedInStock.getKey());
                }
                outputText.append(prescription.getNom()).append(" ").append(prescription.getNbrParCycle()).append(" ").append(prescription.getNbrDeCycle()).append("  OK").append("\n");
            } else if (inCommand) {
                theMedInCommand.addNbrDeMed(prescription.getNbrDeMed());
                outputText.append(prescription.getNom()).append(" ").append(prescription.getNbrParCycle()).append(" ").append(prescription.getNbrDeCycle()).append("  COMMANDE").append("\n");
            } else {
                commandeMed.put(prescription.getKey(), prescription);
                outputText.append(prescription.getNom()).append(" ").append(prescription.getNbrParCycle()).append(" ").append(prescription.getNbrDeCycle()).append("  COMMANDE").append("\n");
            }
        }
        writeToOutputFile(outputText + "\n");
    }



    public void stock() {
        LocalDate date1 = LocalDate.parse(currentDate);
        writeToOutputFile("STOCK "+ currentDate + "\n");

        ArrayList<Medicament> toRemove = new ArrayList<>();
        for (Map.Entry<String, Medicament> entry : stockMed.entrySet()) {
            Medicament medicament = entry.getValue();
            if (LocalDate.parse(medicament.getDateStr()).isAfter(date1)) {
                writeToOutputFile(medicament.getNom() +" "+ medicament.getNbrDeMed() +" "+
                        medicament.getDateStr() + "\n");
            } else {
                toRemove.add(medicament);
            }
        }
        writeToOutputFile("\n");
        toRemove.forEach(medicament -> stockMed.remove(medicament.getKey()));
    }

    public static int calculateDaysBetweenDates(String startDate, String endDate) {
        LocalDate startDateLD = LocalDate.parse(startDate);
        LocalDate endDateLD = LocalDate.parse(endDate);

        long daysBetween = ChronoUnit.DAYS.between(startDateLD, endDateLD);
        // Adjust for leap years between 2000 and 2025
        int leapYears = countLeapYears(startDateLD, endDateLD);
        daysBetween -= leapYears;

        return (int) daysBetween;
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




    public void writeToOutputFile(String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(txt, true))) {
            writer.write(content);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
