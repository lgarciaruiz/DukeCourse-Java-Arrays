import edu.duke.*;
/**
 * Write a description of CaesarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarBreaker {
    public String decrypt(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        int [] frequency = countLetters(encrypted);
        int maxIndex = indexOfMax(frequency);
        int key = maxIndex - 4;
        if (maxIndex < 4) {
            key = 26 -(4 - maxIndex);
        }
        return cc.encrypt(encrypted, 26 - key);
    }
    
    public int[] countLetters(String message){
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
        public int indexOfMax(int[] values){
        int index = 0;
        for (int i = 0; i < values.length; i++){
            if (values[i] > values[index]){
                index = i;
            }
        }
        return index;
    }
    
    public String halfOfString(String message, int start){
        StringBuilder stringA = new StringBuilder();
        boolean Even = false;
        if (start % 2 == 0){
         Even = true;
        }
        for (int i = start; i < message.length(); i++){
            if (i % 2 == 0 && Even == true){
               char newChar = message.charAt(i);
               stringA.append(newChar);
            }
            else if (i % 2 != 0 && Even == false){
               char newChar = message.charAt(i);
               stringA.append(newChar);
            }
        }
        return stringA.toString();
    }
    
    public int getKey(String s){
        int [] frequency = countLetters(s);
        int maxIndex = indexOfMax(frequency);
        int key = maxIndex - 4;
        if (maxIndex < 4) {
            key = 26 - (4 - maxIndex);
        }
        return key;
    }
    
    public String decryptTwoKeys(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        String firstString = halfOfString(encrypted,0);
        String secondString = halfOfString(encrypted,1);
        int keyA = getKey(firstString);
        int keyB = getKey(secondString);
        System.out.println("First key = " + keyA + " Second key = " + keyB);
        return cc.encryptTwoKeys(encrypted, 26-14, 26-24);
    }
    
    public void testDecrypt(){
        //CaesarCipher cc = new CaesarCipher();
        //String encrypted = cc.encrypt("This is a file with lots of EEEEEEEEEES", 12);
        //System.out.println(encrypted);
        System.out.println(decrypt("Top ncmy qkff vi vguv vbg ycpx")); 
    }
    
    public void testHalfString(){
        System.out.println(halfOfString("Pi cddc qt xc iwt", 0));
    }
    
    public void testDecryptTwoKeys(){
        //CaesarCipher cc = new CaesarCipher();
        //String encrypted = cc.encryptTwoKeys();
        //System.out.println(encrypted);
        //FileResource fr = new FileResource("data/mysteryTwoKeysPractice.txt");
        //String content = fr.asString();
        System.out.println(decryptTwoKeys("Hfs cpwewloj loks cd Hoto kyg Cyy.")); 
    }
}
