package fr.epita.titanic.tests;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.epita.titanic.datamodel.Passenger;

public class TestCSVReadingWithRegex {

    public static void main(String[] args) {
        String line = "892,3,\"Kelly, , Mr. James\",male,34.5,0,0,330911,7.8292,,Q";

        line = line.replaceAll(",",";");
        line =   line.replaceAll("; ", ", ");
        String[] split = line.split(";");

        Passenger instance = new Passenger();
        instance.setPassengerId(Integer.parseInt(split[0]));
        instance.setpClass(Integer.parseInt(split[1]));
        instance.setName(split[2]);
        instance.setSex(split[3]);
        instance.setAge(Double.parseDouble(split[4]));

        //To be continued

        /*
        Pattern pattern = Pattern.compile("your regex");
        Matcher matcher = pattern.matcher(line);
        matcher.group();
        */


        System.out.println(Arrays.asList(split));



    }

}
