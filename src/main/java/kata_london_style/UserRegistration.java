package kata_london_style;

public class UserRegistration implements ContentProcessCommand
{
  @Override
  public String execute(String name)
  {
    return "OK";
  }
}
