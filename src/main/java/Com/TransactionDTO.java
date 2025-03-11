package Com;

import java.sql.Date;

public class TransactionDTO {private int txnId;
	private Date txnDateTime;
	private int sourceId;
	private int targetId;
	private String sourceType;
	private String destType;
	private double txnAmount;
	
public TransactionDTO(int txnId, Date txnDateTime, int sourceId, int targetId, String sourceType, String destType,
		double txnAmount) {
	super();
	this.txnId = txnId;
	this.txnDateTime = txnDateTime;
	this.sourceId = sourceId;
	this.targetId = targetId;
	this.sourceType = sourceType;
	this.destType = destType;
	this.txnAmount = txnAmount;
}
public int getTxnId() {
	return txnId;
}
public void setTxnId(int txnId) {
	this.txnId = txnId;
}
public Date getTxnDateTime() {
	return txnDateTime;
}
public void setTxnDateTime(Date txnDateTime) {
	this.txnDateTime = txnDateTime;
}
public int getSourceId() {
	return sourceId;
}
public void setSourceId(int sourceId) {
	this.sourceId = sourceId;
}
public int getTargetId() {
	return targetId;
}
public void setTargetId(int targetId) {
	this.targetId = targetId;
}
public String getSourceType() {
	return sourceType;
}
public void setSourceType(String sourceType) {
	this.sourceType = sourceType;
}
public String getDestType() {
	return destType;
}
public void setDestType(String destType) {
	this.destType = destType;
}
public double getTxnAmount() {
	return txnAmount;
}
public void setTxnAmount(double txnAmount) {
	this.txnAmount = txnAmount;
}
	

}
