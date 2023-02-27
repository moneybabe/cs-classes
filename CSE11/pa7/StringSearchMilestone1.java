import java.nio.file.*;
import java.io.IOException;

class FileHelper {
    static String[] getLines(String path) {
        try {
            return Files.readAllLines(Paths.get(path)).toArray(String[]::new);
        }
        catch(IOException e) {
            System.err.println("Error reading file " + path + ": " + e);
            return new String[]{"Error reading file " + path + ": " + e};
        }
    }
}

class StringSearch{
    static public void main(String[] args) {
        String[] strLines = FileHelper.getLines(args[0]);
        for (String i: strLines) {
            System.out.println(i);
        }
    }

}
