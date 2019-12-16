import edu.duke.*;
import java.util.*;

public class GladLib {
    private ArrayList<String> adjectiveList;
    private ArrayList<String> nounList;
    private ArrayList<String> colorList;
    private ArrayList<String> countryList;
    private ArrayList<String> nameList;
    private ArrayList<String> animalList;
    private ArrayList<String> timeList;
    private ArrayList<String> verbList;
    private ArrayList<String> fruitList;
    
    private ArrayList<String> usedWordList;
    
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "datalong";
    
    public GladLib(){
        //constructor initiallizes all arrayLists to their source; dataSourceDirectory is set to the data folder
        initializeFromSource(dataSourceDirectory);
        //initialized random object
        myRandom = new Random();
        //initialize used word list
        usedWordList = new ArrayList<String>();
    }
    
    public GladLib(String source){
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        //all files get lines written to their respective arrayList
        adjectiveList = readIt(source+"/adjective.txt"); 
        nounList = readIt(source+"/noun.txt");
        colorList = readIt(source+"/color.txt");
        countryList = readIt(source+"/country.txt");
        nameList = readIt(source+"/name.txt");      
        animalList = readIt(source+"/animal.txt");
        timeList = readIt(source+"/timeframe.txt");
        verbList = readIt(source+"/verb.txt");
        fruitList = readIt(source+"/fruit.txt");
    }
    
    private String randomFrom(ArrayList<String> source){
        //accepts an arrayList 
        //sets a random integer based on the amount of words the arrayList has
        int index = myRandom.nextInt(source.size());
        //locates word found in the random index above and returns it
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        //checks to see what label or word its looking for was passed over and returns random word from that list
        if (label.equals("country")) {
            return randomFrom(countryList);
        }
        if (label.equals("color")){
            return randomFrom(colorList);
        }
        if (label.equals("noun")){
            return randomFrom(nounList);
        }
        if (label.equals("name")){
            return randomFrom(nameList);
        }
        if (label.equals("adjective")){
            return randomFrom(adjectiveList);
        }
        if (label.equals("animal")){
            return randomFrom(animalList);
        }
        if (label.equals("timeframe")){
            return randomFrom(timeList);
        }
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        if (label.equals("verb")){
            return randomFrom(verbList);
        }
        if (label.equals("fruit")){
            return randomFrom(fruitList);
        }
        return "**UNKNOWN**";
    }
    
    private String processWord(String w){
        //sets first index to the beggining of <
        int first = w.indexOf("<");
        //sets first index to the beggining of >
        int last = w.indexOf(">",first);
        //if it doesnt find the brackets then the word should be returend as it does not need to be replaced
        if (first == -1 || last == -1){
            return w;
        }
        //sets prefix to the found < and the char preceeding it
        String prefix = w.substring(0,first);
        //sets suffix to the found > and the char post it
        String suffix = w.substring(last+1);
        //looks for substitute word to replace <word> by passing the type of new word its looking for eg. noun
        String sub = getSubstitute(w.substring(first+1,last));
        while (true){
            if (usedWordList.contains(sub)){
                sub = getSubstitute(w.substring(first+1,last));
            }
            else{
                usedWordList.add(sub);
                break;
            }
        }
        //returns new word including the pre and post character
        return prefix+sub+suffix;
    }
    
    //prints out story, lineWidth = how long (in characters) you want the lines to go to the right before breaking
    private void printOut(String s, int lineWidth){
        //character counter to keep track of chars written on one line
        int charsWritten = 0;
        //iterates over every word on the string, the regex \\s+ removes the spaces and sets the string before the space as a word
        for(String w : s.split("\\s+")){
            //checks if this line has reached the limit of characters allowed on the line
            if (charsWritten + w.length() > lineWidth){
                //prints line break
                System.out.println();
                //resets character counter on new line
                charsWritten = 0;
            }
            //prints out word with space
            System.out.print(w+" ");
            //updates character counter
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        //initializes new story output to empty string
        String story = "";
        //checks if source is url
        if (source.startsWith("http")) {
            //sets url resource if it does
            URLResource resource = new URLResource(source);
            //itirates over everyword in url
            for(String word : resource.words()){
                //sets story variable to include new word first follwed by a space
                story = story + processWord(word) + " ";
            }
        }
        else {
            //sets source as file resource
            FileResource resource = new FileResource(source);
            //itirates over everyword in file
            for(String word : resource.words()){
                //sets story variable to include new word first follwed by a space
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            //if source is url set new urlresource
            URLResource resource = new URLResource(source);
            //iterate over lines in ulr
            for(String line : resource.lines()){
                //adds line to list arrayList
                list.add(line);
            }
        }
        else {
            //sets source as fileresource if doesnt start with http
            FileResource resource = new FileResource(source);
            //itirates over lines in resource file
            for(String line : resource.lines()){
                //adds line to list arrayList
                list.add(line);
            }
        }
        return list;
    }
    
    private void printWordsReplaced(){
        System.out.println("\n\nWords replaced = " + usedWordList.size());
    }
    
    public void makeStory(){
        usedWordList.clear();
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate3.txt");
        printOut(story, 60);
        printWordsReplaced();
    }
    


}
