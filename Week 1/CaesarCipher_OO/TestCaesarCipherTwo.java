import edu.duke.*;
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author Leonardo Garcia
 * @version v1
 */
public class TestCaesarCipherTwo {
    public void breakCaesarCipher(String input){
        String firstString = halfOfString(input,0);
        String secondString = halfOfString(input,1);
        int keyA = getKey(firstString);
        int keyB = getKey(secondString);
        System.out.println("First key = " + keyA + " Second key = " + keyB);
        CaesarCipherTwo cc = new CaesarCipherTwo(26 - keyA, 26 - keyB);
        System.out.println(cc.encrypt(input));
    }
        
    private int getKey(String s){
        int [] frequency = countLetters(s);
        int maxIndex = indexOfMax(frequency);
        int key = maxIndex - 4;
        if (maxIndex < 4) {
            key = 26 - (4 - maxIndex);
        }
        return key;
    }
    
    private String halfOfString(String message, int start){
        String halfString = "";
        for (int i = start; i < message.length(); i = i + 2){
               char newChar = message.charAt(i);
               halfString = halfString + message.charAt(i);
        }
        return halfString;
    } 
    
    private int indexOfMax(int[] values){
        int index = 0;
        for (int i = 0; i < values.length; i++){
            if (values[i] > values[index]){
                index = i;
            }
        }
        return index;
    }  

    private int[] countLetters(String message){
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
                         //01234567890123456789012345
        int[] counts = new int[26];
        for (int i = 0; i < message.length(); i++){
            char character = Character.toLowerCase(message.charAt(i));
            int indexOfChar = alphabet.indexOf(character);
            if (indexOfChar != -1){
                counts[indexOfChar] +=1;
            }
        }
        return counts;
    } 
    
    public void simpleTests(){
        FileResource fr = new FileResource();
        //CaesarCipherTwo cc = new CaesarCipherTwo(21,8);
        String message = fr.asString();
        //String encryptedMessage = cc.encrypt(message);
        //System.out.println(encryptedMessage);
        //System.out.println(cc.decrypt(encryptedMessage));
        breakCaesarCipher(message);
    }
}
