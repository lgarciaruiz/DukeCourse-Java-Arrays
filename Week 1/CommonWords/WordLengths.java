import edu.duke.*;
/**
 * Write a description of WordLengths here.
 * 
 * @author Leo Garcia 
 * @version (a version number or a date)
 */
public class WordLengths {
    public void countWordLengths(FileResource fr, int[] counts){
        int wordLength;
        for (String word : fr.words()){
            wordLength = wordLegnth(word);
            if (wordLength < counts.length){
              counts[wordLength] += 1;
            }
            else{
            counts[counts.length - 1] += 1;
            }
        }
        for (int i = 1; i < counts.length; i++){
            System.out.println(counts[i] + " word with " + i + " characters" );
        }
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
    
    public int wordLegnth(String word){
        int count = 0;
        for (int i = 0; i < word.length(); i++){
            if (!Character.isLetter(word.charAt(i)) && i == 0 || !Character.isLetter(word.charAt(word.length()-1)) && i == word.length()-1){
            }
            else {
                 count += 1;
            }
        }
        return count;
    }
    
    public void testWordLenght(){
        System.out.println(wordLegnth("\"blue-jeans\""));
    }
    
    public void testCountWordLengths(){
        FileResource fr = new FileResource();
        int[] counts = new int [31];
        countWordLengths(fr, counts);
        System.out.println(indexOfMax(counts));
    }
}
