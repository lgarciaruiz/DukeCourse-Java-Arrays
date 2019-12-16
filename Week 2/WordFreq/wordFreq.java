import java.util.*;
import edu.duke.*;
/**
 * Write a description of wordFreq here.
 * Count occurences of words
 * 
 * @author Leonardo Garcia 
 * @version v1
 */
public class wordFreq {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public wordFreq() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void findUnique(){
        myWords.clear();
        myFreqs.clear();
        FileResource resource = new FileResource();
        for (String word : resource.words()){
            word = word.toLowerCase();
            // this checks if word has an index in the array myWords by setting an index for current word in loop
            int index = myWords.indexOf(word);
            // if word has not been entered into arrayList HAS NOT BEEN SEEN index will be -1
            if (index == -1){
                myWords.add(word);
                //adding 1 because this is the first time it sees the word
                myFreqs.add(1);
            }
            else{
                //when word has been added once before find the previuos value
                int value = myFreqs.get(index);
                //set the new value for current word as value + new value
                myFreqs.set(index,value + 1);
            }
        }
    }
    
    public int findIndexOfMax(){
        int index = 0;
        int value = 0;
        for (int i = 0; i < myFreqs.size(); i++){
            if (value < myFreqs.get(i)){
                value = myFreqs.get(i);
                index = i;
            }
        }
        
        return index;
    }
    
    public void tester() {
        findUnique();
        //size prints out amount of words in array list myWords
        System.out.println("# unique words " + myWords.size());
        for (int i = 0; i < myWords.size(); i++){
            System.out.println(myFreqs.get(i) + "\t" + myWords.get(i)); 
        }
        int maxIndex = findIndexOfMax();
        System.out.println("The word " + "\"" + myWords.get(maxIndex) + "\"" + " appears most often with " + myFreqs.get(maxIndex) + " appearances.");
    }
}
