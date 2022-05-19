package application;

import java.util.HashMap;
import java.util.Map;

public class Data 
{
	//Time and values
	@SuppressWarnings("serial")
	public Map<String, Double> timeAndValues = new HashMap<String, Double>()
	{{
		put("", 0.00);
		put("5:00 am" , 5.00);
		put("5:15 am" , 5.25);
		put("5:30 am" , 5.50);
		put("5:45 am" , 5.75);
		put("6:00 am" , 6.00);
		put("6:15 am" , 6.25);
		put("6:30 am" , 6.50);
		put("6:45 am" , 6.75);
		put("7:00 am" , 7.00);
		put("7:15 am" , 7.25);
		put("7:30 am" , 7.50);
		put("7:45 am" , 7.75);
		put("8:00 am" , 8.00);
		put("8:15 am" , 8.25);
		put("8:30 am" , 8.50);
		put("8:45 am" , 8.75);
		put("9:00 am" , 9.00);
		put("9:15 am" , 9.25);
		put("9:30 am" , 9.50);
		put("9:45 am" , 9.75);
		put("10:00 am" , 10.00);
		put("10:15 am" , 10.25);
		put("10:30 am" , 10.50);
		put("10:45 am" , 10.75);
		put("11:00 am" , 11.00);
		put("11:15 am" , 11.25);
		put("11:30 am" , 11.50);
		put("11:45 am" , 11.75);
		put("12:00 pm" , 12.00);
		put("12:15 pm" , 12.25);
		put("12:30 pm" , 12.50);
		put("12:45 pm" , 12.75);
		put("1:00 pm" , 13.00);
		put("1:15 pm" , 13.25);
		put("1:30 pm" , 13.50);
		put("1:45 pm" , 13.75);
		put("2:00 pm" , 14.00);
		put("2:15 pm" , 14.25);
		put("2:30 pm" , 14.50);
		put("2:45 pm" , 14.75);
		put("3:00 pm" , 15.00);
		put("3:15 pm" , 15.25);
		put("3:30 pm" , 15.50);
		put("3:45 pm" , 15.75);
		put("4:00 pm" , 16.00);
		put("4:15 pm" , 16.25);
		put("4:30 pm" , 16.50);
		put("4:45 pm" , 16.75);
		put("5:00 pm" , 17.00);
		put("5:15 pm" , 17.25);
		put("5:30 pm" , 17.50);
		put("5:45 pm" , 17.75);
		put("6:00 pm" , 18.00);
		put("6:15 pm" , 18.25);
		put("6:30 pm" , 18.50);
		put("6:45 pm" , 18.75);
		put("7:00 pm" , 19.00);
		put("7:15 pm" , 19.25);
		put("7:30 pm" , 19.50);
		put("7;45 pm" , 19.75);
		put("8:00 pm" , 20.00);
		put("8:15 pm" , 20.25);
		put("8:30 pm" , 20.50);
		put("8:45 pm" , 20.75);
		put("9:00 pm" , 21.00);
		put("9:15 pm" , 21.25);
		put("9:30 pm" , 21.50);
		put("9:45 pm" , 21.75);
		put("10:00 pm" , 22.00);
	}};
	
	@SuppressWarnings("serial")
	public Map<String, Integer> day_indexes = new HashMap<String, Integer>()
	{{
		put("Tuesday", 0);
		put("Wednesday", 1);
		put("Thursday", 2);
		put("Friday", 3);
		put("Saturday", 4);
		put("Sunday", 5);
		put("Monday", 6);
	}};
		
	public String[] time = new String[] {	"",
									"5:00 am",
									"5:15 am",
									"5:30 am",
									"5:45 am",
									"6:00 am",
									"6:15 am",
									"6:30 am",
									"6:45 am",
									"7:00 am",
									"7:15 am",
									"7:30 am",
									"7:45 am",
									"8:00 am",
									"8:15 am",
									"8:30 am",
									"8:45 am",
									"9:00 am",
									"9:15 am",
									"9:30 am",
									"9:45 am",
									"10:00 am",
									"10:15 am",
									"10:30 am",
									"10:45 am",
									"11:00 am",
									"11:15 am",
									"11:30 am",
									"11:45 am",
									"12:00 pm",
									"12:15 pm",
									"12:30 pm",
									"12:45 pm",
									"1:00 pm",
									"1:15 pm",
									"1:30 pm",
									"1:45 pm",
									"2:00 pm",
									"2:15 pm",
									"2:30 pm",
									"2:45 pm",
									"3:00 pm",
									"3:15 pm",
									"3:30 pm",
									"3:45 pm",
									"4:00 pm",
									"4:15 pm",
									"4:30 pm",
									"4:45 pm",
									"5:00 pm",
									"5:15 pm",
									"5:30 pm",
									"5:45 pm",
									"6:00 pm",
									"6:15 pm",
									"6:30 pm",
									"6:45 pm",
									"7:00 pm",
									"7:15 pm",
									"7:30 pm",
									"7:45 pm",
									"8:00 pm",
									"8:15 pm",
									"8:30 pm",
									"8:45 pm",
									"9:00 pm",
									"9:15 pm",
									"9:30 pm",
									"9:45 pm",
									"10:00 pm"};


	@SuppressWarnings("serial")	
	public Map<String, Double> workers = new HashMap<String, Double>()
	{{
		put("Ken Scott", 16.00);
		put("Brent Stalman" , 15.00);
	}};


	//setters and getters
	public Map<String, Double> getTimeAndValues() 						{ return timeAndValues;}
	public void setTimeAndValues(Map<String, Double> timeAndValues) 	{ this.timeAndValues = timeAndValues;}
	public Map<String, Integer> getDay_indexes() 						{ return day_indexes;}
	public void setDay_indexes(Map<String, Integer> day_indexes) 		{ this.day_indexes = day_indexes;}
	public String[] getTime() 											{ return time;}
	public void setTime(String[] time) 									{ this.time = time;}
	public Map<String, Double> getWorkers() 							{ return workers;}
	public void setWorkers(Map<String, Double> workers) 				{ this.workers = workers;}
}