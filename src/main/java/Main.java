import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

public class Main {
    static final int FILE_NAME_LENGTH = 15;
    public static void main(String[] args) throws Exception {
        File inputFile = new File("src/main/input.txt");
        Scanner reader = new Scanner(inputFile);
        while (reader.hasNextLine()) {
            String sourceUrl = reader.nextLine();
            System.out.println("Качаем тред " + sourceUrl);

            Document resp = Jsoup.connect(sourceUrl).userAgent("Mozilla").get();
            List<String> list2 = resp.getElementsByClass("fileThumb").eachAttr("href");

            System.out.println(resp.title());
            File newDir = new File(Utils.createDir(resp.title()));
            System.out.println(newDir.mkdir());

            for (String url : list2) {
                File file = new File(newDir + "/" + (url.substring(url.length() - FILE_NAME_LENGTH)));
                URL source = new URL("https:" + url);
                FileUtils.copyURLToFile(source, file);
                System.out.println("Downloading file " + file + " from " + source);
            } // 38.79 секунд. Скрипт на PowerShell делает то же самое за 7 минут.
            System.out.println("Скачано " + list2.size() + " файлов");
        }
    }
}
