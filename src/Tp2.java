import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;

public class Tp2 {

    public static void main(String[] args) throws IOException {
        Locale.setDefault(Locale.US);

        // Get the file names
        String info, writeOn;
        try {
            info = args[0];
            writeOn = args[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            info = args[0];
            writeOn = "output.txt";
        }
        //read the file
        BufferedReader reader = new BufferedReader(new FileReader(info));

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append("\n"+line);

        }
        String text = sb.toString();
        String[] parts = text.split(";");
        // System.out.println(Arrays.toString(parts));
        // System.out.println(parts.length);
        Command command = new Command(writeOn);
        for (String part : parts) {
            command.firstWord(part);
        }
    }



}



