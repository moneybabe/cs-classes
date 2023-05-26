/**
 * This is an implementation of a file system that stores files in a HashMap.
 * It has the following instance variables:
 * nameMap: a HashMap that maps file names to a list of files with the same name
 * dateMap: a HashMap that maps modified dates to a list of files with the same date
 */


import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileSystem {

    MyHashMap<String, ArrayList<FileData>> nameMap;
    MyHashMap<String, ArrayList<FileData>> dateMap;

    /**
     * Default constructor that initializes the instance variables
     * nameMap and dateMap
     * nameMap maps file names to a list of files with the same name
     * dateMap maps modified dates to a list of files with the same date
     */
    public FileSystem() {
        this.nameMap = new MyHashMap<>();
        this.dateMap = new MyHashMap<>();
    }


    /**
     * Constructor that initializes the instance variables by reading
     * the data from the input file
     * nameMap and dateMap
     * nameMap maps file names to a list of files with the same name
     * dateMap maps modified dates to a list of files with the same date
     * @param inputFile the name of the input file
     */
    public FileSystem(String inputFile) {
        // Add your code here
        this.nameMap = new MyHashMap<>();
        this.dateMap = new MyHashMap<>();
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
     * @return true if the file was successfully added, false otherwise
     * @param fileName the name of the file
     * @param directory the directory of the file
     * @param modifiedDate the last modified date of the file
     */
    public boolean add(String fileName, String directory, String modifiedDate) {
        FileData file;
        if (fileName == null || directory == null || modifiedDate == null) {
            file = new FileData("", "/", "01/01/2021");
        }  else {
            file = new FileData(fileName, directory, modifiedDate);
        }

        // Update nameMap
        ArrayList<FileData> nameArr;
        if (this.nameMap.containsKey(file.name)) {
            nameArr = this.nameMap.get(file.name);
            for (int i = 0; i < nameArr.size(); i++) {
                if (nameArr.get(i).dir.equals(file.dir)) {
                    return false;
                }
            }
            nameArr.add(file);
        } else {
            nameArr = new ArrayList<>();
            nameArr.add(file);
            this.nameMap.put(file.name, nameArr);
        }

        // Update dateMap
        ArrayList<FileData> dateArr = this.dateMap.get(file.lastModifiedDate);
        if (dateArr != null) {
            dateArr.add(file);
        } else {
            dateArr = new ArrayList<>();
            dateArr.add(file);
            this.dateMap.put(file.lastModifiedDate, dateArr);
        }

        return true;
    }

    /**
     * Finds a file in the file system
     * @return the file if it was found, null otherwise
     * @param name the name of the file
     * @param directory the directory of the file
     */
    public FileData findFile(String name, String directory) {
        ArrayList<FileData> nameArr = this.nameMap.get(name);
        if (nameArr == null) { return null; }   
        
        for (FileData file: nameArr) {
            if (file.dir.equals(directory)) {
                return file;
            }
        }

        return null;
    }

    /**
     * Finds all filenames in the file system 
     * @return an ArrayList of all files with the given name
     */
    public ArrayList<String> findAllFilesName() {
        if (this.nameMap.isEmpty()) { return new ArrayList<>(); }
        return (ArrayList<String>) this.nameMap.keys();
    }

    /**
     * Finds all files in the file system by name
     * @return an ArrayList of all files with the given name
     * @param name the name of the file
     */
    public ArrayList<FileData> findFilesByName(String name) {
        ArrayList<FileData> nameArr = this.nameMap.get(name);
        if (nameArr == null) { return new ArrayList<>(); }
        return nameArr;
    }

    /**
     * Finds all files in the file system by date
     * @return an ArrayList of all files with the given date
     * @param modifiedDate the last modified date of the file
     */
    public ArrayList<FileData> findFilesByDate(String modifiedDate) {
        ArrayList<FileData> dateArr = this.dateMap.get(modifiedDate);
        if (dateArr == null) { return new ArrayList<>(); }
        return dateArr;
    }

    /**
     * Finds all files in the file system that have the same name and date
     * given the date
     * @return an ArrayList of all files with the given date
     * @param modifiedDate the last modified date of the file
     */
    public ArrayList<FileData> findFilesInMultDir(String modifiedDate) {
        ArrayList<FileData> dateArr = findFilesByDate(modifiedDate);
        ArrayList<FileData> result = new ArrayList<>();
        for (FileData file: dateArr) {
            ArrayList<FileData> sameNameArr = this.findFilesByName(file.name);
            if (sameNameArr.size() > 1) {
                for (FileData sameName: sameNameArr) {
                    if (!result.contains(sameName)) {
                        result.add(sameName);
                    }
                }
            }
        }
        return result;
    }

    /**
     * Removes a file from the file system by name
     * @return true if the file was successfully removed, false otherwise
     * @param name the name of the file
     */
    public boolean removeByName(String name) {
        if (name == null) { return false; }

        ArrayList<FileData> nameArr = this.nameMap.get(name);
        if (nameArr == null) { return false; }
        this.nameMap.remove(name);
        
        ArrayList<FileData> dateArr;
        int index;
        for (FileData file: nameArr) {
            dateArr = this.dateMap.get(file.lastModifiedDate);
            index = dateArr.indexOf(file);
            if (index == -1) { continue; }
            dateArr.remove(index);
            if (dateArr.size() == 0) { this.dateMap.remove(file.lastModifiedDate); }
            else { this.dateMap.replace(file.lastModifiedDate, dateArr); }
        }
        
        return true;
    }

    /**
     * Removes a file from the file system by name and redirectory
     * @return true if the file was successfully removed, false otherwise
     * @param modifiedDate the last modified date of the file
     */
    public boolean removeFile(String name, String directory) {
        if (name == null || directory == null) { return false; }

        ArrayList<FileData> nameArr = this.nameMap.get(name);
        ArrayList<FileData> dateArr;
        int count = 0;
        if (nameArr == null) { return false; }

        for (FileData file: nameArr) {
            if (file.dir.equals(directory)) {
                dateArr = this.dateMap.get(file.lastModifiedDate);
                dateArr.remove(dateArr.indexOf(file));
                if (dateArr.size() == 0) { this.dateMap.remove(file.lastModifiedDate); }
                else { this.dateMap.replace(file.lastModifiedDate, dateArr); }    
            }
        }

        for (int i = 0; i < nameArr.size(); i++) {
            if (nameArr.get(i).dir.equals(directory)) {
                nameArr.remove(i);
                if (nameArr.size() == 0) { this.nameMap.remove(name); }
                else { this.nameMap.replace(name, nameArr); }

                count += 1;
            }
        }

        if (count == 0) { return false; }
        return true;
    }

}
