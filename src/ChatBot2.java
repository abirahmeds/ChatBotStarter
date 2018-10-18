import java.util.Random;
import java.util.Scanner;

/**
 * A program to carry on conversations with a human user.
 * This version:
 * @author Brooklyn Tech CS Department
 * @version September 2018
 */
public class ChatBot2
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
		return "Do you really like to use Facebook?";
		
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
			response = "Is anyone there...?";
		}

		else if (findKeyword(statement, "no") >= 0)
		{
			response = "Why not?";
			emotion--;
		}

		else if (findKeyword(statement, "yes") >= 0)
		{
			response = "Why?";
			emotion++;
		}

		else if (findKeyword(statement, "It is fun") >= 0)
		{
			response = "Yes, it is fun!";
			emotion++;
		}
		else if (findKeyword(statement, "It's fun") >= 0)
		{
			response = "Yes, it is fun!";
			emotion++;
		}
		else if (findKeyword(statement, "I don't like Facebook") >= 0)
		{
			response = "Really?";
			emotion--;
		}
		else if (findKeyword(statement, "I dont like Facebook") >= 0)
		{
			response = "Really?";
			emotion--;
		}
		else if (findKeyword(statement, "I don't care") >= 0)
		{
			response = "Why don't you care?";
			emotion--;
		}
		else if (findKeyword(statement, "I dont care") >= 0)
		{
			response = "Why don't you care?";
			emotion--;
		}
		else if (findKeyword(statement, "so what") >= 0)
		{
			response = "Why are you being so rude?";
			emotion--;
		}
		else if (findKeyword(statement, "interesting") >= 0)
		{
			response = "What's interesting about it";
			emotion++;
		}
		else if (findKeyword(statement, "no") >= 0)
		{
			response = "Why not, you can always add a filter :)";
			emotion--;
		}
		else if (findKeyword(statement, "k") >= 0)
		{
			response = "k.";
			emotion--;
		}
		else if (findKeyword(statement, "i don't know") >= 0)
		{
			response = "What do you mean????????";
			emotion--;
		}
		else if (findKeyword(statement, "i dont know") >= 0)
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
		else if (findKeyword(statement, "i could") >= 0)
		{
			response = "YES OR NO!!";
			emotion--;
		}


		// Response transforming I want to statement
		else if (findKeyword(statement, "I love to", 0) >= 0)
		{
			response = transformIWantToStatement(statement);
		}
		else if (findKeyword(statement, "I want to",0) >= 0)
		{
			response = transformIWantStatement(statement);

		}
		else if (findKeyword(statement, "I hate",0) >= 0)
		{
			response = transformIWantStatement(statement);

		}
		else if (findKeyword(statement, "I like",0) >= 0)
		{
			response = transformIWantStatement(statement);

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
		int psn = findKeyword (statement, "I love to", 0);
		String restOfStatement = statement.substring(psn + 9).trim();
		return "Why do you love to " + restOfStatement + "?";
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
		int psn = findKeyword (statement, "I want to", 0);
		String restOfStatement = statement.substring(psn + 9).trim();
		return "Why do you want to " + restOfStatement + "?";
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
	private String transformILikeToStatement(String statement)
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
		int psn = findKeyword (statement, "I like", 0);
		String restOfStatement = statement.substring(psn + 6).trim();
		return "Why do you like " + restOfStatement + "?";
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
	private int findKeyword(String statement, String goal,
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
			"You don't say.",
			"Facebook was founded on February of 2004",
			"Could you say that again?",
			"Oh really now?",
			"Facebook is the most popular social media",
			"Instagram influencers are charging up to $100,000 for a sponsored post.",

	};
	private String [] randomAngryResponses = {"Why did you say you like Facebook then?!?", "Are you serious?!?", "What's wrong with you?"};
	private String [] randomHappyResponses = {"Yay!", "I think so too!", "Of course!"};
	
}
