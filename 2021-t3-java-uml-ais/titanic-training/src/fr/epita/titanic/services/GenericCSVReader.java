package fr.epita.titanic.services;

import java.util.ArrayList;
import java.util.List;

public class GenericCSVReader {

    private String delim;
    private String escapeChar;



    public String processColumn(String line, String startDelim, String endDelim) {
        String content = "";
        int i = 0;
        if (line.startsWith(startDelim)){
            line = line.substring(startDelim.length());
        }
        while (!content.endsWith(endDelim) && i <= line.length() - 1) {
            content += line.charAt(i);
            i++;
        }
        content = content.replaceAll(startDelim, "");
        content = content.replaceAll(endDelim, "");
        return content;
    }


    public GenericCSVReader(String delim, String escapeChar) {
        this.delim = delim;
        this.escapeChar = escapeChar;
    }

    public List<String> extractColumnValues(String line) {
        List<String> columnsValues = new ArrayList<>();

        //while we have something to process
        while (!line.isEmpty()){

            //by default, initialize start delimiter to default delimiter
            String startDelim = delim;
            String endDelim = delim;
            int escapeCharLength = 0;
            //if the line doesn't start with the delimiter, then we are at the beginning of the process, there's no leading start delimiter
            if (!line.startsWith(delim)){
                startDelim = "";
            }
            //if the line starts with an escape char (we are athe beginning of the process, and the first column is an escaped column)
            if (line.startsWith(escapeChar)){
                startDelim = escapeChar;
                endDelim = escapeChar + delim;
                escapeCharLength = escapeChar.length();
            }
            //if the line starts with delim + escape char (in the middle escaped column)
            if (line.startsWith(delim + escapeChar)) {
                startDelim = delim + escapeChar;
                endDelim = escapeChar + delim;
                escapeCharLength = escapeChar.length();
            }
            //we process the column with the required start and end delim
            String currentColumn = processColumn(line, startDelim, endDelim);

            //we removed the processed content from the current line (allows to not manage offsets)
            line = line.substring(currentColumn.length() + startDelim.length() + escapeCharLength);

            //save current column value in the columnValues list
            columnsValues.add(currentColumn);
        }
        return columnsValues;
    }
}
