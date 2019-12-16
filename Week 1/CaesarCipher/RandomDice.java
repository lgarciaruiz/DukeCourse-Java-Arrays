import java.util.*;
/**
 * Write a description of RandomDice here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RandomDice {
    public void Simulate(int rolls){
    Random rand = new Random();
    int [] counts = new int [13];
    
    for (int i = 0; i < rolls; i++){
        int d1 = rand.nextInt(6) + 1;
        int d2 = rand.nextInt(6) + 1;
        counts[d1+d2] += 1;
    }
    for (int i = 2; i <= 12; i++){
    System.out.println(i + "= \t" + counts[i] + " \t" + 100.0 * counts[i]/rolls);
    }
    }
    
    public void simpleSimulate(int rolls){
    Random rand = new Random();
    int twos= 0;
    int tweleves = 0;
    
    for (int i = 0; i < rolls; i++){
        int d1 = rand.nextInt(6) + 1;
        int d2 = rand.nextInt(6) + 1;
        if (d1 + d2 == 2){
            twos += 1;
        }
        else if (d1 + d2 == 12){
            tweleves += 1;
        }
    }
    System.out.println("2s " + twos + " \n" + "12s " + tweleves);
    }

}
