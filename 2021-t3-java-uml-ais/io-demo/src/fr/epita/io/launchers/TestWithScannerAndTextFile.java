package fr.epita.io.launchers;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class TestWithScannerAndTextFile {

    public static void main(String[] args) {
        File file = new File(".");
        System.out.println(file.getAbsolutePath());

        File testFile = new File("./io-demo/test.txt");

        if (testFile.exists()){
            System.out.println("file exists");
            try (Scanner scanner = new Scanner(testFile)){

                while(scanner.hasNext()){
                    String line = scanner.nextLine();
                    if (!line.isEmpty()){
                        System.out.println(line);
                    }

                }

            }catch (FileNotFoundException fnfe){
                fnfe.printStackTrace();
            }
        }
    }
}
