import java.util.*;
import edu.duke.*;
/**
 * Write a description of dnaHashMap here.
 * 
 * @author Leonardo Garcia 
 * @version v1
 */

public class dnaHashMap {
    
    private HashMap<String,Integer> map;
    
    public dnaHashMap() {
        map = new HashMap<String, Integer>();
    }
    
    private void printCodonCounts(int start, int end) {
        System.out.println("The following codons have total amounts from " + start + " - " + end);
        for (String word : map.keySet()){
            if (map.get(word) >= start && map.get(word) <= end){
                System.out.println(word);
            }
        }
    }
    
    private String getMostCommonCodon() {
        String mostCommonCodon = "";
        int occurances = 0;
        for (String word : map.keySet()){
            if (occurances < map.get(word)){
                occurances = map.get(word);
                mostCommonCodon = word;
            } 
        }
        return mostCommonCodon;
    }
    
    private void buildCodonMap(int start, String dna) {
        map.clear();
        for (int i = start; i < dna.length()+1; i++){
            String codon = dna.substring(start,i);
            if(codon.length() == 3){
                if (map.keySet().contains(codon)){
                    map.put(codon,map.get(codon)+1);
                    start = i;
                }
                else{
                    map.put(codon,1);
                    start = i;
                }
            }
        }
    }
    
    public void test(){
        int count = 0;
        FileResource fr = new FileResource();
        String dna = fr.asString();
        buildCodonMap(0,dna.trim());
        for (String w : map.keySet()){
            System.out.println(w + "\t"+ map.get(w));
            count += 1;
        }
        System.out.println("COUNT = " + count);
        System.out.println("Most common codon is " + getMostCommonCodon());
        printCodonCounts(1,5);
    }
}
