import java.io.*;
import java.util.Scanner;

public class FileHandlingUtility {

    static String fileName = "sample.txt";

    // Method to write to a file
    public static void writeFile() {
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write("Hello! This is a file handling program in Java.\n");
            writer.write("This file is created and written using FileWriter.");
            writer.close();
            System.out.println("File written successfully.");
        } catch (IOException e) {
            System.out.println("Error while writing file.");
        }
    }

    // Method to read from a file
    public static void readFile() {
        try {
            File file = new File(fileName);
            Scanner sc = new Scanner(file);
            System.out.println("Reading file content:");
            while (sc.hasNextLine()) {
                System.out.println(sc.nextLine());
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    // Method to modify (append) a file
    public static void modifyFile() {
        try {
            FileWriter writer = new FileWriter(fileName, true);
            writer.write("\nThis line is added later (file modified).");
            writer.close();
            System.out.println("File modified successfully.");
        } catch (IOException e) {
            System.out.println("Error while modifying file.");
        }
    }

    public static void main(String[] args) {
        writeFile();
        readFile();
        modifyFile();
        readFile();
    }
}
