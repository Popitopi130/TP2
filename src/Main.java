import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        //read the file
        BufferedReader reader = new BufferedReader(new FileReader("test1.txt"));

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append("\n"+line);

        }
        String text = sb.toString();
        String[] parts = text.split(";");
        Command command = new Command();
        //for (String part : parts) {
            command.firstWord(parts[0]);
        //}
    }



}



