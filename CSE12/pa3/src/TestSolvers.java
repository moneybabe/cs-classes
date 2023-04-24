import java.util.ArrayList;

/*
 * Write your JUnit tests here
 * Use the formatMaze() method to get nicer JUnit output
 */

import org.junit.Test;
import static org.junit.Assert.*;

public class TestSolvers {

	/* Helper method to compare two mazes */
	public void checkMaze(SearchWorklist wl, Maze startMaze, String[] expected) {
		Square s = MazeSolver.solve(startMaze, wl);
		if(expected == null) { assertNull(s); }
		else {
			ArrayList<Square> sp = startMaze.storePath();
			String actualStr = formatMaze(startMaze.showSolution(sp));
			String expectedStr = formatMaze(expected);
			assertEquals(expectedStr, actualStr);
		}
	}	

	/* Helper method to format String[] output as String */
	public String formatMaze(String[] arr) {
		String result = "";
		for (String s: arr)
			result += "\n"+s;
		return (result+"\n");
	}

	/* Add your own Worklist tests below */

	/* ************** HINT ******************** 
	 * Use the helper methods to create simple
	 * tests that are easier to debug. 
	 */
	

	@Test
	public void defaultTest() {
		checkMaze(new StackWorklist(), 
					new Maze(new String[] {"#___", "__F_", "S##_", "____"}), 
					new String[] {"#___", "__F*", "S##*", "****"});
				
		checkMaze(new QueueWorklist(), 
					new Maze(new String[] {"#___", "__F_", "S##_", "____"}), 
					new String[] {"#___", "**F_", "S##_", "____"});

		checkMaze(new StackWorklist(),
					new Maze(new String[] {"#_#_", "____", "_##S", "F___"}),
					new String[] {"#_#_", "____", "_##S", "F***"});

		checkMaze(new QueueWorklist(),
					new Maze(new String[] {"#_#_", "____", "_##S", "F___"}),
					new String[] {"#_#_", "____", "_##S", "F***"});
	}

	@Test
	public void testNoSolution() {
		checkMaze(new StackWorklist(),
					new Maze(new String[] {"#_#_", "___#", "_##S", "F__#"}),
					null);

		checkMaze(new QueueWorklist(),
					new Maze(new String[] {"#_#_", "___#", "_##S", "F__#"}),
					null);
	}

	@Test
	public void testNoWall() {
		checkMaze(new StackWorklist(),
					new Maze(new String[] {"____", "S___", "___F", "____"}),
					new String[] {"____", "S***", "___F", "____"});

		checkMaze(new QueueWorklist(),
					new Maze(new String[] {"____", "S___", "___F", "____"}),
					new String[] {"____", "S___", "***F", "____"});
	}
}



