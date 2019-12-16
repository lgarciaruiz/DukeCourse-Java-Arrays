
/**
 * Write a description of CaesarCipher here.
 * 
 * @author Leonardo Garcia 
 * @version v1
 */
public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    
    public CaesarCipher(int key){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
        mainKey = key;
    }
    
    public String encrypt(String input){
        StringBuilder encryptedMessage = new StringBuilder(input);
        for (int i = 0; i < encryptedMessage.length(); i++){
            char character = Character.toUpperCase(encryptedMessage.charAt(i));
            int charIndex = alphabet.indexOf(character);
            if (charIndex != -1){
                character = shiftedAlphabet.charAt(charIndex);
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
        CaesarCipher cc = new CaesarCipher(26 - mainKey);
        String decryptedMessage = cc.encrypt(input);
        return decryptedMessage;
    }
}
