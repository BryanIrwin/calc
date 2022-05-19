package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import settings.FileSystem;
import settings.PrintingAndReports;
import settings.TimeSettings;

public class GUI
{
	//used to hold the data which populates the list views
	Data data = new Data();	
	
	double[] weekday_hours = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
	
	//used to create, save, update settings file
	FileSystem fileSystem = new FileSystem();
	TimeSettings timeSettings;
	
	//deals with all methods of printing and report generation
	PrintingAndReports printing = new PrintingAndReports();
	
	int startIndex = 0, 
		endIndex = 0;		
	
	ArrayList<String> workers = new ArrayList<String>();
		
	@FXML
    private Tab weekdays_tab,    			
    			printingAndReports,
    			settings_tab;	
	@FXML
	private ListView<String> 	tue_startTime, 			tue_endTime,
								wed_startTime, 			wed_endTime,
								thur_startTime, 		thur_endTime,
								fri_startTime, 			fri_endTime,
								sat_startTime, 			sat_endTime,
								sun_startTime, 			sun_endTime,
								mon_startTime, 			mon_endTime,
								app_startTime,			app_endTime,
								settings_worker_list,	weekly_report_list;	
	
	@FXML
	private Text 	tue_text, wed_text, thur_text, fri_text, sat_text, sun_text, mon_text,
					tue_totalHours, wed_totalHours, thur_totalHours, fri_totalHours, sat_totalHours, sun_totalHours, mon_totalHours,
					weekTotalHours, worker_name, hourly_rate, totalOwedTo_worker, changes_made_text;	
	@FXML
	private CheckBox 	tue_setToDefault,
						wed_setToDefault,
						thur_setToDefault,
						fri_setToDefault,
						sat_setToDefault,
						sun_setToDefault,
						mon_setToDefault;	
	@FXML
    private TableView<String> report_table;
	
	@FXML
    private TextField workerName, payRate;
	
	@FXML
    private ChoiceBox<String> workerList;
	
	@FXML
	private Button 	saveAll_settings, add_Worker, remove_Worker, save_WorkerSettings,
					remove_selectedWorker, print_report, save_asPDF;
	
	@FXML
    public void initialize() throws ClassNotFoundException, IOException
	{
		//setup each day's start & end time initial values. 
		addDataToLists();
		load_timeSettings();
		calculateWeekHours();	
				
		//Action Listeners
		setupWeek_actionListeners();	
		
		//loads worker list 
		load_workerSettings();
		populateWorkerList();	
    }

	
	//sets up each day's list and hours
	public void addDataToLists() 
	{
		//app time settings
		addItemsToListView(app_startTime, app_endTime, data.time);
		
		//Tuesday
		addItemsToListView(tue_startTime, tue_endTime, data.time);		
		
		//Wednesday
		addItemsToListView(wed_startTime, wed_endTime, data.time);		
		
		//Thursday
		addItemsToListView(thur_startTime, thur_endTime, data.time);		
		
		//Friday
		addItemsToListView(fri_startTime, fri_endTime, data.time);		
		
		//Saturday
		addItemsToListView(sat_startTime, sat_endTime, data.time);		
		
		//Sunday
		addItemsToListView(sun_startTime, sun_endTime, data.time);		
		
		//Monday
		addItemsToListView(mon_startTime, mon_endTime, data.time);
		
	}
	
