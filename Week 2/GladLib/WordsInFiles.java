import java.util.*;
import java.io.*;
import edu.duke.*;
/**
 * Write a description of WordsInFiles here.
 * 
 * @author Leonardo Garcia
 * @version v1
 */
public class WordsInFiles {
    private HashMap<String,ArrayList<String>> map;
    
    public WordsInFiles() {
        map = new HashMap<String,ArrayList<String>>();
    }
    
    private void addWordsFromFile(File f){
        //convert file to file resource to be able to read file word by word
        FileResource fr = new FileResource(f);
        for (String word : fr.words()){
            if (! map.keySet().contains(word)){
                //create arrayList to store file names
                ArrayList<String> fileList = new ArrayList<String>();
                //add file name to arrayList
                fileList.add(f.getName());
                //store word file name mapped to word it contains
                map.put(word,fileList);
            }
            else{
                //word already exists in map
                if(! map.get(word).contains(f.getName())){
                    //add file name to arrayList of files that belongs to the current word
                    map.get(word).add(f.getName());
                }
            }
        }
    }
    
    private void buildWordFileMap() {
        map.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            addWordsFromFile(f);
        }
    }
    
    private int maxNumber() {
        int maxNumber = 0;
        for (String w : map.keySet()){
            if (map.get(w).size() > maxNumber) {
                maxNumber = map.get(w).size();
            }
        }
        return maxNumber;
    }
    
    private ArrayList<String> wordsInNumFiles(int number) {
        ArrayList<String> array = new ArrayList<String>();
        int count = 0;
        for (String w : map.keySet()){
            count = map.get(w).size();
            if (count == number){
                array.add(w);
            }
        }
        System.out.println("amount of words in " + number + " files " + array.size());
        return array;
    }
    
    private void printFilesIn(String word) {
        System.out.println("\nBelow are the files the word " + word + " appears in:");
        for (String w : map.keySet()){
            if (w.equals(word)){
                for (int i = 0; i < map.get(w).size(); i++){
                    System.out.println(map.get(w).get(i));
                }
            }
        } 
    }
    
    public void tester() {
        buildWordFileMap();
        //this for loop prints out all the map with keys and arraylist
        //for (String w : map.keySet()){
          //  System.out.println(w + "\t" + map.get(w));
        //}
        System.out.println("\nMax number of files a word appears in is " + maxNumber());
        System.out.println("\nWords that appear in given number of files " + wordsInNumFiles(7));
        printFilesIn("sea");
    }
}
