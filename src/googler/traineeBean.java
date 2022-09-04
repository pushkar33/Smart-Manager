package googler;

public class traineeBean {
	
	String tid;
	String name;
	String mobile;
	String college;
	String sem;
	String branch;
	String technology;
	String batchdate;
	String batchtime;
	int totalfee;
	int advancefee;
	int balance;
	String picpath;
	String dofadmn;
	
	// Constructor
	
	public traineeBean(String tid, String name, String mobile, String college, String sem, String branch,
			String technology, String batchdate, String batchtime, int totalfee, int advancefee, int balance,
			String picpath, String dofadmn) {
		super();
		this.tid = tid;
		this.name = name;
		this.mobile = mobile;
		this.college = college;
		this.sem = sem;
		this.branch = branch;
		this.technology = technology;
		this.batchdate = batchdate;
		this.batchtime = batchtime;
		this.totalfee = totalfee;
		this.advancefee = advancefee;
		this.balance = balance;
		this.picpath = picpath;
		this.dofadmn = dofadmn;
	}
	
	// Getters and Setters

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getSem() {
		return sem;
	}

	public void setSem(String sem) {
		this.sem = sem;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getTechnology() {
		return technology;
	}

	public void setTechnology(String technology) {
		this.technology = technology;
	}

	public String getBatchdate() {
		return batchdate;
	}

	public void setBatchdate(String batchdate) {
		this.batchdate = batchdate;
	}

	public String getBatchtime() {
		return batchtime;
	}

	public void setBatchtime(String batchtime) {
		this.batchtime = batchtime;
	}

	public int getTotalfee() {
		return totalfee;
	}

	public void setTotalfee(int totalfee) {
		this.totalfee = totalfee;
	}

	public int getAdvancefee() {
		return advancefee;
	}

	public void setAdvancefee(int advancefee) {
		this.advancefee = advancefee;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getPicpath() {
		return picpath;
	}

	public void setPicpath(String picpath) {
		this.picpath = picpath;
	}

	public String getDofadmn() {
		return dofadmn;
	}

	public void setDofadmn(String dofadmn) {
		this.dofadmn = dofadmn;
	}
	
	
	
	
	
	
	

}
