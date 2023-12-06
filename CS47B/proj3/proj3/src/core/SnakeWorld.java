package core;

import java.io.*;
import java.util.LinkedList;
import java.util.Random;

import tileengine.TETile;
import tileengine.Tileset;

public class SnakeWorld implements Serializable {
    public static final int WIDTH = World.WIDTH;
    public static final int HEIGHT = World.HEIGHT;
    public static final int TARGET_SCORE = 5;

    private Random random;
    private TETile[][] map;
    private Snake snake;
    private int[] food;
    private int score;

    public Random getRandom() {
        return this.random;
    }

    public TETile[][] getMap() {
        return this.map;
    }
    
    public Snake getSnake() {
        return this.snake;
    }

    public int[] getFood() {
        return this.food;
    }

    public int getScore() {
        return this.score;
    }
    
    public void setMap(TETile[][] map) {
        this.map = map;
    }
    
    public void setSnake(Snake snake) {
        this.snake = snake;
    }
    
    public void setFood(int[] food) {
        this.food = food;
    }
    
    public void setScore(int score) {
        this.score = score;
    }

    public SnakeWorld(Random random) {
        this.random = random;
        this.snake = new Snake();
        this.createMap(true);
    }
    
    public boolean isWon() {
        return this.score >= TARGET_SCORE;
    }

    public void turn(int[] direction) {
        this.snake.turn(direction);
    }

    public boolean move() {
        boolean isLost = false;
        int xNext = ((this.snake.getHead()[0] + this.snake.direction[0]) + WIDTH) % WIDTH;
        int yNext = ((this.snake.getHead()[1] + this.snake.direction[1]) + HEIGHT) % HEIGHT;
        int[] next = new int[] {xNext, yNext};
        if (this.inSnake(next)) {
            isLost = true;
            return isLost;
        } else if (this.equals(next, this.food)) {
            this.eat(next);
            this.createMap(true);
        } else {
            this.move(next);
            this.createMap(false);
        }
        return isLost;
    }
    
    private boolean equals(int[] coord1, int[] coord2) {
        if (coord1 == null || coord2 == null) {
            return false;
        }
        return coord1[0] == coord2[0] && coord1[1] == coord2[1];
    }

    private boolean inSnake(int[] coord) {
        for (int[] body : this.snake.body) {
            if (this.equals(body, coord)) {
                return true;
            }
        }
        return false;
    }

    private void generateFood() {
        int[] coord = new int[] {this.random.nextInt(WIDTH), this.random.nextInt(HEIGHT)};
        while (this.inSnake(coord)) {
            coord = new int[] {this.random.nextInt(WIDTH), this.random.nextInt(HEIGHT)};
        }
        this.food = coord;
        this.map[this.food[0]][this.food[1]] = Tileset.FOOD;
    }

    private void createMap(boolean genFood) {
        this.map = new TETile[WIDTH][HEIGHT];
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                this.map[i][j] = Tileset.NOTHING;
            }
        }
        for (int[] coord : this.snake.body) {
            this.map[coord[0]][coord[1]] = Tileset.SNAKE_BODY;
        }
        map[this.snake.getHead()[0]][this.snake.getHead()[1]] = Tileset.SNAKE_HEAD;
        if (genFood) {
            this.generateFood();
        } else {
            this.map[this.food[0]][this.food[1]] = Tileset.FOOD;
        }
    }

    private void eat(int[] coord) {
        this.snake.eat(coord);
        this.score++;
    }

    private void move(int[] next) {
        this.snake.move(next);
    };

    private class Snake implements Serializable {
        private LinkedList<int[]> body;
        private int[] direction;

        public Snake() {
            this.createBody();
            this.direction = new int[] {1, 0};
        }

        private void createBody() {
            this.body = new LinkedList<>();
            for (int i = 0; i < 40; i++) {
                this.body.addLast(new int[] {WIDTH / 2 - i, HEIGHT / 2});
            }
        }

        public int[] getHead() {
            return this.body.getFirst();
        }

        public boolean move(int[] next) {
            this.body.addFirst(next);
            this.body.removeLast();
            return true;
        }

        public void eat(int[] coord) {
            this.body.addFirst(coord);
            for (int i = 0; i < 10; i++) {
                int[] next = new int[] {((this.getHead()[0] + this.direction[0]) + WIDTH) % WIDTH,
                    ((this.getHead()[1] + this.direction[1]) + HEIGHT) % HEIGHT};
                this.body.addFirst(next);
            }
        }
        
        public void turn(int[] dir) {
            if (!this.isOpposite(dir)) {
                this.direction = dir;
            }
        }

        private boolean isOpposite(int[] dir) {
            return this.direction[0] + dir[0] == 0 && this.direction[1] + dir[1] == 0;
        }
    }
}
