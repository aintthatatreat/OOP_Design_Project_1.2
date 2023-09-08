
package App;

import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Please give your string of numbers:\n");
        Reader reader = new Reader();
        List<Double> arr = reader.ReadInputs();
        Analyzer analyzer = new Analyzer(arr);
        analyzer.analyze();
        reader = null;
        analyzer = null;
    }
}
