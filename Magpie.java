public class Magpie
{
  /**
   * Get a default greeting  
   * @return a greeting
   */
  public String getGreeting()
  {
    return "Hello, let's talk.";
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
    if (statement.trim().length() == 0)
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
   
    else if(statement.indexOf("no") >= 0)
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
    else if (statement.indexOf("Kiang") >= 0
               || statement.indexOf("Landgraf") >= 0)
    {
      final int teacher = 2;
      double r = Math.random();
      int teacherlove = (int)(r * teacher);
      
      if (teacherlove == 0) {response = "Oh, he's a really great teacher.";}
      if (teacherlove == 1) {response = "Him? I love that guy. He's cool";}
      else if (teacherlove == 2) {response = "That guy? I like him.";}
      return response;
    }
    else if (statement.indexOf("cat") >= 0)
    {
      return "You have a cat? I have one too... His name is Binky";
    }
    else if (statement.indexOf("dog") >= 0)
    {
      return "You have a dog? I have one too... His name is Rover";
    }
    else
    {
      response = getRandomResponse();
    }
    return response;
  }
  
  /**
   * Pick a default response to use if nothing else fits.
   * @return a non-committal string
   */
  private String getRandomResponse()
  {
    final int NUMBER_OF_RESPONSES = 4;
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
    
    return response;
  }
}
