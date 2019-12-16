import java.util.*;
import edu.duke.*;
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CharactersInPlay {
    private ArrayList<String> characterNames;
    private ArrayList<Integer> characterCount;
    
    public CharactersInPlay() {
        characterNames = new ArrayList<String>();
        characterCount = new ArrayList<Integer>();
    }
    
    public void update(String person) {
        int index = characterNames.indexOf(person);
        if (index == -1){
            characterNames.add(person);
            characterCount.add(1);
        }
        else{
            int value = characterCount.get(index);
            characterCount.set(index, value + 1);
        }        
    }
    
    public void findAllCharacters() {
        characterNames.clear();
        characterCount.clear();
        FileResource resource = new FileResource();
        for (String line : resource.lines()){
            int nameIndex = line.indexOf('.');
            if (nameIndex != -1){
                String name = line.substring(0,nameIndex);
                name = name.toUpperCase();
                update(name);
            }
        }
    }
    
        public int findIndexOfMax(){
        int index = 0;
        int value = 0;
        for (int i = 0; i < characterCount.size(); i++){
            if (value < characterCount.get(i)){
                value = characterCount.get(i);
                index = i;
            }
        }
        
        return index;
    }
    
    public void tester() {
        findAllCharacters();
        for (int i = 0; i < characterNames.size(); i++){
            if (characterCount.get(i) > 4){
                System.out.println(characterNames.get(i) + " has " + characterCount.get(i) + " speaking parts.");
            }
        }
        int maxIndex = findIndexOfMax();
        System.out.println(characterNames.get(maxIndex) + " " + characterCount.get(maxIndex));
        charactersWithNumParts(10,15);
    }
    
    public void charactersWithNumParts(int num1, int num2) {
       
        for (int i = 0; i < characterCount.size(); i++){
            if (characterCount.get(i) >= num1 && characterCount.get(i) <= num2){
                System.out.println(characterNames.get(i));
            }
        }
    }
}
