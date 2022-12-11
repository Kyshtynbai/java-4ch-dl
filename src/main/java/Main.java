import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.net.URL;
import java.util.List;

public class Main {
    static final int FILE_NAME_LENGTH = 15;
    public static void main(String[] args) throws Exception {
        String sourceUrl = "";
        Document resp = Jsoup.connect(sourceUrl).userAgent("Mozilla").get();
        List<String> list2 = resp.getElementsByClass("fileThumb").eachAttr("href");
        System.out.println(resp.title());
        //list2.forEach(s -> System.out.println("https:" + s)); // TODO хуйнуть всё, что ниже в метод для лямбды
        for (String url : list2) {
            File file = new File(url.substring(url.length()-FILE_NAME_LENGTH));
            URL source = new URL("https:" + url);
            FileUtils.copyURLToFile(source, file);
            System.out.println("Downloading file " + file + " from " + source);
        } // 38.79 секунд. Скрипт на PowerShell делает то же самое за 7 минут.
        System.out.println("Скачано " + list2.size() + " файлов");
    }
}
