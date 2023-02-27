import java.nio.file.*;
import java.util.ArrayList;


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

interface Transform {
    String transform(String s);
}

class UpperTransform implements Transform {
    public String transform(String s) {
        return s.toUpperCase();
    }
}

class LowerTransform implements Transform {
    public String transform(String s) {
        return s.toLowerCase();
    }
}

class FirstTransform implements Transform {
    int length;

    FirstTransform(String t) {
        this.length = Integer.parseInt(t.replaceAll("\\D+",""));
    }

    public String transform(String s) {
        if (s.length() < this.length) {
            return s;
        }

        return s.substring(0, this.length);
    }
}

class LastTransform implements Transform {
    int length;

    LastTransform(String t) {
        this.length = Integer.parseInt(t.replaceAll("\\D+",""));
    }

    public String transform(String s) {
        if (s.length() < this.length) {
            return s;
        }

        return s.substring(s.length()-this.length, s.length());
    }
}

class ReplaceTransform implements Transform {
    String[] paraStr;

    ReplaceTransform(String t) {
        String parameters = t.substring(t.indexOf("'")+1, t.lastIndexOf("'"));
        String para = parameters.replaceAll("'", "");
        this.paraStr = para.split(";");

    }
    
    public String transform(String s) {
        return s.replaceAll(this.paraStr[0], this.paraStr[1]);
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

    static Transform readTransform(String t) {
        if (t.contains("upper")) {
            return new UpperTransform();
        } else if (t.contains("lower")) {
            return new LowerTransform();
        } else if (t.contains("first")) {
            return new FirstTransform(t);
        } else if (t.contains("last")) {
            return new LastTransform(t);
        } else if (t.contains("replace")) {
            return new ReplaceTransform(t);
        }
        
        return null;
    }

    static public void main(String[] args) {
        String[] strLines = FileHelper.getLines(args[0]);
        if (args.length == 1) {
            for (String i: strLines) {
                System.out.println(i);
            }

            return;
        }

        Query query = StringSearch.readQuery(args[1]);
        ArrayList<String> lines = new ArrayList<String>();

        if (args[1].contains("not")) {
            for (String i: strLines) {
                if (!query.matches(i)) {
                    lines.add(i);
                }
            }
        } else {
            for (String i: strLines) {
                if (query.matches(i)) {
                    lines.add(i);
                }
            }
        }

        if (args.length == 2) {
            for (String i: lines) {
                System.out.println(i);
            }
        }

        if (args.length == 3) {
            Transform transform = StringSearch.readTransform(args[2]);
            ArrayList<String> transformedLines = new ArrayList<String>();
            for (String i: lines) {
                transformedLines.add(transform.transform(i));
            }
            
            for (String i: transformedLines) {
                System.out.println(i);
            }
        }
    }
}