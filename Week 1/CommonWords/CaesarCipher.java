
/**
 * Write a description of wordPlay here.
 * 
 * @author Leonardo Garcia 
 * @version (a version number or a date)
 */
public class CaesarCipher {
 public String encrypt(String input, int key) {
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        //Write down the alphabet
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //Compute the shifted alphabet
        String shiftedAlphabet = alphabet.substring(key)+
        alphabet.substring(0,key);
        String lcsAlphabet = alphabet.toLowerCase();
        //Count from 0 to < length of encrypted, (call it i)
        for(int i = 0; i < encrypted.length(); i++) {
            //Look at the ith character of encrypted (call it currChar)
            char currChar = encrypted.charAt(i);
            int idx;
            if (Character.isLowerCase(currChar)){
               idx = lcsAlphabet.indexOf(currChar);
               if(idx != -1){             
                    //Get the idxth character of shiftedAlphabet (newChar)
                    char newChar = shiftedAlphabet.toLowerCase().charAt(idx);
                    //Replace the ith character of encrypted with newChar
                    encrypted.setCharAt(i, newChar);
               }
            }
            else{
               //Find the index of currChar in the alphabet (call it idx)
               idx = alphabet.indexOf(currChar);
               //If currChar is in the alphabet
               if(idx != -1){             
                    //Get the idxth character of shiftedAlphabet (newChar)
                    char newChar = shiftedAlphabet.charAt(idx);
                    //Replace the ith character of encrypted with newChar
                    encrypted.setCharAt(i, newChar);
               }
               //Otherwise: do nothing
            }
        }
        //Your answer is the String inside of encrypted
        return encrypted.toString();
    }
    
 public String encryptTwoKeys(String input, int key1, int key2){
    StringBuilder sb = new StringBuilder(input);
    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    //Compute the shifted alphabet
    String shiftedAlphabet = alphabet.substring(key1) + alphabet.substring(0,key1);
    String lcsAlphabet = alphabet.toLowerCase();
    String Key2ShiftedAlphabet = alphabet.substring(key2) + alphabet.substring(0,key2);
    String Key2lcsAlphabet = alphabet.toLowerCase();
    for (int i = 0; i < input.length(); i++){
        char currChar = sb.charAt(i);
        int index;
        if (isEven(i) == false){
           if (Character.isLowerCase(currChar)){
               index = lcsAlphabet.indexOf(currChar);
               if (index != -1){
                   char newChar = shiftedAlphabet.toLowerCase().charAt(index);
                   sb.setCharAt(i, newChar);
               }
           }
           else{
               index = alphabet.indexOf(currChar);
               if (index != -1){
                   char newChar = shiftedAlphabet.charAt(index);
                   sb.setCharAt(i, newChar);
               }
           }
        }
        else{
           if (Character.isLowerCase(currChar)){
               index = Key2lcsAlphabet.indexOf(currChar);
               if (index != -1){
                   char newChar = Key2ShiftedAlphabet.toLowerCase().charAt(index);
                   sb.setCharAt(i, newChar);
               }
           }
           else{
               index = alphabet.indexOf(currChar);
               if (index != -1){
                   char newChar = Key2ShiftedAlphabet.charAt(index);
                   sb.setCharAt(i, newChar);
               }
           }
        }
        
    }
    return sb.toString();
    }
    
  public void testEncryptTwoKeys(){
    System.out.println(encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21));
    }
    
   public void testCaesar() {
        int key = 15;
        //FileResource fr = new FileResource();
        //String message = fr.asString();
        String encrypted = encrypt("This is a file with lots of EEEEEEEEEEEEEs", key);
        System.out.println("key is " + key + "\n" + encrypted);
        String decrypted = encrypt(encrypted, 26-key);
        System.out.println(decrypted);
    }   
    
   public boolean isVowel(char ch){
     ch = Character.toLowerCase(ch);
     if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u'){
        return true;
        }
     return false;
    }
    
  public boolean isEven(int index){
     if ((index + 1) % 2 == 0){
        return true;
        }
     return false;
    }
    
   public String replaceVowels(String phrase, char ch){
    StringBuilder newPhrase = new StringBuilder(phrase);
    for (int i = 0; i < phrase.length(); i++){
        char currChar = phrase.charAt(i);
        if (isVowel(currChar)){
            newPhrase.setCharAt(i, ch);
        }
    }
    return newPhrase.toString();
    }
    
   public String emphasize(String phrase, char ch){
    StringBuilder newPhrase = new StringBuilder(phrase);
    for (int i = 0; i < phrase.length(); i++){
        if (phrase.charAt(i) == Character.toLowerCase(ch) || phrase.charAt(i) == Character.toUpperCase(ch)){
            if (isEven(i)){
                newPhrase.setCharAt(i,'+');
            }
            else{
                newPhrase.setCharAt(i,'*');
            }
        }
    }
    return newPhrase.toString();
    }
    
   public void testIsVowel(){
    System.out.println(isVowel('a'));
    System.out.println(isVowel('F'));
    System.out.println(isVowel('C'));
    System.out.println(isVowel('U'));
    }
    
   public void testReplaceVowels(){
    System.out.println(replaceVowels("Hello WOrld", '*'));
    }
   public void testemphasize(){
    System.out.println(emphasize("Mary Bella Abracadabra",'a'));
    }
}
