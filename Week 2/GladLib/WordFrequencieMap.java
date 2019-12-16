import edu.duke.*;
import java.util.*;
/**
 * Write a description of WordFrequencieMap here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordFrequencieMap {
    public void countWords() {
        FileResource fr = new FileResource();
        HashMap<String,Integer> map = new HashMap<String,Integer>();
        int total = 0;
        for (String word : fr.words()){
            word = word.toLowerCase();
            if (map.keySet().contains(word)){
                map.put(word,map.get(word) + 1);
            }
            else{
                map.put(word,1);
            }
        }
        for (String word : map.keySet()){
            int occurences = map.get(word);
            if (occurences > 200){
                System.out.println(occurences+"\t"+word);
            }
        }
        System.out.println(map.size());
    }
}
