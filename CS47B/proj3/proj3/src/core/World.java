package core;

import tileengine.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class World implements Serializable {
    public static final int WIDTH = 80;
    public static final int HEIGHT = 40;

    private Random random;
    private TETile[][] map;
    private Set<int[]> floor;
    private int[] avatarCoord;
    private int[] snakeGameCoord;
    private boolean inSnakeGame = false;
    
    public World(Random random) {
        this.random = random;
        this.createMap();
    }
    
    public Random getRandom() {
        return this.random;
    }

    public TETile[][] getMap() {
        return this.map;
    }

    public Set<int[]> getFloor() {
        return this.floor;
    }

    public int[] getAvatarCoord() {
        return this.avatarCoord;
    }

    public int[] getSnakeGameCoord() {
        return this.snakeGameCoord;
    }

    public boolean getInSnakeGame() {
        return this.inSnakeGame;
    }

    public void setMap(TETile[][] map) {
        this.map = map;
    }

    public void setFloor(Set<int[]> floor) {
        this.floor = floor;
    }

    public void setAvatarCoord(int[] avatarCoord) {
        this.avatarCoord = avatarCoord;
    }

    public void setSnakeGameCoord(int[] snakeGameCoord) {
        this.snakeGameCoord = snakeGameCoord;
    }

    public void setInSnakeGame(boolean inSnakeGame) {
        this.inSnakeGame = inSnakeGame;
    }

    public TETile[][] putSnakeGame() {
        List<int[]> floorList = new ArrayList<>(this.floor);
        floorList.remove(this.avatarCoord);
        this.snakeGameCoord = floorList.get(this.random.nextInt(floorList.size()));
        this.map[this.snakeGameCoord[0]][this.snakeGameCoord[1]] = Tileset.MISSION;
        return this.map;
    }
    
    public boolean move(int[] direction) {
        boolean enterSnakeGame = false;
        int[] next = new int[] {this.avatarCoord[0] + direction[0],
                this.avatarCoord[1] + direction[1]};
        if (this.inFloor(next)) {
            this.map[avatarCoord[0]][avatarCoord[1]] = Tileset.FLOOR;
            this.map[next[0]][next[1]] = Tileset.AVATAR;
            this.avatarCoord = next;
        }
        if (this.equals(this.avatarCoord, this.snakeGameCoord)) {
            this.snakeGameCoord = null;
            this.inSnakeGame = true;
            enterSnakeGame = true;
            return enterSnakeGame;
        }
        return enterSnakeGame;
    }
    
    private boolean equals(int[] coord1, int[] coord2) {
        if (coord1 == null || coord2 == null) {
            return false;
        }
        return coord1[0] == coord2[0] && coord1[1] == coord2[1];
    }

    private boolean inFloor(int[] p) {
        for (int[] q : this.floor) {
            if (this.equals(q, p)) {
                return true;
            }
        }
        return false;
    }
    
    private Set<int[]> generateVertical() {
        int[][] moves = new int[][] {{-1, 0}, {1, 0}, {0, 1}};
        int[] curr = new int[] {this.random.nextInt(WIDTH), 1};
        Set<int[]> path = new LinkedHashSet<>();
        while (curr[1] < HEIGHT - 2) {
            int[] move = moves[this.random.nextInt(moves.length)];
            int numberOfMoves = this.random.nextInt(5) + 10;
            for (int j = 0; j < numberOfMoves; j++) {
                int[] next = new int[] {curr[0] + move[0], curr[1] + move[1]};
                if (next[0] < 1 || next[0] >= WIDTH - 1 || next[1] >= HEIGHT - 1) {
                    break;
                }
                curr = next;
                path.add(curr);
            }
        }
        return path;
    }
    
    private Set<int[]> generateHorizontal() {
        int[][] moves = new int[][] {{0, -1}, {0, 1}, {1, 0}};
        int[] curr = new int[] {1, this.random.nextInt(HEIGHT)};
        Set<int[]> path = new LinkedHashSet<>();
        while (curr[0] < WIDTH - 2) {
            int[] move = moves[this.random.nextInt(moves.length)];
            int numberOfMoves = this.random.nextInt(5) + 10;
            for (int j = 0; j < numberOfMoves; j++) {
                int[] next = new int[] {curr[0] + move[0], curr[1] + move[1]};
                if (next[0] >= WIDTH - 1 || next[1] < 1 || next[1] >= HEIGHT - 1) {
                    break;
                }
                curr = next;
                path.add(curr);
            }
        }
        return path;
    }

    private int countNeighbors(int[] p) {
        int[][] neighbors = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int count = 0;
        int[] neighbor = new int[2];
        for (int[] n : neighbors) {
            neighbor[0] = p[0] + n[0];
            neighbor[1] = p[1] + n[1];
            if (inFloor(neighbor)) {
                count++;
            }
        }
        return count;
    }
    
    private int countNeighbors(int[] p, boolean includeCorner) {
        int[][] neighbors = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1},
                                         {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
        int count = 0;
        int[] neighbor = new int[2];
        for (int[] n : neighbors) {
            neighbor[0] = p[0] + n[0];
            neighbor[1] = p[1] + n[1];
            if (inFloor(neighbor)) {
                count++;
            }
        }
        return count;
    }

    private Set<int[]> getEnds(Set<int[]> path) {
        Set<int[]> ends = new LinkedHashSet<>();
        int count = 0;
        for (int[] p : path) {
            count = countNeighbors(p);
            if (count == 1) {
                ends.add(p);
            }
        }
        return ends;
    }

    private Set<int[]> generateRoom(int[] vtx) {
        Set<int[]> room = new LinkedHashSet<>();
        int left = this.random.nextInt(3) + 2;
        int right = this.random.nextInt(3) + 2;
        int top = this.random.nextInt(3) + 2;
        int bottom = this.random.nextInt(3) + 2;
        for (int x = Math.max(vtx[0] - left, 1); x <= Math.min(vtx[0] + right, WIDTH - 2); x++) {
            for (int y = Math.max(vtx[1] - bottom, 1); y <= Math.min(vtx[1] + top, HEIGHT - 2); y++) {
                room.add(new int[] {x, y});
            }
        }
        return room;
    }

    private Set<int[]> generateRooms(Set<int[]> path) {
        Set<int[]> ends = getEnds(path);
        Set<int[]> rooms = new LinkedHashSet<>();
        for (int[] end : ends) {
            rooms.addAll(generateRoom(end));
        }
        if (ends.size() < 15) {
            int nMoreRooms = this.random.nextInt(5) + 15 - ends.size();
            List<int[]> pathList = new ArrayList<>(path);
            for (int i = 0; i < nMoreRooms; i++) {
                rooms.addAll(generateRoom(pathList.get(this.random.nextInt(pathList.size()))));
            }
        }
        return rooms;
    }

    private TETile[][] buildMap() {
        int neighborCount = 0;
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                neighborCount = countNeighbors(new int[] {x, y}, true);
                if (neighborCount > 0 && !inFloor(new int[] {x, y})) {
                    this.map[x][y] = Tileset.WALL;
                }
            }
        }
        return this.map;
    }
    
    private TETile[][] putAvatar() {
        List<int[]> floorList = new ArrayList<>(this.floor); 
        this.avatarCoord = floorList.get(this.random.nextInt(floorList.size()));
        this.map[this.avatarCoord[0]][this.avatarCoord[1]] = Tileset.AVATAR;
        return this.map;
    }

    private void createMap() {
        this.map = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                this.map[x][y] = Tileset.NOTHING;
            }
        }

        this.floor = new LinkedHashSet<>();
        int nVLines = this.random.nextInt(2) + 2;
        int nHLines = this.random.nextInt(2) + 1;
        for (int i = 0; i < nVLines; i++) {
            this.floor.addAll(this.generateVertical());
        }
        for (int i = 0; i < nHLines; i++) {
            this.floor.addAll(this.generateHorizontal());
        }
        this.floor.addAll(this.generateRooms(floor));
        for (int[] p : this.floor) {
            this.map[p[0]][p[1]] = Tileset.FLOOR;
        }
        this.buildMap();
        this.putAvatar();
        this.putSnakeGame();
    }
}
