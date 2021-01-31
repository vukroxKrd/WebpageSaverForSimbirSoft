import java.io.*;
import java.net.URL;

public class Reader {

    public void readFromFile(String fileName) throws IOException {

        FileInputStream fileInputStream = new FileInputStream(fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));

        while (true) {
            String line = reader.readLine();
            if (line == null)
                break;
            System.out.println(line);
        }
    }
}
