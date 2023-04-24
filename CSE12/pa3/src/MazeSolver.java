import java.util.ArrayList;
/* Class to implement a Maze solver */

public abstract class MazeSolver {
	
	public static Square solve(Maze maze, SearchWorklist wl) {
		/* Complete this method */
		wl.add(maze.start);
		maze.start.visit();

		while (!wl.isEmpty()) {
			Square current = wl.remove();
			if (current == maze.finish) {
				return current;
			}
			
			ArrayList<Square> neighbors = availableNeighbors(maze, current);
			for (Square neighbor : neighbors) {
				neighbor.visit();
				neighbor.setPrevious(current);
				wl.add(neighbor);
			}
		}

		return null;
	}

	/* Add any helper methods you want */
	public static ArrayList<Square> availableNeighbors(Maze maze, Square current) {
		ArrayList<Square> neighbors = new ArrayList<>();

		try {
			if (!maze.contents[current.getRow() - 1][current.getCol()].getIsWall() &&
					!maze.contents[current.getRow() - 1][current.getCol()].isVisited()) {
				neighbors.add(maze.contents[current.getRow() - 1][current.getCol()]);
			}
		} catch (IndexOutOfBoundsException e) { }

		try {
			if (!maze.contents[current.getRow() + 1][current.getCol()].getIsWall() &&
					!maze.contents[current.getRow() + 1][current.getCol()].isVisited()) {
				neighbors.add(maze.contents[current.getRow() + 1][current.getCol()]);
			}
		} catch (IndexOutOfBoundsException e) { }

		try {
			if (!maze.contents[current.getRow()][current.getCol() + 1].getIsWall() &&
					!maze.contents[current.getRow()][current.getCol() + 1].isVisited()) {
				neighbors.add(maze.contents[current.getRow()][current.getCol() + 1]);
			}
		} catch (IndexOutOfBoundsException e) { }

		try {
			if (!maze.contents[current.getRow()][current.getCol() - 1].getIsWall() &&
					!maze.contents[current.getRow()][current.getCol() - 1].isVisited()) {
				neighbors.add(maze.contents[current.getRow()][current.getCol() - 1]);
			}
		} catch (IndexOutOfBoundsException e) { }
		
		
		return neighbors;
	}
}