	//used to setup all day's action listeners
	public void setupWeek_actionListeners() 
    {
    	//Tuesday
    	setupActionListener(tue_startTime, tue_endTime, tue_text, data.day_indexes.get("Tuesday"));
    	
    	//Wednesday
    	setupActionListener(wed_startTime, wed_endTime, wed_text, data.day_indexes.get("Wednesday"));
    	
    	//Thursday
    	setupActionListener(thur_startTime, thur_endTime, thur_text, data.day_indexes.get("Thursday"));
    	
    	//Friday
    	setupActionListener(fri_startTime, fri_endTime, fri_text, data.day_indexes.get("Friday"));
    	
    	//Saturday
    	setupActionListener(sat_startTime, sat_endTime, sat_text, data.day_indexes.get("Saturday"));
    	
    	//Sunday
    	setupActionListener(sun_startTime, sun_endTime, sun_text, data.day_indexes.get("Sunday"));
    	
    	//Monday
    	setupActionListener(mon_startTime, mon_endTime, mon_text, data.day_indexes.get("Monday"));
    	
    	
    	workerList.getSelectionModel()
		    .selectedItemProperty()
		    .addListener( (ObservableValue<? extends String> observable, String oldWorker, String newWorker) ->  
		    {
		    	updateHourlyRateInCalculations(newWorker);
		    	calculateTotal();
		    });
		    
    	
    	
    	settings_worker_list.getSelectionModel()
	    .selectedItemProperty()
	    .addListener( (ObservableValue<? extends String> observable, String oldWorker, String updatedWorkerInfo) ->  {
	    	putValuesInTextFields(updatedWorkerInfo);
	    	remove_Worker.setDisable(false);
	    	});
    	
    }    
	public void setupActionListener(ListView<String> start, ListView<String> end, Text text, int dayIndex)
	{
		start.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String old_val, String new_val) -> {
			calculateDayHours(start, end, text, dayIndex);				
			setToZero(start, end);
			
	    });
		end.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String old_val, String new_val) -> {
			calculateDayHours(start, end, text, dayIndex);		
			setToZero(end, start);
	    });		
		
	}
		
	public void updateHourlyRateInCalculations(String workerName) 
	{
		if(data.workers.get(workerName) != null)
		{
			hourly_rate.setText(data.workers.get(workerName) +"");	
		}
		else
		{
			hourly_rate.setText("0.0");
		}		
	}
	
	
	//used if a user clears the time on one day, the counter part time will also be set.
	public void setToZero(ListView<String> start, ListView<String> end) 
	{
		if(start.getSelectionModel().getSelectedIndex() == 0) 
		{
			end.getSelectionModel().selectFirst();
			end.scrollTo(0);
		}
	}
 		
	//used to setup a day when app starts
	public void setTimesByDefault(ListView<String> start, int startTimeIndex, ListView<String> end, int endTimeIndex, boolean ignoreDefaultTime)
	{		
		if(!ignoreDefaultTime)
		{
			start.getSelectionModel().select(startTimeIndex);
			start.scrollTo(startTimeIndex);
			end.getSelectionModel().select(endTimeIndex);
			end.scrollTo(endTimeIndex);
		}
		else
		{
			start.getSelectionModel().select(0);
			start.scrollTo(0);
			end.getSelectionModel().select(0);
			end.scrollTo(0);
		}
	}		
	
	//adds time to list view for a given day
    public void addItemsToListView(ListView<String> start, ListView<String> end, String[] data) 
    {
    	for(String s: data) 
    	{
    		start.getItems().add(s);
    		end.getItems().add(s);
    	}
    }
    
    
    /** Calculations **/
    public double getTotalHours_forWeek() 
    {
    	double total = 0.0;
    	for(double d: weekday_hours) 
    	{
    		total += d;
    	}
    	
    	return total;
    }
    public void calculateWeek_plusHourlyRate() 
    {           	
    	//display values on total tab
    	tue_totalHours.setText(weekday_hours[data.day_indexes.get("Tuesday")]+"");
    	wed_totalHours.setText(weekday_hours[data.day_indexes.get("Wednesday")]+"");    	
    	thur_totalHours.setText(weekday_hours[data.day_indexes.get("Thursday")]+"");
	 	fri_totalHours.setText(weekday_hours[data.day_indexes.get("Friday")]+"");
    	sat_totalHours.setText(weekday_hours[data.day_indexes.get("Saturday")]+"");
    	sun_totalHours.setText(weekday_hours[data.day_indexes.get("Sunday")]+"");
    	mon_totalHours.setText(weekday_hours[data.day_indexes.get("Monday")]+"");
  
    	//total hours
    	weekTotalHours.setText(getTotalHours_forWeek()+"");
    	
    	calculateTotal();
    }
    public void calculateWeekHours() 
    {
    	calculateDayHours(tue_startTime, 	tue_endTime, 	tue_totalHours, 	data.day_indexes.get("Tuesday"));
    	calculateDayHours(wed_startTime, 	wed_endTime, 	wed_totalHours, 	data.day_indexes.get("Wednesday"));
    	calculateDayHours(thur_startTime, 	thur_endTime, 	thur_totalHours, 	data.day_indexes.get("Thursday"));
    	calculateDayHours(fri_startTime, 	fri_endTime, 	fri_totalHours, 	data.day_indexes.get("Friday"));
    	calculateDayHours(sat_startTime, 	sat_endTime, 	sat_totalHours, 	data.day_indexes.get("Saturday"));
    	calculateDayHours(sun_startTime, 	sun_endTime, 	sun_totalHours, 	data.day_indexes.get("Sunday"));
    	calculateDayHours(mon_startTime, 	mon_endTime, 	mon_totalHours, 	data.day_indexes.get("Monday"));
    	
    }
    
    public void calculateTotal() 
    {
    	if(hourly_rate.getText().length() > 0 && hourly_rate.getText() != null) 
    	{
    		double total = Double.parseDouble(hourly_rate.getText()) *  Double.parseDouble(weekTotalHours.getText());
    		totalOwedTo_worker.setText("$ "+total);
    	}
    }
    public double calculateDayHours(ListView<String> startTime, ListView<String> endTime, Text text, int dayIndex) 
    {
    	String 	start_time = startTime.getSelectionModel().getSelectedItem(),
    			end_time = endTime.getSelectionModel().getSelectedItem();
    	
    	if(start_time == null) 
    	{
    		start_time = "";
    	}
    	if(end_time == null)
    	{
    		end_time = "";
    	}
    	
    	double 	startValue = data.timeAndValues.get(start_time),
    			endValue = data.timeAndValues.get(end_time), 
    			total = endValue - startValue; 
    	
    	if(total < 0) 
    	{
    		text.setFill(Color.RED);
    		weekday_hours[dayIndex] = 0.0;
    		calculateWeek_plusHourlyRate();
    		return 0.0;
    	}
    	else
    	{
    		text.setFill(Color.BLACK);
    		weekday_hours[dayIndex] = total;
    		calculateWeek_plusHourlyRate();
    		return total;
    	}
    }
    
    public void populateWorkerList() 
    {    	
    	//week tab
    	workerList.getItems().clear();
    	workerList.getItems().addAll(data.workers.keySet());   	
    	
    	//settings tab
    	settings_worker_list.getItems().clear();
    	settings_addWorkersToList(settings_worker_list);   
    }        
    public void settings_addWorkersToList(ListView<String> list) 
    {
    	ArrayList<String> workersAndPayRates = new ArrayList<String>();
    	for (Map.Entry<String, Double> item : data.workers.entrySet()) 
    	{	
    		String both = item.getKey()+"\n$"+item.getValue()+" per hour";    
    		workersAndPayRates.add(both);
    	}
    	
    	list.getItems().addAll(workersAndPayRates); 
    }
   
    
    /** Time Settings **/
    @FXML
    void saveAll_Settings(ActionEvent event) throws IOException, ClassNotFoundException
    {     	
    	//save settings
    	save_timeSettings();
    	
		
		//load recent changes
		load_timeSettings();
    }    
    public void save_timeSettings() throws IOException 
    {
    	int startIndex = app_startTime.getSelectionModel().getSelectedIndex(),
			endIndex = app_endTime.getSelectionModel().getSelectedIndex();
    	
    	boolean tue = tue_setToDefault.isSelected(), 
		    	wed = wed_setToDefault.isSelected(), 
		    	thur = thur_setToDefault.isSelected(), 
		    	fri = fri_setToDefault.isSelected(), 
		    	sat = sat_setToDefault.isSelected(), 
		    	sun = sun_setToDefault.isSelected(), 
		    	mon = mon_setToDefault.isSelected();    	
    	
    	//Instantiate object
    	timeSettings = new TimeSettings(startIndex, endIndex, tue, wed, thur, fri, sat, sun, mon); 
    	
    	//hand object to save method for writing to disk
    	fileSystem.saveTimeSettings(timeSettings);    	

    	//update save settings button
    	changes_made_text.setText("");
		changes_made_text.setFill(Color.BLACK);
		saveAll_settings.setDisable(true);		
    }
    public void load_timeSettings() throws IOException, ClassNotFoundException 
	{
		if(fileSystem.doesFileExist(fileSystem.getFilePath_timeSettings())) 
		{
			//load file into memory and push changes
			timeSettings = fileSystem.load_timeSettings();
			pushSettings_time(timeSettings);		
			setupLists_defaultSettings();
		}
		else //create new settings file if one doesn't exist
		{			
			//app settings			
			setTimesByDefault(app_startTime, 0, app_endTime, 0, false);				
			
			addDataToLists();
			save_timeSettings();
			timeSettings = fileSystem.load_timeSettings();
		}		
	}
    void pushSettings_time(TimeSettings fromFile_timeSettings) 
    {
    	//time settings
    	app_startTime.getSelectionModel().select(fromFile_timeSettings.getStartIndex());
    	app_endTime.getSelectionModel().select(fromFile_timeSettings.getEndIndex());
    	
    	startIndex = app_startTime.getSelectionModel().getSelectedIndex();
		endIndex = app_endTime.getSelectionModel().getSelectedIndex();		
		//scroll to index on settings tab
		app_startTime.scrollTo(startIndex);
		app_endTime.scrollTo(endIndex);		 
		
    	//which days to ignore default settings
    	tue_setToDefault.setSelected(fromFile_timeSettings.isTue_setByDefault());
    	wed_setToDefault.setSelected(fromFile_timeSettings.isWed_setByDefault());
    	thur_setToDefault.setSelected(fromFile_timeSettings.isThur_setByDefault());
    	fri_setToDefault.setSelected(fromFile_timeSettings.isFri_setByDefault());
    	sat_setToDefault.setSelected(fromFile_timeSettings.isSat_setByDefault());
    	sun_setToDefault.setSelected(fromFile_timeSettings.isSun_setByDefault());
    	mon_setToDefault.setSelected(fromFile_timeSettings.isMon_setByDefault());    
    }
    public void setupLists_defaultSettings() 
	{
		setTimesByDefault(tue_startTime, startIndex, tue_endTime, endIndex, tue_setToDefault.isSelected());
		setTimesByDefault(wed_startTime, startIndex, wed_endTime, endIndex, wed_setToDefault.isSelected());
		setTimesByDefault(thur_startTime, startIndex, thur_endTime, endIndex, thur_setToDefault.isSelected());
		setTimesByDefault(fri_startTime, startIndex, fri_endTime, endIndex, fri_setToDefault.isSelected());
		setTimesByDefault(sat_startTime, startIndex, sat_endTime, endIndex, sat_setToDefault.isSelected());
		setTimesByDefault(sun_startTime, startIndex, sun_endTime, endIndex, sun_setToDefault.isSelected());
		setTimesByDefault(mon_startTime, startIndex, mon_endTime, endIndex, mon_setToDefault.isSelected());
	}
    
    //checks for a change in settings from initial loading
    @FXML
    public boolean post_ChangeInSettings(MouseEvent event) 
    {    	
    	if(scan_timeSettingsChanges())
    	{
    		changes_made_text.setText("unsaved changes...");
    		changes_made_text.setFill(Color.RED);
    		saveAll_settings.setDisable(false);
    		
    		return true;
    	}    	
    	else
    	{
    		changes_made_text.setText("");
    		changes_made_text.setFill(Color.BLACK);
    		saveAll_settings.setDisable(true);
    		
    		return false;
    	}
    }       
    public boolean scan_timeSettingsChanges() 
    {
    	//timeSettings    	
    	if(app_startTime.getSelectionModel().getSelectedIndex() == timeSettings.getStartIndex()) {return true;}
    	if(app_endTime.getSelectionModel().getSelectedIndex() == timeSettings.getEndIndex()) {return true;}
    	
    	//week
    	if(tue_setToDefault.isSelected() == timeSettings.isTue_setByDefault()) 		{return true;}
    	if(wed_setToDefault.isSelected() == timeSettings.isWed_setByDefault()) 		{return true;}
    	if(thur_setToDefault.isSelected() == timeSettings.isThur_setByDefault()) 	{return true;}
    	if(fri_setToDefault.isSelected() == timeSettings.isFri_setByDefault()) 		{return true;}
    	if(sat_setToDefault.isSelected() == timeSettings.isSat_setByDefault()) 		{return true;}
    	if(sun_setToDefault.isSelected() == timeSettings.isSun_setByDefault()) 		{return true;}
    	if(mon_setToDefault.isSelected() == timeSettings.isMon_setByDefault()) 		{return true;}

    	return false;
    }

    /** Worker Settings **/
    @FXML
    void save_workerSettings(ActionEvent event) throws IOException 
    {
    	fileSystem.save_Workers(data.getWorkers());
    	save_WorkerSettings.setDisable(true);
    }
    void load_workerSettings() throws FileNotFoundException, IOException 
    {
    	if(fileSystem.doesFileExist(fileSystem.getWorkerFilePath())) 
    	{
    		data.setWorkers(fileSystem.load_Workers());
    	}
    }
    boolean validate_workerName(String workerName)
    {
    	if(isAlpha(workerName) && workerName != null && workerName.length() >0) 
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
    boolean validate_payRate(String pay_rate) 
    {
    	if(pay_rate != null && pay_rate.length() > 0 && checkForValidNumbers(pay_rate))
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}    	
    }
    
    @FXML
    void remove_worker(ActionEvent event) throws IOException 
    {
    	infoBox_removeWorker("", "Deleting A Worker", "You're about to permanently delete a worker!\n\nAre you sure you want to continue?", AlertType.CONFIRMATION);
    }
    void infoBox_removeWorker(String infoMessage, String titleBar, String headerMessage, AlertType type) throws IOException
    {
        Alert alert = new Alert(type);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.setContentText(infoMessage);       
        
        Optional<ButtonType> result = alert.showAndWait();
        if(!result.isPresent() || result.get() == ButtonType.OK)
        {        	
        	//remove worker
        	String[] worker = settings_worker_list.getSelectionModel().getSelectedItem().split("\n");
        	data.workers.remove(worker[0]);
        	
        	//update list & textField
        	populateWorkerList();
        	payRate.setText("");
			workerName.setText("");
			
			
			//disable button
        	remove_Worker.setDisable(true);
        	
        	
        	//saves changes to disk
        	fileSystem.save_Workers(data.getWorkers());
        	save_WorkerSettings.setDisable(true);
        }
    }    
    @FXML
    void add_worker(ActionEvent event) 
    {
    	String worker_Name; 
		double pay_rate;
		
		if( validate_workerName(workerName.getText()) && validate_payRate(payRate.getText())) 
		{
			worker_Name = workerName.getText();
			pay_rate = Double.parseDouble(payRate.getText());
			
			data.workers.put(worker_Name, pay_rate);
			
			//update worker list
			populateWorkerList();
			payRate.setText("");
			workerName.setText("");
			save_WorkerSettings.setDisable(false);
		}
		else
		{
			
		}
    }
   
    //settings tab
    public void putValuesInTextFields(String menAndValue) 
    {
    	if(menAndValue != null) 
    	{
    		String[] menInfo = menAndValue.split("\n");
        	String  worker_name = menInfo[0],
        			worker_payRate = menInfo[1].substring(1,  5);
        	
        	workerName.setText(worker_name);
    		payRate.setText(worker_payRate);
    	}
    }


    public boolean checkForValidNumbers(String value) 
    {
    	String 	regex = "^\\d*\\.\\d+|\\d+\\.\\d*$";

		if(value.matches(regex))
		    return true;
		else
		    return false;
    }
    public static boolean isAlpha(String str) 
    {
        return ((!str.equals("")) && (str != null) && (str.matches("^[a-zA-Z\s]*$")));
    }
    
    /** Printing Tab
     * @throws IOException **/
    @FXML
    void add_to_Report(ActionEvent event) throws IOException
    {	
    	if(workerList.getSelectionModel().getSelectedItem() != null) 
    	{    		
    		String 	worker = workerList.getSelectionModel().getSelectedItem(),
        			tue = tue_totalHours.getText(),
        			wed = wed_totalHours.getText(),
        			thur = thur_totalHours.getText(),
        			fri = fri_totalHours.getText(),
        			sat = sat_totalHours.getText(),
        			sun = sun_totalHours.getText(),
        			mon = mon_totalHours.getText(),
        			weekTotal = weekTotalHours.getText(),
        			hourlyRate = hourly_rate.getText(),
        			total = totalOwedTo_worker.getText();
        	
        	//send worker info to reports
        	printing.send_to_reports(worker, tue, wed, thur, fri, sat, sun, mon, weekTotal, hourlyRate, total, weekly_report_list);
        	infoBox_addToReport("", "Worker Added To Report", workerList.getSelectionModel().getSelectedItem() +" -  was added to the report.", AlertType.INFORMATION);
        	printingAndReports.setDisable(false);
    	}
    	else
    	{
    		infoBox_addToReport("", "Adding Worker Details to Report", "No worker selected for report. ", AlertType.INFORMATION);
    	}
    }
    void infoBox_addToReport(String infoMessage, String titleBar, String headerMessage, AlertType type) throws IOException
    {
        Alert alert = new Alert(type);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.setContentText(infoMessage);       
        
        Optional<ButtonType> result = alert.showAndWait();
        if(!result.isPresent() || result.get() == ButtonType.OK)
        {        	
        	
        }
    }
    @FXML
    void remove_worker_onReportLIst(ActionEvent event) throws IOException 
    {
    	if(weekly_report_list.getSelectionModel().getSelectedIndex() > 0) 
    	{    		
    		infoBox_addToReport("", "Removing Worker From Report", weekly_report_list.getSelectionModel().getSelectedItem() + " has been removed from the report list", AlertType.INFORMATION);
    		weekly_report_list.getItems().remove(weekly_report_list.getSelectionModel().getSelectedIndex());
    		printing.getWeekReport_list().remove(weekly_report_list.getSelectionModel().getSelectedIndex());	    		
    	}
    }
    
    
    @FXML
    void print_report(ActionEvent event) throws IOException
    {
    	printing.sendTo_printer(weekly_report_list);
    }    
    @FXML
    void saveAs_pdf(ActionEvent event) throws IOException 
    {
    	printing.saveAsFile(weekly_report_list);
    }
    
}
   