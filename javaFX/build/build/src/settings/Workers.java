package settings;

import java.io.Serializable;
import java.util.Map;

public class Workers implements Serializable
{
	private static final long serialVersionUID = 2L;
	
	public Map<String, Double> workers_map_file;

	public Workers(Map<String, Double> workers_map_file) 
	{		
		this.workers_map_file = workers_map_file;
	}
	
	public Map<String, Double> getWorkers_map_file() {return workers_map_file; }
	public void setWorkers_map_file(Map<String, Double> workers_map_file){this.workers_map_file = workers_map_file;}
}