package App;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Reader {

    public Reader(){

    }

    static boolean isNumeric(String strNum) {

        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    // 'isNumeric' method is implemented from this link: https://www.baeldung.com/java-check-string-number
    
    public List<Double> ReadInputs() throws IOException{

        List<Double> arr = new ArrayList<Double>();
        // Declare a list object while will be the array which we use to store inputs and return

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // Declare the reader object so that we can get inputs from console

        String input = "";
        // input string

        do {

            input = reader.readLine();
            // read the line line in from console

            if(input.isEmpty()){
                continue;
            }

            if(!(isNumeric(input))){
                System.out.println("Please input a valid number");
                continue;
            }
            // Check if input is a valid number so that it can be converted
        
            // Check if float
            
            Double flt_input = Double.parseDouble(input);
            // Parse the number and store it
            
            arr.add(flt_input);
            // Add the input to the list

            System.out.println(Arrays.toString(arr.toArray()));
            // Prints the current array to console after each iteration. 

        }while(!(input.isEmpty()));
        // Stops when the input string is empty

        reader = null;
        // makes reader null such that the memory can be picked up by the garbage collector

        return arr;
    }
}
