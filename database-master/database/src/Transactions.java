import java.math.BigDecimal;
import java.sql.Timestamp;

public class Transactions {
    
   
    protected int transactionNumber;
    protected String transactionType;  
    protected double  ppsbalance; 
    protected double  dollarbalance;
    protected String orginid;
    protected String destinationid;   
    protected Timestamp transtimestamp;
    
    
	public Transactions(int transactionNumber, String transactionType, double ppsbalance, double dollarbalance, String orginid,
			String destinationid, Timestamp transtimestamp) {
		super();
		this.transactionNumber = transactionNumber;
		this.transactionType = transactionType;
		this.ppsbalance = ppsbalance;
		this.dollarbalance = dollarbalance;
		this.orginid = orginid;
		this.destinationid = destinationid;
		this.transtimestamp = transtimestamp;
	}
	
	public double getDollarbalance() {
		return dollarbalance;
	}


	public void setDollarbalance(double dollarbalance) {
		this.dollarbalance = dollarbalance;
	}


	public int getTransactionNumber() {
		return transactionNumber;
	}
	
	public void setTransactionNumber(int transactionNumber) {
		this.transactionNumber = transactionNumber;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public double getPpsbalance() {
		return ppsbalance;
	}
	public void setPpsbalance(double ppsbalance) {
		this.ppsbalance = ppsbalance;
	}
	public String getOrginid() {
		return orginid;
	}
	public void setOrginid(String orginid) {
		this.orginid = orginid;
	}
	public String getDestinationid() {
		return destinationid;
	}
	public void setDestinationid(String destinationid) {
		this.destinationid = destinationid;
	}
	public Timestamp getTranstimestamp() {
		return transtimestamp;
	}
	public void setTranstimestamp(Timestamp transtimestamp) {
		this.transtimestamp = transtimestamp;
	}
}
     