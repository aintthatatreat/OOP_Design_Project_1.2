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

    public Analyzer(List<Double> array){
        this.arr = array;
    }

    public void setArr(List<Double> array){
        this.arr = array;
    }

    public List<Double> getArr(){
        return this.arr;
    }

    private double mean(double[] arr){
        int s = arr.length;
        double mean = 0.0;
        for(int i = 0; i < s; i++){
            mean += arr[i];
        }
        double d = s; 
        return mean / d;
    }

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

    private double variance(double[] arr){
        double sd = standardDeviation(arr); 
        return sd * sd;
    }

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

    private double max(double[] arr){

        double curr_max = arr[0];

        for(int i = 0; i < arr.length; i++){

            if(arr[i] > curr_max){

                curr_max = arr[i];
            }
        }

        return curr_max;
    }

    private double min(double[] arr){

        double curr_min = arr[0];

        for(int i = 0; i < arr.length; i++){
            if(arr[i] < curr_min){
                curr_min = arr[i];
            }
        }

        return curr_min;
    }

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
    

    public void analyze(){
        
        arr = getArr();

        double[] array = new double[arr.size()];

        for(int i = 0; i < array.length; i++){
            array[i] = arr.get(i);
        }

        Arrays.sort(array);

        double average = mean(array);
        double med = median(array);
        List<Object> mode_and_count = mode(array);
        double maximum = max(array);
        double minimum = min(array);
        double sd = standardDeviation(array);
        double var = variance(array);

        String av = "Mean: " + average + "\n";
        String me = "Median: " + med + "\n";
        String mo = "Mode: " + mode_and_count.get(1) + " with a count of " + mode_and_count.get(0) + "\n";
        String ma = "Maximum: " + maximum + "\n";
        String mi = "Minimum: " + minimum + "\n";
        String stand = "Standard Deviation: " + sd + "\n";
        String va = "Variance: " + var + "\n";

        String tot = array + "\n" + av + me + mo + ma+ mi + stand + va;

        writeUsingOutputStream(tot);
    }
}
