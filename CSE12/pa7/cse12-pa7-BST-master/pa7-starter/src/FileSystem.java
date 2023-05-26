/**
 * This file contains an implementation of a file system in class FileSystem
 * that stores files in a BST.
 */
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This is an implementation of a file system that stores files in a BST.
 * It has the following instance variables:
 * nameTree: a BST that maps file names to a list of files with the same name
 * dateTree: a BST that maps modified dates to a list of files with the same date
 * It has the following methods:
 * add: adds a file to the file system
 * findFileNamesByDate: finds the file names that were last modified on the given date
 * findFileNamesByName: finds the file names that have the given name
 * filter(overload): finds the file within a certain range of dates
 * filter(overload): finds the file that contains a certain name pattern
 * outputNameTree: outputs the nameTree in a list
 * outputDateTree: outputs the dateTree in a list
 */
public class FileSystem {

    BST<String, FileData> nameTree;
    BST<String, ArrayList<FileData>> dateTree;
    public FileSystem() {
        this.nameTree = new BST<>();
        this.dateTree = new BST<>();
    }
    public FileSystem(String inputFile) {
    	// Add your code here
        this.dateTree = new BST<>();
        this.nameTree = new BST<>();
        try {
            File f = new File(inputFile);
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String[] data = sc.nextLine().split(", ");
                // Add your code here
                this.add(data[0], data[1], data[2]);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);

        }
    }


    /**
     * Adds a file to the file system
     * It replace the old file if the new file is more recent
     * If the new file is unique, it is added to the file system
     * @param name the name of the file
     * @param dir the directory of the file
     * @param date the date the file was last modified
     */
    public void add(String name, String dir, String date) {
    	if (name == null || dir == null || date == null) { return; }
        FileData newFile = new FileData(name, dir, date);
    	
        // If name already exists, check if new file is more recent
        if (this.nameTree.containsKey(name)) {
            FileData oldFile = this.nameTree.get(name);

            // If new file is more recent, update
            if (newFile.lastModifiedDate.compareTo(oldFile.lastModifiedDate) > 0) {
                
                // Update nameTree
                this.nameTree.set(name, newFile);

                // Update dateTree
                ArrayList<FileData> dateArr = this.dateTree.get(oldFile.lastModifiedDate);
                dateArr.remove(oldFile);
                if (dateArr.size() == 0) {
                    this.dateTree.remove(oldFile.lastModifiedDate);
                } else {
                    this.dateTree.set(oldFile.lastModifiedDate, dateArr);
                }
            }
        } else {
            this.nameTree.set(name, newFile);
            
            // Update dateTree
            if (this.dateTree.containsKey(newFile.lastModifiedDate)) {
                ArrayList<FileData> dateArr = this.dateTree.get(newFile.lastModifiedDate);
                dateArr.add(newFile);
                this.dateTree.set(newFile.lastModifiedDate, dateArr);
            } else {
                ArrayList<FileData> dateArr = new ArrayList<>();
                dateArr.add(newFile);
                this.dateTree.set(newFile.lastModifiedDate, dateArr);
            }
        }

    }

    /**
     * Find the file names that were last modified on the given date
     * @param date
     * @return a list of file names that were last modified on the given date
     */
    public ArrayList<String> findFileNamesByDate(String date) {
        if (date == null) { return null; }
        ArrayList<String> result = new ArrayList<>();
        if (!this.dateTree.containsKey(date)) { return result; }

        ArrayList<FileData> dateArr = this.dateTree.get(date);
        for (FileData file : dateArr) {
            result.add(file.name);
        }

        return result;
    }

    /**
     * Find the files that have last modified dates between the given start and end dates
     * @param date
     * @return a file system that contains the files that have 
     * last modified dates between the given start and end dates
     */
    public FileSystem filter(String startDate, String endDate) {
        List<String> dates = this.dateTree.keys();
        FileSystem filteredFileSystem = new FileSystem();
        for (String date : dates) {
            if (date.compareTo(startDate) >= 0 && date.compareTo(endDate) < 0) {
                ArrayList<FileData> dateArr = this.dateTree.get(date);
                for (FileData file : dateArr) {
                    filteredFileSystem.add(file.name, file.dir, file.lastModifiedDate);
                }
            }
        }
        return filteredFileSystem;
    }
    
    /**
     * Find the files that have names that contain the given wild card
     * @param wildCard
     * @return a file system that contains the files that have 
     * names that contain the given wild card
     */
    public FileSystem filter(String wildCard) {
        List<String> names = this.nameTree.keys();
        FileSystem filteredFileSystem = new FileSystem();
        for (String name : names) {
            if (name.contains(wildCard)) {
                FileData file = this.nameTree.get(name);
                filteredFileSystem.add(file.name, file.dir, file.lastModifiedDate);
            }
        }
        return filteredFileSystem;
    }
    
    /**
     * Output the file system in the following format:
     * name: {name, dir: dir, date: date}
     * @return a list of strings that represent the file system in ascending order by name
     */
    public List<String> outputNameTree(){
        List<String> names = this.nameTree.keys();
        List<String> result = new ArrayList<>();
        for (String name : names) {
            FileData file = this.nameTree.get(name);
            result.add(name + ": " + file.toString());
        }
        return result;
    }
    
    /**
     * Output the file system in the following format:
     * date: {name, dir: dir, date: date}
     * @return a list of strings that represent the file system in ascending order by date
     */
    public List<String> outputDateTree(){
        List<String> dates = this.dateTree.keys();
        List<String> result = new ArrayList<>();
        for (int i = dates.size() - 1; i >= 0; i--) {
            String date = dates.get(i);
            ArrayList<FileData> dateArr = this.dateTree.get(date);
            for (int j = dateArr.size() - 1; j >= 0; j--) {
                FileData file = dateArr.get(j);
                result.add(date + ": " + file.toString());
            }
        }
        return result;
    }
    

}

