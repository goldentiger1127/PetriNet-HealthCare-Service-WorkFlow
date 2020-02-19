package jinhu;

public class HistoryRecord {
	
	private String TransactionName;
	
	private long DelayTime;
	
	public HistoryRecord(String transactionName, long delayTime) {
		super();
		TransactionName = transactionName;
		DelayTime = delayTime;
	}

	public String getTransactionName() {
		return TransactionName;
	}

	public void setTransactionName(String transactionName) {
		TransactionName = transactionName;
	}

	public long getDelayTime() {
		return DelayTime;
	}

	public void setDelayTime(long delayTime) {
		DelayTime = delayTime;
	}
	
	

}
