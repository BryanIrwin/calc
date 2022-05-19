package settings;

public class Report_Item 
{
	String 	worker, 
			tue, wed, thur, fri, sat, sun, mon, 
			weekTotal, hourlyRate, grandTotal;

	public Report_Item(String worker, String tue, String wed, String thur, String fri, String sat, String sun,
			String mon, String weekTotal, String hourlyRate, String grandTotal) {
		super();
		this.worker = worker;
		this.tue = tue;
		this.wed = wed;
		this.thur = thur;
		this.fri = fri;
		this.sat = sat;
		this.sun = sun;
		this.mon = mon;
		this.weekTotal = weekTotal;
		this.hourlyRate = hourlyRate;
		this.grandTotal = grandTotal;
	}

	
	public String getWorker() {	return worker;}
	public void setWorker(String worker) {		this.worker = worker;	}
	public String getTue() {		return tue;	}
	public void setTue(String tue) {		this.tue = tue;	}
	public String getWed() {		return wed;	}
	public void setWed(String wed) {		this.wed = wed;	}
	public String getThur() {		return thur;	}
	public void setThur(String thur) {		this.thur = thur;	}
	public String getFri() {		return fri;	}
	public void setFri(String fri) {		this.fri = fri;	}
	public String getSat() {		return sat;	}
	public void setSat(String sat) {		this.sat = sat;	}
	public String getSun() {		return sun;	}
	public void setSun(String sun) {		this.sun = sun;	}
	public String getMon() {		return mon;	}
	public void setMon(String mon) {		this.mon = mon;	}
	public String getWeekTotal() {		return weekTotal;	}
	public void setWeekTotal(String weekTotal) {		this.weekTotal = weekTotal;	}
	public String getHourlyRate() {		return hourlyRate;	}
	public void setHourlyRate(String hourlyRate) {		this.hourlyRate = hourlyRate;	}
	public String getGrandTotal() {		return grandTotal;	}
	public void setGrandTotal(String grandTotal) {		this.grandTotal = grandTotal;	}
}