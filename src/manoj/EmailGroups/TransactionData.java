package manoj.EmailGroups;

public class TransactionData
{
	private String senderId;
	private String receiverId;
	private String[] keywords;

	public TransactionData(final String senderId,
							final String receiverId,
							final String[] keywords)
	{
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.keywords = keywords;
	}

	public String getSenderId()
	{
		return senderId;		
	}

	public String getReceiverId()
	{
		return receiverId;		
	}

	public String[] getKeywords()
	{
		return keywords;		
	}
}