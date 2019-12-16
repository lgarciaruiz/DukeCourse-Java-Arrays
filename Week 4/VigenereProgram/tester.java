import edu.duke.*;
import java.util.*;
/**
 * Write a description of tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class tester {
    
    public void caesarTester() {
        CaesarCipher cp = new CaesarCipher(23);
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrpted = cp.encrypt(message);
        System.out.println(cp.encrypt(message));
        System.out.println(cp.decrypt(encrpted));
        
        char ch = 'a';
        char encryCh = cp.encryptLetter(ch);
        System.out.println(cp.encryptLetter(ch));
        System.out.println(cp.decryptLetter(encryCh));
    }
    
    public void caesarCrackerTester() {
        CaesarCracker cc = new CaesarCracker('a');
        FileResource fr = new FileResource();
        String message = fr.asString();
        int key = cc.getKey(message);
        System.out.println(key);
        System.out.println(cc.decrypt(message));
    }
    
    public void vigenereTester() {
        int [] key = {17, 14, 12, 4};
        VigenereCipher vc = new VigenereCipher(key);
        FileResource fr = new FileResource();
        String message = fr.asString();
        System.out.println(vc.encrypt(message));
        String encrypted = vc.encrypt(message);
        System.out.println(vc.decrypt(encrypted));
    }
    
    public void vigenereBreakerTester() {
        VigenereBreaker vb = new VigenereBreaker();
        String message = "abcdefghijklm";
        int startSlice = 4;
        int numberOfSlices = 5;
        
        System.out.println(vb.sliceString(message,startSlice,numberOfSlices));
    }
    
    public void tryKeyLengthTesterVB() {
        VigenereBreaker vb = new VigenereBreaker();
        FileResource fr = new FileResource();
        String message = fr.asString();
        int [] array = vb.tryKeyLength(message,4,'e');
        for (int i = 0; i < array.length; i++){
             System.out.println(array[i]);
        }
    }
    public void readDictionaryTester(){
        VigenereBreaker vb = new VigenereBreaker();
        FileResource fr = new FileResource();
        System.out.println(vb.readDictionary(fr));
    }
    
    public void countWordsTester(){
        String message = "abcdefghijklm  LMNTO HELLO";
        VigenereBreaker vb = new VigenereBreaker();
        FileResource fr = new FileResource();
        System.out.println(vb.countWords(message, vb.readDictionary(fr)));
    }
    
    public void mostCommonCharInTester() {
        VigenereBreaker vb = new VigenereBreaker();
        FileResource dic = new FileResource();
        HashSet<String> dictionary = vb.readDictionary(dic);
        System.out.println(vb.mostCommonCharIn(dictionary));
    }
    
    public void breakVignereTest(){
    VigenereBreaker vb = new VigenereBreaker();
    vb.breakVigenere();
    }
}
