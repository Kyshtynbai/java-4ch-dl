public class Utils {
    static String createDir(String threadTitle) {
        return threadTitle.replaceAll("[^a-zA-Z]", "_");
    }
}
