package tileengine;

import edu.princeton.cs.algs4.StdDraw;

import java.awt.Color;
import java.awt.Font;
import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;    

import core.SnakeWorld;

/**
 * Utility class for rendering tiles. You do not need to modify this file. You're welcome
 * to, but be careful. We strongly recommend getting everything else working before
 * messing with this renderer, unless you're trying to do something fancy like
 * allowing scrolling of the screen or tracking the avatar or something similar.
 */
public class TERenderer implements Serializable {
    private static final int TILE_SIZE = 16;
    private int width;
    private int height;
    private int xOffset;
    private int yOffset;

    public TERenderer(int w, int h, int xOff, int yOff) {
        this.initialize(w, h, xOff, yOff);
    }

    public TERenderer(int w, int h) {
        this.initialize(w, h);
    }

    /**
     * Same functionality as the other initialization method. The only difference is that the xOff
     * and yOff parameters will change where the renderFrame method starts drawing. For example,
     * if you select w = 60, h = 30, xOff = 3, yOff = 4 and then call renderFrame with a
     * TETile[50][25] array, the renderer will leave 3 tiles blank on the left, 7 tiles blank
     * on the right, 4 tiles blank on the bottom, and 1 tile blank on the top.
     * @param w width of the window in tiles
     * @param h height of the window in tiles.
     */
    public void initialize(int w, int h, int xOff, int yOff) {
        this.width = w;
        this.height = h;
        this.xOffset = xOff;
        this.yOffset = yOff;
        StdDraw.setCanvasSize(width * TILE_SIZE + xOffset, height * TILE_SIZE + yOffset);
        Font font = new Font("Monaco", Font.BOLD, TILE_SIZE - 2);
        StdDraw.setFont(font);      
        StdDraw.setXscale(0, width + xOffset);
        StdDraw.setYscale(0, height + yOffset);

        StdDraw.clear(new Color(0, 0, 0));

        StdDraw.enableDoubleBuffering();
        StdDraw.show();
    }

    /**
     * Initializes StdDraw parameters and launches the StdDraw window. w and h are the
     * width and height of the world in number of tiles. If the TETile[][] array that you
     * pass to renderFrame is smaller than this, then extra blank space will be left
     * on the right and top edges of the frame. For example, if you select w = 60 and
     * h = 30, this method will create a 60 tile wide by 30 tile tall window. If
     * you then subsequently call renderFrame with a TETile[50][25] array, it will
     * leave 10 tiles blank on the right side and 5 tiles blank on the top side. If
     * you want to leave extra space on the left or bottom instead, use the other
     * initializatiom method.
     * @param w width of the window in tiles
     * @param h height of the window in tiles.
     */
    public void initialize(int w, int h) {
        initialize(w, h, 0, 0);
    }

    /**
     * Takes in a 2d array of TETile objects and renders the 2d array to the screen, starting from
     * xOffset and yOffset.
     *
     * If the array is an NxM array, then the element displayed at positions would be as follows,
     * given in units of tiles.
     *
     *              positions   xOffset |xOffset+1|xOffset+2| .... |xOffset+world.length
     *                     
     * startY+world[0].length   [0][M-1] | [1][M-1] | [2][M-1] | .... | [N-1][M-1]
     *                    ...    ......  |  ......  |  ......  | .... | ......
     *               startY+2    [0][2]  |  [1][2]  |  [2][2]  | .... | [N-1][2]
     *               startY+1    [0][1]  |  [1][1]  |  [2][1]  | .... | [N-1][1]
     *                 startY    [0][0]  |  [1][0]  |  [2][0]  | .... | [N-1][0]
     *
     * By varying xOffset, yOffset, and the size of the screen when initialized, you can leave
     * empty space in different places to leave room for other information, such as a GUI.
     * This method assumes that the xScale and yScale have been set such that the max x
     * value is the width of the screen in tiles, and the max y value is the height of
     * the screen in tiles.
     * @param world the 2D TETile[][] array to render
     */
    public void renderFrame(TETile[][] world) {
        int numXTiles = world.length;
        int numYTiles = world[0].length;
        StdDraw.clear(new Color(0, 0, 0));
        for (int x = 0; x < numXTiles; x += 1) {
            for (int y = 0; y < numYTiles; y += 1) {
                if (world[x][y] == null) {
                    throw new IllegalArgumentException("Tile at position x=" + x + ", y=" + y
                            + " is null.");
                }
                Font font = new Font("Monaco", Font.BOLD, TILE_SIZE - 2);
                StdDraw.setFont(font);
                world[x][y].draw(x + xOffset, y + yOffset);
            }
        }
        StdDraw.setPenColor(Color.darkGray);
        StdDraw.filledRectangle(this.width, 0, this.width, this.yOffset);
        StdDraw.show();
    }
    
