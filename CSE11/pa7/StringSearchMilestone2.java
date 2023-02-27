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


class ContainsQuery {
    String criteria;

    ContainsQuery(String criteria) {
        this.criteria = criteria;
    }

    boolean matches(String s) {
        return s.contains(this.criteria);
    }
}


class StringSearch{
    static public void main(String[] args) {
        String[] strLines = FileHelper.getLines(args[0]);
        if (args.length == 1) {
            for (String i: strLines) {
                System.out.println(i);
            }
        }

        ContainsQuery query = new ContainsQuery(args[1].substring(args[1].indexOf("'")+1, args[1].length()-1));
        for (String i: strLines) {
            if (query.matches(i)) {
                System.out.println(i);
            }
        }
    }
}
