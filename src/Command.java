import java.util.ArrayList;
import java.util.List;

public class Command {
    public static void firstWord(String parts) {
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

    public static void approv(String parts){
        String modifiedInput = parts.replaceFirst("\n", "");
        String[] partition = modifiedInput.split("\n");
        List<Medicament> medicamentList = new ArrayList<>();
        for (String partitions: partition){
            if (partitions.startsWith("APPROV :")) {
                continue;}
            String med = partitions.replaceFirst("Medicament","") ;
            String[] medi = med.split(" ");
            //Medicament medicament = new Medicament(medi[2],Integer.parseInt(medi[1]), Integer.parseInt(medi[0]));
            //medicamentList.add(medicament);
            MedicamentTree medicamentTree = new MedicamentTree();
        }



    }
    public static void date(){}
    public static void prescription(){}
    public static void stock(){}
}
