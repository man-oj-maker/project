package manoj.EmailGroups;

import java.io.*;
import java.text.*;
import java.util.*;

public class readCSV
{
	public List<String[]> readCSVFile(final String filename) throws IOException
	{
		List<String[]> data = new ArrayList<>();
		try(BufferedReader br = new BufferedReader(new FileReader(filename)))
		{
			String line;
			while((line = br.readLine()) != null)
			{
				data.add(line.split(","));
			}
		}
		catch(FileNotFoundException e)
		{
			System.out.println(e.getMessage());
		}

		return data;
	}

	public List<EmailData> readEmail(final String filename) throws IOException, ParseException
	{
		List<String[]> data = readCSVFile(filename);
		List<EmailData> emailData = new ArrayList<>();
		for(int i=1; i<data.size(); i++)
		{
			String emailID = data.get(i)[0];
			String senderID = data.get(i)[1];
			String receiverIDs = data.get(i)[2];
			String[] receiverID = receiverIDs.split(" ");
     		Date date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(data.get(i)[3]);
     		String keyword = data.get(i)[4];
     		String[] keywords = keyword.split(" ");
     		String reply = data.get(i)[5];

     		emailData.add(new EmailData(emailID,senderID,receiverID,date,keywords,reply));
		}
		return emailData;
	}

	public List<String> readKeywords(final String filename) throws IOException
	{
		List<String> data = new ArrayList<>();
		try(BufferedReader br = new BufferedReader(new FileReader(filename)))
		{
			String line;
			while((line = br.readLine()) != null)
			{
				data.add(line);
			}
		}
		catch(FileNotFoundException e)
		{
			System.out.println(e.getMessage());
		}
		return data;
	}


	public List<String[]> readPersons(final String filename) throws IOException
	{
		return readCSVFile(filename);
	}
}