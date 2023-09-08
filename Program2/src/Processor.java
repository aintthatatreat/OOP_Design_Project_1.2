package App;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;


public class Processor {

    private String input;
    // String to hold console input

    public Processor(){
        this.input = "";
    }
    // Default constructor

    public Processor(String s){
        this.input = s;
    }
    // Parameterized constructor

    public void setInput(String s){
        this.input = s;
    }
    // Setter method for console input (In case I need it later)

    public String getInput(){
        return this.input;
    }
    // Getter method for console input

    public void read() throws IOException{

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // Object which reads console input

        System.out.println("Please input a string to be palindrome'd and sorted:");

        this.input = reader.readLine();
        // Reading line to 'input' variable

        reader = null;
        // Deleteing reader to prevent memory leaks (I don't know if this is necessary but I know Java can be prone to memory leaks
        // and this will get picked up by the garbage collector)
    }

    public void clean(){

        this.input = (this.input.replaceAll("\\s", "")).toUpperCase();
        // removing all spaces from string and making it uppercase
    }

    public void sortArr(){

        char[] temp = this.input.toCharArray();
        Arrays.sort(temp);
        this.input = new String(temp);
        //this.input = temp;
        // Sorting letters by converting string to an array of characters and then sorting them based on UTF-8 encoding (So I would assume)
        // before constructing a new string object from that sorted array.
    }

    public void palindrome(){

        String temp = "";
        // Temporary string that is used to hold palindrome as it is getting built.

        for(int i = this.input.length() - 1; i > 0; i--){
            temp = temp + this.input.charAt(i);
        }
        // Constructing palindrome

        this.input = temp + this.input;
    }
    // Constructs the palindrome by moving through the sorted string backwards, adding each element to the end of the 'temp' string,
    // before stopping before the 'last' (first) letter of the input string. Then adds the sorted string to the end of the constructed string
    // to create the palindrome. 

    public void print(){
        System.out.println(this.input);
    }
    // Just prints the resulting string to console, nothing special


}
