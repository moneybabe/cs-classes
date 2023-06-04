/**
 * @author Neo Lee, yl003@ucsd.edu
 * This file contains the test cases for FileSystem.java,
 * all inside the class FileSystemTest.
 */
import static org.junit.Assert.*;

import org.junit.*;

/**
 * This class contains unit tests for FileSystem.java.
 */
public class FileSystemTest {
    @Test
    public void testAdd() {
        FileSystem fs = new FileSystem();
        for (int i = 0; i < 100; i++) {
            fs.add("filename" + i, "dir" + i, "date" + i);
        }
        assertEquals(100, fs.nameTree.size());
        assertEquals(100, fs.dateTree.size());
    }

    @Test
    public void testAddDuplicate() {
        FileSystem fs = new FileSystem();
        for (int i = 0; i < 100; i++) {
            fs.add("filename" + i, "dir" + i, "date" + i);
        }
        fs.add("filename0", "dir0", "date0");
        assertEquals(100, fs.nameTree.size());
        assertEquals(100, fs.dateTree.size());
    }

    @Test
    public void testAddNull() {
        FileSystem fs = new FileSystem();
        for (int i = 0; i < 100; i++) {
            fs.add("filename" + i, "dir" + i, "date" + i);
        }
        fs.add(null, "dir", "date");
        fs.add("filename", null, "date");
        fs.add("filename", "dir", null);
        assertEquals(100, fs.nameTree.size());
        assertEquals(100, fs.dateTree.size());
    }

    @Test
    public void testFindFileNamesByDate() {
        FileSystem fs = new FileSystem();
        for (int i = 0; i < 100; i++) {
            fs.add("filename" + i, "dir" + i, "date" + i);
        }
        assertEquals(1, fs.findFileNamesByDate("date0").size());
        assertEquals(1, fs.findFileNamesByDate("date99").size());
        assertEquals(0, fs.findFileNamesByDate("date100").size());
    }

    @Test
    public void testFindFileNamesByDateNull() {
        FileSystem fs = new FileSystem();
        for (int i = 0; i < 100; i++) {
            fs.add("filename" + i, "dir" + i, "date" + i);
        }
        assertEquals(null, fs.findFileNamesByDate(null));
    }

    @Test
    public void testFindFileNamesByDateEmpty() {
        FileSystem fs = new FileSystem();
        for (int i = 0; i < 100; i++) {
            fs.add("filename" + i, "dir" + i, "date" + i);
        }
        assertEquals(0, fs.findFileNamesByDate("").size());
    }

    @Test
    public void testFilterByDate() {
        FileSystem fs = new FileSystem();
        for (int i = 0; i < 10; i++) {
            fs.add("filename" + i, "dir" + i, "date" + i);
        }
        assertEquals(5, fs.filter("date0", "date5").nameTree.size());
        assertEquals(5, fs.filter("date0", "date5").dateTree.size());
    }
    
    @Test
    public void testFilterByDateEmptyReturn() {
        FileSystem fs = new FileSystem();
        for (int i = 0; i < 10; i++) {
            fs.add("filename" + i, "dir" + i, "date" + i);
        }
        assertEquals(0, fs.filter("date5", "date0").nameTree.size());
        assertEquals(0, fs.filter("date5", "date0").dateTree.size());
        assertEquals(0, fs.filter("", "").nameTree.size());
        assertEquals(0, fs.filter("", "").dateTree.size());
        assertEquals(0, fs.filter("A", "Z").dateTree.size());
    }

    @Test
    public void testFilterByWildCard() {
        FileSystem fs = new FileSystem();
        for (int i = 0; i < 10; i++) {
            fs.add("filename" + i, "dir" + i, "date" + i);
        }
        assertEquals(10, fs.filter("filename").nameTree.size());
        assertEquals(10, fs.filter("filename").dateTree.size());
        assertEquals(1, fs.filter("5").nameTree.size());
        for (int i = 0; i < 10; i++) {
            fs.add("random" + i, "dir" + i, "date" + i);
        }
        assertEquals(10, fs.filter("random").nameTree.size());
        assertEquals(10, fs.filter("random").dateTree.size());
        assertEquals(2, fs.filter("5").nameTree.size());
    }

    @Test
    public void testFilterByWildCardEmptyReturn() {
        FileSystem fs = new FileSystem();
        for (int i = 0; i < 10; i++) {
            fs.add("filename" + i, "dir" + i, "date" + i);
        }
        assertEquals(0, fs.filter("A").nameTree.size());
        assertEquals(0, fs.filter("filename12").dateTree.size());
    }

    @Test
    public void testOutputNameTree() {
        FileSystem fs = new FileSystem();
        for (int i = 0; i < 10; i++) {
            fs.add("filename" + i, "dir" + i, "date" + i);
        }
        assertEquals(10, fs.outputNameTree().size());
    }

    @Test
    public void testOutputNameTreeDuplicate() {
        FileSystem fs = new FileSystem();
        for (int i = 0; i < 10; i++) {
            fs.add("filename", "dir" + i, "date" + i);
        }
        assertEquals(1, fs.outputNameTree().size());
    }

    @Test
    public void testOutputDateTree() {
        FileSystem fs = new FileSystem();
        for (int i = 0; i < 10; i++) {
            fs.add("filename" + i, "dir" + i, "date");
        }
        assertEquals(10, fs.outputDateTree().size());
    }

    @Test
    public void testOutputDateTreeDuplicate() {
        FileSystem fs = new FileSystem();
        for (int i = 0; i < 10; i++) {
            fs.add("filename" + i, "dir" + i, "date");
        }
        assertEquals(10, fs.outputDateTree().size());
    }
}