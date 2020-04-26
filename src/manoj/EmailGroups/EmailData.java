package manoj.EmailGroups;

import java.util.*;

public class EmailData
{
	private String emailId;
	private String senderId;
	private String[] receieverList;
	private Date dateSent;
	private String[] keywords;
	private String replyId;

	public EmailData(final String emailId, final String senderId,
					 final String[] receieverIDs, final Date date,
					 final String[] keyword, final String reply)
	{
		this.emailId = emailId;
		this.senderId = senderId;
		this.receieverList = receieverIDs;
		this.dateSent = date;
		this.keywords = keyword;
		this.replyId = reply;
	}

	public String getEmailId()
	{
		return emailId;
	}

	public String getSenderId()
	{
		return senderId;
	}

	public String[] getReceiverId()
	{
		return receieverList;
	}

	public Date getDate()
	{
		return dateSent;
	}

	public String[] getKeywords()
	{
		return keywords;
	}

	public String getReplyId()
	{
		return replyId;
	}
}