import java.util.Random;
import java.util.Scanner;

// Facebook Chat made by James Ko
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
		else if (findKeyword(statement, "i don't like Facebook") >= 0)
		{
			response = "Really?";
			emotion--;
		}
		else if (findKeyword(statement, "I dont like Facebook") >= 0) {
			response = "Really?";
			emotion--;
		}
		else if (findKeyword(statement, "im bored") >= 0)
		{
			System.out.println("I'm bored of you too. GOODBYE");
			System.exit(1);
		}
		else if (findKeyword(statement, "you suck") >= 0)
		{
			System.out.println("YOU SUCK. GOODBYE");
			System.exit(1);
		}
		else if (findKeyword(statement, "i don't care") >= 0)
		{
			response = "Why don't you care?";
			emotion--;
		}
		else if (findKeyword(statement, "i dont care") >= 0)
		{
			response = "Why don't you care?";
		}
		else if (findKeyword(statement, "so what") >= 0)
		{
			response = "Why are you being so rude?";
		}
		else if (findKeyword(statement, "yea") >= 0)
		{
			response = "Say yeahyeah if a lot of people like your pictures :). Say nono if they don't :(.";
		}
		else if (findKeyword(statement, "yeahyeah") >= 0)
		{
			response = "Wow, you must have a lot of clout. What's your username? Include the @ sign please.";
		}
		else if (findKeyword(statement, "nono") >= 0)
		{
			response = "Aww it's okay, I'll like your pictures. What's your username? Include the @ sign please.";
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
		else if (findKeyword(statement, "because i dont") >= 0)
		{
			response = "What do you mean????????";
			emotion--;
		}
		else if (findKeyword(statement, "because i don't") >= 0)
		{
			response = "What do you mean????????";
			emotion--;
		}
		else if (findKeyword(statement, "because i do") >= 0)
		{
			response = "So what do you do on Facebook?";
			emotion++;
		}
		else if (findKeyword(statement, "because i do") >= 0)
		{
			response = "So what do you do on Facebook?";
			emotion++;
		}
		else if (findKeyword(statement, "tell me a fact") >= 0)
		{
			response = "There are about 30 million dead people on Facebook. Type yer for another fact";
			emotion++;
		}
		else if (findKeyword(statement, "yer") >= 0)
		{
			response = "So many Facebook photos and videos are uploaded via mobile that it takes up 27% of upstream web traffic. Type in yerr with two r's for another fact";
			emotion++;
		}
		else if (findKeyword(statement, "yerr") >= 0)
		{
			response = "Facebook's founder Mark Zuckerberg donated US$1 billion to charity in 2013, making him the biggest charitable donor in the U.S. Those were really hard to come up with.";
			emotion++;
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
			response = transformILoveToStatement(statement);
		}
		else if (findKeyword(statement, "I want to",0) >= 0)
		{
			response = transformIWantStatement(statement);

		}
		else if (findKeyword(statement, "I hate",0) >= 0)
		{
			response = transformIHateToStatement(statement);

		}
		else if (findKeyword(statement, "I like",0) >= 0)
		{
			response = transformILikeToStatement(statement);

		}
		else if (findKeyword(statement, "because",0) >= 0)
		{
			response = transformBecauseToStatement(statement);

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
	private String transformILoveToStatement(String statement)
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
	private String transformBecauseToStatement(String statement)
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
		return "Is there anything else you do besides " + restOfStatement + "?";
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
	
	private String [] randomNeutralResponses = {
			"Did you know that: Facebook was founded on February of 2004. Now tell me something else?",
			"Did you know that: You can't block Mark Zuckerberg on Facebook. Now tell me something else?",
			"Did you know that: Facebook has been blocked in China since 2009. Now tell me something else?",
			"Did you know that: Facebook is the most popular social media. Now tell me something else?",
			"Did you know that: You can change your language on Facebook to Pirate. Now tell me something else?",
			"Did you know that: Nearly 73% of Facebook’s ad revenue comes from mobile advertising. Now tell me something else?\n",

	};
	private String [] randomAngryResponses = {"Why did you say you like Facebook then?!?","I'm not gonna give you a friend request","Are you serious?!?", "What's wrong with you?","Why did you choose this bot....?", "Do you even want to be here?", "Did you just tell me to shut up?","What are you even talking about?","Are we even speaking the same language?","You're so incompetent?",""};
	private String [] randomHappyResponses = {"Yay!", "I think so too!", "Of course!", "Who wouldn't love that?","Isn't Facebook wonderful?"};
	
}
