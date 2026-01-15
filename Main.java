import java.io.*;
import java.util.Scanner;

 class Main {

    static String fileName = "sample.txt";

    public static void main(String[] args) {
        writeFile();
        readFile();
    }

    // Method to write to a file
    public static void writeFile() {
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write("Hello!\n");
            writer.write("This file is created using Java File Handling.\n");
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
}
