import java.io.IOException;
import java.net.URL;



public class Main {
    public static void main(String[] args) throws IOException {

        Writer writer = new Writer();
        URL url = new URL("https://www.simbirsoft.com/");
        String fileName = "E:\\PROJECTS\\websitereader-master\\websitereader-master\\temp.file";
        writer.writeToFileFromTheWebSite(url, fileName);

        //Reader reader = new Reader();
        //String fileName = "C:\\Users\\slava\\Documents\\filename.html";
        //reader.readFromFile(fileName);

    }
}
