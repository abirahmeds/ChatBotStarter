import java.util.Scanner;

/**
 * A simple class to run our chatbot teams.
 * @author Brooklyn Tech CS Department
 * @version September 2018
 */
public class ChatBotRunner {

	/**
	 * Create instances of each chatbot, give it user input, and print its replies. Switch chatbot responses based on which chatbot the user is speaking too.
	 */
	public static void main(String[] args) {
		ChatBot1 chatbot1 = new ChatBot1();
		ChatBot2 chatbot2 = new ChatBot2();
		ChatBot3 chatbot3 = new ChatBot3();


		Scanner in = new Scanner(System.in);
		System.out.println("Hi! I'm Social Media Chatbot!");
		String statement = in.nextLine();
		System.out.println("Which do you like best Instagram, Facebook, or Snapchat.");
		statement = in.nextLine();

		while (!statement.toLowerCase().equals("bye")) {
			{
				while (findKeyword(statement, "instagram", 0)) {
					chatbot1.chatLoop(statement);
					statement = in.nextLine();
				}
				while (findKeyword(statement, "facebook", 0)) {
					chatbot2.chatLoop(statement);
					statement = in.nextLine();
				}
				while (findKeyword(statement, "snapchat", 0)) {
					chatbot3.chatLoop(statement);
					statement = in.nextLine();
				}
				while (findKeyword(statement, "myspace", 0)) {
					System.out.println("LOL, no one uses that anymore!");
					chatbot1.chatLoop(statement);
					statement = in.nextLine();

				}
				while (findKeyword(statement, "none", 0)) {
					System.out.println("C'mon you have to use something?!?");
					chatbot2.chatLoop(statement);
					statement = in.nextLine();
				}
				while (findKeyword(statement, "no", 0)) {
					System.out.println("C'mon you have to use something?!?");
					chatbot1.chatLoop(statement);
					statement = in.nextLine();
				}
			}
		}
	}
	private static boolean findKeyword(String statement, String goal,
							int startPos)
	{
		String phrase = statement.trim().toLowerCase();
		goal = goal.toLowerCase();

		// The only change to incorporate the startPos is in
		// the line below
		int psn = phrase.indexOf(goal, startPos);

		// Refinement--make sure the goal isn't part of a
		// word
		while (psn >= 0)
		{
			// Find the string of length 1 before and after
			// the word
			String before = " ", after = " ";
			if (psn > 0)
			{
				before = phrase.substring(psn - 1, psn);
			}
			if (psn + goal.length() < phrase.length())
			{
				after = phrase.substring(
						psn + goal.length(),
						psn + goal.length() + 1);
			}

			// If before and after aren't letters, we've
			// found the word
			if (((before.compareTo("a") < 0) || (before
					.compareTo("z") > 0)) // before is not a
					// letter
					&& ((after.compareTo("a") < 0) || (after
					.compareTo("z") > 0)))
			{
				return true;
			}

			// The last position didn't work, so let's find
			// the next, if there is one.
			psn = phrase.indexOf(goal, psn + 1);

		}

		return false;
	}
}

