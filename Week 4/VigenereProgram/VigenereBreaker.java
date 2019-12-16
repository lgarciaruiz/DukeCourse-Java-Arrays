import java.util.*;
import java.io.*;
import edu.duke.*;

public class VigenereBreaker {
    public HashSet<String> readDictionary(FileResource fr) {
    HashSet<String> set = new HashSet<String>();
    for (String line : fr.lines()){
        line = line.toLowerCase();
        set.add(line);
    }
    return set;
    }
    
    public int countWords(String message, HashSet<String> dictionary) {
    int count = 0;     // splits message into individual words
    for (String word : message.split("\\W+")){
        if (dictionary.contains(word.toLowerCase())){
            count += 1;
        }
    }
    return count;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
    String decrypted = "";
    int count = 0;
    for (int i = 1; i < 100; i++){
        int [] key = tryKeyLength(encrypted,i,mostCommonCharIn(dictionary));
        VigenereCipher vc = new VigenereCipher(key);
        int currCount = countWords(vc.decrypt(encrypted), dictionary);
        if (currCount > count){
            count = currCount;
            decrypted = vc.decrypt(encrypted);
        }
    }
    return decrypted;
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary) {
    HashMap<Character,Integer> map = new HashMap<Character,Integer>();
    char character = '*';
    int currV = 0;
    for (String word : dictionary){
        word = word.toLowerCase();
        for (int i=0; i < word.length(); i++){
            if (! map.containsKey(word.charAt(i))){
                map.put(word.charAt(i),1);
            }
            else{
                map.put(word.charAt(i),map.get(word.charAt(i)) + 1);
            }
        }
    }
    
    for(Character curChar : map.keySet()){
        if(currV < map.get(curChar)){
            currV = map.get(curChar);
            character = curChar;
        }
    }
    return character;
    }
    
    public void breakForAllLangs(String encrypted, HashMap<String,HashSet<String>> languages) {
        String decrypted = "";
        String langUsed = "";
        int count = 0;
        for (String language : languages.keySet()){
            decrypted = breakForLanguage(encrypted,languages.get(language));
            int currCount = countWords(decrypted,languages.get(language));
            System.out.println(language + " checked");
            if(count < currCount){
                count = currCount;
                decrypted = breakForLanguage(encrypted,languages.get(language));
                langUsed = language;
            }
        }
        System.out.println(decrypted + "\nLanguage used is " + langUsed + "\nWord count is " + count);
    }
    
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder answer = new StringBuilder();
        //loop over encrypted message and slice the message
        for (int i = whichSlice; i < message.length(); i += totalSlices){
            //set answer to character in ith position and build string
            answer = answer.append(message.charAt(i));
        }
        return answer.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker(mostCommon);
        //iterate over the key length to slice message
        for (int i = 0; i < klength; i++){
            //get all the slices from the encrypted message
            String slice = sliceString(encrypted, i, klength);
            //get the key for each slice
            int sliceKey = cc.getKey(slice);
            //add key to array
            key[i] = sliceKey;
        }
        return key;
    }

    public void breakVigenere () {
        HashMap<String,HashSet<String>> map = new HashMap<String,HashSet<String>>();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            HashSet<String> words = readDictionary(fr);
            map.put(f.getName(),words);
        }
        FileResource encrypted = new FileResource("secretmessage4.txt");
        breakForAllLangs(encrypted.asString(),map);
        //FileResource fr = new FileResource("secretmessage2.txt");
        //String encrypted = fr.asString();
        //FileResource dic = new FileResource();
        //HashSet<String> dictionary = readDictionary(dic);
        //String decrypted = breakForLanguage(encrypted, dictionary);
        //System.out.println(decrypted);
        //System.out.println(countWords(decrypted, dictionary));
        //int [] key = tryKeyLength(encrypted,4, 'e');
        //VigenereCipher vc = new VigenereCipher(key);
        //System.out.println(vc.decrypt(encrypted));
    }
    
}
