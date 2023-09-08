package App;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.Math;
import java.io.FileOutputStream;
import java.io.File;

public class Analyzer {

    private List<Double> arr;
    // List object to store user inputs

    public Analyzer(List<Double> array){
        this.arr = array;
    }
    // Constructor

    public void setArr(List<Double> array){
        this.arr = array;
    }
    // Setter for arr

    public List<Double> getArr(){
        return this.arr;
    }
    // Getter for arr

    private double mean(double[] arr){

        int s = arr.length;
        double mean = 0.0;
        for(int i = 0; i < s; i++){
            mean += arr[i];
        }
        double d = s; 
        return mean / d;
    }
    // Using a variable to store a running total which will then be divided by the length of the input array

    private double standardDeviation(double[] arr){

        double average = mean(arr);
        int s = arr.length;

        double running_total = 0.0;

        for(int i = 0; i < s; i++){
            running_total += Math.abs(arr[i] - average);
        }

        double d = s; 

        return running_total / d;
        
    }
    // As above, except it depends on the mean() method to run the standard deviation calculation.

    private double variance(double[] arr){
        double sd = standardDeviation(arr); 
        return sd * sd;
    }
    // Depends on the StandardDeviation() method to calculate variance (squares it)

    private double median(double[] arr){
        int s = arr.length;

        if((s % 2) == 0){
            int d = s / 2;

            double value1 = arr[d-1];
            double value2 = arr[d];

            return (value1 + value2) / 2.0;
        }

        int d = s/2;

        return arr[d];
    }
    // Takes median by having two different cases. In the case where there are an even number of elements in the array, 
    // this will take the average of the middle two entries. In the case where there are an odd number of elements,
    // this will simple take the middle element by dividing the size of the array by two and rounding down. 

    private List<Object> mode(double[] arr){

        HashMap<Double, Integer> hmap = new HashMap<Double, Integer>();

        int curr_max = 1;
        double max_value = arr[0];

        for(int i = 0; i < arr.length; i++){

            if(hmap.get(arr[i]) != null){
                hmap.put(arr[i], hmap.get(arr[i]) + 1);

                if(hmap.get(arr[i]) > curr_max){
                    curr_max = hmap.get(arr[i]);
                    max_value = arr[i];
                }
            }
            else{
                hmap.put(arr[i], 1);
            }

        }

        hmap = null;

        return Arrays.asList(curr_max, max_value); 


    }
    // This method takes the mode of the input array. It does this by creating a hashmap which will contain the frequency of each element in the array. 
    // After that, we take the maximum count element in the hashmap and return both that and the associated frequency.
    // We use the hashmap object because it is much faster to access each element inside compared to an array. 
    //(Also it behaves like a dictionary, which is what we need)

    private double max(double[] arr){

        double curr_max = arr[0];

        for(int i = 0; i < arr.length; i++){

            if(arr[i] > curr_max){

                curr_max = arr[i];
            }
        }

        return curr_max;
    }
    // This just loops through the list of given numbers to find the maximum using a temporary max which all others are compared to

    private double min(double[] arr){

        double curr_min = arr[0];

        for(int i = 0; i < arr.length; i++){
            if(arr[i] < curr_min){
                curr_min = arr[i];
            }
        }

        return curr_min;
    }
    // This also loops through all elements of the array to using a temporary minimum variable to compare to all other elements. 

    private static void writeUsingOutputStream(String data) {
        OutputStream os = null;
        try {
            os = new FileOutputStream(new File("/Users/vernonwalker/Desktop/All Other Classes/Object Oriented Programming/Project_1.2_Vernon_Walker/Results.txt"));
            os.write(data.getBytes(), 0, data.length());
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // This file writing system was found via https://www.digitalocean.com/community/tutorials/java-write-to-file
    // Used FilerOutputStream object to print to the file 'results'. To be clear, the results file is just a temporary file that is used before I write the 
    // the 'Results1' file used for grading. 
    

    public void analyze(){
        
        arr = getArr();
        // Gets the array (could have used this.arr but whatever the outcome is the same)

        double[] array = new double[arr.size()];
        // Array used to fit the converted List<Double> to just a double[] array. 

        for(int i = 0; i < array.length; i++){
            array[i] = arr.get(i);
        }
        // Puts in each element from the list to the array. 

        Arrays.sort(array);
        // Sorts the elements in the array so it actually works with some of the following methods (primarily median)

        double average = mean(array);
        // Take the average
        double med = median(array);
        // Take the median
        List<Object> mode_and_count = mode(array);
        // Take the mode and the count for the mode
        double maximum = max(array);
        // Take the maximum element
        double minimum = min(array);
        // Take the minimum element
        double sd = standardDeviation(array);
        // Take the standard deviation
        double var = variance(array);
        // Take the variance

        String av = "Mean: " + average + "\n";
        String me = "Median: " + med + "\n";
        String mo = "Mode: " + mode_and_count.get(1) + " with a count of " + mode_and_count.get(0) + "\n";
        String ma = "Maximum: " + maximum + "\n";
        String mi = "Minimum: " + minimum + "\n";
        String stand = "Standard Deviation: " + sd + "\n";
        String va = "Variance: " + var + "\n";
        // This part makes all of those previous calculations into strings so that they can be printed to the 'Results.txt' file. 

        String tot = array + "\n" + av + me + mo + ma+ mi + stand + va;
        // Adds all the strings into one. 

        writeUsingOutputStream(tot);
        // Outputs those strings. 
    }
}
