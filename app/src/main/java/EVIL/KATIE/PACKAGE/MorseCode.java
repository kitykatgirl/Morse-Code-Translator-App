package EVIL.KATIE.PACKAGE;

// Source - https://stackoverflow.com/a/65871051
// Posted by Martin
// Retrieved 2026-04-24, License - CC BY-SA 4.0

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MorseCode {

    private static final String[] english = {
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l",
            "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x",
            "y", "z", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0",
            ",", ".", "?", "!", ":", "@", "=", "-", "+", "\"", "/", "&",
            "'", "(", ")"
    };

    private static final String[] morse = {
            ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..",
            ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.",
            "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..", ".----",
            "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----.",
            "-----", "--..--", ".-.-.-", "..--..", "-.-.--", "---...", ".--.-.",
            "-...-", "-....-", ".-.-.", ".-..-.", "-..-.", ".-...", ".----.",
            "-.--.", "-.--.-"
    };

    private static final Map<String, String> EN_TO_MORSE = new HashMap<>();
    private static final Map<String, String> MORSE_TO_EN = new HashMap<>();

    static {
        for (int i = 0; i < english.length; i++) {
            EN_TO_MORSE.put(english[i], morse[i]);
            MORSE_TO_EN.put(morse[i], english[i]);
        }
    }

//    public static void main(String[] args) {
//
//        String output;
//
//        output = MorseCode.run(false, "Hello, World!");
//        System.out.println(output); // .... . .-.. .-.. --- --..-- / .-- --- .-. .-.. -.. -.-.--
//
//        output = MorseCode.run(true, ".... . .-.. .-.. --- --..-- / .-- --- .-. .-.. -.. -.-.--");
//        System.out.println(output); // hello, world!
//    }

    public static String convertMorse(boolean codeToEnglish, String input) { // TRUE MEANS YOU TURN TGHE CODE TO ENGGLISH FALSE MEANS ENGLISH TO CODE

        if (input == null || input.isEmpty())
            throw new IllegalArgumentException("Invalid input");

        String wordSplitter, wordJoiner, charSplitter, charJoiner;
        Map<String, String> mapper;

        if (codeToEnglish) {
            wordSplitter = " / ";
            wordJoiner = " ";
            charJoiner = "";
            charSplitter = " ";
            mapper = MORSE_TO_EN;
        } else {
            wordSplitter = " ";
            wordJoiner = " / ";
            charJoiner = " ";
            charSplitter = "";
            mapper = EN_TO_MORSE;
        }

        return Arrays
                .stream(input.trim().toLowerCase().split(wordSplitter))
                .map(word -> createWord(word, charJoiner, charSplitter, mapper))
                .collect(Collectors.joining(wordJoiner));
    }

    private static String createWord(String word, String joiner, String splitter, Map<String, String> mapper) {
        return Arrays.stream(word.split(splitter)).map(mapper::get).collect(Collectors.joining(joiner));
    }

}
