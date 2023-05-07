import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    ArrayList<String> fruits = new ArrayList<String>();

    public String handleRequest(URI url) {
        if (url.getPath().contains("/add")) {
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) {
                fruits.add(parameters[1]);
                return String.format("Item %s has been added to Francis and Neo's basket!", parameters[1]);
            }
        }

        if (url.getPath().contains("/search")) {
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) {                
                ArrayList<String> queryFruits = new ArrayList<String>();
                for (String fruit: fruits) {
                    if (fruit.contains(parameters[1])) {
                        queryFruits.add(fruit);
                    }
                }
                return "This is Francis and Neo's fruits: " + queryFruits.toString();
            }
        }

        return "Invalid request";
    }
}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