    /**
     * Like renderFrame, but does not clear the screen or show the tiles
     */
    private void drawTiles(TETile[][] world) {
        StdDraw.clear(Color.black);
        for (int x = 0; x < world.length; x += 1) {
            for (int y = 0; y < world[x].length; y += 1) {
                Font font = new Font("Monaco", Font.BOLD, TILE_SIZE - 2);
                StdDraw.setFont(font);
                world[x][y].draw(x + xOffset, y + yOffset);
            }
        }
        StdDraw.setPenColor(Color.darkGray);
        StdDraw.filledRectangle(this.width, 0, this.width, this.yOffset);
    }
    
    private void renderSignature() {
        Font cornerFont = new Font("Monaco", Font.PLAIN, 15);
        StdDraw.setPenColor(Color.PINK);
        StdDraw.setFont(cornerFont);
        StdDraw.textRight(this.width - 1, 1, "By Jade & Neo >_<");
    }
    
    private void renderHUD(TETile[][] map) {
        this.renderSignature();
        int x = (int) Math.floor(StdDraw.mouseX()) - this.xOffset;
        int y = (int) Math.floor(StdDraw.mouseY()) - this.yOffset;
        Font font = new Font("Monacao", Font.PLAIN, 17);
        StdDraw.setPenColor(Color.white);
        StdDraw.setFont(font);
        if (x >= 71 && x <= 73 && y < 0) {
            StdDraw.text(this.width / 2, 1, "You are pointing at Jade");
        } else if (x >= 75 && x <= 76 && y < 0) {
            StdDraw.text(this.width / 2, 1, "Get your hands off me dude.");
        } else if (x <= 12 && y < 0) {
            StdDraw.text(this.width / 2, 1, "That's right, check your time");
        } else if (y < 0) {
            StdDraw.text(this.width / 2, 1, "You are pointing at HUD stoopid :p");
        } else if (x < 0 || x >= map.length || y < 0 || y >= map[0].length) {
            StdDraw.text(this.width / 2, 1, "You are pointing at nothing");
        } else {
            StdDraw.text(this.width / 2, 1, "You are pointing at " + map[x][y].description());
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        StdDraw.textLeft(1, 1, dtf.format(now));
    }

    public void renderMainMenu() {
        StdDraw.clear(new Color(0, 0, 0));
        Font titleFont = new Font("Monaco", Font.BOLD, 60);
        StdDraw.setFont(titleFont);
        StdDraw.setPenColor(Color.white);
        StdDraw.text(this.width / 2, this.height / 4 * 3, "THE GAME");
        Font optionFont = new Font("Monaco", Font.PLAIN, 30);
        StdDraw.setFont(optionFont);
        StdDraw.text(this.width / 2, this.height / 2, "New Game (N)");
        StdDraw.text(this.width / 2, this.height / 2 - 2, "Load Game (L)");
        StdDraw.text(this.width / 2, this.height / 2 - 4, "Quit (Q)");
        this.renderSignature(); 
        StdDraw.show();
    }
    
    public void renderMainMenu(String input) {
        this.renderMainMenu();  
        StdDraw.setPenColor(Color.white);
        StdDraw.setFont(new Font("Monaco", Font.PLAIN, 20));
        StdDraw.text(this.width / 2, this.height / 2 - 10, "Your Input: " + input);
        StdDraw.text(this.width / 2, this.height / 2 - 12, "(Enter integers and press S when done)");
        StdDraw.text(this.width / 2, this.height / 2 - 14, "(Press R to reset)");
        StdDraw.show();
    }
    
    
    public void renderGame(TETile[][] map) {
        this.drawTiles(map);
        this.renderHUD(map);
        StdDraw.show();
        StdDraw.pause(20);
    }

    public void renderColonInput(TETile[][] map, String input) {
        this.drawTiles(map);
        this.renderSignature(); 
        Font font = new Font("Monacao", Font.PLAIN, 17);
        StdDraw.setPenColor(Color.white);
        StdDraw.setFont(font);
        StdDraw.textLeft(0.5, 1, input);
        StdDraw.show();
    }
    
    private void renderSnakeScore(int score) {
        StdDraw.setPenColor(Color.white);
        StdDraw.setFont(new Font("Monaco", Font.PLAIN, 20));
        StdDraw.textLeft(0, this.height, "Score: " + score);
    }

    public void renderSnakeGame(TETile[][] map, int score) {
        this.drawTiles(map);
        this.renderHUD(map);
        this.renderSnakeScore(score);
        StdDraw.show();
        StdDraw.pause(20);
    }

    public void renderSnakeColonInput(TETile[][] map, String input, int score) {
        this.drawTiles(map);
        this.renderSignature();
        this.renderSnakeScore(score);
        Font font = new Font("Monacao", Font.PLAIN, 17);
        StdDraw.setPenColor(Color.white);
        StdDraw.setFont(font);
        StdDraw.textLeft(0.5, 1, input);
        StdDraw.show();
    }

    public void renderStartSnake() {
        for (int i = 3; i > 0; i--) {
            StdDraw.clear(Color.black);
            StdDraw.setFont(new Font("Monaco", Font.BOLD, 60));
            StdDraw.setPenColor(Color.white);
            StdDraw.text(this.width / 2, this.height / 5 * 3, "SNAKE GAME");
            StdDraw.setFont(new Font("Monaco", Font.BOLD, 30));
            StdDraw.text(this.width / 2, this.height / 5 * 2.5,
                    "You need to eat " + SnakeWorld.TARGET_SCORE + " apples to win");
            StdDraw.text(this.width / 2, this.height / 5 * 2, "Starting in " + i + " seconds");
            StdDraw.show();
            StdDraw.pause(1000);
        }
    }
    
    public void renderResumeSnake(int score) {
        for (int i = 3; i > 0; i--) {
            StdDraw.clear(Color.black);
            StdDraw.setFont(new Font("Monaco", Font.BOLD, 60));
            StdDraw.setPenColor(Color.white);
            StdDraw.text(this.width / 2, this.height / 5 * 3, "SNAKE GAME");
            StdDraw.setFont(new Font("Monaco", Font.BOLD, 30));
            StdDraw.text(this.width / 2, this.height / 5 * 2.5,
                    "You need to eat " + (SnakeWorld.TARGET_SCORE - score) + " more apples to win");
            StdDraw.text(this.width / 2, this.height / 5 * 2, "Resuming in " + i + " seconds");
            StdDraw.show();
            StdDraw.pause(1000);
        }

    }
    
    public void renderLostSnake() {
        StdDraw.clear(Color.black);
        StdDraw.setFont(new Font("Monaco", Font.BOLD, 60));
        StdDraw.setPenColor(Color.white);
        StdDraw.text(this.width / 2, this.height / 5 * 3, "YOU LOST!");
        StdDraw.setFont(new Font("Monaco", Font.BOLD, 30));
        StdDraw.text(this.width / 2, this.height / 5 * 2, "Try again next time :p");
        StdDraw.show();
        StdDraw.pause(1500);
    }
    
    public void renderWinSnake() {
        for (int i = 3; i > 0; i--) {
            StdDraw.clear(Color.black);
            Font titleFont = new Font("Monaco", Font.BOLD, 60);
            StdDraw.setFont(titleFont);
            StdDraw.setPenColor(Color.white);
            StdDraw.text(this.width / 2, this.height / 5 * 3, "YOU WON!");
            StdDraw.setFont(new Font("Monaco", Font.BOLD, 30));
            StdDraw.text(this.width / 2, this.height / 5 * 2, "Returning to main menu in " + i + " seconds");
            StdDraw.show();
            StdDraw.pause(1000);
        }
    }
}
