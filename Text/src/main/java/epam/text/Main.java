package epam.text;


import epam.text.parser.TextParser;
import epam.text.part.CompositePart;
import epam.text.write.Writer;

public class Main {


    public static void main(String[] args)  {

        String path = "input.txt";
        TextParser textParser = new TextParser();
        CompositePart wholeText = textParser.parse(path);
        String file = "output.txt";
        Writer write = new Writer();
        write.writeTXT(wholeText.toString(),file);
    }


}