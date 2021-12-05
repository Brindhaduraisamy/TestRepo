

public class Account {
	protected String userid;   
    protected double  ppsbalance;
    protected double  dollarbalance;
   
    public Account(String userid, double ppsbalance, double dollarbalance) {
		super();
		this.userid = userid;
		this.ppsbalance = ppsbalance;
		this.dollarbalance = dollarbalance;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public double getPpsbalance() {
		return ppsbalance;
	}
	public void setPpsbalance(double ppsbalance) {
		this.ppsbalance = ppsbalance;
	}
	public double getDollarbalance() {
		return dollarbalance;
	}
	public void setDollarbalance(double dollarbalance) {
		this.dollarbalance = dollarbalance;
	}
	
	
}
     