import java.util.Random;
import java.util.Scanner;
//Abir Ahmed
/**
 * A program to carry on conversations with a human user.
 * This version:
 * @author Brooklyn Tech CS Department
 * @version September 2018
 */
public class ChatBot1
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
		return "Do you like posting pictures on Instagram?";
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
			response = "Bro, please talk to me. Ask me to tell you a fact :)";
			emotion--;
		}
		else if (findKeyword(statement, "yeah") >= 0)
		{
			response = "You are part of over a billion users. Do you post often?";
		}
		else if (findKeyword(statement, "what") >= 0)
		{
			response = "I said Instagram is the best social media site. Say yup if you think so!";
		}
		else if (findKeyword(statement, "yup") >= 0)
		{
			response = "Right!?! Ask me to tell you a fun fact about this amazing platform.";
			emotion++;
		}
		
		else if (findKeyword(statement, "no") >= 0)
		{
			response = "Why not? :(";
		}
		else if (findKeyword(statement, "k") >= 0)
		{
			response = "k.";
			emotion--;
		}
		else if (findKeyword(statement, "tell me a fact") >= 0)
		{
			response = "Pizza is the most Instagrammed food globally, followed by Sushi. If you want another fact type in yer";
			emotion++;
		}
		else if (findKeyword(statement, "yer") >= 0)
		{
			response = "More than 400 million people use Instagram each day. Type in yerr with two r's for another fact";
			emotion++;
		}
		else if (findKeyword(statement, "yerr") >= 0)
		{
			response = "56 percent of Instagram users make $50,000/year or more.";
			emotion++;
		}
		else if (findKeyword(statement, "yes") >= 0)
		{
			response = "Me too man, I post a lot of beach selfies. You should double-tap on them!";
			emotion++;
		}
		else if (findKeyword(statement, "yea") >= 0)
		{
			response = "Say yeahyeah if a lot of people like your pictures :). Say nono if they don't :(.";
			emotion++;
		}
		else if (findKeyword(statement, "yeahyeah") >= 0)
		{
			response = "Wow, you must have a lot of clout. What's your username? Include the @ sign please.";
			emotion++;
		}
		else if (findKeyword(statement, "nono") >= 0)
		{
			response = "Aww it's okay, I'll like your pictures. What's your username? Include the @ sign please.";
			emotion--;
		}
		else if (findKeyword(statement, "@") >= 0)
		{
			response = "Nice name. I'll make sure to follow you.";
			emotion++;
		}
		else if (findKeyword(statement, "ugly") >= 0)
		{
			response = "No you're not. Just add some filters if you think so. Always works :)";
			emotion--;
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
			emotion--;
		}
		else if (findKeyword(statement, "sometimes") >= 0)
		{
			response = "Why not more often?";
			emotion--;
		}
		else if (findKeyword(statement, "maybe") >= 0)
		{
			response = "YES OR NO!!";
			emotion--;
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
			emotion--;

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
			"Did you know Instagram was founded on October of 2010",
			"Could you say that again?",
			"Oh really now?",
			"17% of teens say Instagram is the most important social media site.",
			"Instagram influencers are charging up to $100,000 for a sponsored post. Have you heard of this?",

	};
	private String [] randomAngryResponses = {"A N G R Y","I'm not gonna follow your profile", "k", "NOW IM MAD!", "STOP!!", "Ok and?"};
	private String [] randomHappyResponses = {"H A P P Y", "YAY", "WOOHOOO", "I will follow you if you follow back", "I'm gonna like all your pictures", ":)"};

}
