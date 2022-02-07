package fr.epita.titanic.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import fr.epita.titanic.datamodel.Passenger;

public class PassengerCSVReader {


     public List<Passenger> read(File file){
          List<Passenger> passengers = new ArrayList<>();
          try {
               List<String> lines = Files.readAllLines(file.toPath());
               for (String line: lines){
                    line = line.replaceAll(",",";");
                    line =   line.replaceAll("; ", ", ");
                    String[] split = line.split(";");

                    Passenger instance = new Passenger();
                    instance.setPassengerId(Integer.parseInt(split[0]));
                    instance.setpClass(Integer.parseInt(split[1]));
                    instance.setName(split[2]);
                    instance.setSex(split[3]);
                    instance.setAge(Double.parseDouble(split[4]));
                    //to be completed

                    passengers.add(instance);
               }
          } catch (IOException e) {
               e.printStackTrace();
          }


          return passengers;
     }
}
