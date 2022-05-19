package settings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class FileSystem
{
	private String filePath_timeSettings = "time_settings.gbw";
	
	public void objectToFile(Workers worker) throws IOException 
	{		
		FileOutputStream fileOutputStream = new FileOutputStream(filePath_timeSettings);
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
		objectOutputStream.writeObject(worker);
		
		objectOutputStream.close();		
	}
	
	public boolean doesFileExist(String file) throws IOException 
	{
		File f = new File(file);
		return (f.exists());  
	}
		
	public void saveTimeSettings(TimeSettings timeSettings) throws IOException 
	{		
		FileOutputStream fileOutputStream = new FileOutputStream(filePath_timeSettings);
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
		objectOutputStream.writeObject(timeSettings);
		
		objectOutputStream.close();	
	}	
	public TimeSettings load_timeSettings() throws IOException, ClassNotFoundException
	{
		FileInputStream fileInputStream = new FileInputStream(getFilePath_timeSettings());
		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		return (TimeSettings) objectInputStream.readObject();
	}

	/** Worker settings **/
	String workerFilePath = "workers.properties";	
	public void save_Workers(Map<String, Double> all_workers) throws IOException 
	{		
		Properties properties = new Properties();

		for (Map.Entry<String, Double> entry : all_workers.entrySet())
		{
		    properties.put(entry.getKey(), entry.getValue()+"");
		}

		properties.store(new FileOutputStream("workers.properties"), null);
		System.out.println("Changes saved to file");
	}	
	

	public Map<String, Double> load_Workers() throws FileNotFoundException, IOException
	{	
		Map<String, Double> all_workers = new HashMap<String, Double>();
		Properties properties = new Properties();
		properties.load(new FileInputStream("workers.properties"));

		for (String key : properties.stringPropertyNames())
		{
			all_workers.put(key, Double.parseDouble((String) properties.get(key)));
		}
		
		return all_workers;
	}
	
	//setters and getters	
	public String getFilePath_timeSettings() 							{ return filePath_timeSettings;	}
	public String getWorkerFilePath() 									{ return workerFilePath;}
	public void setWorkerFilePath(String workerFilePath) 				{ this.workerFilePath = workerFilePath;}
	public void setFilePath_timeSettings(String filePath_timeSettings) 	{ this.filePath_timeSettings = filePath_timeSettings;	}
}
