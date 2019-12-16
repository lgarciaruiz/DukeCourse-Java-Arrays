import edu.duke.*;
/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author Leonardo Garcia 
 * @version v1
 */
public class TestCaesarCipher {
    public void breakCaesarCipher(String input){
        int[] frequency = countLetters(input);
        int maxIndex = indexOfMax(frequency);
        int newKey = maxIndex - 4;
        if (maxIndex < 4) {
            newKey = 26 -(4 - maxIndex);
        }
        CaesarCipher cc = new CaesarCipher(26 - newKey);
        System.out.println(cc.encrypt(input));
    }
    
    private int[] countLetters(String message){
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
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
    
    private int indexOfMax(int[] values){
        int index = 0;
        for (int i = 0; i < values.length; i++){
            if (values[i] > values[index]){
                index = i;
            }
        }
        return index;
    }
    
    public void simpleTests(){
        //FileResource fr = new FileResource();
        CaesarCipher cc = new CaesarCipher(15);
        String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        String encryptedMessage = cc.encrypt(message);
        System.out.println(encryptedMessage);
        System.out.println(cc.decrypt(encryptedMessage));
        //breakCaesarCipher(encryptedMessage);
    }
}
