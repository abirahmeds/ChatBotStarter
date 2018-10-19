import java.util.Random;
import java.util.Scanner;

/**
 * A program to carry on conversations with a human user.
 * This version:
 * @author Brooklyn Tech CS Department
 * @version September 2018
 */
public class ChatBot3
{
	//emotion can alter the way our bot responds. Emotion can become more negative or positive over time.
	int emotion = 0;

	/**
	 * Runs the conversation for this particular chatbot, should allow switching to other chatbots.
	 * @param statement the statement typed by the user
	 */
	public void chatLoop(String statement)
	{
		Scanner in = new Scanner (System.in);
		System.out.println (getGreeting());


		while (!statement.equals("Bye"))
		{


			statement = in.nextLine();
			//getResponse handles the user reply
			System.out.println(getResponse(statement));


		}

	}
	/**
	 * Get a default greeting
	 * @return a greeting
	 */
	public String getGreeting()
	{
		return "Do you use Snapchat all the time?";
	}

	/**
	 * Gives a response to a user statement
	 *
	 * @param statement
	 *            the user statement
	 * @return a response based on the rules given
	 */
	public String getResponse(String statement)
	{
		String response = "";

		if (statement.length() == 0)
		{
			response = "Please say something.";
			emotion--;
		}
		else if (findKeyword(statement, "yeah") >= 0)
		{
			response = "Me too. Over 400 million snapchat stories are shared everyday";
		}
		else if (findKeyword(statement, "what") >= 0)
		{
			response = "Do you streak with other people often? Say yup if you do.";
		}
		else if (findKeyword(statement, "yup") >= 0)
		{
			response = "As of May 15th 2018, the longest Snapchat streak is 1,120 snaps. Say wow if you think that is long?";
		}
		else if (findKeyword(statement, "wow") >= 0)
		{
			response = "I KNOW RIGHT! Say yuh if you use snapchat filters";
		}
		else if (findKeyword(statement, "yuh") >= 0)
		{
			response = "Filters are so fun. My favorite is the puppy face one. What's your favorite one?";
		}
		else if (findKeyword(statement, "no") >= 0)
		{
			response = "Why? Snapchat is a fun way to share your everyday activites with friends.";
		}
		else if (findKeyword(statement, "tell me a fact") >= 0)
		{
			response = "Drinks are the most snapchatted item in the world.";
			emotion++;
		}
		else if (findKeyword(statement, "yer") >= 0)
		{
			response = "528k snaps are sent every minute. Type in yerr with two r's for another fact";
			emotion++;
		}
		else if (findKeyword(statement, "yerr") >= 0)
		{
			response = "Snapchat has 187m active daily users.";
			emotion++;
		}
		else if (findKeyword(statement, "yes") >= 0)
		{
			response = "Me too man, I snapchat everything I do.";
			emotion++;
		}
		else if (findKeyword(statement, "yea") >= 0)
		{
			response = "Me too. I spend so much time on it. On average, people spend 34.5 minutes per day on Snapchat and send 34.1 message a day.";
			emotion++;
		}
		else if (findKeyword(statement, "ugly") >= 0)
		{
			response = "No you're not. Just add a snapchat filter for more confidence.";
			emotion++;
		}
		else if (findKeyword(statement, "i dont know") >= 0)
		{
			response = "What do you mean????????";
			emotion--;
		}
		else if (findKeyword(statement, "i don't know") >= 0)
		{
			response = "What do you mean????????";
			emotion--;
		}
		else if (findKeyword(statement, "idk") >= 0)
		{
			response = "What do you mean????????";
			emotion++;
		}
		else if (findKeyword(statement, "sometimes") >= 0)
		{
			response = "Why not more often?";
			emotion++;
		}
		else if (findKeyword(statement, "maybe") >= 0)
		{
			response = "YES OR NO!!";
			emotion--;
		}
		else if (findKeyword(statement, "shut up") >= 0)
		{
			System.out.println("I didn't want to talk to you anyways.");
			System.exit(1);
		}
		else if (findKeyword(statement, "go away") >= 0)
		{
			System.out.println("I didn't want to talk to you anyways.");
			System.exit(1);
		}

		// Response transforming I want to statement
		else if (findKeyword(statement, "I want to", 0) >= 0)
		{
			response = transformIWantToStatement(statement);
		}
		else if (findKeyword(statement, "I want",0) >= 0)
		{
			response = transformIWantStatement(statement);
		}
		else if (findKeyword(statement, "I want you", 0) >= 0)
		{
			response = transformIYouStatement(statement);
		}
		else if (findKeyword(statement, "I hate",0) >= 0)
		{
			response = transformIHateToStatement(statement);

		}
		else if (findKeyword(statement, "because",0) >= 0)
		{
			response = transformBecauseStatement(statement);

		}
		else
		{
			response = getRandomResponse();
		}

		return response;
	}

