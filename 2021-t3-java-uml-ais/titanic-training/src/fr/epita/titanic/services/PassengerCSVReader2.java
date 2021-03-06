package fr.epita.titanic.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import fr.epita.titanic.datamodel.Passenger;

public class PassengerCSVReader2 {


   public static final Function<List<String>, Passenger> functionForTest = strings -> {
       Passenger instance = new Passenger();
       instance.setPassengerId(Integer.parseInt(strings.get(0)));
       instance.setpClass(Integer.parseInt(strings.get(1)));
       instance.setName(strings.get(2));
       instance.setSex(strings.get(3));
       String s = strings.get(4);
       instance.setAge(Double.parseDouble(checkForNull(s)));
       return instance;

   };
    public static final Function<List<String>, Passenger> functionForTrain = new Function<List<String>, Passenger>() {
        @Override
        public Passenger apply(List<String> strings) {
            Passenger instance = new Passenger();
            instance.setPassengerId(Integer.parseInt(strings.get(0)));
            instance.setSurvived(Integer.parseInt(strings.get(1)));
            instance.setpClass(Integer.parseInt(strings.get(2)));
            instance.setName(strings.get(3));
            instance.setSex(strings.get(4));
            String s = strings.get(5);
            instance.setAge(Double.parseDouble(checkForNull(s)));
            return instance;

        }
    };



    public List<Passenger> readPassengers(File file, Function<List<String>, Passenger> extractionFunction) throws IOException {

        //given
        List<String> lines = Files.readAllLines(file.toPath());
        List<String> errorLines = new ArrayList<>();

        //when
        GenericCSVReader csvReader = new GenericCSVReader(",","\"");
        lines.remove(0);
        List<Passenger> passengers = new ArrayList<>();
        for (String line : lines){
            try {
                List<String> strings = csvReader.extractColumnValues(checkForNull(line));
                passengers.add(extractionFunction.apply(strings));
                //to be completed
            }catch (Exception e){
                errorLines.add(checkForNull(line));
            }
        }

        return passengers;


    }

    private static String checkForNull(String s) {
        if (s == null){
            return "";
        }
        return s;
    }

    private String checkForNullNumber(String s) {
        if (s == null){
            return "0";
        }
        return s;
    }


}
