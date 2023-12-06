import core.AutograderBuddy;
import edu.princeton.cs.algs4.StdDraw;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import tileengine.TERenderer;
import tileengine.TETile;

public class WorldGenTests {
    @Test
    public void basicTest() {
        // put different seeds here to test different worlds
        TETile[][] tiles = AutograderBuddy.getWorldFromInput("n5197880843569031643s");
        TETile[][] t2 = AutograderBuddy.getWorldFromInput("n5197880843569031643s");

        TERenderer ter = new TERenderer(tiles.length, tiles[0].length);
        assertEquals(tiles[0].length, t2[0].length);
        ter.renderFrame(tiles);
        StdDraw.pause(5000); // pause for 5 seconds so you can see the output
    }
    
    @Test
    public void basicInteractivityTest() {
        // TODO: write a test that uses an input like "n123swasdwasd"
    }
    
    @Test
    public void basicSaveTest() {
        // TODO: write a test that calls getWorldFromInput twice, with "n123swasd:q" and with "lwasd"
    }
    
    @Test
    public void fullTest () {
        String input = "L";
        TETile[][] tiles = AutograderBuddy.getWorldFromInput(input);
        TERenderer ter = new TERenderer(tiles.length, tiles[0].length);
        ter.renderFrame(tiles);
    }
}