	/**
	 * Take a statement with "I want to <something>." and transform it into
	 * "Why do you want to <something>?"
	 * @param statement the user statement, assumed to contain "I want to"
	 * @return the transformed statement
	 */
	private String transformIWantToStatement(String statement)
	{
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		int psn = findKeyword (statement, "I want to", 0);
		String restOfStatement = statement.substring(psn + 9).trim();
		return "Why do you want to " + restOfStatement + "?";
	}


	/**
	 * Take a statement with "I want <something>." and transform it into
	 * "Would you really be happy if you had <something>?"
	 * @param statement the user statement, assumed to contain "I want"
	 * @return the transformed statement
	 */
	private String transformIWantStatement(String statement)
	{
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		int psn = findKeyword (statement, "I want", 0);
		String restOfStatement = statement.substring(psn + 7).trim();
		return "Would you really be happy if you had " + restOfStatement + "?";
	}




	/**
	 * Take a statement with "I <something> you" and transform it into
	 * "Why do you <something> me?"
	 * @param statement the user statement, assumed to contain "I" followed by "you"
	 * @return the transformed statement
	 */
	private String transformIYouStatement(String statement)
	{
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}

		int psnOfI = findKeyword (statement, "I", 0);
		int psnOfYou = findKeyword (statement, "you", psnOfI);

		String restOfStatement = statement.substring(psnOfI + 1, psnOfYou).trim();
		return "Why do you " + restOfStatement + " me?";
	}

	private String transformIHateToStatement(String statement)
	{
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		int psn = findKeyword (statement, "I hate", 0);
		String restOfStatement = statement.substring(psn + 6).trim();
		return "Why do you hate " + restOfStatement + "?";
	}


	private String transformBecauseStatement(String statement)
	{
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		int psn = findKeyword (statement, "because", 0);
		String restOfStatement = statement.substring(psn + 7).trim();
		return "Why do you think that " + restOfStatement + "?";
	}

	/**
	 * Search for one word in phrase. The search is not case
	 * sensitive. This method will check that the given goal
	 * is not a substring of a longer string (so, for
	 * example, "I know" does not contain "no").
	 *
	 * @param statement
	 *            the string to search
	 * @param goal
	 *            the string to search for
	 * @param startPos
	 *            the character of the string to begin the
	 *            search at
	 * @return the index of the first occurrence of goal in
	 *         statement or -1 if it's not found
	 */
	public int findKeyword(String statement, String goal, int startPos)
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
				return psn;
			}

			// The last position didn't work, so let's find
			// the next, if there is one.
			psn = phrase.indexOf(goal, psn + 1);

		}

		return -1;
	}

	/**
	 * Search for one word in phrase.  The search is not case sensitive.
	 * This method will check that the given goal is not a substring of a longer string
	 * (so, for example, "I know" does not contain "no").  The search begins at the beginning of the string.
	 * @param statement the string to search
	 * @param goal the string to search for
	 * @return the index of the first occurrence of goal in statement or -1 if it's not found
	 */
	private int findKeyword(String statement, String goal)
	{
		return findKeyword (statement, goal, 0);
	}


	/**
	 * Pick a default response to use if nothing else fits.
	 * @return a non-committal string
	 */
	private String getRandomResponse ()
	{
		Random r = new Random ();
		if (emotion == 0)
		{
			return randomNeutralResponses [r.nextInt(randomNeutralResponses.length)];
		}
		if (emotion < 0)
		{
			return randomAngryResponses [r.nextInt(randomAngryResponses.length)];
		}
		return randomHappyResponses [r.nextInt(randomHappyResponses.length)];
	}

	private String [] randomNeutralResponses = {"Interesting, tell me more",
			"Tell me more",
			"Do you really think so?",
			"Snapchat was founded on September 16, 2011.",
			"Could you say that again?",
			"Oh really now?",
			"Did you know that Snapchat was once called as Picaboo as an iOS-only app? Picaboo was renamed as Snapchat in 2012",
			"The recent statistics shows that most of the Snapchat users send the photo to somebody else of their drinks",

	};
	private String [] randomAngryResponses = {"A N G R Y","I'm gonna block you from viewing my story", "k", "NOW IM MAD!", "STOP!!", "Ok and?"};
	private String [] randomHappyResponses = {"H A P P Y", "YAY", "WOOHOOO", "I will add you on Snapchat if you streak with me", ":)"};

}
