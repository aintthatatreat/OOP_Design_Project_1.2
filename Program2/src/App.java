package App;

import java.io.IOException;


public class App {
    public static void main(String[] args){
        Processor processor = new Processor();

        do{
            try{

                processor.read();

            }catch(IOException e){

                System.out.println("Error reading console");
                e.printStackTrace();

            }
            processor.clean();
            processor.sortArr();
            processor.palindrome();
            processor.print();
        }
        while(!(processor.getInput().isEmpty()));
        processor = null;
    }
}
