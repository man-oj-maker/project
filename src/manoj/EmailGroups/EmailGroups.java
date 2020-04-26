package manoj.EmailGroups;

import java.io.IOException;
import java.time.*;
import java.util.*;
import java.util.stream.Collectors;
import java.text.ParseException;

public class EmailGroups
{

	private List<String[]> peoples;
	private List<String> words;
	private List<EmailData> emails;
	private List<TransactionData> emailTransac;

	private List<List<String>> emailGroups;

	EmailGroups(final String email, final String keywords,
			final String person) throws IOException, ParseException
	{
		readCSV readcsv = new readCSV();
		this.peoples = readcsv.readPersons(person);
		this.words = readcsv.readKeywords(keywords);
		this.emails = readcsv.readEmail(email);
		this.emailTransac = emailTransactions();
		this.emailGroups = groupEmails();
	}


	private List<TransactionData> emailTransactions()
	{
		List<TransactionData> result = new ArrayList<>();
		int countDay = 30;
		LocalDate date = LocalDate.now().minusDays(countDay);
		for(EmailData email : emails)
		{
			if(!email.getDate().before(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant())))
			{
				for(int j=0; j<email.getReceiverId().length; j++)
				{
					result.add(new TransactionData(email.getSenderId(), email.getReceiverId()[j], email.getKeywords()));
				}

				if(!email.getReplyId().equals("null"))
				{
					result.add(new TransactionData(email.getReplyId(), email.getEmailId(),email.getKeywords()));
				}
			}
		}
		return result;
	}

	private List<List<String>> groupEmails()
	{
		List<List<String[]>> list = mergeList();
		List<List<String>> result = new ArrayList<>();
		for(List<String[]> s1 : list)
		{
			List<String> emailGroup = new ArrayList<>();
			for(String[] s2 : s1)
			{
				int checkLast = 5;
				if(Integer.parseInt(s2[2]) >= checkLast)
				{
					emailGroup.add(s2[0]);
					emailGroup.add(s2[1]);
				}
			}
			result.add(emailGroup.stream().distinct().collect(Collectors.toList()));
		}
		return result.stream().distinct().collect(Collectors.toList());
	}


	private List<List<String[]>> mergeList()
	{
		List<List<String[]>> list = emailGroupsList();
		List<List<String[]>> result = new ArrayList<>();
		for(List<String[]> strings : list)
		{
			List<String[]> l = new ArrayList<>();
			List<String[]> l2 = new ArrayList<>();
			if(strings.size() != 0)
			{
				for (String[] string : strings)
				{
					boolean check = false;
					if (l.size() == 0) 
					{
						String[] addNew = {string[0], string[1], "1"};
						l.add(addNew);
						l2.add(addNew);
					}
					int i = 0;
					for (String[] newLists1 : l) 
					{
						if (newLists1[0].equals(string[0]) && newLists1[1].equals(string[1])) 
						{
							int count = Integer.parseInt(l2.get(i)[2]) + 1;
							String[] str = {string[0], string[1], String.valueOf(count)};
							l2.set(i, str);
							check = true;
						} 
						else if (newLists1[0].equals(string[1]) && newLists1[1].equals(string[0])) 
						{
							int count = Integer.parseInt(l2.get(i)[2]) + 1;
							String[] str = {string[0], string[1], String.valueOf(count)};
							l2.set(i, str);
							check = true;
						}
						i++;if (check == false) 
						{
							String[] addNew = {string[0], string[1], "1"};
							l.add(addNew);
							l2.add(addNew);
						}
					}
					if (check == false) 
					{
						String[] addNew = {string[0], string[1], "1"};
						l.add(addNew);
						l2.add(addNew);
					}
				}
			}
			result.add(l2);
		}
		return result;
	}


	private List<List<String[]>> emailGroupsList()
	{
		List<List<String[]>> result = new ArrayList<>(words.size());
		for(String keyword : words)
		{
			List<String[]> string  = new ArrayList<>();
			for(TransactionData emailTransaction : emailTransac)
			{
				for(int i=0;i<emailTransaction.getKeywords().length; i++)
				{
					if(keyword.equals(emailTransaction.getKeywords()[i]))
					{
						String[] s = {emailTransaction.getSenderId(), emailTransaction.getReceiverId()};
						string.add(s);
					}
				}
			}
			result.add(string);
		}
		return result;
	}

	public List<String[]> getPeoples()
	{
		return peoples;
	}

	public void printPeoples() {
		for (String[] people : peoples) {
			System.out.printf("%-30s %10s %30s \n",
					people[0],
					people[1],
					people[2]);
		}
	}

	public List<String> getKeywords() {
		return words;
	}
	public void printKeywords() {
		for (String word : words) {
			System.out.printf("%s \n", word);
		}
	}

	public List<EmailData> getEmails() {
		return emails;
	}

	public List<TransactionData> getEmailTransactions() {
		return emailTransac;
	}

	public void printEmailTransactions() {
		for (TransactionData emailTransaction : emailTransac) {
			System.out.printf("%-30s %10s %30s \n",
					emailTransaction.getReceiverId(),
					emailTransaction.getSenderId(),
					Arrays.toString(emailTransaction.getKeywords()));
		}
	}
	public List<List<String>> getEmailGroups() {
		return emailGroups;
	}

	public void printEmailGroups() {
		for (List<String> emailGroup : emailGroups) {
			if (emailGroup.size() != 0) {
				System.out.print("[\t");
				for (String emailGroup1 : emailGroup) {
					System.out.print(emailGroup1 + "\t");
				}
				System.out.print("]");
				System.out.println();
			}
		}
	}
}