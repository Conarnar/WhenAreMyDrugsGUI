// writes medicine info to JSON

import java.io.FileNotFoundException; 
import java.io.PrintWriter; 
import org.json.simple.JSONObject; 
import java.util.Scanner;

public class medicineToJson
{ 
	public static void main(String[] args) throws FileNotFoundException 
	{ 
		// creating JSONObject 
        JSONObject jo = new JSONObject(); 
        
        Scanner myScanner = new Scanner(System.in);  // Create a Scanner object
        
        System.out.print("Enter drug name: ");
        String drugName = myScanner.nextLine();  // Read user input

        System.out.print("Enter days of week: ");
        String days = myScanner.nextLine();  // Read user input

        System.out.print("Enter time of day: ");
        String time = myScanner.nextLine();  // Read user input

        System.out.print("Reminder ahead of time: ");
        String reminderTime = myScanner.nextLine();  // Read user input

        System.out.print("Repeat times: ");
        String repeatTime = myScanner.nextLine();  // Read user input

        System.out.print("Repeat intervals: ");
        String repeatIntervals = myScanner.nextLine();  // Read user input

		
		// putting data to JSONObject 
		jo.put("Drug", drugName); 
		jo.put("Days", days); 
        jo.put("Time to take", time); 
        jo.put("Reminder ahead to take", reminderTime); 
		
		
		// writing JSON to file:"JSONExample.json" in cwd 
		PrintWriter pw = new PrintWriter("WhenToDrug.json"); 
		pw.write(jo.toJSONString()); 
		
		pw.flush(); 
        pw.close(); 
        
        
	} 
} 
