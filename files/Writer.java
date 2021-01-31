import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Writer {

    public void writeToFileFromTheWebSite(URL url, String fileName) throws IOException {

        //Используем блок try-with-resources
        //Открываем connection, затем как мы вычитаем данные оно автоматически закроется
        try(InputStream inputStream = url.openConnection().getInputStream()) {

            //Вычитываем в ридер байтовый поток из inputstream
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

            //Вычитываем сразу все строки из ридера, и создаем результирующую одну строку
            //в которой будут слепленые строки из reader, и в качестве разделителя
            //между строк используем перевод каретки - т.е. каждая новая строка будет записана с новой строки (символ "\n")
            // это хипстерский вариант
            //String collect = reader.lines().collect(Collectors.joining("\n"));
            //Смотрим на экране что мы там скачали -
            //System.out.println("Выводим на экран скачанную информацию" + collect);

            //А это трушный -
            ArrayList<String> arrayList = new ArrayList<>();
            while (reader.readLine() != null) {
                //каждую прочитанную строку добавляем в массив ArrayList строк, если она не содержит "null"
                arrayList.add(reader.readLine());
            }

            //Сразу после вычитки можно закрыть ридер
            reader.close();

            //Смотрим на экране что мы там скачали (и видим null в конце)-
            System.out.println("Выводим на экран скачанную информацию: \n");
            for (int i = 0; i < arrayList.size(); i++) {
                System.out.println(arrayList.get(i));
            }

            //Записываем нашу информацию из массива в файл, имя которого передали в аргументе метода
            //Создаем самозакрываемый BufferedWriter
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
                for (int i = 0; i < arrayList.size(); i++) {

                    //ВАЖНО!! для противодействиям парсеров сайтов обычно на страницу добавляют текстовые поля со значением "null"
                    // чтобы массив вышел за границы, поэтому делаем проверку на null -
                    if(arrayList.get(i) != null) {
                        //Чтоб удобно читалось, для записи в файл формируем строку с символом перевода коретки в конце
                        String stringForWrite = String.format("%s%s", arrayList.get(i), "\n");
                        bw.write(stringForWrite);
                    }
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}


