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

interface Query {
    boolean matches(String s);
}

class LengthQuery implements Query {
    int criteria;

    LengthQuery(String q) {
        this.criteria = Integer.parseInt(q.replaceAll("\\D+",""));
    }

    public boolean matches(String s) {
        return s.length() == criteria;
    }
}

class GreaterQuery implements Query {
    int criteria;

    GreaterQuery(String q) {
        this.criteria = Integer.parseInt(q.replaceAll("\\D+",""));
    }

    public boolean matches(String s) {
        return s.length() > criteria;
    }
}

class LessQuery implements Query {
    int criteria;
    
    LessQuery(String q) {
        this.criteria = Integer.parseInt(q.replaceAll("\\D+",""));
    }
    
    public boolean matches(String s) {
        return s.length() < criteria;
    }
}

class ContainsQuery implements Query{
    String criteria;

    ContainsQuery(String q) {
        this.criteria = q.substring(q.indexOf("'")+1, q.lastIndexOf("'"));
    }

    public boolean matches(String s) {
        return s.contains(this.criteria);
    }
}

class StartsQuery implements Query {
    String criteria;

    StartsQuery(String q) {
        this.criteria = q.substring(q.indexOf("'")+1, q.lastIndexOf("'"));
    }

    public boolean matches(String s) {
        return s.startsWith(this.criteria);
    }
}

class EndsQuery implements Query {
    String criteria;

    EndsQuery(String q) {
        this.criteria = q.substring(q.indexOf("'")+1, q.lastIndexOf("'"));
    }

    public boolean matches(String s) {
        return s.endsWith(this.criteria);
    }
}

class StringSearch{

    static Query readQuery(String q) {
        if (q.contains("length")) {
            return new LengthQuery(q);
        } else if (q.contains("greater")) {
            return new GreaterQuery(q);
        } else if (q.contains("less")) {
            return new LessQuery(q);
        } else if (q.contains("contains")) {
            return new ContainsQuery(q);
        } else if (q.contains("starts")) {
            return new StartsQuery(q);
        } else if (q.contains("ends")) {
            return new EndsQuery(q);
        }

        return null;
    }

    static public void main(String[] args) {
        String[] strLines = FileHelper.getLines(args[0]);
        if (args.length == 1) {
            for (String i: strLines) {
                System.out.println(i);
            }
        }

        Query query = StringSearch.readQuery(args[1]);
        if (args[1].contains("not")) {
            for (String i: strLines) {
                if (!query.matches(i)) {
                    System.out.println(i);
                }
            }
        } else {
            for (String i: strLines) {
                if (query.matches(i)) {
                    System.out.println(i);
                }
            }
        }
    }
}