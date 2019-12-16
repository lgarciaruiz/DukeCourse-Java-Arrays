
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author Leonardo Garcia 
 * @version v1
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         // initialize records to a new array list object of type LogEntry
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         //read in file and itirate over lines
         FileResource fr = new FileResource(filename);
         for (String line : fr.lines()){
             //create new logentry by calling webparserparseentry and passing in ine
             LogEntry logEntry = WebLogParser.parseEntry(line);
             //add logentry to arraylist
             records.add(logEntry);
            }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     //counts number of unique ip addresses in logentry arraylist
     public int countUniqueIPs(){
        ArrayList<String> uniqueIps = new ArrayList<String>();
        //iterate over records in arrylist of log entries
        for (LogEntry le : records){
            //get ip address by using the getIpAddress method created in logEntry class
            String ipAddr = le.getIpAddress();
            if (!uniqueIps.contains(ipAddr)){
                uniqueIps.add(ipAddr);
            }
        }
        return uniqueIps.size();
     }
        
     public void printAllHigherThanNum(int num) {
        //iterate over records in arrylist of log entries
        System.out.println("The following entries have a status code greater than " + num + ":");
        for (LogEntry le : records){
            //get ip address by using the getIpAddress method created in logEntry class
            int statusCode = le.getStatusCode();
            if (statusCode > num){
                System.out.println(le);
            }
        }
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
         ArrayList<String> ipAdd = new ArrayList<String>();
         for (LogEntry le : records){
            String date = le.getAccessTime().toString();
            date = date.substring(4,10);
            String ipAddr = le.getIpAddress();
            if ((!ipAdd.contains(ipAddr)) && date.equals(someday)){
                ipAdd.add(ipAddr);
            }
         }
         return ipAdd;
     }
     
     public int countUniqueIPsInRange(int low, int high) {
        ArrayList<String> ipAdd = new ArrayList<String>();
        for (LogEntry le : records){
            //get ip address by using the getIpAddress method created in logEntry class
            int statusCode = le.getStatusCode();
            String ipAddr = le.getIpAddress();
            if ((statusCode >= low && statusCode <= high) && !ipAdd.contains(ipAddr)){
                ipAdd.add(ipAddr);
            }
        } 
        return ipAdd.size();
     }
     
     public HashMap<String,Integer> countVisitsPerIP() {
         HashMap<String,Integer> counts = new HashMap<String,Integer>();
         for (LogEntry le : records){
             String IP = le.getIpAddress();
             if (! counts.containsKey(IP)){
                counts.put(IP,1);
             }
             else{
                 counts.put(IP,counts.get(IP)+1);
             }
         }
         return counts;
     }
     
     public int mostNumberVisitsByIP(HashMap<String,Integer> map) {
         int maxVisits = 0;
         for (String key : map.keySet()){
             if (map.get(key) > maxVisits){
                maxVisits = map.get(key);
             }
         }
         return maxVisits;
     }
     
     public ArrayList<String> iPsMostVisits(HashMap<String,Integer> map) {
         ArrayList<String> ipsMostVisits = new ArrayList<String>();
         int maxVisits = mostNumberVisitsByIP(map);
         for (String key : map.keySet()){
             if (map.get(key) == maxVisits){
                 ipsMostVisits.add(key);
             }
         }
         return ipsMostVisits;
     }
     
     public HashMap<String,ArrayList<String>> iPsForDays() {
         HashMap<String,ArrayList<String>> map = new HashMap<String,ArrayList<String>>();
         for (LogEntry le : records){
            String date = le.getAccessTime().toString();
            date = date.substring(4,10);
            if(! map.containsKey(date)){
                ArrayList<String> list = new ArrayList<String>();
                list.add(le.getIpAddress());
                map.put(date,list);
            }
            else{
                map.get(date).add(le.getIpAddress());
            }
         }
         return map;
     }
     
     public String dayWithMostIPVisits(HashMap<String,ArrayList<String>> map) {
         int ipAmounts = 0;
         String day = "";
         for (String key : map.keySet()){
             if (map.get(key).size() > ipAmounts){
                ipAmounts = map.get(key).size();
                day = key;
             }
         }
         return day;
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String,ArrayList<String>> map, String day) {
         HashMap<String, Integer> newMap = new HashMap<String,Integer>();
         ArrayList<String> list = new ArrayList<String>();
         for (String key : map.keySet()){
             if (key.equals(day)){
                 ArrayList<String> currList = map.get(key);
                 for (int i = 0 ; i < currList.size(); i++){
                    String ipAddr = currList.get(i);
                    if (! newMap.containsKey(ipAddr)){
                         newMap.put(ipAddr,1);
                    }
                    else{
                        newMap.put(ipAddr,newMap.get(ipAddr)+1);
                    }
                 }
                 int maxVisits = mostNumberVisitsByIP(newMap);
                 for (String newKey : newMap.keySet()){
                     if (newMap.get(newKey) == maxVisits){
                         list.add(newKey);
                     }
                 }
             }
         }
         return list;
     }
}
