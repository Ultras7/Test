package epam.text.write;


import java.io.FileWriter;
import java.io.IOException;


public class Writer {


    public void writeTXT(String symbol, String path){
        try (FileWriter fileWriter = new FileWriter(path)) {

            for (int i = 0; i < symbol.length(); i++) {
                fileWriter.append(symbol.charAt(i));
            }
            } catch (IOException e){
            System.out.println("Can't write a file");
        }
    }
}
