import static org.junit.Assert.*;

import java.io.File;

import org.junit.*;

public class FileSystemTest {

    @Test 
    public void testAdd() {
        FileSystem fs = new FileSystem();
        fs.add(null,  null, null);
        assertEquals(1, fs.nameMap.size());
        assertEquals(1, fs.dateMap.size());
        for (int i = 0; i < 10; i++) {
            fs.add("name" + i, "dir" + i, "date" + i);
        }
        assertEquals(11, fs.nameMap.size());
        assertEquals(11, fs.dateMap.size());
    }

    @Test
    public void testFindFilesInMultDir() {
        FileSystem fs = new FileSystem();
        for (int i = 0; i < 10; i++) {
            fs.add("name", "dir" + i, "date");
        }
        assertEquals(10, fs.findFilesInMultDir("date").size());
        for (int i = 0; i < 10; i++) {
            fs.add("name" + i, "dir" + i, "date"+ i);
        }
        assertEquals(10, fs.findFilesInMultDir("date").size());
    }

    @Test
    public void testRemoveByName() {
        FileSystem fs = new FileSystem();
        for (int i = 0; i < 10; i++) {
            fs.add("name" + i, "dir" + i, "date" + i);
        }
        assertEquals(10, fs.nameMap.size());
        assertEquals(10, fs.dateMap.size());
        assertTrue(fs.removeByName("name5"));
        assertEquals(9, fs.nameMap.size());
        fs.removeByName("name5");
        assertEquals(9, fs.nameMap.size());
        
    }
}
