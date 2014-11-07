public class Magpie
{
  /**
   * Get a default greeting  
   * @return a greeting
   */
  public String getGreeting()
  {
    return "Hello, let's talk. Tell me about yourself..."; //user will be more inclined to talk about their pet or family
  }
  
  
  private int findKeyword(String statement, String goal, int startPos)  
  {    
    String phrase = statement.trim();    
    int psn = phrase.toLowerCase().indexOf(goal.toLowerCase(), startPos);    
    while (psn >= 0)    
    {      
      String before = " ", after = " ";      
      if (psn > 0)      
      {        
        before = phrase.substring (psn - 1, psn).toLowerCase();      
      }      
      if (psn + goal.length() < phrase.length())      
      {        
        after = phrase.substring(psn + goal.length(),                                 
                                 psn + goal.length() + 1).toLowerCase(); 
      } 
      /* determine the values of psn, before, and after at this point in the method. */ 
      if (((before.compareTo ("a") < 0 ) || (before.compareTo("z") > 0)) &&           
          ((after.compareTo ("a") < 0 ) || (after.compareTo("z") > 0)))      
      { 
        return psn; 
      }      
      psn = phrase.indexOf(goal.toLowerCase(), psn + 1);    
    } 
    return -1; 
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
    if (statement.trim().length() == 0) //varying responses for blank answers
    {
      final int blankhuman = 5;
      double r = Math.random();
      int blankreply = (int)(r * blankhuman);
      
      if (blankreply == 0) {response = "SAY SOMETHING! Or I'm giving up on you";}
      if (blankreply == 1) {response = "Why so quiet?";}
      if (blankreply == 2) {response = "Don't be shy, I won't judge you";}
      if (blankreply == 3) {response = "Ooooo... awkward silence";}
      if (blankreply == 4) {response = "You have no reply? Take your time, I'll wait...";}
      else if (blankreply == 5) {response = "...";}
      return response;
    }
    
    else if (findKeyword(statement, "no", 0) >= 0)
    {
      response = "Why so negative?";
    }
    else if (statement.indexOf("mother") >= 0
               || statement.indexOf("father") >= 0
               || statement.indexOf("sister") >= 0
               || statement.indexOf("brother") >= 0)
    {
      response = "Tell me more about your family.";
    }
    else if (findKeyword(statement, "Kiang", 0) >= 0) //varying responses for teachers
    {
      final int teacher = 2;
      double r = Math.random();
      int teacherlove = (int)(r * teacher);
      
      if (teacherlove == 0) {response = "Oh, he's a really great teacher.";}
      if (teacherlove == 1) {response = "Him? I love that guy. He's cool";}
      else if (teacherlove == 2) {response = "That guy? I like him.";}
      return response;
    }
    else if (findKeyword(statement, "Landgraf", 0) >= 0)
    {
      final int teacher = 2;
      double r = Math.random();
      int teacherlove = (int)(r * teacher);
      
      if (teacherlove == 0) {response = "Oh, he's a really great teacher.";}
      if (teacherlove == 1) {response = "Him? I love that guy. He's cool";}
      else if (teacherlove == 2) {response = "That guy? I like him.";}
      return response;
    }
    else if (findKeyword(statement,"cat", 0) >= 0) //pet responses
    {
      return "You have a cat? I have one too... His name is Binky";
    }
    else if (findKeyword(statement, "dog", 0) >= 0)
    {
      return "You have a dog? I have one too... His name is Rover";
    }
    else if (findKeyword(statement, "sport", 0) >= 0 || findKeyword(statement, "sports", 0) >= 0) //three more key words (sport, school, music)
    {
      return "Sports? What's your favorite sport and why?";
    }
    else if (findKeyword(statement, "school", 0) >= 0)
    {
      return "School? What do you like about school?";
    }
    else if (findKeyword(statement, "music", 0) >= 0)
    {
      return "I love music! What's your favorite song?";
    }
    
    // Responses which require transformations
    else if (findKeyword(statement, "I want to", 0) >= 0)
    {
      response = transformIWantToStatement(statement);
    }
    
    else
    {
      // Look for a two word (you <something> me)
      // pattern
      int psn = findKeyword(statement, "you", 0);
      
      if (psn >= 0
            && findKeyword(statement, "me", psn) >= 0)
      {
        response = transformYouMeStatement(statement);
      }
      else
      {
        response = getRandomResponse();
      }
    }
    return response;
  }
  
  /**
   * Take a statement with "I want to <something>." and transform it into 
   * "What would it mean to <something>?"
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
    return "Would you really be happy if you " + restOfStatement + "?";
  }
  
  
  
  /**
   * Take a statement with "you <something> me" and transform it into 
   * "What makes you think that I <something> you?"
   * @param statement the user statement, assumed to contain "you" followed by "me"
   * @return the transformed statement
   */
  private String transformYouMeStatement(String statement)
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
    
    int psnOfYou = findKeyword (statement, "you", 0);
    int psnOfMe = findKeyword (statement, "me", psnOfYou + 3);
    
    String restOfStatement = statement.substring(psnOfYou + 3, psnOfMe).trim();
    return "Why do you " + restOfStatement + " you?";
  }
  
  private int findKeyword(String statement, String goal)
  {
    return findKeyword (statement, goal, 0);
  }
  
  /**
   * Pick a default response to use if nothing else fits.
   * @return a non-committal string
   */
  private String getRandomResponse()
  {
    final int NUMBER_OF_RESPONSES = 6;
    double r = Math.random();
    int whichResponse = (int)(r * NUMBER_OF_RESPONSES);
    String response = "";
    
    if (whichResponse == 0)
    {
      response = "Interesting, tell me more.";
    }
    else if (whichResponse == 1)
    {
      response = "Hmmm.";
    }
    else if (whichResponse == 2)
    {
      response = "Do you really think so?";
    }
    else if (whichResponse == 3)
    {
      response = "You don't say.";
    }
    else if (whichResponse == 4) //3 more non-committal statements
    {
      response = "Really?";
    }
    else if (whichResponse == 5)
    {
      response = "What!";
    }
    else if (whichResponse == 6)
    {
      response = "That's cool!";
    }
    
    return response;
  }
}