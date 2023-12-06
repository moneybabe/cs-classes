package core;

public class Direction {
    
    public static int[] get(char c) {
        switch (c) {
            case 'W':
                return new int[] {0, 1};
            case 'S':
                return new int[] {0, -1};
            case 'A':
                return new int[] {-1, 0};
            case 'D':
                return new int[] {1, 0};
            default:
                return null;
        }
    }
}
