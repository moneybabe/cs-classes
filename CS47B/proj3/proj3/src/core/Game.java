package core;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.princeton.cs.algs4.StdDraw;
import tileengine.TERenderer;

public class Game implements Serializable {
    
    private class Wrapper implements Serializable {
        private Random random;
        private TERenderer ter;
        private World world;
        private SnakeWorld snakeWorld;

        public Wrapper(Random random, TERenderer ter, World world, SnakeWorld snakeWorld) {
            this.random = random;
            this.ter = ter;
            this.world = world;
            this.snakeWorld = snakeWorld;
        }
    }

    private Random random;
    private TERenderer ter;
    private World world;
    private SnakeWorld snakeWorld;

    public Game() {
        this.ter = new TERenderer(World.WIDTH, World.HEIGHT, 0, 2);
    }

    public Game(String autograderInput) {
        String patternString = "(N\\d+S|L)([WASD]*)(:Q)?";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(autograderInput.toUpperCase());
        if (matcher.find()) {
            String group1 = matcher.group(1);
            String group2 = matcher.group(2);
            String group3 = matcher.group(3);
            if (group1.equals("L")) {
                this.loadGame();
            } else {
                Long seed = Long.parseLong(group1.substring(1, group1.length() - 1));
                this.random = new Random(seed);
                this.world = new World(this.random);
            }
            for (int i = 0; i < group2.length(); i++) {
                char c = group2.charAt(i);
                this.world.move(Direction.get(c));
            }
            if (group3 != null) {
                this.saveGame();
            }
        }
    }

    public World getWorld() {
        return this.world;
    }

    public boolean start() throws IOException {
        char c = ' ';
        String input = "";
        String outcome = "";
        while (true) {
            if (outcome.equals("quit")) {
                return true;
            }
            ter.renderMainMenu();
            c = StdDraw.hasNextKeyTyped() ? Character.toUpperCase(StdDraw.nextKeyTyped()) : ' ';
            switch (c) {
                case 'Q':
                    return true;
                case 'N':
                    input = this.getSeed(input + c);
                    if (input.equals("quit")) {
                        return true;
                    } else if (!input.equals("")) {
                        Long seed = Long.parseLong(input);
                        this.random = new Random(seed);
                        this.world = new World(this.random);
                        outcome = this.playMainGame();
                    }
                    break;
                case 'L':
                    Wrapper wrapper = this.loadGame();
                    if (wrapper == null) {
                        return true;
                    } else if (this.world.getInSnakeGame()) {
                        this.ter.renderResumeSnake(this.snakeWorld.getScore());
                        outcome = this.playSnakeGame();
                        if (outcome.equals("lost")) {
                            outcome = this.playMainGame();
                        }
                    } else {
                        outcome = this.playMainGame();
                    }
                    break;
                default:
                    break;
            }
        }
    }
        
    public String playMainGame() throws IOException {
        char c;
        String input;
        while (true) {
            c = StdDraw.hasNextKeyTyped() ? Character.toUpperCase(StdDraw.nextKeyTyped()) : ' ';
            this.ter.renderGame(this.world.getMap());
            if (c == ':') {
                char d;
                input = ":";
                this.ter.renderColonInput(this.world.getMap(), input);
                while (input.length() != 0) {
                    d = StdDraw.hasNextKeyTyped() ? StdDraw.nextKeyTyped() : ' ';
                    if (input.length() == 1 && (d == 'Q' || d == 'q')) {
                        this.saveGame();
                        return "quit";
                    } else if (d == '\b') {
                        input = input.substring(0, input.length() - 1);
                        this.ter.renderColonInput(this.world.getMap(), input);
                    } else if (d != ' ') {
                        input += d;
                        this.ter.renderColonInput(this.world.getMap(), input);
                    }
                }
            } else {
                int[] direction = Direction.get(c);
                if (direction != null) {
                    boolean enterSnakeGame = this.moveMain(direction, c);
                    if (enterSnakeGame) {
                        this.snakeWorld = new SnakeWorld(this.random);
                        this.ter.renderStartSnake();
                        String outcome = this.playSnakeGame();
                        if (outcome.equals("quit") || outcome.equals("won")) {
                            return outcome;
                        }
                    }
                }
            }
        }
    }
    
    public String playSnakeGame() {
        char c;
        String input;
        while (true) {
            c = StdDraw.hasNextKeyTyped() ? Character.toUpperCase(StdDraw.nextKeyTyped()) : ' ';
            if (c == ':') {
                char d;
                input = ":";
                this.ter.renderSnakeColonInput(this.snakeWorld.getMap(), input, this.snakeWorld.getScore());
                while (input.length() != 0) {
                    d = StdDraw.hasNextKeyTyped() ? StdDraw.nextKeyTyped() : ' ';
                    if (input.length() == 1 && (d == 'Q' || d == 'q')) {
                        this.saveGame();
                        return "quit";
                    } else if (d == '\b') {
                        input = input.substring(0, input.length() - 1);
                        this.ter.renderSnakeColonInput(this.snakeWorld.getMap(), input, this.snakeWorld.getScore());
                    } else if (d != ' ') {
                        input += d;
                        this.ter.renderSnakeColonInput(this.snakeWorld.getMap(), input, this.snakeWorld.getScore());
                    }
                }
            } else {
                this.turnShake(c);
            }

            boolean isLost = this.snakeWorld.move();
            if (isLost) {
                this.world.putSnakeGame();
                this.ter.renderLostSnake();
                this.world.setInSnakeGame(false);
                return "lost";
            } else if (this.snakeWorld.isWon()) {
                this.ter.renderWinSnake();
                this.world.setInSnakeGame(false);
                return "won";
            }
            this.ter.renderSnakeGame(this.snakeWorld.getMap(), this.snakeWorld.getScore());
        }
    }
    
    private String getSeed(String input) {
        this.ter.renderMainMenu(input);
        char c;
        while (input.length() != 0) {
            c = StdDraw.hasNextKeyTyped() ? Character.toUpperCase(StdDraw.nextKeyTyped()) : ' ';
            switch (c) {
                case 'Q':
                    return "quit";
                case 'R':
                    return "";
                case 'S':
                    if (input.length() > 1) {
                        return input.substring(1, input.length());
                    }
                    break;
                case '\b':
                    input = input.substring(0, input.length() - 1);
                    this.ter.renderMainMenu(input);
                    break;
                default:
                    if (Character.isDigit(c)) {
                        input += c;
                        this.ter.renderMainMenu(input);
                    }
                    break;
            }
        }
        return "";
    }
    
    private void saveGame() {
        String filename = "game.txt";
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(new Wrapper(this.random, this.ter, this.world, this.snakeWorld));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Wrapper loadGame() {
        String filename = "game.txt";
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            Wrapper wrapper = (Wrapper) in.readObject();
            this.random = wrapper.random;
            this.ter = wrapper.ter;
            this.world = wrapper.world;
            this.snakeWorld = wrapper.snakeWorld;
            return wrapper;
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }

    private void clearKeys() {
        while (StdDraw.hasNextKeyTyped()) {
            StdDraw.nextKeyTyped();
        }
    }
    
    private boolean moveMain(int[] direction, char c) {
        boolean enterSnakeGame = false;
        if (StdDraw.isKeyPressed(c)) {
            enterSnakeGame = this.world.move(direction);
            this.ter.renderGame(this.world.getMap());
        } else {
            this.clearKeys();
        }
        return enterSnakeGame;
    }
    
    private void turnShake(char c) {
        int[] direction = Direction.get(c);
        if (direction != null) {
            this.snakeWorld.turn(direction);
        }
        this.clearKeys();
    }
}
