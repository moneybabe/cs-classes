package core;

import java.io.IOException;

public class Main {
    protected static final long SEED = 0;

    public static void main(String[] args) throws IOException {
        Game game = new Game();
        boolean isQuit = game.start();
        if (isQuit) {
            System.exit(0);
        }
    }
}
