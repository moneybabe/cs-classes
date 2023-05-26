/**
 * This file contains a class that represents a file in a file system.
 * It has the following instance variables:
 * name: the name of the file
 * dir: the directory of the file
 * lastModifiedDate: the last modified date of the file
 */

 public class FileData {

    public String name;
    public String dir;
    public String lastModifiedDate;

    public FileData(String name, String directory, String modifiedDate) {
        this.name = name;
        this.dir = directory;
        this.lastModifiedDate = modifiedDate;
    }

    public String toString() {
        return "{Name: " + this.name + ", " +
                "Directory: " + this.dir + ", " + 
                "Modified Date: " + this.lastModifiedDate + "}";
    }
}
