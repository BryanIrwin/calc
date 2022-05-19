package settings;

import java.awt.Desktop;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.ListView;

public class PrintingAndReports
{	
	/** Report Display **/
	List<Report_Item> weekReport_list = new ArrayList<Report_Item>();
	public void send_to_reports(String worker,String tue, String wed, String thur, String fri, 
			String sat, String sun, String mon, String weekTotal, String hourlyRate, String grandTotal, ListView<String> report_list)
	{		
		//add items to report list
		weekReport_list.add(new Report_Item(worker, tue, wed, thur, fri, sat, sun, mon, weekTotal, hourlyRate, grandTotal));
		
		//clears list
		report_list.getItems().clear();
		
		
		//add items to list
		addItemsToList(report_list);
		
		//DEBUG: output message - replace with pop up message
		System.out.println("Worker added to report.");
	}
	public String dynamic_Spacer(String item, int columWidth)
	{
		String newSpacer = "";
		
		if(item.length() < columWidth) 
		{
			int difference = columWidth - item.length();
			
			for(int x=0; x< difference; x++) 
			{
				newSpacer+= " ";
			}
		}
		
		return item + newSpacer;
	}
	
	
	public int getLargestIndex(List<Report_Item> list)
	{
		int largestIndex =0;
		for(Report_Item i: list) 
		{
			if(i.getWorker().length() > largestIndex) 
			{
				largestIndex = i.getWorker().length();
			}
		}
		
		return largestIndex;
	}
	
	ArrayList<String> weekPrintout = new ArrayList<String>();
	String[] daysOfWeek = {"Name","T", "W", "Th", "F", "Sa", "Su", "M","Hours", "Rate", "Total"};
	public int getLargestWeekdayIndex(String[] daysOfWeek)
	{
		int largestIndex =0;
		for(String s: daysOfWeek) 
		{
			if(s.length() > largestIndex) 
			{
				largestIndex = s.length();
			}
		}
		
		return largestIndex;
	}
	public void addItemsToList(ListView<String> list) 
	{			
		// Prepare list
		weekPrintout.clear();
		
		//setup fields
		int col_width = getLargestIndex(weekReport_list),
			weekDaySpacer = 15;	
		
		/** Header **/
		if(weekPrintout.size() < 1) 
		{			
			String weekDays = String.format("%"+col_width+"s","Workers");
			for(int i=1; i<daysOfWeek.length; i++) 
			{
				weekDays += String.format("%"+weekDaySpacer+"s",daysOfWeek[i]);
			}
						
			weekPrintout.add(weekDays);
		}
		
		/** Body Data **/
		for(Report_Item i: weekReport_list) 
		{
			String workerDataRow = String.format( "%"+col_width+"s%"+		
													13+"s%"+
													5+"s%"+
													25+"s%"+
													20+"s%"+
													weekDaySpacer+"s%"+
													20+"s%"+
													22+"s%"+
													
													20+"s%"+													
													weekDaySpacer+"s%"+													
													20+"s",
									 i.getWorker(),
									 i.getTue(),
									 i.getWed(),
									 i.getThur(),
									 i.getFri(),
									 i.getSat(),
									 i.getSun(),
									 i.getMon(),
									 i.getWeekTotal(),
									 i.getHourlyRate(),
									 i.getGrandTotal()
													);			
			
			weekPrintout.add(workerDataRow);			
		}
					
		for(String i: weekPrintout) 
		{
			list.getItems().add(i);
		}	
		
		list.getSelectionModel().select(0);	
	}

	/** Printing **/
	public ArrayList<String> sendTo_printer(ListView<String> list) throws IOException 
	{
		ArrayList<String> printOut_weeklyData = new ArrayList<String>();
		int col_width = getLargestIndex(weekReport_list),
			weekDaySpacer = 8;	
		
		
		/** Header **/
		String weekDays = String.format("%"+col_width+"s","Workers");
		for(int i=1; i<daysOfWeek.length; i++) 
		{
			weekDays += String.format("%"+weekDaySpacer+"s",daysOfWeek[i]);
		}
		System.out.println(weekDays);
		printOut_weeklyData.add(weekDays);
		
		/** Body **/
		for(Report_Item i: weekReport_list) 
		{
			String fString = String.format( "%"+col_width+"s%"+		
													weekDaySpacer+"s%"+
													weekDaySpacer+"s%"+
													weekDaySpacer+"s%"+
													weekDaySpacer+"s%"+
													weekDaySpacer+"s%"+
													weekDaySpacer+"s%"+
													weekDaySpacer+"s%"+
													
													weekDaySpacer+"s%"+													
													weekDaySpacer+"s%"+													
													weekDaySpacer+"s",
								i.getWorker(),
								i.getTue(),
								i.getWed(),
								i.getThur(),
								i.getFri(),
								i.getSat(),
								i.getSun(),
								i.getMon(),
								i.getWeekTotal(),
								i.getHourlyRate(),
								i.getGrandTotal()
								);		    
			
			System.out.println(fString);	
			printOut_weeklyData.add(fString);
		}
		
		
		
		//print(list);
		
		
		return printOut_weeklyData;
	}

	public void saveAsFile(ListView<String> list) throws IOException 
	{
		String path = "report.txt";
		
		FileWriter fw = new FileWriter(path);
	
		for(String i: sendTo_printer(list)) 
		{
			fw.write(i+"\n");
		}
	 
		fw.close();
		
		
		//Runtime.getRuntime().exec("notepad.exe report.txt");
		//String url = "report.txt";
		//java.awt.Desktop.getDesktop().browse( java.net.URI.create(url));
		
		if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) 
		{
		    try {
				Desktop.getDesktop().browse(new URI("report.txt"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public String getWorkersAsString(ListView<String> list) 
	{
		String workers = "";
		for(int x=0; x<list.getItems().size(); x++) 
		{
			String worker = list.getItems().get(x);
			workers += worker +"\n";
		}		
		
		return workers;
	}
	public List<Report_Item> getWeekReport_list() 
	{
		return weekReport_list;
	}
}