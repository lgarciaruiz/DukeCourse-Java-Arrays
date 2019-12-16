
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author Leonardo Garcia
 * @version v1
 */
public class CaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int mainKey1;
    private int mainKey2;
    
    public CaesarCipherTwo(int key1, int key2){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        mainKey1 = key1;
        mainKey2 = key2;
    }
    
    public String encrypt(String input){
        StringBuilder encryptedMessage = new StringBuilder(input);
        for (int i = 0; i < encryptedMessage.length(); i++){
            char character = Character.toUpperCase(encryptedMessage.charAt(i));
            int charIndex = alphabet.indexOf(character);
            if (charIndex != -1 && i % 2 == 0){
                character = shiftedAlphabet1.charAt(charIndex);
                if (Character.isLowerCase(encryptedMessage.charAt(i))){
                    encryptedMessage.setCharAt(i,Character.toLowerCase(character));
                }
                else{
                encryptedMessage.setCharAt(i,character);
                }
            }
            else if(charIndex != -1 && i % 2 != 0){
                character = shiftedAlphabet2.charAt(charIndex);
                if (Character.isLowerCase(encryptedMessage.charAt(i))){
                    encryptedMessage.setCharAt(i,Character.toLowerCase(character));
                }
                else{
                encryptedMessage.setCharAt(i,character);
                }
            }
        }
        return encryptedMessage.toString();
    }
    
    public String decrypt(String input){
        CaesarCipherTwo cc = new CaesarCipherTwo(26 - mainKey1, 26 - mainKey2);
        String decryptedMessage = cc.encrypt(input);
        return decryptedMessage;
    }
}
