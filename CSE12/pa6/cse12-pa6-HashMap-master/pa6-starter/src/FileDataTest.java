import static org.junit.Assert.*;

import org.junit.*;

public class FileDataTest {
    @Test 
    public void testFileData() {
        FileData fd = new FileData("name", "dir", "date");
        assertEquals("name", fd.name);
        assertEquals("dir", fd.dir);
        assertEquals("date", fd.lastModifiedDate);
    }

    @Test
    public void testToString() {
        FileData fd = new FileData("name", "dir", "date");
        assertEquals("{Name: name, Directory: dir, Modified Date: date}", fd.toString());
    }

    @Test
    public void testLongFile() {
        FileData fd = new FileData("name", "dir", "date");
        fd.name = "This is a very long file name";
        fd.dir = "This is a very long directory name";
        fd.lastModifiedDate = "This is a very long date";
        assertEquals("{Name: " + fd.name + 
                    ", Directory: " + fd.dir + 
                    ", Modified Date: " + fd.lastModifiedDate + "}", 
                    fd.toString());
    }
}
