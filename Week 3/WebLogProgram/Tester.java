
/**
 * Write a description of class Tester here.
 * 
 * @author Leonardo Garcia 
 * @version v1
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // create logAnalyzer object
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        //use readFile method in logAnalyzer; must call the variable you create when creating a new object that has the method you need
        logAnalyzer.readFile("short-test_log");
        logAnalyzer.printAll();
    }
    
    public void testUniqueIP() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("short-test_log");
        System.out.println("there are " + logAnalyzer.countUniqueIPs() + " unique IPs in this file.");
    }
    
    public void testPrintAllHigherThanNum() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("weblog1_log");
        logAnalyzer.printAllHigherThanNum(400);
    }
    
    public void testUniqueIPVisitsOnDay() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("weblog2_log");
        System.out.println("Number of unique IPs visited on date: " + logAnalyzer.uniqueIPVisitsOnDay("Sep 24").size());
    }
    public void testCountUniqueIPsInRange() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("weblog2_log");
        System.out.println(logAnalyzer.countUniqueIPsInRange(400,499));
    }
    
    public void testCountVisitsPerIP() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("short-test_log");
        HashMap<String, Integer> counts = logAnalyzer.countVisitsPerIP();
        System.out.println(counts);
    }
    
    public void testmostNumberVisitsByIP() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("weblog2_log");
        HashMap<String, Integer> counts = logAnalyzer.countVisitsPerIP();
        System.out.println(logAnalyzer.mostNumberVisitsByIP(counts));
    }
    
    public void testIPsMostVisits() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("weblog2_log");
        HashMap<String, Integer> counts = logAnalyzer.countVisitsPerIP();
        System.out.println(logAnalyzer.iPsMostVisits(counts));
    }
    
    public void testIPsForDays() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("weblog1_log");
        System.out.println(logAnalyzer.iPsForDays());
    }
    
    public void testDayWithMostIPVisits() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("weblog2_log");
        HashMap<String,ArrayList<String>> map = logAnalyzer.iPsForDays();
        System.out.println(logAnalyzer.dayWithMostIPVisits(map));
    }
    
    public void testIPsWithMostVisitsOnDay() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("weblog2_log");
        HashMap<String,ArrayList<String>> map = logAnalyzer.iPsForDays();
        System.out.println(logAnalyzer.iPsWithMostVisitsOnDay(map,"Sep 30"));
    }
}
