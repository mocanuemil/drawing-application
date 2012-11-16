package drawapp;

/**
 *
 * @author Mocanu Emil George
 */

public class ParseException extends Exception
{
  public ParseException()
  {
    super("Exception during parsing");
  }

  public ParseException(String message)
  {
    super(message);
  }
}