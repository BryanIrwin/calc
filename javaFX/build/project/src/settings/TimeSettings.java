package settings;

import java.io.Serializable;

public class TimeSettings  implements Serializable
{
	private static final long serialVersionUID = 1L;
	int startIndex, endIndex;
	boolean 	tue_setByDefault,
				wed_setByDefault,
				thur_setByDefault,
				fri_setByDefault,
				sat_setByDefault,
				sun_setByDefault,
				mon_setByDefault;
	
	public TimeSettings(int startIndex, int endIndex, boolean tue_setByDefault, boolean wed_setByDefault, boolean thur_setByDefault, 
			boolean fri_setByDefault, boolean sat_setByDefault, boolean sun_setByDefault, boolean mon_setByDefault) {		
		this.startIndex = startIndex;
		this.endIndex = endIndex;
		this.tue_setByDefault = tue_setByDefault;
		this.wed_setByDefault = wed_setByDefault;
		this.thur_setByDefault = thur_setByDefault;
		this.fri_setByDefault = fri_setByDefault;
		this.sat_setByDefault = sat_setByDefault;
		this.sun_setByDefault = sun_setByDefault;
		this.mon_setByDefault = mon_setByDefault;
	}

	//setters and getters
	public int getStartIndex() 									{ return startIndex;}
	public void setStartIndex(int startIndex) 					{ this.startIndex = startIndex;}
	public int getEndIndex() 									{ return endIndex;}
	public void setEndIndex(int endIndex) 						{ this.endIndex = endIndex;}
	public boolean isTue_setByDefault() 						{ return tue_setByDefault;}
	public void setTue_setByDefault(boolean tue_setByDefault) 	{ this.tue_setByDefault = tue_setByDefault;}
	public boolean isWed_setByDefault() 						{ return wed_setByDefault;}
	public void setWed_setByDefault(boolean wed_setByDefault) 	{ this.wed_setByDefault = wed_setByDefault;}
	public boolean isThur_setByDefault() 						{ return thur_setByDefault;}
	public void setThur_setByDefault(boolean thur_setByDefault) { this.thur_setByDefault = thur_setByDefault;}
	public boolean isFri_setByDefault() 						{ return fri_setByDefault;}
	public void setFri_setByDefault(boolean fri_setByDefault) 	{ this.fri_setByDefault = fri_setByDefault;}
	public boolean isSat_setByDefault() 						{ return sat_setByDefault;}
	public void setSat_setByDefault(boolean sat_setByDefault) 	{ this.sat_setByDefault = sat_setByDefault;}
	public boolean isSun_setByDefault() 						{ return sun_setByDefault;}
	public void setSun_setByDefault(boolean sun_setByDefault) 	{ this.sun_setByDefault = sun_setByDefault;}
	public boolean isMon_setByDefault() 						{ return mon_setByDefault;}
	public void setMon_setByDefault(boolean mon_setByDefault)	{ this.mon_setByDefault = mon_setByDefault;}		
}