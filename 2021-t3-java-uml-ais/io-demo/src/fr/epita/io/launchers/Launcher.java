package fr.epita.io.launchers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Launcher {

    public static void main(String[] args) throws IOException {
        File file = new File("./io-demo/test.csv");

        // 1- display all the lines in the file
        List<String> lines = Files.readAllLines(file.toPath());
        Map<String, String> map = new LinkedHashMap<>();
        lines.remove(0);
        for(String line : lines){
            // 2- lineParts the lines on the separator (";") using the String.lineParts() method
            String[] lineParts = line.split(";");
            if (lineParts.length > 1){
                map.put(lineParts[0], lineParts[1]);
            }
        }

        System.out.println(map);
        System.out.println(map.get("java"));




    }
}
