package manoj.EmailGroups;
import java.io.IOException;
import java.text.ParseException;

public class Main {

	public static void main(final String[] args) throws IOException, ParseException {
		EmailGroups emailGroups = new EmailGroups(
				args[0],
				args[1],
				args[2]);
		emailGroups.printEmailGroups();
	}
}
