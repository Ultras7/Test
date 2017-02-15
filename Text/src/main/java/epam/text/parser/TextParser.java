package epam.text.parser;

import epam.text.part.CompositePart;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextParser {


    private static final String REGEX_PARAGRAPH = "(\\s*(.+))([^(\\s*([^\\t]+)\\s)])|\\s*([^\\t]+)";
    private static final String REGEX_SENTENCE = "([^(\\.|!|\\?)]+)(\\.|!|\\?)";
    private static final String REGEX_WORD = "([^(\\s)]*)(\\s)*";
    public static final String REGEX_SYMBOL = ".{1}";
    public static final String REGEX_WORD_AND_SIGN = "([\\.,!\\?:;@]{1})|([^\\.,!\\?:;@]*)";


    public CompositePart parse(String path) {
        String text = initialization(path);
        CompositePart wholeText = new CompositePart();
        wholeText = parseToParagraph(wholeText, text);
        return wholeText;
    }


    private String initialization(final String path) {
        String text = "";
        try {
            FileInputStream inFile = new FileInputStream(path);
            byte[] str = new byte[inFile.available()];
            inFile.read(str);
            text = new String(str); // String with all text
            System.out.println(text);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

    private CompositePart parseToParagraph(CompositePart wholeText, String text) {
        Pattern patternParagraph = Pattern.compile(REGEX_PARAGRAPH);
        String paragraph = "";
        Matcher matcher = patternParagraph.matcher(text);
        while (matcher.find()) {
            CompositePart paragraphList = new CompositePart();
            paragraph = matcher.group();
            paragraphList = parseToSentense(paragraphList, paragraph);
            wholeText.addElement(paragraphList);
        }
        return wholeText;
    }


    private CompositePart parseToSentense(CompositePart paragraphList, String paragraph) {
        Pattern patternSentense = Pattern.compile(REGEX_SENTENCE);
        Matcher matcher = patternSentense.matcher(paragraph);
        String sentense = "";
        while (matcher.find()) {
            CompositePart sentenseList = new CompositePart();
            sentense = matcher.group();
            sentenseList = parseToWord(sentenseList, sentense);
            paragraphList.addElement(sentenseList);
        }
        return paragraphList;
    }

    private CompositePart parseToWord(CompositePart sentenceList,String sentence) {
        // parse to word
        Pattern pattertWord = Pattern.compile(REGEX_WORD);
        String word = "";
        Matcher matcher = pattertWord.matcher(sentence);
        while (matcher.find()) {
            CompositePart wordList = new CompositePart();
            word = matcher.group();
            //System.out.print(word);
            wordList = parseToSignAndWord(wordList, word);
            sentenceList.addElement(wordList);
        }
        return sentenceList;
    }

    private CompositePart parseToSignAndWord(CompositePart wordList, String word) {
        // parse to sign and word
        Pattern pattern = Pattern.compile(REGEX_WORD_AND_SIGN);
        String wordSign = "";
        Matcher matcher = pattern.matcher(word);
        CompositePart wordSignList = new CompositePart();
        while (matcher.find()) {
            wordSign = matcher.group();
            wordSignList = parseToSymbol(wordSignList, wordSign);
            wordList.addElement(wordSignList);
        }
        return wordSignList;
    }

    private CompositePart parseToSymbol(CompositePart wordSignList, String wordSign) {
        char[] chars = wordSign.toCharArray();
        for (char ch : chars) {
            Symbol symbol = new Symbol(ch);
            wordSignList.addElement(symbol);
        }
        return wordSignList;
    }


}
