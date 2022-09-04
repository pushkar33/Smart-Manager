package batchManager;

public class batchBean {
	
	String batch;
	String sdate;
	String stime;
	int tseats;
	int booked;
	int fee;
	
   
    // Constructor
	
	public batchBean(String batch, String sdate, String stime, int tseats, int booked, int fee) {
		super();
		this.batch = batch;
		this.sdate = sdate;
		this.stime = stime;
		this.tseats = tseats;
		this.booked = booked;
		this.fee = fee;
	}
	
	
	
	// Getters and Setters
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public String getStime() {
		return stime;
	}
	public void setStime(String stime) {
		this.stime = stime;
	}
	public int getTseats() {
		return tseats;
	}
	public void setTseats(int tseats) {
		this.tseats = tseats;
	}
	public int getBooked() {
		return booked;
	}
	public void setBooked(int booked) {
		this.booked = booked;
	}
	public int getFee() {
		return fee;
	}
	public void setFee(int fee) {
		this.fee = fee;
	}
	
	

}
