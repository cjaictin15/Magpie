import java.util.Random;

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
  
  //expands contractions
  private String expandContraction(String statement)
  {
    String temp = statement;
    
    //expands can't <- exception
    while (findKeyword(temp, "can't") >= 0) {
      int psn = findKeyword(temp, "can't"); //the position of the contraction
      String beginning = temp.substring(0,psn); //the piece before the contraction
      String contraction = "can't"; //the contraction
      String end = temp.substring(psn + 5); // the piece after the contraction
      contraction = contraction.substring(0,contraction.length() - 2) + "not"; //expands the contraction
      temp = beginning + contraction + end; //pieces together the new contraction
    }
    
    //expands I'm <- exception
    while (findKeyword(temp, "I'm") >= 0) {
      int psn = findKeyword(temp, "I'm"); //the position of the contraction
      String beginning = temp.substring(0,psn); //the piece before the contraction
      String contraction = "I'm"; //the contraction
      String end = temp.substring(psn + 3); // the piece after the contraction
      contraction = contraction.substring(0,contraction.length() - 2) + " am"; //expands the contraction
      temp = beginning + contraction + end; //pieces together the new contraction
    }
    
    String [] nPattern = { // the words that follow the pattern <something>n't = <something> not
      "aren't",
      "couldn't",
      "didn't",
      "doesn't",
      "don't",
      "hadn't",
      "hasn't",
      "haven't",
      "shouldn't",
      "weren't",
      "wouldn't",
      "isn't",
      "mustn't",
      "mightn't"
    };
    
    //expands all the contractions with the <something>n't pattern
    for (int i = 0; i < nPattern.length; i++) {
      while (findKeyword(temp, nPattern[i]) >= 0) { //while there are still contractions
        int psn = findKeyword(temp, nPattern[i]); //the position of the contraction
        String beginning = temp.substring(0,psn); //the piece before the contraction
        String contraction = temp.substring(psn,psn + nPattern[i].length()); //the contraction
        String end = temp.substring(psn + contraction.length()); // the piece after the contraction
        contraction = contraction.substring(0,contraction.length() - 3) + " not"; //expands the contraction
        temp = beginning + contraction + end; //pieces together the new contraction
      }
    }
    
    String [] rePattern = { // the words that follow the pattern <something>'re = <something> are
      "you're",
      "we're",
      "they're"
    };
    
    //expands all the contractions with the <something>'re pattern
    for (int i = 0; i < rePattern.length; i++) {
      while (findKeyword(temp, rePattern[i]) >= 0) { //while there are still contractions
        int psn = findKeyword(temp, rePattern[i]); //the position of the contraction
        String beginning = temp.substring(0,psn); //the piece before the contraction
        String contraction = temp.substring(psn,psn + rePattern[i].length()); //the contraction
        String end = temp.substring(psn + contraction.length()); // the piece after the contraction
        contraction = contraction.substring(0,contraction.length() - 3) + " are"; //expands the contraction
        temp = beginning + contraction + end; //pieces together the new contraction
      }
    }
    
    String [] sPattern = { // the words that follow the pattern <something>'s = <something> is
      "he's",
      "she's",
      "it's",
      "that's",
      "who's",
      "what's",
      "when's",
      "where's",
      "why's",
      "how's"
    };
    
    //expands all the contractions with the <something>'s pattern
    for (int i = 0; i < sPattern.length; i++) {
      while (findKeyword(temp, sPattern[i]) >= 0) { //while there are still contractions
        int psn = findKeyword(temp, sPattern[i]); //the position of the contraction
        String beginning = temp.substring(0,psn); //the piece before the contraction
        String contraction = temp.substring(psn,psn + sPattern[i].length()); //the contraction
        String end = temp.substring(psn + contraction.length()); // the piece after the contraction
        contraction = contraction.substring(0,contraction.length() - 2) + " is"; //expands the contraction
        temp = beginning + contraction + end; //pieces together the new contraction
      }
    }
    
    String [] willPattern = { // the words that follow the pattern <something>'ll = <something> will
      "I'll",
      "you'll",
      "he'll",
      "she'll",
      "it'll",
      "we'll",
      "they'll",
      "that'll",
      "who'll",
      "what'll",
      "when'll",
      "where'll",
      "why'll",
      "how'll"
    };
    
    //expands all the contractions with the <something>'ll pattern
    for (int i = 0; i < willPattern.length; i++) {
      while (findKeyword(temp, willPattern[i]) >= 0) { //while there are still contractions
        int psn = findKeyword(temp, willPattern[i]); //the position of the contraction
        String beginning = temp.substring(0,psn); //the piece before the contraction
        String contraction = temp.substring(psn,psn + willPattern[i].length()); //the contraction
        String end = temp.substring(psn + contraction.length()); // the piece after the contraction
        contraction = contraction.substring(0,contraction.length() - 3) + " will"; //expands the contraction
        temp = beginning + contraction + end; //pieces together the new contraction
      }
    }
    
    String [] vePattern = { // the words that follow the pattern <something>'ve = <something> have
      "I've",
      "you've",
      "we've",
      "they've",
      "would've",
      "should've",
      "could've",
      "might've",
      "must've"
    };
    
    //expands all the contractions with the <something>'ve pattern
    for (int i = 0; i < vePattern.length; i++) {
      while (findKeyword(temp, vePattern[i]) >= 0) { //while there are still contractions
        int psn = findKeyword(temp, vePattern[i]); //the position of the contraction
        String beginning = temp.substring(0,psn); //the piece before the contraction
        String contraction = temp.substring(psn,psn + vePattern[i].length()); //the contraction
        String end = temp.substring(psn + contraction.length()); // the piece after the contraction
        contraction = contraction.substring(0,contraction.length() - 3) + " have"; //expands the contraction
        temp = beginning + contraction + end; //pieces together the new contraction
      }
    }
    
    return temp;
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
    return "Why do you " + restOfStatement + " me?";
  }
  
    private String transformIsStatementtoquestion(String statement)
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
    
    int psnOfIs = findKeyword (statement, "Is", 0);
    
    String restOfStatement = statement.substring(psnOfIs).trim();
    return "Why is " + restOfStatement + " ?";
  }
  
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
    return randomResponses [r.nextInt(randomResponses.length)];
  }
  
  private String [] randomResponses = {"Interesting, tell me more",
    "Hmmm.",
    "Do you really think so?",
    "You don't say.",
    "What!",
    "That's cool",
    "Really?",
    "Go on..."
  };
}