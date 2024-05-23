package java.Extrapunkte;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Password{
    final static String[] ERROR_MESSAGES = new String[]{
                                                "Hoppla, da ist wohl etwas schiefgelaufen!",
                                                "Ein derartiges Passwort zu erzeugen ist mir leider nicht möglich",
                                                "Message 3"};
    final static String[] OUTPUT_MESSAGES = new String[]{
                                                "Herzlich Willkommen! Wie lange soll das Passwort sein?",
                                                "Soll das Passwort Großbuchstaben enthalten?",
                                                "Soll das Passwort ebenfalls Kleinbuchstaben enthalten?",
                                                "Soll das Passwort Sonderzeichen enthalten?",
                                                "Soll das Passwort Zahlen enthalten?"};
    final static HashMap<String, char[]> ALL_CHARACTERS = init();
    final static String[] POSSIBLE_CHARACTERS = new String[]{"G", "K", "S", "N"};
    static ArrayList<String> requirements = new ArrayList<>();
    public static void main(String[] args){
        getRequirements();
        System.out.println(requirements);
        System.out.println(generatePassword());
    }
    public static String generatePassword(){
        // translate requirements list into random array
        String outputPassword = "";
        ArrayList<String> letterSelection = new ArrayList<>();
        for (int i = 1; i < requirements.size(); i++) {
            if(requirements.get(i).equals("y")){
                letterSelection.add(POSSIBLE_CHARACTERS[i-1]);
            }
        }

        // shuffle random letters
        for (int i = 0; i < Integer.valueOf(requirements.get(0)); i++) {
            Random random = new Random();
            char[] currentVocab = new char[]{};
            currentVocab = ALL_CHARACTERS.get(letterSelection.get(random.nextInt(letterSelection.size())));
            outputPassword += currentVocab[random.nextInt(currentVocab.length)];
        }
        return outputPassword;
    }
    public static void getRequirements(){
        try (Scanner scanner = new Scanner(System.in)) {
            while(requirements.size() < 5){
                System.out.println(OUTPUT_MESSAGES[requirements.size()]);
                String input = scanner.nextLine();
                if (requirements.isEmpty()){
                    try{
                        Integer.parseInt(input);
                        requirements.add(input);
                    } catch(Exception e){
                        System.out.println(ERROR_MESSAGES[0]);
                        continue;
                    }
                } else {
                    if (input.toLowerCase().equals("y") || input.toLowerCase().equals("n")){
                        requirements.add(input);
                    }
                }
            }
        }
    }
    private static HashMap<String, char[]> init(){
        HashMap<String, char[]> allCharacters = new HashMap<String, char[]>();
        allCharacters.put("G", new char[]{
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
        });
    
        allCharacters.put("K", new char[]{
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
        });
    
        allCharacters.put("S", new char[]{
            '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '_', '=', 
            '+', '[', ']', '{', '}', '\\', '|', ';', ':', '\'', '"', ',', 
            '.', '<', '>', '/', '?', '`', '~'
        });
        allCharacters.put("N", new char[]{'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9'});
        return allCharacters;
    }
}